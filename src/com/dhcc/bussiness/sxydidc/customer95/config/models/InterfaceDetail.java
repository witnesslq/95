package com.dhcc.bussiness.sxydidc.customer95.config.models;

import com.dhcc.bussiness.sxydidc.quality.models.TopoInterface;

/*
 * 端口细节
 * 端口基本信息，包括 名称、流速、状态、描述
 * 端口绑定关系，包括 
 */
public class InterfaceDetail {

	private DeviceInterface basic;
	/**
	 * @return
	 * @see com.dhcc.bussiness.sxydidc.customer95.config.models.DeviceInterface#getIfSpeed()
	 */
	public String getIfSpeed() {
		return basic.getIfSpeed();
	}
	/**
	 * @return
	 * @see com.dhcc.bussiness.sxydidc.customer95.config.models.DeviceInterface#getIfStatus()
	 */
	public String getIfStatus() {
		return basic.getIfStatus();
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "InterfaceDetail [basic=" + basic + ", bound=" + bound + "]";
	}
	/**
	 * @return
	 * @see com.dhcc.bussiness.sxydidc.customer95.config.models.DeviceInterface#getIfDescr()
	 */
	public String getIfDescr() {
		return basic.getIfDescr();
	}
	/**
	 * @return
	 * @see com.dhcc.bussiness.sxydidc.customer95.config.models.DeviceInterface#getId()
	 */
	public long getId() {
		return basic.getId();
	}
	/**
	 * @return
	 * @see com.dhcc.bussiness.sxydidc.customer95.config.models.DeviceInterface#getNodeId()
	 */
	public String getNodeId() {
		return basic.getNodeId();
	}
	/**
	 * @return
	 * @see com.dhcc.bussiness.sxydidc.customer95.config.models.DeviceInterface#getIfIndex()
	 */
	public String getIfIndex() {
		return basic.getIfIndex();
	}
	/**
	 * @return
	 * @see com.dhcc.bussiness.sxydidc.customer95.config.models.DeviceInterface#getIfDesc()
	 */
	public String getIfDesc() {
		return basic.getIfDesc();
	}
	/**
	 * @return
	 * @see com.dhcc.bussiness.sxydidc.quality.models.TopoInterface#getStartTime()
	 */
	public String getStartTime() {
		return bound.getStartTime();
	}
	/**
	 * @return
	 * @see com.dhcc.bussiness.sxydidc.quality.models.TopoInterface#getEndTime()
	 */
	public String getEndTime() {
		return bound.getEndTime();
	}
	/**
	 * @return
	 * @see com.dhcc.bussiness.sxydidc.quality.models.TopoInterface#getCustomerId()
	 */
	public String getCustomerId() {
		return bound.getCustomerId();
	}
	private TopoInterface bound;
	
	/**
	 * 
	 */
	public InterfaceDetail() {
		super();
		this.basic = new DeviceInterface();
		this.bound = new TopoInterface();
	}

	/**
	 * @param ifSpeed
	 * @see com.dhcc.bussiness.sxydidc.customer95.config.models.DeviceInterface#setIfSpeed(java.lang.String)
	 */
	public void setIfSpeed(String ifSpeed) {
		basic.setIfSpeed(ifSpeed);
	}
	/**
	 * @param ifStatus
	 * @see com.dhcc.bussiness.sxydidc.customer95.config.models.DeviceInterface#setIfStatus(java.lang.String)
	 */
	public void setIfStatus(String ifStatus) {
		basic.setIfStatus(ifStatus);
	}
	/**
	 * @param ifDescr
	 * @see com.dhcc.bussiness.sxydidc.customer95.config.models.DeviceInterface#setIfDescr(java.lang.String)
	 */
	public void setIfDescr(String ifDescr) {
		basic.setIfDescr(ifDescr);
	}
	/**
	 * @param id
	 * @see com.dhcc.bussiness.sxydidc.customer95.config.models.DeviceInterface#setId(long)
	 */
	public void setId(long id) {
		basic.setId(id);
	}
	/**
	 * @param nodeId
	 * @see com.dhcc.bussiness.sxydidc.customer95.config.models.DeviceInterface#setNodeId(java.lang.String)
	 */
	public void setNodeId(String nodeId) {
		basic.setNodeId(nodeId);
	}
	/**
	 * @param ifIndex
	 * @see com.dhcc.bussiness.sxydidc.customer95.config.models.DeviceInterface#setIfIndex(java.lang.String)
	 */
	public void setIfIndex(String ifIndex) {
		basic.setIfIndex(ifIndex);
	}
	/**
	 * @param ifDesc
	 * @see com.dhcc.bussiness.sxydidc.customer95.config.models.DeviceInterface#setIfDesc(java.lang.String)
	 */
	public void setIfDesc(String ifDesc) {
		basic.setIfDesc(ifDesc);
	}
	/**
	 * @param startTime
	 * @see com.dhcc.bussiness.sxydidc.quality.models.TopoInterface#setStartTime(java.lang.String)
	 */
	public void setStartTime(String startTime) {
		bound.setStartTime(startTime);
	}
	/**
	 * @param endTime
	 * @see com.dhcc.bussiness.sxydidc.quality.models.TopoInterface#setEndTime(java.lang.String)
	 */
	public void setEndTime(String endTime) {
		bound.setEndTime(endTime);
	}
	/**
	 * @param customerId
	 * @see com.dhcc.bussiness.sxydidc.quality.models.TopoInterface#setCustomerId(java.lang.String)
	 */
	public void setCustomerId(String customerId) {
		bound.setCustomerId(customerId);
	}
	
}
