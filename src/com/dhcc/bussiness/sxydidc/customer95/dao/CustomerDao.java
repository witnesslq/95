package com.dhcc.bussiness.sxydidc.customer95.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.dhcc.bussiness.sxydidc.alarm.HibernateUtil;
import com.dhcc.bussiness.sxydidc.customer95.models.Customer;
import com.dhcc.bussiness.sxydidc.customer95.models.CustomerSummary;
import com.dhcc.bussiness.sxydidc.customer95.models.ProductIp;

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
	
	/*
	 * 每个客户的IP统计、告警数量统计
	 */
	public List<CustomerSummary> queryCustomerStatistic(){

				Session session = sessionFactory.getCurrentSession();
				Transaction transaction = session.beginTransaction();
				try{
					Query query = session.createSQLQuery( "select customer_id,customer_name,ip_count,alarm_count from  (select customer_id,customer_name,0 as ip_count from customer c where not exists (select * from  ( select customer_id from ( select customer.customer_id,customer_name,product_ip.ip from customer  join contract on customer.customer_id=contract.customer_id  join product on product.contract_id = contract.contract_id  join product_ip on product.product_id = product_ip.product_id ) i group by i.customer_id) i   where i.customer_id = c.customer_id) union all select customer_id,customer_name,count(i.ip) as ip_count from ( select customer.customer_id,customer_name,product_ip.ip from customer  join contract on customer.customer_id=contract.customer_id  join product on product.contract_id = contract.contract_id  join product_ip on product.product_id = product_ip.product_id ) i group by i.customer_id,customer_name ) i "
							+ "    natural join "
							+ "(select customer_id,0 as alarm_count from customer c where not exists(     select * from (     select customer_id from ( select customer.customer_id,product_ip.ip from customer join contract on customer.customer_id=contract.customer_id join product on product.contract_id = contract.contract_id join product_ip on product.product_id = product_ip.product_id) c  join alarm_info   on alarm_info.ip = c.ip  group by customer_id          ) i where i.customer_id = c.customer_id )   union all  select customer_id,count(*) as alarm_count from (    select customer.customer_id,product_ip.ip from customer join contract on customer.customer_id=contract.customer_id join product on product.contract_id = contract.contract_id join product_ip on product.product_id = product_ip.product_id	) c  join alarm_info   on alarm_info.ip = c.ip  group by customer_id) a              ");
					
					List<Object[]> resultList = query.list();
					List<CustomerSummary> list = new ArrayList<CustomerSummary>();
					
					for(Object[] row:resultList){
						CustomerSummary customerSummary = new CustomerSummary();
						customerSummary.setCustomerId((String)row[0]);
						customerSummary.setCustomerName((String)row[1]);
						customerSummary.setIpCount(((BigDecimal)row[2]).longValue());
						customerSummary.setAlarmCount(((BigDecimal)row[3]).longValue());
						list.add(customerSummary);
					}
					transaction.commit();
					return list;
				}catch(RuntimeException e){
					transaction.rollback();
					log.error(e);
				}
				return Collections.emptyList();
			
	}
	
	/*
	 * 由客户ID查询到此客户完整的信息
	 */
	public List<ProductIp> queryBy(Customer customer) {

		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try{
			Query query  = session.createQuery("select new ProcuctIp(new ProductIpId(c.customerId,pi.ip)) from Customer c join Contract ct on c.customerId = ct.customerId "
					+ "join Product p on ct.contractId=p.contractId "
					+ "join ProductIp pi on p.productId= pi.id.productId where c.customerId=:customerId");
			
			query.setString("customerId", customer.getCustomerId());
			
			List<ProductIp> list = query.list();
			transaction.commit();
			return list;
		}catch(RuntimeException e){
			transaction.rollback();
			log.error(e);
		}
		return Collections.emptyList();
	}
	
	/*
	 * 每个客户的IP统计、告警数量统计
	 */
	public List<CustomerSummary> queryCustomerStatisticForGatherInterface(){

				Session session = sessionFactory.getCurrentSession();
				Transaction transaction = session.beginTransaction();
				try{
					Query query = session.createSQLQuery("select customer_id,customer_name,0 as port_count from customer c where not exists (select * from (select customer_id from topo_interface natural join customer ) where c.customer_id =customer_id )"
							+ " union all "
							+ "select customer_id,customer_name,count(*) as port_count from topo_interface natural join customer group by customer_id,customer_name" );
					
					List<Object[]> resultList = query.list();
					List<CustomerSummary> list = new ArrayList<CustomerSummary>();
					
					for(Object[] row:resultList){
						CustomerSummary customerSummary = new CustomerSummary();
						customerSummary.setCustomerId((String)row[0]);
						customerSummary.setCustomerName((String)row[1]);
						customerSummary.setPortCount(((BigDecimal)row[2]).longValue());
						
						list.add(customerSummary);
					}
					transaction.commit();
					return list;
				}catch(RuntimeException e){
					transaction.rollback();
					log.error(e);
				}
				return Collections.emptyList();
			
	}
}
