package com.dhcc.bussiness.sxydidc.customer95.config.actions;

import org.junit.BeforeClass;
import org.junit.Test;

public class GetUnboundedDeviceDetailActionTest {

	static GetUnboundedDeviceDetailAction action;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		action = new GetUnboundedDeviceDetailAction();
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

}
