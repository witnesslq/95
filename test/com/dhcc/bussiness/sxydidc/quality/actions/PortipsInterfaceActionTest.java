/**
 * 
 */
package com.dhcc.bussiness.sxydidc.quality.actions;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dhcc.bussiness.sxydidc.customer95.models.Customer;
import com.dhcc.bussiness.sxydidc.quality.models.TopoInterface;

/**
 * @author HP
 *
 */
public class PortipsInterfaceActionTest {

	static PortipsInterfaceAction action;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		action = new PortipsInterfaceAction();
		action.setCustomer(new Customer("a6fcb705-dccb-453c-a58e-ba5006bd2707"));
		List<TopoInterface> list = new ArrayList();
		TopoInterface in1 = new TopoInterface(),in2 = new TopoInterface();
		in1.setNodeId("183.203.0.80");
		in1.setIfIndex("23");
		in2.setNodeId("183.203.0.80");
		in2.setIfIndex("35");
		list.add(in1);list.add(in2);
		action.setGatherInterfaceList(list);
		action.setType("month");
		action.setDateFormat("yyyy-mm");
		action.setDate("2016-11");
	}

	/**
	 * Test method for {@link com.dhcc.bussiness.sxydidc.quality.actions.PortipsInterfaceAction#execute()}.
	 */
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

	@Test
	public void testExecuteForGatherInterface(){
		try {
			action.executeForGatherInterface();
			System.out.println(action.getList());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
