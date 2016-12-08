package com.dhcc.bussiness.sxydidc.customer95.actions;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dhcc.bussiness.sxydidc.customer95.models.Customer;

public class CustomerActionTest {

	static CustomerAction action ;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		action = new CustomerAction();
		Customer customer = new Customer();
		customer.setCustomerName("公司");
		action.setCustomer(customer);
		File file = new File("G:\\快盘\\开发实施项目\\ISP流量统计与质量监测系统\\文档\\客户合同及产品信息模版.xlsx");
		action.setFile(file);
	}

	@Test
	public void testExecute() {
		try {
			action.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testExecuteQueryCustomerList(){
		
		action.executeQueryCustomerList();
		System.out.println(action.getList());
	}
}
