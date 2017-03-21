package com.sdtower.common.bean;

import java.util.List;
import java.util.Map;

public class ManagerPage {
	
	//当用户为地区时  在管理员区域而不在用户中的
	
	private List<Area> otherareas;

	//本管理员id
	private String adminid;
	
	//本管理员id
	private int power;
	//下辖的管理员
	private List<AdminManagerInfo> admins;
	
	private AdminManagerInfo admin;
	
	//检索参数
	private ManagerQueryItems queryItem = new ManagerQueryItems();
	
	//用户角色列表
	private List<ManagerPower> powers;
	
	//检索页No
	private int pageNo =1;
	
	//总数据条数
	private int adminCount;
	
	/**
	 * 界面表示用的  Page信息Map
	 */
	private Map  pageinfo;
	
	//本管理者
	private SysUserInfo manager;
	
	//管理地市
	private String adminCity;
	
	private List<Area> adminareas;
	
	private List<City> usercitys;
	
	private List<Area> userareas;
	
	private String areaid;
	
	private List<Power> childPowers;

	public List<AdminManagerInfo> getAdmins() {
		return admins;
	}

	public void setAdmins(List<AdminManagerInfo> admins) {
		this.admins = admins;
	}

	public AdminManagerInfo getAdmin() {
		return admin;
	}

	public void setAdmin(AdminManagerInfo admin) {
		this.admin = admin;
	}

	public ManagerQueryItems getQueryItem() {
		return queryItem;
	}

	public void setQueryItem(ManagerQueryItems queryItem) {
		this.queryItem = queryItem;
	}

	public List<ManagerPower> getPowers() {
		return powers;
	}

	public void setPowers(List<ManagerPower> powers) {
		this.powers = powers;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getAdminCount() {
		return adminCount;
	}

	public void setAdminCount(int adminCount) {
		this.adminCount = adminCount;
	}

	public Map getPageinfo() {
		return pageinfo;
	}

	public void setPageinfo(Map pageinfo) {
		this.pageinfo = pageinfo;
	}

	public SysUserInfo getManager() {
		return manager;
	}

	public void setManager(SysUserInfo manager) {
		this.manager = manager;
	}

	public String getAdminCity() {
		return adminCity;
	}

	public void setAdminCity(String adminCity) {
		this.adminCity = adminCity;
	}

	public List<Area> getAdminareas() {
		return adminareas;
	}

	public void setAdminareas(List<Area> adminareas) {
		this.adminareas = adminareas;
	}

	public List<City> getUsercitys() {
		return usercitys;
	}

	public void setUsercitys(List<City> usercitys) {
		this.usercitys = usercitys;
	}

	public List<Area> getUserareas() {
		return userareas;
	}

	public void setUserareas(List<Area> userareas) {
		this.userareas = userareas;
	}

	public String getAreaid() {
		return areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}

	public List<Power> getChildPowers() {
		return childPowers;
	}

	public void setChildPowers(List<Power> childPowers) {
		this.childPowers = childPowers;
	}

	public String getAdminid() {
		return adminid;
	}

	public void setAdminid(String adminid) {
		this.adminid = adminid;
	}

	public List<Area> getOtherareas() {
		return otherareas;
	}

	public void setOtherareas(List<Area> otherareas) {
		this.otherareas = otherareas;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	
	
	

}
