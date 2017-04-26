package com.dhcc.bussiness.sxydidc.customer95.config.services;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dhcc.bussiness.sxydidc.quality.models.TopoHostNode;

public class UnboundedDeviceInterfaceServiceTest {

	static DeviceInterfaceServiceSupport service;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		service = new UnboundedDeviceInterfaceService();
	}

	@Test
	public void testQueryBy() {
		TopoHostNode host = new TopoHostNode();
		host.setIpAddress("183.203.0.111");
		System.out.println(service.queryBy(host));
	}

}
