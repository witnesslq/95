package com.dhcc.bussiness.sxydidc.customer95.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CustomerParseUtilTest {

	 private CustomerParseUtil util;
	@Before
	public  void setUp() throws Exception {
		util = new CustomerParseUtil();
	}

	@Test
	public void testUUID(){
		System.out.println(UUID.randomUUID().toString());
	}
	
	@Test
	public void testConvertTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	
			System.out.println(sdf.format(new Date(1283955)));
		
	}
	
	@Test
	public void testRegexpIp(){
		Matcher matcher = Pattern.
				compile("(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})").
				matcher("19.19.290.222 12.89.0.7");
		while(matcher.find()){
			System.out.println(matcher.group());
		}
	}
}
