package com.dhcc.bussiness.sxydidc.ip;

import java.io.PrintWriter;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.dhcc.bussiness.sxydidc.rsdatacenter.RsdatacenterDao;
import com.dhcc.bussiness.sxydidc.rsdatacenter.RsdatacenterModel;
import com.dhcc.common.util.AnyTypeAction;

import com.dhcc.modal.sxydidc.ip.Rsip;
import com.dhcc.modal.system.PageModel;
import com.mockrunner.util.common.StringUtil;
import com.opensymphony.xwork2.ActionContext;

/**
 * IP地址信息
 */
public class IPAction  extends AnyTypeAction<Rsip,IPModel>{


	private static final long serialVersionUID = 1L;
	private IPDao dao = new IPDao();
	private RsdatacenterDao dcdao = new RsdatacenterDao();
	private JSONObject json;
	private String flownumber;
	private String id;
	private String ids;
	private String key;
	private String needRoleFilter="false";//是否需要根据角色过滤综合查询模块里的数据true：需要，false：不需要
	private String username;//资源调度模糊查询
	private IPModel ip;
	private String sysLogTitle;//日志标题
	private String sysLogContent;//日志内容
	
	private int iTotalDisplayRecords;   
	private int iTotalRecords;  
	private int start;   
	private int length; 
	private String extra_search;
	private String search;
	private String ipsegid;
	private String deviceid;
	private String customerid;
	private String statusName;
	
	String userid = (String)ActionContext.getContext().getSession().get("userid");//当前用户id
	

	public String queryIP(){
		PageModel pm = new PageModel();
		pm.setCurrentPage(super.getPage());
		pm.setPerPage(super.getPagesize());
		if(StringUtil.isEmptyOrNull(key)){
			if(ip==null){
				pm = dao.queryIP(pm,needRoleFilter);
			}else{
				pm = dao.queryIPByCondition(pm, ip,ip.getNeedDevice(),needRoleFilter);
			}			
		}else{
			pm=dao.quickSearch(pm, key,needRoleFilter);
		}
		super.setRecord(pm.getTotalRecord());
		super.setTotal(pm.getTotalPage());
		super.setListmodel(pm.getList());
		return SUCCESS;
	}
	
	
	public String queryIpAllInfo(){
		PageModel pm = new PageModel();
		int currentpage=(start/length)+1;
		pm.setCurrentPage(currentpage);
		pm.setPerPage(length);
		IPModel ipbean=new IPModel();
		if(!"".equals(ipsegid)){
			ipbean.setIpsegid(ipsegid);
		}
		if(!"".equals(deviceid)){
			ipbean.setDeviceid(deviceid);
		}
		if(!"".equals(customerid)){
			ipbean.setCustomerid(customerid);
		}
		if(!"".equals(statusName)){
			ipbean.setStatus(statusName);
		}		
		if(StringUtil.isEmptyOrNull(search)){
			if("".equals(ipsegid)&&"".equals(deviceid)&&"".equals(customerid)&&"".equals(statusName)){
				pm = dao.queryIP(pm,needRoleFilter);
			}else{
				pm = dao.queryIPByCondition(pm, ipbean,true,needRoleFilter);
			}			
		}else{
			pm=dao.quickSearch(pm, search,needRoleFilter);
		}
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
	public String queryIPInfo(){
		List<IPModel> result=null;
		if(StringUtil.isEmptyOrNull(key)){
			if(ip==null){
				result = dao.queryIP(needRoleFilter);
			}else{
				result = dao.queryIPByCondition(ip,ip.getNeedDevice(),needRoleFilter);
			}			
		}else{
			result=dao.quickSearch(key,needRoleFilter);
		}
		PrintWriter pw = null;
		try {
			JSONArray json = JSONArray.fromObject(result);
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
	
	public String deleteIPByIds(){
		String result=dao.deleteIPByIds(ids);
			if(result.trim().equals("01")){
				json = JSONObject.fromObject("{\"result\":\"01\"}");
			}else if(result.trim().equals("02")){
				json = JSONObject.fromObject("{\"result\":\"02\"}");
			}else if(result.trim().equals("03")){
				json = JSONObject.fromObject("{\"result\":\"error\"}");
			}
			setJSON(json);		 
		return SUCCESS;
	}
	
	public String queryIPById(){
		ip=dao.queryIPById(id);
		if(ip!=null){
			json = JSONObject.fromObject(ip);
		}else{
			json = JSONObject.fromObject("{\"result\":\"error\"}");
		}
		setJSON(json);
		return SUCCESS;
		
	}
	
	public String updateIP(){
		boolean result=dao.updateIP(ip);
		if(result){
			ip=dao.queryIPById(ip.getId());
			json = JSONObject.fromObject(ip);
		}else{
			json = JSONObject.fromObject("{\"result\":\"error\"}");
		}
		setJSON(json);
		return SUCCESS;
	}
	
	
	public String saveIP(){
		dao.saveIP(ip);
		ip=dao.queryIPById(id);
		if(ip!=null){
			json = JSONObject.fromObject(ip);
		}else{
			json = JSONObject.fromObject("{\"result\":\"error\"}");
		}
		setJSON(json);
		return SUCCESS;
	}
	
	public String queryIPProperty(){
		PageModel pm = new PageModel();
		List<IPModel> list=dao.queryIPProperty(ip.getIpadd());
		pm.setList(list);
		if(list!=null&&(!list.isEmpty())){
			json = JSONObject.fromObject(pm);
		}else{
			json = JSONObject.fromObject("{\"result\":\"error\"}");
		}
		setJSON(json);
		return SUCCESS;
	}
	private void setJSON(JSONObject json){
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
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getNeedRoleFilter() {
		return needRoleFilter;
	}

	public void setNeedRoleFilter(String needRoleFilter) {
		this.needRoleFilter = needRoleFilter;
	}

	public IPModel getIp() {
		return ip;
	}

	public void setIp(IPModel ip) {
		this.ip = ip;
	}

	public String getSysLogTitle() {
		return sysLogTitle;
	}

	public void setSysLogTitle(String sysLogTitle) {
		this.sysLogTitle = sysLogTitle;
	}

	public String getSysLogContent() {
		return sysLogContent;
	}

	public void setSysLogContent(String sysLogContent) {
		this.sysLogContent = sysLogContent;
	}

	@Override
	public java.util.List<IPModel> getListmodel() {
		return super.getListmodel();
	}

	@Override
	public Integer getPage() {
		return super.getPage();
	}

	@Override
	public Integer getPagesize() {
		return super.getPagesize();
	}

	@Override
	public Integer getRecord() {
		return super.getRecord();
	}

	@Override
	public Integer getTotal() {
		return super.getTotal();
	}

	public String getFlownumber() {
		return flownumber;
	}

	public void setFlownumber(String flownumber) {
		this.flownumber = flownumber;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}


	public String getIpsegid() {
		return ipsegid;
	}


	public void setIpsegid(String ipsegid) {
		this.ipsegid = ipsegid;
	}


	public String getDeviceid() {
		return deviceid;
	}


	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}


	public String getCustomerid() {
		return customerid;
	}


	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}


	public String getStatusName() {
		return statusName;
	}


	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
}
