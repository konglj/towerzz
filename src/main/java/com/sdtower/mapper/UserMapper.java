package com.sdtower.mapper;

import java.util.List;
import java.util.Map;

import com.sdtower.common.bean.OrderInfo;
import com.sdtower.common.bean.SendNoticeParameter;
import com.sdtower.common.bean.UserChargeInfo;
import com.sdtower.common.bean.UserInfo;
import com.sdtower.common.bean.UserManagePage;
import com.sdtower.common.bean.UserMoney;
import com.sdtower.common.bean.UserMoneyPage;
import com.sdtower.common.bean.UserType;

public interface UserMapper {
	
	public List<UserInfo> getUserInfos(UserManagePage usermanagepage);
	
	public int getUserInfosCount(UserManagePage usermanagepage);
	
	public List<UserMoney> getUserMoneys(UserMoneyPage usermoneypage);
	
	public int getUserMoneysCount(UserMoneyPage usermoneypage);
	
	public UserInfo getUserDetail(String wxid);
	
	public List<OrderInfo> getOrderInfos(String wxid);
	
	public int updateuserstate(Map map);
	
	public int updateusertype(Map map);
	
	public int updateUserBz(Map map);
	
	public  List<Map>    getDcUser(UserManagePage usermanagepage);
	
	public List<Map>  getDcUserFee(UserMoneyPage userMoneyPage);
	
	
	public int updateUserLevle(UserChargeInfo chargeInfo);
	
	public Map getUserArea(Map map);
	
	public List<UserType> getUserTypes();
	
	public List<UserInfo> getUserInfosByTwNotice(SendNoticeParameter parameter);
}
