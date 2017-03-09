package com.dhcc.bussiness.sxydidc.customer95.dao;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import com.dhcc.bussiness.sxydidc.customer95.models.Customer;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CustomerDaoTest {

	@Resource(name="customerDao")
	 CustomerDao dao;

	@Transactional
	@Test
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
