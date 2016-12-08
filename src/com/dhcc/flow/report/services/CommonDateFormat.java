/**
 * 
 */
package com.dhcc.flow.report.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * 各种系统用到的日期格式的统一聚集处
 * @author HP
 *
 */
public enum CommonDateFormat {

	FLOW_MONTH_DATE_FORMAT("yyyy年MM月"),
	FLOW_MONTH_GENERAL_DATE_FORMAT("yyyy-MM"),
	FLOW_YEAR_DATE_FORMAT("yyyy");
	
	private SimpleDateFormat sdf;

	/**
	 * @param sdf
	 */
	private CommonDateFormat(String dateFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		this.sdf = sdf;
	}

	/*
	 * 使用指定格式格式化日期
	 * @param date
	 * @return
	 * @see java.text.DateFormat#format(java.util.Date)
	 */
	public final String format(Date date) {
		return sdf.format(date);
	}

	/*
	 * 转换为无格式日期
	 * @param source
	 * @return
	 * @throws ParseException
	 * @see java.text.DateFormat#parse(java.lang.String)
	 */
	public Date parse(String source) throws ParseException {
		return sdf.parse(source);
	}
	
	
}
