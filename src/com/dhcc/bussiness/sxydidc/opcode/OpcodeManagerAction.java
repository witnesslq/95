package com.dhcc.bussiness.sxydidc.opcode;

import java.io.PrintWriter;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.dhcc.common.util.AnyTypeAction;
import com.dhcc.common.util.CommUtil;
import com.dhcc.modal.sxydidc.Opcode;

public class OpcodeManagerAction extends AnyTypeAction<Opcode, OpcodeModel>{

	private static final long serialVersionUID = 1L;
	private OpcodeDao dao = new OpcodeDao();
	
	private Opcode model = new Opcode();
	private OpcodeModel opcodeModel = new OpcodeModel();
	private String ids;
	private String sysLogTitle;//日志标题
	private String sysLogContent;//日志内容
	private String querySql;
	private String dtype;
	
	public String codeAdd(){
		model.setId(CommUtil.getID());
		querySql = dao.QuerySql(model.getId());
		sysLogTitle = "添加编码信息";
		sysLogContent = "添加ID为"+model.getId()+"编码信息记录！";
		return super.Add(model, opcodeModel, querySql, sysLogTitle, sysLogContent);
	}
	
	public String codeDel(){
		sysLogTitle = "删除编码信息";
		return super.Del(ids, "opcode", sysLogTitle);
	}
	
	public String codeQueryById(){
		querySql = dao.QuerySql(model.getId());
		return super.Mess(opcodeModel, querySql);
	}
	
	public String codeUpdate(){
		querySql = dao.QuerySql(model.getId());
		sysLogTitle = "修改编码信息";
		sysLogContent = "修改ID为"+model.getId()+"编码信息记录！";
		return super.Updept(model, opcodeModel, querySql, sysLogTitle, sysLogContent);
	}

	
	/**
	 * 根据类型查询配置表的下落框
	 */
	public String queryConfigSelected() {
		List list = dao.getSelectHTML(dtype);
		JSONArray json = JSONArray.fromObject(list);
		try {
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			PrintWriter pw = ServletActionContext.getResponse().getWriter();
			pw.print(json);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;

	}
	
	public Opcode getModel() {
		return model;
	}

	public void setModel(Opcode model) {
		this.model = model;
	}

	public OpcodeModel getOpcodeModel() {
		return opcodeModel;
	}

	public void setOpcodeModel(OpcodeModel opcodeModel) {
		this.opcodeModel = opcodeModel;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getDtype() {
		return dtype;
	}

	public void setDtype(String dtype) {
		this.dtype = dtype;
	}
	
}
