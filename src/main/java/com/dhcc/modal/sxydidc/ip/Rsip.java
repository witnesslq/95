package com.dhcc.modal.sxydidc.ip;

public class Rsip {
	private String id;//
    private String ipadd;//IP地址
    private String ipsegid;//网段ID
    private String status;//IP状态
    private String customerid;//客户ID
    private String deviceid;//设备ID
    private String remark;//备注
    private Integer sort;//排序
    private String dcid;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIpadd() {
		return ipadd;
	}
	public void setIpadd(String ipadd) {
		this.ipadd = ipadd;
	}
	public String getIpsegid() {
		return ipsegid;
	}
	public void setIpsegid(String ipsegid) {
		this.ipsegid = ipsegid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCustomerid() {
		return customerid;
	}
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}
	public String getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getDcid() {
		return dcid;
	}
	public void setDcid(String dcid) {
		this.dcid = dcid;
	}
	
}
