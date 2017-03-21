package com.sdtower.mapper;

import java.util.List;
import java.util.Map;

import com.sdtower.common.bean.Order;
import com.sdtower.common.bean.OrderPage;
import com.sdtower.common.bean.OrderTower;
import com.sdtower.common.bean.OrderUser;
import com.sdtower.common.bean.ReportOrder;
import com.sdtower.common.bean.ReportOrderPage;
import com.sdtower.common.bean.Score;
import com.sdtower.common.bean.TxSource;
import com.sdtower.common.bean.UserChargeInfo;
import com.sdtower.common.bean.UserOrderInfo;

public interface OrderMapper {
	
	public List<Order> getOrders(OrderPage orderPage);
	
	public int getOrdersCount(OrderPage orderPage);
	
	public OrderTower getOrderTower(int id);
	
	public OrderUser getOrderUser(String wxid);
	
	public int updateOrderState(Map map);
	
	public int updateTowerState(Map map);
	
	public List<Map> getCheckCancelOrder();
	

	//获取站址的下单历史
	public List<Order> getOrderHistorys(int towerid);
	
	public List<Map> getDcTowerOrderHistorys(int towerid);

	
	/*
	 * 订单统计
	 */
	
	public int updateTowerUserOrderInfo(Map map);
	
	
	public int insertUserOrder(UserOrderInfo userOrderInfo);
	
	public int checkUserOrder(String wxid);
	
	public UserOrderInfo getUserOrderInfo(String wxid);
	
	public int updateUserCancelOrderCount(Map map);
	/**
	 * 修改金额
	 */
	public int updateUserCharge(UserChargeInfo chargeInfo);
	
	public int insertTxSource(TxSource txSource);
	
	public UserChargeInfo getUserCharge(String wxid);
	
	/**
	 * 积分
	 */
	
	public int insertScore(Score score);
	
	

	/**
	 * 导出
	 */
	public List<Map> getDcOrders(OrderPage orderPage);
	
    //-------------------------------------------------------------------------
	
	

}
