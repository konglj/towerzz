package com.sdtower.common.listener;

import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.sdtower.service.OrderService;

@Component
public class TaskCancel  {
	
	@Autowired
	private OrderService orderService;
	
	@Scheduled(cron="0/60 * * * * ? ")
	public void task(){
	  List<Map> orders=orderService.getCheckCancelOrder();
	  if(orders!=null&&orders.size()>0){
		  for (Map map : orders) {
			orderService.updateOrderCancel(map);
		}
		  
	  }
	  
	  System.out.println("111");
	}

}
