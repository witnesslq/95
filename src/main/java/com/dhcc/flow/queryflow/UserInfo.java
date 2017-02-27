package com.dhcc.flow.queryflow;

public class UserInfo {
	private String userid;
	private String username;
	private String ipsum;
	
	//修改成查询端口后字段
	private String customer_id;
	private String customer_name;
	private String if_desc;          //端口描述
	private String ipaddress;        //端口对于IP
	
	public String getIpaddress() {
		return ipaddress;
	}
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getIf_desc() {
		return if_desc;
	}
	public void setIf_desc(String if_desc) {
		this.if_desc = if_desc;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getIpsum() {
		return ipsum;
	}
	public void setIpsum(String ipsum) {
		this.ipsum = ipsum;
	}
	
	
}
