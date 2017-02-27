package com.dhcc.bussiness.sxydidc.customer95.services;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dhcc.bussiness.sxydidc.customer95.models.CustomerSummary;

public class CustomerSummaryServiceTest {

	static CustomerSummaryService cs;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		cs = new CustomerSummaryService();
	}

	@Test
	public void testAssembleCustomerSummary() {
		System.out.println(cs.assembleCustomerSummary());
	}
	
	@Test
	public void testAsembleCustomerSummaryForGatherInterface(){
		System.out.println(cs.assembleCustomerSummaryForGatherInterface());
	}
}
