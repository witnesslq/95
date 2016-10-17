package com.dhcc.bussiness.sxydidc.rsserver;

import java.io.Serializable;

public class RsserverModelRs implements Serializable {

	
	 private String id;//
	    private String name;//设备名称
	    private String code;//设备编码
	    private String sn;//设备序列号
	    private String moduletype;//设备型号
	    private String devicetype;//设备类型：数据字典：01：网络设备 02：服务器 03：xxxx
	    private Integer owner;//设备所属   1：局方   2：客户  默认为1
	    private String roomid;//所在机房
	    private String rackid;//安装机架
	    private Integer ucount;//U位数量
	    private String customerid;//所属客户ID
	    private String remark;//备注
	    private String num;//新增的时候服务器数量
	    private Integer startu;//起始U位
	    private Integer power;//设备功率，单位为瓦
	    private String status;//设备状态：数据字典01：空闲  02：预占  03：实占  04：在线运行   05：停机   
	    private String createtime;//创建时间
	    private String createman;//
	    private String customer;//客户名称
	    private String room;//机房
	    private String rack;//所在机架
	    private String ipadd;//服务器的ip
	    private String port;//服务器对应的端口
	    private String ownername;//客户所属
	    private String Uno;//所占U位数
	    private String minU;//所占U位数
	    private String maxU;//所占U位数
	    private String devicedetail;//服务器配置
	    private String buytime;//设备购入时间
	    private String confid;//服务器的配置信息ID
	    private String useyears;//设备使用年限
	    private String ipid;//关联IP地址
	    private String  gettime;//购入时间
		public String getDevicedetail() {
			return devicedetail;
		}
		public void setDevicedetail(String devicedetail) {
			this.devicedetail = devicedetail;
		}
		public String getOwnername() {
			return ownername;
		}
		public void setOwnername(String ownername) {
			this.ownername = ownername;
		}
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
		public Integer getOwner() {
			return owner;
		}
		public void setOwner(Integer owner) {
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
		public Integer getUcount() {
			return ucount;
		}
		public void setUcount(Integer ucount) {
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
		public Integer getStartu() {
			return startu;
		}
		public void setStartu(Integer startu) {
			this.startu = startu;
		}
		public Integer getPower() {
			return power;
		}
		public void setPower(Integer power) {
			this.power = power;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getCreatetime() {
			return createtime;
		}
		public void setCreatetime(String createtime) {
			this.createtime = createtime;
		}
		public String getCreateman() {
			return createman;
		}
		public void setCreateman(String createman) {
			this.createman = createman;
		}
		public String getCustomer() {
			return customer;
		}
		public void setCustomer(String customer) {
			this.customer = customer;
		}
		public String getRoom() {
			return room;
		}
		public void setRoom(String room) {
			this.room = room;
		}
		public String getRack() {
			return rack;
		}
		public void setRack(String rack) {
			this.rack = rack;
		}
		public String getIpadd() {
			return ipadd;
		}
		public void setIpadd(String ipadd) {
			this.ipadd = ipadd;
		}
		public String getPort() {
			return port;
		}
		public void setPort(String port) {
			this.port = port;
		}
		public String getNum() {
			return num;
		}
		public void setNum(String num) {
			this.num = num;
		}
		public String getUno() {
			return Uno;
		}
		public void setUno(String uno) {
			Uno = uno;
		}
		public String getMinU() {
			return minU;
		}
		public void setMinU(String minU) {
			this.minU = minU;
		}
		public String getMaxU() {
			return maxU;
		}
		public void setMaxU(String maxU) {
			this.maxU = maxU;
		}
		public String getBuytime() {
			return buytime;
		}
		public void setBuytime(String buytime) {
			this.buytime = buytime;
		}
		public String getConfid() {
			return confid;
		}
		public void setConfid(String confid) {
			this.confid = confid;
		}
		public String getUseyears() {
			return useyears;
		}
		public void setUseyears(String useyears) {
			this.useyears = useyears;
		}
		public String getIpid() {
			return ipid;
		}
		public void setIpid(String ipid) {
			this.ipid = ipid;
		}
		public String getGettime() {
			return gettime;
		}
		public void setGettime(String gettime) {
			this.gettime = gettime;
		}
		
	
}
