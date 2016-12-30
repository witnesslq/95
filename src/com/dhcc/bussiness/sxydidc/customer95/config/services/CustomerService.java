package com.dhcc.bussiness.sxydidc.customer95.config.services;

import java.util.UUID;

import com.dhcc.bussiness.sxydidc.customer95.config.dao.CustomerDao;
import com.dhcc.bussiness.sxydidc.customer95.models.Customer;

public class CustomerService {

	public void saveOrUpdate(Customer customer){
		
		/*
		 * 没有ID认为是新用户，有ID的话是更改老用户
		 */
		if(customer.getCustomerId() == null)
			customer.setCustomerId(UUID.randomUUID().toString());
		CustomerDao dao = new CustomerDao();
		dao.saveOrUpdate(customer);
	}
}
