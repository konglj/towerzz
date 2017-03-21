package com.sdtower.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdtower.common.bean.Area;
import com.sdtower.common.bean.City;
import com.sdtower.mapper.AreaMapper;
import com.sdtower.service.AreaService;

@Service
public class AreaSeriviceImpl implements AreaService{

	@Autowired
	private AreaMapper areaMapper;
	@Override
	public List<Area> getAreas(Map map) {
		return areaMapper.getAreas(map);
	}

	@Override
	public List<City> getCitys(Map map) {
		return areaMapper.getCitys(map);
	}
	
	@Override
	public List<Area> getOtherAreas(Map map){
		return areaMapper.getOtherAreas(map);
	}

	@Override
	public Area getAreaByCityNameAndAreaName(Map map) {
		return areaMapper.getAreaByCityNameAndAreaName(map);
	}

	@Override
	public String getCityname(String cityid) {
		
		Map map=areaMapper.getCityname(cityid);
		String cityname="";
		if(map!=null){
			cityname=(String)map.get("City_name");
		}
		if(cityname==null)
			cityname="";
		return cityname;
	}

}
