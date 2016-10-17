package com.dhcc.bussiness.sxydidc.device;

public class DevicepackModel {
	private String id;
    private String deviceid;
    private String code;
    private String sn;
    private String porttype;
    private int rowcount;
    private int perrow;
    private int portcount;
    private int slotno;
    private int packno;
    private String status="01";
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
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

	public String getPorttype() {
		return porttype;
	}
	public void setPorttype(String porttype) {
		this.porttype = porttype;
	}
	public int getPortcount() {
		return portcount;
	}
	public void setPortcount(int portcount) {
		this.portcount = portcount;
	}
	public int getRowcount() {
		return rowcount;
	}
	public void setRowcount(int rowcount) {
		this.rowcount = rowcount;
	}
	public int getPerrow() {
		return perrow;
	}
	public void setPerrow(int perrow) {
		this.perrow = perrow;
	}
	public int getSlotno() {
		return slotno;
	}
	public void setSlotno(int slotno) {
		this.slotno = slotno;
	}
	public int getPackno() {
		return packno;
	}
	public void setPackno(int packno) {
		this.packno = packno;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
    
}
