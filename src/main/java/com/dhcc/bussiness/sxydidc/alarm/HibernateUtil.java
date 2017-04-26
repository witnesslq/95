package com.dhcc.bussiness.sxydidc.alarm;

import java.io.Serializable;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil  {

	private static final Log log = LogFactory.getLog(HibernateUtil.class);
	private static SessionFactory sessionFactory = null;
	static{
	
		try{
			if(sessionFactory == null){
				Configuration configuration = new Configuration();
				sessionFactory = configuration.configure().buildSessionFactory();
			}
	
		}catch(Throwable e){
			e.printStackTrace();
		}
		
	}
	public static SessionFactory getSessionFactory() {
		try {
			return sessionFactory;
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}
	
	/*
	 * 获取session
	 * 一般来说，HibernateUtil总要在loaded时，初始话sessionFactory
	 */
	public static synchronized Session getSession(){
		if(sessionFactory != null)
			return sessionFactory.getCurrentSession();
		return null;
	}
}
