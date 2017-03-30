package com.dhcc.bussiness.sxydidc.quality.actions;

import java.util.List;

import com.dhcc.bussiness.sxydidc.customer95.models.Customer;
import com.dhcc.bussiness.sxydidc.quality.dao.TopoInterfaceDao;
import com.dhcc.bussiness.sxydidc.quality.models.TopoInterface;
import com.dhcc.bussiness.sxydidc.quality.services.DateRange;
import com.opensymphony.xwork2.ActionSupport;

public class TopoInterfaceAction extends ActionSupport {

	private List<TopoInterface> list;
	private long startDate;
	private long endDate;
	
	/**
	 * @return the startDate
	 */
	public long getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(long startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the endDate
	 */
	public long getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(long endDate) {
		this.endDate = endDate;
	}
	private long date;
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
	private Customer customer;
	/**
	 * @return the list
	 */
	public List<TopoInterface> getList() {
		return list;
	}
	/**
	 * @param list the list to set
	 */
	public void setList(List<TopoInterface> list) {
		this.list = list;
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
	/* 指定客户的采集端口表
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		TopoInterfaceDao dao = new TopoInterfaceDao();
		this.list = dao.queryGatherInterfaceListFor(this.customer, new DateRange(this.startDate,this.endDate));
		return SUCCESS;
	}
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#validate()
	 */
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		if(this.customer == null || this.customer.getCustomerId() ==null){
			this.addFieldError("customer","没有客户ID");
		}
	}

}
