package com.sdtower.service;

import java.util.List;
import java.util.Map;

import com.sdtower.common.bean.IndexPage;
import com.sdtower.common.bean.Message;
import com.sdtower.common.bean.Notice;
import com.sdtower.common.bean.SysUserInfo;


public interface LoginService {

	public SysUserInfo getLoginInfo(Map map);
	
	public List<Message> getTopMessage(IndexPage indexPage);
	
	public List<Notice> getTopNotice(IndexPage indexPage);
	
	public int getUsercount(Map map); 
	
	public int getTowercount(Map map);
	
	public int getIngOrdercount(Map map);
	
	public int getSuccOrdercount(Map map);
	
}
