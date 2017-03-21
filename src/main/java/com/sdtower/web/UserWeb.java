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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sdtower.common.bean.Area;
import com.sdtower.common.bean.City;
import com.sdtower.common.bean.SysUserInfo;
import com.sdtower.common.bean.TxPage;
import com.sdtower.common.bean.TxRecord;
import com.sdtower.common.bean.UserInfo;
import com.sdtower.common.bean.UserManagePage;
import com.sdtower.common.bean.UserMoney;
import com.sdtower.common.bean.UserMoneyPage;
import com.sdtower.common.bean.UserParameter;
import com.sdtower.common.util.ExcelDC;
import com.sdtower.common.util.PageUtil;
import com.sdtower.common.util.ParamerUtil;
import com.sdtower.common.util.ResultUtil;
import com.sdtower.service.AreaService;
import com.sdtower.service.UserService;

@Controller
@RequestMapping("/user")
public class UserWeb {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AreaService areaService;
	
	@RequestMapping("/usermanage")
	public ModelAndView getUsers(HttpServletRequest request,UserManagePage userManagerPage){
		if(userManagerPage==null)
			userManagerPage=new UserManagePage();
		
		UserParameter parameter=userManagerPage.getParameter();
		SysUserInfo sysUserInfo=ParamerUtil.getSysUserFromSesson(request);
		List<City> citys=sysUserInfo.getCitys();
		//List<Area> areas=new ArrayList<Area>();//sysUserInfo.getAreas();
		
		
		if (parameter.getUsercity() == "") {
			if (citys == null || citys.size() == 0) {
				citys = new ArrayList<City>();
			} else if (citys.size() == 1) {
				userManagerPage.getParameter().setUsercity(citys.get(0).getCityid());
		//		areas = sysUserInfo.getAreas();
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
			userManagerPage.setAdmincity(citys.get(0).getCityid());
			
			Map map=new HashMap();
			map.put("cityid", parameter.getUsercity());
			userareas=areaService.getAreas(map);
		}else if(adminType==2){
			//userManagerPage.setAdminareas(sysUserInfo.getAreas());
			userManagerPage.setAdmincitys(sysUserInfo.getCitys());
			//userareas=sysUserInfo.getAreas();
			Map map=new HashMap();
			map.put("cityid", parameter.getUsercity());
			userareas=areaService.getAreas(map);
		}
		
		
		List<UserInfo> userinfos=userService.getUserInfos(userManagerPage);
		userManagerPage.setUserinfos(userinfos);
		if(parameter.getUsercity()!=null&&!parameter.getUsercity().equals("")){
			Map map=new HashMap();
			map.put("cityid", parameter.getUsercity());
			userareas=areaService.getAreas(map);
		}
		userManagerPage.setUsercitys(usercitys);
		userManagerPage.setUserareas(userareas);
		//设置页面
		PageUtil pageUtil=new PageUtil(userManagerPage.getPageno(),userManagerPage.getPageSize(),userManagerPage.getPagerowtotal());
		userManagerPage.setPageinfo(pageUtil.getPage());
		userManagerPage.setAdminpower(sysUserInfo.getAdminpower());
		return new ModelAndView("/user/usermanage","userManagerPage",userManagerPage);
	}
	
	@RequestMapping("/usermoney")
	public ModelAndView getUserMoneys(HttpServletRequest request,UserMoneyPage userMoneyPage){
		if(userMoneyPage==null)
			userMoneyPage=new UserMoneyPage();
		
		UserParameter parameter=userMoneyPage.getParameter();
		SysUserInfo sysUserInfo=ParamerUtil.getSysUserFromSesson(request);
		List<City> citys=sysUserInfo.getCitys();
		//List<Area> areas=new ArrayList<Area>();//sysUserInfo.getAreas();
		if (parameter.getUsercity() == "") {
			if (citys == null || citys.size() == 0) {
				citys = new ArrayList<City>();
			} else if (citys.size() == 1) {
				parameter.setUsercity(citys.get(0).getCityid());
		//		areas = sysUserInfo.getAreas();
			}
		} 
		int adminType=ParamerUtil.getAdminType(sysUserInfo.getAdminpower());
		List<Area> userareas=new ArrayList<Area>();
		List<City> usercitys=sysUserInfo.getCitys();
		if(adminType==1){
			userMoneyPage.setAdmincity(citys.get(0).getCityid());
			
			Map map=new HashMap();
			map.put("cityid", parameter.getUsercity());
			userareas=areaService.getAreas(map);
		}else if(adminType==2){
			//userMoneyPage.setAdminareas(sysUserInfo.getAreas());
			userMoneyPage.setAdmincitys(sysUserInfo.getCitys());
		//	userareas=sysUserInfo.getAreas();
			Map map=new HashMap();
			map.put("cityid", parameter.getUsercity());
			userareas=areaService.getAreas(map);
		}
		
		
		List<UserMoney> usermoneys=userService.getUserMoneys(userMoneyPage);
		userMoneyPage.setUsermoneys(usermoneys);
		if(parameter.getUsercity()!=null&&!parameter.getUsercity().equals("")){
			Map map=new HashMap();
			map.put("cityid", parameter.getUsercity());
			userareas=areaService.getAreas(map);
		}
		userMoneyPage.setUsercitys(usercitys);
		userMoneyPage.setUserareas(userareas);
		//设置页面
		PageUtil pageUtil=new PageUtil(userMoneyPage.getPageno(),userMoneyPage.getPageSize(),userMoneyPage.getPagerowtotal());
		userMoneyPage.setPageinfo(pageUtil.getPage());
		userMoneyPage.setAdminpower(sysUserInfo.getAdminpower());
		return new ModelAndView("/user/usermoney","userMoneyPage",userMoneyPage);
	}
	
	@RequestMapping("/user_info")
	public ModelAndView getUserInfo(HttpServletRequest request,String wxid) {

		UserInfo userinfo = userService.getUserdetail(wxid);
		if (userinfo == null) {
			return null;
		}
		UserManagePage usermanagePage = new UserManagePage();
		usermanagePage.setUserinfo(userinfo);
		return new ModelAndView("/user/user_info", "usermanagePage", usermanagePage);
	}
	
	@RequestMapping("/updatestate")
	@ResponseBody
	public Object updateState(HttpServletRequest request,String wxid, int state) {

		Map map=new HashMap();
		map.put("wxid", wxid);
		map.put("state", state);
		int count=0;
		try {
			count =userService.updateuserstate(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(count==0)
			return ResultUtil.generateResponseMsg("error");
		return ResultUtil.generateResponseMsg("success");
	}
	
	@RequestMapping("/modifytype")
	public ModelAndView modifytype(HttpServletRequest request,String wxid) {

		UserInfo userinfo = userService.getUserdetail(wxid);
		if (userinfo == null) {
			return null;
		}
		UserManagePage usermanagePage = new UserManagePage();
		usermanagePage.setUserinfo(userinfo);
		return new ModelAndView("/user/modifytype", "usermanagePage", usermanagePage);
	}
	
	@RequestMapping("/modifybz")
	public ModelAndView modifyBz(HttpServletRequest request,String wxid) {

		UserInfo userinfo = userService.getUserdetail(wxid);
		if (userinfo == null) {
			return null;
		}
		UserManagePage usermanagePage = new UserManagePage();
		usermanagePage.setUserinfo(userinfo);
		return new ModelAndView("/user/modifybz", "usermanagePage", usermanagePage);
	}
	
	
	
	@RequestMapping("/changetype")
	@ResponseBody
	public Object changestate(HttpServletRequest request,
			 String wxid, String usertype) {

		Map map=new HashMap();
		map.put("wxid", wxid);
		map.put("usertype", usertype);
		int count=0;
		try {
			count =userService.updateusertype(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(count==0)
			return ResultUtil.generateResponseMsg("error");
		return ResultUtil.generateResponseMsg("success");
	}
	
	@RequestMapping("/changebz")
	@ResponseBody
	public Object changebz(HttpServletRequest request,
			 String wxid, String userbz) {

		Map map=new HashMap();
		map.put("wxid", wxid);
		map.put("userbz", userbz);
		int count=0;
		try {
			count =userService.updateUserBz(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(count==0)
			return ResultUtil.generateResponseMsg("error");
		return ResultUtil.generateResponseMsg("success");
	}
	
	@RequestMapping("/h4")
	public ModelAndView getH4(){
	
		return new ModelAndView("/user/h4");
	}
	
	
	
	
	@RequestMapping("/dc_users")
	@ResponseBody
	public void dcUser(HttpServletRequest request,
			HttpServletResponse response, UserManagePage  usermanagepage)  throws Exception {
		String filename="";
		ExcelDC ecDc = new ExcelDC(request, "user_info.xls");
		List<Map> list = userService.getDcUser(usermanagepage);
		HSSFWorkbook workbook = ecDc.getUsers(list);
		 ByteArrayOutputStream os = new ByteArrayOutputStream();

	        try {
	        	workbook.write(os);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		  byte[] content = os.toByteArray();
	        InputStream is = new ByteArrayInputStream(content);

	       filename=new String(("用户信息.xls").getBytes(), "iso-8859-1");
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
	
	@RequestMapping("/dc_fee")
	@ResponseBody
	public void dcFee(HttpServletRequest request,
			HttpServletResponse response, UserMoneyPage  userMoneyPage)  throws Exception {
		String filename="";
		ExcelDC ecDc = new ExcelDC(request, "fee.xls");
		List<Map> list = userService.getDcUserFee(userMoneyPage);
		HSSFWorkbook workbook = ecDc.getUsersFee(list);
		 ByteArrayOutputStream os = new ByteArrayOutputStream();

	        try {
	        	workbook.write(os);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		  byte[] content = os.toByteArray();
	        InputStream is = new ByteArrayInputStream(content);

	       filename=new String(("用户钱包.xls").getBytes(), "iso-8859-1");
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
