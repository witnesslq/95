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
import com.dhcc.bussiness.sxydidc.alarm.actions.PaginationData;
import com.dhcc.bussiness.sxydidc.alarm.models.AlarmInfo;

/**
 * Home object for domain model class AlarmInfo.
 * @see com.dhcc.bussiness.sxydidc.alarm.dao.AlarmInfo
 * @author Hibernate Tools
 */
public class AlarmInfoDao  {

	private static final Log log = LogFactory.getLog(AlarmInfoDao.class);

	private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();



	public void persist(AlarmInfo transientInstance) {
		log.debug("persisting AlarmInfo instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(AlarmInfo instance) {
		log.debug("attaching dirty AlarmInfo instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(AlarmInfo instance) {
		log.debug("attaching clean AlarmInfo instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(AlarmInfo persistentInstance) {
		log.debug("deleting AlarmInfo instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AlarmInfo merge(AlarmInfo detachedInstance) {
		log.debug("merging AlarmInfo instance");
		try {
			AlarmInfo result = (AlarmInfo) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public AlarmInfo findById(java.lang.String id) {
		log.debug("getting AlarmInfo instance with id: " + id);
		try {
			Session session =  sessionFactory.getCurrentSession();
			Transaction transaction = session.beginTransaction(); 
		    
			AlarmInfo instance = (AlarmInfo)session
					.get("com.dhcc.bussiness.sxydidc.alarm.models.AlarmInfo", id);
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

	public List<AlarmInfo> findByExample(AlarmInfo instance) {
		log.debug("finding AlarmInfo instance by example");
		try {
			List<AlarmInfo> results = (List<AlarmInfo>) sessionFactory
					.getCurrentSession()
					.createCriteria(
							"com.dhcc.bussiness.sxydidc.alarm.models.AlarmInfo")
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
	 * 分页查询
	 */
	public void pagingList(PaginationData data){
		Session session =  sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction(); 
		try {
			
			Query query = session.createQuery("from AlarmInfo a order by a.time desc").setCacheable(true);
			data.setTotalCount(query.list().size());
			query.setFirstResult(data.getCurrentPageStart());
			query.setMaxResults(data.getCurrentPageEnd());
			data.setList(query.list());
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}finally{
			transaction.commit();
		}
	}
	
	/*
	 * 查询所有告警
	 */
	public List<AlarmInfo> queryAll(){
		Session session =  sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction(); 
		try {
			
			Query query = session.createQuery("from AlarmInfo a order by a.time desc").setCacheable(true);
			
			return query.list();
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}finally{
			transaction.commit();
		}
	}
}
