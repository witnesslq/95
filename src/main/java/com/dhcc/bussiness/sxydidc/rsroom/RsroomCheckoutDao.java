package com.dhcc.bussiness.sxydidc.rsroom;
import java.util.ArrayList;
import java.util.List;

import com.dhcc.bussiness.sxydidc.rack.RackModel;
import com.dhcc.bussiness.sxydidc.rack.RoomRackUModel;
import com.dhcc.bussiness.sxydidc.rsserver.RsserverModelRs;
import com.dhcc.common.database.DBManager;
import com.dhcc.modal.system.Tsdict;
import com.mockrunner.util.common.StringUtil;

public class RsroomCheckoutDao {
	  public List<RackModel> getRackInfo(String id){
		   List<RackModel> model = null;
		   DBManager dbm = new DBManager();  
		   if(!StringUtil.isEmptyOrNull(id)){
			   StringBuilder querySql=new StringBuilder("select rack.*,room.roomname as roomname,temp.name as customername,temp.totalu as totalu,temp.freeu as freeu,temp.disrentu as disrentu,temp.whlrentu as whlrentu,temp.preu as preu ");
			   querySql.append(" from rsrack rack left join rsroom room on rack.roomid=room.id");
			   querySql.append(" left join ( select u.rackid as rackid,group_concat(distinct(cust.name)) as name,count(*) as totalu,sum(case u.status when '01' then 1 else 0 end) as freeu,sum(case u.status when '02' then 1 else 0 end) as preu,sum(case u.status when '03' then 1 else 0 end) as disrentu,sum(case u.status when '04' then 1 else 0 end) as whlrentu from rsuseat u left join busccustomer cust on cust.id=u.customerid where (u.status!='99' or u.status is null) group by u.rackid )temp ");
			   querySql.append(" on rack.id=temp.rackid  where rack.roomid='"+id+"' and  rack.status!='99'order by room.id,rack.id  ");
			 		try {
						model=dbm.getObjectList(RackModel.class, querySql.toString()); 
					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						dbm.close();
					}
				
			}
		return model;   
	   }
	  
	  public List<RoomStatuInfoModel> getStatuInfoInfo(String id){
		   List<RoomStatuInfoModel> model = null;
		   DBManager dbm = new DBManager();
		   if(!StringUtil.isEmptyOrNull(id)){
				 String sql ="select count(*) as number, status from rsrack  where roomid='"+id+"' and status!='99' group by status";
					try {
						model=dbm.getObjectList(RoomStatuInfoModel.class, sql); 
					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						dbm.close();
					}
				
			}
		return model;   
	   }
	  
	  public List<RoomRackUModel> getUInfo(String id){
		   List<RoomRackUModel> model = null;
		   DBManager dbm = new DBManager();
		   if(!StringUtil.isEmptyOrNull(id)){
				 String sql ="select useat.*,device.name as devicename,cust.name as customername,ips.ip as ip,ports.portname as port,device.status as runstatus ,device.moduletype as model ,unumber.uno as uno,unumber.nonumber as nonumber,device.devicetype as devicetype from rsuseat useat left join rsdevice device on useat.deviceid=device.id left join busccustomer cust on useat.customerid=cust.id left join (select  group_concat(DISTINCT ipadd) as ip, deviceid from rsip group by deviceid) ips on ips.deviceid=device.id left join  (select  group_concat(DISTINCT portname) as portname, toserverid,tonetdevid from rsnetdevport group by toserverid,tonetdevid)  ports on device.id=ports.toserverid or  device.id=ports.tonetdevid left  join ( select count(*)  as uno,deviceid ,max(no) as nonumber from  rsuseat  group by deviceid) unumber on unumber.deviceid=useat.deviceid where useat.rackid='"+id+"' order by useat.no desc ";
					try {
						model=dbm.getObjectList(RoomRackUModel.class, sql); 
					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						dbm.close();
					}
				
			}
		return model;   
	   }
	  public RsserverModelRs getdeviceInfo(String id){
		   RsserverModelRs model = new RsserverModelRs();
		   DBManager dbm = new DBManager();
		   if(!StringUtil.isEmptyOrNull(id)){
				 String sql ="select device.*,(select cus.name from busccustomer cus where cus.id=device.customerid ) customer,(select rack.name from rsrack rack where rack.id=device.rackid) rack,(select room.roomname from rsroom room where room.id=device.roomid) room,(select dict.dvalue from tsdict dict where dict.dtype='OWNER' and dict.dkey = device.owner) ownername ,seat.Uno as Uno ,seat.minU as minU ,seat.maxU as maxU , FROM_UNIXTIME(device.buytime/1000,'%Y-%m-%d %H:%i:%s') as gettime from rsdevice device left join (select count(*) as Uno,min(no) as minU,max(no) as maxU,deviceid from rsuseat group by  deviceid) seat on seat.deviceid=device.id where device.id='"+id+"' ";
					try {
						model=(RsserverModelRs)dbm.getObject(RsserverModelRs.class, sql); 
					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						dbm.close();
					}
				
			}
		return model;   
	   }
	  
	  public RackModel getOneRackInfo(String id){
		  RackModel model = new RackModel();
		   DBManager dbm = new DBManager();
		   if(!StringUtil.isEmptyOrNull(id)){
				 String sql ="select rack.*,room.roomname as roomname from rsrack rack left join rsroom room on rack.roomid=room.id  where rack.id='"+id+"'";
					try {
						model=(RackModel)dbm.getObject(RackModel.class, sql); 
					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						dbm.close();
					}
				
			}
		return model;   
	   }
	  
	  
	  public RackModel updateRackInfo(String id,String rowno,String colno,String xposition,String yposition){
		  RackModel model = new RackModel();
		   DBManager dbm = new DBManager();
		   if(!StringUtil.isEmptyOrNull(id)){
				 String sql ="update rsrack set colno='"+colno+"' ,rowno='"+rowno+"',xposition='"+xposition+"',yposition='"+yposition+"' where  id='"+id+"'";
					try {
						dbm.executeUpdate(sql); 
					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						dbm.close();
					}
				
			}
		return model;   
	   }
	  
	  
	  public RsroomModel queryRoomInfo(String room_id){
		  RsroomModel model = new RsroomModel();
		   DBManager dbm = new DBManager();
		  
				 String sql ="select * from rsroom where id='"+room_id+"'";
					try {
						
						model=(RsroomModel)dbm.getObject(RsroomModel.class, sql); 
					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						dbm.close();
					}
		return model;   
	   }
	  
	  
	  public List<RackModel> queryAllRackInfo(String id){
		  List<RackModel> model = null;
		   DBManager dbm = new DBManager();
		   if(!StringUtil.isEmptyOrNull(id)){
				 String sql ="select * from rsrack where roomid='"+id+"' and status!='99'";
					try {
						model=dbm.getObjectList(RackModel.class, sql); 
					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						dbm.close();
					}
				
			}
		return model;   
	   }
	  public List<RoomAndRackModel> getRoomAndRackInfo(String room_id){
		  List<RoomAndRackModel> model = null;
		   DBManager dbm = new DBManager();
		   if(!StringUtil.isEmptyOrNull(room_id)){
				 String sql ="select * from rsrack rack left join rsroom room on rack.roomid=room.id where roomid='"+room_id+"' and rack.status!='99'";
					try {
						model=dbm.getObjectList(RoomAndRackModel.class, sql); 
					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						dbm.close();
					}
				
			}
		return model;   
	  }
	  public void updateDistion(List<RackModel> info){
		   DBManager dbm = new DBManager();
		   if(info.size()>0){
			   List<String> sqls = new ArrayList<String>();
			   for(int i=0;i<info.size();i++){
				 String sql ="update rsrack set xposition='"+info.get(i).getXposition()+"',yposition='"+info.get(i).getYposition()+"' where  id='"+info.get(i).getId()+"'";
				 sqls.add(sql);
			   }	
				 try {
						dbm.excuteBatchSql(sqls); 
					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						dbm.close();
					}
				
			}
	  }
	  public Tsdict racktype(){
		  Tsdict model = new Tsdict();
		   DBManager dbm = new DBManager();
		  
				 String sql ="select * from tsdict where dtype='RACKTYPE' and dvalue='柱子'";
					try {
						
						model=(Tsdict)dbm.getObject(Tsdict.class, sql); 
					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						dbm.close();
					}
		return model;     
	  }
	  public void deleteOneRack(String id){
		   DBManager dbm = new DBManager();
		   if(id.length()>0){  
				 String sql ="update rsrack set status='99' where  id='"+id+"'";   
				 try {
						dbm.executeUpdate(sql); 
					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						dbm.close();
					}
				
			}
	  }
	  public void deleteSomeRack(String ids){
		   DBManager dbm = new DBManager();
		   List<String> sqls = new ArrayList<String>();
		   if(ids.length()>0){  
			   for(int i=0;i<ids.split(",").length;i++){
				
				 String sql ="update rsrack set status='99' where  id='"+ids.split(",")[i]+"'";  
				 String sql2="update rsuseat set status='99' where  rackid='"+ids.split(",")[i]+"'";  
				 sqls.add(sql);
				 sqls.add(sql2);
				 }
				 try {
						dbm.excuteBatchSql(sqls); 
					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						dbm.close();
					}
				
			}
	  }
	  public int selectUSatus(String ids){
		  DBManager dbm = new DBManager();
		  int count=0;
		  String sql ="select count(*) from rsuseat where status!='01' and rackid in("+ids+")";  
				try {	
				count=dbm.executeQueryCount(sql); 
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				dbm.close();
			}
		return count;	
	  }
	  public int selectdRackSatus(String ids){
		  DBManager dbm = new DBManager();
		  int count=0;
		  String sql ="select count(*) from rsrack where  typeid='03' and id in("+ids+")";  
				try {	
				count=dbm.executeQueryCount(sql); 
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				dbm.close();
			}
		return count;	
	  }
	  public void saveSomeRack(List<String> sqls){
		  DBManager dbm = new DBManager();
		  try {
				dbm.excuteBatchSql(sqls); 
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				dbm.close();
			}
	  }
}
