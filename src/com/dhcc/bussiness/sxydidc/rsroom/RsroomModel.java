package com.dhcc.bussiness.sxydidc.rsroom;

import java.io.Serializable;

import com.dhcc.common.util.StringUtil;

public class RsroomModel implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
	private String roomname;
	private String roomcode;
	private String type;
	private String grade;
	private Integer colcount;
	private Integer rowcount;
	private Integer width;
	private Integer height;
	private String racktype;//机架规格
	private boolean needRack=false;//是否需要添加机架
	private String address;
	private String maintancer;
	private String maintancerid;
	private String telephone;
	private String remark;
	private String deptid;
	private String deptname;
	private String area;
	private String areaid;
	private Integer totalRack;//总机架数
	private Integer freeRack;//空闲机架数
	private Integer disRentRack;//散租机架数
	private Integer whlRentRack;//整租机架数
	private Integer preRack;//预占机架数
	private String status="01";
	private String dcid;//所属数据中心ID
	private String dcname;//所属数据中心NAME
	private String gradeValue;//机房级别值
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRoomname() {
		return roomname;
	}
	public void setRoomname(String roomname) {
		this.roomname = roomname;
	}
	public String getRoomcode() {
		return roomcode;
	}
	public void setRoomcode(String roomcode) {
		this.roomcode = roomcode;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public Integer getColcount() {
		return colcount;
	}
	public void setColcount(Integer colcount) {
		this.colcount = colcount;
	}
	public Integer getRowcount() {
		return rowcount;
	}
	public void setRowcount(Integer rowcount) {
		this.rowcount = rowcount;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public String getRacktype() {
		return racktype;
	}
	public void setRacktype(String racktype) {
		this.racktype = racktype;
	}
	public boolean getNeedRack() {
		return needRack;
	}
	public void setNeedRack(boolean needRack) {
		this.needRack = needRack;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMaintancer() {
		return maintancer;
	}
	public void setMaintancer(String maintancer) {
		this.maintancer = maintancer;
	}
	public String getMaintancerid() {
		return maintancerid;
	}
	public void setMaintancerid(String maintancerid) {
		this.maintancerid = maintancerid;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDeptid() {
		return deptid;
	}
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAreaid() {
		return areaid;
	}
	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}
	public Integer getTotalRack() {
		return totalRack;
	}
	public void setTotalRack(Integer totalRack) {
		this.totalRack = totalRack;
	}
	public Integer getFreeRack() {
		return freeRack;
	}
	public void setFreeRack(Integer freeRack) {
		this.freeRack = freeRack;
	}
	public Integer getDisRentRack() {
		return disRentRack;
	}
	public void setDisRentRack(Integer disRentRack) {
		this.disRentRack = disRentRack;
	}
	public Integer getWhlRentRack() {
		return whlRentRack;
	}
	public void setWhlRentRack(Integer whlRentRack) {
		this.whlRentRack = whlRentRack;
	}
	public Integer getPreRack() {
		return preRack;
	}
	public void setPreRack(Integer preRack) {
		this.preRack = preRack;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	/**
	 * 获取机房级别Value
	 * @return
	 */
	public String getGradeValue() {
		if(!StringUtil.isNullOrEmpty(this.grade)){
			if(this.grade.equals("01")){
				return "A";
			}else if(this.grade.equals("02")){
				return "B";
			}else if(this.grade.equals("03")){
				return "C";
			}
		}
		return "";
	}
	public void setGradeValue(String gradeValue) {
		this.gradeValue = gradeValue;
	}

}
