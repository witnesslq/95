package com.dhcc.bussiness.sxydidc.alarm.actions;

import com.dhcc.bussiness.sxydidc.alarm.dao.AlarmInfoDao;
import com.dhcc.bussiness.sxydidc.alarm.models.AlarmInfo;

public class AlarmInfoAction  extends  PaginationData<AlarmInfo>{

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;
	@Override
	public String execute() throws Exception {

		AlarmInfoDao dao = new AlarmInfoDao();
		dao.pagingList(this);
		return SUCCESS;
	}
	
}
