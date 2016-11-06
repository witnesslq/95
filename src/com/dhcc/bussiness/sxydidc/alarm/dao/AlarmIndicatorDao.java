package com.dhcc.bussiness.sxydidc.alarm.dao;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.dhcc.bussiness.sxydidc.alarm.HibernateUtil;
import com.dhcc.bussiness.sxydidc.alarm.models.AlarmIndicator;
import com.dhcc.bussiness.sxydidc.alarm.models.AlarmRule;

public class AlarmIndicatorDao {
	private static final Log log = LogFactory.getLog(AlarmIndicatorDao.class);

	private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	
	/* 所有指标，其规则的值类型是数字
	 * 
	 */
	public List<AlarmIndicator> queryListForAllNumberType(){
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try{
			/*Query query = session.createQuery("from AlarmIndicator i inner join fetch i.alarmRules r "
					+ "where r.alarmRuleValueType.name='number' and r.alarmRuleValueType.valueCount >=1");*/
			Query query = session.createQuery("from AlarmIndicator i where  "
					+ "(select count(*) from i.alarmRules r where r.alarmRuleValueType.name='number' and r.alarmRuleValueType.valueCount >=1) >0");
			List<AlarmIndicator> list = query.list();
			transaction.commit();
			return list;
		}catch(RuntimeException e){
			transaction.rollback();
			log.error(e);
			e.printStackTrace();
			throw e;
		}
	}
	
	/*
	 * 更新告警指标及其相关的告警规则
	 */
	public void updateIndicator(AlarmIndicator indicator){
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		
		try{
			AlarmIndicator persistedIndicator = (AlarmIndicator)session.get(AlarmIndicator.class, indicator.getIndicatorId());
			Iterator<AlarmRule> rules = indicator.getAlarmRules().iterator(),
					persistedRules;
			while(rules.hasNext()){
				AlarmRule rule =rules.next();
				
				persistedRules = persistedIndicator.getAlarmRules().iterator();
				while(persistedRules.hasNext()){
					AlarmRule persistedRule = persistedRules.next();
					if(rule.equals(persistedRule)){
						persistedRule.setValue(rule.getValue());
					}
				}
			}
			transaction.commit();
		}catch(RuntimeException e){
			transaction.rollback();
			log.error(e);
			e.printStackTrace();
		}finally{
			
		}
	}
}
