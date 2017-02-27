package com.dhcc.bussiness.sxydidc.alarm.dao;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.dhcc.bussiness.sxydidc.alarm.dao.AlarmInfoDao;

public class AlarmInfoDaoTest {

	 AlarmInfoDao dao = null;
	@Before
	public  void setUp() throws Exception {
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
	@Ignore
	public void testPagingList(){
		System.out.println(dao.queryAll());
	}
	
	@Test
	@Ignore
	public void testQueryAll(){
		System.out.println(dao.queryAll());

	}
}
