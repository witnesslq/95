package com.dhcc.bussiness.sxydidc.useat;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import com.dhcc.common.database.DBManager;
import com.dhcc.common.system.page.PageFactory;
import com.dhcc.common.util.CommUtil;
import com.dhcc.common.util.DataCenterUtil;
import com.dhcc.modal.sxydidc.useat.Rsuseat;
import com.dhcc.modal.system.PageModel;
import com.mockrunner.util.common.StringUtil;

public class USeatDao {
	private static final Logger logger = Logger.getLogger(USeatDao.class);
	private static DBManager dbm;  

	
	public PageModel queryUSeat(PageModel pm,String needRoleFilter){
		dbm=new DBManager();
		logger.info("call SeatDao.queryUSeat() start");
		StringBuilder querySql=null;
		StringBuilder countSql=null;
		if(Boolean.parseBoolean(needRoleFilter)&&DataCenterUtil.queryAllData()){
			countSql=new StringBuilder(" select count(*) from rsuseat useat left join rsrack rack on useat.rackid=rack.id left join rsroom room on rack.roomid=room.id left join rsdevice device on useat.deviceid=device.id left join busccustomer cust on useat.customerid=cust.id ");
			querySql=new StringBuilder(" select useat.*,rack.name as rackname,room.id as roomid,room.roomname as roomName,device.name as devicename,cust.name as customername from rsuseat useat left join rsrack rack on useat.rackid=rack.id left join rsroom room on rack.roomid=room.id left join rsdevice device on useat.deviceid=device.id left join busccustomer cust on useat.customerid=cust.id order by rack.roomid,rack.id,useat.no ");
		}else{
			countSql=new StringBuilder(" select count(*) from rsuseat useat left join rsrack rack on useat.rackid=rack.id left join rsroom room on rack.roomid=room.id left join rsdevice device on useat.deviceid=device.id left join busccustomer cust on useat.customerid=cust.id  where room.dcid='"+DataCenterUtil.getDCID()+"' ");
			querySql=new StringBuilder(" select useat.*,rack.name as rackname,room.id as roomid,room.roomname as roomName,device.name as devicename,cust.name as customername from rsuseat useat left join rsrack rack on useat.rackid=rack.id left join rsroom room on rack.roomid=room.id left join rsdevice device on useat.deviceid=device.id left join busccustomer cust on useat.customerid=cust.id where room.dcid='"+DataCenterUtil.getDCID()+"' order by rack.roomid,rack.id,useat.no ");
		}
	
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

			List<USeatModel> list =  dbm.getObjectList(USeatModel.class, sql);
			pm.setList(list);
			logger.info("call USeatDao.queryUSeat() success");
		} catch (Exception e) {
			logger.info("call USeatDao.queryUSeat() fail");
			e.printStackTrace();
		}finally{
			dbm.close();
			dbm=null;
		}
		logger.info("call USeatDao.queryUSeat() finish");
		return pm;
	}
	
	public PageModel queryFreeUSeat(PageModel pm,String cusid){
		dbm=new DBManager();
		logger.info("call SeatDao.queryUSeat() start");
		StringBuilder countSql=new StringBuilder(" select count(*) from rsuseat useat left join rsrack rack on useat.rackid=rack.id left join rsdevice device on useat.deviceid=device.id left join busccustomer cust on useat.customerid=cust.id where useat.status='01' or (useat.status = '02' and useat.customerid ='"+cusid+"')");
		StringBuilder querySql=new StringBuilder(" select useat.*,(select rack.name from rsrack rack where useat.rackid=rack.id) as rackname,(select device.name from rsdevice device where useat.deviceid=device.id ) as devicename,(select cust.name from busccustomer cust where useat.customerid=cust.id) as customername from rsuseat useat where useat.status='01' or (useat.status = '02' and useat.customerid ='"+cusid+"') ");	
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

			List<USeatModel> list =  dbm.getObjectList(USeatModel.class, sql);
			pm.setList(list);
			logger.info("call USeatDao.queryUSeat() success");
		} catch (Exception e) {
			logger.info("call USeatDao.queryUSeat() fail");
			e.printStackTrace();
		}finally{
			dbm.close();
			dbm=null;
		}
		logger.info("call USeatDao.queryUSeat() finish");
		return pm;
	}
	
	public PageModel queryUSeatByCondition(PageModel pm,USeatModel useatModel,String needRoleFilter){
		dbm=new DBManager();
		logger.info("call USeatDao.queryUSeatByCondition() start");
		String countSql=null;
		String querySql=null;
		if(Boolean.parseBoolean(needRoleFilter)&&DataCenterUtil.queryAllData()){
			countSql="  select count(*) from rsuseat useat left join rsrack rack on useat.rackid=rack.id left join rsroom room on rack.roomid=room.id left join rsdevice device on useat.deviceid=device.id left join busccustomer cust on useat.customerid=cust.id where 1=1 ";
			querySql="  select useat.*,rack.name as rackname,room.id as roomid,room.roomname as roomName,device.name as devicename,cust.name as customername from rsuseat useat left join rsrack rack on useat.rackid=rack.id left join rsroom room on rack.roomid=room.id left join rsdevice device on useat.deviceid=device.id left join busccustomer cust on useat.customerid=cust.id where 1=1 ";			
		}else{
			countSql="  select count(*) from rsuseat useat left join rsrack rack on useat.rackid=rack.id left join rsroom room on rack.roomid=room.id left join rsdevice device on useat.deviceid=device.id left join busccustomer cust on useat.customerid=cust.id where room.dcid='"+DataCenterUtil.getDCID()+"' ";
			querySql="  select useat.*,rack.name as rackname,room.id as roomid,room.roomname as roomName,device.name as devicename,cust.name as customername from rsuseat useat left join rsrack rack on useat.rackid=rack.id left join rsroom room on rack.roomid=room.id left join rsdevice device on useat.deviceid=device.id left join busccustomer cust on useat.customerid=cust.id where room.dcid='"+DataCenterUtil.getDCID()+"' ";
		}

		StringBuilder conditionSql=new StringBuilder();
		if(!StringUtil.isEmptyOrNull(useatModel.getRackid())){
			conditionSql.append(" and useat.rackid='").append(useatModel.getRackid()).append("' ");
		}
		
		if(!StringUtil.isEmptyOrNull(useatModel.getRoomid())){
			conditionSql.append(" and rack.roomid='").append(useatModel.getRoomid()).append("' ");
		}
		
		if(!StringUtil.isEmptyOrNull(useatModel.getStatus())){
			conditionSql.append(" and useat.status='").append(useatModel.getStatus()).append("' ");
		}		
				
		if(!StringUtil.isEmptyOrNull(useatModel.getDeviceid())){
			conditionSql.append(" and useat.deviceid='").append(useatModel.getDeviceid()).append("' ");
		}
		
		if(!StringUtil.isEmptyOrNull(useatModel.getCustomerid())){
			conditionSql.append(" and useat.customerid='").append(useatModel.getCustomerid()).append("' ");
		}
		if(!useatModel.getNeedFilter()){
			if(useatModel.getNo()!=null&&useatModel.getNo()!=0){
				conditionSql.append(" and useat.no=").append(useatModel.getNo()).append(" ");
			}			
		}else{
			conditionSql.append(" and useat.no>5 ");
			conditionSql.append(" and useat.status='01' ");
		}


		countSql=countSql+conditionSql.toString();
		querySql=querySql+conditionSql.toString()+" order by rack.roomid,rack.id,useat.no ";
		
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
			
			List<USeatModel> list =  dbm.getObjectList(USeatModel.class, sql);
			pm.setList(list);
			logger.info("call USeatDao.queryUSeatByCondition() success");
			
		} catch (Exception e) {
			logger.info("call USeatDao.queryUSeatByCondition() fail");
			e.printStackTrace();
		}finally{
			dbm.close();
			dbm=null;
		}
		logger.info("call USeatDao.queryUSeatByCondition() finish");
		return pm;		
	}
	
	public boolean deleteUSeatByIds(String ids){
		dbm=new DBManager();
		logger.info("call USeatDao.deleteUSeatByIds() start");
		boolean result=false;
		StringBuilder deleteSql=null;
		List<String> list=new ArrayList<String>();  
			if(!StringUtil.isEmptyOrNull(ids)){
				if(ids.indexOf(",")!=-1){//如果是批量删除
					ids=ids.replaceAll(",", "','");
				}
				deleteSql=new StringBuilder(" delete from rsuseat where id in('"+ids+"') ");
				list.add(deleteSql.toString());
				try {
					result=dbm.excuteBatchSql(list); 
					logger.info("call USeatDao.deleteUSeatByIds() success");
				} catch (Exception e) {
					logger.info("call USeatDao.deleteUSeatByIds() fail");
					e.printStackTrace();
				}finally{
					dbm.close();
					dbm=null;
				}
			}
		logger.info("call USeatDao.deleteUSeatByIds() finish");
		return result;
	}
	
	public USeatModel queryUSeatById(String id){
		dbm=new DBManager();
		logger.info("call USeatDao.queryUSeatById() start");
		USeatModel useatModel=new USeatModel();
		if(!StringUtil.isEmptyOrNull(id)){
			StringBuilder querySql=new StringBuilder(" select useat.*,rack.name as rackname,device.name as devicename,cust.name as customername from rsuseat useat left join rsrack rack on useat.rackid=rack.id left join rsdevice device on useat.deviceid=device.id left join busccustomer cust on useat.customerid=cust.id where useat.id='"+id+"' ");
			try {
				useatModel=  (USeatModel) dbm.getObject(USeatModel.class, querySql.toString());
				logger.info("call USeatDao.queryUSeatById() success");
			} catch (Exception e) {
				logger.info("call USeatDao.queryUSeatById() fail");
				e.printStackTrace();
			}finally{
				dbm.close();
				dbm=null;
			}
		}
		logger.info("call USeatDao.queryUSeatById() finish");
		return useatModel;
	}
	
	public String saveUSeat(USeatModel useatModel){
		dbm=new DBManager();
		logger.info("call USeatDao.saveUSeat() start");
		String id=CommUtil.getID();
		if(useatModel!=null){
			Rsuseat useat=new Rsuseat();
			useatModel.setId(id);
			try {
				BeanUtils.copyProperties(useat, useatModel);
				dbm.insertObject(useat, "rsuseat");
				logger.info("call USeatDao.saveUSeat() success");
			} catch (IllegalAccessException e) {
				logger.info("call USeatDao.saveUSeat() fail");
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				logger.info("call USeatDao.saveUSeat() fail");
				e.printStackTrace();
			}finally{
				dbm.close();
				dbm=null;
			}
		}
		logger.info("call USeatDao.saveUSeat() finish");
		return id;
	}
	
	public boolean updateUSeat(USeatModel useatModel){
		dbm=new DBManager();
		logger.info("call USeatDao.updateUSeat() start");
		boolean result=false;
		if(!StringUtil.isEmptyOrNull(useatModel.getId())){
			Rsuseat useat=new Rsuseat();
			try {
				BeanUtils.copyProperties(useat, useatModel);
				result=dbm.updateObject(useat); 
				logger.info("call USeatDao.updateUSeat() success");
			} catch (IllegalAccessException e) {
				result=false;
				logger.info("call USeatDao.updateUSeat() fail");
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				result=false;
				logger.info("call USeatDao.updateUSeat() fail");
				e.printStackTrace();
			}finally{
				dbm.close();
				dbm=null;
			}
		}
		logger.info("call USeatDao.updateUSeat() finish");
		return result;		
	}
}
