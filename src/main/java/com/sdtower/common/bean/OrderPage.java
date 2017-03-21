package com.sdtower.common.bean;

import java.util.List;
import java.util.Map;

public class OrderPage {
	
	private int look;
	
	private int adminpower;
	
	private int isShPage=0;
	
	private int pageno=1;
	
	private int pageSize=10;
	
	private int pagerowtotal;
	
	private Map  pageinfo;
	
	private Order order=new Order();
	
	private OrderParameter parameter=new OrderParameter();
	
	private List<Area> adminareas;
	
	private List<City> admincitys;
	
	private String admincity;
	
	private List<City> towercitys;
	
	private List<Area> towerareas;
	

    private List<City> usercitys;
	
	private List<Area> userareas;
	
	
	
	
	private List<Order> orders;
	
	//order info
	
	private int orderid;
	
	
   private OrderUser orderuser;
	
	
	private OrderTower ordertower;
	
	private List<TowerShInfo> shinfos;

	
	

	public int getLook() {
		return look;
	}

	public void setLook(int look) {
		this.look = look;
	}

	public int getAdminpower() {
		return adminpower;
	}

	public void setAdminpower(int adminpower) {
		this.adminpower = adminpower;
	}

	public int getPageno() {
		return pageno;
	}

	public void setPageno(int pageno) {
		this.pageno = pageno;
	}

	
	
	public int getPagerowtotal() {
		return pagerowtotal;
	}

	public void setPagerowtotal(int pagerowtotal) {
		this.pagerowtotal = pagerowtotal;
	}

	public List<Area> getAdminareas() {
		return adminareas;
	}

	public void setAdminareas(List<Area> adminareas) {
		this.adminareas = adminareas;
	}

	public Map getPageinfo() {
		return pageinfo;
	}

	public void setPageinfo(Map pageinfo) {
		this.pageinfo = pageinfo;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	


	public List<City> getTowercitys() {
		return towercitys;
	}

	public void setTowercitys(List<City> towercitys) {
		this.towercitys = towercitys;
	}

	public List<Area> getTowerareas() {
		return towerareas;
	}

	public void setTowerareas(List<Area> towerareas) {
		this.towerareas = towerareas;
	}
	
	

	public List<City> getAdmincitys() {
		return admincitys;
	}

	public void setAdmincitys(List<City> admincitys) {
		this.admincitys = admincitys;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}


	public OrderParameter getParameter() {
		return parameter;
	}

	public void setParameter(OrderParameter parameter) {
		this.parameter = parameter;
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

	

	public String getAdmincity() {
		return admincity;
	}

	public void setAdmincity(String admincity) {
		this.admincity = admincity;
	}

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	

	public OrderUser getOrderuser() {
		return orderuser;
	}

	public void setOrderuser(OrderUser orderuser) {
		this.orderuser = orderuser;
	}

	public OrderTower getOrdertower() {
		return ordertower;
	}

	public void setOrdertower(OrderTower ordertower) {
		this.ordertower = ordertower;
	}

	public List<TowerShInfo> getShinfos() {
		return shinfos;
	}

	public void setShinfos(List<TowerShInfo> shinfos) {
		this.shinfos = shinfos;
	}

	public int getIsShPage() {
		return isShPage;
	}

	public void setIsShPage(int isShPage) {
		this.isShPage = isShPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	
	
	
	
	
	
	
	
	

}
