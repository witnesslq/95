package com.dhcc.bussiness.sxydidc.quality.dao;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class TopoHostNodeDaoTest {

	static TopoHostNodeDao dao ;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = new TopoHostNodeDao();
	}

	@Test
	public void testQueryAll() {
		System.out.println(dao.queryAll());
	}

}
