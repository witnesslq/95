package com.dhcc.flow.report.actions;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dhcc.flow.report.models.FlowAccount;
import com.dhcc.flow.report.models.FlowAccountId;

public class FlowAccountActionTest {

	static FlowAccountAction action;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		action = new FlowAccountAction();
	}

	@Test
	public void testExecute() {
		FlowAccount flowAccount =new FlowAccount();
		FlowAccountId fId = new FlowAccountId();
		fId.setCustomerId("f89869bb-eb85-41ba-a9ba-b6a8815566c2");
		flowAccount.setId(fId);
		
		action.setFlowAccount(flowAccount);
		action.setDate(new Date().getTime());
		try {
			action.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(action.getList());
	}

}
