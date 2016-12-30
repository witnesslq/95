package com.dhcc.bussiness.sxydidc.customer95.config.actions;

import java.util.List;

import com.dhcc.bussiness.sxydidc.alarm.actions.PaginationData;
import com.dhcc.bussiness.sxydidc.customer95.config.models.DeviceDetail;
import com.dhcc.bussiness.sxydidc.customer95.config.services.DeviceInterfaceService;
import com.dhcc.bussiness.sxydidc.customer95.models.Customer;
import com.dhcc.bussiness.sxydidc.quality.models.TopoHostNode;
import com.dhcc.bussiness.sxydidc.quality.models.TopoInterface;
import com.opensymphony.xwork2.ActionSupport;

public class DeviceDetailAction extends PaginationData<DeviceDetail>{

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

	/* 分页的设备详情
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String queryPagingDeviceDetail() throws Exception {
		// TODO Auto-generated method stub
		DeviceInterfaceService service = new DeviceInterfaceService();
		service.paging(this);
		return super.SUCCESS;
	}

	/* 某些设备详情
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String querySomeDeviceDetail() throws Exception {
		// TODO Auto-generated method stub
		DeviceInterfaceService service =new DeviceInterfaceService();
		this.setList(service.getDeviceDetailList(this.hostList));
		
		return super.SUCCESS;
	}
}
