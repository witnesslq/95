package com.dhcc.common.system.components;

import java.io.PrintWriter;

import org.apache.struts2.ServletActionContext;

import com.dhcc.common.util.CommUtil;
import com.dhcc.modal.system.Tscomponent;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONObject;


public class ComponentManagerAction extends ActionSupport implements ModelDriven<Tscomponent> {
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;
	private Tscomponent model = new Tscomponent();
	private String ids;// 前台传过来的id的字符串，用‘，’来分割
	private ComponentDao dao = new ComponentDao();

	/**
	 * 删除根据id
	 * @return
	 */
	public String componentDelete() {
		if (ids != null && !ids.equals("")) {
			dao.componentDel(ids.split(","));
		}
		return SUCCESS;
	}

	/**
	 * 添加信息方法
	 * @return
	 */
	public String componentAdd() {
		model.setId(CommUtil.getID());
		//model.setTopcorpid(new TreeDao().queryTopCorpId(model.getPid()));
//		if("".equals(model.getPid())){
//			model.setPid(topcorpid);
//		}
		JSONObject json;
		if(dao.componentAdd(model)){
			json = JSONObject.fromObject(model);
		}else{
			json = JSONObject.fromObject("{\"result\":\"error\"}");
		}
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
	 * 获取信息方法
	 * 
	 * @return
	 */
	public String componentgetById() {
		PrintWriter pw = null;
		try {
			JSONObject json = JSONObject.fromObject(dao.componentQueryById(model.getId()));
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
	 * 更新信息方法
	 * 
	 * @return
	 */
	public String componentUpdate() {
		

		JSONObject json;
		if(dao.componentModify(model)){
			json = JSONObject.fromObject(model);
		}else{
			json = JSONObject.fromObject("{\"result\":\"error\"}");
		}
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
	
	


	public Tscomponent getModel() {
		return model;
	}

	public void setModel(Tscomponent model) {
		this.model = model;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
	
}
