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
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sdtower.common.bean.Area;
import com.sdtower.common.bean.City;
import com.sdtower.common.bean.DataTower;
import com.sdtower.common.bean.DataTowerPage;
import com.sdtower.common.bean.DataTowerParameter;
import com.sdtower.common.bean.DataUser;
import com.sdtower.common.bean.DataUserPage;
import com.sdtower.common.bean.IndexPage;
import com.sdtower.common.bean.QueryTerms;
import com.sdtower.common.bean.SysUserInfo;
import com.sdtower.common.bean.TxPage;
import com.sdtower.common.bean.UserParameter;
import com.sdtower.common.util.ExcelDC;
import com.sdtower.common.util.PageUtil;
import com.sdtower.common.util.PageUtil1;
import com.sdtower.common.util.ParamerUtil;
import com.sdtower.common.util.ResultUtil;
import com.sdtower.common.util.StrUtil;
import com.sdtower.service.AreaService;
import com.sdtower.service.LoginService;
import com.sdtower.service.TowerService;
import com.sdtower.service.UserService;

@Controller
@RequestMapping("/")
public class LoginWeb {

	@Autowired
	private LoginService loginService;
	
	@Autowired
	private TowerService towerService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AreaService areaService;

	@RequestMapping("/login")
	public ModelAndView getLogin() {

		return new ModelAndView("/login");
	}

	@RequestMapping("/footer")
	public ModelAndView getFooter() {

		return new ModelAndView("/footer");
	}

	@RequestMapping("/top")
	public ModelAndView getTop(HttpServletRequest request) {
		SysUserInfo userInfo=ParamerUtil.getSysUserFromSesson(request);
		//int roleid=userInfo.getAdminpower();
		//获取权限列表
		String powerw=","+userInfo.getAdminpowerw()+",";
		Map map=new HashMap();
		map.put("name", userInfo.getAdminid());
		map.put("powerw", powerw);
		return new ModelAndView("/top","info",map);
	}
	
	@RequestMapping("/service")
	public ModelAndView getService(HttpServletRequest request) {
		return new ModelAndView("/service");
	}

	@RequestMapping("/go_login")
	@ResponseBody
	public Object login(HttpServletRequest request,
			@RequestParam(value = "userid") String adminid,
			@RequestParam(value = "userpsd") String password) {
		// 验证用户
		Map map = new HashMap();
		map.put("adminid", adminid);
		map.put("password", password);
		SysUserInfo sysUserInfo = loginService.getLoginInfo(map);
		if (sysUserInfo == null) {
			return ResultUtil.generateResponseMsg("error", StrUtil.login_error);
		}

		ParamerUtil.setSysUserInSesson(request, sysUserInfo);

		return ResultUtil.generateResponseMsg("success");
	}

	@RequestMapping("/main")
	public ModelAndView getMain() {

		return new ModelAndView("/main");
	}

	@RequestMapping("/left")
	public ModelAndView getLeft(HttpServletRequest request) {
		SysUserInfo userInfo=ParamerUtil.getSysUserFromSesson(request);
		//int roleid=userInfo.getAdminpower();
		//获取权限列表
		String powerw=","+userInfo.getAdminpowerw()+",";
		return new ModelAndView("/left","powerw",powerw);
	}

	@RequestMapping("/index")
	public ModelAndView getIndex(HttpServletRequest request, IndexPage indexpage) {
		if(indexpage==null)
			indexpage=new IndexPage();
		SysUserInfo userInfo=ParamerUtil.getSysUserFromSesson(request);
		indexpage.setUser(userInfo);
		indexpage.setMessages(loginService.getTopMessage(indexpage));
		indexpage.setNotices(loginService.getTopNotice(indexpage));
		//获取统计信息
		Map map = new HashMap();
	//	map.put("admincity", userInfo.getAdmincityid());
	//	map.put("adminareas", userInfo.getAreas());
	//	map.put("adminpower", userInfo.getAdminpower());
		map.put("admincitys", userInfo.getCitys());
		indexpage.setUsercount(loginService.getUsercount(map));
		indexpage.setTowercount(loginService.getTowercount(map));
		indexpage.setIngordercount(loginService.getIngOrdercount(map));
		indexpage.setSuccordercount(loginService.getSuccOrdercount(map));
		
		return new ModelAndView("/index1","indexpage",indexpage);
	}
	
	@RequestMapping("/report")
	public ModelAndView getReport() {

		return new ModelAndView("/report");
	}
	
	@RequestMapping("/data")
	public ModelAndView getData() {

		return new ModelAndView("/data");
	}
	
	@RequestMapping("/toweranalysis")
	public ModelAndView getDataTower(HttpServletRequest request,DataTowerPage datatowerPage) {
		if(datatowerPage==null)
			datatowerPage=new DataTowerPage();
		
		DataTowerParameter parameter=datatowerPage.getParameter();
		SysUserInfo sysUserInfo=ParamerUtil.getSysUserFromSesson(request);
		List<City> citys=sysUserInfo.getCitys();
		List<Area> towerareas=new ArrayList<Area>();
		List<City> towercitys=sysUserInfo.getCitys();
		if (parameter.getTowercity() == "") {
			if (citys == null || citys.size() == 0) {
				citys = new ArrayList<City>();
			} else if (citys.size() == 1) {
				parameter.setTowercity(citys.get(0).getCityid());
				Map map = new HashMap();
				map.put("cityid", citys.get(0).getCityid());
				towerareas=areaService.getAreas(map);
			}
		} else {
			if (citys.size() == 1) {
			} else if (citys.size() > 1) {
				Map map = new HashMap();
				map.put("cityid", parameter.getTowercity());
				towerareas = areaService.getAreas(map);
			}
		}
		
		int adminType=ParamerUtil.getAdminType(sysUserInfo.getAdminpower());
		
		List<City> usercitys=sysUserInfo.getCitys();
		/*
		if(adminType==1){
			datatowerPage.setAdminCity(citys.get(0).getCityid());
			
			Map map=new HashMap();
			map.put("cityid", parameter.getTowercity());
			towerareas=areaService.getAreas(map);
		}else if(adminType==2){
			//datatowerPage.setAdminareas(sysUserInfo.getAreas());
			datatowerPage.setAdmincitys(sysUserInfo.getCitys());
			towerareas=sysUserInfo.getAreas();
		}
		*/
		List<DataTower> towers=towerService.getDataTowers(datatowerPage);
		datatowerPage.setDatatowers(towers);
		datatowerPage.setTowercitys(towercitys);
		datatowerPage.setTowerareas(towerareas);
		//设置页面
		PageUtil pageUtil=new PageUtil(datatowerPage.getPageNo(),datatowerPage.getPagerowtotal());
		datatowerPage.setPageinfo(pageUtil.getPage());
		return new ModelAndView("/toweranalysis","datatowerPage",datatowerPage);
	}
	
	@RequestMapping("/useranalysis")
	public ModelAndView getDataUser(HttpServletRequest request,DataUserPage datauserPage) {
		if(datauserPage==null)
			datauserPage=new DataUserPage();
		
		UserParameter parameter=datauserPage.getParameter();
		SysUserInfo sysUserInfo=ParamerUtil.getSysUserFromSesson(request);
		List<City> citys=sysUserInfo.getCitys();
		List<Area> userareas=new ArrayList<Area>();
		List<City> usercitys=sysUserInfo.getCitys();
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
		
		/*
		if(adminType==1){
			datauserPage.setAdminCity(citys.get(0).getCityid());
			
			Map map=new HashMap();
			map.put("cityid", parameter.getUsercity());
			userareas=areaService.getAreas(map);
		}else if(adminType==2){
			//datauserPage.setAdminareas(sysUserInfo.getAreas());
			datauserPage.setAdmincitys(sysUserInfo.getCitys());
			userareas=sysUserInfo.getAreas();
		}
		*/
		
		List<DataUser> datausers=userService.getDataUsers(datauserPage);
		datauserPage.setDatausers(datausers);
		datauserPage.setUsercitys(usercitys);
		datauserPage.setUserareas(userareas);
		//设置页面
		PageUtil pageUtil=new PageUtil(datauserPage.getPageNo(),datauserPage.getPagerowtotal());
		datauserPage.setPageinfo(pageUtil.getPage());
		return new ModelAndView("/useranalysis","datauserPage",datauserPage);
	}
	
	@RequestMapping("/dc_datatowers")
	@ResponseBody
	public void dcTowers(HttpServletRequest request,
			HttpServletResponse response,DataTowerPage datatowerPage)   throws Exception {
		ExcelDC ecDc = new ExcelDC(request, "data_tower.xls");
		List<Map> list = towerService.getDcDataTowers(datatowerPage);
		HSSFWorkbook workbook = ecDc.getDataTowers(list);
		 ByteArrayOutputStream os = new ByteArrayOutputStream();

	        try {
	        	workbook.write(os);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		  byte[] content = os.toByteArray();
	        InputStream is = new ByteArrayInputStream(content);

	        // 设置response参数，可以打开下载页面
	        response.reset();
	        response.setContentType("application/vnd.ms-excel;charset=utf-8");
	        response.setHeader("Content-Disposition", "attachment;filename=tower_data.xls");

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

	
	@RequestMapping("/dc_datausers")
	@ResponseBody
	public void dcTowers(HttpServletRequest request,
			HttpServletResponse response,DataUserPage datauserPage)   throws Exception {
		ExcelDC ecDc = new ExcelDC(request, "data_user.xls");
		List<Map> list = userService.getDcDataUsers(datauserPage);
		HSSFWorkbook workbook = ecDc.getDataUsers(list);
		 ByteArrayOutputStream os = new ByteArrayOutputStream();

	        try {
	        	workbook.write(os);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		  byte[] content = os.toByteArray();
	        InputStream is = new ByteArrayInputStream(content);

	        // 设置response参数，可以打开下载页面
	        response.reset();
	        response.setContentType("application/vnd.ms-excel;charset=utf-8");
	        response.setHeader("Content-Disposition", "attachment;filename=data_user_info.xls");

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
