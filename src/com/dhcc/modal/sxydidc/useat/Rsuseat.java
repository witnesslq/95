package com.dhcc.modal.sxydidc.useat;

public class Rsuseat {
    private String id;//
    private String rackid;//所属机柜
    private String status;//u位状态，数据字典01：空闲  02：预占  03：实占  04：使用中（已放设备）
    private String deviceid;//放置的设备id
    private String customerid;//所属客户id
    private Integer no;//序号第几U，从1开始

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
}