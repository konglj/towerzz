package com.sdtower.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sdtower.common.Config;
import com.sdtower.common.bean.Area;
import com.sdtower.common.bean.City;
import com.sdtower.common.bean.Order;
import com.sdtower.common.bean.OrderPage;
import com.sdtower.common.bean.OrderParameter;
import com.sdtower.common.bean.SendNoticeParameter;
import com.sdtower.common.bean.SysUserInfo;
import com.sdtower.common.bean.TwNotice;
import com.sdtower.common.bean.TwNoticePage;
import com.sdtower.common.bean.TwNoticeParameter;
import com.sdtower.common.bean.TwNoticeSend;
import com.sdtower.common.bean.UserType;
import com.sdtower.common.util.FileUpload;
import com.sdtower.common.util.HttpRequst;
import com.sdtower.common.util.PageUtil;
import com.sdtower.common.util.ParamerUtil;
import com.sdtower.common.util.ResultUtil;
import com.sdtower.service.AreaService;
import com.sdtower.service.QRCodeService;
import com.sdtower.service.TwnoticeService;
import com.sdtower.service.UserService;

@Controller
@RequestMapping("/twnotice")
public class TwNoticeWeb {

	@Autowired
	private TwnoticeService twnoticeService;
	
	@Autowired
	private QRCodeService qrCodeService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AreaService areaService;
	
	@RequestMapping("/notice")
	public ModelAndView getTwNotices(HttpServletRequest request,TwNoticePage twNoticePage){
		
		if (twNoticePage == null)
			twNoticePage = new TwNoticePage();

		TwNoticeParameter parameter = twNoticePage.getParameter();
		SysUserInfo sysUserInfo = ParamerUtil.getSysUserFromSesson(request);
		List<City> citys = sysUserInfo.getCitys();
		int adminType = ParamerUtil.getAdminType(sysUserInfo.getAdminpower());
		if (adminType == 1) {
			twNoticePage.setAdmincity(citys.get(0).getCityid());
		} else if (adminType == 2) {
			twNoticePage.setAdminareas(sysUserInfo.getAreas());
		}
		twnoticeService.getTwNotices(twNoticePage);
		if(twNoticePage.getTwNotices()!=null&&twNoticePage.getTwNotices().size()>0){
			String path = request.getSession().getServletContext()
					.getRealPath("/");
			setEwm(path,twNoticePage.getTwNotices());
			
		}
		
		// 设置页面
		PageUtil pageUtil = new PageUtil(twNoticePage.getPageno(),
				twNoticePage.getPagerowtotal());
		twNoticePage.setPageinfo(pageUtil.getPage());
		return new ModelAndView("/twnotice/noticemanage","twNoticePage",twNoticePage);
	}
	
	@RequestMapping("/editnotice")
	public ModelAndView editTwNotice(HttpServletRequest request,int noticeid){
		TwNotice twNotice=null;
		if(noticeid!=0){
			twNotice=twnoticeService.getTwNotice(noticeid);
		}
		if(twNotice==null)
			twNotice=new TwNotice();
		return new ModelAndView("/twnotice/editnotice","twNotice",twNotice);
	}
	
	@RequestMapping("/save_notice")
	@ResponseBody
	public Object addTwNotice(HttpServletRequest request,TwNotice twNotice){
		SysUserInfo userInfo=ParamerUtil.getSysUserFromSesson(request);
		twNotice.setAdduser(userInfo.getId());
		//twNotice.setUrlroot(Config.getWebip());
		int count=0;
		try {
			
		
		if(twNotice.getId()==0)
		   count=twnoticeService.insertTwNotice(twNotice);
		else
			count=twnoticeService.updateTwNotice(twNotice);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(count==0)
			ResultUtil.generateResponseMsg("error","保存失败");
		return ResultUtil.generateResponseMsg("success");
	}
	
	@RequestMapping("/del_notice")
	@ResponseBody
	public Object delTwNotice(HttpServletRequest request,int id){
		int count=0;
		try {
			
		
		   count=twnoticeService.delTwNotice(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(count==0)
			return ResultUtil.generateResponseMsg("error","删除失败");
		return ResultUtil.generateResponseMsg("success");
	}
	
	@RequestMapping("/view_notice")
	public ModelAndView getTwNoticeInfo(HttpServletRequest request,int noticeid){
		TwNotice twNotice=null;
	    twNotice=twnoticeService.getTwNoticeInfo(noticeid);
		if(twNotice==null)
			twNotice=new TwNotice();
		return new ModelAndView("/twnotice/viewnotice","twNotice",twNotice);
	}
	
	
	@RequestMapping("/noticeinfo")
	public ModelAndView getTwNoticePhone(HttpServletRequest request,int noticeid){
		TwNotice twNotice=null;
			twNotice=twnoticeService.getTwNotice(noticeid);
		if(twNotice==null)
			twNotice=new TwNotice();
		return new ModelAndView("/twnotice/notice","twNotice",twNotice);
	}
	
	
	@RequestMapping("/sengmsg")
	public ModelAndView SendTwNotice(HttpServletRequest request,int noticeid){
		TwNotice twNotice=twnoticeService.getTwNotice(noticeid);
		List<UserType> userTypes=userService.getUserTypes();
		TwNoticePage twNoticePage=new TwNoticePage();
		twNoticePage.setUserTypes(userTypes);
		twNoticePage.setTwNotice(twNotice);
		SysUserInfo sysUserInfo = ParamerUtil.getSysUserFromSesson(request);
		List<City> citys = sysUserInfo.getCitys();
		int adminType = ParamerUtil.getAdminType(sysUserInfo.getAdminpower());
		twNoticePage.setAdmincitys(citys);
		if (adminType == 1) {
			twNoticePage.setAdmincity(citys.get(0).getCityid());
			twNoticePage.setAdminareas(sysUserInfo.getAreas());
		} else if (adminType == 2) {
			//twNoticePage.setAdminareas(sysUserInfo.getAreas());
			twNoticePage.setAdmincitys(sysUserInfo.getCitys());
		}
		twNoticePage.setProvince(Config.getProvince());
		//twNoticePage.setAdmintype(adminType);
		twNoticePage.setAdmintype(0);
		//获取用户类型
		return new ModelAndView("/twnotice/sendmsg","twNoticePage",twNoticePage);
	}
	
	

	@RequestMapping("/send")
	@ResponseBody
	public Object sendTwNotices(HttpServletRequest request,SendNoticeParameter parameter ){
		SysUserInfo sysUserInfo=ParamerUtil.getSysUserFromSesson(request);
		parameter.setSenduser(sysUserInfo.getId());
		parameter.setSendusername(sysUserInfo.getAdminname());
		parameter.setAdmintype(0);
		if(parameter.getAdmintype()==0){
			if(parameter.getCitys()==null||parameter.getCitys().size()==0)
				return ResultUtil.generateResponseMsg("error","请发送选择区域！");
		}else{
			if(parameter.getAreas()==null||parameter.getAreas().size()==0)
				return ResultUtil.generateResponseMsg("error","请发送选择区域！");
		}
		int count=0;
		try {
			count=twnoticeService.sendNotice(parameter);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(count==0)
			return ResultUtil.generateResponseMsg("error","发送失败！");
		
		//获取用户类型
		return ResultUtil.generateResponseMsg("success");
	}
	
	@RequestMapping("/send_list")
	public ModelAndView sendTwNoticeSendList(HttpServletRequest request,int noticeid ){
	    List<TwNoticeSend> list=twnoticeService.getTwNoticeSends(noticeid);
	    if(list==null)
	    	list=new ArrayList<TwNoticeSend>();
	    return new ModelAndView("/twnotice/send_info","sends",list);
		
	}
	
	private void setEwm(String path,List<TwNotice> list){
		for (TwNotice twNotice : list) {
			String ccbPath = path + "/assets/img/mid.png";
			String ewm="/images/ewm/" + twNotice.getId()+".jpg";
			String ewmPath = path +ewm;
			try {
				qrCodeService.QRCodeEncoder(ewmPath, "jpg", twNotice.getUrlroot(), 140);
				twNotice.setEwmurl(".."+ewm);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	@RequestMapping("/upload_image")
	@ResponseBody
	public Object  uploadImage(HttpServletRequest request){
		String filepath="images\\twnotice\\";
		String image=FileUpload.uploadFile(request, filepath, "imgFile");
		Map map=new HashMap();
		if(image==null){
			map.put("error", 1);
			map.put("message", "上传失败");
		}else{
			map.put("error", 0);
			String imagePath=Config.getSystemip().subSequence(0, Config.getSystemip().lastIndexOf("/"))+image.replace("\\", "/");
			map.put("url",imagePath );
		}
		
		return map;
	}
 }
