package com.dhcc.modal.sxydidc.customer;

import java.io.Serializable;

/**
 * 客户资料表
 */
public class Busccustomer implements Serializable {
	private String id="";              
	private String name="";            
	private String no="";              
	private String type="";            
	private String remark="";
	private String status="";
	
	public Busccustomer() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
