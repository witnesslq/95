package com.dhcc.bussiness.sxydidc.rsroom;

import java.util.List;

public class RoomAndRackModel {
	 private String id;//机架id
	    private String name;//机架名称
	    private String code;//机架编码
	    private String typeid;//机架规格，数据字典：42:42U，01：柱子，02：空位。。。
	    private Integer ucount;//u位数量
	    private String roomid;//所在机房id
	    private String roomName;//机房名称
	    private Integer colno;//列号
	    private Integer rowno;//行号
	    private Integer xposition;//位置的x坐标，单位是像素
	    private Integer yposition;//位置的y坐标，单位是像素
	    private String power;//最大功率
	    private String status;//机柜状态：数据字典：01：空闲  02：散租，03：整租 04：预占
	    private String customerid;//所属客户id
	    private String customerName;//客户名称
	    private Integer useredU;  //已用U位
	    private String roomname;
		private String roomcode;
		private String type;
		private String grade;
		private Long colcount;
		private Long rowcount;
		private Long width;
		private Long height;
		private String racktype;//机架规格
		private String address;
		private String maintancer;
		private String maintancerid;
		private String telephone;
		private String remark;
		private String area;
		private String areaid;
		@SuppressWarnings("unchecked")
		private List racks;
		private Long rackcount;
		
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
	    /** 机柜状态：数据字典：01：空闲  02：散租，03：整租 04：预占 */
	    public String getStatus() {
	        return status;
	    }
	    /** 机柜状态：数据字典：01：空闲  02：散租，03：整租 04：预占 */
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
		public String getRoomName() {
			return roomName;
		}
		public void setRoomName(String roomName) {
			this.roomName = roomName;
		}
		public String getCustomerName() {
			return customerName;
		}
		public void setCustomerName(String customerName) {
			this.customerName = customerName;
		}
		public Integer getUseredU() {
			return useredU;
		}
		public void setUseredU(Integer useredU) {
			this.useredU = useredU;
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
		public Long getColcount() {
			return colcount;
		}
		public void setColcount(Long colcount) {
			this.colcount = colcount;
		}
		public Long getRowcount() {
			return rowcount;
		}
		public void setRowcount(Long rowcount) {
			this.rowcount = rowcount;
		}
		public Long getWidth() {
			return width;
		}
		public void setWidth(Long width) {
			this.width = width;
		}
		public Long getHeight() {
			return height;
		}
		public void setHeight(Long height) {
			this.height = height;
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
		public List getRacks() {
			return racks;
		}
		public void setRacks(List racks) {
			this.racks = racks;
		}
		public Long getRackcount() {
			return rackcount;
		}
		public void setRackcount(Long rackcount) {
			this.rackcount = rackcount;
		}
		public String getRacktype() {
			return racktype;
		}
		public void setRacktype(String racktype) {
			this.racktype = racktype;
		}
	    
}
