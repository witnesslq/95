package com.dhcc.modal.sxydidc.rsserver;
public class Rsdevice {
    private String id;//
    private String name;//设备名称
    private String code;//设备编码
    private String sn;//设备序列号
    private String moduletype;//设备型号
    private String devicetype;//设备类型：数据字典：01：网络设备 02：服务器 03：xxxx
    private Integer owner;//设备所属   1：局方   2：客户  默认为1
    private String roomid;//所在机房
    private String rackid;//安装机架
    private String customerid;//所属客户ID
    private Integer startu;//起始U位
    private Integer power;//设备功率，单位为瓦
    private Integer ucount;//U位数量
    private String confid;//服务器的配置信息ID
    private String createtime;//创建时间
    private String createman;//
    private String buytime;//设备购入时间
    private String status;//设备状态：数据字典01：空闲  02：预占  03：实占  04：在线运行   05：停机   
    private String remark;//配置信息
    private String comment;//备注
    private String useyears;//设备使用年限
    private String dcid;//所属数据中心ID

    /**  */
    public String getId() {
        return id;
    }
    /**  */
    public void setId(String id) {
        this.id = id;
    }
    /** 设备名称 */
    public String getName() {
        return name;
    }
    /** 设备名称 */
    public void setName(String name) {
        this.name = name;
    }
    /** 设备编码 */
    public String getCode() {
        return code;
    }
    /** 设备编码 */
    public void setCode(String code) {
        this.code = code;
    }
    /** 设备序列号 */
    public String getSn() {
        return sn;
    }
    /** 设备序列号 */
    public void setSn(String sn) {
        this.sn = sn;
    }
    /** 设备型号 */
    public String getModuletype() {
        return moduletype;
    }
    /** 设备型号 */
    public void setModuletype(String moduletype) {
        this.moduletype = moduletype;
    }
    /** 设备类型：数据字典：01：网络设备 02：服务器 03：xxxx */
    public String getDevicetype() {
        return devicetype;
    }
    /** 设备类型：数据字典：01：网络设备 02：服务器 03：xxxx */
    public void setDevicetype(String devicetype) {
        this.devicetype = devicetype;
    }
    /** 设备所属   1：局方   2：客户  默认为1 */
    public Integer getOwner() {
        return owner;
    }
    /** 设备所属   1：局方   2：客户  默认为1 */
    public void setOwner(Integer owner) {
        this.owner = owner;
    }
    /** 所在机房 */
    public String getRoomid() {
        return roomid;
    }
    /** 所在机房 */
    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }
    /** 安装机架 */
    public String getRackid() {
        return rackid;
    }
    /** 安装机架 */
    public void setRackid(String rackid) {
        this.rackid = rackid;
    }
    /** 所属客户ID */
    public String getCustomerid() {
        return customerid;
    }
    /** 所属客户ID */
    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }
    /** 起始U位 */
    public Integer getStartu() {
        return startu;
    }
    /** 起始U位 */
    public void setStartu(Integer startu) {
        this.startu = startu;
    }
    /** 设备功率，单位为瓦 */
    public Integer getPower() {
        return power;
    }
    /** 设备功率，单位为瓦 */
    public void setPower(Integer power) {
        this.power = power;
    }
    /** U位数量 */
    public Integer getUcount() {
        return ucount;
    }
    /** U位数量 */
    public void setUcount(Integer ucount) {
        this.ucount = ucount;
    }
    /** 服务器的配置信息ID */
    public String getConfid() {
        return confid;
    }
    /** 服务器的配置信息ID */
    public void setConfid(String confid) {
        this.confid = confid;
    }
    /** 创建时间 */
    public String getCreatetime() {
        return createtime;
    }
    /** 创建时间 */
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
    /**  */
    public String getCreateman() {
        return createman;
    }
    /**  */
    public void setCreateman(String createman) {
        this.createman = createman;
    }
    /** 设备购入时间 */
    public String getBuytime() {
        return buytime;
    }
    /** 设备购入时间 */
    public void setBuytime(String buytime) {
        this.buytime = buytime;
    }
    /** 设备状态：数据字典01：空闲  02：预占  03：实占  04：在线运行   05：停机    */
    public String getStatus() {
        return status;
    }
    /** 设备状态：数据字典01：空闲  02：预占  03：实占  04：在线运行   05：停机    */
    public void setStatus(String status) {
        this.status = status;
    }
    /** 备注 */
    public String getRemark() {
        return remark;
    }
    /** 备注 */
    public void setRemark(String remark) {
        this.remark = remark;
    }
    /** 设备使用年限 */
    public String getUseyears() {
        return useyears;
    }
    /** 设备使用年限 */
    public void setUseyears(String useyears) {
        this.useyears = useyears;
    }
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getDcid() {
		return dcid;
	}
	public void setDcid(String dcid) {
		this.dcid = dcid;
	}
    
}