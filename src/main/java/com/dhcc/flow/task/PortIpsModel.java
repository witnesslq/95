package com.dhcc.flow.task;


public class PortIpsModel {
	private String ipaddress; //ip��ַ
	private String entity;		//OutBandwidthUtilHdx��InBandwidthUtilHdx
	private String subentity;	//�˿�����
	private String collecttime; //�ɼ�ʱ��
	private String restype;   
	private String category;
	private String utilhdxunit;
	private double utilhdx;  //����
	private double utilhdxperc;  //����������
	private double discardsperc;// ������
	private double errorsperc;   //������
	private String percunit;     //�����ʵ�λ
	private String utilhdxflag;  //�Ƿ�Ϊ�쳣
	private String recover;      //���������
	private String ifspeed;     //����
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
