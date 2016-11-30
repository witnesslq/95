package com.dhcc.flow.queryflow;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.dhcc.common.system.corp.CorpDao;
import com.dhcc.common.util.AnyTypeAction;
import com.opensymphony.xwork2.ActionSupport;

public class FlowManager extends ActionSupport{
	
	private String userid;
	private String ipaddress;   //端口信息
	private String ip;          //ip信息
	private String datetime;
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	/*
	 * 当天客户所有流量信息(首页)
	 */
	public  String  queryFlow(){
		FlowDao dao = new FlowDao();
		String startDate="";
		String datetype="";
		if (datetime!=null && !datetime.equals("")) {
			//天
			if(datetime.length()==10) {
				startDate=datetime;
				datetype="day";
			//年月
			}else if(datetime.length()==7) {
				startDate=datetime;
				datetype="month";
			//年
			} else {
				startDate=datetime;
				datetype="year";
			}
		} else {
			Date currentdate=new Date();
		    SimpleDateFormat matter=new SimpleDateFormat("yyyy-MM-dd");
		    startDate=matter.format(currentdate);
		    datetype="day";
		}
		
		List list = dao.getcustomerflow(startDate,datetype);
		JSONArray json = JSONArray.fromObject(list);
		System.out.println(json);
		PrintWriter pw = null;
		try {
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			pw = ServletActionContext.getResponse().getWriter();
			pw.print(json);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pw.flush();
			pw.close();
		}

		return SUCCESS;
	}
	
	/*
	 * 按客户条件查询       ip的流量信息
	 */
	public  String  queryUserFlow(){
		FlowDao dao = new FlowDao();
		
		
		String startDate="";
		String datetype="";
		if (datetime!=null && !datetime.equals("")) {
			//天
			if(datetime.length()==10) {
				startDate=datetime;
				datetype="day";
			//年月
			}else if(datetime.length()==7) {
				startDate=datetime;
				datetype="month";
			//年
			} else {
				startDate=datetime;
				datetype="year";
			}
		} else {
			Date currentdate=new Date();
		    SimpleDateFormat matter=new SimpleDateFormat("yyyy-MM-dd");
		    startDate=matter.format(currentdate);
		    datetype="day";
		}
		
		List list = dao.getcustomeranddateflow(userid,ipaddress,ip,startDate,datetype);
		JSONArray json = JSONArray.fromObject(list);
		//System.out.println(json);
		PrintWriter pw = null;
		try {
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			pw = ServletActionContext.getResponse().getWriter();
			pw.print(json);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pw.flush();
			pw.close();
		}

		return SUCCESS;
	}
	
	/*
	 * 按客户条件查询       ip的流量信息
	 */
	/*public  String  queryUserFlow(){
		FlowDao dao = new FlowDao();
		
		
		String startDate="";
		String datetype="";
		if (datetime!=null && !datetime.equals("")) {
			//天
			if(datetime.length()==10) {
				startDate=datetime;
				datetype="day";
			//年月
			}else if(datetime.length()==7) {
				startDate=datetime;
				datetype="month";
			//年
			} else {
				startDate=datetime;
				datetype="year";
			}
		} else {
			Date currentdate=new Date();
		    SimpleDateFormat matter=new SimpleDateFormat("yyyy-MM-dd");
		    startDate=matter.format(currentdate);
		    datetype="day";
		}
		
		List list = dao.getuseranddateflow(userid,ipaddress,startDate,datetype);
		JSONArray json = JSONArray.fromObject(list);
		//System.out.println(json);
		PrintWriter pw = null;
		try {
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			pw = ServletActionContext.getResponse().getWriter();
			pw.print(json);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pw.flush();
			pw.close();
		}

		return SUCCESS;
	}*/
	
	/**
	 * 用户名称
	 * @return
	 */
	public String getcustomerInfo() {

		FlowDao md = new FlowDao();
		List<UserInfo> list = new ArrayList<UserInfo>();
		list=md.getcustomerName();
		PrintWriter pw = null;
		try {
			JSONArray json = JSONArray.fromObject(list);//
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			pw = ServletActionContext.getResponse().getWriter();
			pw.print(json);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pw.flush();
			pw.close();
		}
		return SUCCESS;
	
	}
	
	/**
	 * 用户对应的端口列表
	 * @return
	 */
	public String getcustomer_port() {

		FlowDao md = new FlowDao();
		List<UserInfo> list = new ArrayList<UserInfo>();
		list=md.getcustomer_port(userid);
		PrintWriter pw = null;
		try {
			JSONArray json = JSONArray.fromObject(list);//
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			pw = ServletActionContext.getResponse().getWriter();
			pw.print(json);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pw.flush();
			pw.close();
		}
		return SUCCESS;
	
	}
	
		
	/**
	 * 用户名称和ip个数
	 * @return
	 */
	/*public String getUserInfo() {

		FlowDao md = new FlowDao();
		List<UserInfo> list = new ArrayList<UserInfo>();
		list=md.getuserinfo();
		PrintWriter pw = null;
		try {
			JSONArray json = JSONArray.fromObject(list);//
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			pw = ServletActionContext.getResponse().getWriter();
			pw.print(json);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pw.flush();
			pw.close();
		}
		return SUCCESS;
	
	}*/
	
	/**
	 * 用户对应的ip列表
	 * @return
	 */
	/*public String getUserInfo_ip() {

		FlowDao md = new FlowDao();
		List<UserInfo> list = new ArrayList<UserInfo>();
		list=md.getuser_ip(userid);
		PrintWriter pw = null;
		try {
			JSONArray json = JSONArray.fromObject(list);//
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			pw = ServletActionContext.getResponse().getWriter();
			pw.print(json);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pw.flush();
			pw.close();
		}
		return SUCCESS;
	
	}*/
	
	public static void main(String[] args) {
		FlowManager man = new FlowManager();
		//man.queryFlow();
	}
}
