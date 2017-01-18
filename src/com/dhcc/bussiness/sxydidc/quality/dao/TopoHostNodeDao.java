package com.dhcc.bussiness.sxydidc.quality.dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.dhcc.bussiness.sxydidc.alarm.HibernateUtil;
import com.dhcc.bussiness.sxydidc.quality.models.TopoHostNode;

public class TopoHostNodeDao {
private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	
	public List<TopoHostNode> queryAll(){

		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try{
			Query query = session.createQuery("from TopoHostNode");
			List<TopoHostNode> list = query.list();
			transaction.commit();
			return list;
		}catch(HibernateException e){
			transaction.rollback();
			e.printStackTrace();
		}
		return Collections.EMPTY_LIST;
	
	}
	
	/*
	 * 所有设备的IP
	 */
	public List<TopoHostNode> queryAllWithOnlyIp(){


		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try{
			Query query = session.createQuery("select new TopoHostNode(t.ipAddress) from TopoHostNode t");
			List<TopoHostNode> list = query.list();
			transaction.commit();
			return list;
		}catch(HibernateException e){
			transaction.rollback();
			e.printStackTrace();
		}
		return Collections.EMPTY_LIST;
	}
}
