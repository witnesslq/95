package com.dhcc.bussiness.sxydidc.customer95.config.services;

import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.dhcc.bussiness.sxydidc.alarm.HibernateUtil;
import com.dhcc.bussiness.sxydidc.customer95.config.dao.CustomerDao;
import com.dhcc.bussiness.sxydidc.customer95.models.Customer;

@Configuration
@ImportResource("classpath:applicationContext.xml")
@ComponentScan(basePackageClasses=CustomerDao.class)
public class CustomerService {

	private static final Log log = LogFactory.getLog(CustomerService.class);
	
	@Autowired
	private CustomerDao dao;
	@Transactional
	public void saveOrUpdate(Customer customer){
		
		/*
		 * 没有ID认为是新用户，有ID的话是更改老用户
		 */
		if(customer.getCustomerId() == null)
			customer.setCustomerId(UUID.randomUUID().toString());
	
	try{
		dao.queryOne(customer);
	}catch(IllegalArgumentException e){
		log.info(e);
		dao.saveOrUpdate(customer);
	}
		
	}
	
	/*
	 * 检查客户是否存在
	 */
	public boolean has(Customer customer){
		CustomerDao dao = new CustomerDao();
		try{
			dao.queryOne(customer);
			return true;
		}catch(IllegalArgumentException e){
			log.info(e.getMessage());
			return false;
		}
	}
}
