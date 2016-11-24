package com.dhcc.bussiness.sxydidc.order;

public class OrderModel {
	private String id;
	private String custid;
	private String customerno;
	private String custType;
	private String custName;
	private String applyno;
	private String business;
	private String hirestarttime;
	private String hireendtime;
	private String createtime;
	private String updateapplyno;
	private String updatetime;
	private String status;
	
	private String prodId;//产品ID
	private String prodName;//产品名称
	private String typename;//产品类型名称
	private String prodProperty;//产品属性
	private String amount="1";//产品数量
	private String prodPrice="0";//产品价格
	private String prodDiscount="0";//产品最低折扣价
	
	private String instanceID;//流程实例ID
	private String flowStatus;//工单状态
	
		
	public OrderModel() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getCustid() {
		return custid;
	}
	public void setCustid(String custid) {
		this.custid = custid;
	}
	public String getCustomerno() {
		return customerno;
	}
	public void setCustomerno(String customerno) {
		this.customerno = customerno;
	}
	public String getCustType() {
		return custType;
	}
	public void setCustType(String custType) {
		this.custType = custType;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	
	public String getApplyno() {
		return applyno;
	}
	public void setApplyno(String applyno) {
		this.applyno = applyno;
	}
	public String getBusiness() {
		return business;
	}
	public void setBusiness(String business) {
		this.business = business;
	}

	public String getHirestarttime() {
		return hirestarttime;
	}
	public void setHirestarttime(String hirestarttime) {
		this.hirestarttime = hirestarttime;
	}
	public String getHireendtime() {
		return hireendtime;
	}
	public void setHireendtime(String hireendtime) {
		this.hireendtime = hireendtime;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUpdateapplyno() {
		return updateapplyno;
	}
	public void setUpdateapplyno(String updateapplyno) {
		this.updateapplyno = updateapplyno;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public String getProdProperty() {
		return prodProperty;
	}
	public void setProdProperty(String prodProperty) {
		this.prodProperty = prodProperty;
	}
	
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getProdPrice() {
		return prodPrice;
	}
	public void setProdPrice(String prodPrice) {
		this.prodPrice = prodPrice;
	}
	public String getProdDiscount() {
		return prodDiscount;
	}
	public void setProdDiscount(String prodDiscount) {
		this.prodDiscount = prodDiscount;
	}
	public String getInstanceID() {
		return instanceID;
	}
	public void setInstanceID(String instanceID) {
		this.instanceID = instanceID;
	}
	public String getFlowStatus() {
		return flowStatus;
	}
	public void setFlowStatus(String flowStatus) {
		this.flowStatus = flowStatus;
	}
	
}
