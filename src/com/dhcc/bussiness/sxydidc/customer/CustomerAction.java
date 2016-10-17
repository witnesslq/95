package com.dhcc.bussiness.sxydidc.customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.*;
import org.apache.struts2.ServletActionContext;
import net.sf.json.JSONObject;
import com.dhcc.common.util.AnyTypeAction;
import com.dhcc.common.util.CreateNum;
import com.dhcc.modal.sxydidc.customer.Busccustomer;
import com.dhcc.modal.system.PageModel;
import com.mockrunner.util.common.StringUtil;
import com.opensymphony.xwork2.ActionContext;

/**
 * 客户信息
 */
public class CustomerAction extends AnyTypeAction<Busccustomer, CustomerModel> {
	private static final long serialVersionUID = 1L;
	private CustomerDao dao = new CustomerDao();
	
	private String id;
	private String type;
	private String ids;
	private String key;
	private CustomerModel customer;
	private String sysLogTitle;		//日志标题
	private String sysLogContent;	//日志内容
	private String flownumber;		//工单号
	private String customerType;	//客户类型
	private String likeQuery;
		
	// 查询客户密钥
	public String queryCustomerKey() {
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		try {
			String jsonString = dao.queryCustomerKey(likeQuery);
			
			PrintWriter writer = ServletActionContext.getResponse().getWriter();
			writer.print(jsonString);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	// 创建客户密钥
	public void initCustomerKeys(String custId) {
		dao.initCustomerKeys(custId);
	}
	
	// 更新客户密钥
	public void updateCustomerKeys(String custId) {
		dao.updateCustomerKeys(custId);
	}
	
	@SuppressWarnings("unchecked")
	public String queryCustomer(){
		PageModel pm = new PageModel();
		pm.setCurrentPage(super.getPage());
		pm.setPerPage(super.getPagesize());
		System.out.println(customerType);
		if(StringUtil.isEmptyOrNull(key)){
			if(customer==null){
				pm = dao.queryCustomer(pm,customerType);
			}else{
				pm = dao.queryCustomerByCondition(pm, customer,customerType);
			}			
		}else{
			pm=dao.quickSearch(pm, customer,key);
		}

		super.setRecord(pm.getTotalRecord());
		super.setTotal(pm.getTotalPage());
		super.setListmodel(pm.getList());
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String queryAllCustomer(){
		PageModel pm = new PageModel();
		pm.setCurrentPage(super.getPage());
		pm.setPerPage(super.getPagesize());
		if(customer==null){
			pm = dao.queryAllCustomer(pm);
		}else{
			pm = dao.queryAllCustomerByCondition(pm, customer);
		}		
		super.setRecord(pm.getTotalRecord());
		super.setTotal(pm.getTotalPage());
		super.setListmodel(pm.getList());
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String queryCustomerByUser(){
		PageModel pm = new PageModel();
		pm.setCurrentPage(super.getPage());
		pm.setPerPage(super.getPagesize());
		if(customer==null){
			customer=new CustomerModel();
		}
		customer.setManager(ActionContext.getContext().getSession().get("userid").toString());
		if(StringUtil.isEmptyOrNull(key)){
			pm = dao.queryCustomerByCondition(pm, customer ,customerType);			
		}else{
			pm=dao.quickSearch(pm, customer,key);
		}
		super.setRecord(pm.getTotalRecord());
		super.setTotal(pm.getTotalPage());
		super.setListmodel(pm.getList());
		return SUCCESS;
	}	
	
	public String queryCusByflownum() throws SQLException{
		String jsonString = dao.queryCusByflownum(flownumber);
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		try {
			PrintWriter writer = ServletActionContext.getResponse().getWriter();
			writer.print(jsonString);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String queryCustomerProperty(){
		PageModel pm = new PageModel();
		List<CustomerModel> list=dao.queryCustomerProperty("");
		pm.setList(list);
		JSONObject json;
		if(list!=null&&(!list.isEmpty())){
			json = JSONObject.fromObject(pm);
		}else{
			json = JSONObject.fromObject("{\"result\":\"error\"}");
		}
		setJSON(json);
		return SUCCESS;
	}
	
	public String deleteCustomerByIds(){
		 dao.deleteCustomerByIds(ids);
		return SUCCESS;
	}
	
	public String queryCustomerById(){
		customer=dao.queryCustomerById(id,type);
		JSONObject json;
		if(customer!=null){
			json = JSONObject.fromObject(customer);
		}else{
			json = JSONObject.fromObject("{\"result\":\"error\"}");
		}
		setJSON(json);
		return SUCCESS;
		
	}
	
	public String updateCustomer(){
		boolean result=dao.updateCustomer(customer);
		JSONObject json;
		if(result){
			customer=dao.queryCustomerById(customer.getId(),customer.getType());
			json = JSONObject.fromObject(customer);
		}else{
			json = JSONObject.fromObject("{\"result\":\"error\"}");
		}
		setJSON(json);
		return SUCCESS;
	}
	
	public String saveCustomer(){
		customer.setManager(ActionContext.getContext().getSession().get("userid").toString());
		id=dao.saveCustomer(customer);
		customer=dao.queryCustomerById(id,customer.getType());
		dao.initCustomerKeys(id);//初始化该客户密钥
		JSONObject json;
		if(customer!=null){
			json = JSONObject.fromObject(customer);
		}else{
			json = JSONObject.fromObject("{\"result\":\"error\"}");
		}
		setJSON(json);
		return SUCCESS;
	}
	
	
	public String editCustomerKey(){
		if(!StringUtil.isEmptyOrNull(ids)){
			for(String id:ids.split(",")){
				dao.updateCustomerKeys(id);
			}
		}
		return SUCCESS;
	}
	
	@SuppressWarnings("static-access")
	public String createCustNo(){
		customer=new CustomerModel();
		CreateNum createNum=new CreateNum();
		String custNO = createNum.getNum("CUSTNO");
		customer.setNo(custNO);
		JSONObject json;
		if(!StringUtil.isEmptyOrNull(custNO)){
			json = JSONObject.fromObject(customer);
		}else{
			json = JSONObject.fromObject("{\"result\":\"error\"}");
		}
		setJSON(json);
		return SUCCESS;
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
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public CustomerModel getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerModel customer) {
		this.customer = customer;
	}
	public String getSysLogTitle() {
		return sysLogTitle;
	}
	public void setSysLogTitle(String sysLogTitle) {
		this.sysLogTitle = sysLogTitle;
	}
	public String getSysLogContent() {
		return sysLogContent;
	}
	public void setSysLogContent(String sysLogContent) {
		this.sysLogContent = sysLogContent;
	}
	public String getFlownumber() {
		return flownumber;
	}
	public void setFlownumber(String flownumber) {
		this.flownumber = flownumber;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public String getLikeQuery() {
		return likeQuery;
	}
	public void setLikeQuery(String likeQuery) {
		this.likeQuery = likeQuery;
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
}