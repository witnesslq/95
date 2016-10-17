package com.dhcc.modal.sxydidc.rsroom;
public class Rsroom {
    private String id;//
    private String roomname;//机房名称
    private String roomcode;//机房编码
    private String grade;//机房等级：数据字典：01：A  02：B
    private Integer colcount;//机柜的总列数
    private Integer rowcount;//机柜的总行数
    private Integer width;//机房宽，单位是cm
    private Integer height;//机房长度：单位cm
    private String address;//机房地址
    private String maintancer;//机房负责人id
    private String telephone;//联系电话
    private String remark;//备注
    private String areaid;//地域id
    private String deptid;//所属公司或单位
    private String status;//机房状态
    private String racktype;//机架规格
    private String dcid;//所属数据中心

    /**  */
    public String getId() {
        return id;
    }
    /**  */
    public void setId(String id) {
        this.id = id;
    }
    /** 机房名称 */
    public String getRoomname() {
        return roomname;
    }
    /** 机房名称 */
    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }
    /** 机房编码 */
    public String getRoomcode() {
        return roomcode;
    }
    /** 机房编码 */
    public void setRoomcode(String roomcode) {
        this.roomcode = roomcode;
    }
    /** 机房等级：数据字典：01：A  02：B */
    public String getGrade() {
        return grade;
    }
    /** 机房等级：数据字典：01：A  02：B */
    public void setGrade(String grade) {
        this.grade = grade;
    }
    /** 机柜的总列数 */
    public Integer getColcount() {
        return colcount;
    }
    /** 机柜的总列数 */
    public void setColcount(Integer colcount) {
        this.colcount = colcount;
    }
    /** 机柜的总行数 */
    public Integer getRowcount() {
        return rowcount;
    }
    /** 机柜的总行数 */
    public void setRowcount(Integer rowcount) {
        this.rowcount = rowcount;
    }
    /** 机房宽，单位是cm */
    public Integer getWidth() {
        return width;
    }
    /** 机房宽，单位是cm */
    public void setWidth(Integer width) {
        this.width = width;
    }
    /** 机房长度：单位cm */
    public Integer getHeight() {
        return height;
    }
    /** 机房长度：单位cm */
    public void setHeight(Integer height) {
        this.height = height;
    }
    /** 机房地址 */
    public String getAddress() {
        return address;
    }
    /** 机房地址 */
    public void setAddress(String address) {
        this.address = address;
    }
    /** 机房负责人id */
    public String getMaintancer() {
        return maintancer;
    }
    /** 机房负责人id */
    public void setMaintancer(String maintancer) {
        this.maintancer = maintancer;
    }
    /** 联系电话 */
    public String getTelephone() {
        return telephone;
    }
    /** 联系电话 */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    /** 备注 */
    public String getRemark() {
        return remark;
    }
    /** 备注 */
    public void setRemark(String remark) {
        this.remark = remark;
    }
    /** 地域id */
    public String getAreaid() {
        return areaid;
    }
    /** 地域id */
    public void setAreaid(String areaid) {
        this.areaid = areaid;
    }

	public String getDeptid() {
		return deptid;
	}
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRacktype() {
		return racktype;
	}
	public void setRacktype(String racktype) {
		this.racktype = racktype;
	}
	public String getDcid() {
		return dcid;
	}
	public void setDcid(String dcid) {
		this.dcid = dcid;
	}
}