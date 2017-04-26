package com.dhcc.bussiness.sxydidc.customer95.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.HibernateException;

import com.dhcc.bussiness.sxydidc.customer95.dao.CustomerDao;
import com.dhcc.bussiness.sxydidc.customer95.models.Customer;
import com.dhcc.bussiness.sxydidc.customer95.services.CustomerParseUtil;
import com.dhcc.bussiness.sxydidc.customer95.services.Excel;
import com.opensymphony.xwork2.ActionSupport;

public class CustomerAction extends ActionSupport implements Excel,RequestAware {

	private final Log log = LogFactory.getLog(CustomerAction.class);
	File file;
	String fileContentType;
	String fileFileName;
	
	private List<Customer> list;
	
	/**
	 * @return the list
	 */
	public List<Customer> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<Customer> list) {
		this.list = list;
	}

	/**
	 * @return the fileContentType
	 */
	public String getFileContentType() {
		return fileContentType;
	}

	/**
	 * @param fileContentType the fileContentType to set
	 */
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	/**
	 * @return the fileFileName
	 */
	public String getFileFileName() {
		return fileFileName;
	}

	/**
	 * @param fileFileName the fileFileName to set
	 */
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	/**
	 * @return the file
	 */
	public File getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(File file) {
		this.file = file;
	}

	/* 增加客户
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		try{
			CustomerParseUtil util = new CustomerParseUtil();
			List<Customer> customerList = null;
			if(this.isExcelFile()){
				customerList = util.parse(this);
			}else{
				request.put("result", "ERROR");
				request.put("summary", "添加客户失败");
				request.put("description", "不是Excel文件");
				return ActionSupport.ERROR;
			}
			CustomerDao dao = new CustomerDao();
			if(dao.deleteAll()){
				if(dao.saveMany(customerList)){
					return ActionSupport.SUCCESS;
				}
			}
		}catch(HibernateException e){
			request.put("description", "数据库错误");
			e.printStackTrace();
		}catch(NumberFormatException e){
			request.put("description", "数据格式有问题");
			e.printStackTrace();
		}catch(Exception e){
			log.error(e);
			e.printStackTrace();
		}
		request.put("result", "ERROR");
		request.put("summary", "添加客户失败");
		return ActionSupport.ERROR;
	}


	public boolean isExcelFile() {
		// TODO Auto-generated method stub
		if(fileFileName == null)	return false;
		if (fileFileName.endsWith("xls")) {
			return true;
		} else if (fileFileName.endsWith("xlsx")) {
			return true;
		} 

		return false;
	}


	public Workbook getWorkbook() throws IOException,FileNotFoundException {
	InputStream stream = new FileInputStream(file);
	Workbook wb = null;
	if (fileFileName.endsWith("xls")) {
		wb = new HSSFWorkbook(stream);
	} else if (fileFileName.endsWith("xlsx")) {
		wb = new XSSFWorkbook(stream);
	} 
	return wb;
	}

	Map<String,Object> request ;
	public void setRequest(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.request = arg0;
	}

	/**
	 * @return the request
	 */
	public Map<String, Object> getRequest() {
		return request;
	}

	private Customer customer;
	
	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/*
	 * 按照用户查询用户
	 */
	public String executeQueryCustomerList(){
		CustomerDao dao  = new CustomerDao();
		this.list = dao.queryWithPatternBy(customer);
		return SUCCESS;
	}
}
