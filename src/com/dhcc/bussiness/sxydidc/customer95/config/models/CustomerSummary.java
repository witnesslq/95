package com.dhcc.bussiness.sxydidc.customer95.config.models;

import com.dhcc.bussiness.sxydidc.customer95.models.Customer;

public class CustomerSummary {

	/**
	 * @param portCount
	 */
	public CustomerSummary(int portCount) {
		super();
		this.portCount = portCount;
	}
	private Customer customer;
	private String status;
	private int portCount;
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
	 * 
	 */
	public CustomerSummary() {
		super();
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the portCount
	 */
	public int getPortCount() {
		return portCount;
	}
	/**
	 * @param portCount the portCount to set
	 */
	public void setPortCount(int portCount) {
		this.portCount = portCount;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CustomerSummary [customer=" + customer + ", status=" + status
				+ ", portCount=" + portCount + "]";
	}
	
}
