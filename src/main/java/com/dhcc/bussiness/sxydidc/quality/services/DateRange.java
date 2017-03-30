package com.dhcc.bussiness.sxydidc.quality.services;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateRange {

	private static final String DAY_FORMAT = "yyyy-MM-dd";
	private static final String MONTH_FORMAT = "yyyy-MM";
	private static final String YEAR_FORMAT = "yyyy";
	private String startDate;
	private String endDate;
	
	/**
	 * @param startDate
	 * @param endDate
	 */
	private DateRange(String startDate, String endDate) {
		super();
		this.startDate = startDate+" 00:00:00";
		this.endDate = endDate+" 23:59:59";
	}

	public DateRange(String type,long startDate,long endDate){
		
	}
	public DateRange(long startDate,long endDate) {
		this(sdf.format(new Date(startDate)),sdf.format(new Date(endDate)) );
	}


	/**
	 * @param startDate
	 * @param endDate
	 */
	private DateRange(String baseDate) {
		super();
		this.startDate = baseDate+" 00:00:00";
		this.endDate =  baseDate+" 23:59:59";
	}
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * 
	 */
	public DateRange(long date) {
		
		this(sdf.format(new Date(date)) );
	}


	/**
	 * 
	 */
	public DateRange() {
		this(sdf.format(new Date()));
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
}
