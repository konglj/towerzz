package com.sdtower.common.bean;

public class DataTower {

	private int id;
	
	private String towername;
	
	private String cityname;
	
	private String areaname;
	
	private String towerlevel;
	
	private int towerfee;
	
	private int viewcount;
	
	private int selecount;
	
	private int cancelcount;
	
	private int timecount;
	
	private int rejectcount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTowername() {
		return towername;
	}

	public void setTowername(String towername) {
		this.towername = towername;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public String getAreaname() {
		return areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}

	public String getTowerlevel() {
		return towerlevel;
	}

	public void setTowerlevel(String towerlevel) {
		this.towerlevel = towerlevel;
	}

	public int getViewcount() {
		return viewcount;
	}

	public void setViewcount(int viewcount) {
		this.viewcount = viewcount;
	}

	public int getSelecount() {
		return selecount;
	}

	public void setSelecount(int selecount) {
		this.selecount = selecount;
	}

	public int getCancelcount() {
		return cancelcount;
	}

	public void setCancelcount(int cancelcount) {
		this.cancelcount = cancelcount;
	}

	public int getTimecount() {
		return timecount;
	}

	public void setTimecount(int timecount) {
		this.timecount = timecount;
	}

	public int getRejectcount() {
		return rejectcount;
	}

	public void setRejectcount(int rejectcount) {
		this.rejectcount = rejectcount;
	}

	public int getTowerfee() {
		return towerfee;
	}

	public void setTowerfee(int towerfee) {
		this.towerfee = towerfee;
	}
	
}
