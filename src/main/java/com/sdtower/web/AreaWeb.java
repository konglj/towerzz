package com.sdtower.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sdtower.common.bean.Area;
import com.sdtower.common.util.ResultUtil;
import com.sdtower.service.AreaService;

@Controller
@RequestMapping("/area")
public class AreaWeb {
	
	@Autowired
	private AreaService areaService;
	
	@RequestMapping("/get_area")
	@ResponseBody
	public Object getAreaByCity(String cityid){
		List<Area> list=new ArrayList<Area>();
		Map  map=new HashMap();
		map.put("cityid", cityid);
		list=areaService.getAreas(map);
		if(list==null)
			return ResultUtil.generateResponseMsg("error");
		return ResultUtil.generateResponseMsg("success", list);
	}
	

}
