package com.dhcc.bussiness.sxydidc.customer95.config.actions;

import com.dhcc.bussiness.sxydidc.customer95.config.dao.CustomerDao;
import com.dhcc.bussiness.sxydidc.customer95.models.Customer;
import com.opensymphony.xwork2.ActionSupport;

public class UpdateCustomerAction extends ActionSupport {

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UpdateCustomerAction [customer=" + customer
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

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/* 更新客户名称
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub

		CustomerDao dao = new CustomerDao();
		dao.saveOrUpdate(customer);
		return super.SUCCESS;
	}

	/* 
	 * customer客户必需有ID
	 * @see com.opensymphony.xwork2.ActionSupport#validate()
	 */
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		if(this.customer !=null){
			if(this.customer.getCustomerId() == null && this.customer.getCustomerName()==null)
				this.addFieldError("customer", "没有客户ID和客户名称");
			else if(this.customer.getCustomerId()==null)
				this.addFieldError("customer", "没有客户ID");
			else if( this.customer.getCustomerName()==null)
				this.addFieldError("customer", "没有客户名称");
			else if("".equals(this.customer.getCustomerName()))
				this.addFieldError("customer", "客户不合法，不能是空串");
	
		}else {
			this.addFieldError("customer", "没有客户ID和客户名称");
		}
		
		
	}

	
}
