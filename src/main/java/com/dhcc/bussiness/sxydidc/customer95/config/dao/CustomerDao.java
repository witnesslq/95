package com.dhcc.bussiness.sxydidc.customer95.config.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.dhcc.bussiness.sxydidc.alarm.HibernateUtil;
import com.dhcc.bussiness.sxydidc.customer95.config.models.CustomerSummary;
import com.dhcc.bussiness.sxydidc.customer95.models.Customer;

@Component
public class CustomerDao {

	public List<CustomerSummary>  queryAll(){
		Session session= HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		
		try{
			Query query = session.createSQLQuery("select customer_id,customer_name,'下线' as status,0 as port_count from customer c where c.customer_id not in (select customer_id from topo_interface t where t.customer_id = c.customer_id and endtime is null )"
					+ "union "
					+ "select c.customer_id,customer_name,'在线' as stauts,count(*) as port_count from customer c join topo_interface t on c.customer_id = t.customer_id and endtime is null group by c.customer_id,customer_name ");
			List<Object[] > rows = query.list();
			
			List<CustomerSummary> list = new ArrayList<CustomerSummary>();
			
			for(Object[] row:rows){
				CustomerSummary customerSummary = new CustomerSummary();
				Customer customer = new Customer();
				customer.setCustomerId((String)row[0]);
				customer.setCustomerName((String)row[1]);
				customerSummary.setCustomer(customer);
				customerSummary.setStatus((String)row[2]);
				customerSummary.setPortCount(((BigDecimal)row[3]).intValue());
				
				list.add(customerSummary);
			}
			
			transaction.commit();
			return list;
		}catch(HibernateException e){
			transaction.rollback();
			e.printStackTrace();
		}
		
		return Collections.emptyList();
	}
	
	@Autowired
	private SessionFactory sessionFactory;
	
	/*
	 * 添加或者更新客户信息
	 */
	public void saveOrUpdate(Customer customer) {

		Session session= sessionFactory.getCurrentSession();
			session.saveOrUpdate(customer);
	}
	
	/*
	 * 某单个客户所占端口数量
	 */
	public CustomerSummary  queryBy(Customer customer){
		Session session = null;
		try{
		session = sessionFactory.getCurrentSession();
		}catch(Exception e){
			e.printStackTrace();
		}
		try{
			Query query = session.createQuery("select count(*) as portCount from  TopoInterface t  where t.customerId=:customerId and t.endTime is null")
					.setString("customerId", customer.getCustomerId());
			
			List<Long> rows = query.list();
			
			CustomerSummary customerSummary  = new CustomerSummary();
			
			customerSummary.setPortCount(rows.get(0).intValue());
			
			return customerSummary;
		}catch(HibernateException e){
			
			e.printStackTrace();
			throw e;
		}
	}
	
	/*
	 * 删除客户
	 */
	public void delete(Customer customer){


		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		
		try{
			 session.delete(customer);
			Query query = session.createQuery("delete from TopoInterface t where t.id.customerId=:customerId")
			 .setString("customerId", customer.getCustomerId());
			 
			query.executeUpdate();
			 
			transaction.commit();
		}catch(RuntimeException e){
			try{
				transaction.rollback();
			}catch(HibernateException ex){
				ex.printStackTrace();
			}

			e.printStackTrace();
		}
	}
	
	/*
	 * 查询客户
	 */
	public Customer queryOne(Customer customer){

		Session session = null;
		try{
		session = sessionFactory.getCurrentSession();
		}catch(Exception e){
			e.printStackTrace();
		}

		try{
			Query query = session.createQuery("select new Customer(c.customerId,c.customerName) from Customer c where c.customerName=:customerName")
					.setString("customerName", customer.getCustomerName());
			
			List<Customer> list = query.list();
			
			if(list.size()>0)
				return list.get(0);
			else
				throw new IllegalArgumentException("没有名称为 "+customer.getCustomerName()+" 客户");
		}catch(HibernateException e){
			e.printStackTrace();
			throw e;
		}
	}
}
