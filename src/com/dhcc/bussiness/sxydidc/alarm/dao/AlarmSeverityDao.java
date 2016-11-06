package com.dhcc.bussiness.sxydidc.alarm.dao;

// Generated 2016-10-21 17:16:17 by Hibernate Tools 4.3.1

import static org.hibernate.criterion.Example.create;

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.dhcc.bussiness.sxydidc.alarm.HibernateUtil;
import com.dhcc.bussiness.sxydidc.alarm.models.AlarmSeverity;

/**
 * Home object for domain model class AlarmSeverity.
 * @see com.dhcc.bussiness.sxydidc.alarm.dao.AlarmSeverity
 * @author Hibernate Tools
 */
public class AlarmSeverityDao {

	private static final Log log = LogFactory.getLog(AlarmSeverityDao.class);

	private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	
	public void persist(AlarmSeverity transientInstance) {
		log.debug("persisting AlarmSeverity instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(AlarmSeverity instance) {
		log.debug("attaching dirty AlarmSeverity instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(AlarmSeverity instance) {
		log.debug("attaching clean AlarmSeverity instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(AlarmSeverity persistentInstance) {
		log.debug("deleting AlarmSeverity instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AlarmSeverity merge(AlarmSeverity detachedInstance) {
		log.debug("merging AlarmSeverity instance");
		try {
			AlarmSeverity result = (AlarmSeverity) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public AlarmSeverity findById(java.math.BigDecimal id) {
		log.debug("getting AlarmSeverity instance with id: " + id);
		try {
			Session session = sessionFactory
					.getCurrentSession();
			Transaction transaction = session.beginTransaction();
			AlarmSeverity instance = (AlarmSeverity) session
					.get("com.dhcc.bussiness.sxydidc.alarm.dao.AlarmSeverity",
							id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			transaction.commit();
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<AlarmSeverity> findByExample(AlarmSeverity instance) {
		log.debug("finding AlarmSeverity instance by example");
		try {
			List<AlarmSeverity> results = (List<AlarmSeverity>) sessionFactory
					.getCurrentSession()
					.createCriteria(
							"com.dhcc.bussiness.sxydidc.alarm.dao.AlarmSeverity")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	/*
	 * 所有告警严重性程度
	 */
	public List<AlarmSeverity> queryAll(){
		
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try{
			Query query = session.createQuery("from AlarmSeverity a order by a.severityId");
			List<AlarmSeverity> list = query.list();
			transaction.commit();
			return list;
		}catch(RuntimeException e){
			transaction.rollback();
			log.error(e);
			e.printStackTrace();
			throw e;
		}
	}
}
