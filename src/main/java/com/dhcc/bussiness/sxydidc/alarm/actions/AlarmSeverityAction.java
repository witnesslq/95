package com.dhcc.bussiness.sxydidc.alarm.actions;

import java.util.List;

import com.dhcc.bussiness.sxydidc.alarm.dao.AlarmSeverityDao;
import com.dhcc.bussiness.sxydidc.alarm.models.AlarmSeverity;
import com.opensymphony.xwork2.ActionSupport;

public class AlarmSeverityAction extends ActionSupport {

	List<AlarmSeverity> list;
	
	/**
	 * @return the list
	 */
	public List<AlarmSeverity> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<AlarmSeverity> list) {
		this.list = list;
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		AlarmSeverityDao dao = new AlarmSeverityDao();
		
		this.list = dao.queryAll();
		return ActionSupport.SUCCESS;
	}

}
