package com.sdtower.service.impl;

import com.sdtower.common.bean.UserInfo;

public interface TowerUserMapper {
	
	public UserInfo getUserInfoByWxid(String wxid);

}
