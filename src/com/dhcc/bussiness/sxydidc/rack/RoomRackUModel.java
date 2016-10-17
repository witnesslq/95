package com.dhcc.bussiness.sxydidc.rack;

public class RoomRackUModel {
	  private String id;//
	    private String rackid;//所属机架
	    private String rackName;//机架名称
	    private String devicecode;//机架编码
	    private String status;//u位状态，数据字典01：空闲  02：预占  03：实占  04：使用中（已放设备）业务状态
	    private String deviceid;//放置的设备id
	    private String deviceName;//设备名称
	    private String customerid;//所属客户id
	    private String customerName;//客户名称
	    private Integer no; //序号第几U，从1开始
	    private String ip;  //占用ip
	    private String port;  //上联端口
	    private String runstatus;  //运行状态
	    private String model;  //型号
	    private String uno;  //U位上设备所占u位数
	    private String nonumber;  //U位上设备所占最大u为编号
	    private String devicetype;
	    /**  */
	    public String getId() {
	        return id;
	    }
	    /**  */
	    public void setId(String id) {
	        this.id = id;
	    }
	    /** 所属机柜 */
	    public String getRackid() {
	        return rackid;
	    }
	    /** 所属机柜 */
	    public void setRackid(String rackid) {
	        this.rackid = rackid;
	    }
	    /** u位状态，数据字典01：空闲  02：预占  03：实占  04：使用中（已放设备） */
	    public String getStatus() {
	        return status;
	    }
	    /** u位状态，数据字典01：空闲  02：预占  03：实占  04：使用中（已放设备） */
	    public void setStatus(String status) {
	        this.status = status;
	    }
	    /** 放置的设备id */
	    public String getDeviceid() {
	        return deviceid;
	    }
	    /** 放置的设备id */
	    public void setDeviceid(String deviceid) {
	        this.deviceid = deviceid;
	    }
	    /** 所属客户id */
	    public String getCustomerid() {
	        return customerid;
	    }
	    /** 所属客户id */
	    public void setCustomerid(String customerid) {
	        this.customerid = customerid;
	    }
	    /** 序号第几U，从1开始 */
	    public Integer getNo() {
	        return no;
	    }
	    /** 序号第几U，从1开始 */
	    public void setNo(Integer no) {
	        this.no = no;
	    }
		public String getRackName() {
			return rackName;
		}
		public void setRackName(String rackName) {
			this.rackName = rackName;
		}
		public String getDeviceName() {
			return deviceName;
		}
		public void setDeviceName(String deviceName) {
			this.deviceName = deviceName;
		}
		public String getCustomerName() {
			return customerName;
		}
		public void setCustomerName(String customerName) {
			this.customerName = customerName;
		}
		public String getIp() {
			return ip;
		}
		public void setIp(String ip) {
			this.ip = ip;
		}
		public String getPort() {
			return port;
		}
		public void setPort(String port) {
			this.port = port;
		}
		public String getRunstatus() {
			return runstatus;
		}
		public void setRunstatus(String runstatus) {
			this.runstatus = runstatus;
		}
		public String getModel() {
			return model;
		}
		public void setModel(String model) {
			this.model = model;
		}
		public String getUno() {
			return uno;
		}
		public void setUno(String uno) {
			this.uno = uno;
		}
		public String getNonumber() {
			return nonumber;
		}
		public void setNonumber(String nonumber) {
			this.nonumber = nonumber;
		}
		public String getDevicetype() {
			return devicetype;
		}
		public void setDevicetype(String devicetype) {
			this.devicetype = devicetype;
		}
	    
	    
}
