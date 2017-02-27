package com.dhcc.bussiness.sxydidc.quality.cache;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.quartz.SchedulerException;

public class CustomerSummarySchedulerTest {

	static CustomerSummaryScheduler scheduler;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		scheduler = new CustomerSummaryScheduler();
	}

	@Test
	public void testStart() {
		try {
			scheduler.start();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			try {
				scheduler.shutdown();
			} catch (SchedulerException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	

	public static void main(String[] arg) throws Exception{
		CustomerSummaryScheduler scheduler = new CustomerSummaryScheduler();
		scheduler.start();
	}
}
