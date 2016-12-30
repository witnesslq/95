package com.dhcc.bussiness.sxydidc.customer95.config.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.dhcc.bussiness.sxydidc.alarm.actions.PaginationData;
import com.dhcc.bussiness.sxydidc.customer95.config.dao.DeviceInterfaceDao;
import com.dhcc.bussiness.sxydidc.customer95.config.models.DeviceDetail;
import com.dhcc.bussiness.sxydidc.quality.models.TopoHostNode;
import com.dhcc.bussiness.sxydidc.quality.models.TopoInterface;

public class DeviceInterfaceService {
	
	/*
	 * 分页的设备详细信息
	 * 详细信息包括，设备基本信息，设备端口占用信息
	 */
	public void paging(PaginationData<DeviceDetail> data) {
		DeviceInterfaceDao dao = new DeviceInterfaceDao();
		List<TopoHostNode> hostList = dao.queryPagingListBy(data);
		
		data.setList(this.getDeviceDetailList(hostList));
	}
	
	/*
	 * 某些设备的端口占用情况
	 */
	public List<DeviceDetail> getDeviceDetailList(List<TopoHostNode> hostList){

		DeviceInterfaceDao dao = new DeviceInterfaceDao();
		List<DeviceDetail> deviceList = new ArrayList();
		
		for (TopoHostNode host : hostList) {

			List<TopoInterface> interfaces = dao.queryBy(host);
			Set<TopoInterface> interfaceSet = new HashSet();
			interfaceSet.addAll(interfaces);

			deviceList.add(new DeviceDetail(host, interfaceSet));
		}
		
		return deviceList;
	}

}
