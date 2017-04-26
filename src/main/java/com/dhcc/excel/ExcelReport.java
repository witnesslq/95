/**
 * <p>Description:create excel report,bridge pattern</p>
 * <p>Company: dhcc.com</p>
 * @author afunms
 * @project afunms
 * @date 2006-11-18
 */

package com.dhcc.excel;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import jxl.CellView;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.struts2.ServletActionContext;
import org.jfree.chart.ChartUtilities;

import com.dhcc.flow.queryflow.FlowModel;


public class ExcelReport extends AbstractionReport1 {
	private HttpServletRequest request;
	
	private Hashtable dataHash;
	
	

	public Hashtable getDataHash() {
		return dataHash;
	}

	public void setDataHash(Hashtable dataHash) {
		this.dataHash = dataHash;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	// I_MonitorIpList monitorManager=new MonitoriplistManager();

	

	public ExcelReport(ImplementorReport1 impReport) {
		super(impReport);
	}

	public ExcelReport(ImplementorReport1 impReport, Hashtable hash) {
		super(impReport);
		reportHash = hash;
	}

	@Override
	public void createReport_queryflow(String filename, String datetime) {

		/*if (impReport.getTable() == null) {
			fileName = null;
			return;
		}*/
		WritableWorkbook wb = null;
		try {
			fileName =  ServletActionContext.getServletContext().getRealPath("/system/queryFlow/"+"flow.xls");
			if (!new File(fileName).exists()) {
				new File(fileName).createNewFile();
			}
			wb = Workbook.createWorkbook(new File(fileName));
			String starttime = (String) reportHash.get("starttime");
			String username = (String) reportHash.get("username");
			String ip = (String) reportHash.get("ip");
			String port = (String) reportHash.get("port");
			WritableSheet sheet = wb.createSheet("流量统计报表", 0);
			List flowlist = (List) reportHash.get("flowlist");

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			Label tmpLabel = null;
			/*tmpLabel = new Label(0, 0, "流量统计报表", labelFormat);
			sheet.addCell(tmpLabel);
			tmpLabel = new Label(0, 1, "流量统计时间段:" + starttime, labelFormat1);
			sheet.addCell(tmpLabel);

			sheet.mergeCells(0, 0, 6, 0);
			sheet.mergeCells(0, 1, 6, 1);*/
			
			tmpLabel = new Label(0, 0, "客户名称", _labelFormat);
			sheet.addCell(tmpLabel);
			tmpLabel = new Label(1, 0, username, p_labelFormat);
			sheet.addCell(tmpLabel);
			sheet.mergeCells(1, 0, 2, 0);
			tmpLabel = new Label(0, 1, "端口名称", _labelFormat);
			sheet.addCell(tmpLabel);
			tmpLabel = new Label(1, 1, port, p_labelFormat);
			sheet.addCell(tmpLabel);
			sheet.mergeCells(1, 1, 2, 0);
			tmpLabel = new Label(0, 2, "采集时间", _labelFormat);
			sheet.addCell(tmpLabel);
			tmpLabel = new Label(1, 2, "入口流量(M)", _labelFormat);
			sheet.addCell(tmpLabel);
			tmpLabel = new Label(2, 2, "出口流量(M)", _labelFormat);
			sheet.addCell(tmpLabel);
			
			
			
			int row = 3;
			int k = 0;
			int m =0;
			for (int i=0; i<flowlist.size()-2;i++){
				FlowModel flowModel = (FlowModel)flowlist.get(i);
				if(flowModel.getEntity().equals("入口")) {
					
					tmpLabel = new Label(0, row + k, flowModel.getCollecttime(), p_labelFormat);
					sheet.addCell(tmpLabel);
					tmpLabel = new Label(1, row + k, ""+flowModel.getUtilhdx(), p_labelFormat);
					sheet.addCell(tmpLabel);
					k++;
				}
				
			}
			
			for (int i=0; i<flowlist.size()-2;i++){
				FlowModel flowModel = (FlowModel)flowlist.get(i);
				if(flowModel.getEntity().equals("出口")) {
					tmpLabel = new Label(2, row + m, ""+flowModel.getUtilhdx(), p_labelFormat);
					sheet.addCell(tmpLabel);
					m++;
				}
				
			}
			
			wb.write();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (wb != null)
					wb.close();
			} catch (Exception e) {
			}
		}
	
		
	}


}