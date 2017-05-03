package com.dhcc.bussiness.sxydidc.customer95.services;

import java.util.List;

import com.dhcc.bussiness.sxydidc.customer95.dao.CustomerDao;
import com.dhcc.bussiness.sxydidc.customer95.models.CustomerSummary;
import com.dhcc.bussiness.sxydidc.quality.dao.TopoInterfaceDao;
import com.dhcc.bussiness.sxydidc.quality.models.Portips;
import com.dhcc.bussiness.sxydidc.quality.models.TopoInterface;
import com.dhcc.bussiness.sxydidc.quality.services.DateRange;
import com.dhcc.bussiness.sxydidc.quality.services.PortipsDao;
import com.dhcc.bussiness.sxydidc.quality.services.PortipsInterfaceService;

public class CustomerSummaryService {

	public List<CustomerSummary> assembleCustomerSummary(){
		CustomerDao dao = new CustomerDao();

		//所有客户IP数量和告警数量的统计
		List<CustomerSummary> list = dao.queryCustomerStatistic();
		
		TopoInterfaceDao interfaceDao = new TopoInterfaceDao();
		PortipsDao portipsDao = new PortipsDao();
		for(CustomerSummary summary:list){
			
//			每个客户的IP所在的设备IP表和所对端口序号索引
			List<TopoInterface> interfaceList = interfaceDao.queryTopoInterfaceListFor(summary);
			
			Portips portips = portipsDao.queryAvgPortipsForInterface(interfaceList);
			
			summary.setPortips(portips);
		}
		return list;
	}
	
	/*
	 * 采集口维度的客户简况列表，包括端口数、平均丢包率、平均错包率
	 */
	public List<CustomerSummary> assembleCustomerSummaryForGatherInterface(){
		CustomerDao dao = new CustomerDao();

		//所有客户端口数量
		List<CustomerSummary> list = dao.queryCustomerStatisticForGatherInterface();
		
		TopoInterfaceDao interfaceDao = new TopoInterfaceDao();
		PortipsDao portipsDao = new PortipsDao();
	
		DateRange range = new DateRange();
		for(CustomerSummary summary:list){
			
//			每个客户的端口所在的设备IP表和所对端口序号索引
			List<TopoInterface> interfaceList = interfaceDao.queryGatherInterfaceListFor(summary, range);
			PortipsInterfaceService service = new PortipsInterfaceService();
			service.resetRange(range, interfaceList);
			Portips portips = portipsDao.queryAvgPortipsForInterface(interfaceList);
			
			summary.setPortips(portips);
		}
		return list;
	}
}
