package com.dhcc.bussiness.sxydidc.alarm.actions;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class AlarmIndicatorActionTest {

	static AlarmIndicatorAction action;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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
