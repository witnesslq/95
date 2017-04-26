package com.dhcc.bussiness.sxydidc.customer95.config.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.dhcc.bussiness.sxydidc.alarm.actions.PaginationData;
import com.dhcc.bussiness.sxydidc.customer95.config.dao.DeviceInterfaceDao;
import com.dhcc.bussiness.sxydidc.customer95.config.models.DeviceDetail;
import com.dhcc.bussiness.sxydidc.customer95.config.models.InterfaceDetail;
import com.dhcc.bussiness.sxydidc.customer95.models.Customer;
import com.dhcc.bussiness.sxydidc.quality.models.TopoHostNode;

public abstract class DeviceInterfaceServiceSupport {

	public DeviceInterfaceServiceSupport() {
		super();
	}
	
	public abstract void paging(PaginationData<DeviceDetail> data,Customer customer);

	public List<DeviceDetail> getDeviceDetailList(List<TopoHostNode> hostList) {
	
		DeviceInterfaceDao dao = new DeviceInterfaceDao();
		List<DeviceDetail> deviceList = new ArrayList();
		
		for (TopoHostNode host : hostList) {
	
			List interfaces = this.queryBy(host);
			Set interfaceSet = new LinkedHashSet();
			interfaceSet.addAll(interfaces);
	
			deviceList.add(new DeviceDetail(host, interfaceSet));
		}
		
		return deviceList;
	}
	
	abstract List<InterfaceDetail> queryBy(TopoHostNode host);
}