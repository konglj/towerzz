package com.sdtower.mapper;

import java.util.List;
import java.util.Map;

import com.sdtower.common.bean.Area;
import com.sdtower.common.bean.City;

public interface AreaMapper {
	
	public List<Area> getAreas(Map map);
	
	public List<City> getCitys(Map map);
	
	public List<Area> getOtherAreas(Map map);
	
	
	public Area getAreaByCityNameAndAreaName(Map map);
	
	
	public Map getCityname(String cityid);
	
	public  List<Map> getAreasMap(Map map);
	
	public  List<Map> getCitysMap(Map map);

}
