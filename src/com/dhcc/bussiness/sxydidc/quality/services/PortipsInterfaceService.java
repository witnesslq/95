package com.dhcc.bussiness.sxydidc.quality.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.dhcc.bussiness.sxydidc.customer95.models.Customer;
import com.dhcc.bussiness.sxydidc.quality.dao.PortipsDao;
import com.dhcc.bussiness.sxydidc.quality.dao.TopoInterfaceDao;
import com.dhcc.bussiness.sxydidc.quality.models.Portips;
import com.dhcc.bussiness.sxydidc.quality.models.TopoInterface;

public class PortipsInterfaceService {

	/*
	 * 客户规定时段内质量数据
	 */
	public List<Portips> fetchPortipsBy(Customer customer, String type,
			long date) {
		// TODO Auto-generated method stub
		TopoInterfaceDao interfaceDao = new TopoInterfaceDao();
		DateRange dateRange = new DateRange(date);

		List<TopoInterface> interfaceList = interfaceDao
				.queryGatherInterfaceListFor(customer, dateRange);

		resetRange(dateRange, interfaceList);

		PortipsDao portipsDao = new PortipsDao(type, date);
		return portipsDao.queryPortipsForInterface(interfaceList);
	}

	/* 重置端口的性能数据的展示时间段
	 * @param dateRange
	 * @param interfaceList
	 */
	public void resetRange(DateRange dateRange,
			List<TopoInterface> interfaceList) {
		Date rangeStart, rangeEnd;
		SimpleDateFormat timeSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			rangeStart = timeSdf.parse(dateRange.getStartDate());
			rangeEnd = timeSdf.parse(dateRange.getEndDate());

			for (TopoInterface topoInterface : interfaceList) {
				Date startTime = null, endTime = null;

				if(topoInterface.getStartTime() !=null)
					startTime = timeSdf.parse(topoInterface.getStartTime());
				else
					startTime = rangeStart;
				
				if (topoInterface.getEndTime() != null)
					endTime = timeSdf.parse(topoInterface.getEndTime());
				else
					endTime =rangeEnd;

				// startTime在range之间，取startTime，rangeStart之前取rangeStart
				if (startTime.before(rangeStart)
						|| startTime.equals(rangeStart)) {
					topoInterface.setStartTime(timeSdf.format(rangeStart));
				} else if (startTime.after(rangeStart)) {
					topoInterface.setStartTime(timeSdf.format(startTime));
				}

				// endTime在range之间，取endTime, rangeEnd之后取rangeEnd
				if (endTime.before(rangeEnd)
						|| endTime.equals(rangeEnd)) {
					topoInterface.setEndTime(timeSdf.format(endTime));
				} else if (endTime.after(rangeEnd)) {
					topoInterface.setEndTime(timeSdf.format(rangeEnd));
				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * 一些端口规定时段内质量数据
	 */
	public List<Portips> fetchPortipsBy( List<TopoInterface> gatherInterfaceList,String type,long date){
		// TODO Auto-generated method stub

		DateRange dateRange = new DateRange(date);

		resetRange(dateRange, gatherInterfaceList);
		PortipsDao portipsDao = new PortipsDao(type,date);
		return portipsDao.queryPortipsForInterface(gatherInterfaceList);
	}
}
