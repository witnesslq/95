package com.dhcc.bussiness.sxydidc.alarm.actions;

import java.util.List;

import com.dhcc.bussiness.sxydidc.alarm.dao.TsuserDao;
import com.dhcc.bussiness.sxydidc.alarm.models.Tsuser;
import com.opensymphony.xwork2.ActionSupport;

public class TsuserAction extends ActionSupport {

	List<Tsuser> list;
	
	/**
	 * @return the list
	 */
	public List<Tsuser> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<Tsuser> list) {
		this.list = list;
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		TsuserDao dao = new TsuserDao();
		this.list = dao.queryAlll();
		return super.execute();
	}

}
