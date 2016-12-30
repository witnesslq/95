package com.dhcc.bussiness.sxydidc.customer95.config.actions;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dhcc.bussiness.sxydidc.customer95.models.Customer;
import com.dhcc.bussiness.sxydidc.quality.models.TopoHostNode;

public class DeviceInterfaceActionTest {

	static DeviceInterfaceAction action;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		action = new DeviceInterfaceAction();
	}

	@Test
	public void testDeleteAllInterfaceForThisCustomer() {
		Customer customer =new Customer();
		action.setCustomer(customer);
		try {
			action.deleteAllInterfaceForThisCustomer();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testDeleteDeviceInterfaceForThisCustomer() {
		Customer customer =new Customer();
		action.setCustomer(customer);
		TopoHostNode host = new TopoHostNode();
		action.setHost(host);
		try {
			action.deleteDeviceInterfaceForThisCustomer();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
