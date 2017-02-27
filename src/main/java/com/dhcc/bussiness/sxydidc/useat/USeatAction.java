package com.dhcc.bussiness.sxydidc.useat;

import java.io.PrintWriter;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.dhcc.common.util.AnyTypeAction;
import com.dhcc.modal.sxydidc.ip.Rsip;
import com.dhcc.modal.system.PageModel;

/**
 * 客户信息
 */
public class USeatAction  extends AnyTypeAction<Rsip,USeatModel>{


	private static final long serialVersionUID = 1L;
	private USeatDao dao = new USeatDao();
	private String flownumber;
	
	private String id;
	private String ids;
	private String needRoleFilter="false";//是否需要根据角色过滤综合查询模块里的数据true：需要，false：不需要
	private USeatModel useat;
	private String sysLogTitle;//日志标题
	private String sysLogContent;//日志内容
	
	public String queryUSeat(){
		PageModel pm = new PageModel();
		pm.setCurrentPage(super.getPage());
		pm.setPerPage(super.getPagesize());
		if(useat==null){
			pm = dao.queryUSeat(pm,needRoleFilter);
		}else{
			pm = dao.queryUSeatByCondition(pm, useat,needRoleFilter);
		}
		
		super.setRecord(pm.getTotalRecord());
		super.setTotal(pm.getTotalPage());
		super.setListmodel(pm.getList());
		return SUCCESS;
	}
	
	
	public String queryUSeatInfo(){
		List<USeatModel> result=null;
		if(useat==null){
			result = dao.queryUSeat(needRoleFilter);
		}else{
			result = dao.queryUSeatByCondition(useat,needRoleFilter);
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
	
	public String deleteUSeatByIds(){
		 dao.deleteUSeatByIds(ids);
		return SUCCESS;
	}
	
	public String queryUSeatById(){
		useat=dao.queryUSeatById(id);
		JSONObject json;
		if(useat!=null){
			json = JSONObject.fromObject(useat);
		}else{
			json = JSONObject.fromObject("{\"result\":\"error\"}");
		}
		setJSON(json);
		return SUCCESS;
		
	}
	
	public String updateUSeat(){
		boolean result=dao.updateUSeat(useat);
		JSONObject json;
		if(result){
			useat=dao.queryUSeatById(useat.getId());
			json = JSONObject.fromObject(useat);
		}else{
			json = JSONObject.fromObject("{\"result\":\"error\"}");
		}
		setJSON(json);
		return SUCCESS;
	}
	
	
	public String saveUSeat(){
		id=dao.saveUSeat(useat);
		useat=dao.queryUSeatById(id);
		JSONObject json;
		if(useat!=null){
			json = JSONObject.fromObject(useat);
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

	public String getNeedRoleFilter() {
		return needRoleFilter;
	}
	public void setNeedRoleFilter(String needRoleFilter) {
		this.needRoleFilter = needRoleFilter;
	}
	public USeatModel getUseat() {
		return useat;
	}

	public void setUseat(USeatModel useat) {
		this.useat = useat;
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
	public java.util.List<USeatModel> getListmodel() {
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
