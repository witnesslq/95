package com.dhcc.bussiness.sxydidc.customer95.config.actions;

import java.util.List;

import com.dhcc.bussiness.sxydidc.customer95.config.dao.CustomerDao;
import com.dhcc.bussiness.sxydidc.customer95.config.models.CustomerSummary;
import com.opensymphony.xwork2.ActionSupport;

public class CustomerSummaryAction extends ActionSupport {

	private List<CustomerSummary> list;
	
	/**
	 * @return the list
	 */
	public List<CustomerSummary> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<CustomerSummary> list) {
		this.list = list;
	}

	/* 获取客户在线状态、端口数量信息
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		
		CustomerDao dao = new CustomerDao();
		this.list = dao.queryAll();
		return ActionSupport.SUCCESS;
	}

}
