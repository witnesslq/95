package com.dhcc.bussiness.sxydidc.contract;

import com.dhcc.common.util.StringUtil;

public class ContractModel {
	private String id;				// 主键ID
    private String contractno;		// 合同编号
    private String custid;			// 客户ID
    private String custno;			// 客户编号
    private String custname;		// 客户名称
    private String signer;			// 合同签订人
    private String signdate;		// 合同签订日期
    private String contractterm;	// 合同周期
    private String starttime;		// 合同起始时间
    private String endtime;			// 合同结束时间
    private String amount;			// 合同金额
    private String discount;		// 最低折扣价
    private String isvalid;			// 合同是否有效
    private String remark;			// 合同备注
    private String busid;			// 工单ID
    private String orderid;			// 订单ID
    private String status;			// 合同状态
    private String amount1;			//转换后的合同金额 amount/100
    private String discount1;		//转换后最低折扣价 discount/100
    private String isvalid1;		//合同是否有效  返回 是 否 
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContractno() {
		return contractno;
	}
	public void setContractno(String contractno) {
		this.contractno = contractno;
	}
	public String getCustid() {
		return custid;
	}
	public void setCustid(String custid) {
		this.custid = custid;
	}
	
	public String getCustno() {
		return custno;
	}
	public void setCustno(String custno) {
		this.custno = custno;
	}
	
	
	public String getCustname() {
		return custname;
	}
	public void setCustname(String custname) {
		this.custname = custname;
	}
	public String getSigner() {
		return signer;
	}
	public void setSigner(String signer) {
		this.signer = signer;
	}
	
	public String getSigndate() {
		return signdate;
	}
	public void setSigndate(String signdate) {
		this.signdate = signdate;
	}
	public String getContractterm() {
		return contractterm;
	}
	public void setContractterm(String contractterm) {
		this.contractterm = contractterm;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getIsvalid() {
		return isvalid;
	}
	public void setIsvalid(String isvalid) {
		this.isvalid = isvalid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getBusid() {
		return busid;
	}
	public void setBusid(String busid) {
		this.busid = busid;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	public String getAmount1() {
		if(!StringUtil.isNullOrEmpty(this.amount)){
		if(!this.amount.equals("null") && !this.amount.equals("")){
			return Double.parseDouble(amount)/100 + "";
		}
		}
		return "";
	}
	public void setAmount1(String amount1) {
		this.amount1 = amount1;
	}
	public String getDiscount1() {
		if(!StringUtil.isNullOrEmpty(this.discount)){
			if(!this.discount.equals("null") && !this.discount.equals("")){
				return Double.parseDouble(discount)/100 + "";
			}
		}
		return "";
	}
	public void setDiscount1(String discount1) {
		this.discount1 = discount1;
	}
	public String getIsvalid1() {
		if(!StringUtil.isNullOrEmpty(this.isvalid)){
			if (this.isvalid.equals("01")){
				return "是";
			}else{
				return "否";
			}
		}
		return "";
	}
	public void setIsvalid1(String isvalid1) {
		this.isvalid1 = isvalid1;
	}
	
}
