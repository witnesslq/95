package com.dhcc.bussiness.sxydidc.customer95.config.actions;

import java.util.List;

import com.dhcc.bussiness.sxydidc.alarm.actions.PaginationData;
import com.dhcc.bussiness.sxydidc.customer95.config.models.DeviceDetail;
import com.dhcc.bussiness.sxydidc.customer95.config.services.BoundedDeviceInterfaceService;
import com.dhcc.bussiness.sxydidc.customer95.config.services.DeviceInterfaceServiceSupport;
import com.dhcc.bussiness.sxydidc.customer95.config.services.UnboundedDeviceInterfaceService;
import com.dhcc.bussiness.sxydidc.quality.models.TopoHostNode;
import com.opensymphony.xwork2.ActionSupport;

public class GetSomeDeviceInterfaceAction extends PaginationData<DeviceDetail> {


	private List<TopoHostNode> hostList;
	
	/**
	 * @return the hostList
	 */
	public List<TopoHostNode> getHostList() {
		return hostList;
	}

	/**
	 * @param hostList the hostList to set
	 */
	public void setHostList(List<TopoHostNode> hostList) {
		this.hostList = hostList;
	}
	/* 某些设备详情
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		DeviceInterfaceServiceSupport service =new UnboundedDeviceInterfaceService();
		this.setList(service.getDeviceDetailList(this.hostList));
		
		return super.SUCCESS;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GetSomeDeviceInterfaceAction [hostList=" + hostList
				+ ", getActionErrors()=" + getActionErrors()
				+ ", getFieldErrors()=" + getFieldErrors() + "]";
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#validate()
	 */
	@Override
	public void validate() {/*
		// TODO Auto-generated method stub
		if(this.hostList == null){
			this.addFieldError("hostList", "没有指定主机");
		}else{
			for(TopoHostNode host:hostList){
				if(host == null||host.getIpAddress() ==null){
					this.addFieldError("hostList", "没有指定主机");
					break;
				}
			}
		}
	*/}
}
