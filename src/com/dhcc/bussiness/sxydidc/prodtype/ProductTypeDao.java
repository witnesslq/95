package com.dhcc.bussiness.sxydidc.prodtype;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import com.dhcc.common.database.DBManager;
import com.dhcc.common.util.CommUtil;
import com.dhcc.modal.sxydidc.prodtype.Busproducttype;
import com.dhcc.modal.system.CommModel;
import com.dhcc.modal.system.PageModel;
import com.mockrunner.util.common.StringUtil;

public class ProductTypeDao {
	private static final Logger logger = Logger.getLogger(ProductTypeDao.class);
	private static DBManager dbm;  
	
	public PageModel queryProductType(PageModel pm){
		dbm=new DBManager();
		logger.info("call ProductTypeDao.queryProductType() start");
		StringBuilder querySql=new StringBuilder(" select prodtype.* from busproducttype prodtype where (prodtype.status!='99' or prodtype.status is null) order by prodtype.sort ");	
		try {
			List<ProductTypeModel> list =  dbm.getObjectList(ProductTypeModel.class, querySql.toString());
			pm.setList(list);
			logger.info("call ProductTypeDao.queryProductType() success");
		} catch (Exception e) {
			logger.info("call ProductTypeDao.queryProductType() fail");
			e.printStackTrace();
		}finally{
			dbm.close();
			dbm=null;
		}
		logger.info("call ProductTypeDao.queryProductType() finish");
		return pm;
	}
	public  List<CommModel> getSelectedProductType(String dcid) {
		String sql = " select prodtype.id AS id,prodtype.name AS text from busproducttype prodtype " +
				" LEFT JOIN busproducttype prodtype1  on prodtype1.parentid = prodtype.id" +
				" LEFT JOIN busproducttype prodtype2  on prodtype2.parentid = prodtype1.id" +
				" LEFT JOIN busproduct pro on pro.typeid = prodtype2.id" +
				" LEFT JOIN productdatacenter pdc on pdc.productid = pro.id" +
				" where (prodtype.status!='99' or prodtype.status is null) and prodtype.parentid='' and pdc.dcid='"+dcid+"' GROUP BY id ";
		DBManager dbm = null;
		List<CommModel> list = null;
		try {
		dbm = new DBManager();
		list = dbm.getObjectList(CommModel.class, sql);
		} catch (Exception e) {
			logger.error(""+e.getMessage());
		} finally {
		dbm.close();
		}
		return list;
	}
	public  List<CommModel> getSelectedProductBigType(String id,String dcid) {
		System.out.println(id+"2222222");
		String sql = " select prodtype.id AS id,prodtype.name AS text from busproducttype prodtype " +
				" LEFT JOIN busproducttype prodtype1  on prodtype1.parentid = prodtype.id" +
				" LEFT JOIN busproduct pro on pro.typeid = prodtype1.id" +
				" LEFT JOIN productdatacenter pdc on pdc.productid = pro.id" +
				" where (prodtype.status!='99' or prodtype.status is null) and prodtype.parentid='"+id+"' and pdc.dcid='"+dcid+"' GROUP BY id ";
		DBManager dbm = null;
		List<CommModel> list = null;
		try {
		dbm = new DBManager();
		list = dbm.getObjectList(CommModel.class, sql);
		} catch (Exception e) {
			logger.error(""+e.getMessage());
		} finally {
		dbm.close();
		}
		return list;
	}
	public  List<CommModel> getSelectedProductSmallType(String id,String dcid) {
		String sql = " select prodtype.id AS id,prodtype.name AS text from busproducttype prodtype " +
				"LEFT JOIN busproduct pro on pro.typeid = prodtype.id " +
				"LEFT JOIN productdatacenter pdc on pdc.productid = pro.id" +
				"  where (prodtype.status!='99' or prodtype.status is null) and prodtype.parentid='"+id+"' and pdc.dcid='"+dcid+"' GROUP BY id ";
		DBManager dbm = null;
		List<CommModel> list = null;
		try {
		dbm = new DBManager();
		list = dbm.getObjectList(CommModel.class, sql);
		} catch (Exception e) {
			logger.error(""+e.getMessage());
		} finally {
		dbm.close();
		}
		return list;
	}
	public List<ProductTypeModel> queryAllProductType(){
		dbm=new DBManager();
		List<ProductTypeModel> list=new ArrayList<ProductTypeModel>();
		logger.info("call ProductTypeDao.queryAllProductType() start");
		String querySql=" select prodtype.* from busproducttype prodtype where (prodtype.status!='99' or prodtype.status is null) ";
		try {
			list =  dbm.getObjectList(ProductTypeModel.class, querySql);
			logger.info("call ProductTypeDao.queryAllProductType() success");
		} catch (Exception e) {
			logger.info("call ProductTypeDao.queryAllProductType() fail");
			e.printStackTrace();
		}finally{
			dbm.close();
			dbm=null;
		}
		logger.info("call ProductTypeDao.queryAllProductType() finish");
		return list;		
	}
	
	public boolean deleteProductTypeByIds(String ids){
		dbm=new DBManager();
		logger.info("call ProductTypeDao.deleteProductTypeByIds() start");
		boolean result=false;
		StringBuilder deleteSql=null;
		List<String> list=new ArrayList<String>();  
			if(!StringUtil.isEmptyOrNull(ids)){
				String[] idArray=ids.split(",");
				if(idArray.length!=0){
					for(String id:idArray){
						int count=dbm.executeQueryCount(" select count(*) from busproducttype prodtype where prodtype.parentid='"+id+"' ");
						if(count==0){//叶子节点才能执行删除
							//deleteSql=new StringBuilder(" delete from busproducttype prodtype where prodtype.id in('"+id+"') ");
							deleteSql=new StringBuilder(" update busproducttype prodtype set prodtype.status='99'  where prodtype.id in('"+id+"') ");
							list.add(deleteSql.toString());
						}
					}
					try {
						result=dbm.excuteBatchSql(list); 
						logger.info("call ProductTypeDao.deleteProductTypeByIds() success");
					} catch (Exception e) {
						logger.info("call ProductTypeDao.deleteProductTypeByIds() fail");
						e.printStackTrace();
					}finally{
						dbm.close();
						dbm=null;
					}					
				}
			}
		logger.info("call ProductTypeDao.deleteProductTypeByIds() finish");
		return result;
	}
	
	public ProductTypeModel queryProductTypeById(String id){
		dbm=new DBManager();
		logger.info("call ProductTypeDao.queryProductTypeById() start");
		ProductTypeModel productTypeModel=new ProductTypeModel();
		if(!StringUtil.isEmptyOrNull(id)){
			StringBuilder querySql=new StringBuilder(" select prodtype.* from busproducttype prodtype where prodtype.id='"+id+"' ");
			try {
				Busproducttype productType =  (Busproducttype) dbm.getObject(Busproducttype.class, querySql.toString());
				BeanUtils.copyProperties(productTypeModel, productType);
				logger.info("call ProductTypeDao.queryProductTypeById() success");
			} catch (Exception e) {
				logger.info("call ProductTypeDao.queryProductTypeById() fail");
				e.printStackTrace();
			}finally{
				dbm.close();
				dbm=null;
			}
		}
		logger.info("call ProductTypeDao.queryProductTypeById() finish");
		return productTypeModel;
	}
	
	public String saveProductType(ProductTypeModel productTypeModel){
		dbm=new DBManager();
		logger.info("call ProductTypeDao.saveProductType() start");
		String id=CommUtil.getID();
		if(productTypeModel!=null){
			Busproducttype productType=new Busproducttype();
			productTypeModel.setId(id);
			try {
				BeanUtils.copyProperties(productType, productTypeModel);
				dbm.insertObject(productType, "busproducttype");
				logger.info("call ProductTypeDao.saveProductType() success");
			} catch (IllegalAccessException e) {
				logger.info("call ProductTypeDao.saveProductType() fail");
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				logger.info("call ProductTypeDao.saveProductType() fail");
				e.printStackTrace();
			}finally{
				dbm.close();
				dbm=null;
			}
		}
		logger.info("call ProductTypeDao.saveProductType() finish");
		return id;
	}
	
	public boolean updateProductType(ProductTypeModel productTypeModel){
		dbm=new DBManager();
		logger.info("call ProductTypeDao.updateProductType() start");
		boolean result=false;
		if(!StringUtil.isEmptyOrNull(productTypeModel.getId())){
			Busproducttype productType=new Busproducttype();
			try {
				BeanUtils.copyProperties(productType, productTypeModel);
				result=dbm.updateObject(productType); 
				logger.info("call ProductTypeDao.updateProductType() success");
			} catch (IllegalAccessException e) {
				result=false;
				logger.info("call ProductTypeDao.updateProductType() fail");
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				result=false;
				logger.info("call ProductTypeDao.updateProductType() fail");
				e.printStackTrace();
			}finally{
				dbm.close();
				dbm=null;
			}
		}
		logger.info("call ProductTypeDao.updateProductType() finish");
		return result;		
	}
}
