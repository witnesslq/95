package com.dhcc.modal.sxydidc.prodtype;

public class Busproducttype {
    private String id;//
    private String parentid;//父产品类型ID
    private String name;//产品类型名称
    private String code;//产品类型编码
    private String sort;//产品类型排序
    private String status;//产品类型状态

    /**  */
    public String getId() {
        return id;
    }
    /**  */
    public void setId(String id) {
        this.id = id;
    }
    /** 父产品类型ID */
    public String getParentid() {
        return parentid;
    }
    /** 父产品类型ID */
    public void setParentid(String parentid) {
        this.parentid = parentid;
    }
    /** 产品类型名称 */
    public String getName() {
        return name;
    }
    /** 产品类型名称 */
    public void setName(String name) {
        this.name = name;
    }
    /** 产品类型编码 */
    public String getCode() {
        return code;
    }
    /** 产品类型编码 */
    public void setCode(String code) {
        this.code = code;
    }
    /** 产品类型排序 */
    public String getSort() {
        return sort;
    }
    /** 产品类型排序 */
    public void setSort(String sort) {
        this.sort = sort;
    }
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
    
    
}
