package com.dhcc.bussiness.sxydidc.product;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import com.dhcc.common.database.DBManager;
import com.dhcc.common.system.page.PageFactory;
import com.dhcc.common.util.CommUtil;
import com.dhcc.common.util.DataCenterUtil;
import com.dhcc.modal.sxydidc.prodtype.Busproducttype;
import com.dhcc.modal.sxydidc.product.Busproduct;
import com.dhcc.modal.sxydidc.product.ProductDatacenter;
import com.dhcc.modal.sxydidc.rsdatacenter.Rsdatacenter;
import com.dhcc.modal.system.PageModel;
import com.dhcc.modal.system.Tsdict;
import com.dhcc.modal.system.Tsrole;
import com.mockrunner.util.common.StringUtil;
import com.opensymphony.xwork2.ActionContext;

public class ProductDao {
	private static final Logger logger = Logger.getLogger(ProductDao.class);
	private static DBManager dbm; 
	private static DBManager dbm1;  
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	public PageModel queryProduct(PageModel pm){
		dbm=new DBManager();
		logger.info("call ProductDao.queryProduct() start");
		StringBuilder querySql=new StringBuilder(" select prod.*,GROUP_CONCAT(rd.name) as dcname,prodtype.name as typename,area.corpname as regionname,user.username as createname from busproduct prod " +
				"left join busproducttype prodtype on prod.typeid=prodtype.id " +
				"left join tscorp area on prod.regionid=area.id " +
				"left join tsuser user on prod.createrid=user.id " +
				"left join productdatacenter pdc on pdc.productid = prod.id " +
				"LEFT JOIN rsdatacenter rd ON rd.id=pdc.dcid " +
				"where (prod.status!='99' or prod.status is null) group by prod.id");
		try {
			int count =dbm.executeQueryCount(" select count(*) from ("+querySql.toString()+")temp");
			int total = count % pm.getPerPage() == 0 ? count /pm.getPerPage() : count / pm.getPerPage() + 1;
			pm.setTotalPage(total);
			pm.setTotalRecord(count);
			
			/**
			 * 分页sql构造
			 */
			PageFactory pageFactory = new PageFactory();
			String sql = pageFactory.createPageSQL(querySql.toString(), pm);
			pageFactory = null;

			List<ProductModel> list =  dbm.getObjectList(ProductModel.class, sql);
			pm.setList(list);
			logger.info("call ProductDao.queryProduct() success");
		} catch (Exception e) {
			logger.info("call ProductDao.queryProduct() fail");
			e.printStackTrace();
		}finally{
			dbm.close();
			dbm=null;
		}
		logger.info("call ProductDao.queryProduct() finish");
		return pm;
	}
	
	public PageModel queryProductByDcid(PageModel pm,String needRoleFilter){
		dbm=new DBManager();
		logger.info("call ProductDao.queryProduct() start");
		StringBuilder querySql=new StringBuilder(" select prod.*,GROUP_CONCAT(rd.name) as dcname,prodtype.name as typename,area.corpname as regionname,user.username as createname from busproduct prod " +
				"left join busproducttype prodtype on prod.typeid=prodtype.id " +
				"left join tscorp area on prod.regionid=area.id " +
				"left join tsuser user on prod.createrid=user.id " +
				"left join productdatacenter pdc on pdc.productid = prod.id " +
				"LEFT JOIN rsdatacenter rd ON rd.id=pdc.dcid " +
				"where (prod.status!='99' or prod.status is null) and (pdc.dcid='"+DataCenterUtil.getDCID()+"' ");	
		System.out.println(needRoleFilter+"DataCenterUtil.queryAllData()========"+DataCenterUtil.queryAllData());
		
		if(Boolean.parseBoolean(needRoleFilter)&&DataCenterUtil.queryAllData()){
			querySql.append(" or 1=1 ");
		}
		querySql.append(" ) GROUP  BY prod.id ");
		try {
			
			int count =dbm.executeQueryCount(" select count(*) from ("+querySql.toString()+")temp");
			
			int total = count % pm.getPerPage() == 0 ? count /pm.getPerPage() : count / pm.getPerPage() + 1;
			pm.setTotalPage(total);
			pm.setTotalRecord(count);
			
			/**
			 * 分页sql构造
			 */
			PageFactory pageFactory = new PageFactory();
			String sql = pageFactory.createPageSQL(querySql.toString(), pm);
			pageFactory = null;

			List<ProductModel> list =  dbm.getObjectList(ProductModel.class, sql);
			pm.setList(list);
			logger.info("call ProductDao.queryProduct() success");
		} catch (Exception e) {
			logger.info("call ProductDao.queryProduct() fail");
			e.printStackTrace();
		}finally{
			dbm.close();
			dbm=null;
		}
		logger.info("call ProductDao.queryProduct() finish");
		return pm;
	}
	
	public List<ProductModel> queryProByflownum(String flownumber){
		
		DBManager dbManager = new DBManager();
		String sql = "select t.id,fcus.dcid,GROUP_CONCAT(rd.name) as dcname,n.amount,t.property,t.remark,t.sla,t.discount,t.depict,t.name,t.price,FROM_UNIXTIME(t.createtime/1000,'%Y-%m-%d %H:%i:%s') as createtime ,(select area.corpname from tscorp area where t.regionid=area.id) as regionname,(select user.username from tsuser user where t.createrid=user.id) as createname,(select prodtype.name from busproducttype prodtype where t.typeid=prodtype.id) as typename " +
					" from  busproduct t LEFT JOIN flowproduct n on n.proid=t.id " +
					"  LEFT JOIN flowcustomer fcus on fcus.busid=n.busid " +
					" LEFT JOIN rsdatacenter rd ON rd.id=fcus.dcid " +
					" where t.id in (select n.proid from flowproduct n where n.busid='"+flownumber+"')" +
					" and n.busid = '"+flownumber+"' GROUP BY t.id";
		List<ProductModel> list = new ArrayList<ProductModel>();
		try {
			list = dbManager.getObjectList(ProductModel.class, sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbManager.close();
		}
		
		return list;
		
		
	}

	public String findcodebyid(String id){
		DBManager dbm = new DBManager();
		String sql= "SELECT code from busproducttype where id='"+id+"'";
		Busproducttype set =  (Busproducttype) dbm.getObject(Busproducttype.class, sql);
		dbm.close();
		String code=set.getCode();
		return code;
		
	}
	
	public List<ProductModel> queryProBytypeid(String code,String dcid){
		DBManager dbManager = new DBManager();
		System.out.println("222222"+code);
		String sql ="select bt.name as typename,t.id,t.property,t.remark,t.sla,t.discount,t.depict,t.name,t.price from  busproduct t LEFT JOIN productdatacenter pdc on pdc.productid=t.id LEFT JOIN  busproducttype bt ON bt.id=t.typeid where t.typeid in ( SELECT type.id from busproducttype type where type.code like'"+code+"%') and pdc.dcid='"+dcid+"' order by t.sort";
		List<ProductModel> list = new ArrayList<ProductModel>();
		try {
			list = dbManager.getObjectList(ProductModel.class, sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbManager.close();
		}
	
		return list;
		
		
	}

	public List<ProductModel> queryPro(String dcid){	

		DBManager dbManager = new DBManager();
		String sql = "select prod.*,prodtype.name as typename,area.corpname as regionname,FROM_UNIXTIME(prod.createtime/1000,'%Y-%m-%d %H:%i:%s') as createtime,user.username as createname from busproduct prod left join busproducttype prodtype on prod.typeid=prodtype.id left join tscorp area on prod.regionid=area.id left join tsuser user on prod.createrid=user.id left join productdatacenter pdc on pdc.productid = prod.id where (prod.status!='99' or prod.status is null) and pdc.dcid='"+dcid+"' GROUP BY prod.id  ";
		List<ProductModel> list = new ArrayList<ProductModel>();
		try {
			list = dbManager.getObjectList(ProductModel.class, sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbManager.close();
		}
		return list;
	}
	
	public List<ProductModel>  queryquickPro(String name){
	DBManager dbManager = new DBManager();
	String sql = " select prod.*,prodtype.name as typename,area.corpname as regionname,FROM_UNIXTIME(prod.createtime/1000,'%Y-%m-%d %H:%i:%s') as createtime,user.username as createname from busproduct prod left join busproducttype prodtype on prod.typeid=prodtype.id left join tscorp area on prod.regionid=area.id left join tsuser user on prod.createrid=user.id where prod.status!='99' or prod.status is null ";
	List<ProductModel> list = new ArrayList<ProductModel>();
	if(!StringUtil.isEmptyOrNull(name)){
		sql = sql+" and prodtype.name like '%"+name+"%'";
	}
	try {
		list = dbManager.getObjectList(ProductModel.class, sql);
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		dbManager.close();
	}
	
	return list;
	
	
}
	
	public PageModel queryProductByCondition(PageModel pm,ProductModel product){
		dbm=new DBManager();
		logger.info("call ProductDao.queryProductByCondition() start");
		String countSql="  select count(*) from busproduct prod left join busproducttype prodtype on prod.typeid=prodtype.id left join tscorp area on prod.regionid=area.id left join tsuser user on prod.createrid=user.id where (prod.status!='99' or prod.status is null) ";
		String querySql="  select prod.*,prodtype.name as typename,area.corpname as regionname,FROM_UNIXTIME(prod.createtime/1000,'%Y-%m-%d %H:%i:%s') as createtime,user.username as createname from busproduct prod left join busproducttype prodtype on prod.typeid=prodtype.id left join tscorp area on prod.regionid=area.id left join tsuser user on prod.createrid=user.id where (prod.status!='99' or prod.status is null) ";
		StringBuilder conditionSql=new StringBuilder();
		if(!StringUtil.isEmptyOrNull(product.getName())){
			conditionSql.append(" and prod.name like '%").append(product.getName()).append("%' ");
		}
		
		if(!StringUtil.isEmptyOrNull(product.getTypeid())){
			conditionSql.append(" and prod.typeid='").append(product.getTypeid()).append("' ");
		}
		
		if(!StringUtil.isEmptyOrNull(product.getEnabled())){
			conditionSql.append(" and prod.enabled='").append(product.getEnabled()).append("' ");
		}
		
		if(!StringUtil.isEmptyOrNull(product.getCreaterid())){
			conditionSql.append(" and prod.createrid='").append(product.getCreaterid()).append("' ");
		}		
		
		if(!StringUtil.isEmptyOrNull(product.getRegionid())){
			conditionSql.append(" and prod.regionid='").append(product.getRegionid()).append("' ");
		}
		countSql=countSql+conditionSql.toString();
		querySql=querySql+conditionSql.toString();
		querySql=querySql+" order by prod.createtime desc ";
		try {
			int count =dbm.executeQueryCount(countSql);
			int total = count % pm.getPerPage() == 0 ? count /pm.getPerPage() : count / pm.getPerPage() + 1;
			pm.setTotalPage(total);
			pm.setTotalRecord(count);
			
			/**
			 * 分页sql构造
			 */
			PageFactory pageFactory = new PageFactory();
			String sql = pageFactory.createPageSQL(querySql, pm);
			pageFactory = null;
			
			List<ProductModel> list =  dbm.getObjectList(ProductModel.class, sql);
			pm.setList(list);
			logger.info("call ProductDao.queryProductByCondition() success");
			
		} catch (Exception e) {
			logger.info("call ProductDao.queryProductByCondition() fail");
			e.printStackTrace();
		}finally{
			dbm.close();
			dbm=null;
		}
		logger.info("call ProductDao.queryProductByCondition() finish");
		return pm;		
	}
	
	public PageModel queryProductByConditionByDcid(PageModel pm,ProductModel product,String needRoleFilter){
		dbm=new DBManager();
		logger.info("call ProductDao.queryProductByCondition() start");
		String countSql="  select count(*) from busproduct prod left join busproducttype prodtype on prod.typeid=prodtype.id left join tscorp area on prod.regionid=area.id left join tsuser user on prod.createrid=user.id left join productdatacenter pdc on pdc.productid = prod.id where (prod.status!='99' or prod.status is null) and pdc.dcid='"+DataCenterUtil.getDCID()+"' ";
		StringBuilder querySql= new StringBuilder("  select prod.*,prodtype.name as typename,area.corpname as regionname,FROM_UNIXTIME(prod.createtime/1000,'%Y-%m-%d %H:%i:%s') as createtime,user.username as createname from busproduct prod left join busproducttype prodtype on prod.typeid=prodtype.id left join tscorp area on prod.regionid=area.id left join tsuser user on prod.createrid=user.id left join productdatacenter pdc on pdc.productid = prod.id  where (prod.status!='99' or prod.status is null) and ( pdc.dcid='"+DataCenterUtil.getDCID()+"' ");
		if(Boolean.parseBoolean(needRoleFilter)&&DataCenterUtil.queryAllData()){
			querySql.append(" or 1=1 ");
		}
		querySql.append(") ");
		StringBuilder conditionSql=new StringBuilder();
		if(!StringUtil.isEmptyOrNull(product.getName())){
			conditionSql.append(" and prod.name like '%").append(product.getName()).append("%' ");
		}
		
		if(!StringUtil.isEmptyOrNull(product.getTypeid())){
			conditionSql.append(" and prod.typeid='").append(product.getTypeid()).append("' ");
		}
		
		if(!StringUtil.isEmptyOrNull(product.getEnabled())){
			conditionSql.append(" and prod.enabled='").append(product.getEnabled()).append("' ");
		}
		
		if(!StringUtil.isEmptyOrNull(product.getCreaterid())){
			conditionSql.append(" and prod.createrid='").append(product.getCreaterid()).append("' ");
		}		
		
		if(!StringUtil.isEmptyOrNull(product.getRegionid())){
			conditionSql.append(" and prod.regionid='").append(product.getRegionid()).append("' ");
		}
		countSql=countSql+conditionSql.toString();
		querySql=querySql.append(conditionSql.toString())  ;
		querySql=querySql.append(" order by prod.createtime desc ");
		try {
			int count =dbm.executeQueryCount(countSql);
			int total = count % pm.getPerPage() == 0 ? count /pm.getPerPage() : count / pm.getPerPage() + 1;
			pm.setTotalPage(total);
			pm.setTotalRecord(count);
			
			/**
			 * 分页sql构造
			 */
			PageFactory pageFactory = new PageFactory();
			String sql = pageFactory.createPageSQL(querySql.toString(), pm);
			pageFactory = null;
			
			List<ProductModel> list =  dbm.getObjectList(ProductModel.class, sql);
			pm.setList(list);
			logger.info("call ProductDao.queryProductByCondition() success");
			
		} catch (Exception e) {
			logger.info("call ProductDao.queryProductByCondition() fail");
			e.printStackTrace();
		}finally{
			dbm.close();
			dbm=null;
		}
		logger.info("call ProductDao.queryProductByCondition() finish");
		return pm;		
	}
	
	
	public boolean deleteProductByIds(String ids){
		dbm=new DBManager();
		logger.info("call ProductDao.deleteProductByIds() start");
		boolean result=false;
		StringBuilder deleteSql=null;
		List<String> list=new ArrayList<String>();  
			if(!StringUtil.isEmptyOrNull(ids)){
				if(ids.indexOf(",")!=-1){//如果是批量删除
					ids=ids.replaceAll(",", "','");
				}
				deleteSql=new StringBuilder(" update busproduct set status='99' where id in('"+ids+"') ");
				list.add(deleteSql.toString());
				try {
					result=dbm.excuteBatchSql(list); 
					logger.info("call ProductDao.deleteProductByIds() success");
				} catch (Exception e) {
					logger.info("call ProductDao.deleteProductByIds() fail");
					e.printStackTrace();
				}finally{
					dbm.close();
					dbm=null;
				}
			}
		logger.info("call ProductDao.deleteProductByIds() finish");
		return result;
	}
	
	public ProductModel queryProductById(String id){
		dbm=new DBManager();
		logger.info("call ProductDao.queryProductById() start");
		ProductModel productModel=new ProductModel();
		if(!StringUtil.isEmptyOrNull(id)){
			StringBuilder querySql=new StringBuilder(" select prod.*,prodtype.name as typename,area.corpname as regionname,FROM_UNIXTIME(prod.createtime/1000,'%Y-%m-%d %H:%i:%s') as createtime,user.username as createname from busproduct prod left join busproducttype prodtype on prod.typeid=prodtype.id left join tscorp area on prod.regionid=area.id left join tsuser user on prod.createrid=user.id  where  prod.id ='"+id+"' ");
			
			String sql1="select t.*  from rsdatacenter t where t.id in (SELECT dcid from productdatacenter where productid='"+id+"')";
			
			try {
				productModel =  (ProductModel) dbm.getObject(ProductModel.class, querySql.toString());
				
				List<Rsdatacenter> rsdc = dbm.getObjectList(Rsdatacenter.class, sql1);
				String dcid="";
				String dcname="";
				for(int i=0;i<rsdc.size();i++){
					dcid+=rsdc.get(i).getId();
					dcname+=rsdc.get(i).getName();
					if(i<rsdc.size()-1){
						dcid+=";";
						dcname+=";";
					}
				}
				productModel.setDcid(dcid);
				productModel.setDcname(dcname);
				if(StringUtil.isEmptyOrNull(productModel.getCreatetime())){
					productModel.setCreatetime(sdf.format(new Date(System.currentTimeMillis())));
				}else{
					productModel.setCreatetime(sdf.format(sdf.parse(productModel.getCreatetime())));
				}
				
				logger.info("call ProductDao.queryProductById() success");
			} catch (Exception e) {
				logger.info("call ProductDao.queryProductById() fail");
				e.printStackTrace();
			}finally{
				dbm.close();
				dbm=null;
			}
		}
		logger.info("call ProductDao.queryProductById() finish");
		return productModel;
	}
	
	public String saveProduct(ProductModel productModel){
		dbm=new DBManager();
		dbm1=new DBManager();
		logger.info("call ProductDao.saveProduct() start");
		String sql="";
		String id=CommUtil.getID();
		
		if(productModel!=null){
			Busproduct product=new Busproduct();
			ProductDatacenter productDatacenter = new ProductDatacenter();
			Date now=new Date();
			productModel.setId(id);
			
			
			if(StringUtil.isEmptyOrNull(productModel.getCreatetime())){
				productModel.setCreatetime(String.valueOf(now.getTime()));
			}else{
				productModel.setCreatetime(String.valueOf(new Date(productModel.getCreatetime()).getTime()));
			}
				
			try {
				BeanUtils.copyProperties(product, productModel);
				String createrid=ActionContext.getContext().getSession().get("userid").toString();
				product.setCreaterid(createrid);
				dbm.insertObject(product, "busproduct");
				
				
				String dcid[] = productModel.getDcid().split(";");
				for(String s : dcid){
					String id_dc = CommUtil.getID();
					sql = "insert into productdatacenter(id,productid,dcid) values('"+id_dc+"','"+id+"','"+s+"');";
					dbm1.addBatch(sql);
				}
				dbm1.executeBatch();
				logger.info("call ProductDao.saveProduct() success");
			} catch (IllegalAccessException e) {
				logger.info("call ProductDao.saveProduct() fail");
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				logger.info("call ProductDao.saveProduct() fail");
				e.printStackTrace();
			}finally{
				dbm.close();
				dbm=null;
				dbm1.close();
				dbm1=null;
			}
		}
		logger.info("call ProductDao.saveProduct() finish");
		return id;
	}
	
	public boolean updateProduct(ProductModel productModel){
		dbm=new DBManager();
		logger.info("call ProductDao.updateProduct() start");
		boolean result=false;
		List<String> list=new ArrayList<String>();
		String delsql = "delete  from productdatacenter where productid='"+productModel.getId()+"'";
		list.add(delsql);
		
		String dcid[] = productModel.getDcid().split(";");
		for(String s : dcid){
			String id_dc = CommUtil.getID();
			String sql="insert into productdatacenter(id,productid,dcid) values('"+id_dc+"','"+productModel.getId()+"','"+s+"');";
			list.add(sql);
		}
		
		
		if(!StringUtil.isEmptyOrNull(productModel.getId())){
			Busproduct product=new Busproduct();
			Date now=new Date();
			if(StringUtil.isEmptyOrNull(productModel.getCreatetime())){
				productModel.setCreatetime(String.valueOf(now.getTime()));
			}else{
				try {
					productModel.setCreatetime(String.valueOf(sdf.parse(productModel.getCreatetime()).getTime()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			try {
				BeanUtils.copyProperties(product, productModel);
				result=dbm.excuteBatchSql(list); 
				result=dbm.updateObject(product); 
				logger.info("call ProductDao.updateProduct() success");
			} catch (IllegalAccessException e) {
				result=false;
				logger.info("call ProductDao.updateProduct() fail");
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				result=false;
				logger.info("call ProductDao.updateProduct() fail");
				e.printStackTrace();
			}finally{
				dbm.close();
				dbm=null;
			}
		}
		logger.info("call ProductDao.updateProduct() finish");
		return result;		
	}
	
	public PageModel quickSearch(PageModel pm,String key){
		dbm=new DBManager();
		logger.info("call ProductDao.quickSearch() start");
		List<Tsdict> dics=new ArrayList<Tsdict>();
		StringBuilder keys=new StringBuilder("");
		String countSql="  select count(*) from busproduct prod left join busproducttype prodtype on prod.typeid=prodtype.id left join tscorp area on prod.regionid=area.id left join tsuser user on prod.createrid=user.id where (prod.status!='99' or prod.status is null) ";
		String querySql=" select prod.*,GROUP_CONCAT(rd.name) as dcname,prodtype.name as typename,area.corpname as regionname,user.username as createname from busproduct prod " +
				"left join busproducttype prodtype on prod.typeid=prodtype.id " +
				"left join tscorp area on prod.regionid=area.id " +
				"left join tsuser user on prod.createrid=user.id " +
				"left join productdatacenter pdc on pdc.productid = prod.id " +
				"LEFT JOIN rsdatacenter rd ON rd.id=pdc.dcid " +
				"where (prod.status!='99' or prod.status is null)  ";
		StringBuilder conditionSql=new StringBuilder();
		if(!StringUtil.isEmptyOrNull(key)){
			conditionSql.append(" prod.name like '%").append(key).append("%' ");
			conditionSql.append(" or prodtype.name like '%").append(key).append("%' ");
			dics=this.queryDicIds(dbm, key);
			for(Tsdict dic:dics){
				if(dics.lastIndexOf(dic)!=dics.size()-1){
					keys.append("'").append(dic.getDkey()).append("',");
				}else{
					keys.append("'").append(dic.getDkey()).append("'");
				}
			}			
			
			if(!StringUtil.isEmptyOrNull(keys.toString())){
				conditionSql.append(" or prod.property in(").append(keys.toString()).append(") ");
			}
		}
		
		countSql=countSql+" and ("+conditionSql.toString()+") ";
		querySql=querySql+" and ("+conditionSql.toString()+") ";
		querySql=querySql+" group by prod.id order by prod.createtime desc ";
		try {
			int count =dbm.executeQueryCount(countSql);
			int total = count % pm.getPerPage() == 0 ? count /pm.getPerPage() : count / pm.getPerPage() + 1;
			pm.setTotalPage(total);
			pm.setTotalRecord(count);
			
			/**
			 * 分页sql构造
			 */
			PageFactory pageFactory = new PageFactory();
			String sql = pageFactory.createPageSQL(querySql, pm);
			pageFactory = null;
			
			List<ProductModel> list =  dbm.getObjectList(ProductModel.class, sql);
			pm.setList(list);
			logger.info("call ProductDao.quickSearch() success");
			
		} catch (Exception e) {
			logger.info("call ProductDao.quickSearch() fail");
			e.printStackTrace();
		}finally{
			dbm.close();
			dbm=null;
		}
		logger.info("call ProductDao.quickSearch() finish");
		return pm;		
	}
	
	private List<Tsdict> queryDicIds(DBManager dbm,String dvalue){
		List<Tsdict> list=new ArrayList<Tsdict>();
		try {
			list = dbm.getObjectList(Tsdict.class, " select dkey from tsdict where dvalue like '%"+dvalue+"%' ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
