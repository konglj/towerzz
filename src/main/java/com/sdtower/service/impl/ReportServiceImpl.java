package com.sdtower.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdtower.common.bean.Order;
import com.sdtower.common.bean.ReportOrder;
import com.sdtower.common.bean.ReportOrderPage;
import com.sdtower.common.bean.ReportTx;
import com.sdtower.common.bean.ReportTxPage;
import com.sdtower.common.bean.ReportUser;
import com.sdtower.common.bean.ReportUserPage;
import com.sdtower.mapper.ReportMapper;
import com.sdtower.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private ReportMapper reportMapper;
	@Override
	public List<ReportOrder> getReportOrders(ReportOrderPage reportOrderPage) {
		int count = reportMapper.getOrdersCount(reportOrderPage);
		reportOrderPage.setPagerowtotal(count);
		return reportMapper.getReportOrders(reportOrderPage);

	}
	@Override
	public List<Map> getDCReportOrders(ReportOrderPage reportOrderPage) {
		return reportMapper.getDCReportOrders(reportOrderPage);
	}
	@Override
	public List<ReportTx> getReportTxs(ReportTxPage reportTxPage) {
		int count = reportMapper.getReportTxsCount(reportTxPage);
		reportTxPage.setPagerowtotal(count);
		return reportMapper.getReportTxs(reportTxPage);
	}
	@Override
	public List<Map> getDCReportTxs(ReportTxPage reportTxPage) {
		return reportMapper.getDCReportTxs(reportTxPage);
	}
	@Override
	public List<ReportUser> getReportUsers(ReportUserPage reportUserPage) {
		int count = reportMapper.getReportUsersCount(reportUserPage);
		reportUserPage.setPagerowtotal(count);
		return reportMapper.getReportUsers(reportUserPage);
	}
	@Override
	public List<Map> getDCReportUsers(ReportUserPage reportUserPage) {
		return reportMapper.getDCReportUsers(reportUserPage);
	}

}
