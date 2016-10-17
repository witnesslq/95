package com.dhcc.bussiness.sxydidc.ip;

public class IPModel {
	private String id;//
    private String ipadd;//IP地址
    private String ipsegid;//网段ID
    private String ipsegname;//网段名
    private String status;//IP状态
    private String customerid;//客户ID
    private String customername;//客户名称
    private String deviceid;//服务ID
    private String devicename;//服务名称
    private String remark;//备注
    private int sort;//排序
    private boolean needDevice=true;
    private String dcid;
    private String dcname;
    private String statusName;//状态值
    
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
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
	
	public String getIpsegname() {
		return ipsegname;
	}
	public void setIpsegname(String ipsegname) {
		this.ipsegname = ipsegname;
	}
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
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
	public String getDevicename() {
		return devicename;
	}
	public void setDevicename(String devicename) {
		this.devicename = devicename;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public boolean getNeedDevice() {
		return needDevice;
	}
	public void setNeedDevice(boolean needDevice) {
		this.needDevice = needDevice;
	}
	public String getDcid() {
		return dcid;
	}
	public void setDcid(String dcid) {
		this.dcid = dcid;
	}
	public String getDcname() {
		return dcname;
	}
	public void setDcname(String dcname) {
		this.dcname = dcname;
	}
	
}
