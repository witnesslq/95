package com.dhcc.bussiness.sxydidc.ipseg;

import java.io.PrintWriter;
import java.util.*;
import org.apache.struts2.ServletActionContext;
import net.sf.json.*;

import com.dhcc.bussiness.sxydidc.rsdatacenter.RsdatacenterDao;
import com.dhcc.bussiness.sxydidc.rsdatacenter.RsdatacenterModel;
import com.dhcc.common.util.AnyTypeAction;

import com.dhcc.modal.sxydidc.ip.Rsip;
import com.dhcc.modal.system.PageModel;
import com.mockrunner.util.common.StringUtil;
import com.opensymphony.xwork2.ActionContext;

/**
 * 客户信息
 */
public class IPSegAction  extends AnyTypeAction<Rsip,IPSegModel>{


	private static final long serialVersionUID = 1L;
	private IPSegDao dao = new IPSegDao();
	private RsdatacenterDao dcdao = new RsdatacenterDao();
	private JSONObject json;
	private String id;
	private String ids;
	private String key;
	private String needRoleFilter="false";//是否需要根据角色过滤综合查询模块里的数据true：需要，false：不需要
	private IPSegModel ipseg;
	private String splitArray;
	private String sysLogTitle;//日志标题
	private String sysLogContent;//日志内容
	private String flownumber;
	private String username;//ip段名称
	private int iTotalDisplayRecords;   
	private int iTotalRecords;  
	private int start;   
	private int length; 
	private String search;
	String userid = (String)ActionContext.getContext().getSession().get("userid");//当前用户id
	
	public String queryIPSeg(){
		PageModel pm = new PageModel();
		pm.setCurrentPage(super.getPage());
		pm.setPerPage(super.getPagesize());
		if(StringUtil.isEmptyOrNull(key)){
			if(ipseg==null){
				pm = dao.queryIPSeg(pm,needRoleFilter);
			}else{
				pm = dao.queryIPSegByCondition(pm, ipseg,needRoleFilter);
			}			
		}else{
			pm=dao.quickSearch(pm, key,needRoleFilter);
		}
		super.setRecord(pm.getTotalRecord());
		super.setTotal(pm.getTotalPage());
		super.setListmodel(pm.getList());
		return SUCCESS;
	}
	
	
	
	public String queryIPSegInfo(){
		PageModel pm = new PageModel();
		int currentpage=(start/length)+1;
		pm.setCurrentPage(currentpage);
		pm.setPerPage(length);
		if(StringUtil.isEmptyOrNull(search)){
			if(ipseg==null){
				pm = dao.queryIPSeg(pm,needRoleFilter);
			}else{
				pm = dao.queryIPSegByCondition(pm, ipseg,needRoleFilter);
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
	

	public String queryIPSegProperty(){
		PageModel pm = new PageModel();
		List<IPSegModel> list=dao.queryIPSegProperty(ipseg.getName());
		pm.setList(list);
		if(list!=null&&(!list.isEmpty())){
			json = JSONObject.fromObject(pm);
		}else{
			json = JSONObject.fromObject("{\"result\":\"error\"}");
		}
		setJSON(json);
		return SUCCESS;
	}
	
	public String deleteIPSegByIds(){
		String result=dao.deleteIPSegByIds(ids);
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
	
	public String mergeIPSegByIds(){
		 dao.mergeIPSegByIds(ids);
		return SUCCESS;
	}
	
	public String queryIPSegById(){
		ipseg=dao.queryIPSegById(id);
		if(ipseg!=null){
			json = JSONObject.fromObject(ipseg);
		}else{
			json = JSONObject.fromObject("{\"result\":\"error\"}");
		}
		setJSON(json);
		return SUCCESS;
		
	}
	
	public String updateIPSeg(){
		boolean result=dao.updateIPSeg(ipseg);
		if(result){
			ipseg=dao.queryIPSegById(ipseg.getId());
			json = JSONObject.fromObject(ipseg);
		}else{
			json = JSONObject.fromObject("{\"result\":\"error\"}");
		}
		setJSON(json);
		return SUCCESS;
	}
	
	
	public String saveIPSeg(){
		id=dao.saveIPSeg(ipseg);
		ipseg=dao.queryIPSegById(id);
		if(ipseg!=null){
			json = JSONObject.fromObject(ipseg);
		}else{
			json = JSONObject.fromObject("{\"result\":\"error\"}");
		}
		setJSON(json);
		return SUCCESS;
	}
	
	public String addBatchIPSeg(){
		JSONArray jsonList = JSONArray.fromObject(splitArray);
		JSONObject jsonObject=null;
		List<IPSegModel> insertList=new ArrayList<IPSegModel>();
		IPSegModel updateIPSegModel=new IPSegModel();
		for(int i=0;i<jsonList.size();i++){
			jsonObject=jsonList.getJSONObject(i);
			if(StringUtil.isEmptyOrNull(jsonObject.getString("id"))){
				IPSegModel temp=new IPSegModel();
				temp.setId("");
				temp.setStartip(jsonObject.getString("startip"));
				temp.setEndip(jsonObject.getString("endip"));
				temp.setCount(jsonObject.getInt("count"));
				temp.setName(jsonObject.getString("startip")+"~"+jsonObject.getString("endip").substring(jsonObject.getString("endip").lastIndexOf(".")+1));
				insertList.add(temp);
			}else{
				updateIPSegModel.setId(jsonObject.getString("id"));
				updateIPSegModel.setStartip(jsonObject.getString("startip"));
				updateIPSegModel.setEndip(jsonObject.getString("endip"));
				updateIPSegModel.setCount(jsonObject.getInt("count"));
				updateIPSegModel.setName(jsonObject.getString("startip")+"~"+jsonObject.getString("endip").substring(jsonObject.getString("endip").lastIndexOf(".")+1));
			}
		}
		dao.splitIPSeg(updateIPSegModel,insertList);
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

	public IPSegModel getIpseg() {
		return ipseg;
	}

	public void setIpseg(IPSegModel ipseg) {
		this.ipseg = ipseg;
	}

	public String getSplitArray() {
		return splitArray;
	}

	public void setSplitArray(String splitArray) {
		this.splitArray = splitArray;
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
	public java.util.List<IPSegModel> getListmodel() {
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



	public String getSearch() {
		return search;
	}



	public void setSearch(String search) {
		this.search = search;
	}
	
}
