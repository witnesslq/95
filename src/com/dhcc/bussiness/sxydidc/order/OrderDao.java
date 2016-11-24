package com.dhcc.bussiness.sxydidc.order;
import com.dhcc.common.database.DBManager;
import com.mockrunner.util.common.StringUtil;



public class OrderDao {


	
	
	public int queryOccuipedResourceCount(String customerId, String resType){
		DBManager dbm = new DBManager();
		int count = 0;
		try {
			String countSql = "";
			if(resType.trim().equals("SERVER")) {
				countSql = "SELECT count(*) FROM RSDEVICE WHERE CUSTOMERID = '" + customerId + "' AND STATUS = '02'";
			} else if(resType.trim().equals("RACK")) {
				countSql = "SELECT count(*) FROM RSRACK WHERE CUSTOMERID = '" + customerId + "' AND STATUS = '04'";
			} else if(resType.trim().equals("USEAT")) {
				countSql = "SELECT count(*) FROM RSUSEAT USEAT LEFT OUTER JOIN RSRACK RACK ON USEAT.RACKID = RACK.ID WHERE USEAT.CUSTOMERID = '" + customerId + "' AND USEAT.STATUS = '02' AND RACK.STATUS != '04'";
			} else if(resType.trim().equals("IPSEG")) {
				countSql = "SELECT count(*) FROM RSIPSEG WHERE CUSTOMERID = '" + customerId + "' AND STATUS = '04'";
			} else if(resType.trim().equals("IP")) {
				countSql = "SELECT count(*) FROM RSIP IP LEFT OUTER JOIN RSIPSEG IPSEG ON IP.IPSEGID = IPSEG.ID WHERE IP.CUSTOMERID = '" + customerId + "' AND IP.STATUS = '02' AND IPSEG.STATUS != '04'";
			} else if(resType.trim().equals("PORT")) {
				countSql = "SELECT count(*) FROM RSNETDEVPORT WHERE CUSTOMERID = '" + customerId + "' AND STATUS = '02'";
			}
			count = dbm.executeQueryCount(countSql);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dbm.close();
			dbm = null;
		}
		return count;
	}
	public int queryOrderResourceCount(OrderModel order,String restype){
		DBManager dbm=new DBManager();
		int count=0;
		String countSql="";
		if(restype.trim().equals("SERVER")){
			if(!StringUtil.isEmptyOrNull(order.getCustid())){
				countSql="select count(*) from busorder ord left join busorderres res on ord.id=res.orderid left join rsdevice dev on res.resid=dev.id left join rsrack rack on dev.rackid=rack.id left join rsroom room on rack.roomid=room.id where dev.devicetype='02' and res.restype='Server' and ord.custid='"+order.getCustid()+"'";				
			}else if(!StringUtil.isEmptyOrNull(order.getId())){
				countSql="select count(*) from busorder ord left join busorderres res on ord.id=res.orderid left join rsdevice dev on res.resid=dev.id left join rsrack rack on dev.rackid=rack.id left join rsroom room on rack.roomid=room.id where dev.devicetype='02' and res.restype='Server' and ord.id='"+order.getId()+"'";
			}
		}else if(restype.trim().equals("RACK")){
			if(!StringUtil.isEmptyOrNull(order.getCustid())){
				countSql="select count(*) from busorder ord left join busorderres res on ord.id=res.orderid left join rsrack rack on res.resid=rack.id left join rsroom room on rack.roomid=room.id where res.restype='Rack' and ord.custid='"+order.getCustid()+"'";
			}else if(!StringUtil.isEmptyOrNull(order.getId())){
				countSql="select count(*) from busorder ord left join busorderres res on ord.id=res.orderid left join rsrack rack on res.resid=rack.id left join rsroom room on rack.roomid=room.id where res.restype='Rack' and ord.id='"+order.getId()+"'";
			}			
		}else if(restype.trim().equals("USEAT")){
			if(!StringUtil.isEmptyOrNull(order.getCustid())){
				countSql="select count(*) from busorder ord left join busorderres res on ord.id=res.orderid left join rsuseat useat on res.resid=useat.id left join rsdevice dev on useat.deviceid=dev.id left join rsrack rack on useat.rackid=rack.id where res.restype='Useat' and ord.custid='"+order.getCustid()+"'";
			}else if(!StringUtil.isEmptyOrNull(order.getId())){
				countSql="select count(*) from busorder ord left join busorderres res on ord.id=res.orderid left join rsuseat useat on res.resid=useat.id left join rsdevice dev on useat.deviceid=dev.id left join rsrack rack on useat.rackid=rack.id where res.restype='Useat' and ord.id='"+order.getId()+"'";
			}			
		}else if(restype.trim().equals("IPSEG")){
			if(!StringUtil.isEmptyOrNull(order.getCustid())){
				countSql="select count(*) from busorder ord left join busorderres res on ord.id=res.orderid left join rsipseg seg on res.resid=seg.id where res.restype='Ipseg' and ord.custid='"+order.getCustid()+"'";
			}else if(!StringUtil.isEmptyOrNull(order.getId())){
				countSql="select count(*) from busorder ord left join busorderres res on ord.id=res.orderid left join rsipseg seg on res.resid=seg.id where res.restype='Ipseg' and ord.id='"+order.getId()+"'";
			}			
		}else if(restype.trim().equals("IP")){
			if(!StringUtil.isEmptyOrNull(order.getCustid())){
				countSql="select count(*) from busorder ord left join busorderres res on ord.id=res.orderid left join rsip ip on res.resid=ip.id left join rsipseg seg on ip.ipsegid=seg.id left join rsdevice dev on ip.deviceid=dev.id where res.restype='Ip' and ord.custid='"+order.getCustid()+"'";
			}else if(!StringUtil.isEmptyOrNull(order.getId())){
				countSql="select count(*) from busorder ord left join busorderres res on ord.id=res.orderid left join rsip ip on res.resid=ip.id left join rsipseg seg on ip.ipsegid=seg.id left join rsdevice dev on ip.deviceid=dev.id where res.restype='Ip' and ord.id='"+order.getId()+"'";
			}			
		}else if(restype.trim().equals("PORT")){
			if(!StringUtil.isEmptyOrNull(order.getCustid())){
				countSql="select count(*) from busorder ord left join busorderres res on ord.id=res.orderid left join rsnetdevport por on res.resid=por.id left join rsnetdevpack pack on por.netdevpackid=pack.id left join rsdevice dev on pack.deviceid=dev.id where res.restype='Port' and ord.custid='"+order.getCustid()+"'";
			}else if(!StringUtil.isEmptyOrNull(order.getId())){
				countSql="select count(*) from busorder ord left join busorderres res on ord.id=res.orderid left join rsnetdevport por on res.resid=por.id left join rsnetdevpack pack on por.netdevpackid=pack.id left join rsdevice dev on pack.deviceid=dev.id where res.restype='Port' and ord.id='"+order.getId()+"'";
			}			
		}
		
		try {
			count =dbm.executeQueryCount(countSql);

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbm.close();
			dbm=null;
		}

		return count;
	}
	

	public int queryHisOrderResourceCountByApplyno(OrderModel order,String restype){
		DBManager dbm=new DBManager();
		int count=0;
		
		String countSql="";
		if(restype.trim().equals("SERVER")){
			if(!StringUtil.isEmptyOrNull(order.getApplyno())){
				countSql="select count(*) from busorderhis ord left join busorderreshis res on ord.orderid=res.orderid left join rsdevice dev on res.resid=dev.id left join rsrack rack on dev.rackid=rack.id left join rsroom room on rack.roomid=room.id where dev.devicetype='02' and res.restype='Server' and ord.applyno='"+order.getApplyno()+"'";
			}
		}else if(restype.trim().equals("RACK")){
			if(!StringUtil.isEmptyOrNull(order.getApplyno())){
				countSql="select count(*) from busorderhis ord left join busorderreshis res on ord.orderid=res.orderid left join rsrack rack on res.resid=rack.id left join rsroom room on rack.roomid=room.id where res.restype='Rack' and ord.applyno='"+order.getApplyno()+"'";
			}			
		}else if(restype.trim().equals("USEAT")){
			if(!StringUtil.isEmptyOrNull(order.getApplyno())){
				countSql="select count(*) from busorderhis ord left join busorderreshis res on ord.orderid=res.orderid left join rsuseat useat on res.resid=useat.id left join rsdevice dev on useat.deviceid=dev.id left join rsrack rack on useat.rackid=rack.id where res.restype='Useat' and ord.applyno='"+order.getApplyno()+"'";
			}			
		}else if(restype.trim().equals("IPSEG")){
			if(!StringUtil.isEmptyOrNull(order.getApplyno())){
				countSql="select count(*) from busorderhis ord left join busorderreshis res on ord.orderid=res.orderid left join rsipseg seg on res.resid=seg.id where res.restype='Ipseg' and ord.applyno='"+order.getApplyno()+"'";
			}			
		}else if(restype.trim().equals("IP")){
			if(!StringUtil.isEmptyOrNull(order.getApplyno())){
				countSql="select count(*) from busorderhis ord left join busorderreshis res on ord.orderid=res.orderid left join rsip ip on res.resid=ip.id left join rsipseg seg on ip.ipsegid=seg.id left join rsdevice dev on ip.deviceid=dev.id where res.restype='Ip' and ord.applyno='"+order.getApplyno()+"'";
			}			
		}else if(restype.trim().equals("PORT")){
			if(!StringUtil.isEmptyOrNull(order.getApplyno())){
				countSql="select count(*) from busorderhis ord left join busorderreshis res on ord.orderid=res.orderid left join rsnetdevport por on res.resid=por.id left join rsdevice dev on por.toserverid=dev.id where res.restype='Port' and ord.applyno='"+order.getApplyno()+"'";
			}			
		}
		
		try {
			count =dbm.executeQueryCount(countSql);
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally{
			dbm.close();
			dbm=null;
		}
		
		return count;
	}
	public int queryBusSerCount(OrderModel order,String restype){
		DBManager dbm=new DBManager();
		int count=0;
	
		StringBuilder countSql=new StringBuilder();
		if(restype.trim().equals("BUSORD")){
			countSql.append(" select count(*) from(");
			countSql.append(" select yb.id from jbpm_instance_yb yb left join flowcustomer flow on yb.flownumber=flow.busid left join busccustomer cust on flow.custid=cust.id left join buscnetuser net on cust.id=net.id left join tsuser tsu on net.manager=tsu.id where cust.type='03' and cust.id='"+order.getCustid()+"' ");
			countSql.append(" union all ");
			countSql.append(" select yb.id from jbpm_instance_yb yb left join flowcustomer flow on yb.flownumber=flow.busid left join busccustomer cust on flow.custid=cust.id left join buscgroupuser grp on cust.id=grp.id left join tsuser tsu on grp.manager=tsu.id where cust.type='01' and cust.id='"+order.getCustid()+"' ");
			countSql.append(" )temp");
		}else if(restype.trim().equals("SERORD")){
			countSql.append(" select count(*) from servicecustomer ser left join flowcustomer cust on ser.busid=cust.busid where cust.custid='"+order.getCustid()+"' ");
		}
		
		try {
			count =dbm.executeQueryCount(countSql.toString());
			
		} catch (Exception e) {
		
			e.printStackTrace();
		}finally{
			dbm.close();
			countSql=null;
			dbm=null;
		}
	
		return count;
	}
	
}
