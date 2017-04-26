package com.dhcc.bussiness.sxydidc.safeInfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import com.dhcc.bussiness.sxydidc.customer.CustomerModel;
import com.dhcc.bussiness.sxydidc.ip.IPModel;
import com.dhcc.common.database.DBManager;
import com.dhcc.common.system.page.PageFactory;
import com.dhcc.common.util.CommUtil;
import com.dhcc.common.util.DataCenterUtil;
import com.dhcc.modal.system.PageModel;
import com.dhcc.modal.system.Tsconfig;
import com.mockrunner.util.common.StringUtil;
import com.opensymphony.xwork2.ActionContext;

public class SendEmailDao {
	private static final Logger logger = Logger.getLogger(SendEmailDao.class);
	
	public CustomerModel queryCustomerByIP(String ip){
		DBManager dbm=new DBManager();
		logger.info("call SendEmailDao.queryCustomerByIP() start");
		CustomerModel custModel=new CustomerModel();
		if(!StringUtil.isEmptyOrNull(ip)){
			StringBuilder querySql=new StringBuilder(" select cust.* from busccustomer cust left join rsip ip on cust.id=ip.customerid where ip.ipadd='"+ip+"' ");
			try {
				custModel=  (CustomerModel) dbm.getObject(CustomerModel.class, querySql.toString());
				logger.info("call SendEmailDao.queryCustomerByIP() success");
			} catch (Exception e) {
				logger.info("call SendEmailDao.queryCustomerByIP() fail");
				e.printStackTrace();
			}finally{
				dbm.close();
				dbm=null;
			}
		}
		logger.info("call SendEmailDao.queryCustomerByIP() finish");
		return custModel;		
		
	}
	
	public IPModel queryIPByAdd(String ip){
		DBManager dbm=new DBManager();
		logger.info("call SendEmailDao.queryIPByAdd() start");
		IPModel model=new IPModel();
		if(!StringUtil.isEmptyOrNull(ip)){
			StringBuilder querySql=new StringBuilder(" select ip.* from rsip ip where ip.ipadd='"+ip+"' ");
			try {
				model=  (IPModel) dbm.getObject(IPModel.class, querySql.toString());
				logger.info("call SendEmailDao.queryIPByAdd() success");
			} catch (Exception e) {
				logger.info("call SendEmailDao.queryIPByAdd() fail");
				e.printStackTrace();
			}finally{
				dbm.close();
				dbm=null;
			}
		}
		logger.info("call SendEmailDao.queryIPByAdd() finish");
		return model;		
	}
	
	public Tsconfig queryConfig(String dtype, String dkey){
		DBManager dbm=new DBManager();
		logger.info("call SendEmailDao.queryConfig() start");
		Tsconfig config=new Tsconfig();
		if(!StringUtil.isEmptyOrNull(dtype)&&!StringUtil.isEmptyOrNull(dkey)){
			StringBuilder querySql=new StringBuilder(" select * from tsconfig config where config.dtype='"+dtype+"' and config.dkey='"+dkey+"' ");
			try {
				config=  (Tsconfig) dbm.getObject(Tsconfig.class, querySql.toString());
				logger.info("call SendEmailDao.queryConfig() success");
			} catch (Exception e) {
				logger.info("call SendEmailDao.queryConfig() fail");
				e.printStackTrace();
			}finally{
				dbm.close();
				dbm=null;
			}
		}
		logger.info("call SendEmailDao.queryConfig() finish");
		return config;
	}
	
	public  List<Infosectcustmail> queryInfoSectCustMail(String custId){
		DBManager dbm=new DBManager();
		logger.info("call SendEmailDao.queryInfoSectCustMail() start");
		List<Infosectcustmail> list =new ArrayList<Infosectcustmail>();
		if(!StringUtil.isEmptyOrNull(custId)){
			StringBuilder querySql=new StringBuilder(" select * from infosectcustmail where custid='"+custId+"' ");
			try {
				list=dbm.getObjectList(Infosectcustmail.class, querySql.toString());
				logger.info("call SendEmailDao.queryInfoSectCustMail() success");
			} catch (Exception e) {
				logger.info("call SendEmailDao.queryInfoSectCustMail() fail");
				e.printStackTrace();
			}finally{
				dbm.close();
				dbm=null;
			}
		}
		logger.info("call SendEmailDao.queryInfoSectCustMail() finish");
		return list;
	}
	
	public  List<Infosectcommemail> queryInfoSectCommMail(){
		DBManager dbm=new DBManager();
		logger.info("call SendEmailDao.queryInfoSectCommMail() start");
		List<Infosectcommemail> list =new ArrayList<Infosectcommemail>();
		StringBuilder querySql=new StringBuilder(" select * from infosectcommemail ");
		try {
			list=dbm.getObjectList(Infosectcommemail.class, querySql.toString());
			logger.info("call SendEmailDao.queryInfoSectCommMail() success");
		} catch (Exception e) {
			logger.info("call SendEmailDao.queryInfoSectCommMail() fail");
			e.printStackTrace();
		}finally{
			dbm.close();
			dbm=null;
		}
		logger.info("call SendEmailDao.queryInfoSectCommMail() finish");
		return list;
	}
	
	public boolean saveSafeInfo(Infosectsafeinfo safeInfo){
		DBManager dbm=new DBManager();
		boolean result=false;
		logger.info("call SendEmailDao.saveSafeInfo() start");
		try {
			safeInfo.setId(CommUtil.getID());
			result=dbm.insertObject(safeInfo,"infosectsafeinfo");
			logger.info("call SendEmailDao.saveSafeInfo() success");
		} catch (Exception e) {
			logger.info("call SendEmailDao.saveSafeInfo() fail");
			e.printStackTrace();
		}finally{
			dbm.close();
			dbm=null;
		}
		logger.info("call SendEmailDao.saveSafeInfo() finish");	
		return result;
	}
	
	public boolean updateSafeInfo(Infosectsafeinfo safeInfo){
		DBManager dbm=new DBManager();
		boolean result=false;
		logger.info("call SendEmailDao.updateSafeInfo() start");
		try {
			if(dbm.executeUpdate(" update infosectsafeinfo info set info.status='02',info.dealtime="+System.currentTimeMillis()+"-info.sendtime,info.dealuser='"+ActionContext.getContext().getSession().get("username").toString()+"',info.result='"+safeInfo.getResult()+"',info.endtime="+new Date().getTime()+" where info.id='"+safeInfo.getId()+"' ")>0){
				result=true;
			}
			logger.info("call SendEmailDao.updateSafeInfo() success");
		} catch (Exception e) {
			logger.info("call SendEmailDao.updateSafeInfo() fail");
			e.printStackTrace();
		}finally{
			dbm.close();
			dbm=null;
		}
		logger.info("call SendEmailDao.updateSafeInfo() finish");	
		return result;
	}	
	
	public PageModel querySafeInfo(PageModel pm,String begTime,String endTime){
		logger.info("call SendEmailDao.querySafeInfo() start");
		DBManager dbm=new DBManager();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		StringBuilder querySql=new StringBuilder(" select info.id,cust.name as custname,info.url,info.ip,info.toemailadd,info.createuser,info.dealuser,dc.name as dcname,info.result,case when info.status='01' then cast(("+new Date().getTime()+"-info.sendtime)/1000/60 as unsigned)  else cast(info.dealtime/1000/60 as unsigned) end as dealtime,info.status,FROM_UNIXTIME(info.sendtime/1000,'%Y-%m-%d %H:%i:%s') as sendtime,FROM_UNIXTIME(info.endtime/1000,'%Y-%m-%d %H:%i:%s') as endtime from infosectsafeinfo info left join busccustomer cust on info.custid=cust.id left join rsdatacenter dc on info.dcid=dc.id where info.dcid='"+DataCenterUtil.getDCID()+"' ");
		if(!StringUtil.isEmptyOrNull(begTime)&&!StringUtil.isEmptyOrNull(begTime)){
			try {
				querySql.append(" where info.sendtime between "+sdf.parse(begTime).getTime()+" and "+sdf.parse(endTime).getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		querySql.append(" order by info.status,info.sendtime desc ");
		try {
			int count = dbm.executeQueryCount(" select count(*) from ("+querySql.toString()+")temp ");
			int total = count % pm.getPerPage() == 0 ? count /pm.getPerPage() : count / pm.getPerPage() + 1;
			pm.setTotalPage(total);
			pm.setTotalRecord(count);

			PageFactory pageFactory = new PageFactory();
			String sql = pageFactory.createPageSQL(querySql.toString(), pm);
			pageFactory = null;
			List<SafeInfoModel> list =  dbm.getObjectList(SafeInfoModel.class, sql);
			pm.setList(list);
			logger.info("call SendEmailDao.querySafeInfo() success");
		} catch (Exception e) {
			logger.info("call SendEmailDao.querySafeInfo() fail");
			e.printStackTrace();
		}finally{
			dbm.close();
			dbm=null;
		}
		logger.info("call SendEmailDao.querySafeInfo() finish");
		return pm;
	}
	
	public PageModel quickSearch(PageModel pm,String key){
		DBManager dbm=new DBManager();
		logger.info("call SendEmailDao.quickSearch() start");
		String countSql="  select count(*) from infosectsafeinfo info left join rsdatacenter dc on info.dcid=dc.id where info.dcid='"+DataCenterUtil.getDCID()+"' ";
		String querySql="  select info.id,info.url,info.ip,info.toemailadd,info.createuser,info.dealuser,dc.name as dcname,info.result,case when info.status='01' then cast(("+new Date().getTime()+"-info.sendtime)/1000/60 as unsigned)  else cast(info.dealtime/1000/60 as unsigned) end as dealtime,info.status,FROM_UNIXTIME(info.sendtime/1000,'%Y-%m-%d %H:%i:%s') as sendtime,FROM_UNIXTIME(info.endtime/1000,'%Y-%m-%d %H:%i:%s') as endtime from infosectsafeinfo info left join rsdatacenter dc on info.dcid=dc.id where info.dcid='"+DataCenterUtil.getDCID()+"' ";
		StringBuilder conditionSql=new StringBuilder();
		if(!StringUtil.isEmptyOrNull(key)){
			conditionSql.append(" info.url like '%").append(key).append("%' ");
			conditionSql.append(" or info.ip like '%").append(key).append("%' ");
			conditionSql.append(" or info.toemailadd like '%").append(key).append("%' ");
			if(key.indexOf("已")!=-1){
				conditionSql.append(" or info.status='02' ");
			}else if(key.indexOf("未")!=-1){
				conditionSql.append(" or info.status='01' ");
			}

		}
		
		countSql=countSql+" and ("+conditionSql.toString()+") ";
		querySql=querySql+" and ("+conditionSql.toString()+") ";
		querySql=querySql+" order by info.status,info.sendtime desc ";
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
			
			List<Infosectsafeinfo> list =  dbm.getObjectList(Infosectsafeinfo.class, sql);
			pm.setList(list);
			logger.info("call SendEmailDao.quickSearch() success");
			
		} catch (Exception e) {
			logger.info("call SendEmailDao.quickSearch() fail");
			e.printStackTrace();
		}finally{
			dbm.close();
			dbm=null;
		}
		logger.info("call SendEmailDao.quickSearch() finish");
		return pm;		
	}
}
