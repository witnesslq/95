package com.dhcc.bussiness.sxydidc.quality.actions;

import java.util.Calendar;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dhcc.bussiness.sxydidc.customer95.models.Customer;
import com.dhcc.bussiness.sxydidc.quality.services.DateRange;

public class TopoInterfaceActionTest {

	static TopoInterfaceAction action;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		action = new TopoInterfaceAction();
		Customer customer = new Customer();
		customer.setCustomerId("016997fd-2a7f-499c-ac71-f62e16be77be");
		action.setCustomer(customer);
		Calendar ca = Calendar.getInstance();
		ca.set(2017, 0, 20);
		action.setDate(ca.getTimeInMillis());
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
