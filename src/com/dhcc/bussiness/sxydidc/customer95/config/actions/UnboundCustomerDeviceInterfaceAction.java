package com.dhcc.bussiness.sxydidc.customer95.config.actions;

import com.dhcc.bussiness.sxydidc.customer95.config.dao.TopoInterfaceDao;
import com.dhcc.bussiness.sxydidc.customer95.models.Customer;
import com.dhcc.bussiness.sxydidc.quality.models.TopoHostNode;
import com.opensymphony.xwork2.ActionSupport;

public class UnboundCustomerDeviceInterfaceAction extends ActionSupport {

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

	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#validate()
	 */
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		if(this.customer==null||this.customer.getCustomerId() == null){
			this.addFieldError("customer", "没有客户ID");
		}
		if(this.host==null || this.host.getIpAddress() ==null){
			this.addFieldError("customer", "没有设备IP");
		}else{
			
		}
		
	}

	/* 删除单个设备上端口和客户的占用关系
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		TopoInterfaceDao dao = new TopoInterfaceDao();
		dao.unboundAllBy(customer, host);
		return SUCCESS;
	}

}
