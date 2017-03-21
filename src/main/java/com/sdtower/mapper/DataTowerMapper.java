package com.sdtower.mapper;

import java.util.List;
import java.util.Map;

import com.sdtower.common.bean.DataTower;
import com.sdtower.common.bean.DataTowerPage;
import com.sdtower.common.bean.DataTowerParameter;

public interface DataTowerMapper {
	
	public List<DataTower> getDataTowers(DataTowerPage datatowerPage);
	
	public int getDataTowersCount(DataTowerPage datatowerPage);
	
	public List<Map> getDcDataTowers(DataTowerPage datatowerPage);

}


