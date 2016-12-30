package com.dhcc.bussiness.sxydidc.alarm.dao;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class AlarmIndicatorDaoTest {

	static AlarmIndicatorDao dao ;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = new AlarmIndicatorDao();
	}

	@Test
	public void testQueryListForAllNumberType() {
		//System.out.println(dao.queryListForAllNumberType());
		dao.queryListForAllNumberType();
	}

	@Test
	public void testQueryListForAllNumberTypeUseDynamicLazy(){
		dao = new AlarmIndicatorDao();
		dao.queryListForAllNumberTypeUseDynamicLazy();
	}
}
