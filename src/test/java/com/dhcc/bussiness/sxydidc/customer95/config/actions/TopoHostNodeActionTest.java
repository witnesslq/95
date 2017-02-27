package com.dhcc.bussiness.sxydidc.customer95.config.actions;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class TopoHostNodeActionTest {

	static TopoHostNodeAction action;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		action = new TopoHostNodeAction();
		
	}

	@Test
	public void testExecute() {
		try {
			action.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(action.getList());
	}

}
