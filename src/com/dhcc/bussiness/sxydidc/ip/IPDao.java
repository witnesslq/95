package com.dhcc.bussiness.sxydidc.ip;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import com.dhcc.common.database.DBManager;
import com.dhcc.common.system.page.PageFactory;
import com.dhcc.common.util.CommUtil;
import com.dhcc.common.util.DataCenterUtil;
import com.dhcc.modal.sxydidc.ip.Rsip;
import com.dhcc.modal.system.PageModel;
import com.dhcc.modal.system.Tsdict;
import com.mockrunner.util.common.StringUtil;

public class IPDao {
	private static final Logger logger = Logger.getLogger(IPDao.class);
	private static DBManager dbm;  

	
	public PageModel queryIP(PageModel pm,String needRoleFilter){
		dbm=new DBManager();
		logger.info("call IPDao.queryIP() start");
		StringBuilder countSql=new StringBuilder(" select count(*) from rsip ip left join busccustomer cust on ip.customerid=cust.id left join rsipseg seg on ip.ipsegid=seg.id left join rsdevice device on ip.deviceid=device.id left join rsdatacenter dc on ip.dcid=dc.id where (ip.status!='99' or ip.status is null) and (ip.dcid='"+DataCenterUtil.getDCID()+"' ");
		StringBuilder querySql=new StringBuilder(" select ip.*,cust.name as customername,dc.name as dcname,seg.name as ipsegname,device.name as devicename from rsip ip left join busccustomer cust on ip.customerid=cust.id left join rsipseg seg on ip.ipsegid=seg.id left join rsdevice device on ip.deviceid=device.id left join rsdatacenter dc on ip.dcid=dc.id where (ip.status!='99' or ip.status is null) and (ip.dcid='"+DataCenterUtil.getDCID()+"' ");
		if(Boolean.parseBoolean(needRoleFilter)&&DataCenterUtil.queryAllData()){
			countSql.append(" or 1=1 ");
			querySql.append(" or 1=1 ");
		}
		countSql.append(") ");
		querySql.append(") ");
		querySql.append(" order by ip.ipsegid,ip.ipadd desc ");
		try {
			int count =dbm.executeQueryCount(countSql.toString());
			int total = count % pm.getPerPage() == 0 ? count /pm.getPerPage() : count / pm.getPerPage() + 1;
			pm.setTotalPage(total);
			pm.setTotalRecord(count);
			
			/**
			 * 分页sql构造
			 */
			PageFactory pageFactory = new PageFactory();
			String sql = pageFactory.createPageSQL(querySql.toString(), pm);
			pageFactory = null;

			List<IPModel> list =  dbm.getObjectList(IPModel.class, sql);
			pm.setList(list);
			logger.info("call IPDao.queryIP() success");
		} catch (Exception e) {
			logger.info("call IPDao.queryIP() fail");
			e.printStackTrace();
		}finally{
			dbm.close();
			dbm=null;
		}
		logger.info("call IPDao.queryIP() finish");
		return pm;
	}
	/**
	 * 资源调度页面查询ip
	 * */
	public PageModel queryFreeIP(PageModel pm,String cusid,String ipsegid,String dcid){
		dbm=new DBManager();
		logger.info("call IPDao.queryIP() start");
		StringBuilder countSql=new StringBuilder(" select count(*) from rsip ip left join busccustomer cust on ip.customerid=cust.id left join rsipseg seg on ip.ipsegid=seg.id left join rsdevice device on ip.deviceid=device.id where (ip.status='01' or (ip.status = '02' and ip.customerid ='"+cusid+"')) and ip.dcid='"+dcid+"'  ");
		StringBuilder querySql=new StringBuilder(" select ip.*,cust.name as customername,seg.name as ipsegname,device.name as devicename from rsip ip left join busccustomer cust on ip.customerid=cust.id left join rsipseg seg on ip.ipsegid=seg.id left join rsdevice device on ip.deviceid=device.id where (ip.status='01' or (ip.status = '02' and ip.customerid ='"+cusid+"')) and ip.dcid='"+dcid+"' ");	
		
		if(!StringUtil.isEmptyOrNull(ipsegid)){
			String ipsegidsArr[] = ipsegid.split(",");
			String ipsegids = "";
			for(int i=0;i<ipsegidsArr.length;i++){
				if(!StringUtil.isEmptyOrNull(ipsegidsArr[i])){
					ipsegids += "'"+ipsegidsArr[i]+"'";
					if(i < ipsegidsArr.length - 1){
						ipsegids += ",";
					}
				}
			}
			if(!StringUtil.isEmptyOrNull(ipsegids)){
				countSql.append(" and ip.ipsegid not in ("+ipsegids+")");
				querySql.append(" and ip.ipsegid not in ("+ipsegids+")");
			}
		}
		querySql.append(" order by ip.ipsegid,ip.ipadd ASC ");
		try {
			int count =dbm.executeQueryCount(countSql.toString());
			int total = count % pm.getPerPage() == 0 ? count /pm.getPerPage() : count / pm.getPerPage() + 1;
			pm.setTotalPage(total);
			pm.setTotalRecord(count);
			
			/**
			 * 分页sql构造
			 */
			PageFactory pageFactory = new PageFactory();
			String sql = pageFactory.createPageSQL(querySql.toString(), pm);
			pageFactory = null;

			List<IPModel> list =  dbm.getObjectList(IPModel.class, sql);
			pm.setList(list);
			logger.info("call IPDao.queryIP() success");
		} catch (Exception e) {
			logger.info("call IPDao.queryIP() fail");
			e.printStackTrace();
		}finally{
			dbm.close();
			dbm=null;
		}
		logger.info("call IPDao.queryIP() finish");
		return pm;
	}
	
	/**
	 * 资源调度页面查询ip
	 * */
	public PageModel quickFreeSearch(PageModel pm,String cusid ,String username,String ipsegid){
		dbm=new DBManager();
		logger.info("call IPDao.queryIP() start");
		String countSql=" select count(*) from rsip ip left join busccustomer cust on ip.customerid=cust.id left join rsipseg seg on ip.ipsegid=seg.id left join rsdevice device on ip.deviceid=device.id where (ip.status='01' or (ip.status = '02' and ip.customerid ='"+cusid+"')) ";
		String querySql=" select ip.*,cust.name as customername,seg.name as ipsegname,device.name as devicename from rsip ip left join busccustomer cust on ip.customerid=cust.id left join rsipseg seg on ip.ipsegid=seg.id left join rsdevice device on ip.deviceid=device.id where (ip.status='01' or (ip.status = '02' and ip.customerid ='"+cusid+"'))  ";
		if(!StringUtil.isEmptyOrNull(username)){
			querySql = querySql+" and ip.ipadd like '%"+username+"%'";
			countSql = countSql+" and ip.ipadd like '%"+username+"%'";
		}
		if(!StringUtil.isEmptyOrNull(ipsegid)){
			String ipsegidsArr[] = ipsegid.split(",");
			String ipsegids = "";
			for(int i=0;i<ipsegidsArr.length;i++){
				if(!StringUtil.isEmptyOrNull(ipsegidsArr[i])){
					ipsegids += "'"+ipsegidsArr[i]+"'";
					if(i < ipsegidsArr.length - 1){
						ipsegids += ",";
					}
				}
			}
			if(!StringUtil.isEmptyOrNull(ipsegids)){
				countSql += " and ip.ipsegid not in ("+ipsegids+")";
				querySql += " and ip.ipsegid not in ("+ipsegids+")";
			}
		}
		querySql = querySql+" order by ip.ipsegid,ip.ipadd ASC";
		System.out.println(querySql);
		try {
			int count =dbm.executeQueryCount(countSql.toString());
			int total = count % pm.getPerPage() == 0 ? count /pm.getPerPage() : count / pm.getPerPage() + 1;
			pm.setTotalPage(total);
			pm.setTotalRecord(count);
			
			/**
			 * 分页sql构造
			 */
			PageFactory pageFactory = new PageFactory();
			String sql = pageFactory.createPageSQL(querySql.toString(), pm);
			pageFactory = null;

			List<IPModel> list =  dbm.getObjectList(IPModel.class, sql);
			pm.setList(list);
			logger.info("call IPDao.queryIP() success");
		} catch (Exception e) {
			logger.info("call IPDao.queryIP() fail");
			e.printStackTrace();
		}finally{
			dbm.close();
			dbm=null;
		}
		logger.info("call IPDao.queryIP() finish");
		return pm;
	}
	
	
	public PageModel queryIPByCondition(PageModel pm,IPModel iPModel,boolean needDevice,String needRoleFilter){
		dbm=new DBManager();
		logger.info("call ContractDao.queryContractByCondition() start");
		StringBuilder countSql=new StringBuilder("  select count(*) from rsip ip left join busccustomer cust on ip.customerid=cust.id left join rsipseg seg on ip.ipsegid=seg.id left join rsdevice device on ip.deviceid=device.id left join rsdatacenter dc on ip.dcid=dc.id where (ip.dcid='"+DataCenterUtil.getDCID()+"'  ");
		StringBuilder querySql=new StringBuilder("  select ip.*,cust.name as customername,dc.name as dcname,seg.name as ipsegname,device.name as devicename from rsip ip left join busccustomer cust on ip.customerid=cust.id left join rsipseg seg on ip.ipsegid=seg.id left join rsdevice device on ip.deviceid=device.id left join rsdatacenter dc on ip.dcid=dc.id where (ip.dcid='"+DataCenterUtil.getDCID()+"'  ");
		
		if(Boolean.parseBoolean(needRoleFilter)&&DataCenterUtil.queryAllData()){
			countSql.append(" or 1=1 ");
			querySql.append(" or 1=1 ");
		}
		countSql.append(") ");
		querySql.append(") ");
		
		StringBuilder conditionSql=new StringBuilder();
		if(!StringUtil.isEmptyOrNull(iPModel.getIpadd())){
			conditionSql.append(" and ip.ipadd like '%").append(iPModel.getIpadd()).append("%' ");
		}
		
		if(!StringUtil.isEmptyOrNull(iPModel.getStatus())){
			conditionSql.append(" and ip.status='").append(iPModel.getStatus()).append("' ");
		}else{
			conditionSql.append(" and (ip.status!='99' or ip.status is null) ");
		}
		
		if(!StringUtil.isEmptyOrNull(iPModel.getIpsegid())){
			conditionSql.append(" and ip.ipsegid='").append(iPModel.getIpsegid()).append("' ");
		}
		
		if(!StringUtil.isEmptyOrNull(iPModel.getDeviceid())){
			conditionSql.append(" and ip.deviceid='").append(iPModel.getDeviceid()).append("' ");
		}		
		
		if(!StringUtil.isEmptyOrNull(iPModel.getCustomerid())){
			conditionSql.append(" and ip.customerid='").append(iPModel.getCustomerid()).append("' ");
		}

		countSql=countSql.append(conditionSql.toString());
		querySql=querySql.append(conditionSql.toString()+" order by ip.ipsegid,ip.ipadd desc ");
		
		try {
			int count =dbm.executeQueryCount(countSql.toString());
			int total = count % pm.getPerPage() == 0 ? count /pm.getPerPage() : count / pm.getPerPage() + 1;
			pm.setTotalPage(total);
			pm.setTotalRecord(count);
			
			/**
			 * 分页sql构造
			 */
			PageFactory pageFactory = new PageFactory();
			String sql = pageFactory.createPageSQL(querySql.toString(), pm);
			pageFactory = null;
			
			List<IPModel> list =  dbm.getObjectList(IPModel.class, sql);
			pm.setList(list);
			logger.info("call IPDao.queryIPByCondition() success");
			
		} catch (Exception e) {
			logger.info("call IPDao.queryIPByCondition() fail");
			e.printStackTrace();
		}finally{
			dbm.close();
			dbm=null;
		}
		logger.info("call IPDao.queryIPByCondition() finish");
		return pm;		
	}
	
	public List<IPModel> queryIPProperty(String ipadd){
		dbm=new DBManager();
		logger.info("call IPDao.queryIPProperty() start");
		StringBuilder querySql=new StringBuilder(" select ip.* from rsip ip where (ip.status!='99' or ip.status is null) ");
		List<IPModel> list=new ArrayList<IPModel>();
		try {
			list=dbm.getObjectList(IPModel.class, querySql.toString());
			logger.info("call IPDao.queryIPProperty() success");
		} catch (Exception e) {
			logger.info("call IPDao.queryIPProperty() fail");
			e.printStackTrace();
		}finally{
			dbm.close();
			dbm=null;
		}
		logger.info("call IPDao.queryIPSegProperty() finish");
		return list;
	}
	
	public String deleteIPByIds(String ids){
		dbm=new DBManager();
		logger.info("call IPDao.deleteIPByIds() start");
		String result="03";
		StringBuilder deleteSql=null;
		List<String> list=new ArrayList<String>();  
			if(!StringUtil.isEmptyOrNull(ids)){
				if(ids.indexOf(",")!=-1){//如果是批量删除
					ids=ids.replaceAll(",", "','");
				}
				deleteSql=new StringBuilder(" update rsip set status='99' where id in('"+ids+"') and status='01' ");
				list.add(deleteSql.toString());
				try {
					if(dbm.excuteBatchSql(list)){
						result="01"; 
						logger.info("call IPDao.deleteIPByIds() success");
					}else{
						result="02"; 
						logger.info("call IPDao.deleteIPByIds() fail");
					}
					
				} catch (Exception e) {
					result="03"; 
					logger.info("call IPDao.deleteIPByIds() fail");
					e.printStackTrace();
				}finally{
					dbm.close();
					dbm=null;
				}
			}
		logger.info("call IPDao.deleteIPByIds() finish");
		return result;
	}
	
	public IPModel queryIPById(String id){
		dbm=new DBManager();
		logger.info("call IPDao.queryIPById() start");
		IPModel iPModel=new IPModel();
		if(!StringUtil.isEmptyOrNull(id)){
			StringBuilder querySql=new StringBuilder(" select ip.*,cust.name as customername,dc.name as dcname,seg.name as ipsegname,device.name as devicename from rsip ip left join busccustomer cust on ip.customerid=cust.id left join rsipseg seg on ip.ipsegid=seg.id left join rsdevice device on ip.deviceid=device.id left join rsdatacenter dc on ip.dcid=dc.id where ip.id='"+id+"' ");
			try {
				iPModel=  (IPModel) dbm.getObject(IPModel.class, querySql.toString());
				logger.info("call IPDao.queryIPById() success");
			} catch (Exception e) {
				logger.info("call IPDao.queryIPById() fail");
				e.printStackTrace();
			}finally{
				dbm.close();
				dbm=null;
			}
		}
		logger.info("call IPDao.queryIPById() finish");
		return iPModel;
	}
	
	public String saveIP(IPModel iPModel){
		dbm=new DBManager();
		logger.info("call IPDao.saveIP() start");
		String id=CommUtil.getID();
		if(iPModel!=null){
			Rsip ip=new Rsip();
			ip.setId(id);
			try {
				BeanUtils.copyProperties(ip, iPModel);
				dbm.insertObject(ip, "rsip");
				logger.info("call IPDao.saveIP() success");
			} catch (IllegalAccessException e) {
				logger.info("call IPDao.saveIP() fail");
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				logger.info("call IPDao.saveIP() fail");
				e.printStackTrace();
			}finally{
				dbm.close();
				dbm=null;
			}
		}
		logger.info("call IPDao.saveIP() finish");
		return id;
	}
	
	public boolean updateIP(IPModel iPModel){
		dbm=new DBManager();
		logger.info("call IPDao.updateIP() start");
		boolean result=false;
		if(!StringUtil.isEmptyOrNull(iPModel.getId())){
			Rsip ip=new Rsip();
			try {
				BeanUtils.copyProperties(ip, iPModel);
				result=dbm.updateObject(ip); 
				logger.info("call IPDao.updateIP() success");
			} catch (IllegalAccessException e) {
				result=false;
				logger.info("call IPDao.updateIP() fail");
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				result=false;
				logger.info("call IPDao.updateIP() fail");
				e.printStackTrace();
			}finally{
				dbm.close();
				dbm=null;
			}
		}
		logger.info("call IPDao.updateIP() finish");
		return result;		
	}
	
	public PageModel quickSearch(PageModel pm,String key,String needRoleFilter){
		dbm=new DBManager();
		logger.info("call IPDao.quickSearch() start");
		List<Tsdict> dics=new ArrayList<Tsdict>();
		StringBuilder keys=new StringBuilder("");
		String countSql="  select count(*) from rsip ip left join busccustomer cust on ip.customerid=cust.id left join rsipseg seg on ip.ipsegid=seg.id left join rsdevice device on ip.deviceid=device.id left join rsdatacenter dc on ip.dcid=dc.id where (ip.status!='99' or ip.status is null) and (ip.dcid='"+DataCenterUtil.getDCID()+"' ";
		String querySql="  select ip.*,cust.name as customername,dc.name as dcname,seg.name as ipsegname,device.name as devicename from rsip ip left join busccustomer cust on ip.customerid=cust.id left join rsipseg seg on ip.ipsegid=seg.id left join rsdevice device on ip.deviceid=device.id left join rsdatacenter dc on ip.dcid=dc.id where (ip.status!='99' or ip.status is null) and (ip.dcid='"+DataCenterUtil.getDCID()+"' ";
		
		if(Boolean.parseBoolean(needRoleFilter)&&DataCenterUtil.queryAllData()){
			countSql=countSql+" or 1=1 ";
			querySql=querySql+" or 1=1 ";
		}
		countSql=countSql+") ";
		querySql=querySql+") ";
		
		StringBuilder conditionSql=new StringBuilder();
		if(!StringUtil.isEmptyOrNull(key)){
			conditionSql.append(" ip.ipadd like '%").append(key).append("%' ");
			conditionSql.append(" or seg.name like '%").append(key).append("%' ");
			dics=this.queryDicIds(dbm, key);
			for(Tsdict dic:dics){
				if(dics.lastIndexOf(dic)!=dics.size()-1){
					keys.append("'").append(dic.getDkey()).append("',");
				}else{
					keys.append("'").append(dic.getDkey()).append("'");
				}
			}

			if(!StringUtil.isEmptyOrNull(keys.toString())){
				conditionSql.append(" or ip.status in(").append(keys.toString()).append(") ");
			}			
			conditionSql.append(" or device.name like '%").append(key).append("%' ");
			conditionSql.append(" or cust.name like '%").append(key).append("%' ");
		}

		countSql=countSql+" and ("+conditionSql.toString()+") ";
		querySql=querySql+" and ("+conditionSql.toString()+") order by ip.ipsegid,ip.ipadd desc ";
		
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
			
			List<IPModel> list =  dbm.getObjectList(IPModel.class, sql);
			pm.setList(list);
			logger.info("call IPDao.quickSearch() success");
			
		} catch (Exception e) {
			logger.info("call IPDao.quickSearch() fail");
			e.printStackTrace();
		}finally{
			dbm.close();
			dbm=null;
		}
		logger.info("call IPDao.quickSearch() finish");
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
