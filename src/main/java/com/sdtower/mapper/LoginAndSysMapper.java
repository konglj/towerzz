package com.sdtower.mapper;

import java.util.List;
import java.util.Map;

import com.sdtower.common.bean.IndexPage;
import com.sdtower.common.bean.ManagerPage;
import com.sdtower.common.bean.ManagerPower;
import com.sdtower.common.bean.Message;
import com.sdtower.common.bean.Notice;
import com.sdtower.common.bean.SysUserInfo;

public interface LoginAndSysMapper {
	
	public SysUserInfo getLoginInfo(Map map);
	
	public SysUserInfo getSysUserInfo(int id);
	
	public  int upateDlTime(int id);
	
	public int insertAdmin(SysUserInfo sysUserInfo);
	
	public int updateAdmin(SysUserInfo sysUserInfo);
	
	public int delAdmin(int id);
	
	public List<Notice> getTopNotices(Map map);
	
	public List<Message> getTopMessages(Map map);
	
	public int getUsercount(Map map); 
	
	public int getTowercount(Map map);
	
	public int getIngOrdercount(Map map);
	
	public int getSuccOrdercount(Map map);
}
