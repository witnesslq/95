package com.dhcc.bussiness.sxydidc.alarm.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.dhcc.bussiness.sxydidc.alarm.HibernateUtil;
import com.dhcc.bussiness.sxydidc.alarm.models.Tsuser;

public class TsuserDao {

	private static final Log log = LogFactory.getLog(TsuserDao.class);
	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	
	/* 取出所有用户
	 * 
	 */
	public List<Tsuser> queryAlll(){
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		
		try{
			Query query = session.createQuery("from Tsuser");
			List<Tsuser> list = query.list();
			transaction.commit();
			return list;
		}catch(RuntimeException e){
			transaction.rollback();
			log.error(e);
			throw e;
		}
	}
}
