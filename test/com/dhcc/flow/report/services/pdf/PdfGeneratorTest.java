/**
 * 
 */
package com.dhcc.flow.report.services.pdf;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dhcc.flow.report.models.FlowBill;

/**
 * @author HP
 *
 */
public class PdfGeneratorTest {

	static PdfGenerator generator;
	static FlowBill bill;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bill = new FlowBill();
		bill.setBussinessName("优酷");
		bill.setMonthDate(new Date());
		bill.setPointCount(8000);
		bill.setP95PointCount(5500);
		bill.setCabinetCount(20);
		bill.setP95Flow(55.6);
		generator = new PdfGenerator(bill);
	}

	/**
	 * Test method for {@link com.dhcc.flow.report.services.pdf.PdfGenerator#toOutputStream(java.io.OutputStream)}.
	 */
	@Test
	public void testToOutputStream() {
		OutputStream os;
		try {
			os = new FileOutputStream("results/tables/"+bill.getBussinessName()+bill.getMonthDate().getTime()+".pdf");

			generator.toOutputStream(os);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
