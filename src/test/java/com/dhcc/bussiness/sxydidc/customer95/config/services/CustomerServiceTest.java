package com.dhcc.bussiness.sxydidc.customer95.config.services;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dhcc.bussiness.sxydidc.customer95.models.Customer;

public class CustomerServiceTest {

	static CustomerService service ;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		service = new CustomerService();
	}

	@Test
	public void testSaveOrUpdate() {
		Customer customer = new Customer();
		System.out.println(customer);
	}

	@Test
	public void testHas(){
		Customer customer = new Customer();
		customer.setCustomerName("计划表");
		
		System.out.println(service.has(customer));
	}
}
