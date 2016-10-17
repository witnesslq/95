package com.dhcc.modal.sxydidc.contract;

public class Buscontract {
	private String id;				// 主键ID
    private String contractno;		// 合同编号
    private String custid;			// 客户ID
    private String signer;			// 合同签订人
    private long signdate;			// 合同签订日期
    private String contractterm;	// 合同周期
    private long starttime;			// 合同起始时间
    private long endtime;			// 合同结束时间

    private long amount;				// 合同金额
    private long discount;			// 最低折扣价

    private String isvalid;			// 合同是否有效
    private String remark;			// 合同备注
    private String busid;			// 工单ID
    private String orderid;			// 订单ID
    private String status;			// 合同状态
    
    /**  */
    public String getId() {
        return id;
    }
    /**  */
    public void setId(String id) {
        this.id = id;
    }
    /** 合同编号 */
    public String getContractno() {
        return contractno;
    }
    /** 合同编号 */
    public void setContractno(String contractno) {
        this.contractno = contractno;
    }
    /** 工单ID */
    public String getBusid() {
        return busid;
    }
    /** 工单ID */
    public void setBusid(String busid) {
        this.busid = busid;
    }
    /** 客户ID */
    public String getCustid() {
        return custid;
    }
    /** 客户ID */
    public void setCustid(String custid) {
        this.custid = custid;
    }
    /** 合同签订人 */
    public String getSigner() {
        return signer;
    }
    /** 合同签订人 */
    public void setSigner(String signer) {
        this.signer = signer;
    }
    /** 合同签订日期 */
    public long getSigndate() {
        return signdate;
    }
    /** 合同签订日期 */
    public void setSigndate(long signdate) {
        this.signdate = signdate;
    }
    /** 合同周期 */
    public String getContractterm() {
        return contractterm;
    }
    /** 合同周期 */
    public void setContractterm(String contractterm) {
        this.contractterm = contractterm;
    }
    /** 合同起始时间 */
    public long getStarttime() {
        return starttime;
    }
    /** 合同起始时间 */
    public void setStarttime(long starttime) {
        this.starttime = starttime;
    }
    /** 合同结束时间 */
    public long getEndtime() {
        return endtime;
    }
    /** 合同结束时间 */
    public void setEndtime(long endtime) {
        this.endtime = endtime;
    }
   
	public long getAmount() {
   
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public long getDiscount() {
		return discount;
	}

	public void setDiscount(long discount) {

		this.discount = discount;
	}
	/** 合同是否有效 */
    public String getIsvalid() {
        return isvalid;
    }
    /** 合同是否有效 */
    public void setIsvalid(String isvalid) {
        this.isvalid = isvalid;
    }
    /** 合同备注 */
    public String getRemark() {
        return remark;
    }
    /** 合同备注 */
    public void setRemark(String remark) {
        this.remark = remark;
    }
    /** 订单ID */
	public String getOrderid() {
		return orderid;
	}
	/** 订单ID */
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}