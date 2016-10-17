package com.dhcc.modal.sxydidc.rack;

public class Rsrack {
	private String id;// 机架id
	private String name;// 机架名称
	private String code;// 机架编码
	private String typeid;// 机架规格，数据字典：42:42U，01：柱子，02：空位。。。
	private Integer ucount;// u位数量
	private String roomid;// 所在机房id
	private Integer colno;// 列号
	private Integer rowno;// 行号
	private Integer xposition;// 位置的x坐标，单位是像素
	private Integer yposition;// 位置的y坐标，单位是像素
	private String power;// 最大功率
	private String status;// 机柜状态：数据字典：01：空闲 02：散租，03：整租 04：预占
	private String customerid;// 所属客户id
	private String dcid;// 所属数据中心

	/** 机架id */
	public String getId() {
		return id;
	}

	/** 机架id */
	public void setId(String id) {
		this.id = id;
	}

	/** 机架名称 */
	public String getName() {
		return name;
	}

	/** 机架名称 */
	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/** 机架规格，数据字典：42:42U，01：柱子，02：空位。。。 */
	public String getTypeid() {
		return typeid;
	}

	/** 机架规格，数据字典：42:42U，01：柱子，02：空位。。。 */
	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	/** u位数量 */
	public Integer getUcount() {
		return ucount;
	}

	/** u位数量 */
	public void setUcount(Integer ucount) {
		this.ucount = ucount;
	}

	/** 所在机房id */
	public String getRoomid() {
		return roomid;
	}

	/** 所在机房id */
	public void setRoomid(String roomid) {
		this.roomid = roomid;
	}

	/** 列号 */
	public Integer getColno() {
		return colno;
	}

	/** 列号 */
	public void setColno(Integer colno) {
		this.colno = colno;
	}

	/** 行号 */
	public Integer getRowno() {
		return rowno;
	}

	/** 行号 */
	public void setRowno(Integer rowno) {
		this.rowno = rowno;
	}

	/** 位置的x坐标，单位是像素 */
	public Integer getXposition() {
		return xposition;
	}

	/** 位置的x坐标，单位是像素 */
	public void setXposition(Integer xposition) {
		this.xposition = xposition;
	}

	/** 位置的y坐标，单位是像素 */
	public Integer getYposition() {
		return yposition;
	}

	/** 位置的y坐标，单位是像素 */
	public void setYposition(Integer yposition) {
		this.yposition = yposition;
	}

	/** 最大功率 */
	public String getPower() {
		return power;
	}

	/** 最大功率 */
	public void setPower(String power) {
		this.power = power;
	}

	/** 机柜状态：数据字典：01：空闲 02：散租，03：整租 04：预占 */
	public String getStatus() {
		return status;
	}

	/** 机柜状态：数据字典：01：空闲 02：散租，03：整租 04：预占 */
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

	public String getDcid() {
		return dcid;
	}

	public void setDcid(String dcid) {
		this.dcid = dcid;
	}
}
