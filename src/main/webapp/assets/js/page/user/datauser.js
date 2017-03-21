function user_city_change() {
	var city = $("#user_city");
	var area = $("#user_area");
	city_change_g(city, area);
}

function go_page(pageNo) {
	$("#pageno").val(pageNo);
	$("#form_useranalysis").submit();

}


function dc_date_tower() {
	location.href = "dc_datausers.html?"+ $('#form_useranalysis').serialize();
	

}



