package com.sdtower.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdtower.common.bean.Area;
import com.sdtower.common.bean.City;
import com.sdtower.common.bean.IndexPage;
import com.sdtower.common.bean.Message;
import com.sdtower.common.bean.Notice;
import com.sdtower.common.bean.SysUserInfo;
import com.sdtower.common.bean.TxPage;
import com.sdtower.common.util.PageUtil;
import com.sdtower.common.util.ParamerUtil;
import com.sdtower.mapper.AreaMapper;
import com.sdtower.mapper.LoginAndSysMapper;
import com.sdtower.service.LoginService;

@Service
public class LoginServiceImpl  implements LoginService{

	@Autowired
	private LoginAndSysMapper loginMapper;
	
	@Autowired
	private AreaMapper areaMapper;
	
	@Override
	public SysUserInfo getLoginInfo(Map map) {
		//获取用户信息
		SysUserInfo sysUserInfo=loginMapper.getLoginInfo( map);
		if(null==sysUserInfo)
			return null;
	   //更新登录时间
		loginMapper.upateDlTime(sysUserInfo.getId());
		//判断用户角色
		int adminType=ParamerUtil.getAdminType(sysUserInfo.getAdminpower());
		Map mapArea=new HashMap();
		List<City> citys=new ArrayList<City>();
		List<Area> areas=null;
		if(adminType==0){
			citys=areaMapper.getCitys(null);
		}else if(adminType==1){
			/*
			mapArea.put("cityid", sysUserInfo.getAdmincityid());
			areas=areaMapper.getAreas(mapArea);
			citys=areaMapper.getCitys(mapArea);
			*/
		}else if(adminType==2){
			String ids=sysUserInfo.getAdminareacity();
			if(ids.endsWith(","))
				ids=ids.substring(0,ids.length()-1);
			String[]areaids=ids.split(",");
			if(areaids.length>0){
				mapArea.clear();
				mapArea.put("cityids", areaids);
				citys=areaMapper.getCitys(mapArea);
				/*
				if(areas!=null&&areaids.length>0){
					mapArea.put("cityid",areas.get(0).getCityid());
					citys=areaMapper.getCitys(mapArea);
				}
				*/
			}
		}
		/*
		if(citys!=null&&citys.size()>0){
			mapArea.put("cityid",citys.get(0).getCityid());
			areas=areaMapper.getAreas(mapArea);
		}
		*/
		sysUserInfo.setCitys(citys);
		sysUserInfo.setAreas(areas);
		return sysUserInfo;
	}

	@Override
	public List<Message> getTopMessage(IndexPage indexPage) {
		Map map=new HashMap();
		return loginMapper.getTopMessages(map);
	}

	@Override
	public List<Notice> getTopNotice(IndexPage indexPage) {
		Map map=new HashMap();
		return loginMapper.getTopNotices(map);
	}

	@Override
	public int getUsercount(Map map) {
		// TODO Auto-generated method stub
		return loginMapper.getUsercount(map);
	}

	@Override
	public int getTowercount(Map map) {
		// TODO Auto-generated method stub
		return loginMapper.getTowercount(map);
	}

	@Override
	public int getIngOrdercount(Map map) {
		// TODO Auto-generated method stub
		return loginMapper.getIngOrdercount(map);
	}

	@Override
	public int getSuccOrdercount(Map map) {
		// TODO Auto-generated method stub
		return loginMapper.getSuccOrdercount(map);
	}
	
}
