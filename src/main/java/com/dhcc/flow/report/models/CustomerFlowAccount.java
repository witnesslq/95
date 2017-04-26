package com.dhcc.flow.report.models;

import java.text.ParseException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import com.dhcc.bussiness.sxydidc.customer95.models.Customer;
import com.dhcc.flow.report.services.CommonDateFormat;

public class CustomerFlowAccount implements Exportable{

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CustomerFlowAccount [customer=" + customer + ", flowAccount="
				+ flowAccount + "]";
	}

	private Customer customer;
	private FlowAccount flowAccount;
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
	 * @return the flowAccount
	 */
	public FlowAccount getFlowAccount() {
		return flowAccount;
	}
	/**
	 * @param flowAccount the flowAccount to set
	 */
	public void setFlowAccount(FlowAccount flowAccount) {
		this.flowAccount = flowAccount;
	}
	/**
	 * @param customer
	 * @param flowAccount
	 */
	public CustomerFlowAccount(Customer customer, FlowAccount flowAccount) {
		super();
		this.customer = customer;
		this.flowAccount = flowAccount;
	}
	
	public CustomerFlowAccount(String customerId, String customerName,String customerIdToFlowAccount, String datetime, String totalcount,
			String flowcount, String utilhdx, String cabinet) {
		super();
		this.customer = new Customer(customerId,customerName);
		this.flowAccount = new FlowAccount(new FlowAccountId( customerIdToFlowAccount,  datetime,  totalcount, flowcount,  utilhdx,  cabinet));
	}

	public Map<String, Object> convert() {
		// TODO Auto-generated method stub
		Map<String,Object> params = new HashMap<String, Object>();
		
		Calendar calendar = Calendar.getInstance();
		
		String monthDate = this.flowAccount.getId().getDatetime();
		CommonDateFormat sdf = CommonDateFormat.FLOW_MONTH_DATE_FORMAT;
		try {
			calendar.setTime(CommonDateFormat.FLOW_MONTH_GENERAL_DATE_FORMAT.parse(monthDate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String startTime = sdf.format(calendar.getTime())+"1日  00：00",
				endTime = sdf.format(calendar.getTime())+calendar.getActualMaximum(Calendar.DAY_OF_MONTH)+"日 23：55";
				
	      params.put("caption", "山西移动"+sdf.format(calendar.getTime())+this.customer.getCustomerName()+"九五峰值流量计费");
	      params.put("businessName", this.customer.getCustomerName());
	      params.put("startTime", startTime);
	      params.put("endTime", endTime);
	      params.put("pointCount", this.flowAccount.getId().getTotalcount());
	      params.put("95PointCount", this.flowAccount.getId().getFlowcount());
	      params.put("95Flow", this.flowAccount.getId().getUtilhdx());
	      params.put("cabinetCount", this.flowAccount.getId().getCabinet());
		return params;
	}
}
