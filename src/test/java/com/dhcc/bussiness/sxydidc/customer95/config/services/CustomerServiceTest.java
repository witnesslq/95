package com.dhcc.bussiness.sxydidc.customer95.config.services;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.dhcc.bussiness.sxydidc.alarm.HibernateUtil;
import com.dhcc.bussiness.sxydidc.customer95.models.Customer;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes={CustomerService.class})
public class CustomerServiceTest {

	@Autowired
	private  CustomerService service ;

	@Test
	@Transactional
	@Commit
	public void testSaveOrUpdate() {
		Customer customer = new Customer();
		customer.setCustomerName("不存在的用户");
		service.saveOrUpdate(customer);
	}

	@Test
	public void testHas(){
		Customer customer = new Customer();
		customer.setCustomerName("计划表");
		
		System.out.println(service.has(customer));
	}
}
