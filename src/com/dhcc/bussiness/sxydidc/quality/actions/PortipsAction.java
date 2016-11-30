package com.dhcc.bussiness.sxydidc.quality.actions;

import java.text.ParseException;
import java.util.List;

import com.dhcc.bussiness.sxydidc.customer95.models.Customer;
import com.dhcc.bussiness.sxydidc.customer95.models.ProductIp;
import com.dhcc.bussiness.sxydidc.quality.dao.PortipsDao;
import com.dhcc.bussiness.sxydidc.quality.dao.TopoInterfaceDao;
import com.dhcc.bussiness.sxydidc.quality.models.Portips;
import com.dhcc.bussiness.sxydidc.quality.models.TopoInterface;
import com.opensymphony.xwork2.ActionSupport;

public class PortipsAction extends ActionSupport {

	private String type;

	private long date;
	private Customer customer;

	private ProductIp productIp;

	/**
	 * @return the productIp
	 */
	public ProductIp getProductIp() {
		return productIp;
	}

	/**
	 * @param productIp
	 *            the productIp to set
	 */
	public void setProductIp(ProductIp productIp) {
		this.productIp = productIp;
	}

	private List<Portips> list;

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the date
	 */
	public long getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(long date) {
		this.date = date;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer
	 *            the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/*
	 * 指定客户的按天，月，年的丢包率、错包率
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		TopoInterfaceDao interfaceDao = new TopoInterfaceDao();
		// 每个客户的IP所在的设备IP表和所对端口序号索引
		List<TopoInterface> interfaceList = interfaceDao
				.queryTopoInterfaceListFor(customer);

		PortipsDao portipsDao = new PortipsDao(type, date);
		this.list = portipsDao.queryPortipsForInterface(interfaceList);

		return SUCCESS;
	}

	/**
	 * @return the list
	 */
	public List<Portips> getList() {
		return list;
	}

	/**
	 * @param list
	 *            the list to set
	 */
	public void setList(List<Portips> list) {
		this.list = list;
	}

	/*
	 * 指定IP的按天，月，年的丢包率、错包率
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String executeForIp() throws Exception {
		// TODO Auto-generated method stub
		TopoInterfaceDao interfaceDao = new TopoInterfaceDao();
		// 每个客户的IP所在的设备IP表和所对端口序号索引
		List<TopoInterface> interfaceList = interfaceDao
				.queryTopoInterfaceListFor(productIp);

		PortipsDao portipsDao = new PortipsDao(type, date);
		this.list = portipsDao.queryPortipsForInterface(interfaceList);

		return SUCCESS;
	}

}
