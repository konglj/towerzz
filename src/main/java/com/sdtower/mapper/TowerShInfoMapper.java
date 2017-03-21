package com.sdtower.mapper;

import java.util.List;

import com.sdtower.common.bean.TowerShInfo;

public interface TowerShInfoMapper {
	
	public List<TowerShInfo> getShInfos(int orderid);
	
	public int insertTowerShInfo(TowerShInfo shInfos);
	

}
