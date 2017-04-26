package com.dhcc.common.system.email;


import java.io.IOException;
import java.io.PrintWriter;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.dhcc.common.system.email.Dao.EmailDao;
import com.dhcc.common.system.email.Model.EmailSenderModel;
import com.dhcc.common.system.email.Model.Tsemailconfig;
import com.dhcc.common.system.email.Util.EmailSender;
import com.opensymphony.xwork2.ActionSupport;

public class EmailAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String validate;
	private Tsemailconfig tsemailconfig=new Tsemailconfig();
	private String toAddress;
	private EmailDao mailDao=new EmailDao(); 

	public String queryEmailInfo(){
		tsemailconfig=mailDao.queryEmail();
		JSONObject  json=JSONObject.fromObject(tsemailconfig);
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		PrintWriter pw;
		try {
			pw = ServletActionContext.getResponse().getWriter();
			pw.print(json);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String updateEmailInfo(){
		if(validate.trim().equals("1")){
			tsemailconfig.setValidate(true);
		}else{
			tsemailconfig.setValidate(false);
		}
		mailDao.updateEmail(tsemailconfig);
		return SUCCESS;
	}
	
	public String testEmailInfo(){
		EmailSender mailSender=new EmailSender();
		EmailSenderModel mailInfo=new EmailSenderModel();
		mailInfo.setMailServerHost(tsemailconfig.getMailServerHost());
		mailInfo.setMailServerPort(tsemailconfig.getMailServerPort());
		if(validate.trim().equals("1")==true){
			tsemailconfig.setValidate(true);
		}else{
			tsemailconfig.setValidate(false);
		}
		mailInfo.setValidate(tsemailconfig.getValidate());
		mailInfo.setFromAddress(tsemailconfig.getFromAddress());
		mailInfo.setToAddress(tsemailconfig.getFromAddress());
		mailInfo.setSubject("测试邮件");
		mailInfo.setContent("这是一份测试邮件");
		mailInfo.setPassword(tsemailconfig.getPassword());
		mailInfo.setUserName(tsemailconfig.getUserName());
		
		if(mailSender.sendHtmlMail(mailInfo)){
			return SUCCESS;
		}else{
			return ERROR;
		}
	}
	
	
	public String getValidate() {
		return validate;
	}
	public void setValidate(String validate) {
		this.validate = validate;
	}
	public String getToAddress() {
		return toAddress;
	}
	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public Tsemailconfig getTsemailconfig() {
		return tsemailconfig;
	}

	public void setTsemailconfig(Tsemailconfig tsemailconfig) {
		this.tsemailconfig = tsemailconfig;
	}

}
