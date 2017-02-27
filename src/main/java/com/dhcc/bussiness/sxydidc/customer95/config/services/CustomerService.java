package com.dhcc.bussiness.sxydidc.customer95.config.services;

import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.dhcc.bussiness.sxydidc.customer95.config.dao.CustomerDao;
import com.dhcc.bussiness.sxydidc.customer95.models.Customer;

public class CustomerService {

	private static final Log log = LogFactory.getLog(CustomerService.class);
	public void saveOrUpdate(Customer customer){
		
		/*
		 * 没有ID认为是新用户，有ID的话是更改老用户
		 */
		if(customer.getCustomerId() == null)
			customer.setCustomerId(UUID.randomUUID().toString());
		CustomerDao dao = new CustomerDao();
		dao.saveOrUpdate(customer);
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
