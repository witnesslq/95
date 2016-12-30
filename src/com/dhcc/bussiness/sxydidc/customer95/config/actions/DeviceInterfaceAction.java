package com.dhcc.bussiness.sxydidc.customer95.config.actions;

import com.dhcc.bussiness.sxydidc.customer95.config.dao.TopoInterfaceDao;
import com.dhcc.bussiness.sxydidc.customer95.models.Customer;
import com.dhcc.bussiness.sxydidc.quality.models.TopoHostNode;
import com.opensymphony.xwork2.ActionSupport;

public class DeviceInterfaceAction extends ActionSupport {

	private Customer customer;
	private TopoHostNode host;
	
	
	/**
	 * @return the host
	 */
	public TopoHostNode getHost() {
		return host;
	}

	/**
	 * @param host the host to set
	 */
	public void setHost(TopoHostNode host) {
		this.host = host;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/* 删除客户占用的所有端口
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String deleteAllInterfaceForThisCustomer() throws Exception {
		// TODO Auto-generated method stub
		if(customer == null) return ERROR;
		TopoInterfaceDao dao = new TopoInterfaceDao();
		dao.deleteAllBy(customer);
		return SUCCESS;
	}
	
	/* 删除单个设备上端口和客户的占用关系
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String deleteDeviceInterfaceForThisCustomer() throws Exception {
		// TODO Auto-generated method stub
		if(customer == null||host == null) return ERROR;
		TopoInterfaceDao dao = new TopoInterfaceDao();
		dao.deleteAllBy(customer, host);
		return SUCCESS;
	}

}
