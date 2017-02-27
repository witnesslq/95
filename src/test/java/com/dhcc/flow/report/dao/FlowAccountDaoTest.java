package com.dhcc.flow.report.dao;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dhcc.flow.report.models.FlowAccount;
import com.dhcc.flow.report.models.FlowAccountId;

public class FlowAccountDaoTest {

	static FlowAccountDao dao;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = new FlowAccountDao();
	}

	@Test
	public void testQueryAll() {
		System.out.println(dao.queryAll());
	}

	@Test
	public void testQueryBy(){
		FlowAccount flowAccount =new FlowAccount();
		FlowAccountId fId = new FlowAccountId();
		fId.setCustomerId("f89869bb-eb85-41ba-a9ba-b6a8815566c2");
		fId.setDatetime("2016");
		flowAccount.setId(fId);
		
		System.out.println(dao.queryBy(flowAccount));
	}
	
	@Test
	public void testQueryById(){
		FlowAccount flowAccount =new FlowAccount();
		FlowAccountId fId = new FlowAccountId();
		fId.setCustomerId("f89869bb-eb85-41ba-a9ba-b6a8815566c2");
		fId.setDatetime("2016-11");
		flowAccount.setId(fId);
		
		System.out.println(dao.queryById(flowAccount));
	}
	
	@Test
	public void testQueryCustomerFlowAccountBy(){
		FlowAccount flowAccount =new FlowAccount();
		FlowAccountId fId = new FlowAccountId();
		fId.setCustomerId("f89869bb-eb85-41ba-a9ba-b6a8815566c2");
		fId.setDatetime("2016-11");
		flowAccount.setId(fId);
		System.out.println(dao.queryCustomerFlowAccountBy(flowAccount));;
	}
}
