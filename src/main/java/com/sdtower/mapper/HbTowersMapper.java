package com.sdtower.mapper;

import java.util.List;
import java.util.Map;

import com.sdtower.common.bean.AddTowerInfo;
import com.sdtower.common.bean.Area;
import com.sdtower.common.bean.DataTowerPage;
import com.sdtower.common.bean.QueryTerms;
import com.sdtower.common.bean.Tower;
import com.sdtower.common.bean.TowerType;

public interface HbTowersMapper {

public List<Tower> getTowers(QueryTerms queryTerms);
	
	public int  getTowersCount(QueryTerms queryTerms);
	
	public List<Tower> getNoTowers(QueryTerms queryTerms);
	
	public int  getNoTowersCount(QueryTerms queryTerms);
	
	public int addTower(AddTowerInfo tower);
	
	public Area getAreaById(Map map);
	
	public int updateTowerVisible(Map map);
	
	public int delTower(Map map);
	
	public Tower getTowerInfo(int towerid);
	
    public Map getareaorder(Map map);
	
	public int updateareaorder(Map map);
	
	public int insertareaorder(Map map);
	
	public String getareabyid(int area);
	
	public int updateTower(AddTowerInfo tower);
	
	public List<TowerType> getTowertype();
	
	
	public Map getTowerArea(Map map);
	
	public List<Map> dcTowers(QueryTerms queryTerms);
	//站址分析
    public List<Map> getDcDataTowers(DataTowerPage datatowerPage);
}


