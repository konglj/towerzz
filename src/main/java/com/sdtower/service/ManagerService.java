package com.sdtower.service;

import java.util.List;
import java.util.Map;

import com.sdtower.common.bean.AdminManagerInfo;
import com.sdtower.common.bean.BindUser;
import com.sdtower.common.bean.BindUserPage;
import com.sdtower.common.bean.ManagerPage;
import com.sdtower.common.bean.ManagerPower;
import com.sdtower.common.bean.Power;
import com.sdtower.common.bean.SysUserInfo;

public interface ManagerService {

	public List<ManagerPower> getManagerPowers(ManagerPage managerPage);
	
	public List<AdminManagerInfo> getAdmins(ManagerPage managerPage);
	
	public int getSysUsersCount(ManagerPage managerPage);

	AdminManagerInfo getAdmin(int ID);

	int changeAdminPwd(int ID);

	int deleteAdmin(Map map);

	int changeAdmin(Map map);

	int addAdmin(Map map);
	
	public List<Power> getChildPowers(Map map);
	
	public int checkAdminidExist(String adminid);
	
	public void getBindsUsers(BindUserPage bindUserPage);
	
	public int bindUser(Map map);
	
	public int unBindUser(Map map);		
	
	public List<SysUserInfo> getAdminWxByArea(Map map);
	
	
	
}
