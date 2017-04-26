package com.dhcc.flow.queryflow;

import java.util.Calendar;
import java.util.TimerTask;

public class FlowAccountTimer extends TimerTask{

	@Override
	public void run() {
		Calendar c=Calendar.getInstance();
		int day=c.get(Calendar.DAY_OF_MONTH);
		System.out.println("================当前是"+day+"号======================");
		if(day==1) {
		//执行
			FlowDao dao = new FlowDao();
			dao.getUserFlowchargeSql();
		}
		
	}
	
	public static void main(String[] args) {
		FlowAccountTimer t = new FlowAccountTimer();
		t.run();
	}

}
