package com.sdtower.service;

import java.util.List;
import java.util.Map;

import com.sdtower.common.bean.Area;
import com.sdtower.common.bean.City;

public interface AreaService {
	
	public List<Area> getAreas(Map map);
	
	public List<City> getCitys(Map map);
	
	public List<Area> getOtherAreas(Map map);
	
	public Area getAreaByCityNameAndAreaName(Map map);
	
	public String getCityname(String cityid);

	
	
}
