package com.sdtower.service;

import java.util.List;
import java.util.Map;

import com.sdtower.common.bean.DataTower;
import com.sdtower.common.bean.DataTowerPage;
import com.sdtower.common.bean.DataUser;
import com.sdtower.common.bean.DataUserPage;
import com.sdtower.common.bean.UserInfo;
import com.sdtower.common.bean.UserManagePage;
import com.sdtower.common.bean.UserMoney;
import com.sdtower.common.bean.UserMoneyPage;
import com.sdtower.common.bean.UserType;

public interface UserService {
	
	public List<UserInfo> getUserInfos(UserManagePage usermanagepage);
	
	public List<UserMoney> getUserMoneys(UserMoneyPage usermoneypage);

	UserInfo getUserdetail(String wxid);

	int updateuserstate(Map map);

	public int updateusertype(Map map);
	
	public int updateUserBz(Map map);
	
	public List<Map>  getDcUser(UserManagePage usermanagepage);
	
	public List<Map>  getDcUserFee(UserMoneyPage userMoneyPage);
	
	public List<DataUser> getDataUsers(DataUserPage datauserPage);
	
	public List<Map> getDcDataUsers(DataUserPage datauserPage);
	
	public List<UserType> getUserTypes();
	
	
	
}
