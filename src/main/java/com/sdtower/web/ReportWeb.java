package com.sdtower.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sdtower.common.bean.Area;
import com.sdtower.common.bean.City;
import com.sdtower.common.bean.Order;
import com.sdtower.common.bean.OrderPage;
import com.sdtower.common.bean.OrderParameter;
import com.sdtower.common.bean.ReportOrder;
import com.sdtower.common.bean.ReportOrderPage;
import com.sdtower.common.bean.ReportOrderParameter;
import com.sdtower.common.bean.ReportTx;
import com.sdtower.common.bean.ReportTxPage;
import com.sdtower.common.bean.ReportTxParameter;
import com.sdtower.common.bean.ReportUser;
import com.sdtower.common.bean.ReportUserPage;
import com.sdtower.common.bean.ReportUserParameter;
import com.sdtower.common.bean.SysUserInfo;
import com.sdtower.common.bean.TxPage;
import com.sdtower.common.bean.TxParameter;
import com.sdtower.common.bean.TxRecord;
import com.sdtower.common.util.ExcelDC;
import com.sdtower.common.util.PageUtil;
import com.sdtower.common.util.ParamerUtil;
import com.sdtower.common.util.TimeUtil;
import com.sdtower.service.AreaService;
import com.sdtower.service.ReportService;

@Controller
@RequestMapping("/report")
public class ReportWeb {
	
	@Autowired
	private ReportService reportService;
	
	@Autowired
	private AreaService areaService;
	
	@RequestMapping("/order")
	public ModelAndView getReportOrders(HttpServletRequest request,
			ReportOrderPage reportOrderPage) {
		if (reportOrderPage == null)
			reportOrderPage = new ReportOrderPage();

		ReportOrderParameter parameter=reportOrderPage.getParameter();
		
		String todate=parameter.getToweraddtimeend();
		if(parameter.getToweraddtimeend()!=null&&!parameter.getToweraddtimeend().equals("")){
			parameter.setToweraddtimeend(TimeUtil.getDailyEndTime(parameter.getToweraddtimeend()));
		}
		
		SysUserInfo sysUserInfo = ParamerUtil.getSysUserFromSesson(request);
		List<City> citys = sysUserInfo.getCitys();
		List<Area> areas = new ArrayList<Area>();// sysUserInfo.getAreas();
		if (parameter.getTowercity() == "") {
			if (citys == null || citys.size() == 0) {
				citys = new ArrayList<City>();
			} else if (citys.size() == 1) {
				parameter.setTowercity(citys.get(0).getCityid());
				//areas = sysUserInfo.getAreas();
				Map map = new HashMap();
				map.put("cityid", citys.get(0).getCityid());
				areas=areaService.getAreas(map);
			}
		}else{
			if (citys.size() == 1) {
				areas = sysUserInfo.getAreas();
			} else if (citys.size() > 1) {
				Map map = new HashMap();
				map.put("cityid", parameter.getTowercity());
				areas = areaService.getAreas(map);
			}
		} 
		int adminType = ParamerUtil.getAdminType(sysUserInfo.getAdminpower());
		if (adminType == 1) {
			reportOrderPage.setAdmincity(citys.get(0).getCityid());
		} else if (adminType == 2) {
		//reportOrderPage.setAdminareas(sysUserInfo.getAreas());
			reportOrderPage.setAdmincitys(sysUserInfo.getCitys());
		}

		List<ReportOrder> orders = reportService.getReportOrders(reportOrderPage);
		reportOrderPage.setOrders(orders);
		reportOrderPage.setTowercitys(citys);
		reportOrderPage.setTowerareas(areas);
		parameter.setToweraddtimeend(todate);
		
		// 设置页面
		PageUtil pageUtil = new PageUtil(reportOrderPage.getPageno(),
				reportOrderPage.getPagerowtotal());
		reportOrderPage.setPageinfo(pageUtil.getPage());
		return new ModelAndView("/report/order_report", "reportOrderPage", reportOrderPage);
	}
	
	
	
	@RequestMapping("/dc_orders")
	@ResponseBody
	public void dcOrders(HttpServletRequest request,
			HttpServletResponse response, ReportOrderPage reportOrderPage)  throws Exception {
		try {
			if (reportOrderPage.getParameter().getTowername() != null) {
				reportOrderPage.getParameter().setTowername(new String(reportOrderPage.getParameter().getTowername() .getBytes("ISO8859-1"), "utf-8"));
			}
		} catch (UnsupportedEncodingException e) {
		}
		
		try {
			if (reportOrderPage.getParameter().getUsername() != null) {
				reportOrderPage.getParameter().setUsername(new String(reportOrderPage.getParameter().getUsername() .getBytes("ISO8859-1"), "utf-8"));
			}
		} catch (UnsupportedEncodingException e) {
		}
		String filename="订单报表.xls";
		ExcelDC ecDc = new ExcelDC(request, "report_order.xls");
		List<Map> list = reportService.getDCReportOrders(reportOrderPage);
		
		HSSFWorkbook workbook = ecDc.getReportOrders(list);
		 ByteArrayOutputStream os = new ByteArrayOutputStream();

	        try {
	        	workbook.write(os);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		  byte[] content = os.toByteArray();
	        InputStream is = new ByteArrayInputStream(content);

	       filename=new String(filename.getBytes(), "iso-8859-1");
	        // 设置response参数，可以打开下载页面
	        response.reset();
	        response.setContentType("application/vnd.ms-excel;charset=utf-8");
	        response.setHeader("Content-Disposition", "attachment;filename="+filename+"");

	        ServletOutputStream out = response.getOutputStream();

	        BufferedInputStream bis = null;
	        BufferedOutputStream bos = null;

	        try {

	            bis = new BufferedInputStream(is);
	            bos = new BufferedOutputStream(out);

	            byte[] buff = new byte[2048];
	            int bytesRead;

	            // Simple read/write loop.
	            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
	                bos.write(buff, 0, bytesRead);
	            }

	        } catch (final IOException e) {
	            throw e;
	        } finally {
	            if (bis != null)
	                bis.close();
	            if (bos != null)
	                bos.close();
	        }
	}
	
	//收款管理 ------------------------------------------------------------------------------------------


	@RequestMapping("/tx")
	public ModelAndView getReportTx(HttpServletRequest request,ReportTxPage reportTxPage){
		if(reportTxPage==null)
			reportTxPage=new ReportTxPage();
		
		ReportTxParameter parameter=reportTxPage.getParameter();
		
		SysUserInfo sysUserInfo=ParamerUtil.getSysUserFromSesson(request);
		List<City> citys=sysUserInfo.getCitys();
		List<Area> userareas=new ArrayList<Area>();
		if (parameter.getUsercity() == "") {
			if (citys == null || citys.size() == 0) {
				citys = new ArrayList<City>();
			} else if (citys.size() == 1) {
				parameter.setUsercity(citys.get(0).getCityid());
				Map map = new HashMap();
				map.put("cityid", citys.get(0).getCityid());
				userareas=areaService.getAreas(map);
			}
		} else {
			if (citys.size() == 1) {
			} else if (citys.size() > 1) {
				Map map = new HashMap();
				map.put("cityid", parameter.getUsercity());
				userareas = areaService.getAreas(map);
			}
		}
		
		int adminType=ParamerUtil.getAdminType(sysUserInfo.getAdminpower());
		
		List<City> usercitys=sysUserInfo.getCitys();
		if(adminType==1){
			reportTxPage.setAdmincity(citys.get(0).getCityid());
			
			Map map=new HashMap();
			map.put("cityid", parameter.getUsercity());
			userareas=areaService.getAreas(map);
		}else if(adminType==2){
			//reportTxPage.setAdminareas(sysUserInfo.getAreas());
			reportTxPage.setAdmincitys(sysUserInfo.getCitys());
			userareas=sysUserInfo.getAreas();
			Map map=new HashMap();
			map.put("cityid", parameter.getUsercity());
			userareas=areaService.getAreas(map);
		}
		
		List<ReportTx> reportTxs=reportService.getReportTxs(reportTxPage);
		reportTxPage.setReportTx(reportTxs);
		
		
		reportTxPage.setUsercitys(usercitys);
		reportTxPage.setUserareas(userareas);
		//设置页面
		PageUtil pageUtil=new PageUtil(reportTxPage.getPageno(),reportTxPage.getPagerowtotal());
		reportTxPage.setPageinfo(pageUtil.getPage());
		return new ModelAndView("/report/tx_report","reportTxPage",reportTxPage);
	}
	
	
	
	@RequestMapping("/dc_txs")
	@ResponseBody
	public void dcTxs(HttpServletRequest request,
			HttpServletResponse response, ReportTxPage reportTxPage)  throws Exception {
		
		try {
			if (reportTxPage.getParameter().getUsername() != null) {
				reportTxPage.getParameter().setUsername(new String(reportTxPage.getParameter().getUsername() .getBytes("ISO8859-1"), "utf-8"));
			}
		} catch (UnsupportedEncodingException e) {
		}
		String filename="收款确认用户详单.xls";
		ExcelDC ecDc = new ExcelDC(request, "report_fee.xls");
		List<Map> list = reportService.getDCReportTxs(reportTxPage);
		
		HSSFWorkbook workbook = ecDc.getReportTxs(list);
		 ByteArrayOutputStream os = new ByteArrayOutputStream();

	        try {
	        	workbook.write(os);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		  byte[] content = os.toByteArray();
	        InputStream is = new ByteArrayInputStream(content);

	       filename=new String(filename.getBytes(), "iso-8859-1");
	        // 设置response参数，可以打开下载页面
	        response.reset();
	        response.setContentType("application/vnd.ms-excel;charset=utf-8");
	        response.setHeader("Content-Disposition", "attachment;filename="+filename+"");

	        ServletOutputStream out = response.getOutputStream();

	        BufferedInputStream bis = null;
	        BufferedOutputStream bos = null;

	        try {

	            bis = new BufferedInputStream(is);
	            bos = new BufferedOutputStream(out);

	            byte[] buff = new byte[2048];
	            int bytesRead;

	            // Simple read/write loop.
	            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
	                bos.write(buff, 0, bytesRead);
	            }

	        } catch (final IOException e) {
	            throw e;
	        } finally {
	            if (bis != null)
	                bis.close();
	            if (bos != null)
	                bos.close();
	        }
	}
	
	
	//用户报表 ------------------------------------------------------------------------------------------


	@RequestMapping("/user")
	public ModelAndView getReportUser(HttpServletRequest request,ReportUserPage reportUserPage){
		if(reportUserPage==null)
			reportUserPage=new ReportUserPage();
		
		ReportUserParameter parameter=reportUserPage.getParameter();
		
		SysUserInfo sysUserInfo=ParamerUtil.getSysUserFromSesson(request);
		List<City> citys=sysUserInfo.getCitys();
		List<Area> userareas=new ArrayList<Area>();
		if (parameter.getUsercity() == "") {
			if (citys == null || citys.size() == 0) {
				citys = new ArrayList<City>();
			} else if (citys.size() == 1) {
				parameter.setUsercity(citys.get(0).getCityid());
				Map map = new HashMap();
				map.put("cityid", citys.get(0).getCityid());
				userareas=areaService.getAreas(map);
			}
		} else {
			if (citys.size() == 1) {
			} else if (citys.size() > 1) {
				Map map = new HashMap();
				map.put("cityid", parameter.getUsercity());
				userareas = areaService.getAreas(map);
			}
		}
		
		int adminType=ParamerUtil.getAdminType(sysUserInfo.getAdminpower());
		
		List<City> usercitys=sysUserInfo.getCitys();
		/*
		if(adminType==1){
			reportUserPage.setAdmincity(citys.get(0).getCityid());
			
			Map map=new HashMap();
			map.put("cityid", parameter.getUsercity());
			userareas=areaService.getAreas(map);
		}else if(adminType==2){
		//	reportUserPage.setAdminareas(sysUserInfo.getAreas());
			reportUserPage.setAdmincitys(sysUserInfo.getCitys());
			userareas=sysUserInfo.getAreas();
		}
		*/
		
		List<ReportUser> reportUsers=reportService.getReportUsers(reportUserPage);
		reportUserPage.setReportUser(reportUsers);
		
		
		reportUserPage.setUsercitys(usercitys);
		reportUserPage.setUserareas(userareas);
		//设置页面
		PageUtil pageUtil=new PageUtil(reportUserPage.getPageno(),reportUserPage.getPagerowtotal());
		reportUserPage.setPageinfo(pageUtil.getPage());
		return new ModelAndView("/report/user_report","reportTxPage",reportUserPage);
	}
	
	
	
	@RequestMapping("/dc_users")
	@ResponseBody
	public void dcUsers(HttpServletRequest request,
			HttpServletResponse response, ReportUserPage reportUserPage)  throws Exception {
		
		try {
			if (reportUserPage.getParameter().getUsername() != null) {
				reportUserPage.getParameter().setUsername(new String(reportUserPage.getParameter().getUsername() .getBytes("ISO8859-1"), "utf-8"));
			}
		} catch (UnsupportedEncodingException e) {
		}
		String filename="用户报表.xls";
		ExcelDC ecDc = new ExcelDC(request, "report_user.xls");
		List<Map> list = reportService.getDCReportUsers(reportUserPage);
		
		HSSFWorkbook workbook = ecDc.getReportUsers(list);
		 ByteArrayOutputStream os = new ByteArrayOutputStream();

	        try {
	        	workbook.write(os);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		  byte[] content = os.toByteArray();
	        InputStream is = new ByteArrayInputStream(content);

	       filename=new String(filename.getBytes(), "iso-8859-1");
	        // 设置response参数，可以打开下载页面
	        response.reset();
	        response.setContentType("application/vnd.ms-excel;charset=utf-8");
	        response.setHeader("Content-Disposition", "attachment;filename="+filename+"");

	        ServletOutputStream out = response.getOutputStream();

	        BufferedInputStream bis = null;
	        BufferedOutputStream bos = null;

	        try {

	            bis = new BufferedInputStream(is);
	            bos = new BufferedOutputStream(out);

	            byte[] buff = new byte[2048];
	            int bytesRead;

	            // Simple read/write loop.
	            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
	                bos.write(buff, 0, bytesRead);
	            }

	        } catch (final IOException e) {
	            throw e;
	        } finally {
	            if (bis != null)
	                bis.close();
	            if (bos != null)
	                bos.close();
	        }
	}
	
}
