package com.dhcc.bussiness.sxydidc.initialize;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.beanutils.BeanUtils;

import com.dhcc.flow.queryflow.FlowAccountTimer;
import com.dhcc.flow.task.PortIpsTimer;

public class InitListener implements ServletContextListener
{
	Timer flowAccountTimer = new Timer(); 
	Timer portIpsTimer = new Timer(); 
    public InitListener() {
    	    
    	flowAccountTimer.schedule(new FlowAccountTimer(), 1000, 24*60*60*1000);//24*60*60*1000  在1秒后执行此任务,每次间隔2秒执行一次,如果传递一个Data参数,就可以在某个固定的时间执行这个任务.     
        portIpsTimer.schedule(new PortIpsTimer(), 1000, 24*60*60*1000);
    }

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		flowAccountTimer.cancel();
		portIpsTimer.cancel();
	}
	
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}



}