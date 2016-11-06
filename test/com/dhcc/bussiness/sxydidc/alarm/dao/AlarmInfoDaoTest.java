package com.dhcc.bussiness.sxydidc.alarm.dao;

import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dhcc.bussiness.sxydidc.alarm.dao.AlarmInfoDao;

public class AlarmInfoDaoTest {

	static AlarmInfoDao dao = null;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = new AlarmInfoDao();
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
	public void testPagingList(){
		System.out.println(dao.queryAll());
	}
	
	@Test
	public void testQueryAll(){
		System.out.println(dao.queryAll());

	}
}
