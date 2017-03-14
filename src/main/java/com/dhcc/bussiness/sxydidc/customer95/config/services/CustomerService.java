package com.dhcc.bussiness.sxydidc.customer95.config.services;

import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dhcc.bussiness.sxydidc.customer95.config.dao.CustomerDao;
import com.dhcc.bussiness.sxydidc.customer95.models.Customer;


@Component
public class CustomerService {

	private static final Log log = LogFactory.getLog(CustomerService.class);

	@Autowired
	private CustomerDao dao;

	@Transactional
	public void saveOrUpdate(Customer customer) {

		/*
		 * 没有ID认为是新用户，有ID的话是更改老用户
		 */
		if (customer.getCustomerId() == null)
			customer.setCustomerId(UUID.randomUUID().toString());

		try {
			dao.queryOne(customer);
		} catch (IllegalArgumentException e) {
			log.info(e);
			dao.saveOrUpdate(customer);
			return;
		}
		throw new IllegalArgumentException("已存在名称为 "
				+ customer.getCustomerName() + " 客户");
	}

	/*
	 * 检查客户是否存在
	 */
	@Transactional
	public boolean has(Customer customer) {
		try {
			dao.queryOne(customer);
			return true;
		} catch (IllegalArgumentException e) {
			log.info(e.getMessage());
			return false;
		}
	}
}
