package com.dhcc.bussiness.sxydidc.customer95.config.actions;

import java.util.List;

import com.dhcc.bussiness.sxydidc.alarm.actions.PaginationData;
import com.dhcc.bussiness.sxydidc.customer95.config.models.DeviceDetail;
import com.dhcc.bussiness.sxydidc.customer95.config.services.BoundedDeviceInterfaceService;
import com.dhcc.bussiness.sxydidc.customer95.config.services.DeviceInterfaceServiceSupport;
import com.dhcc.bussiness.sxydidc.customer95.config.services.UnboundedDeviceInterfaceService;
import com.dhcc.bussiness.sxydidc.customer95.models.Customer;
import com.dhcc.bussiness.sxydidc.quality.models.TopoHostNode;
import com.dhcc.bussiness.sxydidc.quality.models.TopoInterface;
import com.opensymphony.xwork2.ActionSupport;

public class GetUnboundedDeviceDetailAction extends PaginationData<DeviceDetail>{

	/* 分页的设备详情
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String queryPagingDeviceDetail() throws Exception {
		// TODO Auto-generated method stub
		DeviceInterfaceServiceSupport service = new UnboundedDeviceInterfaceService();
		service.paging(this, null);
		return super.SUCCESS;
	}
}
