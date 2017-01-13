package com.dhcc.bussiness.sxydidc.customer95.config.dao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dhcc.bussiness.sxydidc.alarm.HibernateUtil;
import com.dhcc.bussiness.sxydidc.customer95.config.models.DeviceInterface;
import com.dhcc.bussiness.sxydidc.customer95.config.models.DeviceSummary;
import com.dhcc.bussiness.sxydidc.customer95.models.Customer;
import com.dhcc.bussiness.sxydidc.quality.models.TopoHostNode;
import com.dhcc.bussiness.sxydidc.quality.models.TopoInterface;

public class TopoInterfaceDao {

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/*
	 * 删除客户占用的所有端口
	 */
	public void deleteAllBy(Customer customer){

		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		
		try{
			Query query = session.createQuery("delete from TopoInterface t where t.customerId = :customerId")
			.setString("customerId", customer.getCustomerId());
			
			query.executeUpdate();
			transaction.commit();
		}catch(HibernateException e){
			transaction.rollback();
			e.printStackTrace();
			throw e;
		}
	}
	
	/*
	 * 解除当前客户、当前设备上占用的所有端口
	 */
	public void deleteAllBy(Customer customer,TopoHostNode host){

		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		
		try{
			Query query = session.createQuery("delete from TopoInterface t where t.nodeId = :nodeId and t.customerId=:customerId")
			.setString("nodeId", host.getIpAddress())
			.setString("customerId", customer.getCustomerId());
			
			query.executeUpdate();
			transaction.commit();
		}catch(HibernateException e){
			transaction.rollback();
			e.printStackTrace();
			throw e;
		}
	}
	
	/*
	 * 解除客户对这个端口的占用
	 */
	public void delete(TopoInterface topoInterface){

		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		
		try{
			session.createQuery("delete from TopoInterface t where t.nodeId = :nodeId and t.ifIndex=:ifIndex")
			.setString("nodeId", topoInterface.getNodeId())
			.setString("ifIndex", topoInterface.getIfIndex())
			.executeUpdate();
			transaction.commit();
		}catch(HibernateException e){
			transaction.rollback();
			e.printStackTrace();
			throw e;
		}
	}
	
	/*
	 * 客户占用一个端口
	 */
	public void save(TopoInterface topoInterface){


		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		
		try{
			boolean wasBound = !session.createQuery("from TopoInterface t where t.nodeId=:nodeId and t.ifIndex =:ifIndex")
			.setString("nodeId", topoInterface.getNodeId())
			.setString("ifIndex", topoInterface.getIfIndex())
			.list().isEmpty();
			
			if(wasBound) throw new IllegalArgumentException();
			session.save(topoInterface);
			transaction.commit();
		}catch(HibernateException e){
			transaction.rollback();
			e.printStackTrace();
			throw e;
		}
	
	}
	
	/*
	 * 某一个客户的设备端口占用简况，包括 端口数量
	 */
	public List<DeviceSummary> queryBy(Customer customer) {

		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		
		try{
			Query query = session.createQuery("select t.nodeId as ipAddress,count(*) as portCount from TopoInterface t where t.customerId = :customerId and t.endTime is null group by t.nodeId");
			query.setString("customerId", customer.getCustomerId());
			List<Object[]> rows = query.list();
			List<DeviceSummary> list = new ArrayList();
			
			for(Object[] row:rows){
				DeviceSummary deviceSummary = new DeviceSummary((String)row[0],((Long)row[1]).intValue());		
				list.add(deviceSummary);
			}
			transaction.commit();
			return list;
		}catch(HibernateException e){
			transaction.rollback();
			e.printStackTrace();
			throw e;
		}
	}


	/*
	 * 解绑客户占用的所有端口
	 */
	public void unboundAllBy(Customer customer){

		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		
		try{ 
			Query query = session.createQuery("update from TopoInterface t set t.endTime = :endTime where t.customerId=:customerId and t.endTime is null")
			.setString("endTime", sdf.format(new Date()))
			.setString("customerId", customer.getCustomerId());
			
			query.executeUpdate();
			transaction.commit();
		}catch(HibernateException e){
			transaction.rollback();
			e.printStackTrace();
			throw e;
		}
	}
	
	
	/*
	 * 解绑当前客户、当前设备上占用的所有端口
	 */
	public void unboundAllBy(Customer customer,TopoHostNode host){

		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		
		try{
			Query query = session.createQuery("update from TopoInterface t set t.endTime = :endTime where t.customerId=:customerId and t.nodeId=:nodeId and t.endTime is null")
			.setString("endTime", sdf.format(new Date()))
			.setString("customerId", customer.getCustomerId())
			.setString("nodeId", host.getIpAddress());
			
			query.executeUpdate();
			transaction.commit();
		}catch(HibernateException e){
			transaction.rollback();
			e.printStackTrace();
			throw e;
		}
	}
}
