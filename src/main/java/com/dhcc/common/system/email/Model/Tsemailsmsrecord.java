package com.dhcc.common.system.email.Model;

public class Tsemailsmsrecord {

	private String id;// 
	private String details;// 向被通知用户发送的短信，邮件内容
	private String mobile;// 被通知用户手机号码
	private String email;// 被通知用户电子邮件
	private int sendstate;// 送发状态（0：未发送；1：发送成功；2：发送失败）
	private String sendtime;// 发送成功时间
	private int sendnumber;// 重新发送次数
	private String createtime;// 该条记录产生时间
	private int sort;// 数据类别，用于区分信息来源（1：待办通知 2：服务到期提醒）
	private String loginname;//用户的登录的用户名
	private String password;//用户登录的密码

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getSendstate() {
		return sendstate;
	}

	public void setSendstate(int sendstate) {
		this.sendstate = sendstate;
	}

	public String getSendtime() {
		return sendtime;
	}

	public void setSendtime(String sendtime) {
		this.sendtime = sendtime;
	}

	public int getSendnumber() {
		return sendnumber;
	}

	public void setSendnumber(int sendnumber) {
		this.sendnumber = sendnumber;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

}
