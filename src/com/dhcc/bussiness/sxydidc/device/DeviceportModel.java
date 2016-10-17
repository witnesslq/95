package com.dhcc.bussiness.sxydidc.device;

public class DeviceportModel {
    private String id;//
    private String portname;//端口名称,G1/1/1
    private String portcode;//端口编码
    private String netdevpackid;//所属板卡ID
    private String netdevpackcode;//所属板卡编码
    private String type;//端口类型：01 1000M光口 02 100M电口
    private Integer confrate;//速率
    private String status;//01空闲；02预占；03实占；04使用
    private String customerid;//所属客户id
    private String customercode;//所属客户编码
    private String customername;//客户名称
    private String macaddr;//MAC地址
    private String ipaddr;//IP地址
    private String toserverid;//对口服务器ID
    private String toservercode;//对口服务器编码
    private String toservername;//对口服务器名称
    private String tonetdevid;//对口网络设备ID
    private String tonetdevcode;//对口网络设备编码
    private String tonetdevname;//对口网络设备名称
    private String deviceid;
    private String devicename;
    private String description;//端口描述
    private String no;//序号
    private Integer rowno;//端口行号
    private Integer colno;//端口列号
    private String dcname;//所属数据中心
    
    private String typeName;//类型名称
    
    private String statusName;//状态名称
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPortname() {
		return portname;
	}
	public void setPortname(String portname) {
		this.portname = portname;
	}
	public String getPortcode() {
		return portcode;
	}
	public void setPortcode(String portcode) {
		this.portcode = portcode;
	}
	public String getNetdevpackid() {
		return netdevpackid;
	}
	public void setNetdevpackid(String netdevpackid) {
		this.netdevpackid = netdevpackid;
	}
	public String getNetdevpackcode() {
		return netdevpackcode;
	}
	public void setNetdevpackcode(String netdevpackcode) {
		this.netdevpackcode = netdevpackcode;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getConfrate() {
		return confrate;
	}
	public void setConfrate(Integer confrate) {
		this.confrate = confrate;
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
	public String getCustomercode() {
		return customercode;
	}
	public void setCustomercode(String customercode) {
		this.customercode = customercode;
	}
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	public String getMacaddr() {
		return macaddr;
	}
	public void setMacaddr(String macaddr) {
		this.macaddr = macaddr;
	}
	public String getIpaddr() {
		return ipaddr;
	}
	public void setIpaddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}
	public String getToserverid() {
		return toserverid;
	}
	public void setToserverid(String toserverid) {
		this.toserverid = toserverid;
	}
	public String getToservercode() {
		return toservercode;
	}
	public void setToservercode(String toservercode) {
		this.toservercode = toservercode;
	}
	public String getToservername() {
		return toservername;
	}
	public void setToservername(String toservername) {
		this.toservername = toservername;
	}
	public String getTonetdevid() {
		return tonetdevid;
	}
	public void setTonetdevid(String tonetdevid) {
		this.tonetdevid = tonetdevid;
	}
	public String getTonetdevcode() {
		return tonetdevcode;
	}
	public void setTonetdevcode(String tonetdevcode) {
		this.tonetdevcode = tonetdevcode;
	}
	public String getTonetdevname() {
		return tonetdevname;
	}
	public void setTonetdevname(String tonetdevname) {
		this.tonetdevname = tonetdevname;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public Integer getRowno() {
		return rowno;
	}
	public void setRowno(Integer rowno) {
		this.rowno = rowno;
	}
	public Integer getColno() {
		return colno;
	}
	public void setColno(Integer colno) {
		this.colno = colno;
	}
	public String getDcname() {
		return dcname;
	}
	public void setDcname(String dcname) {
		this.dcname = dcname;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
}
