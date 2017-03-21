package com.sdtower.mapper;

import com.sdtower.common.bean.TowerReport;

public interface ReportTowerMapper {
	
	public int checkTowerExist(int towerid);
	
	public int upateTowerReport(TowerReport towerReport);
	
	public int insertTowerReport(TowerReport towerReport);

}
