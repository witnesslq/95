package com.dhcc.common.system.systemLog;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.dhcc.modal.system.PageModel;
import com.opensymphony.xwork2.ActionSupport;

public class SysLogAction extends ActionSupport {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;
	private List listmodel = new ArrayList();
	private int page; // 当前页码
	private int total; // 
	private int pagesize; // 当前页显示的数据行数
	private int record;// 当前数据条数
	private String starttime;//查询条件 开始时间
	private String endtime;  //查询条件 结束时间
	private String username;//查询条件 用户姓名
	private String ids;//删除时用到的字段
	private String result;//执行操作的结果
	private int iTotalDisplayRecords;   
	private int iTotalRecords;  
	private int start;   
	private int length; 
	private String extra_search;
	private List search;

	/**
	 * 查询系统日志列表
	 */
	public String sysLogQuery(){
		SysLogDao dao =  new SysLogDao();
		PageModel pm = new PageModel();
		pm.setCurrentPage(page);
		pm.setPerPage(pagesize);
		pm = dao.sysLogQueryList(pm, starttime, endtime,username);
		record = pm.getTotalRecord();
		total = pm.getTotalPage();
		listmodel = pm.getList();
		return SUCCESS;
	}

	
	
	/**
	 * 查询系统日志列表
	 */
	public String sysLogInfoQuery(){
		SysLogDao dao =  new SysLogDao();
		PageModel pm = new PageModel();
		int currentpage=(start/length)+1;
		pm.setCurrentPage(currentpage);
		pm.setPerPage(length);
		System.out.println(extra_search);
		if(!"null".equals(extra_search)&&!"".equals(extra_search)&&extra_search!=null){
			starttime=extra_search.substring(0, 19);
			endtime=extra_search.substring(extra_search.length()-19, extra_search.length());
		}
		
		pm = dao.sysLogQueryList(pm, starttime, endtime,username);
	    JSONObject json = new JSONObject();
	        json.put("aaData", pm.getList());
	        json.put("iTotalRecords", pm.getTotalRecord());
	        json.put("iTotalDisplayRecords", pm.getTotalRecord());
	        
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
	/**
	 * 删除根据id
	 */
	public String sysLogDel() {
		SysLogDao dao =  new SysLogDao();
		if (ids != null && !ids.equals("")) {
			result = dao.sysLogDel(ids.split(","));
		}
		return SUCCESS;
	}
	
	public List getListmodel() {
		return listmodel;
	}


	public void setListmodel(List listmodel) {
		this.listmodel = listmodel;
	}


	public int getPage() {
		return page;
	}


	public void setPage(int page) {
		this.page = page;
	}


	public int getTotal() {
		return total;
	}


	public void setTotal(int total) {
		this.total = total;
	}


	public int getPagesize() {
		return pagesize;
	}


	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}


	public int getRecord() {
		return record;
	}


	public void setRecord(int record) {
		this.record = record;
	}


	public String getStarttime() {
		return starttime;
	}


	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}


	public String getEndtime() {
		return endtime;
	}


	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}



	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}



	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}



	public int getiTotalRecords() {
		return iTotalRecords;
	}



	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}



	public int getStart() {
		return start;
	}



	public void setStart(int start) {
		this.start = start;
	}



	public int getLength() {
		return length;
	}



	public void setLength(int length) {
		this.length = length;
	}



	public String getExtra_search() {
		return extra_search;
	}



	public void setExtra_search(String extraSearch) {
		extra_search = extraSearch;
	}



	public List getSearch() {
		return search;
	}



	public void setSearch(List search) {
		this.search = search;
	}






}
