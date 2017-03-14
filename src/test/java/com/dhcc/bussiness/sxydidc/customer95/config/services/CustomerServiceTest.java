package com.dhcc.bussiness.sxydidc.customer95.config.services;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.dhcc.bussiness.sxydidc.customer95.models.Customer;
import com.dhcc.spring.config.ApplicationContextConfig;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes={ApplicationContextConfig.class})
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

	@Transactional
	@Test
	public void testHas(){
		Customer customer = new Customer();
		customer.setCustomerName("test");
		
		assertTrue(service.has(customer));
	}
}
