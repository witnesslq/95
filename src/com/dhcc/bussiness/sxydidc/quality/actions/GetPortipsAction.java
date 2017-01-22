package com.dhcc.bussiness.sxydidc.quality.actions;

import java.util.List;

import com.dhcc.bussiness.sxydidc.quality.dao.PortipsDao;
import com.dhcc.bussiness.sxydidc.quality.models.Portips;
import com.dhcc.bussiness.sxydidc.quality.models.TopoInterface;
import com.dhcc.bussiness.sxydidc.quality.services.PortipsInterfaceService;
import com.opensymphony.xwork2.ActionSupport;

public class GetPortipsAction extends ActionSupport{

	private String type;
	private long date;
	private List<Portips> list;

	/**
	 * @return the list
	 */
	public List<Portips> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<Portips> list) {
		this.list = list;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
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
	 * @param date the date to set
	 */
	public void setDate(long date) {
		this.date = date;
	}


	private List<TopoInterface> gatherInterfaceList;
	/**
	 * @return the gatherInterfaceList
	 */
	public List<TopoInterface> getGatherInterfaceList() {
		return gatherInterfaceList;
	}

	/**
	 * @param gatherInterfaceList the gatherInterfaceList to set
	 */
	public void setGatherInterfaceList(List<TopoInterface> gatherInterfaceList) {
		this.gatherInterfaceList = gatherInterfaceList;
	}
	

	/* 指定索引的采集口按天，月，年的丢包率、错包率
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String executeForGatherInterface() throws Exception {
		// TODO Auto-generated method stub
		PortipsInterfaceService service = new PortipsInterfaceService();
		this.list = service.fetchPortipsBy(this.gatherInterfaceList,type, date);
		
		return SUCCESS;
	}

}
