package com.dhcc.bussiness.sxydidc.quality.actions;

import java.util.List;

import com.dhcc.bussiness.sxydidc.customer95.models.Customer;
import com.dhcc.bussiness.sxydidc.quality.dao.TopoInterfaceDao;
import com.dhcc.bussiness.sxydidc.quality.models.TopoInterface;
import com.opensymphony.xwork2.ActionSupport;

public class TopoInterfaceAction extends ActionSupport {

	private List<TopoInterface> list;
	private Customer customer;
	/**
	 * @return the list
	 */
	public List<TopoInterface> getList() {
		return list;
	}
	/**
	 * @param list the list to set
	 */
	public void setList(List<TopoInterface> list) {
		this.list = list;
	}
	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}
	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	/* 指定客户的采集端口表
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		TopoInterfaceDao dao = new TopoInterfaceDao();
		this.list = dao.queryGatherInterfaceListFor(this.customer);
		return SUCCESS;
	}

}
