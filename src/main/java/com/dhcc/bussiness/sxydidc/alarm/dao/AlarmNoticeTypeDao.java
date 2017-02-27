package com.dhcc.bussiness.sxydidc.alarm.dao;

// Generated 2016-10-21 17:16:17 by Hibernate Tools 4.3.1

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.dhcc.bussiness.sxydidc.alarm.HibernateUtil;
import com.dhcc.bussiness.sxydidc.alarm.models.AlarmNoticeType;

/**
 * Home object for domain model class AlarmNoticeType.
 * @see com.dhcc.bussiness.sxydidc.alarm.dao.AlarmNoticeType
 * @author Hibernate Tools
 */
public class AlarmNoticeTypeDao  {

	private static final Log log = LogFactory.getLog(AlarmNoticeTypeDao.class);

	private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();



	public void persist(AlarmNoticeType transientInstance) {
		log.debug("persisting AlarmNoticeType instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(AlarmNoticeType instance) {
		log.debug("attaching dirty AlarmNoticeType instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(AlarmNoticeType instance) {
		log.debug("attaching clean AlarmNoticeType instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(AlarmNoticeType persistentInstance) {
		log.debug("deleting AlarmNoticeType instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AlarmNoticeType merge(AlarmNoticeType detachedInstance) {
		log.debug("merging AlarmNoticeType instance");
		try {
			AlarmNoticeType result = (AlarmNoticeType) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public AlarmNoticeType findById(java.math.BigDecimal id) {
		log.debug("getting AlarmNoticeType instance with id: " + id);
		try {
			AlarmNoticeType instance = (AlarmNoticeType) sessionFactory
					.getCurrentSession()
					.get("com.dhcc.bussiness.sxydidc.alarm.dao.AlarmNoticeType",
							id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<AlarmNoticeType> findByExample(AlarmNoticeType instance) {
		log.debug("finding AlarmNoticeType instance by example");
		try {
			List<AlarmNoticeType> results = (List<AlarmNoticeType>) sessionFactory
					.getCurrentSession()
					.createCriteria(
							"com.dhcc.bussiness.sxydidc.alarm.dao.AlarmNoticeType")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public List<AlarmNoticeType> queryAll(){
		
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			Query query = session.createQuery("from AlarmNoticeType a order by a.noticeTypeId asc");
			List<AlarmNoticeType> list =query.list(); 
			transaction.commit();
			return list;
		} catch (RuntimeException re) {
			transaction.rollback();
			log.error("get failed", re);
			throw re;
		}
	}
}
