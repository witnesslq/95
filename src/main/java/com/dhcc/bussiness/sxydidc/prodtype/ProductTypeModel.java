package com.dhcc.bussiness.sxydidc.prodtype;

public class ProductTypeModel {
    private String id;//
    private String parentid;//父产品类型ID
    private String name;//产品类型名称
    private String code;//产品类型编码
    private String sort;//产品类型排序
    private String status;//产品类型状态
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
