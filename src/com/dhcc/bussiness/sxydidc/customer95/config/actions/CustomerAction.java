package com.dhcc.bussiness.sxydidc.customer95.config.actions;

import com.dhcc.bussiness.sxydidc.customer95.config.dao.CustomerDao;
import com.dhcc.bussiness.sxydidc.customer95.config.services.CustomerService;
import com.dhcc.bussiness.sxydidc.customer95.models.Customer;
import com.opensymphony.xwork2.ActionSupport;

public class CustomerAction extends ActionSupport {

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

	/* 
	 * 新建用户或者修改用户名称
	 */
	public String saveOrUpdate() throws Exception {
		// TODO Auto-generated method stub
		if(this.customer == null) return super.ERROR;
		
		CustomerService service =new CustomerService();
		service.saveOrUpdate(customer);
		return super.SUCCESS;
	}

}
