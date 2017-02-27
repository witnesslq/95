package com.dhcc.bussiness.sxydidc.customer95.models;

import com.dhcc.bussiness.sxydidc.quality.models.Portips;

public class CustomerSummary extends Customer{

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CustomerSummary [ipCount=" + ipCount + ", alarmCount="
				+ alarmCount + ", portips=" + portips + ", portCount="
				+ portCount + ", getCustomerId()=" + getCustomerId()
				+ ", getCustomerName()=" + getCustomerName() + "]";
	}
	long ipCount;
	long alarmCount;
	Portips portips;
	long portCount;
	/**
	 * @return the portCount
	 */
	public long getPortCount() {
		return portCount;
	}
	/**
	 * @param portCount the portCount to set
	 */
	public void setPortCount(long portCount) {
		this.portCount = portCount;
	}
	/**
	 * @return the portips
	 */
	public Portips getPortips() {
		return portips;
	}
	/**
	 * @param portips the portips to set
	 */
	public void setPortips(Portips portips) {
		this.portips = portips;
	}
	/**
	 * @return the ipCount
	 */
	public long getIpCount() {
		return ipCount;
	}
	/**
	 * @param ipCount the ipCount to set
	 */
	public void setIpCount(long ipCount) {
		this.ipCount = ipCount;
	}
	/**
	 * @return the alarmCount
	 */
	public long getAlarmCount() {
		return alarmCount;
	}
	/**
	 * @param alarmCount the alarmCount to set
	 */
	public void setAlarmCount(long alarmCount) {
		this.alarmCount = alarmCount;
	}
	
}
