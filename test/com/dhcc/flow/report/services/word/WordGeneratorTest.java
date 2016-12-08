package com.dhcc.flow.report.services.word;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dhcc.flow.report.models.FlowBill;
import com.dhcc.flow.report.services.Generatable;

public class WordGeneratorTest {

	static Generatable generator;
	static FlowBill bill ;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bill = new FlowBill();
		generator = new WordGenerator(bill);
	
	}

	@Test
	public void testToOutputStream() {
		
//		
		bill.setBussinessName("优酷");
		bill.setMonthDate(new Date());
		bill.setPointCount(8000);
		bill.setP95PointCount(5500);
		bill.setCabinetCount(20);
		bill.setP95Flow(55.6);
		
		OutputStream os;
		try {
			os = new FileOutputStream("results/tables/"+bill.getBussinessName()+bill.getMonthDate().getTime()+".docx");
			generator.toOutputStream(os);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testFilePath(){
		URL url = this.getClass().getResource("计费账单模版.docx");
		System.out.println(url.getFile());
	}

}
