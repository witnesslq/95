package com.dhcc.bussiness.sxydidc.customer95.actions;

import java.util.List;

import com.dhcc.bussiness.sxydidc.customer95.dao.CustomerDao;
import com.dhcc.bussiness.sxydidc.customer95.models.CustomerSummary;
import com.dhcc.bussiness.sxydidc.customer95.services.CustomerSummaryService;
import com.opensymphony.xwork2.ActionSupport;

public class CustomerSummaryForGatherInterfaceAction extends ActionSupport {

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

	/* 客户概况，包括ip数量、告警数量
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		CustomerSummaryService service = new CustomerSummaryService();
		this.list = service.assembleCustomerSummaryForGatherInterface();
		return super.SUCCESS;
	}

	
}
