package com.dhcc.bussiness.sxydidc.busslogin;

import com.dhcc.common.system.login.LoginDao;
import com.dhcc.common.system.systemLog.SaveLog;
import com.dhcc.common.util.CreateDate;
import com.dhcc.modal.system.Tsuser;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BussLoginAction extends ActionSupport {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;
	
	private String userid;//用户id
	
	/**
	 * 查询该用户名、密码是否正确
	 * @return 是否有改用户
	 */
	public String queryUserByUserId(){
		BussLoginDao dao = new BussLoginDao();
		LoginDao loginDao = new LoginDao();
		Tsuser model = dao.queryUserByUserId(userid);
		if(null != model){
			ActionContext.getContext().getSession().put("userid",model.getId());
			ActionContext.getContext().getSession().put("username",model.getUsername());
			ActionContext.getContext().getSession().put("topcorpid",model.getTopcorpid());
			ActionContext.getContext().getSession().put("corpid",loginDao.QueryUserCorpid(model.getId()));
			
			/**
			 * 写入日志
			 */
			SaveLog saveLog = new SaveLog();
			saveLog.saveLog("用户业务登录", "用户于"+CreateDate.getDateString()+" 登录系统！");
			saveLog = null;
			
			return SUCCESS;
		}else{
			return ERROR;
		}
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
}
