package com.dhcc.bussiness.sxydidc.datacenter;

public class DataCenterModel {
    private String id;//ID主键
    private String name;//名称
    private String address;//地址
    private String companyId;//所属公司
    private String regionId;//所属区域
    private String remark;//备注

    /** ID主键 */
    public String getId() {
        return id;
    }
    /** ID主键 */
    public void setId(String id) {
        this.id = id;
    }
    /** 名称 */
    public String getName() {
        return name;
    }
    /** 名称 */
    public void setName(String name) {
        this.name = name;
    }
    /** 地址 */
    public String getAddress() {
        return address;
    }
    /** 地址 */
    public void setAddress(String address) {
        this.address = address;
    }
    /** 所属公司 */
    public String getCompanyId() {
        return companyId;
    }
    /** 所属公司 */
    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
    /** 所属区域 */
    public String getRegionId() {
        return regionId;
    }
    /** 所属区域 */
    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }
    /** 备注 */
    public String getRemark() {
        return remark;
    }
    /** 备注 */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}
