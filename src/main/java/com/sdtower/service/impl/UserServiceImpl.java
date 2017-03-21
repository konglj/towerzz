package com.sdtower.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sdtower.common.bean.DataUser;
import com.sdtower.common.bean.DataUserPage;
import com.sdtower.common.bean.OrderInfo;
import com.sdtower.common.bean.TxDoinfo;
import com.sdtower.common.bean.TxPage;
import com.sdtower.common.bean.TxRecord;
import com.sdtower.common.bean.TxSource;
import com.sdtower.common.bean.UserInfo;
import com.sdtower.common.bean.UserManagePage;
import com.sdtower.common.bean.UserMoney;
import com.sdtower.common.bean.UserMoneyPage;
import com.sdtower.common.bean.UserType;
import com.sdtower.mapper.DataUserMapper;
import com.sdtower.mapper.TxMapper;
import com.sdtower.mapper.UserMapper;
import com.sdtower.service.TxService;
import com.sdtower.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private DataUserMapper datauserMapper;
	
	
	@Override
	public List<UserInfo> getUserInfos(UserManagePage usermanagepage) {
		// TODO Auto-generated method stub
		int count=userMapper.getUserInfosCount(usermanagepage);
		usermanagepage.setPagerowtotal(count);
		return userMapper.getUserInfos(usermanagepage);
	}

	@Override
	public List<UserMoney> getUserMoneys(UserMoneyPage usermoneypage) {
		int count=userMapper.getUserMoneysCount(usermoneypage);
		usermoneypage.setPagerowtotal(count);
		return userMapper.getUserMoneys(usermoneypage);
	}
	
	@Override
	public UserInfo getUserdetail(String wxid) {
		UserInfo userinfo = userMapper.getUserDetail(wxid);
		List <OrderInfo> orderinfos = userMapper.getOrderInfos(wxid);
		userinfo.setOrderinfos(orderinfos);
		return userinfo;
	}
	
	@Override
	public int updateuserstate(Map map) {
		int count = userMapper.updateuserstate(map);
		if (count == 0)
			throw new RuntimeException();
		return 1;
	}
	
	@Override
	public int updateusertype(Map map) {
		int count = userMapper.updateusertype(map);
		return count;
	}
	
	@Override
	public int updateUserBz(Map map) {
		return userMapper.updateUserBz(map);
	}

	@Override
	public List<Map> getDcUser(UserManagePage usermanagepage) {
		return userMapper.getDcUser(usermanagepage);
	}

	@Override
	public List<Map> getDcUserFee(UserMoneyPage userMoneyPage) {
		return userMapper.getDcUserFee(userMoneyPage);
	}

	@Override
	public List<DataUser> getDataUsers(DataUserPage datauserPage) {
		int count=datauserMapper.getDataUsersCount(datauserPage);
		datauserPage.setPagerowtotal(count);
		return datauserMapper.getDataUsers(datauserPage);
	}

	@Override
	public List<Map> getDcDataUsers(DataUserPage datauserPage) {
		return datauserMapper.getDcDataUsers(datauserPage);
	}

	@Override
	public List<UserType> getUserTypes() {
		return userMapper.getUserTypes();
	}

	

}
