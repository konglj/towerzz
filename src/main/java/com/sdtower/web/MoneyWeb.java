package com.sdtower.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sdtower.common.bean.AddTowerInfo;
import com.sdtower.common.bean.Area;
import com.sdtower.common.bean.City;
import com.sdtower.common.bean.OrderPage;
import com.sdtower.common.bean.SysUserInfo;
import com.sdtower.common.bean.TxPage;
import com.sdtower.common.bean.TxParameter;
import com.sdtower.common.bean.TxRecord;
import com.sdtower.common.bean.UserManagePage;
import com.sdtower.common.bean.UserMoneyPage;
import com.sdtower.common.util.ExcelDC;
import com.sdtower.common.util.ExcelHelper;
import com.sdtower.common.util.FileUpload;
import com.sdtower.common.util.PageUtil;
import com.sdtower.common.util.ParamerUtil;
import com.sdtower.common.util.ResultUtil;
import com.sdtower.service.AreaService;
import com.sdtower.service.TxService;

@Controller
@RequestMapping("/money")
public class MoneyWeb {
	
	@Autowired
	private TxService txService;
	
	@Autowired
	private AreaService areaService;
	
	@RequestMapping("/tx")
	public ModelAndView getTxshs(HttpServletRequest request,TxPage txPage){
		if(txPage==null)
			txPage=new TxPage();
		
		TxParameter parameter=txPage.getParameter();
		if(parameter.getState()==-2){
			parameter.setState(0);
		}
		SysUserInfo sysUserInfo=ParamerUtil.getSysUserFromSesson(request);
		List<City> citys=sysUserInfo.getCitys();
	List<Area> areas=new ArrayList<Area>();//sysUserInfo.getAreas();
		
		if (parameter.getUsercity() == "") {
			if (citys == null || citys.size() == 0) {
				citys = new ArrayList<City>();
			} else if (citys.size() == 1) {
				txPage.getParameter().setUsercity(citys.get(0).getCityid());
		//		areas = sysUserInfo.getAreas();
				Map map = new HashMap();
				map.put("cityid", citys.get(0).getCityid());
				areas=areaService.getAreas(map);
			}
		} else {
			if (citys.size() == 1) {
			//	areas = sysUserInfo.getAreas();
			} else if (citys.size() > 1) {
				Map map = new HashMap();
				map.put("cityid", parameter.getUsercity());
			//	areas = areaService.getAreas(map);
			}
		}
		
		int adminType=ParamerUtil.getAdminType(sysUserInfo.getAdminpower());
		List<Area> userareas=new ArrayList<Area>();
		List<City> usercitys=sysUserInfo.getCitys();
		if(adminType==1){
			txPage.setAdmincity(citys.get(0).getCityid());
			
			Map map=new HashMap();
			map.put("cityid", parameter.getUsercity());
			userareas=areaService.getAreas(map);
		}else if(adminType==2){
			//txPage.setAdminareas(sysUserInfo.getAreas());
			txPage.setAdmincitys(sysUserInfo.getCitys());
			//userareas=sysUserInfo.getAreas();
			Map map=new HashMap();
			map.put("cityid", parameter.getUsercity());
			userareas=areaService.getAreas(map);
		}
		
		List<TxRecord> txrecords=txService.getTxs(txPage);
		txPage.setTxrecords(txrecords);
		
		
		if(parameter.getUsercity()!=null&&!parameter.getUsercity().equals("")){
			
			
		}
		txPage.setUsercitys(usercitys);
		txPage.setUserareas(userareas);
		//设置页面
		PageUtil pageUtil=new PageUtil(txPage.getPageno(),txPage.getPagerowtotal());
		txPage.setPageinfo(pageUtil.getPage());
		txPage.setAdminpower(sysUserInfo.getAdminpower());
		return new ModelAndView("/money/tx","txPage",txPage);
	}
	
	@RequestMapping("/txmanage")
	public ModelAndView getTxdks(HttpServletRequest request,TxPage txPage){
		if(txPage==null)
			txPage=new TxPage();
		
		TxParameter parameter=txPage.getParameter();
		if(parameter.getState()==-2){
			parameter.setState(1);
		}
		SysUserInfo sysUserInfo=ParamerUtil.getSysUserFromSesson(request);
		List<City> citys=sysUserInfo.getCitys();
		List<Area> areas=new ArrayList<Area>();//sysUserInfo.getAreas();

		if (parameter.getUsercity() == "") {
			if (citys == null || citys.size() == 0) {
				citys = new ArrayList<City>();
			} else if (citys.size() == 1) {
				txPage.getParameter().setUsercity(citys.get(0).getCityid());
			//areas = sysUserInfo.getAreas();
			Map map = new HashMap();
			map.put("cityid", citys.get(0).getCityid());
			areas=areaService.getAreas(map);
			}
		} else {
			if (citys.size() == 1) {
			//	areas = sysUserInfo.getAreas();
			} else if (citys.size() > 1) {
				Map map = new HashMap();
				map.put("cityid", parameter.getUsercity());
			//	areas = areaService.getAreas(map);
			}
		}
		
		int adminType=ParamerUtil.getAdminType(sysUserInfo.getAdminpower());
		if(adminType==1){
			txPage.setAdmincity(citys.get(0).getCityid());
		}else if(adminType==2){
			//txPage.setAdminareas(sysUserInfo.getAreas());
			txPage.setAdmincitys(sysUserInfo.getCitys());
		}
		
		List<TxRecord> txrecords=txService.getTxs(txPage);
		txPage.setTxrecords(txrecords);
		List<Area> userareas=new ArrayList<Area>();
		List<City> usercitys=sysUserInfo.getCitys();
		/*
		if(adminType==1){
			txPage.setAdmincity(citys.get(0).getCityid());
			
			Map map=new HashMap();
			map.put("cityid", parameter.getUsercity());
			userareas=areaService.getAreas(map);
		}else if(adminType==2){
			txPage.setAdminareas(sysUserInfo.getAreas());
			userareas=sysUserInfo.getAreas();
		}
		*/
		
		txPage.setUsercitys(usercitys);
		txPage.setUserareas(areas);
		//设置页面
		PageUtil pageUtil=new PageUtil(txPage.getPageno(),txPage.getPagerowtotal());
		txPage.setPageinfo(pageUtil.getPage());
		txPage.setAdminpower(sysUserInfo.getAdminpower());
		return new ModelAndView("/money/txmanage","txPage",txPage);
	}
	
	@RequestMapping("/tx_info")
	public ModelAndView getTxInfo(HttpServletRequest request,
			String txid) {

		TxRecord txrecord = txService.getTx(txid);
		if (txrecord == null) {
			return null;
		}
		TxPage txpage = new TxPage();
		txpage.setTxrecord(txrecord);
		return new ModelAndView("/money/tx_info", "txPage", txpage);
	}
	
	@RequestMapping("/txdk")
	public ModelAndView getTxdkInfo(HttpServletRequest request,
			String txid) {

		TxRecord txrecord = txService.getTx(txid);
		if (txrecord == null) {
			return null;
		}
		TxPage txpage = new TxPage();
		txpage.setTxrecord(txrecord);
		return new ModelAndView("/money/tx_dk", "txPage", txpage);
	}
	
	@RequestMapping("/txsh")
	public ModelAndView getTxshInfo(HttpServletRequest request,
			String txid) {

		TxRecord txrecord = txService.getTx(txid);
		if (txrecord == null) {
			return null;
		}
		TxPage txpage = new TxPage();
		txpage.setTxrecord(txrecord);
		return new ModelAndView("/money/tx_sh", "txPage", txpage);
	}

	@RequestMapping("/tx_sh")
	@ResponseBody
	public Object txSh(HttpServletRequest request,String txid,int result,String shinfo,int fee){
		
		Map map=new HashMap();
		map.put("txid", txid);
		map.put("state", result);
		map.put("shinfo", shinfo);
		map.put("fee", fee);
		
		SysUserInfo admin=ParamerUtil.getSysUserFromSesson(request);
		map.put("adminid", admin.getId());
		int count=0;
		try {
			count =txService.shTx(map);
		} catch (Exception e) {
			
		}
		if(count==0)
			return ResultUtil.generateResponseMsg("error");
		
		return ResultUtil.generateResponseMsg("success");
	}
	
	@RequestMapping("/tx_dk")
	@ResponseBody
	public Object txDk(HttpServletRequest request,String txid,int result,String shinfo,int fee){
		
		Map map=new HashMap();
		map.put("txid", txid);
		map.put("state", result);
		map.put("shinfo", shinfo);
		map.put("fee", fee);
		
		SysUserInfo admin=ParamerUtil.getSysUserFromSesson(request);
		map.put("adminid", admin.getId());
		int count=0;
		try {
			count =txService.dkTx(map);
		} catch (Exception e) {
			
		}
		if(count==0)
			return ResultUtil.generateResponseMsg("error");
		
		return ResultUtil.generateResponseMsg("success");
	}

	@RequestMapping("/h3")
	public ModelAndView getH3(){
	
		return new ModelAndView("/money/h3");
	}
	@RequestMapping("/tx_pl_insert")
	public ModelAndView getPl(){
	
		return new ModelAndView("/money/tx_pl_insert");
	}
	
	@RequestMapping("/tx_dr")
	@ResponseBody
	public Object txDR(HttpServletRequest request){
		String filepath = "\\tmp\\excel\\";
		String excel = FileUpload.uploadFile_excel(request, filepath,
				"file");
		if (excel == null)
			return ResultUtil.generateResponseMsg("error");
		int count = 0;
		List<Map> txs = null;
		SysUserInfo adminInfo = ParamerUtil.getSysUserFromSesson(request);
		try {
			txs = ExcelHelper.getTxRecode(excel);
		}catch(Exception e){
			
			return ResultUtil.generateResponseMsg("error");
		}
		SysUserInfo admin=ParamerUtil.getSysUserFromSesson(request);
		 count=txService.dkTx(txs, admin.getId());
		 if(count==0)
				return ResultUtil.generateResponseMsg("error");
			
			return ResultUtil.generateResponseMsg("success");
		
		
	}
	
	
	@RequestMapping("/dc_moneys")
	@ResponseBody
	public void dcMoney(HttpServletRequest request,
			HttpServletResponse response, TxPage txPage)  throws Exception {
		String filename="";
		ExcelDC ecDc = new ExcelDC(request, "tx_aply.xls");
		List<Map> list = txService.getDcTxs(txPage);
		HSSFWorkbook workbook = ecDc.getMoneys(list);
		 ByteArrayOutputStream os = new ByteArrayOutputStream();

	        try {
	        	workbook.write(os);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		  byte[] content = os.toByteArray();
	        InputStream is = new ByteArrayInputStream(content);

	       filename=new String(("提现申请.xls").getBytes(), "iso-8859-1");
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
