package com.dhcc.bussiness.sxydidc.quality.models;

// Generated 2016-11-18 9:47:54 by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.Date;

/**
 * Portips18320301 generated by hbm2java
 */
public class Portips implements java.io.Serializable {

	private BigDecimal id;
	private String ipaddress;
	private String restype;
	private String category;
	private String entity;
	private String subentity;
	private String utilhdx;
	private String utilhdxperc;
	private String discardsperc;
	private String errorsperc;
	private String collecttime;
	private String utilhdxunit;
	private String percunit;
	private String recover;

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Portips [id=" + id + ", ipaddress=" + ipaddress + ", restype="
				+ restype + ", category=" + category + ", entity=" + entity
				+ ", subentity=" + subentity + ", utilhdx=" + utilhdx
				+ ", utilhdxperc=" + utilhdxperc + ", discardsperc="
				+ discardsperc + ", errorsperc=" + errorsperc
				+ ", collecttime=" + collecttime + ", utilhdxunit="
				+ utilhdxunit + ", percunit=" + percunit + ", recover="
				+ recover + "]";
	}

	public Portips() {
	}

	public Portips(BigDecimal id) {
		this.id = id;
	}

	public Portips(String discardspec,String errorspec){
		this.discardsperc = discardspec;
		this.errorsperc = errorspec;
	}
	public Portips(BigDecimal id, String ipaddress, String restype,
			String category, String entity, String subentity, String utilhdx,
			String utilhdxperc, String discardsperc, String errorsperc,
			String collecttime, String utilhdxunit, String percunit,
			String recover) {
		this.id = id;
		this.ipaddress = ipaddress;
		this.restype = restype;
		this.category = category;
		this.entity = entity;
		this.subentity = subentity;
		this.utilhdx = utilhdx;
		this.utilhdxperc = utilhdxperc;
		this.discardsperc = discardsperc;
		this.errorsperc = errorsperc;
		this.collecttime = collecttime;
		this.utilhdxunit = utilhdxunit;
		this.percunit = percunit;
		this.recover = recover;
	}

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getIpaddress() {
		return this.ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public String getRestype() {
		return this.restype;
	}

	public void setRestype(String restype) {
		this.restype = restype;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getEntity() {
		return this.entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public String getSubentity() {
		return this.subentity;
	}

	public void setSubentity(String subentity) {
		this.subentity = subentity;
	}

	public String getUtilhdx() {
		return this.utilhdx;
	}

	public void setUtilhdx(String utilhdx) {
		this.utilhdx = utilhdx;
	}

	public String getUtilhdxperc() {
		return this.utilhdxperc;
	}

	public void setUtilhdxperc(String utilhdxperc) {
		this.utilhdxperc = utilhdxperc;
	}

	public String getDiscardsperc() {
		return this.discardsperc;
	}

	public void setDiscardsperc(String discardsperc) {
		this.discardsperc = discardsperc;
	}

	public String getErrorsperc() {
		return this.errorsperc;
	}

	public void setErrorsperc(String errorsperc) {
		this.errorsperc = errorsperc;
	}

	public String getCollecttime() {
		return this.collecttime;
	}

	public void setCollecttime(String collecttime) {
		this.collecttime = collecttime;
	}

	public String getUtilhdxunit() {
		return this.utilhdxunit;
	}

	public void setUtilhdxunit(String utilhdxunit) {
		this.utilhdxunit = utilhdxunit;
	}

	public String getPercunit() {
		return this.percunit;
	}

	public void setPercunit(String percunit) {
		this.percunit = percunit;
	}

	public String getRecover() {
		return this.recover;
	}

	public void setRecover(String recover) {
		this.recover = recover;
	}

}
