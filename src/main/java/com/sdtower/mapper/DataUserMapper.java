package com.sdtower.mapper;

import java.util.List;
import java.util.Map;

import com.sdtower.common.bean.DataUser;
import com.sdtower.common.bean.DataUserPage;

public interface DataUserMapper {
	
	public List<DataUser> getDataUsers(DataUserPage datauserPage);
	
	public int getDataUsersCount(DataUserPage datauserPage);
	
	public List<Map> getDcDataUsers(DataUserPage datauserPage);
	
}
