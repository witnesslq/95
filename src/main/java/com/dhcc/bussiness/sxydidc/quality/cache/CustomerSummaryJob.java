package com.dhcc.bussiness.sxydidc.quality.cache;

import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import redis.clients.jedis.Jedis;

import com.dhcc.bussiness.sxydidc.customer95.models.CustomerSummary;
import com.dhcc.bussiness.sxydidc.customer95.services.CustomerSummaryService;
import com.dhcc.bussiness.sxydidc.quality.JedisPoolUtil;

public class CustomerSummaryJob implements Job {

	
	/**
	 * 
	 */
	public CustomerSummaryJob() {
		super();
		// TODO Auto-generated constructor stub
	}

	private List<CustomerSummary> list;
	
	/**
	 * @return the list
	 */
	public List<CustomerSummary> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<CustomerSummary> list) {
		this.list = list;
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		CustomerSummaryService service = new CustomerSummaryService();
		 this.list = service.assembleCustomerSummaryForGatherInterface();
		 
		Jedis jedis =  null;
		try{
			jedis = JedisPoolUtil.getResource();
			
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
		
	}

}
