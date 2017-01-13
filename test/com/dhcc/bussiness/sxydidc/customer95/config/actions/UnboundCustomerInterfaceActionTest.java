package com.dhcc.bussiness.sxydidc.customer95.config.actions;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dhcc.bussiness.sxydidc.customer95.models.Customer;

public class UnboundCustomerInterfaceActionTest {

	static UnboundCustomerInterfaceAction action;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		action = new UnboundCustomerInterfaceAction();
	}

	@Test
	public void testValidate() {
		action.validate();
		System.out.println(action);
	}

	@Test
	public void testExecute() {
		Customer customer = new Customer("5ecf8c9f-2212-42d6-990b-37b8aaf16edb");
		action.setCustomer(customer);
		try {
			action.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
