package com.dhcc.bussiness.sxydidc.product;

import com.dhcc.common.util.StringUtil;

public class ProductModel {
	private String id;  			
	private String typeid; 
	private String typename;
	private String name;  		
	private int price;  		
	private String createtime;  		
	private String createrid; 
	private String createname;
	private String remark;  	
	private String enabled;  	
	private String regionid;
	private String regionname;
	private String discount;
	private String sla;
	private String depict;
	private String dcid;//数据中心id
	private String dcname;//数据中心名称
	private String spec;
	private String property;
	private String amount;
    private String sort;
	private String status;
	
	private String propertyName;//产品性质名称
	
	private String priceValue;//产品价格 数据库取值除以100后的值
	
	private String discountValue;//产品折扣 数据库取值除以100后的值
	
	private String enabledValue;//产品状态
	

	public ProductModel() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	public String getCreaterid() {
		return createrid;
	}
	public void setCreaterid(String createrid) {
		this.createrid = createrid;
	}

	public String getCreatename() {
		return createname;
	}
	public void setCreatename(String createname) {
		this.createname = createname;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRegionid() {
		return regionid;
	}
	public void setRegionid(String regionid) {
		this.regionid = regionid;
	}
	public String getTypeid() {
		return typeid;
	}
	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	public String getRegionname() {
		return regionname;
	}
	public void setRegionname(String regionname) {
		this.regionname = regionname;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getSla() {
		return sla;
	}
	public void setSla(String sla) {
		this.sla = sla;
	}
	public String getDepict() {
		return depict;
	}
	public void setDepict(String depict) {
		this.depict = depict;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}

	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
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
	
	public String getPropertyName() {
		if(!StringUtil.isNullOrEmpty(this.property)){
			if (this.property.equals("01")){
				return "一次性收费";
			}else if(this.property.equals("02")){
				return "周期性收费";
			} 	
		}
		return "";
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public String getPriceValue() {
		return (float)price/100 + "";
	}
	public void setPriceValue(String priceValue) {
		this.priceValue = priceValue;
	}
	public String getDiscountValue() {
		if(!StringUtil.isNullOrEmpty(this.discount)){
			if(!this.discount.equals("null") && !this.discount.equals("")){
				return Float.parseFloat(discount)/100 + "";
			}
		}
		return "";
	}
	public void setDiscountValue(String discountValue) {
		this.discountValue = discountValue;
	}
	public String getEnabledValue() {
		if(!StringUtil.isNullOrEmpty(this.enabled)){
			if (this.enabled.equals("01")){
				return "可用";
			}else if(this.property.equals("02")){
				return "不可用";
			} 	
		}
		return "";
	}
	public void setEnabledValue(String enabledValue) {
		this.enabledValue = enabledValue;
	}
}
