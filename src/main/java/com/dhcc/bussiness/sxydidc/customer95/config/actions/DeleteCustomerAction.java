package com.dhcc.bussiness.sxydidc.customer95.config.actions;

import com.dhcc.bussiness.sxydidc.customer95.config.dao.CustomerDao;
import com.dhcc.bussiness.sxydidc.customer95.config.services.CustomerService;
import com.dhcc.bussiness.sxydidc.customer95.models.Customer;
import com.opensymphony.xwork2.ActionSupport;

public class DeleteCustomerAction extends ActionSupport {

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DeleteCustomerAction [customer=" + customer
				+ ", getActionErrors()=" + getActionErrors()
				+ ", getFieldErrors()=" + getFieldErrors() + "]";
	}



	private Customer customer;

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#validate()
	 */
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		if (this.customer == null || this.customer.getCustomerId() == null)
			this.addFieldError("customer", "必需有客户ID");
	}

	/**
	 * @param customer
	 *            the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	

	/* 删除客户
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String delete() throws Exception {
		// TODO Auto-generated method stub

		CustomerDao dao = new CustomerDao();
		dao.delete(customer);
		return super.SUCCESS;
	}
}
