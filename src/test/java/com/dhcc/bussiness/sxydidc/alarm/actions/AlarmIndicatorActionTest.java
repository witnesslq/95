package com.dhcc.bussiness.sxydidc.alarm.actions;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AlarmIndicatorActionTest {
	 AlarmIndicatorAction action;
	@Before
	public  void setUp() throws Exception {
		action = new AlarmIndicatorAction();
	}

	@Test
	public void testExecute() {
		try {
			action.execute();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(action);
	}

}
