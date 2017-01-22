package com.dhcc.bussiness.sxydidc.quality.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.dhcc.bussiness.sxydidc.alarm.HibernateUtil;
import com.dhcc.bussiness.sxydidc.customer95.models.Customer;
import com.dhcc.bussiness.sxydidc.customer95.models.ProductIp;
import com.dhcc.bussiness.sxydidc.quality.models.Portips;
import com.dhcc.bussiness.sxydidc.quality.models.TopoInterface;
import com.dhcc.bussiness.sxydidc.quality.services.DateRange;

public class TopoInterfaceDao {

	private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	
	public List<TopoInterface> queryAll(){

		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try{
			Query query = session.createQuery("from TopoInterface");
			List<TopoInterface> list = query.list();
			transaction.commit();
			return list;
		}catch(HibernateException e){
			transaction.rollback();
			e.printStackTrace();
		}
		return null;
	
	}
	
	/*
	 * 客户的所有IP所对应的采集口
	 * 计划通过完全自动化的采集来将IP和采集口对应
	 */
	public List<TopoInterface> queryTopoInterfaceListFor(Customer customer){
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try{
			Query query = session.createSQLQuery("select {t.*} from  topo_interface t "
					+ "join ("
					+ "select customer.customer_id,product_ip.ip from customer join contract on customer.customer_id=contract.customer_id join product on product.contract_id = contract.contract_id join product_ip on product.product_id = product_ip.product_id where customer.customer_id='"
					+customer.getCustomerId()
					+"'"
					+ ") ip  on ip.ip = t.node_id")
					.addEntity("t",TopoInterface.class);
			
			List<TopoInterface> interfaceList = query.list();
			
		
			transaction.commit();
			
			return interfaceList;
		}catch(HibernateException e){
			transaction.rollback();
			e.printStackTrace();
		}
		return Collections.emptyList();
	}
	
	public List<TopoInterface> queryTopoInterfaceListFor(ProductIp productIp){
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try{
			Query query = session.createSQLQuery("select node_id,port from  topo_interface where  topo_interface.ip_address='"
					+ productIp.getId().getIp()
					+ "' ")
					.addEntity(TopoInterface.class);
			
			List<TopoInterface> interfaceList = query.list();
			
		
			transaction.commit();
			
			return interfaceList;
		}catch(HibernateException e){
			transaction.rollback();
			e.printStackTrace();
		}
		return Collections.emptyList();
	} 
	

	/*
	 * 客户所对应的采集口
	 * 由于
	 * 多个客户IP会对应到一个vlan口上，目前从采集口视角来可视化丢包率、错包率
	 */
	public List<TopoInterface> queryGatherInterfaceListFor(Customer customer, DateRange dateRange){
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try{
			Query query = session.createSQLQuery("select * from (select * from Topo_Interface where   customer_Id=:customerId) t where "
					+ "  (endTime is null and to_date(startTime,'yyyy-mm-dd hh24:mi:ss')<=to_date(:endTime,'yyyy-mm-dd hh24:mi:ss'))"
					+ " or (to_date(startTime,'yyyy-mm-dd hh24:mi:ss')>=to_date(:startTime,'yyyy-mm-dd hh24:mi:ss') and to_date(startTime,'yyyy-mm-dd hh24:mi:ss')<=to_date(:endTime,'yyyy-mm-dd hh24:mi:ss'))"
					+ " or (to_date(endTime,'yyyy-mm-dd hh24:mi:ss')>=to_date(:startTime,'yyyy-mm-dd hh24:mi:ss') and to_date(endTime,'yyyy-mm-dd hh24:mi:ss')<=to_date(:endTime,'yyyy-mm-dd hh24:mi:ss'))"
					+ " order by node_Id")
					.addEntity(TopoInterface.class);			
			query.setString("customerId", customer.getCustomerId());
			query.setString("startTime", dateRange.getStartDate());
			query.setString("endTime", dateRange.getEndDate());
			List<TopoInterface> interfaceList = query.list();
			
		
			transaction.commit();
			
			return interfaceList;
		}catch(HibernateException e){
			transaction.rollback();
			e.printStackTrace();
		}
		return Collections.emptyList();
	}
	
	public List<TopoInterface> queryGatherInterfaceListFor(TopoInterface gatherInterface){
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try{
			Query query = session.createQuery("from  TopoInterface t where  t.ifIndex='"
					+ gatherInterface.getIfIndex()
					+ "'");
			
			List<TopoInterface> interfaceList = query.list();
			transaction.commit();
			
			return interfaceList;
		}catch(HibernateException e){
			transaction.rollback();
			e.printStackTrace();
		}
		return Collections.emptyList();
	} 
}
