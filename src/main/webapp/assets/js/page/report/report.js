function tower_city_change(){
	var city=$("#tower_city");
	var area=$("#tower_area");
	city_change(city,area);
	
}
function user_city_change(){
	var city=$("#user_city");
	var area=$("#user_area");
	city_change(city,area);
	
}



function go_page(pageNo){
	$("#pageno").val(pageNo);
	  $("#form_report").submit();
	
}


function dc_orders(){
	location.href = "dc_orders.html?"+ $('#form_order').serialize();
	
}


function dc_txs(){
	location.href = "dc_txs.html?"+ $('#form_txs').serialize();
	
}


function dc_users() {
	location.href = "dc_users.html?" + $('#form_users').serialize();

}
