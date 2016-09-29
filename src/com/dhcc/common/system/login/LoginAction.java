package com.dhcc.common.system.login;

import com.dhcc.common.system.systemLog.SaveLog;
import com.dhcc.common.util.CreateDate;
import com.dhcc.modal.system.Tsuser;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 鐢ㄦ埛鐧婚檰鐨勬牎楠孉ction
 * @author GYR
 *
 */
public class LoginAction  extends ActionSupport{
	
	private static final long serialVersionUID = 1L;
	public String userid	 = "";
	public String password	 = "";
	public String verifycode = "";
	public String msg		 = "";	//鐧婚檰澶辫触鐨勪俊鎭�
	private String idforuser;
	
	/**
	 * 鏌ヨ璇ョ敤鎴峰悕銆佸瘑鐮佹槸鍚︽纭�
	 * @return 鏄惁鏈夋敼鐢ㄦ埛
	 */
	public String QueryUser(){
		String sVerifycode = (String) ActionContext.getContext().getSession().get("vCode");
		if((this.verifycode != null && !this.verifycode.trim().equals("")) && (sVerifycode != null && !sVerifycode.trim().equals(""))) {
			if(this.verifycode.equalsIgnoreCase(sVerifycode)) {
				LoginDao dao = new LoginDao();
				//DataCenterDao dcDao=new DataCenterDao();
				Tsuser model = new Tsuser();
				model = dao.QueryUser(this.userid, this.password);
				if(null != model) {
					this.setMsg("LOGINSUCCESS");
					
					idforuser = model.getId();
					ActionContext.getContext().getSession().put("userid",model.getId());
					ActionContext.getContext().getSession().put("username",model.getUsername());
					
					ActionContext.getContext().getSession().put("roleIds",dao.QueryUserRoleId(model.getId()));
					ActionContext.getContext().getSession().put("topcorpid",model.getTopcorpid());
					
					
					ActionContext.getContext().getSession().put("corpid",dao.QueryUserCorpid(model.getId()));
					SaveLog saveLog = new SaveLog();
					saveLog.saveLog("鐢ㄦ埛鐧诲綍", "鐢ㄦ埛浜�"+CreateDate.getDateString()+" 鐧诲綍绯荤粺锛�");
					saveLog = null;
				} else {
					this.setMsg("LOGINERROR");
				}
			} else {
				this.setMsg("LOGINERROR");
			}
		} else {
			this.setMsg("LOGINERROR");
		}
		
		return SUCCESS;
	}
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPossword() {
		return password;
	}
	public void setPossword(String possword) {
		this.password = possword;
	}
	public String getVerifycode() {
		return verifycode;
	}
	public void setVerifycode(String verifycode) {
		this.verifycode = verifycode;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getIdforuser() {
		return idforuser;
	}
	public void setIdforuser(String idforuser) {
		this.idforuser = idforuser;
	}
}