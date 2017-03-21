package com.sdtower.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.functors.IfClosure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sdtower.common.bean.AddTowerInfo;
import com.sdtower.common.bean.Area;
import com.sdtower.common.bean.City;
import com.sdtower.common.bean.QueryTerms;
import com.sdtower.common.bean.SysUserInfo;
import com.sdtower.common.util.PageUtil;
import com.sdtower.common.util.PageUtil1;
import com.sdtower.common.util.ParamerUtil;
import com.sdtower.common.util.ResultUtil;
import com.sdtower.common.util.StrUtil;
import com.sdtower.service.InfoService;
import com.sdtower.service.LoginService;

@Controller
@RequestMapping("/personalSettings")
public class PersonalSettingsWeb {

	@Autowired
	private InfoService infoService;

	@RequestMapping("/info")
	public ModelAndView personalSettings(HttpServletRequest request) {

		SysUserInfo adminInfo = ParamerUtil.getSysUserFromSesson(request);
		int adminType=ParamerUtil.getAdminType(adminInfo.getAdminpower());
		String amdminArea = "";
		if(adminType==0){//超级管理员
			amdminArea = "全部-全部";
		}else if(adminType==1){//地市管理员
			String cityname = adminInfo.getCitys().get(0).getCityname();
			amdminArea = cityname + "-全部";
		}else if(adminType==2){//地区管理员
			String cityname = adminInfo.getCitys().get(0).getCityname();
			//List<Area> areas = adminInfo.getAreas();
			List<City> cities=adminInfo.getCitys();
			StringBuffer areaBuffer = new StringBuffer();
			int count = cities.size();
			for (int i=0; i<count; i++) {
				City city = cities.get(i);
				if(i == count-1){
					areaBuffer.append(city.getCityname());
				} else {
					areaBuffer.append(city.getCityname()).append("、");
				}
			}
			amdminArea =  areaBuffer.toString();
		}
		
		System.out.println("amdminArea="+amdminArea);
		
		Map sysuserInfo = new HashMap();
		sysuserInfo.put("adminId", adminInfo.getAdminid());
		sysuserInfo.put("amdminArea", amdminArea);
		sysuserInfo.put("amdminName", adminInfo.getAdminname());
		sysuserInfo.put("amdminPhone", adminInfo.getAdminphone());
		
		return new ModelAndView("/personalSettings/info", "sysuserInfo", sysuserInfo);
	}
	
	@RequestMapping("/updateinfo")
	@ResponseBody
	public Object updateinfo(HttpServletRequest request,String name,String phone) {
		
		SysUserInfo adminInfo = ParamerUtil.getSysUserFromSesson(request);
		
		Map map = new HashMap();
		if (adminInfo != null) {
			int id = adminInfo.getId();
			
			//String oldpsd = request.getParameter("oldpsd");
			//String newpsd = request.getParameter("newpsd");
			//String againpsd = request.getParameter("againpsd");
			
			//map.put("oldpsd", oldpsd);
			//map.put("newpsd", newpsd);
			//map.put("againpsd", againpsd);
			map.put("name", name);
			map.put("phone", phone);
			map.put("id", id);
		}
		int updateResult = 0;
		
		try {
			updateResult = infoService.updateInfo(map);
			adminInfo.setAdminname(name);
			adminInfo.setAdminphone(phone);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (updateResult != 1) {
			return ResultUtil.generateResponseMsg("error", StrUtil.update_info_error);
			
		}
		return ResultUtil.generateResponseMsg("success");
	}

	
	@RequestMapping("/updatePwd")
	@ResponseBody
	public Object updatePwd(HttpServletRequest request,String newpsd,String oldpsd) {
		
		SysUserInfo adminInfo = ParamerUtil.getSysUserFromSesson(request);
		
		Map map = new HashMap();
		if (adminInfo != null) {
			int id = adminInfo.getId();
			map.put("oldpsd", oldpsd);
			map.put("newpsd", newpsd);
			map.put("id", id);
		}
		int updateResult = 0;
		updateResult=infoService.checkPwd(map);
		if(updateResult==0){
			return ResultUtil.generateResponseMsg("error", StrUtil.update_admin_pwd_error);
		}
		
		try {
			updateResult = 0;
			updateResult = infoService.updatePwd(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (updateResult != 1) {
			return ResultUtil.generateResponseMsg("error", StrUtil.update_info_error);
			
		}
		return ResultUtil.generateResponseMsg("success");
	}
}
