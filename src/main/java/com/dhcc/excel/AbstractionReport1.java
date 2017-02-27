/**
 * <p>Description:abstraction report,bridge pattern</p>
 * <p>Company: dhcc.com</p>
 * @author afunms
 * @project afunms
 * @date 2006-11-18
 */

package com.dhcc.excel;

/**
 * <p>
 * Description:abstraction report,bridge pattern
 * </p>
 * <p>
 * Company: dhcc.com
 * </p>
 * 
 * @author afunms
 * @project afunms
 * @date 2006-11-18
 */

import java.awt.Color;
import java.util.Hashtable;
import java.util.List;

import jxl.format.Alignment;
import jxl.format.VerticalAlignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WriteException;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Cell;
import com.lowagie.text.Element;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;

public abstract class AbstractionReport1 {
	protected String fileName;

	protected Hashtable<?, ?> reportHash;

	protected ImplementorReport1 impReport;

	protected WritableFont labelFont;

	protected WritableCellFormat labelFormat;

	protected WritableCellFormat labelFormat1;

	protected WritableCellFormat _labelFormat;

	protected WritableCellFormat p_labelFormat;

	protected WritableCellFormat b_labelFormat;

	public AbstractionReport1(ImplementorReport1 impReport) {
		this.impReport = impReport;
		impReport.createReport();

		labelFont = new WritableFont(WritableFont.createFont("宋体"), 12, WritableFont.BOLD, false);
		labelFormat = new WritableCellFormat(labelFont);
		labelFormat1 = new WritableCellFormat(labelFont);
		_labelFormat = new WritableCellFormat();
		p_labelFormat = new WritableCellFormat();
		b_labelFormat = new WritableCellFormat();
		try {
			labelFormat.setShrinkToFit(true);
			labelFormat.setBackground(jxl.format.Colour.SKY_BLUE);
			labelFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN, jxl.format.Colour.BLACK);
			labelFormat.setAlignment(Alignment.CENTRE);
			labelFormat.setVerticalAlignment(VerticalAlignment.CENTRE);

			labelFormat1.setShrinkToFit(true);
			labelFormat1.setBackground(jxl.format.Colour.SKY_BLUE);
			labelFormat1.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN, jxl.format.Colour.BLUE);
			labelFormat1.setAlignment(Alignment.CENTRE);
			labelFormat1.setVerticalAlignment(VerticalAlignment.CENTRE);

			_labelFormat.setShrinkToFit(true);
			_labelFormat.setBackground(jxl.format.Colour.SKY_BLUE);
			_labelFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN, jxl.format.Colour.BLACK);
			_labelFormat.setAlignment(Alignment.CENTRE);
			_labelFormat.setVerticalAlignment(VerticalAlignment.CENTRE);

			p_labelFormat.setShrinkToFit(true);
			p_labelFormat.setBackground(jxl.format.Colour.ICE_BLUE);
			p_labelFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN, jxl.format.Colour.BLACK);
			p_labelFormat.setAlignment(Alignment.CENTRE);
			p_labelFormat.setVerticalAlignment(VerticalAlignment.CENTRE);

			b_labelFormat.setShrinkToFit(true);
			b_labelFormat.setBackground(jxl.format.Colour.GRAY_25);
			b_labelFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN, jxl.format.Colour.BLACK);
			b_labelFormat.setAlignment(Alignment.CENTRE);
			b_labelFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		} catch (WriteException e) {
		}
	}

	public String getFileName() {
		return fileName;
	}
	public abstract void createReport_queryflow(String filename,String datetime);
	/**
	 * @author sunqichang/������
	 * 
	 * ���л�ɫ��
	 * @param num
	 * @return
	 */
	protected WritableCellFormat colorChange(final int num) {
		WritableCellFormat lf = new WritableCellFormat();
		try {
			lf.setShrinkToFit(true);
			lf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN, jxl.format.Colour.BLACK);
			lf.setAlignment(Alignment.CENTRE);
			lf.setVerticalAlignment(VerticalAlignment.CENTRE);
			if (num % 2 == 0) {
				//lf.setBackground(jxl.format.Colour.ICE_BLUE);
			}
		} catch (WriteException e) {
			
		}
		return lf;
	}

	/**
	 * @author sunqichang/������
	 * 
	 * ���õ�Ԫ��ĸ�ʽ
	 * 
	 * @param cell
	 *            ��Ԫ��
	 * @param flag
	 *            �Ƿ����û�ɫ����ɫ
	 * @return cell
	 */
	protected Cell setCellFormat(Object obj, boolean flag) {
		Cell cell = null;
		Phrase p = null;
		if (obj instanceof Cell) {
			cell = (Cell) obj;
		} else if (obj instanceof Phrase) {
			p = (Phrase) obj;
			try {
				cell = new Cell(p);
			} catch (BadElementException e) {
			}
		}
		if (cell != null) {
			if (flag) {
				cell.setBackgroundColor(Color.LIGHT_GRAY);
			}
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setLeading(6);
		}
		return cell;
	}

	/**
	 * @author sunqichang/������
	 * 
	 * ���ñ���ʽ
	 * 
	 * @param aTable
	 */
	protected void setTableFormat(Table aTable) {
		aTable.setWidth(100);
		aTable.setAutoFillEmptyCells(true);
		aTable.setPadding(5);
		aTable.setAlignment(Element.ALIGN_CENTER);
	}
}