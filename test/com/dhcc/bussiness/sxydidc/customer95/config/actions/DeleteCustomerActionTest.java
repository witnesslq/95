package com.dhcc.bussiness.sxydidc.customer95.config.actions;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dhcc.bussiness.sxydidc.customer95.models.Customer;

public class DeleteCustomerActionTest {

	static DeleteCustomerAction action;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		action = new DeleteCustomerAction();
	}

	@Test
	public void testValidate() {
		Customer customer = new Customer();
		action.setCustomer(customer);
		action.validate();
		System.out.println(action);;
		action.clearErrors();
		
		action.setCustomer(null);
		action.validate();
		System.out.println(action);;
		action.clearErrors();
		
		customer.setCustomerId("djfoe");
		action.setCustomer(customer);
		action.validate();
		System.out.println(action);;
		action.clearErrors();
	}

	@Test
	public void testDelete() {
		Customer customer = new Customer();
		customer.setCustomerId("jojdojfe");
		action.setCustomer(customer);
		try {
			action.delete();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(action);;
		
	}

}
