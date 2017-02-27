package com.dhcc.flow.report.actions;

import static org.junit.Assert.fail;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.dhcc.bussiness.sxydidc.customer95.models.Customer;
import com.dhcc.flow.report.models.FlowAccount;
import com.dhcc.flow.report.models.FlowAccountId;
import com.dhcc.flow.report.models.FlowBill;
import com.dhcc.flow.report.services.CommonDateFormat;

public class FileExportActionTest {

	 FileExportAction action;
	@Before
	public  void setUp() throws Exception {
		action = new FileExportAction();
	
		FlowAccount flowAccount = new FlowAccount();
		FlowAccountId id =new FlowAccountId();
		id.setCustomerId("f89869bb-eb85-41ba-a9ba-b6a8815566c2");
		flowAccount.setId(id);
		
		action.setDate(CommonDateFormat.FLOW_MONTH_GENERAL_DATE_FORMAT.parse("2016-11").getTime());
		
		action.setExportType("pdf");
		action.setFlowAccount(flowAccount);
	}

	@Test
	@Ignore
	public void testExecute() {

		try {
			action.execute();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		InputStream is = null;
		OutputStream os = null;
		try {
			byte[] buffer = new byte[1024];
			is = action.getStream();
			os = new FileOutputStream("results/tables/test"+action.getDate()+"."+action.getExportType());
			
			while(is.read(buffer) != -1){
				os.write(buffer);
			}
			os.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(is != null)
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(os != null)
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	
	}

	@Test
	public void testDateFormatChinese(){
		System.out.println(CommonDateFormat.FLOW_MONTH_DATE_FORMAT.format(new Date()));
	}
}
