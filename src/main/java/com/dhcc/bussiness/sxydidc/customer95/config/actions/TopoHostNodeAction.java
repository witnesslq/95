package com.dhcc.bussiness.sxydidc.customer95.config.actions;

import java.util.List;

import com.dhcc.bussiness.sxydidc.quality.dao.TopoHostNodeDao;
import com.dhcc.bussiness.sxydidc.quality.models.TopoHostNode;
import com.opensymphony.xwork2.ActionSupport;

public class TopoHostNodeAction extends ActionSupport {

	List<TopoHostNode> list;
	
	/**
	 * @return the list
	 */
	public List<TopoHostNode> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<TopoHostNode> list) {
		this.list = list;
	}

	/* 所有设备的IP
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		TopoHostNodeDao dao = new TopoHostNodeDao();
		this.list = dao.queryAllWithOnlyIp();
		
		return SUCCESS;
	}

}
