package com.dhcc.common.util;

import com.dhcc.bussiness.sxydidc.datacenter.DataCenterModel;
import com.dhcc.bussiness.sxydidc.safeInfo.SendEmailDao;
import com.dhcc.modal.system.Tsconfig;
import com.opensymphony.xwork2.ActionContext;

public class DataCenterUtil {
	
	public static String getDCID(){
		String dcid=(String) ActionContext.getContext().getSession().get("dcid");
		return dcid;
	}
	
	public static DataCenterModel getDC(){
		DataCenterModel dc=(DataCenterModel) ActionContext.getContext().getSession().get("dc");
		return dc;
	}
	
	public static String getDataCenterRoleId(){
		SendEmailDao dao = new SendEmailDao();
		Tsconfig config=dao.queryConfig("DATACENTER", "ROLEID");
		if(config!=null){
			return config.getDvalue();
		}else{
			return getDCID();
		}
		
	}
	
	/**
	 * 查询该登录用户是否可以查询综合查询功能模块下的全部数据
	 * true:可以，
	 * false:不可以
	 * @return
	 */
	public static boolean queryAllData(){
		String loginRoleIds=(String) ActionContext.getContext().getSession().get("roleIds");
		String roleIds=getDataCenterRoleId();
		boolean result=false;
		for(String roleId:loginRoleIds.split(",")){
			if(roleIds.indexOf(roleId)!=-1){
				result=true;
				break;
				
			}else{
				result=false;
				continue;
			}
		}
		return result;
	}

}
