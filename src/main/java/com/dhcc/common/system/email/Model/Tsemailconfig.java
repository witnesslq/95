package com.dhcc.common.system.email.Model;


public class Tsemailconfig {    
	private String id;   
    private String mailServerHost;//发送邮件的服务器IP     
    private String mailServerPort;//发送邮件的服务器端口     
    private String fromAddress; //邮件发送者的地址     
    private String userName;//登陆邮件发送服务器的用户名和密码 
    private String password;    
    private boolean validate;//是否需要身份验证 
   
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMailServerHost() {    
      return mailServerHost;    
    }    
    public void setMailServerHost(String mailServerHost) {    
      this.mailServerHost = mailServerHost;    
    }   
    public String getMailServerPort() {    
      return mailServerPort;    
    }   
    public void setMailServerPort(String mailServerPort) {    
      this.mailServerPort = mailServerPort;    
    }   
    public boolean getValidate() {    
      return validate;    
    }   
    public void setValidate(boolean validate) {    
      this.validate = validate;    
    }   
    public String getFromAddress() {    
      return fromAddress;    
    }    
    public void setFromAddress(String fromAddress) {    
      this.fromAddress = fromAddress;    
    }   
    public String getPassword() {    
      return password;    
    }   
    public void setPassword(String password) {    
      this.password = password;    
    }    
    public String getUserName() {    
      return userName;    
    }   
    public void setUserName(String userName) {    
      this.userName = userName;    
    } 
}