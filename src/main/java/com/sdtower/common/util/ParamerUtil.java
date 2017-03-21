package com.sdtower.common.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.rtf.RTFEditorKit;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.sdtower.common.bean.Area;
import com.sdtower.common.bean.City;
import com.sdtower.common.bean.SysUserInfo;

public class ParamerUtil {

	public static SysUserInfo getSysUserFromSesson(HttpServletRequest request) {

		return (SysUserInfo) request.getSession().getAttribute("sysuser");
	}

	public static void setSysUserInSesson(HttpServletRequest request,
			SysUserInfo sysUserInfo) {
		request.getSession().setAttribute("sysuser", sysUserInfo);
	}

	public static List<City> getSysUserCity(HttpServletRequest request) {
		SysUserInfo user = (SysUserInfo) request.getSession().getAttribute(
				"sysuser");
		if (user != null)
			return user.getCitys();
		return null;
	}

	public static List<Area> getSysUserArea(HttpServletRequest request) {
		SysUserInfo user = (SysUserInfo) request.getSession().getAttribute(
				"sysuser");
		if (user != null)
			return user.getAreas();
		return null;
	}

	public static int getAdminType(int power) {
		if (power == 1 ||power==4 ||power == 6)
			return 0;
		if (power == 3|| power == 7)
			return 2;
		
		return 1;
	}

	public static int getUpdateOrderState(int oldstate, int result) {

		int state = 0;
		switch (oldstate) {

		case 0:
			if (result == 0)
				state = 3;
			else
				state = 0;
			break;

		case 2:
			if (result == 0)
				state = 3;
			else
				state = 4;
			break;
		case 4:
			if (result == 0)
				state = 3;
			else
				state = 5;
			break;

		case 5:
			if (result == 0)
				state = 7;
			else
				state = 6;
			break;

		case 8:
			if (result == 0)
				state = 10;
			else
				state = 9;
			break;

		case 13:
			if (result == 0)
				state = 14;
			else
				state = 12;
			break;

		default:
			break;
		}
		return state;

	}

	public int getCanQDCount(int level) {
		int count = 0;
		switch (level) {
		case 1:
			count = 2;
			break;
		case 2:
			count = 3;
			break;
		case 3:
			count = 4;
			break;
		case 4:
			count = 5;
			break;
		case 5:

			break;

		default:
			break;
		}
		return count;

	}

	public static int getUserLevel(int score, int ep) {
		if (score < 450 || ep < 4)
			return 1;

		if (score >= 450 && score < 900) {
			if (ep >= 4)
				return 2;
			else
				return 1;
		}
		if (score >= 900 && score < 1400) {
			if (ep >= 8)
				return 3;
			else
				return 2;
		}
		if (score >= 1400) {
			if (ep >= 12)
				return 4;
			else
				return 3;
		}

		return 0;
	}

	public static Map<String, Integer> getTowerLevelMap() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("一级站址", 1);
		map.put("二级站址", 2);
		map.put("三级站址", 3);
		map.put("四级站址", 4);
		return map;
	}

	public static List<Integer> getAreaids(List<Area> areas) {
		List<Integer> list = new ArrayList<Integer>();
		for (Area area : areas) {
			list.add(area.getId());
		}

		return list;
	}

	public static boolean isExcel2003(String filePath) {
		try {
			BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream(filePath));
			if (POIFSFileSystem.hasPOIFSHeader(bis)) {
				System.out.println("Excel版本为excel2003及以下");
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

}
