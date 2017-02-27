package com.dhcc.bussiness.sxydidc.rsroom;

import java.io.PrintWriter;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.dhcc.bussiness.sxydidc.rsroom.RsroomModel;
import com.dhcc.common.util.AnyTypeAction;
import com.dhcc.modal.sxydidc.rsroom.Rsroom;
import com.dhcc.modal.system.PageModel;
import com.mockrunner.util.common.StringUtil;

public class RsroomAction extends AnyTypeAction<Rsroom,RsroomModel> {
	private static final Logger logger = Logger.getLogger(RsroomAction.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RsroomDao dao = new RsroomDao();
	private RsroomModel rsroom;
	private JSONObject json;
	private String id;
	private String ids;
	private String key;
	private String needRoleFilter="false";//是否需要根据角色过滤综合查询模块里的数据true：需要，false：不需要
	
	public String queryRoomInfo(){
		List<RsroomModel>  result=null;
		if(StringUtil.isEmptyOrNull(key)){
			if(rsroom==null){
				 result=dao.queryRoom(needRoleFilter);
			}else{
				 result=dao.queryRoomByCondition(rsroom,needRoleFilter);
			}
			
		}else{
			result= dao.quickSearch(key,needRoleFilter);
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
	
	public String queryRoom(){
		PageModel pm = new PageModel();
		pm.setCurrentPage(super.getPage());
		pm.setPerPage(super.getPagesize());
		if(StringUtil.isEmptyOrNull(key)){
			if(rsroom==null){
				pm = dao.queryRoom(pm,needRoleFilter);
			}else{
				pm = dao.queryRoomByCondition(pm, rsroom,needRoleFilter);
			}
			
		}else{
			pm = dao.quickSearch(pm, key,needRoleFilter);
		}
		
		super.setRecord(pm.getTotalRecord());
		super.setTotal(pm.getTotalPage());
		super.setListmodel(pm.getList());
		return SUCCESS;
	}
	public String saveRoom(){
		dao.saveRoom(rsroom);
		rsroom = dao.queryRoomById(rsroom.getId());
		if(rsroom!=null){
			json = JSONObject.fromObject(rsroom);
		}else{
			json = JSONObject.fromObject("{\"result\":\"error\"}");
		}
		setJSON(json);
		return SUCCESS;
	}
	
	public String queryRoomProperty(){
		PageModel pm = new PageModel();
		List<RsroomModel> list=dao.queryRoomProperty(rsroom.getRoomname());
		pm.setList(list);
		if(list!=null&&(!list.isEmpty())){
			json = JSONObject.fromObject(pm);
		}else{
			json = JSONObject.fromObject("{\"result\":\"error\"}");
		}
		setJSON(json);
		return SUCCESS;
	}
	public String deleteRoomByIds(){
		String result=dao.deleteRoomByIds(ids);
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
	
	public String updateRoom(){
		boolean result=dao.updateRsroom(rsroom);
		if(result){
			rsroom=dao.queryRoomById(rsroom.getId());
			json = JSONObject.fromObject(rsroom);
		}else{
			json = JSONObject.fromObject("{\"result\":\"error\"}");
		}
		setJSON(json);
		return SUCCESS;
	}
	
	public String queryRoomById(){
		rsroom=dao.queryRoomById(id);
		if(rsroom!=null){
			json = JSONObject.fromObject(rsroom);
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
	
	public RsroomModel getRsroom() {
		return rsroom;
	}

	public void setRsroom(RsroomModel rsroom) {
		this.rsroom = rsroom;
	}

	public String getNeedRoleFilter() {
		return needRoleFilter;
	}

	public void setNeedRoleFilter(String needRoleFilter) {
		this.needRoleFilter = needRoleFilter;
	}

	@Override
	public java.util.List<RsroomModel> getListmodel() {
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
	
}
