package com.sdtower.common.bean;

import java.util.List;

public class QueryTerms {

	private String towername;
	
	private String cityid;
	
	private int areaid;
	
	private int towertype = 0;
	
	private int towerstate = -1;
	
	private int towervisible=0;
	
	private String fromdate;
	
	private String todate;
	
	private SysUserInfo adminInfo;
	
	private List<Area> adminareas;
	
	private List<City> admincitys;
	
	private String admincity;
	
	private int pageindex = 1;
	
	/**
	 * 每页显示条数
	 */
	private int pageSize=10;
	
	
	
	public int getTowervisible() {
		return towervisible;
	}

	public void setTowervisible(int towervisible) {
		this.towervisible = towervisible;
	}

	public SysUserInfo getAdminInfo() {
		return adminInfo;
	}

	public void setAdminInfo(SysUserInfo adminInfo) {
		this.adminInfo = adminInfo;
	}

	public String getTowername() {
		return towername;
	}

	public void setTowername(String towername) {
		this.towername = towername;
	}

	public String getCityid() {
		return cityid;
	}

	public void setCityid(String cityid) {
		this.cityid = cityid;
	}

	public int getAreaid() {
		return areaid;
	}

	public void setAreaid(int areaid) {
		this.areaid = areaid;
	}

	public int getTowertype() {
		return towertype;
	}

	public void setTowertype(int towertype) {
		this.towertype = towertype;
	}

	public int getTowerstate() {
		return towerstate;
	}

	public void setTowerstate(int towerstate) {
		this.towerstate = towerstate;
	}

	public String getFromdate() {
		return fromdate;
	}

	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}

	public String getTodate() {
		return todate;
	}

	public void setTodate(String todate) {
		this.todate = todate;
	}

	public List<Area> getAdminareas() {
		return adminareas;
	}

	public void setAdminareas(List<Area> adminareas) {
		this.adminareas = adminareas;
	}
	
	

	public List<City> getAdmincitys() {
		return admincitys;
	}

	public void setAdmincitys(List<City> admincitys) {
		this.admincitys = admincitys;
	}

	public String getAdmincity() {
		return admincity;
	}

	public void setAdmincity(String admincity) {
		this.admincity = admincity;
	}

	public int getPageindex() {
		return pageindex;
	}

	public void setPageindex(int pageindex) {
		this.pageindex = pageindex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	
	
	
	
}
