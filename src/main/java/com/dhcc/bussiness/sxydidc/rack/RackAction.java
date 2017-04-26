package com.dhcc.bussiness.sxydidc.rack;

import java.io.PrintWriter;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.dhcc.common.util.AnyTypeAction;

import com.dhcc.modal.sxydidc.ip.Rsip;
import com.dhcc.modal.system.PageModel;
import com.mockrunner.util.common.StringUtil;

/**
 * 机架信息
 */
public class RackAction  extends AnyTypeAction<Rsip,RackModel>{


	private static final long serialVersionUID = 1L;
	private RackDao dao = new RackDao();
	
	private JSONObject json;
	private String id;
	private String ids;
	private String key;
	private String needRoleFilter="false";//是否需要根据角色过滤综合查询模块里的数据true：需要，false：不需要
	private String flownumber;
	private RackModel rack;
	private String sysLogTitle;//日志标题
	private String sysLogContent;//日志内容
	
	public String queryRack(){
		PageModel pm = new PageModel();
		pm.setCurrentPage(super.getPage());
		pm.setPerPage(super.getPagesize());
		if(StringUtil.isEmptyOrNull(key)){
			if(rack==null){
				pm = dao.queryRack(pm,needRoleFilter);
			}else{
				pm = dao.queryRackByCondition(pm, rack,needRoleFilter);
			}			
		}else{
			pm=dao.quickSearch(pm, key,needRoleFilter);
		}

		
		super.setRecord(pm.getTotalRecord());
		super.setTotal(pm.getTotalPage());
		super.setListmodel(pm.getList());
		return SUCCESS;
	}
	
	
	public String queryInfoRack(){
		List<RackModel> result=null;
		if(StringUtil.isEmptyOrNull(key)){
			if(rack==null){
				result=dao.queryRack(needRoleFilter);
			}else{
				result = dao.queryRackByCondition(rack,needRoleFilter);
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
	public String queryRackProperty(){
		PageModel pm = new PageModel();
		List<RackModel> list=dao.queryRackProperty(rack.getName());
		pm.setList(list);
		if(list!=null&&(!list.isEmpty())){
			json = JSONObject.fromObject(pm);
		}else{
			json = JSONObject.fromObject("{\"result\":\"error\"}");
		}
		setJSON(json);
		return SUCCESS;
	}
	
	public String deleteRackByIds(){
		String result=dao.deleteRackByIds(ids);
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
	
	public String queryRackById(){
		rack=dao.queryRackById(id);
		if(rack!=null){
			json = JSONObject.fromObject(rack);
		}else{
			json = JSONObject.fromObject("{\"result\":\"error\"}");
		}
		setJSON(json);
		return SUCCESS;
		
	}
	
	public String updateRack(){
		boolean result=dao.updateRack(rack);
		if(result){
			rack=dao.queryRackById(rack.getId());
			json = JSONObject.fromObject(rack);
		}else{
			json = JSONObject.fromObject("{\"result\":\"error\"}");
		}
		setJSON(json);
		return SUCCESS;
	}
	
	
	public String saveRack(){
		id=dao.saveRack(rack);
		rack=dao.queryRackById(id);
		if(rack!=null){
			json = JSONObject.fromObject(rack);
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
	public RackModel getRack() {
		return rack;
	}

	public void setRack(RackModel rack) {
		this.rack = rack;
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
	public java.util.List<RackModel> getListmodel() {
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
}
