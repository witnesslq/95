package com.dhcc.bussiness.sxydidc.alarm.dao;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class AlarmNoticeTypeDaoTest {

	static AlarmNoticeTypeDao dao;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = new AlarmNoticeTypeDao();
	}

	@Test
	public void testQueryAll() {
		System.out.println(dao.queryAll());
	}

}
