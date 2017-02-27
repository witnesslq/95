package com.dhcc.bussiness.sxydidc.customer95.config.actions;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.dhcc.bussiness.sxydidc.customer95.models.Customer;

public class SaveCustomerActionTest {

	static SaveCustomerAction action;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		action = new SaveCustomerAction();
		
	}

	@Test
	public void testValidate() {
		Customer customer = new Customer();
		customer.setCustomerName("");
		action.setCustomer(customer);
		action.validate();
		System.out.println(action);;
		action.clearErrors();
		
		customer.setCustomerName("计划表");
		action.setCustomer(customer);
		action.validate();
		System.out.println(action);;
		action.clearErrors();

		customer.setCustomerName(null);
		action.setCustomer(customer);
		action.validate();
		System.out.println(action);;
		action.clearErrors();

		action.setCustomer(null);
		action.validate();
		System.out.println(action);;
		action.clearErrors();

		customer.setCustomerName("计表");
		action.setCustomer(customer);
		action.validate();
		System.out.println(action);;
		action.clearErrors();
	}
	

	@Test
	@Ignore
	public void testSave() {
		fail("Not yet implemented"); // TODO
	}

}
