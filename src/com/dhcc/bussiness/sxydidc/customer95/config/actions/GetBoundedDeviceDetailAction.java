package com.dhcc.bussiness.sxydidc.customer95.config.actions;

import com.dhcc.bussiness.sxydidc.alarm.actions.PaginationData;
import com.dhcc.bussiness.sxydidc.customer95.config.models.DeviceDetail;
import com.dhcc.bussiness.sxydidc.customer95.config.models.InterfaceDetail;
import com.dhcc.bussiness.sxydidc.customer95.config.services.BoundedDeviceInterfaceService;
import com.dhcc.bussiness.sxydidc.customer95.config.services.DeviceInterfaceServiceSupport;
import com.dhcc.bussiness.sxydidc.customer95.models.Customer;

public class GetBoundedDeviceDetailAction extends PaginationData<DeviceDetail> {

	private Customer customer;
	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#validate()
	 */
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		if(this.customer == null||this.customer.getCustomerId() ==null){
			this.addFieldError("customer","没有客户ID");
		}
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
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {

		// TODO Auto-generated method stub
		DeviceInterfaceServiceSupport service = new BoundedDeviceInterfaceService();
		service.paging(this, this.customer);
		return super.SUCCESS;
	}

}
