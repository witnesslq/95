package com.dhcc.bussiness.sxydidc.quality.actions;

import java.text.ParseException;
import java.util.List;

import com.dhcc.bussiness.sxydidc.customer95.models.Customer;
import com.dhcc.bussiness.sxydidc.customer95.models.ProductIp;
import com.dhcc.bussiness.sxydidc.quality.dao.PortipsDao;
import com.dhcc.bussiness.sxydidc.quality.dao.TopoInterfaceDao;
import com.dhcc.bussiness.sxydidc.quality.models.Portips;
import com.dhcc.bussiness.sxydidc.quality.models.TopoInterface;
import com.dhcc.bussiness.sxydidc.quality.services.PortipsInterfaceService;
import com.opensymphony.xwork2.ActionSupport;

public class GetCustomerPortipsAction  extends ActionSupport{

	private String type;
	private long date;
	private Customer customer;
	
	

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#validate()
	 */
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		if(this.customer == null || this.customer.getCustomerId() == null){
			this.addFieldError("customer", "没有客户ID");
		}
	}

	private List<Portips> list;
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the date
	 */
	public long getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(long date) {
		this.date = date;
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


	
	/* 指定客户的按天，月，年的丢包率、错包率
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		PortipsInterfaceService service = new PortipsInterfaceService();
		this.list = service.fetchPortipsBy(customer, type, date);
		return SUCCESS;
	}

	/**
	 * @return the list
	 */
	public List<Portips> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<Portips> list) {
		this.list = list;
	}

}
