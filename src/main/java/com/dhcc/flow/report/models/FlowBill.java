package com.dhcc.flow.report.models;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.dhcc.flow.report.services.CommonDateFormat;



public class FlowBill implements Exportable {

	private String bussinessName;
	private Date monthDate;
	private long pointCount;
	private long p95PointCount;
	private double p95Flow;
	private long cabinetCount;
//	
	/**
	 * @return the bussinessName
	 */
	public String getBussinessName() {
		return bussinessName;
	}
	/**
	 * @param bussinessName the bussinessName to set
	 */
	public void setBussinessName(String bussinessName) {
		this.bussinessName = bussinessName;
	}

	/**
	 * @return the pointCount
	 */
	public long getPointCount() {
		return pointCount;
	}
	/**
	 * @param pointCount the pointCount to set
	 */
	public void setPointCount(long pointCount) {
		this.pointCount = pointCount;
	}
	/**
	 * @return the p95PointCount
	 */
	public long getP95PointCount() {
		return p95PointCount;
	}
	/**
	 * @param p95PointCount the p95PointCount to set
	 */
	public void setP95PointCount(long p95PointCount) {
		this.p95PointCount = p95PointCount;
	}
	/**
	 * @return the p95Flow
	 */
	public double getP95Flow() {
		return p95Flow;
	}
	/**
	 * @param p95Flow the p95Flow to set
	 */
	public void setP95Flow(double p95Flow) {
		this.p95Flow = p95Flow;
	}
	/**
	 * @return the cabinetCount
	 */
	public long getCabinetCount() {
		return cabinetCount;
	}
	/**
	 * @param cabinetCount the cabinetCount to set
	 */
	public void setCabinetCount(long cabinetCount) {
		this.cabinetCount = cabinetCount;
	}
	

	public Map<String, Object> convert() {
		// TODO Auto-generated method stub
		Map<String,Object> params = new HashMap<String, Object>();
		
		Calendar calendar = Calendar.getInstance();
		
		CommonDateFormat sdf = CommonDateFormat.FLOW_MONTH_DATE_FORMAT;
		calendar.setTime(monthDate);
		String startTime = sdf.format(monthDate)+"1日  00：00",
				endTime = sdf.format(monthDate)+calendar.getActualMaximum(Calendar.DAY_OF_MONTH)+"日 23：55";
				
	      params.put("caption", "山西移动"+sdf.format(monthDate)+this.bussinessName+"九五峰值流量计费");
	      params.put("businessName", this.bussinessName);
	      params.put("startTime", startTime);
	      params.put("endTime", endTime);
	      params.put("pointCount", ""+this.pointCount);
	      params.put("95PointCount", ""+this.p95PointCount);
	      params.put("95Flow", ""+this.p95Flow);
	      params.put("cabinetCount", ""+this.cabinetCount);
		return params;
	}
	/**
	 * @return the monthDate
	 */
	public Date getMonthDate() {
		return monthDate;
	}
	/**
	 * @param monthDate the monthDate to set
	 */
	public void setMonthDate(Date monthDate) {
		this.monthDate = monthDate;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FlowBill [bussinessName=" + bussinessName + ", monthDate="
				+ monthDate + ", pointCount=" + pointCount + ", p95PointCount="
				+ p95PointCount + ", p95Flow=" + p95Flow + ", cabinetCount="
				+ cabinetCount + "]";
	}
	
	
}
