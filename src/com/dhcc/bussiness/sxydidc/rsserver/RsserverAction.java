package com.dhcc.bussiness.sxydidc.rsserver;

import java.io.PrintWriter;
import org.apache.struts2.ServletActionContext;
import net.sf.json.JSONObject;

import com.dhcc.bussiness.sxydidc.rsdatacenter.RsdatacenterDao;
import com.dhcc.bussiness.sxydidc.rsdatacenter.RsdatacenterModel;
import com.dhcc.common.util.AnyTypeAction;
import com.dhcc.modal.sxydidc.rsserver.Rsdevice;
import com.dhcc.modal.system.PageModel;
import com.mockrunner.util.common.StringUtil;
import com.opensymphony.xwork2.ActionContext;
public class RsserverAction extends AnyTypeAction<Rsdevice,RsserverModel> {
	
	private static final long serialVersionUID = 1L;
	private RsserverDao dao = new RsserverDao();
	
	private RsdatacenterDao dcdao = new RsdatacenterDao();
	private RsserverModel rsserver;
	private JSONObject json;
	private String flownumber;
	private String id;
	private String ids;
	private String key;
	private String needRoleFilter="false";//是否需要根据角色过滤综合查询模块里的数据true：需要，false：不需要
	private String type;
	private String modelname;
	String userid = (String)ActionContext.getContext().getSession().get("userid");//当前用户id
	
	
	public String queryServer(){
		PageModel pm = new PageModel();
		pm.setCurrentPage(super.getPage());
		pm.setPerPage(super.getPagesize());
		if(StringUtil.isEmptyOrNull(key)){
			if(rsserver==null){
				pm = dao.queryServer(pm,needRoleFilter);
			}else{
				pm = dao.queryServerByCondition(pm, rsserver,needRoleFilter);
			}
		}else{
			pm = dao.quickSearch(pm, key,needRoleFilter);
		}
		
		super.setRecord(pm.getTotalRecord());
		super.setTotal(pm.getTotalPage());
		super.setListmodel(pm.getList());
		return SUCCESS;
	}
	
	
	
	
	public String saveServer(){
		id=dao.saveServer(rsserver);
		rsserver = dao.queryServerById(id);
		if(rsserver!=null){
			json = JSONObject.fromObject(rsserver);
		}else{
			json = JSONObject.fromObject("{\"result\":\"error\"}");
		}
		setJSON(json);
		return SUCCESS;
	}
	
	public String updateServer(){
		boolean result=dao.updateServer(rsserver);
		if(result){
			rsserver=dao.queryServerById(rsserver.getId());
			json = JSONObject.fromObject(rsserver);
		}else{
			json = JSONObject.fromObject("{\"result\":\"error\"}");
		}
		setJSON(json);
		return SUCCESS;
	}
	
	
	public String queryServerById(){
		rsserver=dao.queryServerById(id);
		if(rsserver!=null){
			json = JSONObject.fromObject(rsserver);
		}else{
			json = JSONObject.fromObject("{\"result\":\"error\"}");
		}
		setJSON(json);
		return SUCCESS;
		
	}
	
	public String deleteServerByIds(){
		String result=dao.deleteServerByIds(ids);
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
	
	@Override
	public java.util.List<RsserverModel> getListmodel() {
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
	public RsserverModel getRsserver() {
		return rsserver;
	}
	public void setRsserver(RsserverModel rsserver) {
		this.rsserver = rsserver;
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

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getFlownumber() {
		return flownumber;
	}

	public void setFlownumber(String flownumber) {
		this.flownumber = flownumber;
	}

	public String getModelname() {
		return modelname;
	}

	public void setModelname(String modelname) {
		this.modelname = modelname;
	}
	
}
