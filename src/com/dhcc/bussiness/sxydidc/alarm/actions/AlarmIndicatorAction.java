package com.dhcc.bussiness.sxydidc.alarm.actions;

import java.util.Iterator;
import java.util.List;

import com.dhcc.bussiness.sxydidc.alarm.dao.AlarmIndicatorDao;
import com.dhcc.bussiness.sxydidc.alarm.models.AlarmIndicator;
import com.dhcc.bussiness.sxydidc.alarm.models.AlarmRule;
import com.opensymphony.xwork2.ActionSupport;

public class AlarmIndicatorAction extends ActionSupport {

	List<AlarmIndicator> list;
	
	AlarmIndicator indicator;
	
	/**
	 * @return the indicator
	 */
	public AlarmIndicator getIndicator() {
		return indicator;
	}

	/**
	 * @param indicator the indicator to set
	 */
	public void setIndicator(AlarmIndicator indicator) {
		this.indicator = indicator;
	}


	public String updateIndicator(){
		
		AlarmIndicatorDao dao = new AlarmIndicatorDao();
		Iterator<AlarmRule>  alarmRules = indicator.getAlarmRules().iterator();
		while(alarmRules.hasNext()){
			alarmRules.next().setAlarmIndicator(indicator);
		}
		dao.updateIndicator(indicator);
		return ActionSupport.SUCCESS;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AlarmIndicatorAction [list=" + list + ", indicator="
				+ indicator + "]";
	}

	/**
	 * @return the list
	 */
	public List<AlarmIndicator> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<AlarmIndicator> list) {
		this.list = list;
	}

	/* 所有数字类型的规则所属的指标
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		AlarmIndicatorDao dao = new AlarmIndicatorDao();
		this.list = dao.queryListForAllNumberType();
		return super.SUCCESS;
	}

}
