package com.sdtower.service;

import java.util.List;
import java.util.Map;

import com.sdtower.common.bean.AddTowerInfo;
import com.sdtower.common.bean.Area;
import com.sdtower.common.bean.DataTower;
import com.sdtower.common.bean.DataTowerPage;
import com.sdtower.common.bean.Order;
import com.sdtower.common.bean.QueryTerms;
import com.sdtower.common.bean.Tower;
import com.sdtower.common.bean.TowerType;
import com.sdtower.common.bean.TowersPage;

public interface TowerService {
	
	//上架站址管理
	public TowersPage getTowersPage(QueryTerms queryTerms);
	
	public List<Tower> getTowers(QueryTerms queryTerms);
	
	public int  getTowersCount(QueryTerms queryTerms);
	
	//未上架站址管理
	public TowersPage getNoTowersPage(QueryTerms queryTerms);
	
	public List<Tower> getNoTowers(QueryTerms queryTerms);
	
	public int  getNoTowersCount(QueryTerms queryTerms);
	
	//添加站址
	public int addTower(AddTowerInfo tower);
	
	public Area getAreaById(Map map);
	
	//站址分析
	public List<DataTower> getDataTowers(DataTowerPage datatowerPage);
	
	//站址分析
	public List<Map> getDcDataTowers(DataTowerPage datatowerPage);
	
	
	public List<Order> getTowerOrderHistory(int towerid);
	
	public List<Map> getDcTowerOrderHistory(int towerid);
	
	
	
	/**
	 * 上、下架处理
	 */
	public int updateTowerVisible(Map map);
	

	public int delTower(Map map);
	
	public Tower getTowerInfo(int towerid);

	public String getAreaOrder(String city, int area);
	
	
	public int updateTower(AddTowerInfo tower);
	
	public List<TowerType> getTowertype();
	
	public Map<String,Integer>  getTowertypeMap();
	
	public int insertTowers(List<AddTowerInfo> towers);
	
	public List<Map> dcTowers(QueryTerms queryTerms);
}
