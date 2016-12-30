package com.dhcc.bussiness.sxydidc.customer95.config.actions;

import com.dhcc.bussiness.sxydidc.customer95.config.dao.CustomerDao;
import com.dhcc.bussiness.sxydidc.customer95.config.models.CustomerSummary;
import com.dhcc.bussiness.sxydidc.customer95.models.Customer;
import com.opensymphony.xwork2.ActionSupport;

public class SingleCustomerSummaryAction extends ActionSupport {

	private Customer customer;
	private CustomerSummary customerSummary;
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
	/**
	 * @return the customerSummary
	 */
	public CustomerSummary getCustomerSummary() {
		return customerSummary;
	}
	/**
	 * @param customerSummary the customerSummary to set
	 */
	public void setCustomerSummary(CustomerSummary customerSummary) {
		this.customerSummary = customerSummary;
	}
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		if(customer == null) return ERROR;
		CustomerDao dao = new CustomerDao();
		this.customerSummary = dao.queryBy(customer);
		return super.SUCCESS;
	}
	
	
}
