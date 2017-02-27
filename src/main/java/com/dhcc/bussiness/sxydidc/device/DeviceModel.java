package com.dhcc.bussiness.sxydidc.device;

public class DeviceModel {
	private String id;
    private String name;
    private String code;
    private String sn;
    private String status;
    private String moduletype;
    private String devicetype;
    private int owner;
    private String roomid;
    private String rackid;
    private String ipid;
    private String ipadd;
    private String batchIpAdd;//批量多个IP地址
    private int ucount;//U位数量
    private String customerid;
    private String customername;
    private String rackname;
    private String roomname;
    private String remark;
    private String startu;//起始U位
    private int power;
    private String dcid;
    private String dcname;
    
    private int totalPort=0;//总端口数
    private int freePort=0;//空闲端口数
    private int factPort=0;//实占端口数
    private int prePort=0;//预占端口数
    private int usedPort=0;//使用端口数
    
    private String ownerName;//
    
    
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getModuletype() {
		return moduletype;
	}
	public void setModuletype(String moduletype) {
		this.moduletype = moduletype;
	}
	public String getDevicetype() {
		return devicetype;
	}
	public void setDevicetype(String devicetype) {
		this.devicetype = devicetype;
	}
	public int getOwner() {
		return owner;
	}
	public void setOwner(int owner) {
		this.owner = owner;
	}
	public String getRoomid() {
		return roomid;
	}
	public void setRoomid(String roomid) {
		this.roomid = roomid;
	}
	public String getRackid() {
		return rackid;
	}
	public void setRackid(String rackid) {
		this.rackid = rackid;
	}
	
	public String getIpid() {
		return ipid;
	}
	public void setIpid(String ipid) {
		this.ipid = ipid;
	}
	
	public String getIpadd() {
		return ipadd;
	}
	public void setIpadd(String ipadd) {
		this.ipadd = ipadd;
	}
	public String getBatchIpAdd() {
		return batchIpAdd;
	}
	public void setBatchIpAdd(String batchIpAdd) {
		this.batchIpAdd = batchIpAdd;
	}
	public int getUcount() {
		return ucount;
	}
	public void setUcount(int ucount) {
		this.ucount = ucount;
	}
	public String getCustomerid() {
		return customerid;
	}
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getStartu() {
		return startu;
	}
	public void setStartu(String startu) {
		this.startu = startu;
	}
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	public String getRackname() {
		return rackname;
	}
	public void setRackname(String rackname) {
		this.rackname = rackname;
	}
	public String getRoomname() {
		return roomname;
	}
	public void setRoomname(String roomname) {
		this.roomname = roomname;
	}
	public int getTotalPort() {
		return totalPort;
	}
	public void setTotalPort(int totalPort) {
		this.totalPort = totalPort;
	}
	public int getFreePort() {
		return freePort;
	}
	public void setFreePort(int freePort) {
		this.freePort = freePort;
	}
	public int getFactPort() {
		return factPort;
	}
	public void setFactPort(int factPort) {
		this.factPort = factPort;
	}
	public int getPrePort() {
		return prePort;
	}
	public void setPrePort(int prePort) {
		this.prePort = prePort;
	}
	public int getUsedPort() {
		return usedPort;
	}
	public void setUsedPort(int usedPort) {
		this.usedPort = usedPort;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
    
}
