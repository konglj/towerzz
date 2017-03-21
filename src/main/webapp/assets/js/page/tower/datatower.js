function tower_city_change() {
	var city = $("#tower_city");
	var area = $("#tower_area");
	city_change_g(city, area);
}

function go_page(pageNo) {
	$("#pageNo").val(pageNo);
	$("#form_toweranalysis").submit();

}

function dc_date_tower() {
	location.href = "dc_datatowers.html?"+ $('#form_toweranalysis').serialize();
	

}



