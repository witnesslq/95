package com.dhcc.flow.report.actions;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;

import com.dhcc.flow.report.dao.FlowAccountDao;
import com.dhcc.flow.report.models.FlowAccount;
import com.dhcc.flow.report.services.CommonDateFormat;
import com.opensymphony.xwork2.ActionSupport;

public class FlowAccountAction extends ActionSupport{

	private FlowAccount flowAccount;
	private long date;
	private List<FlowAccount> list;
	
	/**
	 * @return the date
	 */
	public long getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(long date) {
		this.date = date;
	}

	/**
	 * @return the list
	 */
	public List<FlowAccount> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<FlowAccount> list) {
		this.list = list;
	}

	/**
	 * @return the flowAccount
	 */
	public FlowAccount getFlowAccount() {
		return flowAccount;
	}

	/**
	 * @param flowAccount the flowAccount to set
	 */
	public void setFlowAccount(FlowAccount flowAccount) {
		this.flowAccount = flowAccount;
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		if(flowAccount ==null||flowAccount.getId()==null || flowAccount.getId().getCustomerId()==null){
			return super.ERROR;
		}
		flowAccount.getId().setDatetime(CommonDateFormat.FLOW_YEAR_DATE_FORMAT.format(new Date(this.getDate())));
		
		FlowAccountDao dao = new FlowAccountDao();
		this.list = dao.queryBy(flowAccount);
		return super.SUCCESS;
	}

	/* 修改账单
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String executeUpdate() throws Exception {
		// TODO Auto-generated method stub
		if(flowAccount ==null||flowAccount.getId()==null || flowAccount.getId().getCustomerId()==null){
			return super.ERROR;
		}
		
		FlowAccountDao dao = new FlowAccountDao();
		
		this.number = dao.update(flowAccount);
		
		return super.SUCCESS;
	}
	
	private int number;

	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}
	
}
