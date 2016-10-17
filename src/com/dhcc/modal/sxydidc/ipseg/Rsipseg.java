package com.dhcc.modal.sxydidc.ipseg;

public class Rsipseg {
    private String id;//
    private String name;//IP段名称
    private String startip;//起始IP
    private String endip;//终止IP
    private String netmask;//子网掩码
    private String gatewayip;//网关IP
    private String dns1;//首先DNS
    private String dns2;//备选DNS
    private String vlanno;//vLan编号
    private String usefor;//用途说明
    private String status;//状态，数据字典：01：空闲  02：散租  03：xxxx
    private String customerid;//所属客户
    private String areaid;//所属地域id
    private String deptid;//所属部门id
    private String remark;//描述
    private Integer count;//IP数量
    private String dcid;//所属数据中心ID

    /**  */
    public String getId() {
        return id;
    }
    /**  */
    public void setId(String id) {
        this.id = id;
    }
    /** IP段名称 */
    public String getName() {
        return name;
    }
    /** IP段名称 */
    public void setName(String name) {
        this.name = name;
    }
    /** 起始IP */
    public String getStartip() {
        return startip;
    }
    /** 起始IP */
    public void setStartip(String startip) {
        this.startip = startip;
    }
    /** 终止IP */
    public String getEndip() {
        return endip;
    }
    /** 终止IP */
    public void setEndip(String endip) {
        this.endip = endip;
    }
    /** 子网掩码 */
    public String getNetmask() {
        return netmask;
    }
    /** 子网掩码 */
    public void setNetmask(String netmask) {
        this.netmask = netmask;
    }
    /** 网关IP */
    public String getGatewayip() {
        return gatewayip;
    }
    /** 网关IP */
    public void setGatewayip(String gatewayip) {
        this.gatewayip = gatewayip;
    }
    /** 首先DNS */
    public String getDns1() {
        return dns1;
    }
    /** 首先DNS */
    public void setDns1(String dns1) {
        this.dns1 = dns1;
    }
    /** 备选DNS */
    public String getDns2() {
        return dns2;
    }
    /** 备选DNS */
    public void setDns2(String dns2) {
        this.dns2 = dns2;
    }
    /** vLan编号 */
    public String getVlanno() {
        return vlanno;
    }
    /** vLan编号 */
    public void setVlanno(String vlanno) {
        this.vlanno = vlanno;
    }
    /** 用途说明 */
    public String getUsefor() {
        return usefor;
    }
    /** 用途说明 */
    public void setUsefor(String usefor) {
        this.usefor = usefor;
    }
    /** 状态，数据字典：01：空闲  02：散租  03：xxxx */
    public String getStatus() {
        return status;
    }
    /** 状态，数据字典：01：空闲  02：散租  03：xxxx */
    public void setStatus(String status) {
        this.status = status;
    }
    /** 所属客户 */
    public String getCustomerid() {
        return customerid;
    }
    /** 所属客户 */
    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }
    /** 所属地域id */
    public String getAreaid() {
        return areaid;
    }
    /** 所属地域id */
    public void setAreaid(String areaid) {
        this.areaid = areaid;
    }
    
    public String getDeptid() {
		return deptid;
	}
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}
	/** 描述 */
    public String getRemark() {
        return remark;
    }
    /** 描述 */
    public void setRemark(String remark) {
        this.remark = remark;
    }
    /** IP数量 */
    public Integer getCount() {
        return count;
    }
    /** IP数量 */
    public void setCount(Integer count) {
        this.count = count;
    }
	public String getDcid() {
		return dcid;
	}
	public void setDcid(String dcid) {
		this.dcid = dcid;
	}
    
}
