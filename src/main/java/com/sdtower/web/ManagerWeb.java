package com.sdtower.web;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sdtower.common.bean.AdminManagerInfo;
import com.sdtower.common.bean.Area;
import com.sdtower.common.bean.BindUser;
import com.sdtower.common.bean.BindUserPage;
import com.sdtower.common.bean.City;
import com.sdtower.common.bean.ManagerPage;
import com.sdtower.common.bean.ManagerPower;
import com.sdtower.common.bean.ManagerQueryItems;
import com.sdtower.common.bean.Power;
import com.sdtower.common.bean.SysUserInfo;
import com.sdtower.common.bean.TxPage;
import com.sdtower.common.bean.TxParameter;
import com.sdtower.common.bean.TxRecord;
import com.sdtower.common.util.PageUtil;
import com.sdtower.common.util.ParamerUtil;
import com.sdtower.common.util.ResultUtil;
import com.sdtower.service.AreaService;
import com.sdtower.service.ManagerService;

@Controller
@RequestMapping("/manager")
public class ManagerWeb {

	@Autowired
	private ManagerService managerService;
	
	@Autowired
	private AreaService areaService;

	@RequestMapping("/manager")
	public ModelAndView managerAdmins(HttpServletRequest request, ManagerPage managerPage) {
		
		ManagerQueryItems parameter=managerPage.getQueryItem();
		SysUserInfo sysUserInfo=ParamerUtil.getSysUserFromSesson(request);
		List<City> citys = sysUserInfo.getCitys();
		List<Area> areas = new ArrayList<Area>();// sysUserInfo.getAreas();
		
		String cityid=managerPage.getQueryItem().getCityId();
		
		//判断角色
		if(cityid==""){
		int adminType=ParamerUtil.getAdminType(sysUserInfo.getAdminpower());
		if(adminType==1){
			managerPage.setAdminCity(citys.get(0).getCityid());
			 areas=sysUserInfo.getAreas();
			//managerPage.setAdminareas(sysUserInfo.getAreas());
		}else if(adminType==2){
		    areas=sysUserInfo.getAreas();
			managerPage.setAdminareas(sysUserInfo.getAreas());
		}
		}else{
			if(parameter.getCityId()!=null&&!parameter.getCityId().equals("")){
				Map map=new HashMap();
				map.put("cityid", parameter.getCityId());
				areas=areaService.getAreas(map);
			}
		}
		
		managerPage.setUsercitys(citys);
		managerPage.setUserareas(areas);
		
		try{
		 	List<ManagerPower> powers = managerService.getManagerPowers(managerPage);
		 	managerPage.setPowers(powers);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try{
			List<AdminManagerInfo> admins = managerService.getAdmins(managerPage);
			managerPage.setAdmins(admins);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		try{
			int adminCount = managerService.getSysUsersCount(managerPage);
			managerPage.setAdminCount(adminCount);
			System.out.println("adminCount="+adminCount);
		} catch(Exception e) {
			
		}
		
		// 设置页面
	    PageUtil pageUtil = new PageUtil(managerPage.getPageNo(),
	    		managerPage.getAdminCount());
	    managerPage.setPageinfo(pageUtil.getPage());
	    managerPage.setAdminid(sysUserInfo.getAdminid());
	    managerPage.setPower(sysUserInfo.getAdminpower());
		
		return new ModelAndView("/manager/manager", "managerPage", managerPage);
	}
	
	@RequestMapping("/editadmin")
	public ModelAndView editadmin(HttpServletRequest request,int id) {

		AdminManagerInfo admin = managerService.getAdmin(id);
		if (admin == null) {
			return null;
		}
		
		
		
		
		SysUserInfo sysUserInfo=ParamerUtil.getSysUserFromSesson(request);
		//获取管理员自已管辖的权限
		Map map=new HashMap();
		map.put("id",sysUserInfo.getAdminpower());
		List<Power> childPowers=managerService.getChildPowers(map);
		//获取管理员自己
		ManagerPage managerPage = new ManagerPage();
		managerPage.setAdmin(admin);
		
		List<City> citys=sysUserInfo.getCitys();
		//List<Area> areas=new ArrayList<Area>();
		
		int adminType=ParamerUtil.getAdminType(sysUserInfo.getAdminpower());
		/*
		List<Area> userareas=new ArrayList<Area>();//用户自己权限
		if(adminType==1){
			managerPage.setAdminCity(citys.get(0).getCityid());
		}else if(adminType==2){
			
			managerPage.setAdminareas(sysUserInfo.getAreas());
		}
		int usertype=ParamerUtil.getAdminType(admin.getAdminpower());
		if(usertype==2){
			String areaids=admin.getAdminarea();
			if(areaids.endsWith(","))
				areaids=areaids.substring(0,areaids.length()-1);
			String [] ids=areaids.split(",");
			map.put("areaids", ids);
			userareas=areaService.getAreas(map);
			//获取除了用户区域外的内容，放到select1
			map.put("cityid",admin.getAdminareacity());
			managerPage.setOtherareas(areaService.getOtherAreas(map));
			
		}else if(usertype==1){
			managerPage.setOtherareas(sysUserInfo.getAreas());
		}
		//List<City> usercitys=areaService.getCitys(null);
		List<City> usercitys=sysUserInfo.getCitys();
		
		*/
		List<City>  usercitys=areaService.getCitys(null);
		if(admin.getAdminpower()==3||admin.getAdminpower()==7){
			String cityStr=admin.getAdminareacity();
			String [] citysStr=cityStr.split(",");
					
			for (City city : usercitys) {
				for (int i = 0; i < citysStr.length; i++) {
					if(city.getCityid().equals(citysStr[i])){
						city.setIscheck(1);
					}
				}
			
		}
		
		}	
		managerPage.setUsercitys(usercitys);
		//managerPage.setUserareas(userareas);
		managerPage.setChildPowers(childPowers);
		//获取
		
		return new ModelAndView("/manager/editmanager", "managerPage", managerPage);
	}
	
	@RequestMapping("/updateadmin")
	@ResponseBody
	public Object updateadmin(HttpServletRequest request, String name, int role, String user_city, String user_area,int id) {

		Map map=new HashMap();
		map.put("id", id);
		map.put("adminname", name);
		map.put("adminpower", role);
		map.put("adminarea", user_area);
		map.put("adminareacity", user_city);
		if(role==3||role==7){
			map.put("adminareacity", user_area);
			}
			
		int count=0;
		try {
			count =managerService.changeAdmin(map);
		} catch (Exception e) {
			
		}
		if(count==0)
			return ResultUtil.generateResponseMsg("error");
		return ResultUtil.generateResponseMsg("success");
	}
	
	@RequestMapping("/addadmin")
	public ModelAndView addAdmin(HttpServletRequest request) {

		AdminManagerInfo admin = new AdminManagerInfo();
		ManagerPage managerPage = new ManagerPage();
		managerPage.setAdmin(admin);
		SysUserInfo sysUserInfo=ParamerUtil.getSysUserFromSesson(request);
		//获取管理员自已管辖的权限
		Map map=new HashMap();
		map.put("id",sysUserInfo.getAdminpower());
		List<Power> childPowers=managerService.getChildPowers(map);
		//获取管理员自己
		//List<City> citys=sysUserInfo.getCitys();
	//	List<Area> areas=new ArrayList<Area>();
		List<City> usercitys=new ArrayList<City>();
		int adminType=ParamerUtil.getAdminType(sysUserInfo.getAdminpower());
		usercitys=areaService.getCitys(null);
		/*
		if(adminType==0){
			//默认角色选中城市管理员
			managerPage.getAdmin().setAdminpower(2);
			usercitys=sysUserInfo.getCitys();
		}
		else if(adminType==1){
			managerPage.getAdmin().setAdminpower(3);
			usercitys=sysUserInfo.getCitys();
			managerPage.setOtherareas(sysUserInfo.getAreas());
		}else if(adminType==2){
			managerPage.setAdminareas(sysUserInfo.getAreas());
			
		}
		*/
		
		List<Area> userareas=new ArrayList<Area>();
		managerPage.setUsercitys(usercitys);
		managerPage.setUserareas(userareas);
		managerPage.setChildPowers(childPowers);
		return new ModelAndView("/manager/addmanager", "managerPage", managerPage);
	}
	
	@RequestMapping("/addadmin1")
	public ModelAndView addAdmin1(HttpServletRequest request) {

		AdminManagerInfo admin = new AdminManagerInfo();
		ManagerPage managerPage = new ManagerPage();
		managerPage.setAdmin(admin);
		SysUserInfo sysUserInfo=ParamerUtil.getSysUserFromSesson(request);
		List<City> citys=sysUserInfo.getCitys();
		List<Area> areas=new ArrayList<Area>();
		
		int adminType=ParamerUtil.getAdminType(sysUserInfo.getAdminpower());
		if(adminType==1){
			managerPage.setAdminCity(citys.get(0).getCityid());
		}else if(adminType==2){
			managerPage.setAdminareas(sysUserInfo.getAreas());
		}
		List<City> usercitys=areaService.getCitys(null);
		List<Area> userareas=new ArrayList<Area>();
		managerPage.setUsercitys(usercitys);
		managerPage.setUserareas(userareas);
		return new ModelAndView("/manager/addmanager", "managerPage", managerPage);
	}
	
	@RequestMapping("/add_admin")
	@ResponseBody
	public Object addadmin(HttpServletRequest request, String adminid, String name, int role, String user_city, String user_area) {

		Map map=new HashMap();
		map.put("adminid", adminid);
		map.put("adminname", name);
		map.put("adminpower", role);
		map.put("adminarea", user_area);
		if(role==3||role==7){
		map.put("adminareacity", user_area);
		}
		
		int count=0;
		count=managerService.checkAdminidExist(adminid);
		if(count>0){
			return ResultUtil.generateResponseMsg("error","管理员账号名已存在");
		}
		
		try {
			count =managerService.addAdmin(map);
		} catch (Exception e) {
			
		}
		if(count==0)
			return ResultUtil.generateResponseMsg("error","添加失败");
		return ResultUtil.generateResponseMsg("success");
	}
	
	@RequestMapping("/del_admin")
	@ResponseBody
	public Object deladmin(HttpServletRequest request,String ids) {
		Map map=new HashMap();
		map.put("ids", ids);
		
		int count=0;
		try {
			count =managerService.deleteAdmin(map);
		} catch (Exception e) {
			
		}
		if(count==0)
			return ResultUtil.generateResponseMsg("error");
		return ResultUtil.generateResponseMsg("success");
	}
	
	@RequestMapping("/resetpwd")
	@ResponseBody
	public Object resetpwd(HttpServletRequest request,int id) {
		int count=0;
		try {
			count =managerService.changeAdminPwd(id);
		} catch (Exception e) {
			
		}
		if(count==0)
			return ResultUtil.generateResponseMsg("error");
		return ResultUtil.generateResponseMsg("success");
	}
	
	
	@RequestMapping("/get_bind_users")
	public ModelAndView getBindUsers(HttpServletRequest request,BindUserPage bindUserPage) {
		
		managerService.getBindsUsers(bindUserPage);
		// 设置页面
				PageUtil pageUtil = new PageUtil(bindUserPage.getPageno(),
			bindUserPage.getPagerowtotal());
				bindUserPage.setPageinfo(pageUtil.getPage());
		return new ModelAndView("/manager/bind_user","bindUserPage",bindUserPage);
	}
	
	@RequestMapping("/binduser")
	@ResponseBody
	public Object bindUser(int adminid,String wxid){
		Map map=new HashMap();
		map.put("id",adminid);
		map.put("wxid",wxid);
		int count=managerService.bindUser(map);
		if(count==0)
			return ResultUtil.generateResponseMsg("error");
		return ResultUtil.generateResponseMsg("success");
	}
	
	@RequestMapping("/unbinduser")
	@ResponseBody
	public Object unBindUser(int adminid){
		
		Map map=new HashMap();
		map.put("id",adminid);
		int count=managerService.unBindUser(map);
		if(count==0)
			return ResultUtil.generateResponseMsg("error");
		return ResultUtil.generateResponseMsg("success");
	}
	
}
