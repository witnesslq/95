package com.dhcc.flow.bussiness.resschedu;
public class RsnetdevportModel {
    private String id;//
    private String portname;//端口名称,G1/1/1
    private String portcode;//端口编码
    private String netdevpackid;//所属板卡ID
    private String type;//端口类型：01 1000M光口 02 100M电口
    private Integer confrate;//速率
    private String status;//01空闲；02预占；03实占；04使用
    private String customerid;//所属客户id
    private String macaddr;//MAC地址
    private String ipaddr;//IP地址
    private String toserverid;//对口服务器ID
    private String tonetdevid;//对口网络设备ID
    private String description;//端口描述
    private String no;//序号
    private Integer rowno;//端口行号
    private Integer colno;//端口列号
    
    private String customername;//客户姓名
    
    private String devicename;//网络设备名称
    
    private String porttypename;//端口类型名称
    
    private String servicename;//服务器名称
    

	/**  */
    public String getId() {
        return id;
    }
    /**  */
    public void setId(String id) {
        this.id = id;
    }
    /** 端口名称,G1/1/1 */
    public String getPortname() {
        return portname;
    }
    /** 端口名称,G1/1/1 */
    public void setPortname(String portname) {
        this.portname = portname;
    }
    /** 端口编码 */
    public String getPortcode() {
        return portcode;
    }
    /** 端口编码 */
    public void setPortcode(String portcode) {
        this.portcode = portcode;
    }
    /** 所属板卡ID */
    public String getNetdevpackid() {
        return netdevpackid;
    }
    /** 所属板卡ID */
    public void setNetdevpackid(String netdevpackid) {
        this.netdevpackid = netdevpackid;
    }
    /** 端口类型：01 1000M光口 02 100M电口 */
    public String getType() {
        return type;
    }
    /** 端口类型：01 1000M光口 02 100M电口 */
    public void setType(String type) {
        this.type = type;
    }
    /** 速率 */
    public Integer getConfrate() {
        return confrate;
    }
    /** 速率 */
    public void setConfrate(Integer confrate) {
        this.confrate = confrate;
    }
    /** 01空闲；02预占；03实占；04使用 */
    public String getStatus() {
        return status;
    }
    /** 01空闲；02预占；03实占；04使用 */
    public void setStatus(String status) {
        this.status = status;
    }
    /** 所属客户id */
    public String getCustomerid() {
        return customerid;
    }
    /** 所属客户id */
    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }
    /** MAC地址 */
    public String getMacaddr() {
        return macaddr;
    }
    /** MAC地址 */
    public void setMacaddr(String macaddr) {
        this.macaddr = macaddr;
    }
    /** IP地址 */
    public String getIpaddr() {
        return ipaddr;
    }
    /** IP地址 */
    public void setIpaddr(String ipaddr) {
        this.ipaddr = ipaddr;
    }
    /** 对口服务器ID */
    public String getToserverid() {
        return toserverid;
    }
    /** 对口服务器ID */
    public void setToserverid(String toserverid) {
        this.toserverid = toserverid;
    }
    /** 对口网络设备ID */
    public String getTonetdevid() {
        return tonetdevid;
    }
    /** 对口网络设备ID */
    public void setTonetdevid(String tonetdevid) {
        this.tonetdevid = tonetdevid;
    }
    /** 端口描述 */
    public String getDescription() {
        return description;
    }
    /** 端口描述 */
    public void setDescription(String description) {
        this.description = description;
    }
    /** 序号 */
    public String getNo() {
        return no;
    }
    /** 序号 */
    public void setNo(String no) {
        this.no = no;
    }
    /** 端口行号 */
    public Integer getRowno() {
        return rowno;
    }
    /** 端口行号 */
    public void setRowno(Integer rowno) {
        this.rowno = rowno;
    }
    /** 端口列号 */
    public Integer getColno() {
        return colno;
    }
    /** 端口列号 */
    public void setColno(Integer colno) {
        this.colno = colno;
    }
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	public String getServicename() {
		return servicename;
	}
	public void setServicename(String servicename) {
		this.servicename = servicename;
	}
	
	public String getPorttypename() {
			return porttypename;
	}
	public void setPorttypename(String porttypename) {
		this.porttypename = porttypename;
	}
	public String getDevicename() {
		return devicename;
	}
	public void setDevicename(String devicename) {
		this.devicename = devicename;
	}
    
}