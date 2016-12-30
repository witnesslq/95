package com.dhcc.bussiness.sxydidc.customer95.config.models;

import java.util.Set;

import com.dhcc.bussiness.sxydidc.quality.models.TopoHostNode;
import com.dhcc.bussiness.sxydidc.quality.models.TopoInterface;

/*
 * 设备端口占用的详细情况，包括哪些端口被哪个客户占用、哪些端口不被占用
 * 不被占用的端口，它的客户ID是null
 */
public class DeviceDetail {

	private TopoHostNode device;
	private Set<TopoInterface> interfaces;
	/**
	 * @return the device
	 */
	public TopoHostNode getDevice() {
		return device;
	}
	/**
	 * 
	 */
	public DeviceDetail() {
		super();
	}
	/**
	 * @param device the device to set
	 */
	public void setDevice(TopoHostNode device) {
		this.device = device;
	}
	
	/**
	 * @param device
	 * @param interfaces
	 */
	public DeviceDetail(TopoHostNode device, Set<TopoInterface> interfaces) {
		super();
		this.device = device;
		this.interfaces = interfaces;
	}
	/**
	 * @return the interfaces
	 */
	public Set<TopoInterface> getInterfaces() {
		return interfaces;
	}
	/**
	 * @param interfaces the interfaces to set
	 */
	public void setInterfaces(Set<TopoInterface> interfaces) {
		this.interfaces = interfaces;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DeviceDetail [device=" + device + ", interfaces=" + interfaces
				+ "]";
	}
	
}
