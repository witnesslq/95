package com.dhcc.bussiness.sxydidc.quality.dao;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dhcc.bussiness.sxydidc.customer95.models.Customer;
import com.dhcc.bussiness.sxydidc.quality.models.TopoInterface;
import com.dhcc.bussiness.sxydidc.quality.services.DateRange;

public class TopoInterfaceDaoTest {

	static TopoInterfaceDao dao;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = new TopoInterfaceDao();
	}

	@Test
	public void testQueryAll() {
		System.out.println(dao.queryAll());
	}

	@Test
	public void testStringReplace(){
		System.out.println("192.168.1.2".replaceAll("\\.", "_"));
	}
	@Test
	public void testQueryTopoInterfaceListFor(){
		Customer customer = new Customer();
		customer.setCustomerId("f90ad976-6a07-4d55-a951-4568abe23a02");
		System.out.println(dao.queryTopoInterfaceListFor(customer));;
	}
	
	@Test
	public void testQueryGatherInterfaceListFor(){
		TopoInterface in = new TopoInterface();
		in.setIfIndex("26");
		System.out.println(dao.queryGatherInterfaceListFor(in));
	}
	
	@Test
	public void testQueryGatherInterfaceListForCustomer(){
		Customer customer = new Customer();
		customer.setCustomerId("016997fd-2a7f-499c-ac71-f62e16be77be");
		
		Calendar ca = Calendar.getInstance();
		ca.set(2017, 0, 20);
		DateRange dateRange = new DateRange(ca.getTimeInMillis());
		System.out.println(dao.queryGatherInterfaceListFor(customer, dateRange));
	}
}
