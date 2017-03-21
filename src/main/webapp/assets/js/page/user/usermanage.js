function user_city_change() {
	var city = $("#user_city");
	var area = $("#user_area");
	city_change(city, area);

}

function go_page(pageNo) {
	$("#pageno").val(pageNo);
	$("#form_order").submit();

}

function dj(wxid, state) {
	var info = '';
	if (state == 0)
		info = "您确定要冻结该用户吗？";
	else {
		info = "您确定要解冻该用户吗？";
	}
	if (confirm(info)) {
		$.ajax({
			url : "updatestate.html",
			type : 'post',
			dataType : "json",
			data : {
				wxid : wxid,
				state : state
			},
			success : function(data) {
				if (data.result == "success") {
					if (confirm("操作成功！")) {
					window.location = 'usermanage.html';
					}else{
						window.location = 'usermanage.html';
					}
				} else {
					alert("操作失败！");
				}
			}
		});
	}
}

function modifytype() {
	$.ajax({
		url : "changetype.html",
		type : 'post',
		dataType : "json",
		data : $("#form_modifytype").serialize(),
		success : function(data) {
			if (data.result == "success") {
				if (confirm("修改成功！")) {
				window.location = 'usermanage.html';
				}else{
					window.location = 'usermanage.html';
				}
			} else {
				alert("修改失败！");
			}
		}
	});
}
function modifybz(){
	
	$.ajax({
		url : "changebz.html",
		type : 'post',
		dataType : "json",
		data : $("#form_modifybz").serialize(),
		success : function(data) {
			if (data.result == "success") {
				if (confirm("修改成功！")) {
				window.location = 'usermanage.html';
				}else{
					window.location = 'usermanage.html';
				}
			} else {
				alert("修改失败！");
			}
		}
	});
}

function dc_users() {
	location.href = "dc_users.html?" + $('#form_usermanage').serialize();

}

function dc_fee() {
	location.href = "dc_fee.html?" + $('#form_usermoney').serialize();

}
