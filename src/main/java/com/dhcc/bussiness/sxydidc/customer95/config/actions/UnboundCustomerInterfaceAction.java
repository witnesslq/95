package com.dhcc.bussiness.sxydidc.customer95.config.actions;

import com.dhcc.bussiness.sxydidc.customer95.config.dao.TopoInterfaceDao;
import com.dhcc.bussiness.sxydidc.customer95.models.Customer;
import com.opensymphony.xwork2.ActionSupport;

public class UnboundCustomerInterfaceAction extends ActionSupport {
	private Customer customer;

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#validate()
	 */
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		if(this.customer== null|| this.customer.getCustomerId() == null){
			this.addFieldError("customer", "没有客户ID");
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UnboundCustomerInterfaceActionData [customer=" + customer
				+ ", getActionErrors()=" + getActionErrors()
				+ ", getFieldErrors()=" + getFieldErrors() + "]";
	}

	/* 删除客户占用的所有端口
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		TopoInterfaceDao dao = new TopoInterfaceDao();
		dao.unboundAllBy(customer);
		return SUCCESS;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}