package com.dhcc.bussiness.sxydidc.customer95.config.actions;

import java.util.List;

import com.dhcc.bussiness.sxydidc.customer95.config.dao.TopoInterfaceDao;
import com.dhcc.bussiness.sxydidc.customer95.config.models.DeviceSummary;
import com.dhcc.bussiness.sxydidc.customer95.models.Customer;
import com.opensymphony.xwork2.ActionSupport;

public class DeviceSummaryAction extends ActionSupport{

	private Customer customer;

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

	private List<DeviceSummary> list;
	
	/**
	 * @return the list
	 */
	public List<DeviceSummary> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<DeviceSummary> list) {
		this.list = list;
	}

	/* 用户端口占用简况 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		TopoInterfaceDao dao = new TopoInterfaceDao();
		
		this.list = dao.queryBy(this.customer);
		return SUCCESS;
	}

	
}
