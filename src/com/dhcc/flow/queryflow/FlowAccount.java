package com.dhcc.flow.queryflow;

public class FlowAccount {
	private String customer_id; //客户id
	
	private String datetime;   //日期
	
	private String totalcount; //采集点数
	
	private String flowcount;  //95%点数 
	
	private float utilhdx;     //流量
	
	private String cabinet;    //机柜

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getTotalcount() {
		return totalcount;
	}

	public void setTotalcount(String totalcount) {
		this.totalcount = totalcount;
	}

	public String getFlowcount() {
		return flowcount;
	}

	public void setFlowcount(String flowcount) {
		this.flowcount = flowcount;
	}

	

	public float getUtilhdx() {
		return utilhdx;
	}

	public void setUtilhdx(float utilhdx) {
		this.utilhdx = utilhdx;
	}

	public String getCabinet() {
		return cabinet;
	}

	public void setCabinet(String cabinet) {
		this.cabinet = cabinet;
	}
	
	
}
