package com.dhcc.flow.bussiness.resschedu;

public class RsDevicepackModel {
	    private String  id;//板卡id
	    private String  deviceid;//所属网络设备id，对应rsdevice表
	    private String  code;//板卡编号
	    private String  sn;//板卡序列号
	    private String  porttype;//板卡上的端口类型，数据字典  01：千兆光口  02：百兆电口
	    private Integer rowcount;//此板卡共几行
	    private Integer perrow;//每行多少个端口
	    private Integer portcount;//总共的端口数
	    private Integer slotno;//板卡所在的插槽编号从0开始
	    private Integer packno;//板卡序号从0开始

	    /** 板卡id */
	    public String getId() {
	        return id;
	    }
	    /** 板卡id */
	    public void setId(String id) {
	        this.id = id;
	    }
	    /** 所属网络设备id，对应rsdevice表 */
	    public String getDeviceid() {
	        return deviceid;
	    }
	    /** 所属网络设备id，对应rsdevice表 */
	    public void setDeviceid(String deviceid) {
	        this.deviceid = deviceid;
	    }
	    /** 板卡编号 */
	    public String getCode() {
	        return code;
	    }
	    /** 板卡编号 */
	    public void setCode(String code) {
	        this.code = code;
	    }
	    /** 板卡序列号 */
	    public String getSn() {
	        return sn;
	    }
	    /** 板卡序列号 */
	    public void setSn(String sn) {
	        this.sn = sn;
	    }
	    /** 板卡上的端口类型，数据字典  01：千兆光口  02：百兆电口 */
	    public String getPorttype() {
	        return porttype;
	    }
	    /** 板卡上的端口类型，数据字典  01：千兆光口  02：百兆电口 */
	    public void setPorttype(String porttype) {
	        this.porttype = porttype;
	    }
	    /** 此板卡共几行 */
	    public Integer getRowcount() {
	        return rowcount;
	    }
	    /** 此板卡共几行 */
	    public void setRowcount(Integer rowcount) {
	        this.rowcount = rowcount;
	    }
	    /** 每行多少个端口 */
	    public Integer getPerrow() {
	        return perrow;
	    }
	    /** 每行多少个端口 */
	    public void setPerrow(Integer perrow) {
	        this.perrow = perrow;
	    }
	    /** 总共的端口数 */
	    public Integer getPortcount() {
	        return portcount;
	    }
	    /** 总共的端口数 */
	    public void setPortcount(Integer portcount) {
	        this.portcount = portcount;
	    }
	    /** 板卡所在的插槽编号从0开始 */
	    public Integer getSlotno() {
	        return slotno;
	    }
	    /** 板卡所在的插槽编号从0开始 */
	    public void setSlotno(Integer slotno) {
	        this.slotno = slotno;
	    }
	    /** 板卡序号从0开始 */
	    public Integer getPackno() {
	        return packno;
	    }
	    /** 板卡序号从0开始 */
	    public void setPackno(Integer packno) {
	        this.packno = packno;
	    }
	}