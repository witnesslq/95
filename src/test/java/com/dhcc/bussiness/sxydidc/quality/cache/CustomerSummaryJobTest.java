package com.dhcc.bussiness.sxydidc.quality.cache;

import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.quartz.JobExecutionException;

import com.dhcc.bussiness.sxydidc.alarm.HibernateUtil;
import com.dhcc.bussiness.sxydidc.customer95.models.CustomerSummary;
import com.dhcc.bussiness.sxydidc.customer95.services.CustomerSummaryService;
import com.dhcc.bussiness.sxydidc.quality.dao.TopoHostNodeDao;
import com.dhcc.bussiness.sxydidc.quality.models.TopoHostNode;

public class CustomerSummaryJobTest {

	static CustomerSummaryJob job;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		job = new CustomerSummaryJob();
	}

	@Test
	@Ignore
	public void testExecute() {
		try {
			job.execute(null);
		} catch (JobExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(job.getList());
	}

	@Test
	public void testCreateIpTable(){
		TopoHostNodeDao dao = new TopoHostNodeDao();
		List<TopoHostNode> list = dao.queryAllWithOnlyIp();
		
		for(TopoHostNode host : list){
			System.out.println(host.getIpAddress().replaceAll("\\.", "_"));
			createTable(host.getIpAddress().replaceAll("\\.", "_"));
		}
	}
	
	public void createTable(String ip){
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		try{
			Query query = session.createSQLQuery("create table PORTIPS"
					+ ip
					+ "( ID           NUMBER(20) not null,  IPADDRESS    VARCHAR2(30), RESTYPE      VARCHAR2(20),  CATEGORY     VARCHAR2(50), ENTITY       VARCHAR2(100),  SUBENTITY    VARCHAR2(60),  UTILHDX      VARCHAR2(100), UTILHDXPERC  VARCHAR2(30), DISCARDSPERC VARCHAR2(30),  ERRORSPERC   VARCHAR2(30),  COLLECTTIME  DATE default sysdate-1,  UTILHDXUNIT  VARCHAR2(30),  PERCUNIT     VARCHAR2(30),  UTILHDXFLAG  VARCHAR2(5), RECOVER      VARCHAR2(100),  IFSPEED      VARCHAR2(10),  CUSTOMER_ID  VARCHAR2(50))");
			query.executeUpdate();
			transaction.commit();
		}catch(RuntimeException e){
			transaction.rollback();
			e.printStackTrace();
		}
	}
}
