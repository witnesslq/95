package com.dhcc.bussiness.sxydidc.customer95.dao;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dhcc.bussiness.sxydidc.customer95.models.Customer;

public class CustomerDaoTest {

	static CustomerDao dao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = new CustomerDao();
	}

	@Test
	public void testDeleteAll() {
		dao.deleteAll();
	}

	@Test
	public void testSaveMany() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryCustomerStatistic(){
		System.out.println(dao.queryCustomerStatistic());
	}
	
	@Test
	public void testQueryCustomerStatisticForGatherInterface(){
		System.out.println(dao.queryCustomerStatisticForGatherInterface());
	}
	
	@Test
	public void testQueryWithPatternBy(){
		Customer customer = new Customer();
		customer.setCustomerName("公司");
		System.out.println(dao.queryWithPatternBy(customer));
	}

}
