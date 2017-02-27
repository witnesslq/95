/**
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/20016630/how-to-create-a-table-in-a-generated-pdf-using-itextsharp
 * 
 * We create a table with five columns, combining rowspan and colspan
 */
package com.dhcc.flow.report.pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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


public class SimpleRowColspan {
    public static final String DEST = "results/tables/simple_rowspan_colspan.pdf";

    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new SimpleRowColspan().createPdf2(DEST);
    }
    
/*    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table = new PdfPTable(5);
        table.setWidths(new int[]{ 1, 2, 2, 2, 1});
        PdfPCell cell;
        cell = new PdfPCell(new Phrase("S/N"));
        cell.setRowspan(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Name"));
        cell.setColspan(3);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Age"));
        cell.setRowspan(2);
        table.addCell(cell);
        table.addCell("SURNAME");
        table.addCell("FIRST NAME");
        table.addCell("MIDDLE NAME");
        table.addCell("1");
        table.addCell("James");
        table.addCell("Fish");
        table.addCell("Stone");
        table.addCell("17");
        document.add(table);
        document.close();
    }*/
    public void createPdf2(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        //方法二：使用iTextAsian.jar中的字体    
        BaseFont baseFont = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);    
            
           
        Font font = new Font(baseFont);  
        
 
        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(100);
        PdfPCell cell;
        
        cell = new PdfPCell(new Phrase("山西移动2014年11月淘宝九五峰值流量计费",font));
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
        
        cell = new PdfPCell(new Phrase("淘宝",font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("2014年11月1日 00：00",font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("2014年11月30日 24：00",font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("8352",font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("7731",font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("25.24",font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("5个",font));
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
    }
}