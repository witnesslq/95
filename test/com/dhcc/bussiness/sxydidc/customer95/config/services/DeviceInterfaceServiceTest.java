package com.dhcc.bussiness.sxydidc.customer95.config.services;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dhcc.bussiness.sxydidc.alarm.actions.PaginationData;
import com.dhcc.bussiness.sxydidc.customer95.config.models.DeviceDetail;

public class DeviceInterfaceServiceTest {

	static DeviceInterfaceService service;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		service = new DeviceInterfaceService();
	}


	@Test
	public void testPagingList() {
		PaginationData<DeviceDetail> data = new PaginationData(1, 5);
		service.paging(data);
		System.out.println(data);
	}

}
