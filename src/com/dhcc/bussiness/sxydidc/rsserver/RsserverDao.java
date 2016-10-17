package com.dhcc.bussiness.sxydidc.rsserver;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import com.dhcc.common.database.DBManager;
import com.dhcc.common.system.page.PageFactory;
import com.dhcc.common.util.CommUtil;
import com.dhcc.common.util.CreateNum;
import com.dhcc.common.util.DataCenterUtil;
import com.dhcc.modal.sxydidc.rsserver.Rsdevice;
import com.dhcc.modal.system.PageModel;
import com.mockrunner.util.common.StringUtil;

public class RsserverDao {
	private static final Logger logger = Logger.getLogger(RsserverDao.class);
	private static DBManager dbm;  
	private static CreateNum createNum=new CreateNum();
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	
	public void init(){
		logger.info("call RsserverDao.init() start");
		dbm=new DBManager();
		logger.info("call RsserverDao.init() finish");
	}
	public void destory(){
		logger.info("call RsserverDao.destory() start");
		dbm.close();
		logger.info("call RsserverDao.destory() finish");
	}
	public RsserverModel queryServerById(String id){
		this.init();
		logger.info("call RsserverDao.queryServerById() start");
		RsserverModel rsserverModel=new RsserverModel();
		
		if(!StringUtil.isEmptyOrNull(id)){
			StringBuilder sql=new StringBuilder("select dev.*,room.roomname as roomname,rack.name as rackname,cust.name as customername,dc.name as dcname,ip.ipadd as ipadd,addr.batchIpAdd as batchIpAdd from rsdevice dev left join busccustomer cust on dev.customerid=cust.id left join rsrack rack on dev.rackid=rack.id left join rsroom room on dev.roomid=room.id left join rsip ip on dev.id=ip.deviceid left join rsdatacenter dc on dev.dcid=dc.id left join (select ip.deviceid as deviceid,group_concat(ip.ipadd) as batchIpAdd from rsip ip group by ip.deviceid)addr on ip.deviceid=addr.deviceid where dev.id='"+id+"' ");
			try {
				rsserverModel=(RsserverModel) dbm.getObject(RsserverModel.class, sql.toString());
				if(StringUtil.isEmptyOrNull(rsserverModel.getBuytime())){
					rsserverModel.setBuytime(sdf.format(new Long(System.currentTimeMillis())));
				}else{
					rsserverModel.setBuytime(sdf.format(new Long(rsserverModel.getBuytime())));
				}
				
				if(StringUtil.isEmptyOrNull(rsserverModel.getCreatetime())){
					rsserverModel.setCreatetime(sdf.format(new Long(System.currentTimeMillis())));
				}else{
					rsserverModel.setCreatetime(sdf.format(new Long(rsserverModel.getCreatetime())));
				}
				
				logger.info("call RsserverDao.queryServerById() success");
			} catch (Exception e) {
				logger.info("call RsserverDao.queryServerById() fail");
				e.printStackTrace();
			}finally{
				this.destory();
			}
			
		}
		logger.info("call RsserverDao.queryServerById() finish");
		return rsserverModel;
	}
	
	
	public PageModel queryServer(PageModel pm,String needRoleFilter){
		this.init();
		logger.info("call RsserverDao.queryServer() start");
		StringBuilder countSql=new StringBuilder(" select count(*) from rsdevice dev left join busccustomer cust on dev.customerid=cust.id left join rsrack rack on dev.rackid=rack.id left join rsroom room on dev.roomid=room.id left join rsdatacenter dc on dev.dcid=dc.id left join (select ip.deviceid as deviceid,group_concat(ip.ipadd) as ipadd from rsip ip group by ip.deviceid)ip on dev.id=ip.deviceid where dev.devicetype='02' and (dev.status!='99' or dev.status is null) and (dev.dcid='"+DataCenterUtil.getDCID()+"' ");
		StringBuilder querySql=new StringBuilder(" select dev.*,room.roomname as roomname,rack.name as rackname,cust.name as customername,dc.name as dcname,ip.ipadd as ipadd from rsdevice dev left join busccustomer cust on dev.customerid=cust.id left join rsrack rack on dev.rackid=rack.id left join rsroom room on dev.roomid=room.id left join rsdatacenter dc on dev.dcid=dc.id left join (select ip.deviceid as deviceid,group_concat(ip.ipadd) as ipadd from rsip ip group by ip.deviceid)ip on dev.id=ip.deviceid where dev.devicetype='02' and (dev.status!='99' or dev.status is null) and (dev.dcid='"+DataCenterUtil.getDCID()+"' "); 
		if(Boolean.parseBoolean(needRoleFilter)&&DataCenterUtil.queryAllData()){
			countSql.append(" or 1=1 ");
			querySql.append(" or 1=1 ");
		}
		countSql.append(") ");
		querySql.append(") ");
		querySql.append(" order by cust.no,dev.code ");
		try {
			int count = dbm.executeQueryCount(countSql.toString());
			int total = count % pm.getPerPage() == 0 ? count /pm.getPerPage() : count / pm.getPerPage() + 1;
			pm.setTotalPage(total);
			pm.setTotalRecord(count);

			/**
			 * 分页sql构造
			 */
			PageFactory pageFactory = new PageFactory();
			String sql = pageFactory.createPageSQL(querySql.toString(), pm);
			pageFactory = null;
			List<RsserverModel> list =  dbm.getObjectList(RsserverModel.class, sql);
			logger.info(list.size());
			pm.setList(list);
			logger.info("call RsserverDao.queryServer() success");
		} catch (Exception e) {
			logger.info("call RsserverDao.queryServer() fail");
			e.printStackTrace();
		}finally{
			this.destory();
		}
		logger.info("call RsserverDao.queryServer() finish");
		return pm;
	}
	public PageModel queryFreeServer(PageModel pm,String cusid,String dcid){
		this.init();
		logger.info("call RsserverDao.queryServer() start");
		StringBuilder countSql=new StringBuilder(" select count(*) from rsdevice device where  device.devicetype='02' and (device.status ='01' or (device.status = '02' and device.customerid ='"+cusid+"' )) and device.dcid='"+dcid+"' ");
		System.out.println(countSql);
		String sql1="select device.*,(select cus.name from busccustomer cus where cus.id=device.customerid ) customer," 
				+"(select rack.name from rsrack rack where rack.id=device.rackid) rack," 
				+"(select room.roomname from rsroom room where room.id=device.roomid) room" 
				+",(select dict.dvalue from tsdict dict where dict.dtype='OWNER' and dict.dkey = device.owner) ownername"
				+" from rsdevice device where device.devicetype='02' and (device.status ='01' or (device.status = '02' and device.customerid ='"+cusid+"'  )) and device.dcid='"+dcid+"'";
		try {
			int count = dbm.executeQueryCount(countSql.toString());
			int total = count % pm.getPerPage() == 0 ? count /pm.getPerPage() : count / pm.getPerPage() + 1;
			System.out.println(count);
			pm.setTotalPage(total);
			pm.setTotalRecord(count);

			/**
			 * 分页sql构造
			 */
			PageFactory pageFactory = new PageFactory();
			String sql2 = pageFactory.createPageSQL(sql1, pm);
			pageFactory = null;
			List<RsserverModel> list =  dbm.getObjectList(RsserverModel.class, sql2);
			logger.info(list.size());
			pm.setList(list);
			logger.info("call RsserverDao.queryServer() success");
		} catch (Exception e) {
			logger.info("call RsserverDao.queryServer() fail");
			e.printStackTrace();
		}finally{
			this.destory();
		}
		logger.info("call RsserverDao.queryServer() finish");
		return pm;
	}
	
	public PageModel queryOrderServer(PageModel pm,String cusid,String flownumber,String dcid){
		this.init();
		logger.info("call RsserverDao.queryServer() start");
		
		String sql1="SELECT netport.id as portid,ip.id as ipid,t.*,ip.ipadd as ipadd,netport.portname as portname,(select dict.dvalue from tsdict dict where dict.dtype='OWNER' and dict.dkey = t.owner) ownername"
					+",(select room.roomname from rsroom room where room.id=t.roomid) roomname" 
					+",(select rack.name from rsrack rack where rack.id=t.rackid) rackname " 
					+",(select cus.name from busccustomer cus where cus.id=t.customerid ) customer FROM rsdevice t "
					+"LEFT JOIN rsip ip ON t.id=ip.deviceid "
					+"LEFT JOIN rsipseg ipseg ON ip.ipsegid=ipseg.id "
					+"LEFT JOIN rsnetdevport netport ON t.id=netport.toserverid "
					+"LEFT JOIN BUSORDERRES orderres ON orderres.resid = t.id "
					+"where  orderres.orderid=(SELECT ID FROM BUSORDER o WHERE o.UPDATEAPPLYNO = '" + flownumber + "')  AND t.devicetype='02' and t.dcid='"+dcid+"' GROUP BY t.id ";
		
		StringBuilder countSql=new StringBuilder(" select count(*) from BUSORDERRES orderres where orderres.orderid=(SELECT ID FROM BUSORDER o WHERE o.UPDATEAPPLYNO = '" + flownumber + "') and restype='Server' ");
		try {
			int count = dbm.executeQueryCount(countSql.toString());
			int total = count % pm.getPerPage() == 0 ? count /pm.getPerPage() : count / pm.getPerPage() + 1;
			System.out.println(count);
			pm.setTotalPage(total);
			pm.setTotalRecord(count);

			/**
			 * 分页sql构造
			 */
			PageFactory pageFactory = new PageFactory();
			String sql2 = pageFactory.createPageSQL(sql1, pm);
			pageFactory = null;
			List<RsserverModel> list =  dbm.getObjectList(RsserverModel.class, sql2);
			logger.info(list.size());
			pm.setList(list);
			logger.info("call RsserverDao.queryServer() success");
		} catch (Exception e) {
			logger.info("call RsserverDao.queryServer() fail");
			e.printStackTrace();
		}finally{
			this.destory();
		}
		logger.info("call RsserverDao.queryServer() finish");
		return pm;
	}
	
	
	
	
	public PageModel queryServerByCondition(PageModel pm,RsserverModel rsserver,String needRoleFilter){
		this.init();
		logger.info("call RsserverDao.queryServerByCondition() start");
		StringBuilder querySql=new StringBuilder(" select dev.*,room.roomname as roomname,rack.name as rackname,cust.name as customername,dc.name as dcname,ip.ipadd as ipadd from rsdevice dev left join busccustomer cust on dev.customerid=cust.id left join rsrack rack on dev.rackid=rack.id left join rsroom room on dev.roomid=room.id left join rsdatacenter dc on dev.dcid=dc.id left join (select ip.deviceid as deviceid,group_concat(ip.ipadd) as ipadd from rsip ip group by ip.deviceid)ip on dev.id=ip.deviceid where dev.devicetype='02' and (dev.status!='99' or dev.status is null) and (dev.dcid='"+DataCenterUtil.getDCID()+"' ");		
		if(Boolean.parseBoolean(needRoleFilter)&&DataCenterUtil.queryAllData()){
			querySql.append(" or 1=1 ");
		}
		querySql.append(") ");
		
		StringBuilder conditionSql=new StringBuilder();
		if(!StringUtil.isEmptyOrNull(rsserver.getName())){
			conditionSql.append(" and dev.name like '%").append(rsserver.getName()).append("%' ");
		}

		if(!StringUtil.isEmptyOrNull(rsserver.getCode())){
			conditionSql.append(" and dev.code like '%").append(rsserver.getCode()).append("%' ");
		}
		
		if(!StringUtil.isEmptyOrNull(rsserver.getSn())){
			conditionSql.append(" and dev.sn like '%").append(rsserver.getSn()).append("%' ");
		}
		
		if(!StringUtil.isEmptyOrNull(rsserver.getModuletype())){
			conditionSql.append(" and dev.moduletype like '%").append(rsserver.getModuletype()).append("%' ");
		}
		
		if(!StringUtil.isEmptyOrNull(rsserver.getDevicetype())){
			conditionSql.append(" and dev.devicetype='").append(rsserver.getDevicetype()).append("' ");
		}
		
		if(!rsserver.getOwner().trim().equals("0")){
			conditionSql.append(" and dev.owner=").append(rsserver.getOwner()).append(" ");
		}
		
		if(!StringUtil.isEmptyOrNull(rsserver.getRoomid())){
			conditionSql.append(" and dev.roomid='").append(rsserver.getRoomid()).append("' ");
		}
		
		if(!StringUtil.isEmptyOrNull(rsserver.getRackid())){
			conditionSql.append(" and dev.rackid='").append(rsserver.getRackid()).append("' ");
		}
		
		if(!StringUtil.isEmptyOrNull(rsserver.getIpid())){
			conditionSql.append(" and ip.id='").append(rsserver.getIpid()).append("' ");
		}
		
		if(!StringUtil.isEmptyOrNull(rsserver.getCustomerid())){
			conditionSql.append(" and dev.customerid='").append(rsserver.getCustomerid()).append("' ");
		}
		
		if(!StringUtil.isEmptyOrNull(rsserver.getStatus())){
			conditionSql.append(" and dev.status='").append(rsserver.getStatus()).append("' ");
		}

		querySql=querySql.append(conditionSql.toString()).append(" order by cust.no,dev.code ");
		
		try {
			int count = dbm.executeQueryCount(" select count(*) from ("+querySql.toString()+")tempa ");
			int total = count % pm.getPerPage() == 0 ? count /pm.getPerPage() : count / pm.getPerPage() + 1;
			pm.setTotalPage(total);
			pm.setTotalRecord(count);

			/**
			 * 分页sql构造
			 */
			PageFactory pageFactory = new PageFactory();
			String sql = pageFactory.createPageSQL(querySql.toString(), pm);
			pageFactory = null;
			List<RsserverModel> list =  dbm.getObjectList(RsserverModel.class, sql);
			pm.setList(list);
			logger.info("call RsserverDao.queryServerByCondition() success");
		} catch (Exception e) {
			logger.info("call RsserverDao.queryServerByCondition() fail");
			e.printStackTrace();
		}finally{
			this.destory();
		}
		logger.info("call RsserverDao.queryServerByCondition() finish");
		return pm;
	}
	
	public PageModel quickSearch(PageModel pm,String key,String needRoleFilter){
		this.init();
		logger.info("call RsserverDao.quickSearch() start");
		StringBuilder countSql=new StringBuilder(" select count(*) from rsdevice dev left join busccustomer cust on dev.customerid=cust.id left join rsrack rack on dev.rackid=rack.id left join rsroom room on dev.roomid=room.id left join rsdatacenter dc on dev.dcid=dc.id left join (select ip.deviceid as deviceid,group_concat(ip.ipadd) as ipadd from rsip ip group by ip.deviceid)ip on dev.id=ip.deviceid where dev.devicetype='02' and (dev.status!='99' or dev.status is null) and (dev.dcid='"+DataCenterUtil.getDCID()+"' ");
		StringBuilder querySql=new StringBuilder(" select dev.*,room.roomname as roomname,rack.name as rackname,cust.name as customername,dc.name as dcname,ip.ipadd as ipadd from rsdevice dev left join busccustomer cust on dev.customerid=cust.id left join rsrack rack on dev.rackid=rack.id left join rsroom room on dev.roomid=room.id left join rsdatacenter dc on dev.dcid=dc.id left join (select ip.deviceid as deviceid,group_concat(ip.ipadd) as ipadd from rsip ip group by ip.deviceid)ip on dev.id=ip.deviceid where dev.devicetype='02' and (dev.status!='99' or dev.status is null) and (dev.dcid='"+DataCenterUtil.getDCID()+"' ");		
		
		if(Boolean.parseBoolean(needRoleFilter)&&DataCenterUtil.queryAllData()){
			countSql.append(" or 1=1 ");
			querySql.append(" or 1=1 ");
		}
		countSql.append(") ");
		querySql.append(") ");
		
		StringBuilder conditionSql=new StringBuilder();
		if(!StringUtil.isEmptyOrNull(key)){
			conditionSql.append(" dev.name like '%").append(key).append("%' ");
			conditionSql.append(" or dev.code like '%").append(key).append("%' ");
			conditionSql.append(" or dev.sn like '%").append(key).append("%' ");
			conditionSql.append(" or dev.moduletype like '%").append(key).append("%' ");
			conditionSql.append(" or dev.useyears like '%").append(key).append("%' ");
			conditionSql.append(" or room.roomname like '%").append(key).append("%' ");
			conditionSql.append(" or rack.name like '%").append(key).append("%' ");
			conditionSql.append(" or cust.name like '%").append(key).append("%' ");
			conditionSql.append(" or ip.ipadd like '%").append(key).append("%' ");
		}
		countSql=countSql.append(" and("+conditionSql.toString()+") ");
		querySql=querySql.append(" and("+conditionSql.toString()+") ").append(" order by cust.no,dev.code ");
		
		try {
			int count = dbm.executeQueryCount(countSql.toString());
			int total = count % pm.getPerPage() == 0 ? count /pm.getPerPage() : count / pm.getPerPage() + 1;
			pm.setTotalPage(total);
			pm.setTotalRecord(count);

			/**
			 * 分页sql构造
			 */
			PageFactory pageFactory = new PageFactory();
			String sql = pageFactory.createPageSQL(querySql.toString(), pm);
			pageFactory = null;
			List<RsserverModel> list =  dbm.getObjectList(RsserverModel.class, sql);
			pm.setList(list);
			logger.info("call RsserverDao.quickSearch() success");
		} catch (Exception e) {
			logger.info("call RsserverDao.quickSearch() fail");
			e.printStackTrace();
		}finally{
			this.destory();
		}
		logger.info("call RsserverDao.quickSearch() finish");
		return pm;
	}
	
	
	
	public String saveServer(RsserverModel rsserverModel){
		this.init();
		logger.info("call RsserverDao.saveServer() start");
		Date now=new Date();
		String id="";
		try {
			if(!StringUtil.isEmptyOrNull(rsserverModel.getAddcount())&&Integer.parseInt(rsserverModel.getAddcount())>1){
				
				if(StringUtil.isEmptyOrNull(rsserverModel.getCreatetime())){
					rsserverModel.setCreatetime(String.valueOf(System.currentTimeMillis()));
				}else{
					rsserverModel.setCreatetime(String.valueOf(sdf.parse(rsserverModel.getCreatetime()).getTime()));
				}
				
				if(StringUtil.isEmptyOrNull(rsserverModel.getBuytime())){
					rsserverModel.setBuytime(String.valueOf(System.currentTimeMillis()));
				}else{
					rsserverModel.setBuytime(String.valueOf(sdf.parse(rsserverModel.getBuytime()).getTime()));
				}
				
				for(int i=1;i<=Integer.parseInt(rsserverModel.getAddcount());i++){
					id=CommUtil.getID();
					dbm.addBatch(new String(" insert into rsdevice(id,name,code,moduletype,devicetype,owner,power,ucount,createtime,buytime,status,useyears,dcid) values('"+id+"','"+new String(rsserverModel.getName()+"-"+i)+"','"+createNum.getNum("SERVERNO")+"','"+rsserverModel.getModuletype()+"','02',1,"+rsserverModel.getPower()+","+rsserverModel.getUcount()+","+new Long(rsserverModel.getCreatetime())+","+new Long(rsserverModel.getBuytime())+",'01','"+rsserverModel.getUseyears()+"','"+DataCenterUtil.getDCID()+"' )"));					
				}
				dbm.executeBatch();
				logger.info("call RsserverDao.saveServer() success");
			}else{
				if(rsserverModel!=null){
					id=CommUtil.getID();
					rsserverModel.setId(id);
					rsserverModel.setDcid(DataCenterUtil.getDCID());
					Rsdevice server=new Rsdevice();
					if(StringUtil.isEmptyOrNull(rsserverModel.getCreatetime())){
						rsserverModel.setCreatetime(String.valueOf(now.getTime()));
					}else{
						rsserverModel.setCreatetime(String.valueOf(sdf.parse(rsserverModel.getCreatetime()).getTime()));
					}
					
					if(StringUtil.isEmptyOrNull(rsserverModel.getBuytime())){
						rsserverModel.setBuytime(String.valueOf(now.getTime()));
					}else{
						rsserverModel.setBuytime(String.valueOf(sdf.parse(rsserverModel.getBuytime()).getTime()));
					}				
					BeanUtils.copyProperties(server, rsserverModel);
					dbm.insertObject(server, "rsdevice");
					logger.info("call RsserverDao.saveServer() success");					
				}
			}			
		} catch (Exception e) {
			logger.info("call RsserverDao.saveServer() fail");
			e.printStackTrace();
		}finally{
			this.destory();
		}		
		logger.info("call RsserverDao.saveServer() finish");
		return id;		
	}
	
	public 	boolean updateServer(RsserverModel rsserverModel){
		this.init();
		logger.info("call RsserverDao.updateServer() start");
		boolean result=false;
		Date now =new Date();
		if(!StringUtil.isEmptyOrNull(rsserverModel.getId())){
			Rsdevice server=new Rsdevice();
			try {
				if(StringUtil.isEmptyOrNull(rsserverModel.getCreatetime())){
					rsserverModel.setCreatetime(String.valueOf(System.currentTimeMillis()));
				}else{
					rsserverModel.setCreatetime(String.valueOf(sdf.parse(rsserverModel.getCreatetime()).getTime()));
				}
				
				if(StringUtil.isEmptyOrNull(rsserverModel.getBuytime())){
					rsserverModel.setBuytime(String.valueOf(System.currentTimeMillis()));
				}else{
					rsserverModel.setBuytime(String.valueOf(sdf.parse(rsserverModel.getBuytime()).getTime()));
				}
				BeanUtils.copyProperties(server, rsserverModel);
				result=dbm.updateObject(server); 
				logger.info("call RsserverDao.updateServer() success");
			} catch (Exception e) {
				result=false;
				logger.info("call RsserverDao.updateServer() fail");
				e.printStackTrace();
			}finally{
				this.destory();
			}
		}
		logger.info("call RsserverDao.updateServer() finish");
		return result;
	}
	
	
	public String deleteServerByIds(String ids){
		this.init();
		logger.info("call RsserverDao.deleteServerByIds() start");
		String result="03";
			List<String> list=new ArrayList<String>();
			if(!StringUtil.isEmptyOrNull(ids)){
				if(ids.indexOf(",")!=-1){//如果是批量删除
					ids=ids.replaceAll(",", "','");
				}
				int count=dbm.executeQueryCount(" select count(*) from rsdevice where status in('02','03','04','05') and id in('"+ids+"') ");
				if(count==0){
					list.add(new StringBuilder(" update rsdevice set status='99' where id in('"+ids+"') and status='01' ").toString());
					result="01";
				}else{
					result="02";
				}
				try {
					if(result.trim().equals("01")){
						dbm.excuteBatchSql(list); 
						logger.info("call RsserverDao.deleteServerByIds() success");						
					}
				} catch (Exception e) {
					result="03";
					logger.info("call RsserverDao.deleteServerByIds() fail");
					e.printStackTrace();
				}finally{
					this.destory();
				}
			}
		logger.info("call RsserverDao.deleteServerByIds() finish");
		return result;
	}
	
	
	
}
