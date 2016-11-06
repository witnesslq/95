package com.dhcc.bussiness.sxydidc.alarm.dao;

import static org.junit.Assert.fail;

import java.math.BigDecimal;

import org.junit.BeforeClass;
import org.junit.Test;

public class AlarmSeverityDaoTest {

	static AlarmSeverityDao dao = null;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = new AlarmSeverityDao();
	}

	@Test
	public void testPersist() {
	}

	@Test
	public void testAttachDirty() {
	}

	@Test
	public void testAttachClean() {
	}

	@Test
	public void testDelete() {
	}

	@Test
	public void testMerge() {
	}

	@Test
	public void testFindById() {
}

	@Test
	public void testFindByExample() {
	}

	@Test
	public void testQueryAll() {
		System.out.println(dao.queryAll());
	}

}
