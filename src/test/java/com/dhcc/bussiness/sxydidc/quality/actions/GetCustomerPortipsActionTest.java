/**
 * 
 */
package com.dhcc.bussiness.sxydidc.quality.actions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dhcc.bussiness.sxydidc.customer95.models.Customer;
import com.dhcc.bussiness.sxydidc.quality.models.TopoInterface;
import com.dhcc.bussiness.sxydidc.quality.services.DateRange;

/**
 * @author HP
 *
 */
public class GetCustomerPortipsActionTest {

	static GetCustomerPortipsAction action;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		action = new GetCustomerPortipsAction();
		action.setCustomer(new Customer("016997fd-2a7f-499c-ac71-f62e16be77be"));
		
		Calendar ca = Calendar.getInstance();
		ca.set(2017, 0, 20);
		DateRange dateRange = new DateRange(ca.getTimeInMillis());
		action.setType("day");
		action.setDate(new Date().getTime());
	}

	/**
	 * Test method for {@link com.dhcc.bussiness.sxydidc.quality.actions.GetCustomerPortipsAction#execute()}.
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

}
