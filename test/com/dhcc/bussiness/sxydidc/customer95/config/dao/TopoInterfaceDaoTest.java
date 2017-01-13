package com.dhcc.bussiness.sxydidc.customer95.config.dao;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dhcc.bussiness.sxydidc.customer95.models.Customer;
import com.dhcc.bussiness.sxydidc.quality.models.TopoHostNode;
import com.dhcc.bussiness.sxydidc.quality.models.TopoInterface;

public class TopoInterfaceDaoTest {

	static TopoInterfaceDao dao;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = new TopoInterfaceDao();
	}

	@Test
	public void testQueryBy() {
		Customer customer = new Customer();
		customer.setCustomerId("5ecf8c9f-2212-42d6-990b-37b8aaf16edb");
		System.out.println(dao.queryBy(customer));
	}

	@Test
	public void testSave(){
		TopoInterface topoInterface = new TopoInterface();
		topoInterface.setNodeId("183.203.0.49");
		topoInterface.setIfIndex("44");
		topoInterface.setIfDesc("GE 1/1/1");
		dao.save(topoInterface);
		System.out.println(topoInterface);
	}
	
	@Test
	public void testDelete(){
		TopoInterface topoInterface = new TopoInterface();
		topoInterface.setNodeId("183.203.0.49");
		topoInterface.setIfIndex("44");
		topoInterface.setIfDesc("GE 1/1/1");
		dao.save(topoInterface);
		dao.delete(topoInterface);
		
	}
	
	@Test
	public void testDeleteAllBy(){
		TopoHostNode host = new TopoHostNode();
		host.setIpAddress("183.203.0.7");
		
		Customer customer = new Customer();
		customer.setCustomerId("");
		dao.deleteAllBy(customer,host);
	}
	
	@Test
	public void testDeleteAllByCustomer(){
			
		Customer customer = new Customer();
		customer.setCustomerId("'' or 1=1");
		dao.deleteAllBy(customer);
	}
	
	@Test
	public void testUnboundAllByCustomer(){
		Customer customer = new Customer("5ecf8c9f-2212-42d6-990b-37b8aaf16edb");
		
		dao.unboundAllBy(customer);
	}
	
	@Test
	public void testUnboundAllBy(){
		Customer customer = new Customer("5ecf8c9f-2212-42d6-990b-37b8aaf16edb");
		TopoHostNode host = new TopoHostNode();
		host.setIpAddress("183.203.0.104");
		dao.unboundAllBy(customer,host);
	}
}
