package com.dhcc.bussiness.sxydidc.customer95.config.dao;

import static org.junit.Assert.*;

import java.util.UUID;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.dhcc.bussiness.sxydidc.customer95.models.Customer;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CustomerDaoTest {

	@Resource(name="customerConfigDao")
	 CustomerDao dao;

	@Test
	public void testQueryBy() {
		Customer customer = new Customer();
		customer.setCustomerId("8154c1ea-b8c7-4c51-8d0a-ea06d71a87b6");
		System.out.println(dao.queryBy(customer));
	}

	@Test
	@Transactional
	public void testSaveOrUpdate(){
		Customer customer = new Customer();
		customer.setCustomerId(UUID.randomUUID().toString());
		customer.setCustomerName("东华软件股份公司");
		dao.saveOrUpdate(customer);
	}
	
	@Test
	@Ignore
	public void testSaveOrUpdateForUpdate(){
		Customer customer = new Customer();
		customer.setCustomerId(UUID.randomUUID().toString());
		customer.setCustomerName("东华软件股份公司");
		dao.saveOrUpdate(customer);
		
		customer.setCustomerName("东华");
		dao.saveOrUpdate(customer);
	}
	
	@Test
	public void testDelete(){
		Customer customer = new Customer();
		customer.setCustomerId("4ab27414-c8de-48aa-8141-06ced4665815");
		
		dao.delete(customer);
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	@Transactional
	public void testQueryOneNotExistThisCustomer(){
		Customer customer = new Customer();
		customer.setCustomerName("test1");
		dao.queryOne(customer);
	}
	
}
