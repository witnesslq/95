package com.dhcc.bussiness.sxydidc.customer95.config.actions;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dhcc.bussiness.sxydidc.quality.models.TopoHostNode;

public class GetSomeDeviceInterfaceActionTest {

	static GetSomeDeviceInterfaceAction action;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		action = new GetSomeDeviceInterfaceAction();
	}

	@Test
	public void testValidate() {
		action.validate();
		System.out.println(action);
	}

	@Test
	public void testExecute() {
		List<TopoHostNode> hostList = new ArrayList();
		hostList.add(new TopoHostNode("183.203.0.111"));
		action.setHostList(hostList);
		try {
			action.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(action.getList());
	}

}
