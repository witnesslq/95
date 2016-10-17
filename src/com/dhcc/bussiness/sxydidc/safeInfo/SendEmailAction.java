package com.dhcc.bussiness.sxydidc.safeInfo;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import org.apache.struts2.ServletActionContext;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.dhcc.bussiness.sxydidc.customer.CustomerModel;
import com.dhcc.common.system.email.Model.EmailSenderModel;
import com.dhcc.common.system.email.Util.EmailSender;
import com.dhcc.common.util.AnyTypeAction;
import com.dhcc.common.util.DataCenterUtil;
import com.dhcc.modal.system.PageModel;
import com.mockrunner.util.common.StringUtil;
import com.opensymphony.xwork2.ActionContext;


public class SendEmailAction extends AnyTypeAction<CustomerModel,CustomerModel>{
	
	private static final long serialVersionUID = 1L;
	private SendEmailDao dao = new SendEmailDao();
	private String id;
	private String custid;
	private String url;
	private String ip;
	private String emailTitle;
	private String emailContent;
	private String emailAdd;
	private String begTime;
	private String endTime;
	private String key;
	private JSONObject json;
	private JSONArray array;
	private CustomerModel customer;
	private Infosectsafeinfo safeInfo;
	
	public String queryCustomerByIP(){
		customer=dao.queryCustomerByIP(ip);
		if(customer!=null){
			json = JSONObject.fromObject(customer);
		}else{
			json = JSONObject.fromObject("{\"result\":\"error\"}");
		}
		setJSON(json);
		return SUCCESS;
		
	}
	
	public String sendEmail(){
		boolean result=false;
		customer=dao.queryCustomerByIP(ip);
		if(DataCenterUtil.getDCID().trim().equals(dao.queryIPByAdd(ip).getDcid().trim())){
			EmailSender mailSender=new EmailSender();
			Infosectsafeinfo safeInfo=new Infosectsafeinfo();
			for(String toAddress:emailAdd.split(",")){
				EmailSenderModel mailInfo=new EmailSenderModel();
				mailInfo.setMailServerHost(dao.queryConfig("INFOSECTEMAIL", "mailserverhost").getDvalue());
				mailInfo.setMailServerPort(dao.queryConfig("INFOSECTEMAIL", "mailserverport").getDvalue());
				mailInfo.setValidate(true);
				mailInfo.setFromAddress(dao.queryConfig("INFOSECTEMAIL", "fromaddress").getDvalue());
				mailInfo.setUserName(dao.queryConfig("INFOSECTEMAIL", "username").getDvalue());
				mailInfo.setPassword(dao.queryConfig("INFOSECTEMAIL", "password").getDvalue());
				mailInfo.setToAddress(toAddress);
				mailInfo.setSubject(emailTitle);
				mailInfo.setContent(emailContent);
				result=mailSender.sendHtmlMail(mailInfo);
				if(!result){
					break;
				}else{
					continue;
				}
			}
			safeInfo.setCustid(custid);
			safeInfo.setUrl(url);
			safeInfo.setIp(ip);
			safeInfo.setToemailadd(emailAdd);
			safeInfo.setStatus("01");
			safeInfo.setSendtime(String.valueOf(new Date().getTime()));
			safeInfo.setCreateuser(ActionContext.getContext().getSession().get("username").toString());
			safeInfo.setDcid(DataCenterUtil.getDCID());
			dao.saveSafeInfo(safeInfo);
			if(!result){
				json = JSONObject.fromObject("{\"result\":\"faile\"}");
			}else{
				json = JSONObject.fromObject("{\"result\":\"success\"}");
			}
		}else{
			json = JSONObject.fromObject("{\"result\":\"error\"}");
		}
		setJSON(json);
		return SUCCESS;	
	}
	
	public String queryInfoSectCustMail(){
		 List<Infosectcustmail> list=dao.queryInfoSectCustMail(customer.getId());
		 array = JSONArray.fromObject(list);
		 setArrayJSON(array);
		 return SUCCESS;
	}
	
	public String queryInfoSectCommMail(){
		List<Infosectcommemail> list=dao.queryInfoSectCommMail();
		 array = JSONArray.fromObject(list);
		 setArrayJSON(array);
		 return SUCCESS;
	}
	
	public String querySafeInfo(){
		PageModel pm = new PageModel();
		pm.setCurrentPage(super.getPage());
		pm.setPerPage(super.getPagesize());
		if(StringUtil.isEmptyOrNull(key)){
			pm=dao.querySafeInfo(pm, begTime, endTime);			
		}else{
			pm=dao.quickSearch(pm,key);
		}
		super.setRecord(pm.getTotalRecord());
		super.setTotal(pm.getTotalPage());
		super.setListmodel(pm.getList());
		return SUCCESS;
	}
	
	public String updateSafeInfo(){
		if(dao.updateSafeInfo(safeInfo)){
			return SUCCESS;
		}else{
			return ERROR;
		}
	}	
		
	private void setJSON(JSONObject json){
		try {
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			PrintWriter pw = ServletActionContext.getResponse().getWriter();
			pw.print(json);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void setArrayJSON(JSONArray json){
		try {
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			PrintWriter pw = ServletActionContext.getResponse().getWriter();
			pw.print(json);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public java.util.List<CustomerModel> getListmodel() {
		return super.getListmodel();
	}

	@Override
	public Integer getPage() {
		return super.getPage();
	}

	@Override
	public Integer getPagesize() {
		return super.getPagesize();
	}

	@Override
	public Integer getRecord() {
		return super.getRecord();
	}

	@Override
	public Integer getTotal() {
		return super.getTotal();
	}

	public CustomerModel getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerModel customer) {
		this.customer = customer;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustid() {
		return custid;
	}

	public void setCustid(String custid) {
		this.custid = custid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getEmailTitle() {
		return emailTitle;
	}

	public void setEmailTitle(String emailTitle) {
		this.emailTitle = emailTitle;
	}

	public String getEmailContent() {
		return emailContent;
	}

	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	}

	public String getEmailAdd() {
		return emailAdd;
	}

	public void setEmailAdd(String emailAdd) {
		this.emailAdd = emailAdd;
	}

	public String getBegTime() {
		return begTime;
	}

	public void setBegTime(String begTime) {
		this.begTime = begTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Infosectsafeinfo getSafeInfo() {
		return safeInfo;
	}

	public void setSafeInfo(Infosectsafeinfo safeInfo) {
		this.safeInfo = safeInfo;
	}
	
}
