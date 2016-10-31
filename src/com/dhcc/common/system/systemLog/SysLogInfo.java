package com.dhcc.common.system.systemLog;

import java.util.List;

public class SysLogInfo {

	private List listmode;
    private int iTotalDisplayRecords;   
    private int iTotalRecords;  
    public SysLogInfo() {  
    	
    }
	public List getListmode() {
		return listmode;
	}
	public void setListmode(List listmode) {
		this.listmode = listmode;
	}
	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}
	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}
	public int getiTotalRecords() {
		return iTotalRecords;
	}
	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}
    
}
