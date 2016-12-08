package com.dhcc.flow.report.dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dhcc.bussiness.sxydidc.alarm.HibernateUtil;
import com.dhcc.flow.report.models.CustomerFlowAccount;
import com.dhcc.flow.report.models.FlowAccount;

public class FlowAccountDao {

	public List<FlowAccount> queryAll(){
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		try{
			Query query = session.createQuery("select f from FlowAccount f,Customer c where f.id.customerId = c.customerId");
			List<FlowAccount> list = query.list();
			transaction.commit();
			return list;
		}catch(HibernateException e){
			transaction.rollback();
			e.printStackTrace();
		}
		return Collections.EMPTY_LIST;
	}
	
	/*
	 * 根据年份和客户ID查询流量账单
	 */
	public List<FlowAccount> queryBy(FlowAccount flowAccount){
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		try{
			Query query = session.createQuery("from FlowAccount f  where f.id.customerId = :customerId and to_char(to_date(f.id.datetime,'yyyy-mm'),'yyyy')=:year order by f.id.datetime asc");
			
			query.setString("customerId", flowAccount.getId().getCustomerId())
			.setString("year", flowAccount.getId().getDatetime());
			
			List<FlowAccount> list = query.list();
			transaction.commit();
			return list;
		}catch(HibernateException e){
			transaction.rollback();
			e.printStackTrace();
		}
		return Collections.EMPTY_LIST;			
	}
	
	public FlowAccount queryById(FlowAccount flowAccount){
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		try{
		    flowAccount = (FlowAccount)session.get(FlowAccount.class, flowAccount.getId());

			transaction.commit();
			return flowAccount;
		}catch(HibernateException e){
			transaction.rollback();
			e.printStackTrace();
		}
		return flowAccount;
	}
	
	/*
	 * 根据客户ID和月度时间获取客户账单
	 */
	public CustomerFlowAccount queryCustomerFlowAccountBy(FlowAccount flowAccount){
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		try{
		    Query query = session.createQuery("select "
		    		+ "new com.dhcc.flow.report.models.CustomerFlowAccount"
			    	+ "("
			    + "c.customerId,c.customerName,"
			    + "f.id.customerId,  f.id.datetime,  f.id.totalcount, f.id.flowcount,  f.id.utilhdx,  f.id.cabinet"
		    		+ ")"
		    		+ " from FlowAccount f,Customer c  where f.id.customerId=c.customerId and f.id.customerId=:customerId and f.id.datetime=:datetime");
		    
		  /*  Query query = session.createQuery("select "
		    		+ "new com.dhcc.bussiness.sxydidc.customer95.models.Customer"
		    		+ "(c.customerId,c.customerName)"
		    		
		    		+ " from FlowAccount f,Customer c  where f.id.customerId=c.customerId and f.id.customerId=:customerId and f.id.datetime=:datetime");
		    */
		    query.setString("customerId", flowAccount.getId().getCustomerId())
		    .setString("datetime", flowAccount.getId().getDatetime());
		    
		    List<CustomerFlowAccount> list = query.list();
		    
			transaction.commit();
		   return list.size()>0?list.get(0):null;
		}catch(HibernateException e){
			transaction.rollback();
			e.printStackTrace();
		}
		return null;
	}
}
