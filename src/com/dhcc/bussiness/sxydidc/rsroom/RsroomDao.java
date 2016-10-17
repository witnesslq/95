package com.dhcc.bussiness.sxydidc.rsroom;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import com.dhcc.bussiness.sxydidc.rsroom.RsroomModel;
import com.dhcc.common.database.DBManager;
import com.dhcc.common.system.page.PageFactory;
import com.dhcc.common.util.CommUtil;
import com.dhcc.common.util.CreateNum;
import com.dhcc.common.util.DataCenterUtil;
import com.dhcc.modal.sxydidc.rsroom.Rsroom;
import com.dhcc.modal.system.PageModel;
import com.dhcc.modal.system.Tsdict;
import com.mockrunner.util.common.StringUtil;

public class RsroomDao {
	private static final Logger logger = Logger.getLogger(RsroomDao.class);
	private static DBManager dbm;  
	private static CreateNum createNum=new CreateNum();
	
	
	public void init(){
		logger.info("call RsroomDao.init() start");
		dbm=new DBManager();
		logger.info("call RsroomDao.init() finish");
	}
	public void destory(){
		logger.info("call RsroomDao.destory() start");
		dbm.close();
		logger.info("call RsroomDao.destory() finish");
	}
	public List<RsroomModel> queryRoomProperty(String roomName){
		dbm=new DBManager();
		logger.info("call RoomDao.queryRoomProperty() start");
		StringBuilder querySql=new StringBuilder(" select room.* from rsroom room ");
		List<RsroomModel> list=new ArrayList<RsroomModel>();
		try {
			
			list=dbm.getObjectList(RsroomModel.class, querySql.toString());
			logger.info("call RoomDao.queryRoomProperty() success");
		} catch (Exception e) {
			logger.info("call RoomDao.queryRoomProperty() fail");
			e.printStackTrace();
		}finally{
			dbm.close();
			dbm=null;
		}
		logger.info("call RoomDao.queryRoomProperty() finish");
		return list;
	}	
	public String saveRoom(RsroomModel rsroomModel){
		this.init();
		logger.info("call RsroomDao.saveRoom() start");
		String result="fail";
		String id=CommUtil.getID();
		if(rsroomModel!=null){
			rsroomModel.setId(id);
			rsroomModel.setDcid(DataCenterUtil.getDCID());
			try {
				Rsroom rsroom=new Rsroom();
				BeanUtils.copyProperties(rsroom, rsroomModel);
				dbm.insertObject(rsroom, "rsroom");
				result="success";
				logger.info("call RsroomDao.saveRoom() success");
				if(rsroomModel.getNeedRack()){//如果需要添加机架
					this.batchAddRack(dbm, rsroomModel);
				}
			} catch (Exception e) {
				result="fail";
				logger.info("call RsroomDao.saveRoom() fail");
				e.printStackTrace();
			}finally{
				this.destory();
			}
		}
		logger.info("call RsroomDao.saveRoom() finish");
		return result;
	}
	
	public RsroomModel queryRoomById(String id){
		this.init();
		logger.info("call RsroomDao.queryRoomById() start");
		RsroomModel rsroomModel=new RsroomModel();
		if(!StringUtil.isEmptyOrNull(id)){
			StringBuilder sql=new StringBuilder("select room.*,area.areaname as area,dc.name as dcname,corp.corpname as deptname,temp.* from rsroom room left join tsarea area on room.areaid=area.id left join rsdatacenter dc on room.dcid=dc.id left join tscorp corp on room.deptid=corp.id left join (");
			sql.append(" select rack.roomid as roomid,rack.typeid,sum(case when rack.status in('01','02','03','04') then 1 else 0 end) as totalRack,sum(case rack.status when '01' then 1 else 0 end) as freeRack,sum(case rack.status when '02' then 1 else 0 end) as disRentRack,sum(case rack.status when '03' then 1 else 0 end) as whlRentRack,sum(case rack.status when '04' then 1 else 0 end) as preRack from rsrack rack where (rack.status!='99' or rack.status is null and cast(rack.typeid as unsigned int)>10) group by rack.roomid ");
			sql.append(")temp on room.id=temp.roomid ");
			sql.append(" where room.id='"+id+"' ");				
				try {
					rsroomModel=(RsroomModel) dbm.getObject(RsroomModel.class, sql.toString()); 
					logger.info("call RsroomDao.queryRoomById() success");
				} catch (Exception e) {
					logger.info("call RsroomDao.queryRoomById() fail");
					e.printStackTrace();
				}finally{
					this.destory();
				}
			
		}
		logger.info("call RsroomDao.queryRoomById() finish");
		return rsroomModel;
	}
	
	public String deleteRoomByIds(String ids){
		this.init();
		logger.info("call RsroomDao.deleteRoomByIds() start");
		String result="03";
			List<String> list=new ArrayList<String>();
			if(!StringUtil.isEmptyOrNull(ids)){
				if(ids.indexOf(",")!=-1){;
					for(String roomId:ids.split(",")){
						int count=dbm.executeQueryCount(" select count(*) from rsrack rack where rack.roomid='"+roomId+"' and(rack.status='02' or rack.status='03') ");
						if(count==0){
							list.add(new String(" update rsroom set status='99' where id='"+roomId+"' and (status='01' or status is null) "));
							result="01";
						}else{
							result="02";
							break;
						}
					}
				}else{
					int count=dbm.executeQueryCount(" select count(*) from rsrack rack where rack.roomid='"+ids+"' and(rack.status='02' or rack.status='03') ");
					if(count==0){
						list.add(new String(" update rsroom set status='99' where id='"+ids+"' and (status='01' or status is null) "));
						result="01";
					}else{
						result="02";
					}
				}
				try {
					if(result.trim().equals("01")){
						dbm.excuteBatchSql(list);
						logger.info("call RsroomDao.deleteRoomByIds() success");
						result="01";
					}else{
						result="02";
						logger.info("call RsroomDao.deleteRoomByIds() fail");
					}
				} catch (Exception e) {
					result="03";
					logger.info("call RsroomDao.deleteRoomByIds() fail");
					e.printStackTrace();
				}finally{
					this.destory();
				}
			}
		logger.info("call RsroomDao.deleteRoomByIds() finish");
		return result;
	}
	
	
	
	public PageModel queryRoom(PageModel pm,String needRoleFilter){
		this.init();
		logger.info("call RsroomDao.queryRoom() start");
		StringBuilder querySql=new StringBuilder("select room.*,area.areaname as area,dc.name as dcname,corp.corpname as deptname,temp.* from rsroom room left join tsarea area on room.areaid=area.id left join rsdatacenter dc on room.dcid=dc.id left join tscorp corp on room.deptid=corp.id left join (");
		querySql.append(" select rack.roomid as roomid,rack.typeid,sum(case when rack.status in('01','02','03','04') then 1 else 0 end) as totalRack,sum(case rack.status when '01' then 1 else 0 end) as freeRack,sum(case rack.status when '02' then 1 else 0 end) as disRentRack,sum(case rack.status when '03' then 1 else 0 end) as whlRentRack,sum(case rack.status when '04' then 1 else 0 end) as preRack from rsrack rack where (rack.status!='99' or rack.status is null and cast(rack.typeid as unsigned int)>10) group by rack.roomid  ");
		querySql.append(")temp on room.id=temp.roomid ");
		querySql.append(" where (room.status!='99' or room.status is null) and (room.dcid='"+DataCenterUtil.getDCID()+"' ");
		if(Boolean.parseBoolean(needRoleFilter)&&DataCenterUtil.queryAllData()){
			querySql.append(" or 1=1 ");
		}
		querySql.append(") ");
		querySql.append(" order by area.id,room.deptid ");
		
		try {
			int count = dbm.executeQueryCount(" select count(*) from ("+querySql.toString()+")tempa ");
			int total = count % pm.getPerPage() == 0 ? count /pm.getPerPage() : count / pm.getPerPage() + 1;
			pm.setTotalPage(total);
			pm.setTotalRecord(count);

			PageFactory pageFactory = new PageFactory();
			String sql = pageFactory.createPageSQL(querySql.toString(), pm);
			pageFactory = null;
			List<RsroomModel> list =  dbm.getObjectList(RsroomModel.class, sql);
			pm.setList(list);
			logger.info("call RsroomDao.queryRoom() success");
		} catch (Exception e) {
			logger.info("call RsroomDao.queryRoom() fail");
			e.printStackTrace();
		}finally{
			this.destory();
		}
		logger.info("call RsroomDao.queryRoom() finish");
		return pm;
	}
	
	public PageModel queryRoomByCondition(PageModel pm,RsroomModel rsroom,String needRoleFilter){
		this.init();
		logger.info("call RsroomDao.queryRoom() start");
		StringBuilder querySql=new StringBuilder("select room.*,area.areaname as area,dc.name as dcname,corp.corpname as deptname,temp.* from rsroom room left join tsarea area on room.areaid=area.id left join rsdatacenter dc on room.dcid=dc.id left join tscorp corp on room.deptid=corp.id left join (");
		querySql.append(" select rack.roomid as roomid,rack.typeid,sum(case when rack.status in('01','02','03','04') then 1 else 0 end) as totalRack,sum(case rack.status when '01' then 1 else 0 end) as freeRack,sum(case rack.status when '02' then 1 else 0 end) as disRentRack,sum(case rack.status when '03' then 1 else 0 end) as whlRentRack,sum(case rack.status when '04' then 1 else 0 end) as preRack from rsrack rack where (rack.status!='99' or rack.status is null and cast(rack.typeid as unsigned int)>10) group by rack.roomid  ");
		querySql.append(")temp on room.id=temp.roomid ");
		querySql.append(" where (room.status!='99' or room.status is null) and (room.dcid='"+DataCenterUtil.getDCID()+"' ");
		if(Boolean.parseBoolean(needRoleFilter)&&DataCenterUtil.queryAllData()){
			querySql.append(" or 1=1 ");
		}
		querySql.append(") ");
		
		
		if(!StringUtil.isEmptyOrNull(rsroom.getRoomcode())){
			querySql.append(" and room.roomcode like '%").append(rsroom.getRoomcode()).append("%' ");
		}
		
		if(!StringUtil.isEmptyOrNull(rsroom.getRoomname())){
			querySql.append(" and room.roomname like '%").append(rsroom.getRoomname()).append("%' ");
		}
		
		if(!StringUtil.isEmptyOrNull(rsroom.getStatus())){
			querySql.append(" and room.status='").append(rsroom.getStatus()).append("' ");
		}
		
		if(!StringUtil.isEmptyOrNull(rsroom.getRacktype())){
			querySql.append(" and room.racktype='").append(rsroom.getRacktype()).append("' ");;
		}
		
		
		querySql.append(" order by area.id,room.deptid ");
		
		try {
			int count = dbm.executeQueryCount(" select count(*) from ("+querySql.toString()+")tempa ");
			int total = count % pm.getPerPage() == 0 ? count /pm.getPerPage() : count / pm.getPerPage() + 1;
			pm.setTotalPage(total);
			pm.setTotalRecord(count);

			PageFactory pageFactory = new PageFactory();
			String sql = pageFactory.createPageSQL(querySql.toString(), pm);
			pageFactory = null;
			List<RsroomModel> list =  dbm.getObjectList(RsroomModel.class, sql);
			pm.setList(list);
			logger.info("call RsroomDao.queryRoom() success");
		} catch (Exception e) {
			logger.info("call RsroomDao.queryRoom() fail");
			e.printStackTrace();
		}finally{
			this.destory();
		}
		logger.info("call RsroomDao.queryRoom() finish");
		return pm;
	}
	
	public 	boolean updateRsroom(RsroomModel roomModel){
		dbm=new DBManager();
		logger.info("call RsroomDao.updateRsroom start");
		boolean result=false;
		if(!StringUtil.isEmptyOrNull(roomModel.getId())){
			Rsroom model=new Rsroom();
			try {
				BeanUtils.copyProperties(model, roomModel);
				result=dbm.updateObject(model); 
				logger.info("call RsroomDao.updateRsroom() success");
			} catch (Exception e) {
				result=false;
				logger.info("call RsroomDao.updateRsroom() fail");
				e.printStackTrace();
			}finally{
				dbm.close();
				dbm=null;
			}
		}
		logger.info("call RsroomDao.updateRsroom() finish");
		return result;			
		
	}
	
	public PageModel quickSearch(PageModel pm,String key,String needRoleFilter){
		this.init();
		logger.info("call RsroomDao.quickSearch() start");
		List<Tsdict> dics=new ArrayList<Tsdict>();
		StringBuilder keys=new StringBuilder("");
		StringBuilder querySql=new StringBuilder("select room.*,area.areaname as area,dc.name as dcname,corp.corpname as deptname,temp.* from rsroom room left join tsarea area on room.areaid=area.id left join rsdatacenter dc on room.dcid=dc.id left join tscorp corp on room.deptid=corp.id left join (");
		querySql.append(" select rack.roomid as roomid,rack.typeid,sum(case when rack.status in('01','02','03','04') then 1 else 0 end) as totalRack,sum(case rack.status when '01' then 1 else 0 end) as freeRack,sum(case rack.status when '02' then 1 else 0 end) as disRentRack,sum(case rack.status when '03' then 1 else 0 end) as whlRentRack,sum(case rack.status when '04' then 1 else 0 end) as preRack from rsrack rack where (rack.status!='99' or rack.status is null and cast(rack.typeid as unsigned int)>10) group by rack.roomid  ");
		querySql.append(")temp on room.id=temp.roomid ");
		querySql.append(" where (room.status!='99' or room.status is null) and (room.dcid='"+DataCenterUtil.getDCID()+"' ");
		if(Boolean.parseBoolean(needRoleFilter)&&DataCenterUtil.queryAllData()){
			querySql.append(" or 1=1 ");
		}
		querySql.append(") ");
		
		StringBuilder conditionSql=new StringBuilder();
		if(!StringUtil.isEmptyOrNull(key)){
			conditionSql.append(" room.roomname like '%").append(key).append("%' ");
			conditionSql.append(" or room.roomcode like '%").append(key).append("%' ");
			dics=this.queryDicIds(dbm, key);
			for(Tsdict dic:dics){
				if(dics.lastIndexOf(dic)!=dics.size()-1){
					keys.append("'").append(dic.getDkey()).append("',");
				}else{
					keys.append("'").append(dic.getDkey()).append("'");
				}
			}

			if(!StringUtil.isEmptyOrNull(keys.toString())){
				conditionSql.append(" or room.grade in(").append(keys.toString()).append(") ");
			}
			
			conditionSql.append(" or corp.corpname like '%").append(key).append("%' ");
			conditionSql.append(" or area.areaname like '%").append(key).append("%' ");
		}
		querySql=querySql.append(" and ("+conditionSql.toString()+") ").append("  order by area.id,room.deptid ");
		try {
			int count = dbm.executeQueryCount(" select count(*) from ("+querySql.toString()+")tempa");
			int total = count % pm.getPerPage() == 0 ? count /pm.getPerPage() : count / pm.getPerPage() + 1;
			pm.setTotalPage(total);
			pm.setTotalRecord(count);

			PageFactory pageFactory = new PageFactory();
			String sql = pageFactory.createPageSQL(querySql.toString(), pm);
			pageFactory = null;
			List<RsroomModel> list =  dbm.getObjectList(RsroomModel.class, sql);
			logger.info(list.size());
			pm.setList(list);
			logger.info("call RsroomDao.quickSearch() success");
		} catch (Exception e) {
			logger.info("call RsroomDao.quickSearch() fail");
			e.printStackTrace();
		}finally{
			this.destory();
		}
		logger.info("call RsroomDao.quickSearch() finish");
		return pm;
	}
	
	private boolean batchAddRack(DBManager dbm,RsroomModel room){
		boolean result=false;	
		logger.info("call RsroomDao.batchAddRack() start");
		String rackId="";
		String useatId="";
		String rackName="";
		String rowNO="";
		String colNO="";
		try {
			for(int i=1;i<=room.getRowcount();i++){
				for(int j=1;j<=room.getColcount();j++){
					rackId=CommUtil.getID();
					rowNO=(char)(65+i-1)+"";
					if((j+"").length()==1){
						colNO="0"+j;
					}else{
						colNO=""+j;
					}
					rackName=rowNO+colNO;
					dbm.addBatch(new String("insert into rsrack(id,name,code,typeid,ucount,roomid,rowno,colno,xposition,yposition,status,dcid) values('"+rackId+"','"+rackName+"','"+createNum.getNum("RACKCODE")+"','"+room.getRacktype()+"',"+(Integer.parseInt(room.getRacktype())<10?0:Integer.parseInt(room.getRacktype()))+",'"+room.getId()+"',"+i+","+j+","+(41*(j-1)+20)+","+(55*(i-1)+20)+",'01','"+room.getDcid()+"')" ));
					if(Integer.parseInt(room.getRacktype())>10){
						for(int k=1;k<=Integer.parseInt(room.getRacktype());k++){
							useatId=CommUtil.getID();
							dbm.addBatch(new String(" insert into rsuseat(id,no,rackid,status) values('"+useatId+"','"+k+"','"+rackId+"','01') "));
						}
					}
				}
			}		
			dbm.executeBatch();
			logger.info("call RsroomDao.batchAddRack() success");
		} catch (Exception e) {
			result=false;
			logger.info("call RsroomDao.batchAddRack() fail");
		}
		logger.info("call RsroomDao.batchAddRack() finish");	
		return result;
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
