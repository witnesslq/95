package com.dhcc.bussiness.sxydidc.rsdatacenter;



import java.io.IOException;
import java.io.PrintWriter;

import org.apache.struts2.ServletActionContext;

import com.dhcc.common.system.user.TreeDao;
import com.dhcc.common.util.AnyTypeAction;
import com.dhcc.modal.sxydidc.rsdatacenter.Rsdatacenter;
import com.dhcc.modal.system.PageModel;
import com.mockrunner.util.common.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



public class RsdatacenterAction extends  AnyTypeAction<Rsdatacenter,RsdatacenterModel>  {
	
	private RsdatacenterDao dao = new RsdatacenterDao();
	private TreeDao treedao = new TreeDao();
	private RsdatacenterModel rsdata;
	private JSONObject json;
	private String key;
	private String userid = ActionContext.getContext().getSession().get("userid").toString();
	private String ids;
	
	
	public String queryRsdatacenter(){
		PageModel pm = new PageModel();
		pm.setCurrentPage(super.getPage());
		pm.setPerPage(super.getPagesize());
		if(StringUtil.isEmptyOrNull(key)){
				pm = dao.queryRsdatacenter(pm);
		}else{
			pm = dao.quickSearchRsdatacenter(pm, key);
		}
		System.out.println(pm.getTotalRecord());
		System.out.println(pm.getList());
		super.setRecord(pm.getTotalRecord());
		super.setTotal(pm.getTotalPage());
		super.setListmodel(pm.getList());
		return SUCCESS;
	}
	
	
	
	
	public String deleteRsdatacenterByIds(){
		dao.deleteRsdatacenterByIds(ids);
			
		return SUCCESS;
	}
	
	
	public String saveRsdatacenter(){
		dao.saveRsdatacenter(rsdata);
		System.out.println(rsdata);
		rsdata = dao.queryRsdatacenterById(rsdata.getId());
		if(rsdata!=null){
			json = JSONObject.fromObject(rsdata);
		}else{
			json = JSONObject.fromObject("{\"result\":\"error\"}");
		}
		setJSON(json);
		return SUCCESS;
	}
	
	public String queryRsdatacenterById(){
		
		rsdata = dao.queryRsdatacenterById(ids);
		if(rsdata!=null){
			json = JSONObject.fromObject(rsdata);
		}else{
			json = JSONObject.fromObject("{\"result\":\"error\"}");
		}
		setJSON(json);
		return SUCCESS;
	}
public String queryRsdatacenterTree(){
		
		JSONArray  jsonarray = JSONArray.fromObject(treedao.queryDatacenter());
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		PrintWriter pw = null;
		try {
			pw = ServletActionContext.getResponse().getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		pw.print(jsonarray);
		pw.flush();  
		pw.close();
		return SUCCESS;
	}
	
	public String queryUserRsDC(){
		
		rsdata = dao.queryRsdatacenterByUserId(userid);
		if(rsdata!=null){
			json = JSONObject.fromObject(rsdata);
		}else{
			json = JSONObject.fromObject("{\"result\":\"error\"}");
		}
		setJSON(json);
		return SUCCESS;
	}

	
	
	public String updateRsdatacenterByIds(){
		dao.updateRsdatacenter(rsdata);
			
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

	





	public String getKey() {
		return key;
	}





	public void setKey(String key) {
		this.key = key;
	}
	
	
	@Override
	public java.util.List<RsdatacenterModel> getListmodel() {
		// TODO Auto-generated method stub
		return super.getListmodel();
	}

	@Override
	public Integer getPage() {
		// TODO Auto-generated method stub
		return super.getPage();
	}

	@Override
	public Integer getPagesize() {
		// TODO Auto-generated method stub
		return super.getPagesize();
	}

	@Override
	public Integer getRecord() {
		// TODO Auto-generated method stub
		return super.getRecord();
	}

	@Override
	public Integer getTotal() {
		// TODO Auto-generated method stub
		return super.getTotal();
	}


	public String getIds() {
		return ids;
	}


	public void setIds(String ids) {
		this.ids = ids;
	}


	public RsdatacenterModel getRsdata() {
		return rsdata;
	}


	public void setRsdata(RsdatacenterModel rsdata) {
		this.rsdata = rsdata;
	}
	
}
