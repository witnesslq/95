package com.dhcc.bussiness.sxydidc.netdevpack;

import java.util.ArrayList;
import java.util.List;

import com.dhcc.modal.sxydidc.device.Rsnetdevpack;
import com.dhcc.modal.sxydidc.device.Rsnetdevport;
import com.opensymphony.xwork2.ActionSupport;

public class DevPackAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List list = new ArrayList();//存放数据集合
	
	private Rsnetdevpack model = new Rsnetdevpack();
	
	private Rsnetdevport portmodel = new Rsnetdevport();
	
	private String deviceid;//当前查询设备id

	private String packid;//
	
	private String result;//处理结果字符串
	
	/**
	 * 网络设备插槽查询
	 */
	public String deviceSlotQueryListByDevId() {
		DevPackDao dao = new DevPackDao();
		list = dao.getDeviceslotList(deviceid);
		dao = null;
		return SUCCESS;
	}
	
	/**
	 * 网络设备板卡端口查询
	 */
	public String packPortQueryListByPackId() {
		DevPackDao dao = new DevPackDao();
		list = dao.getPackPortList(packid);
		dao = null;
		return SUCCESS;
	}
	
	/**
	 * 检查插槽编号是否存在
	 */
	public String querySlotIfExit() {
		DevPackDao dao = new DevPackDao();
		boolean b = dao.querySlotInfo(model);
		if(b){
			result = "YES";
		}else{
			result = "NO";
		}
		dao = null;
		return SUCCESS;
	}
	
	/**
	 * 检查插槽编号是否存在
	 */
	public String addSlotInfo() {
		DevPackDao dao = new DevPackDao();
		boolean b = dao.addSlotInfo(model);
		if(b){
			result = "success";
		}else{
			result = "error";
		}
		dao = null;
		return SUCCESS;
	}
	
	
	/**
	 * 删除插槽
	 */
	public String deleteSlotInfo() {
		DevPackDao dao = new DevPackDao();
		boolean b = dao.deleteSlotInfo(model);
		if(b){
			result = "success";
		}else{
			result = "error";
		}
		dao = null;
		return SUCCESS;
	}
	
	/**
	 * 删除板卡信息
	 */
	public String deletePackInfo() {
		DevPackDao dao = new DevPackDao();
		boolean b = dao.deletePackInfo(model.getId());
		if(b){
			result = "success";
		}else{
			result = "error";
		}
		dao = null;
		return SUCCESS;
	}
	
	/**
	 * 添加板卡信息
	 */
	public String addPackInfo() {
		DevPackDao dao = new DevPackDao();
		int portcount = model.getPortcount();//一共有多少个端口
		int percount = model.getPerrow();//每行多少个
		int rowcount = (int) Math.ceil((double)portcount/percount);
		model.setRowcount(rowcount);
		model.setStatus("");
		boolean b = dao.addPackInfo(model,portmodel);
		if(b){
			result = "success";
		}else{
			result = "error";
		}
		dao = null;
		return SUCCESS;
	}
	
	/**
	 * 检查板卡、插槽上端口信息是否被占用
	 */
	public String checkSlotPortInfo() {
		DevPackDao dao = new DevPackDao();
		result = dao.queryPortExit(deviceid, model.getSlotno()+"", model.getPackno()+"");
		dao = null;
		return SUCCESS;
	}
	
	/**
	 * 检查板卡、插槽上端口信息是否被占用
	 */
	public String checkPackPortInfo() {
		DevPackDao dao = new DevPackDao();
		result = dao.queryPortExit(model.getId());
		dao = null;
		return SUCCESS;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public String getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}

	public String getPackid() {
		return packid;
	}

	public void setPackid(String packid) {
		this.packid = packid;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Rsnetdevpack getModel() {
		return model;
	}

	public void setModel(Rsnetdevpack model) {
		this.model = model;
	}

	public Rsnetdevport getPortmodel() {
		return portmodel;
	}

	public void setPortmodel(Rsnetdevport portmodel) {
		this.portmodel = portmodel;
	}

}
