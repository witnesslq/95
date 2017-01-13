package com.dhcc.bussiness.sxydidc.customer95.config.actions;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dhcc.bussiness.sxydidc.customer95.models.Customer;

public class UpdateCustomerActionTest {

	static UpdateCustomerAction action;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		action = new UpdateCustomerAction();
	}

	@Test
	public void testValidate() {

		Customer customer = new Customer();
		customer.setCustomerName("");
		action.setCustomer(customer);
		action.validate();
		System.out.println(action);;
		action.clearErrors();
		
		customer.setCustomerId("lldjfie");
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

		customer.setCustomerId("djfjio");
		customer.setCustomerName(null);
		action.setCustomer(customer);
		action.validate();
		System.out.println(action);;
		action.clearErrors();

		action.setCustomer(null);
		action.validate();
		System.out.println(action);;
		action.clearErrors();

		customer.setCustomerId("djfjio");
		customer.setCustomerName("jldfji");
		action.setCustomer(customer);
		action.validate();
		System.out.println(action);;
		action.clearErrors();
	}

	@Test
	public void testExecute() {
		Customer customer = new Customer();
		customer.setCustomerId("fc659b6d-7073-46a5-848b-9a69e1b1b200");
		customer.setCustomerName("jji");
		action.setCustomer(customer);
		try {
			action.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(action);;
	}

}
