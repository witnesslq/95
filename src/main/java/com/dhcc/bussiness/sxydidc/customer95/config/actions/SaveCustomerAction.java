package com.dhcc.bussiness.sxydidc.customer95.config.actions;

import com.dhcc.bussiness.sxydidc.customer95.config.dao.CustomerDao;
import com.dhcc.bussiness.sxydidc.customer95.config.services.CustomerService;
import com.dhcc.bussiness.sxydidc.customer95.models.Customer;
import com.dhcc.spring.config.ApplicationContextConfig;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.ValidationErrorAware;

public class SaveCustomerAction extends ActionSupport implements ValidationErrorAware {

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
		if (this.customer != null ){
			if(this.customer.getCustomerName()==null)
				this.addFieldError("customer", "没有客户名称");
			else if("".equals(this.customer.getCustomerName()))
				this.addFieldError("customer", "客户名称不合法，不能是空字符串");
		}else{
			this.addFieldError("customer", "没有客户名称");
		}
		
		if(!this.hasFieldErrors()){
			CustomerService service = ApplicationContextConfig.getContext().getBean(CustomerService.class);
			if(service.has(customer)){
				this.addActionError("客户"+customer.getCustomerName()+"已存在");
			}
		}
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SaveCustomerAction [customer=" + customer
				+ ", getActionErrors()=" + getActionErrors()
				+ ", getFieldErrors()=" + getFieldErrors() + "]";
	}

	/**
	 * @param customer
	 *            the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/*
	 * 新建用户
	 */
	public String save() throws Exception {
		// TODO Auto-generated method stub
	
		CustomerService service = ApplicationContextConfig.getContext().getBean(CustomerService.class);
		try{
			service.saveOrUpdate(customer);
		}catch(IllegalArgumentException e){
			this.addActionError(e.getMessage());
			return ERROR;
		}
		return super.SUCCESS;
	}

	/*
	 * action-level的error 返回ERROR的result
	 * @see com.opensymphony.xwork2.interceptor.ValidationErrorAware#actionErrorOccurred(java.lang.String)
	 */
	@Override
	public String actionErrorOccurred(String currentResultName) {
		// TODO Auto-generated method stub
		if(this.hasFieldErrors())
			return currentResultName;
		if(this.hasActionErrors())
			return ERROR;
		return currentResultName;
	}
}
