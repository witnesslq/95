package com.dhcc.bussiness.sxydidc.rsroom;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.dhcc.bussiness.sxydidc.rack.RackDao;
import com.dhcc.bussiness.sxydidc.rack.RackModel;
import com.dhcc.bussiness.sxydidc.rack.RoomRackUModel;
import com.dhcc.bussiness.sxydidc.rsserver.RsserverModelRs;
import com.dhcc.common.util.CommUtil;
import com.dhcc.common.util.CreateNum;
import com.dhcc.modal.system.Tsdict;
import com.opensymphony.xwork2.ActionSupport;

public class RsroomCheckout extends ActionSupport {

     public String  checkoutRoom(){
    	 RsroomCheckoutDao  dao=new RsroomCheckoutDao();
    	 HttpServletRequest request = ServletActionContext.getRequest();  
	     String id= request.getParameter("id");
    	 List<RackModel> allinfo= dao.getRackInfo(id);
    	
    	 List<RoomStatuInfoModel> StatuInfo=dao.getStatuInfoInfo(id);
    	 RsroomModel roomInfo=dao.queryRoomInfo(id);
    	 request.setAttribute("allinfo", allinfo);
    	 request.setAttribute("StatuInfo", StatuInfo);
    	 request.setAttribute("roomid", id);
    	 request.setAttribute("roomtype", roomInfo.getRacktype());
    	 return SUCCESS; 
     }
		public  String queryUInfo(){
			 RsroomCheckoutDao  dao=new RsroomCheckoutDao();
	    	 HttpServletRequest request = ServletActionContext.getRequest();  
		     String id= request.getParameter("id");
		     List<RoomRackUModel>  UInfo=dao.getUInfo(id);
		     JSONArray json = JSONArray.fromObject(UInfo);
		     setJSON(json);
			 return SUCCESS; 
		}
		public String rsroomDevice(){
			 RsroomCheckoutDao  dao=new RsroomCheckoutDao();
	    	 HttpServletRequest request = ServletActionContext.getRequest();  
		     String id= request.getParameter("id");
		     String ip= request.getParameter("ip");
		     String port= request.getParameter("port");
	    	 RsserverModelRs deviceInfo= dao.getdeviceInfo(id);
	    	 request.setAttribute("deviceInfo", deviceInfo);
	    	 request.setAttribute("ip", ip);
	    	 request.setAttribute("port", port);
	    	 return SUCCESS; 
		}
		public String queryRackInfo(){
			 RsroomCheckoutDao  dao=new RsroomCheckoutDao();
	    	 HttpServletRequest request = ServletActionContext.getRequest();  
		     String id= request.getParameter("id");
		     String room_id= request.getParameter("room_id");
	    	 RackModel rackInfo= dao.getOneRackInfo(id);
	    	 request.setAttribute("rackInfo", rackInfo);
	    	 request.setAttribute("room_id", room_id);
			return SUCCESS;
		}
		public String updateRackInfo(){
			 RsroomCheckoutDao  dao=new RsroomCheckoutDao();
	    	 HttpServletRequest request = ServletActionContext.getRequest();  
		     String id= request.getParameter("id");
		     String rowno= request.getParameter("rowno");
		     String colno= request.getParameter("colno");
		     String xposition= request.getParameter("xposition");
		     String yposition= request.getParameter("yposition");
	         dao.updateRackInfo(id,rowno,colno,xposition,yposition);
			return SUCCESS;	
		}
		public String queryrowno(){
			 RsroomCheckoutDao  dao=new RsroomCheckoutDao();
	    	 HttpServletRequest request = ServletActionContext.getRequest(); 
	    	 String room_id= request.getParameter("room_id");
	    	 RsroomModel rownoInfo= dao.queryRoomInfo(room_id);
	    	 
	    	 JSONObject json = JSONObject.fromObject(rownoInfo);
	    	
	    	 try {
					ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
					PrintWriter pw = ServletActionContext.getResponse().getWriter();
					pw.print(json);
					pw.flush();
					pw.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			return SUCCESS;	
		}
		
		public String saveSomeRack(){
			 RsroomCheckoutDao  dao=new RsroomCheckoutDao();
			 HttpServletRequest request = ServletActionContext.getRequest();  
		     String room_id= request.getParameter("room_id");
		     int startRow= Integer.parseInt(request.getParameter("startRow"));
		     int lastRow= Integer.parseInt(request.getParameter("lastRow"));
		     int startCol= Integer.parseInt(request.getParameter("startCol"));
		     int lastCol= Integer.parseInt(request.getParameter("lastCol"));
		     String maxpower=request.getParameter("maxpower");
		     List<RackModel> allRackInfo= dao.queryAllRackInfo(room_id);
		     RsroomModel rownoInfo= dao.queryRoomInfo(room_id);
		     
		     int row=Integer.parseInt(String.valueOf(rownoInfo.getRowcount())) ;
		     int colo=Integer.parseInt(String.valueOf(rownoInfo.getColcount()));
		     String rsroomcode=rownoInfo.getRoomcode();
		     String racktype=rownoInfo.getRacktype();
		     ArrayList<String> allrowAndcolo=new ArrayList<String>();
		     for(int i=0;i<allRackInfo.size();i++){
		    	 allrowAndcolo.add(allRackInfo.get(i).getRowno()+"-"+allRackInfo.get(i).getColno()); 	
	    	 }
		     List<String> sqls = new ArrayList<String>();
		     StringBuilder rackSql=new StringBuilder("insert into rsrack(id,name,code,typeid,ucount,roomid,rowno,colno,xposition,yposition,power,status,dcid) value");
		     int a=0;
		     int c=0;
		     for(int i=startRow;i<=lastRow;i++){
		    	 for(int j=startCol;j<=lastCol;j++){
		    		 if(!allrowAndcolo.contains(i+"-"+j)){
		    			 char d=(char)(i+64);
		    			 String e;
		    			 if(j<10){
		    				 e="0"+j;
		    				 }
		    			 else{
		    				 e=j+"";
		    			 }	
		    			 String rackId=CommUtil.getID();
		    			 String fistcode=CreateNum.getNum("RACkCODE");
		    			 String rightcode=rsroomcode.substring(4,rsroomcode.length());		 
		    			 StringBuilder sb=new StringBuilder(fistcode);
		    			 sb.insert(4,rightcode);	
		    			 int xpostion=(j-1)*41+24;
		    			 int ypostion=(i-1)*72+24;
		    			 if(a==0){
		    				 rackSql.append("('"+rackId+"','"+d+e+"','"+sb.toString()+"','"+racktype+"','"+Integer.parseInt(racktype)+"','"+room_id+"','"+i+"','"+j+"','"+xpostion+"','"+ypostion+"','"+maxpower+"','01','"+rownoInfo.getDcid()+"')" );		    			
		    			     c=c+1;
		    			 }else{
		    				 rackSql.append(",('"+rackId+"','"+d+e+"','"+sb.toString()+"','"+racktype+"','"+Integer.parseInt(racktype)+"','"+room_id+"','"+i+"','"+j+"','"+xpostion+"','"+ypostion+"','"+maxpower+"','01','"+rownoInfo.getDcid()+"')" );		    				 
		    			 }
		    			 a=a+1;
		    			 StringBuilder uSeatSql=new StringBuilder("insert into rsuseat(id,no,rackid,status) value");  
		    			 for(int z=1;z<=Integer.parseInt(racktype);z++){
		    				 String uSeatId=CommUtil.getID();
		    				 if(z==1){
		    				     uSeatSql.append("('"+uSeatId+"','"+z+"','"+rackId+"','01')"); 
		    				 }else{
		    					 uSeatSql.append(",('"+uSeatId+"','"+z+"','"+rackId+"','01')");  
		    				 }
		    			 }
		    			 sqls.add(uSeatSql.toString());	 
		    		 }		
		    	 }
		     }
		     if(c!=0){
		     sqls.add(rackSql.toString());       
		     dao.saveSomeRack(sqls);
		     }
			return SUCCESS;
		}
		
		public String roomAndrackInfo(){
			 RsroomCheckoutDao  dao=new RsroomCheckoutDao();
			 HttpServletRequest request = ServletActionContext.getRequest();  
		     String room_id= request.getParameter("room_id");
		     List<RoomAndRackModel>  UInfo=dao.getRoomAndRackInfo(room_id);
		     JSONArray json = JSONArray.fromObject(UInfo);
		     setJSON(json);
			 return SUCCESS; 
		}
		public String savePillar(){
			 RsroomCheckoutDao  dao=new RsroomCheckoutDao();
			 HttpServletRequest request = ServletActionContext.getRequest();  
		     String room_id= request.getParameter("room_id");
		     int rowNumber= Integer.parseInt(request.getParameter("rowNumber"));
		     String roomcode= request.getParameter("roomcode");
		     
		     List<RackModel> allRackInfo= dao.queryAllRackInfo(room_id);
		     int pillarRow=1;
		     int rackXposition=0;
		     for(int j=0;j<allRackInfo.size();j++){
		    		if(allRackInfo.get(j).getRowno()==rowNumber){
		    			if(pillarRow<=allRackInfo.get(j).getColno()){
		    				pillarRow=allRackInfo.get(j).getColno()+1;
		    				rackXposition=allRackInfo.get(j).getXposition();
		    			}
		    		
		    		}
		     }
		     Tsdict racktype=dao.racktype();
		     String fistcode=CreateNum.getNum("RACkCODE");
			 String rightcode=roomcode.substring(4,roomcode.length());
			 StringBuilder sb=new StringBuilder(fistcode);
			 sb.insert(4,rightcode);	
		     RackModel rack=new RackModel();
		     rack.setName("柱子");
			 rack.setCode(sb.toString());
			 rack.setTypeid(racktype.getDkey());
			 rack.setRoomid(room_id);
			 rack.setRowno(rowNumber);
			 rack.setColno(pillarRow);
			 rack.setPower("0");
			 rack.setUcount(0);
			 rack.setXposition(rackXposition+41);
			 rack.setYposition((rowNumber-1)*72+24);
			 rack.setStatus("05");
			 RackDao rackdao=new RackDao();
			 rackdao.saveRack(rack);
			return SUCCESS; 
		}
		
		private void setJSON(JSONArray json){
			try {
				ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
				PrintWriter pw = ServletActionContext.getResponse().getWriter();
				pw.print(json);
				pw.flush();
				pw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		public String drag(){
			 RsroomCheckoutDao  dao=new RsroomCheckoutDao();
			 HttpServletRequest request = ServletActionContext.getRequest();  
			 JSONArray date=JSONArray.fromObject(request.getParameter("date"));
			 List<RackModel> info = (List) JSONArray.toCollection(date, RackModel.class);  
			 dao.updateDistion(info);
			 return SUCCESS; 
		}
		public String deleteOneRack(){
			 RsroomCheckoutDao  dao=new RsroomCheckoutDao();
			 HttpServletRequest request = ServletActionContext.getRequest();  
			 String id=request.getParameter("id");
			 dao.deleteOneRack(id);
			 return SUCCESS; 
		}
		public String deletesomeRack(){
			 HttpServletRequest request = ServletActionContext.getRequest();  
			 String ids=request.getParameter("ids");
			 RsroomCheckoutDao  dao=new RsroomCheckoutDao();
			 String id="";
			 for(int i=0;i<ids.split(",").length;i++){
				 if(i==0){
				 id="'"+ids.split(",")[i]+"'";
				 }else{
				 id="'"+ids.split(",")[i]+"',"+id; 
				 }
			 }
			 int count=dao.selectUSatus(id);
			 int PillarCount=dao.selectdRackSatus(id);
			 JSONObject json = null;
			if(count==0){
     		 dao.deleteSomeRack(ids);
     		json = JSONObject.fromObject("{\"PillarCount\":\""+PillarCount+"\",\"result\":\"success\"}");
     		 }else{
			 json = JSONObject.fromObject("{\"result\":\"error\"}");
			 }
		    	
	    	 try {
					ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
					PrintWriter pw = ServletActionContext.getResponse().getWriter();
					pw.print(json);
					pw.flush();
					pw.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			 return SUCCESS; 
		}
}
