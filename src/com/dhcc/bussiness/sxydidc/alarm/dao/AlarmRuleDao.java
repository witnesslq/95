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
import com.dhcc.bussiness.sxydidc.alarm.models.AlarmRule;

/**
 * Home object for domain model class AlarmRule.
 * @see com.dhcc.bussiness.sxydidc.alarm.dao.AlarmRule
 * @author Hibernate Tools
 */
public class AlarmRuleDao  {

	private static final Log log = LogFactory.getLog(AlarmRuleDao.class);

	private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();


	public void persist(AlarmRule transientInstance) {
		log.debug("persisting AlarmRule instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(AlarmRule instance) {
		log.debug("attaching dirty AlarmRule instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(AlarmRule instance) {
		log.debug("attaching clean AlarmRule instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(AlarmRule persistentInstance) {
		log.debug("deleting AlarmRule instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AlarmRule merge(AlarmRule detachedInstance) {
		log.debug("merging AlarmRule instance");
		try {
			AlarmRule result = (AlarmRule) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public AlarmRule findById(java.math.BigDecimal id) {
		log.debug("getting AlarmRule instance with id: " + id);
		try {
			AlarmRule instance = (AlarmRule) sessionFactory.getCurrentSession()
					.get("com.dhcc.bussiness.sxydidc.alarm.dao.AlarmRule", id);
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

	public List<AlarmRule> findByExample(AlarmRule instance) {
		log.debug("finding AlarmRule instance by example");
		try {
			List<AlarmRule> results = (List<AlarmRule>) sessionFactory
					.getCurrentSession()
					.createCriteria(
							"com.dhcc.bussiness.sxydidc.alarm.dao.AlarmRule")
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
	 * 所有的告警规则及其阀值
	 */
	public List<AlarmRule> queryAll(){
		Session session = sessionFactory.openSession();
		Transaction transaction =session.beginTransaction();
		try {
			List list = null;
			Query query = session.createQuery("from AlarmRule");
			list = query.list();
			
			return list;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}finally{
			transaction.commit();
//			session.close();
		}
	}
	
	/*更新告警规则
	 * 
	 */
	public void updateRule(AlarmRule rule){
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try{
			AlarmRule persistedRule = (AlarmRule)session.get(AlarmRule.class,rule.getRuleId());
			persistedRule.setAlarmSeverity(rule.getAlarmSeverity());
			persistedRule.setAlarmNoticeTypes(rule.getAlarmNoticeTypes());
			persistedRule.setIsSuppress(rule.getIsSuppress());
			persistedRule.setTsusers(rule.getTsusers());
			
			transaction.commit();
		}catch(RuntimeException e){
			transaction.rollback();
			log.error(e);
			e.printStackTrace();
		}
	}
}
