package com.dhcc.bussiness.sxydidc.customer95.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.dhcc.bussiness.sxydidc.alarm.HibernateUtil;
import com.dhcc.bussiness.sxydidc.customer95.models.Customer;

public class CustomerDao {

	private static final Log log = LogFactory.getLog(CustomerDao.class);

	private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	public void save(Customer customer){
		
	}
	
	/*
	 * 删除所有客户及相关联的合同，产品
	 */
	public boolean deleteAll(){

		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try{
			Query query = session.createQuery("delete from Customer");
			query.executeUpdate();
			transaction.commit();
			return true;
		}catch(RuntimeException e){
			transaction.rollback();
			log.error(e);
		}
		return false;
	}
	
	/*
	 * 持久化 客户
	 */
	public boolean saveMany(List<Customer> customerList){
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try{
			for(Customer customer:customerList){
				session.save(customer);
			}
			transaction.commit();
			return true;
		}catch(RuntimeException e){
			transaction.rollback();
			log.error(e);
		}
		return false;
	}
}
