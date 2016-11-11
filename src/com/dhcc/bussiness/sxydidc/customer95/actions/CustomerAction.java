package com.dhcc.bussiness.sxydidc.customer95.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.interceptor.RequestAware;

import com.dhcc.bussiness.sxydidc.customer95.dao.CustomerDao;
import com.dhcc.bussiness.sxydidc.customer95.models.Customer;
import com.dhcc.bussiness.sxydidc.customer95.services.CustomerParseUtil;
import com.dhcc.bussiness.sxydidc.customer95.services.Excel;
import com.opensymphony.xwork2.ActionSupport;

public class CustomerAction extends ActionSupport implements Excel,RequestAware {

	File file;
	String fileContentType;
	String fileFileName;
	
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
		CustomerParseUtil util = new CustomerParseUtil();
		List<Customer> customerList = null;
		if(this.isExcelFile()){
			customerList = util.parse(this);
		}else{
			request.put("error", "添加客户失败");
			return ActionSupport.ERROR;
		}
		CustomerDao dao = new CustomerDao();
		if(dao.deleteAll()){
			if(dao.saveMany(customerList)){
				return ActionSupport.SUCCESS;
			}
		}
		request.put("error", "添加客户失败");
		return ActionSupport.ERROR;
	}

	@Override
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

	@Override
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
	@Override
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

}
