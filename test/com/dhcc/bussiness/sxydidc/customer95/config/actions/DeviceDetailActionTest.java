package com.dhcc.bussiness.sxydidc.customer95.config.actions;

import org.junit.BeforeClass;
import org.junit.Test;

public class DeviceDetailActionTest {

	static DeviceDetailAction action;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		action = new DeviceDetailAction();
	}

	@Test
	public void testQueryPagingDeviceDetail() {
		try {
			action.queryPagingDeviceDetail();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(action);
	}

	@Test
	public void testQuerySomeDeviceDetail() {
		try {
			action.querySomeDeviceDetail();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(action);
	}

}
