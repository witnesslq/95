package com.dhcc.bussiness.sxydidc.customer95.config.actions;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dhcc.bussiness.sxydidc.quality.models.TopoInterface;

public class TopoInterfaceActionTest {

	static TopoInterfaceAction action;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		action = new TopoInterfaceAction();
	}

	@Test
	public void testUnbound() {

		TopoInterface topoInterface = new TopoInterface();
		topoInterface.setNodeId("183.203.0.49");
		topoInterface.setIfIndex("44");
		topoInterface.setIfDesc("GE 1/1/1");
		
		action.setTopoInterface(topoInterface);
		try {
			action.unbound();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testBound() {
		TopoInterface topoInterface = new TopoInterface();
		topoInterface.setNodeId("183.203.0.49");
		topoInterface.setIfIndex("44");
		topoInterface.setIfDesc("GE 1/1/1");
		
		action.setTopoInterface(topoInterface);
		try {
			action.bound();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testValidate(){
		action.validate();
		System.out.println(action);
	}
}
