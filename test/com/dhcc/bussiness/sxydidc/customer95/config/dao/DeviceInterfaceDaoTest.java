package com.dhcc.bussiness.sxydidc.customer95.config.dao;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dhcc.bussiness.sxydidc.alarm.actions.PaginationData;
import com.dhcc.bussiness.sxydidc.customer95.config.models.DeviceDetail;
import com.dhcc.bussiness.sxydidc.quality.models.TopoHostNode;

public class DeviceInterfaceDaoTest {

	static DeviceInterfaceDao dao ;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = new DeviceInterfaceDao();
	}

	@Test
	public void testQueryBy(){
		TopoHostNode host = new TopoHostNode();
		host.setIpAddress("183.203.0.49");
		System.out.println(dao.queryBy(host));
	}
	
	@Test
	public void testQueryPagingListBy(){
		PaginationData<DeviceDetail> data = new PaginationData();
		dao.queryPagingListBy(data);
		System.out.println(data);
	}
}
