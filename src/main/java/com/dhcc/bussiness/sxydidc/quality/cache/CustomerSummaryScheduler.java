package com.dhcc.bussiness.sxydidc.quality.cache;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;

import static org.quartz.JobBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;
import static org.quartz.CronScheduleBuilder.*;
import static org.quartz.CalendarIntervalScheduleBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.DateBuilder.*;

public class CustomerSummaryScheduler {

	private  Scheduler scheduler;
	
	/**
	 * @throws SchedulerException
	 * @see org.quartz.Scheduler#start()
	 */
	public void start() throws SchedulerException {

		SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();

		   this.scheduler = schedFact.getScheduler();

		   scheduler.start();

		  // 所有客户简况汇总任务
		  JobDetail job = newJob(CustomerSummaryJob.class)
		      .withIdentity("customerSummaryJob", "group1")
		      .build();

		  // 每隔1分钟执行一次
		  Trigger trigger = newTrigger()
		      .withIdentity("trigger1", "group1")
		      .startNow()
		      .withSchedule(simpleSchedule()
		          .withIntervalInSeconds(60)
		          .repeatForever())
		      .build();

		  scheduler.scheduleJob(job, trigger);
	
	}
	/**
	 * @throws SchedulerException
	 * @see org.quartz.Scheduler#shutdown()
	 */
	public void shutdown() throws SchedulerException {
		scheduler.shutdown();
	}
}
