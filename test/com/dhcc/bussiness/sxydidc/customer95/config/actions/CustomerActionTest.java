package com.dhcc.bussiness.sxydidc.customer95.config.actions;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.eclipse.jdt.internal.compiler.ast.AssertStatement;
import org.junit.BeforeClass;
import org.junit.Test;

import com.dhcc.bussiness.sxydidc.customer95.models.Customer;

public class CustomerActionTest {

	static CustomerAction action;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		action = new CustomerAction();
	}

	@Test
	public void testSaveOrUpdateForSave() {
		Customer customer = new Customer();
		customer.setCustomerName("东华软件");
		action.setCustomer(customer);
		try {
			Assert.assertEquals("success", action.saveOrUpdate());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
