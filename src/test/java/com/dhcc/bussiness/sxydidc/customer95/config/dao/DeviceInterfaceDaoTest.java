package com.dhcc.bussiness.sxydidc.customer95.config.dao;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dhcc.bussiness.sxydidc.alarm.actions.PaginationData;
import com.dhcc.bussiness.sxydidc.customer95.config.models.DeviceDetail;
import com.dhcc.bussiness.sxydidc.customer95.config.models.InterfaceDetail;
import com.dhcc.bussiness.sxydidc.customer95.models.Customer;
import com.dhcc.bussiness.sxydidc.quality.models.TopoHostNode;

public class DeviceInterfaceDaoTest {

	static DeviceInterfaceDao dao ;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = new DeviceInterfaceDao();
	}

	@Test
	public void testQueryBoundedInterfaceBy(){
		TopoHostNode host = new TopoHostNode();
		host.setIpAddress("183.203.0.111");
		System.out.println(dao.queryBoundedInterfaceBy(host));
	}
	@Test
	public void testQueryUnBoundedInterfaceBy(){
		TopoHostNode host = new TopoHostNode();
		host.setIpAddress("183.203.0.17");
		System.out.println(dao.queryUnboundedInterfaceBy(host));
	}
	
	@Test
	public void testQueryPagingListBy(){
		PaginationData<DeviceDetail> data = new PaginationData();
		dao.queryPagingListBy(data);
		System.out.println(data);
	}
	
	@Test
	public void testQueryUnboundedDeviceBy(){
		PaginationData<DeviceDetail> data = new PaginationData();
		
		System.out.println(dao.queryUnboundedDeviceBy(data));
	}
	
	@Test
	public void testQueryBoundedDeviceBy(){
		PaginationData<DeviceDetail> data = new PaginationData();
		Customer customer = new Customer("f90ad976-6a07-4d55-a951-4568abe23a02");
		System.out.println(dao.queryBoundedDeviceBy(data,customer));
	}
}
