package com.dhcc.bussiness.sxydidc.quality.actions;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dhcc.bussiness.sxydidc.customer95.models.Customer;

public class TopoInterfaceActionTest {

	static TopoInterfaceAction action;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		action = new TopoInterfaceAction();
		action.setCustomer(new Customer("0767ec2c-1713-4a2b-94a8-0982b78e0caf"));
	}

	@Test
	public void testExecute() {
		try {
			action.execute();
			System.out.println(action.getList());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
