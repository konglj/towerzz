package com.sdtower.common.bean;

public class AddTowerInfo {
	
	private int id;
	
	//系统编号
	private String towerid;
	
	//自定义编号
	private String toweridefined;
	
	//站址名称
	private String towername;
	
	//运营商需求
	private String towerwho;
	
	//城市
	private String cityid;
	
	//地区
	private int towerarea;
	
	//详细地址
	private String toweraddress;
	
	//站址类型
	private int towerlevel;
	
	//站址类型名称
	private String towerlevelname;
	
	//谈单费
	private int towerfee;
	
	//区域经理联系方式
	private String managerphone;
	
	//区域经理姓名
	private String managername;
	
	//站址状态 0:未抢单 1：已抢单
	private int towerstate;
	
	private int towertypeid;
	
	//站型
	private String towertype;
	
	//租用面积
	private String towerlarge;
	
	//经度
	private String towerj;
	
	//纬度
	private String towerw;
	
	//区域经理
	private String towermanager;
	
	//区域经理
   private String towermanagername;
	
	//站址描述
	private String towerinfo;
	
	//是否上架 0:未上架；1上架
	private int towervisible;
	
	//是否为红包站址 0:否；1：是
	private int towerhb;
	
	//添加者id
	private int toweradduser;
	
	//订单编号
	private String towerorderid;
	
	//站址图片
	private String towerimg;
	
	private String areaname;
	
	private String cityname;
	
	private int isfavourite;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTowerid() {
		return towerid;
	}

	public void setTowerid(String towerid) {
		this.towerid = towerid;
	}

	public String getTowername() {
		return towername;
	}

	public void setTowername(String towername) {
		this.towername = towername;
	}


	public String getToweridefined() {
		return toweridefined;
	}

	public void setToweridefined(String toweridefined) {
		this.toweridefined = toweridefined;
	}

	public String getCityid() {
		return cityid;
	}

	public void setCityid(String cityid) {
		this.cityid = cityid;
	}

	public String getManagerphone() {
		return managerphone;
	}

	public void setManagerphone(String managerphone) {
		this.managerphone = managerphone;
	}
	
	

	public String getManagername() {
		return managername;
	}

	public void setManagername(String managername) {
		this.managername = managername;
	}

	public int getTowerhb() {
		return towerhb;
	}

	public void setTowerhb(int towerhb) {
		this.towerhb = towerhb;
	}

	public int getToweradduser() {
		return toweradduser;
	}

	public void setToweradduser(int toweradduser) {
		this.toweradduser = toweradduser;
	}

	public String getTowerwho() {
		return towerwho;
	}

	public void setTowerwho(String towerwho) {
		this.towerwho = towerwho;
	}

	public int getTowerarea() {
		return towerarea;
	}

	public void setTowerarea(int towerarea) {
		this.towerarea = towerarea;
	}

	public String getToweraddress() {
		return toweraddress;
	}

	public void setToweraddress(String toweraddress) {
		this.toweraddress = toweraddress;
	}

	public int getTowerlevel() {
		return towerlevel;
	}

	public void setTowerlevel(int towerlevel) {
		this.towerlevel = towerlevel;
	}
	
	

	public String getTowerlevelname() {
		return towerlevelname;
	}

	public void setTowerlevelname(String towerlevelname) {
		this.towerlevelname = towerlevelname;
	}

	public int getTowerfee() {
		return towerfee;
	}

	public void setTowerfee(int towerfee) {
		this.towerfee = towerfee;
	}

	public int getTowerstate() {
		return towerstate;
	}

	public void setTowerstate(int towerstate) {
		this.towerstate = towerstate;
	}

	public String getTowertype() {
		return towertype;
	}

	public void setTowertype(String towertype) {
		this.towertype = towertype;
	}

	public String getTowerlarge() {
		return towerlarge;
	}

	public void setTowerlarge(String towerlarge) {
		this.towerlarge = towerlarge;
	}

	public String getTowerj() {
		return towerj;
	}

	public void setTowerj(String towerj) {
		this.towerj = towerj;
	}

	public String getTowerw() {
		return towerw;
	}

	public void setTowerw(String towerw) {
		this.towerw = towerw;
	}

	public String getTowermanager() {
		return towermanager;
	}

	public void setTowermanager(String towermanager) {
		this.towermanager = towermanager;
	}
	
	
	

	public String getTowermanagername() {
		return towermanagername;
	}

	public void setTowermanagername(String towermanagername) {
		this.towermanagername = towermanagername;
	}

	public String getTowerinfo() {
		return towerinfo;
	}

	public void setTowerinfo(String towerinfo) {
		this.towerinfo = towerinfo;
	}

	public int getTowervisible() {
		return towervisible;
	}

	public void setTowervisible(int towervisible) {
		this.towervisible = towervisible;
	}

	public String getTowerorderid() {
		return towerorderid;
	}

	public void setTowerorderid(String towerorderid) {
		this.towerorderid = towerorderid;
	}

	public String getTowerimg() {
		return towerimg;
	}

	public void setTowerimg(String towerimg) {
		this.towerimg = towerimg;
	}

	public String getAreaname() {
		return areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public int getIsfavourite() {
		return isfavourite;
	}

	public void setIsfavourite(int isfavourite) {
		this.isfavourite = isfavourite;
	}

	public int getTowertypeid() {
		return towertypeid;
	}

	public void setTowertypeid(int towertypeid) {
		this.towertypeid = towertypeid;
	}
	
	
	
	
	

}
