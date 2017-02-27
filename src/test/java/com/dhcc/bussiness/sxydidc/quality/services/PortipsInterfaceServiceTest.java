package com.dhcc.bussiness.sxydidc.quality.services;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.dhcc.bussiness.sxydidc.customer95.models.Customer;

public class PortipsInterfaceServiceTest {

	static PortipsInterfaceService service;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		service = new PortipsInterfaceService();
	}

	@Test
	public void testFetchPortipsByCustomerStringLong() {

		Customer customer = new Customer();
		customer.setCustomerId("016997fd-2a7f-499c-ac71-f62e16be77be");
		
		SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd");
		Calendar ca = Calendar.getInstance();
		ca.set(2017, 0, 20);
	
		System.out.println(service.fetchPortipsBy(customer, "day", ca.getTime().getTime()));
	
	}

	@Test
	@Ignore
	public void testFetchPortipsByListOfTopoInterfaceStringLong() {
		fail("Not yet implemented"); // TODO
	}

}
