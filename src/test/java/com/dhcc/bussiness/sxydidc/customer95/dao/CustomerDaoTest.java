package com.dhcc.bussiness.sxydidc.customer95.dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.dhcc.bussiness.sxydidc.customer95.models.Customer;

public class CustomerDaoTest {

	 CustomerDao dao;
	
	@Before
	public  void setUp() throws Exception {
		dao = new CustomerDao();
	}

	@Test
	@Ignore
	public void testDeleteAll() {
		dao.deleteAll();
	}

	@Test
	@Ignore
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
