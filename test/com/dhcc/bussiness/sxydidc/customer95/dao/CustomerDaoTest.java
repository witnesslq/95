package com.dhcc.bussiness.sxydidc.customer95.dao;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class CustomerDaoTest {

	static CustomerDao dao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = new CustomerDao();
	}

	@Test
	public void testDeleteAll() {
		dao.deleteAll();
	}

	@Test
	public void testSaveMany() {
		fail("Not yet implemented");
	}

}
