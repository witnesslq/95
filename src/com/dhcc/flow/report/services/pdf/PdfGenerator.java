package com.dhcc.flow.report.services.pdf;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import com.dhcc.flow.report.models.Exportable;
import com.dhcc.flow.report.services.Generator;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfGenerator extends Generator {

	@Override
	public void toOutputStream(OutputStream os) throws IOException {
		try{
			
			Map<String,Object> params = this.getExportableObj().convert();
			String caption = String.valueOf(params.get("caption")),
					businessName= String.valueOf(params.get("businessName")),
			startTime= String.valueOf(params.get("startTime")),
			endTime= String.valueOf(params.get("endTime")),
			pointCount=String.valueOf(params.get("pointCount")),
			p95PointCount= String.valueOf(params.get("95PointCount")),
		      p95Flow=String.valueOf(params.get("95Flow")),
		      cabinetCount= String.valueOf(params.get("cabinetCount"));
		      
        Document document = new Document();
        PdfWriter.getInstance(document, os);
        document.open();
        //方法二：使用iTextAsian.jar中的字体    
        BaseFont baseFont = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);    
            
        Font font = new Font(baseFont);  
 
        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(100);
        PdfPCell cell;
        
        cell = new PdfPCell(new Phrase(caption,font));
        cell.setFixedHeight(30);
        cell.setColspan(7);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("业务名称",font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("开始时间",font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("结束时间",font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("流量峰值点数",font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("95%峰值流量点",font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("95%峰值流量(Gbps)",font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("机柜数量",font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase(businessName,font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(startTime,font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase(endTime,font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(pointCount,font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase(p95PointCount,font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(p95Flow,font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase(cabinetCount,font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);
        
        //审核意见
        cell = new PdfPCell(new Phrase("审核意见：", font));
        cell.setFixedHeight(30);
        cell.setColspan(7);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);
        
//        签字
        cell = new PdfPCell(new Phrase("", font));
        cell.setColspan(3);
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("签字（盖章）：", font));
        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("", font));
        cell.setColspan(2);
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);
//      日期
      cell = new PdfPCell(new Phrase("", font));
      cell.setColspan(3);
      cell.setBorder(Rectangle.NO_BORDER);
      table.addCell(cell);
      cell = new PdfPCell(new Phrase("日期：", font));
      cell.setColspan(2);
      cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
      cell.setBorder(Rectangle.NO_BORDER);
      table.addCell(cell);
      cell = new PdfPCell(new Phrase("", font));
      cell.setColspan(2);
      cell.setBorder(Rectangle.NO_BORDER);
      table.addCell(cell);
      
      Paragraph tableParagraph = new  Paragraph();
         
      tableParagraph.add(table);
       document.add(tableParagraph);
        Paragraph annotation = new  Paragraph("注：\n"
        		+ "1.提取规则：对淘宝租用端口每五分钟进行一次采样，一个月为一个周期，并将该计费周期内采集到的数据由大到小排序，去掉最大的 5%数量的值，取剩下 95%的点的数据的最大值为当月峰值流量。\n"
        		+ "2.计算规则：当月共提取S个峰值点，去掉最大的前5%的峰值点，即峰值最大的0.05*S 个点，取第0.05*S+1个点，为当月的95%峰值流量。\n"
        		+ "（1）流量峰值点数：S；（2）： 95%峰值流量点：0.05*S+1个；（3） 95%峰值流量(Gbps)：第0.05*S+1个点对应峰值流量。",font);
        annotation.setSpacingBefore(15f);
        document.add(annotation);
        document.close();
		}catch(DocumentException e){
			throw new IOException(e);
		}
    }

	/**
	 * 
	 */
	public PdfGenerator() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param exportableObj
	 */
	public PdfGenerator(Exportable exportableObj) {
		super(exportableObj);
		// TODO Auto-generated constructor stub
	}

}
