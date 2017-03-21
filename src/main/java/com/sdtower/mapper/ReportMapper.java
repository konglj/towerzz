package com.sdtower.mapper;

import java.util.List;
import java.util.Map;

import com.sdtower.common.bean.ReportOrder;
import com.sdtower.common.bean.ReportOrderPage;
import com.sdtower.common.bean.ReportTx;
import com.sdtower.common.bean.ReportTxPage;
import com.sdtower.common.bean.ReportUser;
import com.sdtower.common.bean.ReportUserPage;

public interface ReportMapper {
	

	/**
	 * 订单报表
	 */
	public List<ReportOrder> getReportOrders(ReportOrderPage reportOrderPage);
	
	public int getOrdersCount(ReportOrderPage reportOrderPage);
	
	/**
	 * 订单报表导出
	 */
	public List<Map> getDCReportOrders(ReportOrderPage reportOrderPage);
	
	
	
	//收款报表
	public List<ReportTx> getReportTxs(ReportTxPage reportTxPage);
	
	public int getReportTxsCount(ReportTxPage reportTxPage);
	
	//收款报表导出
	public List<Map> getDCReportTxs(ReportTxPage reportTxPage);
	
	
	//用户报表
	public List<ReportUser> getReportUsers(ReportUserPage reportUserPage);
	
	public int getReportUsersCount(ReportUserPage reportUserPage);
	
	//用户报表导出
	public List<Map> getDCReportUsers(ReportUserPage reportUserPage);
	
	

}
