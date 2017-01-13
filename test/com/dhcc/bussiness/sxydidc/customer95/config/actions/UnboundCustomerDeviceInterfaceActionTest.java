package com.dhcc.bussiness.sxydidc.customer95.config.actions;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dhcc.bussiness.sxydidc.customer95.models.Customer;
import com.dhcc.bussiness.sxydidc.quality.models.TopoHostNode;

public class UnboundCustomerDeviceInterfaceActionTest {

	static UnboundCustomerDeviceInterfaceAction action;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		action = new UnboundCustomerDeviceInterfaceAction();
	}

	@Test
	public void testExecute() {
		Customer customer =new Customer("5ecf8c9f-2212-42d6-990b-37b8aaf16edb");
		action.setCustomer(customer);
		TopoHostNode host = new TopoHostNode();
		host.setIpAddress("183.203.0.104");
		action.setHost(host);
		try {
			action.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
