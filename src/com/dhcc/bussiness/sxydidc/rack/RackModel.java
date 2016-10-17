package com.dhcc.bussiness.sxydidc.rack;

public class RackModel {
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
    private String Ucustomername;//U位客户名称
    private Integer useredU;  //已用U位
    private Integer totalU;//总U位数
    private Integer freeU;//空闲U位数
    private Integer disRentU;//散租U位数
    private Integer whlRentU;//整租U位数
    private Integer preU;//预占U位数
    private boolean needFilter=false;
    private String dcid;// 所属数据中心ID
    private String dcname;//所属数据中心名称
    private String typeName;//机架规格名称
    private String statusName;//机架状态名称
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
	public Integer getTotalU() {
		return totalU;
	}
	public void setTotalU(Integer totalU) {
		this.totalU = totalU;
	}
	public Integer getFreeU() {
		return freeU;
	}
	public void setFreeU(Integer freeU) {
		this.freeU = freeU;
	}
	public Integer getDisRentU() {
		return disRentU;
	}
	public void setDisRentU(Integer disRentU) {
		this.disRentU = disRentU;
	}
	public Integer getWhlRentU() {
		return whlRentU;
	}
	public void setWhlRentU(Integer whlRentU) {
		this.whlRentU = whlRentU;
	}
	public Integer getPreU() {
		return preU;
	}
	public void setPreU(Integer preU) {
		this.preU = preU;
	}
	public String getUcustomername() {
		return Ucustomername;
	}
	public void setUcustomername(String ucustomername) {
		Ucustomername = ucustomername;
	}
	public boolean getNeedFilter() {
		return needFilter;
	}
	public void setNeedFilter(boolean needFilter) {
		this.needFilter = needFilter;
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
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
}
