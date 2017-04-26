package com.dhcc.bussiness.sxydidc.quality.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

public class PortipsDaoTest {

	static PortipsDao dao ;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//dao = new PortipsDao();
	}

	@Test
	public void testQueryPortipsForCustomer() {

	}

	@Test
	public void testParseMonthDate(){
		try {
			Date date = new SimpleDateFormat("yyyy-MM").parse("2016-11-02");
			System.out.println();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDateFormat(){
		try {
			Date date = new SimpleDateFormat("mm/dd/yyyy").parse("2016-11-11");
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			System.out.println(c.get(Calendar.YEAR)+"-"+c.get(Calendar.MONTH)+"-"+c.get(Calendar.DAY_OF_MONTH));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
