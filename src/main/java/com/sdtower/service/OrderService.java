package com.sdtower.service;

import java.util.List;
import java.util.Map;

import com.sdtower.common.bean.Order;
import com.sdtower.common.bean.OrderPage;

public interface OrderService {
	
	public List<Order> getOrders(OrderPage orderPage);

	public void getOrderInfo(OrderPage orderPage);
	
	public int shOrder(Map  map);
	
	public int updateTowerFirstFeeAply(Map map);
	
	public int updateTowerEndFeeAply(Map map);
	
	public int updateFeeSh(Map map);
	
	public int updateOrderYq(Map map);
	
	public int updateOrderShjj(Map map);
	
	public List<Map> getDcOrders(OrderPage orderPage);
	
	public List<Map> getCheckCancelOrder();
	
	public int updateOrderCancel(Map map);
}

