package com.dhcc.bussiness.sxydidc.alarm.actions;

import java.util.List;

import com.dhcc.bussiness.sxydidc.alarm.dao.AlarmRuleDao;
import com.dhcc.bussiness.sxydidc.alarm.models.AlarmRule;
import com.opensymphony.xwork2.ActionSupport;

public class AlarmRuleAction extends ActionSupport {

	List<AlarmRule>	list;
	AlarmRule rule;
	
	/**
	 * @return the rule
	 */
	public AlarmRule getRule() {
		return rule;
	}

	/**
	 * @param rule the rule to set
	 */
	public void setRule(AlarmRule rule) {
		this.rule = rule;
	}

	/**
	 * @return the list
	 */
	public List<AlarmRule> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<AlarmRule> list) {
		this.list = list;
	}

	/* 所有的告警规则
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		AlarmRuleDao dao = new AlarmRuleDao();
		this.list = dao.queryAll();
		return ActionSupport.SUCCESS;
	}

	/* 更新告警规则
	 * 
	 */
	public String updateRule() throws Exception{
		AlarmRuleDao dao = new AlarmRuleDao();
		dao.updateRule(rule);
		return ActionSupport.SUCCESS;
	}
	
}
