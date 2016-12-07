package com.dhcc.flow.queryflow;

public class FlowModel {
	private String userid;
	private String ipaddress;
	
	private String subentity;   //端口索引
	
	private String utilhdx;     //端口流量
	
	private float utilhdxperc;  //端口利用率
	
	private String ifspeed;     //速率
	
	private String collecttime;  //采集时间
	
	private String utilhdxflag="0"; //是否为异常流量
	
	private float sumthevalue;    //最大流量
	private float avgthevalue;    //平均流量
	
	private String entity; //出口  or  入口
	
	
	public String getIfspeed() {
		return ifspeed;
	}

	public void setIfspeed(String ifspeed) {
		this.ifspeed = ifspeed;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public float getSumthevalue() {
		return sumthevalue;
	}

	public void setSumthevalue(float sumthevalue) {
		this.sumthevalue = sumthevalue;
	}

	public float getAvgthevalue() {
		return avgthevalue;
	}

	public void setAvgthevalue(float avgthevalue) {
		this.avgthevalue = avgthevalue;
	}

	public String getUtilhdxflag() {
		return utilhdxflag;
	}

	public void setUtilhdxflag(String utilhdxflag) {
		this.utilhdxflag = utilhdxflag;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public String getUtilhdx() {
		return utilhdx;
	}

	public void setUtilhdx(String utilhdx) {
		this.utilhdx = utilhdx;
	}

	

	public float getUtilhdxperc() {
		return utilhdxperc;
	}

	public void setUtilhdxperc(float utilhdxperc) {
		this.utilhdxperc = utilhdxperc;
	}

	public String getCollecttime() {
		return collecttime;
	}

	public void setCollecttime(String collecttime) {
		this.collecttime = collecttime;
	}

	public String getSubentity() {
		return subentity;
	}

	public void setSubentity(String subentity) {
		this.subentity = subentity;
	}

	
	
	
}
