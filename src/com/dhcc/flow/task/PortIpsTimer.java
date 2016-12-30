package com.dhcc.flow.task;

import java.util.Hashtable;
import java.util.List;
import java.util.TimerTask;

import sun.nio.ch.ThreadPool;

public class PortIpsTimer extends TimerTask{
	

	public void run() {
		// TODO Auto-generated method stub
		System.out.println("**********开始采集汇总数据任务**************");
		PortIpsDao dao =new PortIpsDao();
		
		List list = dao.getNodeHost();
		
		for(int i=0;i<list.size();i++) {
			HostNode node = (HostNode)list.get(i);
			String ip=node.getIp_address().replaceAll("\\.", "_");
			System.out.println("**************开始采集ip为"+ip+"的流量信息*******************");
			//采集半年前的数据
			dao.gethalfyearData(ip);
			//采集一年前的数据
			dao.getyearData(ip);
		}
		
		System.out.println("**********结束汇总数据任务**************");
	}
	
	public static void main(String[] args) {
		PortIpsTimer t = new PortIpsTimer();
		t.run();
	}
}

