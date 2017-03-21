package com.sdtower.common.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReportTxPage {

	private int pageno = 1;

	private int pagerowtotal;

	private Map pageinfo;

	private List<ReportTx> reportTx = new ArrayList< ReportTx>();

	private ReportTxParameter parameter = new ReportTxParameter();

	private List<Area> adminareas;
	
	private List<City> admincitys;

	private String admincity;

	private List<City> usercitys;

	private List<Area> userareas;

	private List<ReportOrder> orders;
	

	public int getPageno() {
		return pageno;
	}

	public void setPageno(int pageno) {
		this.pageno = pageno;
	}

	public int getPagerowtotal() {
		return pagerowtotal;
	}

	public void setPagerowtotal(int pagerowtotal) {
		this.pagerowtotal = pagerowtotal;
	}

	public Map getPageinfo() {
		return pageinfo;
	}

	public void setPageinfo(Map pageinfo) {
		this.pageinfo = pageinfo;
	}
	
	public List<ReportTx> getReportTx() {
		return reportTx;
	}

	public void setReportTx(List<ReportTx> reportTx) {
		this.reportTx = reportTx;
	}

	public ReportTxParameter getParameter() {
		return parameter;
	}

	public void setParameter(ReportTxParameter parameter) {
		this.parameter = parameter;
	}

	public List<Area> getAdminareas() {
		return adminareas;
	}

	public void setAdminareas(List<Area> adminareas) {
		this.adminareas = adminareas;
	}
	
	

	public List<City> getAdmincitys() {
		return admincitys;
	}

	public void setAdmincitys(List<City> admincitys) {
		this.admincitys = admincitys;
	}

	public String getAdmincity() {
		return admincity;
	}

	public void setAdmincity(String admincity) {
		this.admincity = admincity;
	}

	public List<City> getUsercitys() {
		return usercitys;
	}

	public void setUsercitys(List<City> usercitys) {
		this.usercitys = usercitys;
	}

	public List<Area> getUserareas() {
		return userareas;
	}

	public void setUserareas(List<Area> userareas) {
		this.userareas = userareas;
	}

	public List<ReportOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<ReportOrder> orders) {
		this.orders = orders;
	}

}
