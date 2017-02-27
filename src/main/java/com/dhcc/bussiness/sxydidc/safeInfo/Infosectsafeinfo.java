package com.dhcc.bussiness.sxydidc.safeInfo;

public class Infosectsafeinfo {
    private String id;//
    private String custid;//客户名称
    private String url;//不良信息URL地址
    private String ip;//不良信息IP地址
    private String toemailadd;//接受邮件地址
    private String sendtime;//发送邮件时间
    private String endtime="0";//完成时间
    private String dealtime="0";//处理时间（分钟数）
    private String status="01";//处理状态(01:未处理,02:已处理)
    private String createuser;//创建人
    private String dealuser;//处理人
    private String result;//处理结果描述
    private String dcid;//

    /**  */
    public String getId() {
        return id;
    }
    /**  */
    public void setId(String id) {
        this.id = id;
    }
     
    public String getCustid() {
		return custid;
	}
	public void setCustid(String custid) {
		this.custid = custid;
	}
	
	/** 不良信息URL地址 */
    public String getUrl() {
        return url;
    }
    /** 不良信息URL地址 */
    public void setUrl(String url) {
        this.url = url;
    }
    /** 不良信息IP地址 */
    public String getIp() {
        return ip;
    }
    /** 不良信息IP地址 */
    public void setIp(String ip) {
        this.ip = ip;
    }
    /** 接受邮件地址 */
    public String getToemailadd() {
        return toemailadd;
    }
    /** 接受邮件地址 */
    public void setToemailadd(String toemailadd) {
        this.toemailadd = toemailadd;
    }
    /** 发送邮件时间 */
    public String getSendtime() {
        return sendtime;
    }
    /** 发送邮件时间 */
    public void setSendtime(String sendtime) {
        this.sendtime = sendtime;
    }
    
    public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	/** 处理时间（分钟数） */
    public String getDealtime() {
        return dealtime;
    }
    /** 处理时间（分钟数） */
    public void setDealtime(String dealtime) {
        this.dealtime = dealtime;
    }
    public String getCreateuser() {
		return createuser;
	}
	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}
	public String getDealuser() {
		return dealuser;
	}
	public void setDealuser(String dealuser) {
		this.dealuser = dealuser;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	/** 处理结果(02:已处理，01:未处理) */
    public String getStatus() {
        return status;
    }
    /** 处理结果(02:已处理，01:未处理) */
    public void setStatus(String status) {
        this.status = status;
    }
	public String getDcid() {
		return dcid;
	}
	public void setDcid(String dcid) {
		this.dcid = dcid;
	}
    
}
