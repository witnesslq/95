package com.dhcc.bussiness.sxydidc.alarm.actions;

import java.util.List;

import com.dhcc.bussiness.sxydidc.alarm.dao.AlarmNoticeTypeDao;
import com.dhcc.bussiness.sxydidc.alarm.models.AlarmNoticeType;
import com.opensymphony.xwork2.ActionSupport;

public class AlarmNoticeTypeAction extends ActionSupport {

	List<AlarmNoticeType> list;
	
	/**
	 * @return the list
	 */
	public List<AlarmNoticeType> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<AlarmNoticeType> list) {
		this.list = list;
	}

	/* 所有告警通知类型
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		AlarmNoticeTypeDao dao  = new AlarmNoticeTypeDao();
		this.list = dao.queryAll();
		return ActionSupport.SUCCESS;
	}

}
