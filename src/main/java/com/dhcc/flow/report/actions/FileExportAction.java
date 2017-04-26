package com.dhcc.flow.report.actions;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Date;

import com.dhcc.flow.report.dao.FlowAccountDao;
import com.dhcc.flow.report.models.CustomerFlowAccount;
import com.dhcc.flow.report.models.Exportable;
import com.dhcc.flow.report.models.FlowAccount;
import com.dhcc.flow.report.services.CommonDateFormat;
import com.dhcc.flow.report.services.Generator;
import com.dhcc.flow.report.services.pdf.PdfGenerator;
import com.dhcc.flow.report.services.word.WordGenerator;
import com.opensymphony.xwork2.ActionSupport;

public class FileExportAction extends ActionSupport{

	
	private String exportType;
	private long date;
	
	private FlowAccount flowAccount;
	
	/**
	 * @return the flowAccount
	 */
	public FlowAccount getFlowAccount() {
		return flowAccount;
	}

	/**
	 * @param flowAccount the flowAccount to set
	 */
	public void setFlowAccount(FlowAccount flowAccount) {
		this.flowAccount = flowAccount;
	}

	/**
	 * @return the date
	 */
	public long getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(long date) {
		this.date = date;
	}


	/**
	 * @return the exportType
	 */
	public String getExportType() {
		return exportType;
	}

	/**
	 * @param exportType the exportType to set
	 */
	public void setExportType(String exportType) {
		this.exportType = exportType;
	}

	private Exportable exportableObj;
	
	/**
	 * @return the exportableObj
	 */
	public Exportable getExportableObj() {
		return exportableObj;
	}

	/**
	 * @param exportableObj the exportableObj to set
	 */
	public void setExportableObj(Exportable exportableObj) {
		this.exportableObj = exportableObj;
	}

	/* 导出账单
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub

		if(this.flowAccount == null||this.flowAccount.getId() == null||flowAccount.getId().getCustomerId()==null){
			return ActionSupport.ERROR;
		}
		
		FlowAccountDao dao = new FlowAccountDao();
		flowAccount.getId().setDatetime(CommonDateFormat.FLOW_MONTH_GENERAL_DATE_FORMAT.format(new Date(date)));
		this.exportableObj = dao.queryCustomerFlowAccountBy(flowAccount);
		
		//没有可以导出的账单
		if(this.exportableObj == null){
			return ActionSupport.ERROR;
		}
		this.contentType = "application/octet-stream;charset=UTF-8";
		this.filename = ((CustomerFlowAccount)this.exportableObj).getCustomer().getCustomerName()
				+CommonDateFormat.FLOW_MONTH_DATE_FORMAT.format(
						CommonDateFormat.FLOW_MONTH_GENERAL_DATE_FORMAT.parse(flowAccount.getId().getDatetime())
					)
				+".";
		

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Generator generator = null;
		
		if("docx".equals(this.exportType)){
			this.filename += this.exportType;
			generator = new WordGenerator(this.exportableObj);
		}else if("pdf".equals(this.exportType)){
			this.filename += this.exportType;
			generator = new PdfGenerator(this.exportableObj);
		}else{
			this.filename += "docx";
			generator = new WordGenerator(this.exportableObj);
		}
		this.filename = URLEncoder.encode(this.filename, "UTF-8");
		try {
			generator.toOutputStream(baos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.stream = new ByteArrayInputStream(baos.toByteArray());
		
		return SUCCESS;
	}

	private InputStream stream;
	private String contentType;
	private String filename;
	
	/**
	 * @return the stream
	 */
	public InputStream getStream() {
		return stream;
	}

	/**
	 * @param stream the stream to set
	 */
	public void setStream(InputStream stream) {
		this.stream = stream;
	}

	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * @param contentType the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

}
