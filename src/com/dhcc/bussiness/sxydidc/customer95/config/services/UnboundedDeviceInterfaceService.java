package com.dhcc.bussiness.sxydidc.customer95.config.services;

import java.util.List;

import com.dhcc.bussiness.sxydidc.alarm.actions.PaginationData;
import com.dhcc.bussiness.sxydidc.customer95.config.dao.DeviceInterfaceDao;
import com.dhcc.bussiness.sxydidc.customer95.config.models.DeviceDetail;
import com.dhcc.bussiness.sxydidc.customer95.config.models.InterfaceDetail;
import com.dhcc.bussiness.sxydidc.customer95.models.Customer;
import com.dhcc.bussiness.sxydidc.quality.models.TopoHostNode;

public class UnboundedDeviceInterfaceService extends
		DeviceInterfaceServiceSupport {

	@Override
	List<InterfaceDetail> queryBy(TopoHostNode host) {
		// TODO Auto-generated method stub
		DeviceInterfaceDao dao = new DeviceInterfaceDao();
		
		return dao.queryUnboundedInterfaceBy(host);
	}

	@Override
	public void paging(PaginationData<DeviceDetail> data, Customer customer) {
		DeviceInterfaceDao dao = new DeviceInterfaceDao();
		List<TopoHostNode> hostList = dao.queryUnboundedDeviceBy(data);
		
		data.setList(this.getDeviceDetailList(hostList));
	}
}
