package com.dhcc.bussiness.sxydidc.device;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.dhcc.bussiness.sxydidc.ip.IPModel;
import com.dhcc.common.database.DBManager;
import com.dhcc.common.system.page.PageFactory;
import com.dhcc.common.util.CommUtil;
import com.dhcc.common.util.DataCenterUtil;
import com.dhcc.modal.sxydidc.device.*;
import com.dhcc.modal.system.PageModel;
import com.mockrunner.util.common.StringUtil;

public class DeviceDao {
	private static final Logger logger = Logger.getLogger(DeviceDao.class);
	private static DBManager dbm;  

	
	public PageModel queryDevice(PageModel pm,String needRoleFilter){
		dbm=new DBManager();
		logger.info("call DeviceDao.queryDevice() start");
		StringBuilder querySql=new StringBuilder(" select dv.*,rack.name as rackname,room.roomname as roomname,cust.name as customername,dc.name as dcname,ip.ipadd as ipadd,temp.totalport as totalPort,temp.freeport as freePort,temp.preport as prePort,temp.factport as factPort,temp.usedport as usedPort from rsdevice dv left join rsrack rack on dv.rackid=rack.id left join rsroom room on dv.roomid=room.id left join busccustomer cust on dv.customerid=cust.id left join rsdatacenter dc on dv.dcid=dc.id left join (select ip.deviceid as deviceid,group_concat(ip.ipadd) as ipadd from rsip ip group by ip.deviceid)ip on dv.id=ip.deviceid left join (");
		querySql.append(" select pack.deviceid as devid,count(*) as totalport,sum(case port.status when '01' then 1 else 0 end) as freeport,sum(case port.status when '02' then 1 else 0 end) as preport,sum(case port.status when '03' then 1 else 0 end) as factport,sum(case port.status when '04' then 1 else 0 end) as usedport from rsnetdevport port left join rsnetdevpack pack on port.netdevpackid=pack.id  where (port.status!='99' or port.status is null) group by pack.deviceid ");
		querySql.append(")temp on dv.id=temp.devid ");
		querySql.append(" where dv.devicetype='01' and (dv.status!='99' or dv.status is null) and (dv.dcid='"+DataCenterUtil.getDCID()+"' ");
		if(Boolean.parseBoolean(needRoleFilter)&&DataCenterUtil.queryAllData()){
			querySql.append(" or 1=1 ");
		}
		querySql.append(") ");
		querySql.append(" order by room.roomcode,rack.code,dv.code ");
		try {
			int count =dbm.executeQueryCount(" select count(*) from ("+querySql.toString()+")tempa ");
			int total = count % pm.getPerPage() == 0 ? count /pm.getPerPage() : count / pm.getPerPage() + 1;
			pm.setTotalPage(total);
			pm.setTotalRecord(count);
			
			/**
			 * 分页sql构造
			 */
			PageFactory pageFactory = new PageFactory();
			String sql = pageFactory.createPageSQL(querySql.toString(), pm);
			pageFactory = null;

			List<DeviceModel> list =  dbm.getObjectList(DeviceModel.class, sql);
			pm.setList(list);
			logger.info("call DeviceDao.queryDevice() success");
		} catch (Exception e) {
			logger.info("call DeviceDao.queryDevice() fail");
			e.printStackTrace();
		}finally{
			dbm.close();
			dbm=null;
		}
		logger.info("call DeviceDao.queryDevice() finish");
		return pm;
	}
	
	
	public PageModel queryFreeport(PageModel pm,String cusid){
		dbm=new DBManager();
		logger.info("call DeviceDao.queryFreeport() start");
		StringBuilder countSql=new StringBuilder(" select count(*) from rsnetdevport dv where dv.status='01' or (dv.status = '02' and dv.customerid ='"+cusid+"') ");
		StringBuilder querySql=new StringBuilder(" select dv.id,dv.confrate, dv.no,(select a.name from rsdevice a,rsnetdevpack b where dv.netdevpackid=b.id and b.deviceid=a.id) as device from rsnetdevport dv where dv.status='01' or (dv.status = '02' and dv.customerid ='"+cusid+"')  ");	
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

			List<DeviceportModel> list =  dbm.getObjectList(DeviceportModel.class, sql);
			pm.setList(list);
			logger.info("call DeviceDao.queryFreeport() success");
		} catch (Exception e) {
			logger.info("call DeviceDao.queryFreeport() fail");
			e.printStackTrace();
		}finally{
			dbm.close();
			dbm=null;
		}
		logger.info("call DeviceDao.queryFreeport() finish");
		return pm;
	}
	
	public PageModel queryDeviceByCondition(PageModel pm,DeviceModel deviceModel,String needRoleFilter){
		dbm=new DBManager();
		logger.info("call ContractDao.queryDeviceByCondition() start");
		StringBuilder querySql=new StringBuilder(" select dv.*,rack.name as rackname,room.roomname as roomname,cust.name as customername,dc.name as dcname,ip.ipadd as ipadd,temp.totalport as totalPort,temp.freeport as freePort,temp.preport as prePort,temp.factport as factPort,temp.usedport as usedPort from rsdevice dv left join rsrack rack on dv.rackid=rack.id left join rsroom room on dv.roomid=room.id left join busccustomer cust on dv.customerid=cust.id left join rsdatacenter dc on dv.dcid=dc.id left join (select ip.deviceid as deviceid,group_concat(ip.ipadd) as ipadd from rsip ip group by ip.deviceid)ip on dv.id=ip.deviceid left join (");
		querySql.append(" select pack.deviceid as devid,count(*) as totalport,sum(case port.status when '01' then 1 else 0 end) as freeport,sum(case port.status when '02' then 1 else 0 end) as preport,sum(case port.status when '03' then 1 else 0 end) as factport,sum(case port.status when '04' then 1 else 0 end) as usedport from rsnetdevport port left join rsnetdevpack pack on port.netdevpackid=pack.id  where (port.status!='99' or port.status is null) group by pack.deviceid ");
		querySql.append(")temp on dv.id=temp.devid ");
		querySql.append(" where dv.devicetype='01' and (dv.status!='99' or dv.status is null) and (dv.dcid='"+DataCenterUtil.getDCID()+"' ");
		if(Boolean.parseBoolean(needRoleFilter)&&DataCenterUtil.queryAllData()){
			querySql.append(" or 1=1 ");
		}
		querySql.append(") ");
		
		StringBuilder conditionSql=new StringBuilder();
		if(!StringUtil.isEmptyOrNull(deviceModel.getName())){
			conditionSql.append(" and dv.name like '%").append(deviceModel.getName()).append("%' ");
		}
		
		if(deviceModel.getOwner()!=0){
			conditionSql.append(" and dv.owner=").append(deviceModel.getOwner()).append(" ");
		}
				
		if(!StringUtil.isEmptyOrNull(deviceModel.getRoomid())){
			conditionSql.append(" and dv.roomid='").append(deviceModel.getRoomid()).append("' ");
		}	
		
		if(!StringUtil.isEmptyOrNull(deviceModel.getStatus())){
			conditionSql.append(" and dv.status='").append(deviceModel.getStatus()).append("' ");
		}
		if(!StringUtil.isEmptyOrNull(deviceModel.getDevicetype())){
			conditionSql.append(" and dv.devicetype='").append(deviceModel.getDevicetype()).append("' ");
		}	
		if(!StringUtil.isEmptyOrNull(deviceModel.getRackid())){
			conditionSql.append(" and dv.rackid='").append(deviceModel.getRackid()).append("' ");
		}
		querySql=querySql.append(conditionSql.toString()).append(" order by room.roomcode,rack.code,dv.code ");
		
		try {
			int count =dbm.executeQueryCount(" select count(*) from ("+querySql.toString()+")tempa ");
			int total = count % pm.getPerPage() == 0 ? count /pm.getPerPage() : count / pm.getPerPage() + 1;
			pm.setTotalPage(total);
			pm.setTotalRecord(count);
			
			/**
			 * 分页sql构造
			 */
			PageFactory pageFactory = new PageFactory();
			String sql = pageFactory.createPageSQL(querySql.toString(), pm);
			pageFactory = null;
			
			List<DeviceModel> list =  dbm.getObjectList(DeviceModel.class, sql);
			pm.setList(list);
			logger.info("call DeviceDao.queryDeviceByCondition() success");
			
		} catch (Exception e) {
			logger.info("call DeviceDao.queryDeviceByCondition() fail");
			e.printStackTrace();
		}finally{
			dbm.close();
			dbm=null;
		}
		logger.info("call DeviceDao.queryDeviceByCondition() finish");
		return pm;		
	}
	
	public PageModel quickSearch(PageModel pm,String key,String needRoleFilter){
		dbm=new DBManager();
		logger.info("call ContractDao.quickSearch() start");
		StringBuilder querySql=new StringBuilder(" select dv.*,rack.name as rackname,room.roomname as roomname,cust.name as customername,dv.name as dcname,ip.ipadd as ipadd,temp.totalport as totalPort,temp.freeport as freePort,temp.preport as prePort,temp.factport as factPort,temp.usedport as usedPort from rsdevice dv left join rsrack rack on dv.rackid=rack.id left join rsroom room on dv.roomid=room.id left join busccustomer cust on dv.customerid=cust.id left join rsdatacenter dc on dv.dcid=dv.id left join (select ip.deviceid as deviceid,group_concat(ip.ipadd) as ipadd from rsip ip group by ip.deviceid)ip on dv.id=ip.deviceid left join (");
		querySql.append(" select pack.deviceid as devid,count(*) as totalport,sum(case port.status when '01' then 1 else 0 end) as freeport,sum(case port.status when '02' then 1 else 0 end) as preport,sum(case port.status when '03' then 1 else 0 end) as factport,sum(case port.status when '04' then 1 else 0 end) as usedport from rsnetdevport port left join rsnetdevpack pack on port.netdevpackid=pack.id  where (port.status!='99' or port.status is null) group by pack.deviceid ");
		querySql.append(")temp on dv.id=temp.devid ");
		querySql.append(" where dv.devicetype='01' and (dv.status!='99' or dv.status is null) and (dv.dcid='"+DataCenterUtil.getDCID()+"' ");
		if(Boolean.parseBoolean(needRoleFilter)&&DataCenterUtil.queryAllData()){
			querySql.append(" or 1=1 ");
		}
		querySql.append(") ");
		StringBuilder conditionSql=new StringBuilder();
		if(!StringUtil.isEmptyOrNull(key)){
			conditionSql.append(" dv.name like '%").append(key).append("%' ");
			conditionSql.append(" or dv.code like '%").append(key).append("%' ");
			querySql=querySql.append(" and("+conditionSql.toString()+") ").append(" order by room.roomcode,rack.code,dv.code ");
		}
		try {
			int count =dbm.executeQueryCount(" select count(*) from ("+querySql.toString()+")tempa ");
			int total = count % pm.getPerPage() == 0 ? count /pm.getPerPage() : count / pm.getPerPage() + 1;
			pm.setTotalPage(total);
			pm.setTotalRecord(count);
			
			/**
			 * 分页sql构造
			 */
			PageFactory pageFactory = new PageFactory();
			String sql = pageFactory.createPageSQL(querySql.toString(), pm);
			pageFactory = null;
			
			List<DeviceModel> list =  dbm.getObjectList(DeviceModel.class, sql);
			pm.setList(list);
			logger.info("call DeviceDao.quickSearch() success");
			
		} catch (Exception e) {
			logger.info("call DeviceDao.quickSearch() fail");
			e.printStackTrace();
		}finally{
			dbm.close();
			dbm=null;
		}
		logger.info("call DeviceDao.quickSearch() finish");
		return pm;		
	}	
	
	public PageModel queryDevicePortByCondition(PageModel pm,DeviceportModel port,String needRoleFilter){
		dbm=new DBManager();
		logger.info("call ContractDao.queryDevicePortByCondition() start");
		StringBuilder querySql=new StringBuilder(" select por.*,pack.code as netdevpackcode,dev.id as deviceid,dev.name as devicename,cust.no as customercode,cust.name as customername from rsnetdevport por left join rsnetdevpack pack on por.netdevpackid=pack.id left join rsdevice dev on pack.deviceid=dev.id left join busccustomer cust on por.customerid=cust.id left join rsdatacenter dc on por.dcid=dc.id where (por.status!='99' or por.status is null) ");
		
		if(Boolean.parseBoolean(needRoleFilter)&&DataCenterUtil.queryAllData()){
			querySql.append(" and por.netdevpackid is not null and pack.deviceid is not null ");
		}else{
			querySql.append(" and por.netdevpackid is not null and pack.deviceid is not null and dev.dcid='"+DataCenterUtil.getDCID()+"'  ");
		}
		
		StringBuilder conditionSql=new StringBuilder();
		if(!StringUtil.isEmptyOrNull(port.getPortname())){
			conditionSql.append(" and por.portname like '%").append(port.getPortname()).append("%' ");
		}
		if(!StringUtil.isEmptyOrNull(port.getPortcode())){
			conditionSql.append(" and por.portcode like '%").append(port.getPortcode()).append("%' ");
		}	
		if(!StringUtil.isEmptyOrNull(port.getType())){
			conditionSql.append(" and por.type='").append(port.getType()).append("' ");
		}
		if(!StringUtil.isEmptyOrNull(port.getStatus())){
			conditionSql.append(" and por.status='").append(port.getStatus()).append("' ");
		}	
		if(!StringUtil.isEmptyOrNull(port.getCustomerid())){
			conditionSql.append(" and cust.id='").append(port.getCustomerid()).append("' ");
		}
		if(!StringUtil.isEmptyOrNull(port.getTonetdevid())){
			conditionSql.append(" and pack.deviceid='").append(port.getTonetdevid()).append("' ");
		}
		querySql=querySql.append(conditionSql.toString()).append(" order by pack.code,dev.code,cust.no ");
		
		try {
			int count =dbm.executeQueryCount(" select count(*) from ("+querySql.toString()+")tempa ");
			int total = count % pm.getPerPage() == 0 ? count /pm.getPerPage() : count / pm.getPerPage() + 1;
			pm.setTotalPage(total);
			pm.setTotalRecord(count);
			
			/**
			 * 分页sql构造
			 */
			PageFactory pageFactory = new PageFactory();
			String sql = pageFactory.createPageSQL(querySql.toString(), pm);
			pageFactory = null;
			
			List<DeviceportModel> list =  dbm.getObjectList(DeviceportModel.class, sql);
			pm.setList(list);
			logger.info("call DeviceDao.queryDevicePortByCondition() success");
			
		} catch (Exception e) {
			logger.info("call DeviceDao.queryDevicePortByCondition() fail");
			e.printStackTrace();
		}finally{
			dbm.close();
			dbm=null;
		}
		logger.info("call DeviceDao.queryDevicePortByCondition() finish");
		return pm;		
	}
	
	public List<DeviceModel> queryDeviceProperty(String name){
		dbm=new DBManager();
		logger.info("call DeviceDao.queryDeviceProperty() start");
		StringBuilder querySql=new StringBuilder(" select dv.* from rsdevice dv where dv.devicetype='01' and (dv.status!='99' or dv.status is null) ");
		if(!StringUtil.isEmptyOrNull(name)){
			querySql.append(" and dev.name like '%"+name+"%' ");
		}
		List<DeviceModel> list=new ArrayList<DeviceModel>();
		try {
			list=dbm.getObjectList(DeviceModel.class, querySql.toString());
			logger.info("call DeviceDao.queryDeviceProperty() success");
		} catch (Exception e) {
			logger.info("call DeviceDao.queryDeviceProperty() fail");
			e.printStackTrace();
		}finally{
			dbm.close();
			dbm=null;
		}
		logger.info("call DeviceDao.queryDeviceProperty() finish");
		return list;
	}
	
	public boolean deleteDeviceportByIds(String ids){
		dbm=new DBManager();
		logger.info("call DeviceDao.deleteDeviceportByIds() start");
		boolean result=false;
		StringBuilder deleteSql=null;
		List<String> list=new ArrayList<String>();  
			if(!StringUtil.isEmptyOrNull(ids)){
				if(ids.indexOf(",")!=-1){//如果是批量删除
					ids=ids.replaceAll(",", "','");
				}
				deleteSql=new StringBuilder(" update rsnetdevport por set por.status='99' where id in('"+ids+"') and por.status='01' ");
				list.add(deleteSql.toString());
				try {
					result=dbm.excuteBatchSql(list); 
					logger.info("call DeviceDao.deleteDeviceportByIds() success");
				} catch (Exception e) {
					logger.info("call DeviceDao.deleteDeviceportByIds() fail");
					e.printStackTrace();
				}finally{
					dbm.close();
					dbm=null;
				}
			}
		logger.info("call DeviceDao.deleteDeviceportByIds() finish");
		return result;
	}
	
	public String deleteDevicepackByIds(String ids){
		dbm=new DBManager();
		logger.info("call DeviceDao.deleteDevicepackByIds() start");
		String flag="03";
		StringBuilder deleteSql=null;
		List<String> list=new ArrayList<String>();  
			if(!StringUtil.isEmptyOrNull(ids)){
				if(ids.indexOf(",")!=-1){//如果是批量删除
					ids=ids.replaceAll(",", "','");
				}
				deleteSql=new StringBuilder(" update rsnetdevpack pack set pack.status='99' where id in('"+ids+"') and pack.status='01' ");
				list.add(deleteSql.toString());
				try {
					int count=dbm.executeQueryCount(" select count(*) from rsnetdevport por where por.netdevpackid='"+ids+"' and (por.status='02' or por.status='03' or por.status='04' ) ");
					if(count==0){
						Boolean result=dbm.excuteBatchSql(list); 
						if(result){
							flag="01";
						}
						logger.info("call DeviceDao.deleteDevicepackByIds() success");	
					}else{
						flag="02";
						logger.info("call DeviceDao.deleteDevicepackByIds() fail");
					}
				} catch (Exception e) {
					flag="03";
					logger.info("call DeviceDao.deleteDevicepackByIds() fail");
					e.printStackTrace();
				}finally{
					dbm.close();
					dbm=null;
				}
			}
		logger.info("call DeviceDao.deleteDevicepackByIds() finish");
		return flag;
	}
	
	public String deleteDeviceByIds(String ids){
		dbm=new DBManager();
		logger.info("call DeviceDao.deleteDeviceByIds() start");
		String result="03";
		StringBuilder deleteSql=null;
		List<String> list=new ArrayList<String>();  
			if(!StringUtil.isEmptyOrNull(ids)){
				if(ids.indexOf(",")!=-1){//如果是批量删除
					ids=ids.replaceAll(",", "','");
					for(String devId:ids.split(",")){
						int count=dbm.executeQueryCount(" select count(*) from rsnetdevpack where deviceid='"+devId+"' and (status!='99' or status is null) ");
						if(count==0){
							result="01";
							list.add(new StringBuilder(" update rsdevice set status='99' where id='"+devId+"' and status='01' ").toString());
							//释放IP和U位资源
							list.add(new StringBuilder(" update rsip set status='01',deviceid=null where deviceid='"+devId+"'" ).toString());
							list.add(new StringBuilder(" update rsuseat set status='01',deviceid=null where deviceid='"+devId+"'" ).toString());
						}else{
							result="02";
							break;
						}
					}
				}else{
					int count=dbm.executeQueryCount(" select count(*) from rsnetdevpack where deviceid='"+ids+"' and (status!='99' or status is null) ");
					if(count==0){
						result="01";
						list.add(new StringBuilder(" update rsdevice set status='99' where id='"+ids+"' and status='01' ").toString());
						//释放IP和U位资源
						list.add(new StringBuilder(" update rsip set status='01',deviceid=null where deviceid='"+ids+"'" ).toString());
						list.add(new StringBuilder(" update rsuseat set status='01',deviceid=null where deviceid='"+ids+"'" ).toString());						
					}else{
						result="02";
					}
				}
				
				try {
					if(result.trim().equals("01")){
						dbm.excuteBatchSql(list);
						logger.info("call DeviceDao.deleteDeviceByIds() success");
					}
				} catch (Exception e) {
					result="03";
					logger.info("call DeviceDao.deleteDeviceByIds() fail");
					e.printStackTrace();
				}finally{
					dbm.close();
					dbm=null;
				}
			}
		logger.info("call DeviceDao.deleteDeviceByIds() finish");
		return result;
	}
	
	
	public PageModel querypackDeviceById(String id,PageModel pm){
		dbm=new DBManager();
		logger.info("call DeviceDao.queryDeviceById() start");

		if(!StringUtil.isEmptyOrNull(id)){
			StringBuilder querySql=new StringBuilder(" select pack.* from rsnetdevpack pack  where pack.deviceid='"+id+"' and(pack.status!='99' or pack.status is null) ");
			StringBuilder countSql=new StringBuilder(" select count(*) from rsnetdevpack pack  where pack.deviceid='"+id+"' ");
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

				List<DevicepackModel> list =  dbm.getObjectList(DevicepackModel.class, sql.toString());
				pm.setList(list);				
				logger.info("call DeviceDao.queryDeviceById() success");
			} catch (Exception e) {
				logger.info("call DeviceDao.queryDeviceById() fail");
				e.printStackTrace();
			}finally{
				dbm.close();
				dbm=null;
			}
		}
		logger.info("call DeviceDao.queryDeviceById() finish");
		return pm;
	}
	public PageModel queryportByDevpackId(PageModel pm,String packId,String needRoleFilter){
		dbm=new DBManager();
		logger.info("call DeviceDao.queryportByDevpackId() start");
		String querySql=new String();
		String countSql=new String();
		
		if(!StringUtil.isEmptyOrNull(packId)){
			querySql=" select por.*,pack.code as netdevpackcode,dev.id as deviceid,dev.name as devicename,cust.no as customercode,cust.name as customername,dc.name as dcname from rsnetdevport por left join rsnetdevpack pack on por.netdevpackid=pack.id left join rsdevice dev on pack.deviceid=dev.id left join busccustomer cust on por.customerid=cust.id left join rsdatacenter dc on (por.dcid=dc.id or dev.dcid=dc.id) where por.netdevpackid='"+packId+"' and(por.status!='99' or por.status is null) ";
		}else{
			querySql=" select por.*,pack.code as netdevpackcode,dev.id as deviceid,dev.name as devicename,cust.no as customercode,cust.name as customername,dc.name as dcname from rsnetdevport por left join rsnetdevpack pack on por.netdevpackid=pack.id left join rsdevice dev on pack.deviceid=dev.id left join busccustomer cust on por.customerid=cust.id left join rsdatacenter dc on (por.dcid=dc.id or dev.dcid=dc.id) where (por.status!='99' or por.status is null) ";
		}
		
		if(Boolean.parseBoolean(needRoleFilter)&&DataCenterUtil.queryAllData()){
			querySql=querySql+" and pack.id is not null and dev.id is not null ";
		}else{
			querySql=querySql+" and dev.dcid='"+DataCenterUtil.getDCID()+"'  ";
		}
		
		countSql=" select count(*) from ("+querySql.toString()+")temp ";
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

			List<DeviceportModel> list =  dbm.getObjectList(DeviceportModel.class, sql.toString());
			pm.setList(list);				
			logger.info("call DeviceDao.queryportByDevpackId() success");
		} catch (Exception e) {
			logger.info("call DeviceDao.queryportByDevpackId() fail");
			e.printStackTrace();
		}finally{
			dbm.close();
			dbm=null;
		}		
		logger.info("call DeviceDao.queryportByDevpackId() finish");
		return pm;
	}
	public DeviceModel queryDeviceById(String id){
		dbm=new DBManager();
		logger.info("call DeviceDao.queryDeviceById() start");
		DeviceModel deviceModel=new DeviceModel();
		if(!StringUtil.isEmptyOrNull(id)){
			StringBuilder querySql=new StringBuilder(" select dv.*,rack.name as rackname,room.roomname as roomname,cust.name as customername,dc.name as dcname,ip.id as ipid,ip.ipadd as ipadd,addr.batchIpAdd as batchIpAdd,temp.totalport,temp.freeport,temp.preport,temp.factport,temp.usedport from rsdevice dv left join rsrack rack on dv.rackid=rack.id left join rsroom room on dv.roomid=room.id left join busccustomer cust on dv.customerid=cust.id left join rsip ip on dv.id=ip.deviceid left join rsdatacenter dc on dv.dcid=dc.id left join (select ip.deviceid as deviceid,group_concat(ip.ipadd) as batchIpAdd from rsip ip group by ip.deviceid)addr on ip.deviceid=addr.deviceid left join (");
			querySql.append(" select pack.deviceid AS deviceid,count(*) as totalport,sum(case port.status when '01' then 1 else 0 end) as freeport,sum(case port.status when '02' then 1 else 0 end) as preport,sum(case port.status when '03' then 1 else 0 end) as factport,sum(case port.status when '04' then 1 else 0 end) as usedport from rsnetdevport port left join rsnetdevpack pack on port.netdevpackid=pack.id where (port.status!='99' or port.status is null) group by pack.deviceid ");
			querySql.append(")temp on dv.id=temp.deviceid ");
			querySql.append(" where dv.devicetype='01' and (dv.status!='99' or dv.status is null) and dv.id='"+id+"'" );
			querySql.append(" order by room.id,rack.id ");			
			try {
				deviceModel=  (DeviceModel) dbm.getObject(DeviceModel.class, querySql.toString());
				logger.info("call DeviceDao.queryDeviceById() success");
			} catch (Exception e) {
				logger.info("call DeviceDao.queryDeviceById() fail");
				e.printStackTrace();
			}finally{
				dbm.close();
				dbm=null;
			}
		}
		logger.info("call DeviceDao.queryDeviceById() finish");
		return deviceModel;
	}
	
	public DeviceportModel queryPortById(String id){
		dbm=new DBManager();
		logger.info("call DeviceDao.queryPortById() start");
		DeviceportModel portModel=new DeviceportModel();
		if(!StringUtil.isEmptyOrNull(id)){
			StringBuilder querySql=new StringBuilder("  select por.portcode,por.portname,por.confrate,por.type,por.status,por.macaddr,por.ipaddr,por.rowno,por.colno,por.no,por.description,pack.code as netdevpackcode,cust.no as customercode,cust.name as customername,todev.id as tonetdevid,todev.tonetdevcode as tonetdevcode,todev.tonetdevname as tonetdevname,toserver.id as toserverid,toserver.toservercode as toservercode,toserver.toservername as toservername,dev.name as devicename  from rsnetdevport por left join rsnetdevpack pack on por.netdevpackid=pack.id left join busccustomer cust on por.customerid=cust.id left join (select dev.id as id,dev.code as tonetdevcode,dev.name as tonetdevname from rsdevice dev left join  rsnetdevport por on dev.id=por.tonetdevid where dev.devicetype='01' and por.id='"+id+"')todev on por.tonetdevid=todev.id left join (select dev.id as id,dev.code as toservercode,dev.name as toservername from rsdevice dev left join rsnetdevport por on dev.id=por.toserverid  where dev.devicetype='02' and por.id='"+id+"')toserver on por.toserverid=toserver.id left join rsdevice dev on pack.deviceid=dev.id where por.id='"+id+"' ");
			try {
				portModel=  (DeviceportModel) dbm.getObject(DeviceportModel.class, querySql.toString());
				logger.info("call DeviceDao.queryPortById() success");
			} catch (Exception e) {
				logger.info("call DeviceDao.queryPortById() fail");
				e.printStackTrace();
			}finally{
				dbm.close();
				dbm=null;
			}
		}
		logger.info("call DeviceDao.queryPortById() finish");
		return portModel;
	}
	
	
	
	public DevicepackModel queryDevicePackById(String id){
		dbm=new DBManager();
		logger.info("call DeviceDao.queryDevicePackById() start");
		DevicepackModel devicePack=new DevicepackModel();
		if(!StringUtil.isEmptyOrNull(id)){
			StringBuilder querySql=new StringBuilder(" select pack.* from rsnetdevpack pack  where pack.id='"+id+"' ");
			try {
				devicePack=  (DevicepackModel) dbm.getObject(DevicepackModel.class, querySql.toString());
				logger.info("call DeviceDao.queryDevicePackById() success");
			} catch (Exception e) {
				logger.info("call DeviceDao.queryDevicePackById() fail");
				e.printStackTrace();
			}finally{
				dbm.close();
				dbm=null;
			}
		}
		logger.info("call DeviceDao.queryDevicePackById() finish");
		return devicePack;		
	}
	
	public String saveDevice(DeviceModel deviceModel){
		dbm=new DBManager();
		logger.info("call DeviceDao.saveDevice() start");
		String id=CommUtil.getID();
		logger.info(id);
		if(deviceModel!=null){
			deviceModel.setId(id);
			deviceModel.setDcid(DataCenterUtil.getDCID());
			Rsdevice device=new Rsdevice();
			device.setCreatetime(String.valueOf(new Date().getTime()));
			device.setBuytime(String.valueOf(new Date().getTime()));
			try {
				if(StringUtil.isEmptyOrNull(deviceModel.getStartu())){
					deviceModel.setStartu("0");
				}
				BeanUtils.copyProperties(device, deviceModel);
				dbm.insertObject(device, "rsdevice");
				dbm.executeUpdate(" update rsip set status='04',deviceid='"+id+"' where id='"+deviceModel.getIpid()+"'" );
				dbm.executeUpdate(" update rsuseat set status='04',deviceid='"+id+"' where (convert(no,unsigned) between "+deviceModel.getStartu()+" and "+(Integer.parseInt(deviceModel.getStartu())+deviceModel.getUcount()-1)+")  and rackid='"+deviceModel.getRackid()+"' ");
				logger.info("call DeviceDao.saveDevice() success");
			} catch (IllegalAccessException e) {
				logger.info("call DeviceDao.saveDevice() fail");
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				logger.info("call DeviceDao.saveDevice() fail");
				e.printStackTrace();
			}finally{
				dbm.close();
				dbm=null;
			}
		}
		logger.info("call DeviceDao.saveDevice() finish");
		return id;
	}
	public String saveportDevice(DeviceportModel deviceportModel){
		dbm=new DBManager();
		logger.info("call DeviceDao.saveDevice() start");
		String id=CommUtil.getID();
		logger.info(id);
		if(deviceportModel!=null){
			deviceportModel.setId(id);
			Rsnetdevport deviceport=new Rsnetdevport();
			try {
				BeanUtils.copyProperties(deviceport, deviceportModel);
				dbm.insertObject(deviceport, "rsnetdevport");
				logger.info("call DeviceDao.saveDevice() success");
			} catch (IllegalAccessException e) {
				logger.info("call DeviceDao.saveDevice() fail");
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				logger.info("call DeviceDao.saveDevice() fail");
				e.printStackTrace();
			}finally{
				dbm.close();
				dbm=null;
			}
		}
		logger.info("call DeviceDao.saveDevice() finish");
		return id;
	}
	public String savepackDevice(DevicepackModel devicepackModel){
		dbm=new DBManager();
		logger.info("call DeviceDao.saveDevice() start");
		String id=CommUtil.getID();
		logger.info(id);
		if(devicepackModel!=null){
			devicepackModel.setId(id);
			Rsnetdevpack devicepack=new Rsnetdevpack();
			try {
				BeanUtils.copyProperties(devicepack, devicepackModel);
				dbm.insertObject(devicepack, "rsnetdevpack");
				logger.info("call DeviceDao.saveDevice() success");
			} catch (Exception e) {
				logger.info("call DeviceDao.saveDevice() fail");
				e.printStackTrace();
			} finally{
				dbm.close();
				dbm=null;
			}
		}
		logger.info("call DeviceDao.saveDevice() finish");
		return id;
	}
	public boolean updateDevice(DeviceModel deviceModel){
		dbm=new DBManager();
		logger.info("call DeviceDao.updateDevice() start");
		boolean result=false;
		if(!StringUtil.isEmptyOrNull(deviceModel.getId())){
			Rsdevice device=new Rsdevice();
			try {
				BeanUtils.copyProperties(device, deviceModel);
				IPModel ip=(IPModel) dbm.getObject(IPModel.class, " select * from rsip where deviceid='"+deviceModel.getId()+"' ");
				DeviceModel old=(DeviceModel) dbm.getObject(DeviceModel.class, " select * from rsdevice where id='"+deviceModel.getId()+"' ");
				//操作IP资源
				if(ip!=null){
					dbm.executeUpdate(" update rsip set status='01',deviceid=null where id='"+ip.getId()+"' and deviceid='"+old.getId()+"' " );
					dbm.executeUpdate(" update rsip set status='04',deviceid='"+deviceModel.getId()+"' where id='"+deviceModel.getIpid()+"'" );
				}else{
					dbm.executeUpdate(" update rsip set status='04',deviceid='"+deviceModel.getId()+"' where id='"+deviceModel.getIpid()+"'" );
				}
				//操作U位资源
				if(old!=null){
					dbm.executeUpdate(" update rsuseat set status='01',deviceid=null where rackid='"+old.getRackid()+"' and deviceid='"+old.getId()+"' ");
					dbm.executeUpdate(" update rsuseat set status='04',deviceid='"+deviceModel.getId()+"' where (convert(no,unsigned) between "+Integer.parseInt(deviceModel.getStartu())+" and "+(Integer.parseInt(deviceModel.getStartu())+deviceModel.getUcount()-1)+")  and rackid='"+deviceModel.getRackid()+"' ");					
				}
				
				if(StringUtil.isEmptyOrNull(device.getCreatetime())){
					device.setCreatetime(String.valueOf(new Date().getTime()));
					device.setBuytime(String.valueOf(new Date().getTime()));
				}
				result=dbm.updateObject(device); 
				logger.info("call DeviceDao.updateDevice() success");
			}catch (Exception e) {
				result=false;
				logger.info("call DeviceDao.updateDevice() fail");
				e.printStackTrace();
			}finally{
				dbm.close();
				dbm=null;
			}
		}
		logger.info("call DeviceDao.updateDevice() finish");
		return result;		
	}
	
	public boolean updateDevicePack(DevicepackModel packdevice){
		dbm=new DBManager();
		logger.info("call DeviceDao.updateDevicePack() start");
		boolean result=false;
		if(!StringUtil.isEmptyOrNull(packdevice.getId())){
			Rsnetdevpack pack=new Rsnetdevpack();
			try {
				BeanUtils.copyProperties(pack, packdevice);
				result=dbm.updateObject(pack);
				logger.info("call DeviceDao.updateDevicePack() success");
			}catch (Exception e) {
				result=false;
				logger.info("call DeviceDao.updateDevicePack() fail");
				e.printStackTrace();
			}finally{
				dbm.close();
				dbm=null;
			}
		}
		logger.info("call DeviceDao.updateDevicePack() finish");
		return result;		
	}
	
	public boolean updateDevicePort(DeviceportModel devicePort){
		dbm=new DBManager();
		logger.info("call DeviceDao.updateDevicePort() start");
		boolean result=false;
		if(!StringUtil.isEmptyOrNull(devicePort.getId())){
			Rsnetdevport port=new Rsnetdevport();
			try {
				BeanUtils.copyProperties(port, devicePort);
				result=dbm.updateObject(port);
				logger.info("call DeviceDao.updateDevicePort() success");
			}catch (Exception e) {
				result=false;
				logger.info("call DeviceDao.updateDevicePort() fail");
				e.printStackTrace();
			}finally{
				dbm.close();
				dbm=null;
			}
		}
		logger.info("call DeviceDao.updateDevicePort() finish");
		return result;		
	}
	
	//批量添加端口
	public boolean batchAddPort(DBManager dbm,DevicepackModel devicepackModel){
		boolean result=false;	
		logger.info("call DeviceDao.batchAddPort() start");
		int sort=0;
		try {
			for(int i=0;i<devicepackModel.getPortcount();i++){
				//dbm.addBatch(new String("insert into rsnetdevport(id,portname,portcode,netdevpackid,type,status,customerid,no) values('"+CommUtil.getID()+"','"+(subIP+ip)+"','"+iPSegModel.getId()+"','01','"+iPSegModel.getCustomerid()+"',"+sort+")"));
				sort++;
			}			
			dbm.executeBatch();
			logger.info("call DeviceDao.batchAddPort() success");
		} catch (Exception e) {
			result=false;
			logger.info("call DeviceDao.batchAddPort() fail");
		}finally{			
			dbm.close();
			dbm=null;
		}
		logger.info("call DeviceDao.batchAddPort() finish");	
		return result;		
	}
	
}
