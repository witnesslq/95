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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TopoInterfaceAction [topoInterface=" + topoInterface
				+ ", getActionErrors()=" + getActionErrors()
				+ ", getFieldErrors()=" + getFieldErrors() + "]";
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#validate()
	 */
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		if(topoInterface == null || topoInterface.getCustomerId() ==null){
			this.addFieldError("topoInterface", "没有客户ID");
		}
		
		if(topoInterface ==null || topoInterface.getIfIndex() == null){
			this.addFieldError("topoInterface", "没有客户ID");
		}
		
		if(topoInterface == null || topoInterface.getNodeId() == null){
			this.addFieldError("topoInterface", "没有IP地址");
		}
	}

	/* 解绑占用的端口
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String unbound() throws Exception {
		// TODO Auto-generated method stub
		TopoInterfaceDao dao = new TopoInterfaceDao();
		dao.update(topoInterface);
		return super.SUCCESS;
	}
	
	/* 占用端口
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String bound() throws Exception {
		// TODO Auto-generated method stub
		TopoInterfaceDao dao = new TopoInterfaceDao();
		
		try{
			dao.save(topoInterface);
		}catch(IllegalArgumentException e){
			this.addActionError(e.getMessage());
			throw e;
		}
		return super.SUCCESS;
	}

}
