package com.dhcc.bussiness.sxydidc.customer95.config.actions;

import com.dhcc.bussiness.sxydidc.customer95.config.dao.TopoInterfaceDao;
import com.dhcc.bussiness.sxydidc.quality.models.TopoInterface;
import com.opensymphony.xwork2.ActionSupport;

public class TopoInterfaceAction extends ActionSupport {

	private TopoInterface topoInterface;
	
	/**
	 * @return the topoInterface
	 */
	public TopoInterface getTopoInterface() {
		return topoInterface;
	}

	/**
	 * @param topoInterface the topoInterface to set
	 */
	public void setTopoInterface(TopoInterface topoInterface) {
		this.topoInterface = topoInterface;
	}

	/* 解绑占用的端口
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		if(topoInterface == null) return ERROR;
		TopoInterfaceDao dao = new TopoInterfaceDao();
		dao.delete(topoInterface);
		return super.SUCCESS;
	}
	
	/* 占用端口
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String save() throws Exception {
		// TODO Auto-generated method stub
		if(topoInterface == null) return ERROR;
		TopoInterfaceDao dao = new TopoInterfaceDao();
		dao.save(topoInterface);
		return super.SUCCESS;
	}

}
