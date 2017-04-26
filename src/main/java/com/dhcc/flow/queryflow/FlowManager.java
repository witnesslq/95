package com.dhcc.flow.queryflow;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.dhcc.common.system.corp.CorpDao;
import com.dhcc.common.util.AnyTypeAction;
import com.dhcc.excel.*;
import com.opensymphony.xwork2.ActionContext;
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
		ActionContext.getContext().getSession().put("flowList", list);
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
		ActionContext.getContext().getSession().put("flowList", list);
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
	
	public String report_flow(){
		
		String username="";
		String ipadd="";
		String port="";
		String startDate="";
		if(userid!=null && !userid.equals("")){
			username=userid;
		} else {
			username="所有客户";
		}
		
		if(ip!=null && !ip.equals("")) {
			ipadd=ip;
			port=ipaddress;
		} else {
			ipadd="所有ip";
			port="所有端口";
		}
		
		if (datetime!=null && !datetime.equals("")) {
			startDate=datetime;
		} else {
			Date currentdate=new Date();
		    SimpleDateFormat matter=new SimpleDateFormat("yyyy-MM-dd");
		    startDate=matter.format(currentdate);
		}
		
		
		List list = (List) ActionContext.getContext().getSession().get("flowList");
		Hashtable reporthash = new Hashtable();
		reporthash.put("flowlist", list);
		reporthash.put("starttime", startDate);
		reporthash.put("username", username);
		reporthash.put("ip", ipadd);
		reporthash.put("port", port);

		AbstractionReport1 report = new ExcelReport(new IpResourceReport(), reporthash);
		System.out.println("开始创建报表==============1111111======");
		report.createReport_queryflow(username,datetime);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("filename", report.getFileName());
		System.out.println("结束报表==============22222222======");
		return "success";
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
