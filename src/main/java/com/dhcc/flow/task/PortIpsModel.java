package com.dhcc.flow.task;


public class PortIpsModel {
	private String ipaddress; //ip地址
	private String entity;		//OutBandwidthUtilHdx、InBandwidthUtilHdx
	private String subentity;	//端口索引
	private String collecttime; //采集时间
	private String restype;   
	private String category;
	private String utilhdxunit;
	private double utilhdx;  //流量
	private double utilhdxperc;  //带宽利用率
	private double discardsperc;// 丢包率
	private double errorsperc;   //错误率
	private String percunit;     //利用率单位
	private String utilhdxflag;  //是否为异常
	private String recover;      //纠错后数据
	private String ifspeed;     //速率
	public String getIpaddress() {
		return ipaddress;
	}
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}
	public String getSubentity() {
		return subentity;
	}
	public void setSubentity(String subentity) {
		this.subentity = subentity;
	}
	public String getCollecttime() {
		return collecttime;
	}
	public void setCollecttime(String collecttime) {
		this.collecttime = collecttime;
	}
	public String getRestype() {
		return restype;
	}
	public void setRestype(String restype) {
		this.restype = restype;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getUtilhdxunit() {
		return utilhdxunit;
	}
	public void setUtilhdxunit(String utilhdxunit) {
		this.utilhdxunit = utilhdxunit;
	}
	
	public double getUtilhdx() {
		return utilhdx;
	}
	public void setUtilhdx(double utilhdx) {
		this.utilhdx = utilhdx;
	}
	public double getUtilhdxperc() {
		return utilhdxperc;
	}
	public void setUtilhdxperc(double utilhdxperc) {
		this.utilhdxperc = utilhdxperc;
	}
	public double getDiscardsperc() {
		return discardsperc;
	}
	public void setDiscardsperc(double discardsperc) {
		this.discardsperc = discardsperc;
	}
	public double getErrorsperc() {
		return errorsperc;
	}
	public void setErrorsperc(double errorsperc) {
		this.errorsperc = errorsperc;
	}
	public String getPercunit() {
		return percunit;
	}
	public void setPercunit(String percunit) {
		this.percunit = percunit;
	}
	public String getUtilhdxflag() {
		return utilhdxflag;
	}
	public void setUtilhdxflag(String utilhdxflag) {
		this.utilhdxflag = utilhdxflag;
	}
	public String getRecover() {
		return recover;
	}
	public void setRecover(String recover) {
		this.recover = recover;
	}
	public String getIfspeed() {
		return ifspeed;
	}
	public void setIfspeed(String ifspeed) {
		this.ifspeed = ifspeed;
	}
	
	
	
	
	
	
}
