package com.dhcc.bussiness.sxydidc.rsserver;

import java.io.Serializable;

import com.dhcc.common.util.StringUtil;

public class RsserverModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;// id
	private String name;// 设备名称
	private String code;// 设备编码
	private String sn;// 设备序列号
	private String moduletype;// 设备型号
	private String devicetype;// 设备类型：数据字典：01：网络设备 02：服务器 03：xxxx
	private String owner;// 设备所属 1：局方 2：客户 默认为1
	private String roomid;// 所在机房
	private String roomname;// 机房
	private String rackid;// 安装机架
	private String rackname;// 所在机架
	private String customerid;// 所属客户ID
	private String customername;// 客户名称
	private String ipid;// IP地址id
	private String ipadd;// 服务器的ip
	private String batchIpAdd;// 批量多个IP地址
	private int ucount;// U位数量
	private int startu;// 起始U位
	private String status = "01";// 设备状态：数据字典01：空闲 02：预占 03：实占 04：在线运行 05：停机
	private String addcount;// 新增的时候服务器数量
	private int power;// 设备功率，单位为瓦
	private String buytime;// 购入时间
	private String createtime;// 创建时间
	private String useyears;// 使用年限
	private String remark;// 服务器配置信息
	private String comment;// 备注
	private String portname;// 使用的端口名称
	private String dcid;// 所属数据中心ID
	private String dcname;// 所属数据中心
	
	private String ownerName;//设备所属 1：局方 2：客户 默认为1
	private String statusName;//设备状态值
	private String portid;//端口id

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

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
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

	public String getCustomerid() {
		return customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getStartu() {
		return startu;
	}

	public void setStartu(int startu) {
		this.startu = startu;
	}

	public String getAddcount() {
		return addcount;
	}

	public void setAddcount(String addcount) {
		this.addcount = addcount;
	}

	public String getBuytime() {
		return buytime;
	}

	public void setBuytime(String buytime) {
		this.buytime = buytime;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getUseyears() {
		return useyears;
	}

	public void setUseyears(String useyears) {
		this.useyears = useyears;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public String getPortname() {
		return portname;
	}

	public void setPortname(String portname) {
		this.portname = portname;
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

	public String getDcname() {
		return dcname;
	}

	public void setDcname(String dcname) {
		this.dcname = dcname;
	}
	

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getStatusName() {
		if(!StringUtil.isNullOrEmpty(this.status)){
			if(this.status.equals("01")){
				return "空闲";
			}else if(this.status.equals("02")){
				return "预占";
			}else if(this.status.equals("03")){
				return "实占";
			}else if(this.status.equals("04")){
				return "使用中";
			}else if(this.status.equals("05")){
				return "停机";
			}
		}
		return "";
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getPortid() {
		return portid;
	}

	public void setPortid(String portid) {
		this.portid = portid;
	}

}
