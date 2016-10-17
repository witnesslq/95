package com.dhcc.bussiness.sxydidc.useat;

public class USeatModel {
    private String id;//
    private String roomid;//机房id
    private String roomName;//机房名称
    private String rackid;//所属机柜
    private String rackName;//机柜名称
    private String status;//u位状态，数据字典01：空闲  02：预占  03：实占  04：使用中（已放设备）
    private String deviceid;//放置的设备id
    private String deviceName;//设备名称
    private String customerid;//所属客户id
    private String customerName;//客户名称
    private Integer no;//序号第几U，从1开始
    private String nostr;//序号字符串
    private boolean needFilter=false;
    private String statusName;//U位状态值

    /**  */
    public String getId() {
        return id;
    }
    /**  */
    public void setId(String id) {
        this.id = id;
    }
    
    public String getRoomid() {
		return roomid;
	}
	public void setRoomid(String roomid) {
		this.roomid = roomid;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	/** 所属机柜 */
    public String getRackid() {
        return rackid;
    }
    /** 所属机柜 */
    public void setRackid(String rackid) {
        this.rackid = rackid;
    }
    /** u位状态，数据字典01：空闲  02：预占  03：实占  04：使用中（已放设备） */
    public String getStatus() {
        return status;
    }
    /** u位状态，数据字典01：空闲  02：预占  03：实占  04：使用中（已放设备） */
    public void setStatus(String status) {
        this.status = status;
    }
    /** 放置的设备id */
    public String getDeviceid() {
        return deviceid;
    }
    /** 放置的设备id */
    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }
    /** 所属客户id */
    public String getCustomerid() {
        return customerid;
    }
    /** 所属客户id */
    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }
    /** 序号第几U，从1开始 */
    public Integer getNo() {
        return no;
    }
    /** 序号第几U，从1开始 */
    public void setNo(Integer no) {
        this.no = no;
    }
	public String getRackName() {
		return rackName;
	}
	public void setRackName(String rackName) {
		this.rackName = rackName;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getNostr() {
		return nostr;
	}
	public void setNostr(String nostr) {
		this.nostr = nostr;
	}
	public boolean getNeedFilter() {
		return needFilter;
	}
	public void setNeedFilter(boolean needFilter) {
		this.needFilter = needFilter;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
    
}
