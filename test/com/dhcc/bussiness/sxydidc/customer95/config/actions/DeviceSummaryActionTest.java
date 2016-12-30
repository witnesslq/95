package com.dhcc.bussiness.sxydidc.customer95.config.actions;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dhcc.bussiness.sxydidc.customer95.models.Customer;

public class DeviceSummaryActionTest {

	static DeviceSummaryAction action;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		action = new DeviceSummaryAction();
	}

	@Test
	public void testExecute() {
		Customer customer = new Customer("f90ad976-6a07-4d55-a951-4568abe23a02");
		action.setCustomer(customer);
		try {
			action.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(action.getList());
	}

}
