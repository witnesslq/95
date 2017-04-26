package com.dhcc.bussiness.sxydidc.order;

public class OrderResModel {
    private String id;//
    private String orderid;//订单ID
    private String applyno;//申请单号
    private String resid;//资源ID
    private String resname;//资源名称
    private String rescode;//资源编码
    private String restype;//资源类型
    private String resstatus;//资源状态
    private String rackid;//机架ID
    private String rackname;//机架名称
    private String roomid;//机房ID
    private String roomname;//机房名称
    private String custid;//客户ID
    private String custname;//客户名称
    private String deviceid;//设备ID
    private String devicename;//设备名称
    private String ipsegid;//网段ID
    private String ipsegname;//网段名称
    private String netmask;//子网掩码
    private String startip;//开始IP
    private String endip;//终止IP
    private String ucount;
    private String remark;
    private int serverCount;
    private int rackCount;
    private int useatCount;
    private int ipsegCount;
    private int ipCount;
    private int portCount;
    private int busOrdCount;//工单数量
    private int serOrdCount;//服务单数量
    /**  */
    public String getId() {
        return id;
    }
    /**  */
    public void setId(String id) {
        this.id = id;
    }
    /** 订单ID */
    public String getOrderid() {
        return orderid;
    }
    /** 订单ID */
    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }
    /** 申请单号 */
    public String getApplyno() {
        return applyno;
    }
    /** 申请单号 */
    public void setApplyno(String applyno) {
        this.applyno = applyno;
    }
    /** 资源ID */
    public String getResid() {
        return resid;
    }
    /** 资源ID */
    public void setResid(String resid) {
        this.resid = resid;
    }

	public String getResname() {
		return resname;
	}
	public void setResname(String resname) {
		this.resname = resname;
	}
	public String getRescode() {
		return rescode;
	}
	public void setRescode(String rescode) {
		this.rescode = rescode;
	}
	public String getResstatus() {
		return resstatus;
	}
	public void setResstatus(String resstatus) {
		this.resstatus = resstatus;
	}
	public String getRackid() {
		return rackid;
	}
	public void setRackid(String rackid) {
		this.rackid = rackid;
	}
	public String getRackname() {
		return rackname;
	}
	public void setRackname(String rackname) {
		this.rackname = rackname;
	}
	public String getRoomid() {
		return roomid;
	}
	public void setRoomid(String roomid) {
		this.roomid = roomid;
	}
	public String getRoomname() {
		return roomname;
	}
	public void setRoomname(String roomname) {
		this.roomname = roomname;
	}
	public String getCustid() {
		return custid;
	}
	public void setCustid(String custid) {
		this.custid = custid;
	}
	public String getCustname() {
		return custname;
	}
	public void setCustname(String custname) {
		this.custname = custname;
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
	
	public String getNetmask() {
		return netmask;
	}
	public void setNetmask(String netmask) {
		this.netmask = netmask;
	}
	public String getStartip() {
		return startip;
	}
	public void setStartip(String startip) {
		this.startip = startip;
	}
	public String getEndip() {
		return endip;
	}
	public void setEndip(String endip) {
		this.endip = endip;
	}
	public String getRestype() {
		return restype;
	}
	public void setRestype(String restype) {
		this.restype = restype;
	}
	public String getUcount() {
		return ucount;
	}
	public void setUcount(String ucount) {
		this.ucount = ucount;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getServerCount() {
		return serverCount;
	}
	public void setServerCount(int serverCount) {
		this.serverCount = serverCount;
	}
	public int getRackCount() {
		return rackCount;
	}
	public void setRackCount(int rackCount) {
		this.rackCount = rackCount;
	}
	public int getUseatCount() {
		return useatCount;
	}
	public void setUseatCount(int useatCount) {
		this.useatCount = useatCount;
	}
	public int getIpsegCount() {
		return ipsegCount;
	}
	public void setIpsegCount(int ipsegCount) {
		this.ipsegCount = ipsegCount;
	}
	public int getIpCount() {
		return ipCount;
	}
	public void setIpCount(int ipCount) {
		this.ipCount = ipCount;
	}
	public int getPortCount() {
		return portCount;
	}
	public void setPortCount(int portCount) {
		this.portCount = portCount;
	}
	public int getBusOrdCount() {
		return busOrdCount;
	}
	public void setBusOrdCount(int busOrdCount) {
		this.busOrdCount = busOrdCount;
	}
	public int getSerOrdCount() {
		return serOrdCount;
	}
	public void setSerOrdCount(int serOrdCount) {
		this.serOrdCount = serOrdCount;
	}
}
