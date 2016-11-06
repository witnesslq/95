package com.dhcc.bussiness.sxydidc.alarm.dao;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

public class AlarmRuleDaoTest {

	static AlarmRuleDao dao  = null;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = new AlarmRuleDao();
	}

	@Test
	public void testQueryAll() {
		List list = dao.queryAll();
		System.out.println(list);
	}

}
