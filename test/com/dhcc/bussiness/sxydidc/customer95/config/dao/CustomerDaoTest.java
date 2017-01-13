package com.dhcc.bussiness.sxydidc.customer95.config.dao;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dhcc.bussiness.sxydidc.customer95.models.Customer;

public class CustomerDaoTest {

	static CustomerDao dao;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao  = new CustomerDao();
	}

	@Test
	public void testQueryBy() {
		Customer customer = new Customer();
		customer.setCustomerId("8154c1ea-b8c7-4c51-8d0a-ea06d71a87b6");
		System.out.println(dao.queryBy(customer));
	}

	@Test
	public void testSaveOrUpdate(){
		Customer customer = new Customer();
		customer.setCustomerId(UUID.randomUUID().toString());
		customer.setCustomerName("东华软件股份公司");
		dao.saveOrUpdate(customer);
	}
	
	@Test
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
	
	@Test
	public void testQueryOne(){
		Customer customer = new Customer();
		customer.setCustomerName("计划表");
		System.out.println(dao.queryOne(customer));;
	}
	
}
