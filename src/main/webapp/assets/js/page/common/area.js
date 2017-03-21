function city_change(select_city,select_area){
	var city=select_city.val();
	if(city==""){
		select_area.empty();
		select_area.append("<option value='0'>全部</option>"); 
	}else{
		$.ajax({
			url : "../area/get_area.html",
			type : 'post',
			dataType : "json",
			data : {cityid:city},
			success : function(data) {
				if (data.result == "success") {
					select_area.empty();
					select_area.append("<option value='0'>全部</option>"); 
					$.each(data.msg, function(i, item){  
						select_area.append("<option value='"+item.id+"'>"+item.areaname+"</option>"); 
						});  
				} else{
				}
					
			}
		});
	
	}
	
}



function city_change_g(select_city,select_area){
	var city=select_city.val();
	if(city==""){
		select_area.empty();
		select_area.append("<option value='0'>全部</option>"); 
	}else{
		$.ajax({
			url : "area/get_area.html",
			type : 'post',
			dataType : "json",
			data : {cityid:city},
			success : function(data) {
				if (data.result == "success") {
					select_area.empty();
					select_area.append("<option value='0'>全部</option>"); 
					$.each(data.msg, function(i, item){  
						select_area.append("<option value='"+item.id+"'>"+item.areaname+"</option>"); 
						});  
				} else{
				}
					
			}
		});
	
	}
	
}