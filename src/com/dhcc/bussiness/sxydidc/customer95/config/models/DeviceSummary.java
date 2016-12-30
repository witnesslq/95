package com.dhcc.bussiness.sxydidc.customer95.config.models;

import com.dhcc.bussiness.sxydidc.quality.models.TopoHostNode;

public class DeviceSummary {
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DeviceSummary [host=" + host + ", portCount=" + portCount + "]";
	}
	/**
	 * @param host
	 * @param portCount
	 */
	public DeviceSummary(String ipAddress, int portCount) {
		super();
		this.host = new TopoHostNode(ipAddress);
		this.portCount = portCount;
	}
	private TopoHostNode host;
	private int portCount;
	/**
	 * @return the portCount
	 */
	public int getPortCount() {
		return portCount;
	}
	/**
	 * @param portCount the portCount to set
	 */
	public void setPortCount(int portCount) {
		this.portCount = portCount;
	}
	/**
	 * @return the host
	 */
	public TopoHostNode getHost() {
		return host;
	}
	/**
	 * @param host the host to set
	 */
	public void setHost(TopoHostNode host) {
		this.host = host;
	}
	
}
