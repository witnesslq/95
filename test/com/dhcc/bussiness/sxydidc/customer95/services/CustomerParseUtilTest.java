package com.dhcc.bussiness.sxydidc.customer95.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.BeforeClass;
import org.junit.Test;

public class CustomerParseUtilTest {

	static CustomerParseUtil util;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		util = new CustomerParseUtil();
	}

	@Test
	public void testRead() {
	
	util.read("G:\\快盘\\开发实施项目\\ISP流量统计与质量监测系统\\文档\\", "客户合同及产品信息模版", "xlsx");
	
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
