function user_city_change(){
	var city=$("#user_city");
	var area=$("#user_area");
	city_change(city,area);
	
}

function go_page(pageNo){
	$("#pageno").val(pageNo);
	  $("#form_manager").submit();
	
}

function jump_page() {
	if($("#jumppage").val()==""||isNaN($("#jumppage").val())){
	return;
	}
	$("#pageno").val($("#jumppage").val());
	$("#form_manager").submit();

}

function modifyadmin(){
	if($("#adminid").val()==''){
		alert("请输入账号！");
		return;
	}
	if($("#name").val()==''){
		alert("请输入姓名！");
		return;
	}
	var power=$("#role").val();
	
	if(power==3||power==7){
		var checkedsub = $('input[type="checkbox"][name="user_area"]:checked').length;
		if(checkedsub==0)
		{
			alert("请选择管理区域");
			return;
		}
		
	}
	$.ajax({
		url : "updateadmin.html",
		type : 'post',
		dataType : "json",
		data : $("#form_modifyadmin").serialize(),
		success : function(data) {
			if (data.result == "success") {
				if (confirm("修改成功！")) {
				window.location='manager.html';
				}else{
					window.location='manager.html';
					
				}
			} else{
				alert("修改失败！");
			}
		}
	});
}

function addadmin(){
	if($("#adminid").val()==''){
		alert("请输入账号！");
		return;
	}
	if($("#name").val()==''){
		alert("请输入姓名！");
		return;
	}
	var power=$("#role").val();
	
	if(power==3||power==7){
		var checkedsub = $('input[type="checkbox"][name="user_area"]:checked').length;
		if(checkedsub==0)
		{
			alert("请选择管理区域");
			return;
		}
		
	}
	$.ajax({
		url : "add_admin.html",
		type : 'post',
		dataType : "json",
		data : $("#form_addadmin").serialize(),
		success : function(data) {
			if (data.result == "success") {
				if (confirm("添加成功！")) {
					window.location='manager.html';
					}else{
						window.location='manager.html';
						
					}
			} else{
				alert(data.msg);
			}
		}
	});
}


function resetpwd(id){
	if (confirm("您确定要讲用户密码重置为111111吗！")) {
	$.ajax({
		url : "resetpwd.html",
		type : 'post',
		dataType : "json",
		data :{
			id:id
		},
		success : function(data) {
			if (data.result == "success") {
				alert("重置成功！");
			} else{
				alert("重置失败！");
			}
		}
	 });
	}
	
}

function del_admin(id){
	if (confirm("您确定要删除该管理员吗！")) {
	$.ajax({
		url : "del_admin.html",
		type : 'post',
		dataType : "json",
		data :{
			ids:id
		},
		success : function(data) {
			if (data.result == "success") {
				if (confirm("删除成功！")) {
				window.location='manager.html';
				}else{
					window.location='manager.html';
				}
			} else{
				alert("删除失败！");
			}
		}
	});
	}
	
}

function del_admin_all(){
	$.ajax({
		url : "del_admin.html",
		type : 'post',
		dataType : "json",
		data :{
			ids:$("#selectadminids").val()
		},
		success : function(data) {
			if (data.result == "success") {
				if (confirm("删除成功！")){
				window.location='manager.html';
			}else{
				window.location='manager.html';
			}
			} else{
				alert("删除失败！");
			}
		}
	});
	
}


	function change_role(){
		var power=$("#role").val();
		if(power==3||power==7){
			//var select_area=$("#user_area");
			$("#glqy").show();
		}else{
			$("#glqy").hide();
			$('input[type="checkbox"][name="user_area"]').each(function() {
				$(this).attr("checked", false);
			});
			$("#province").attr("checked", false);
			
			
		}
		
	}
	

function check_all() {
	$('input[type="checkbox"][name="chk_list"]').each(function() {
		$(this).attr("checked", true);
	});
}
function downTip() {
	var selectid = '';
	$('input[type="checkbox"][name="chk_list"]:checked').each(function() {
		selectid += $(this).val() + ",";
	});
	if (selectid == '') {
		alert("请选择管理员！");
		return;
	}
	$("#selectadminids").val(selectid);
	$("#tip").fadeIn(200);

}
function check_all(ob,name) {
	 if ($(ob).attr("checked")) {  
			$('input[type="checkbox"][name="'+name+'"]').each(function() {
				$(this).attr("checked", true);
			});
	    } else {  
	    	$('input[type="checkbox"][name="'+name+'"]').each(function() {
				$(this).attr("checked", false);
			});
	    }  
}

//子复选框的事件  
function set_check_all(ob,name){  
	
  //当没有选中某个子复选框时，SelectAll取消选中  
  if (!$(ob).attr("checked")) {  
  	$("#"+name+'').attr("checked", false);
  	return;
  }  
  var chsub = $('input[type="checkbox"][name="'+$(ob).attr("name")+'"]').length; //获取subcheck的个数  
  var checkedsub = $('input[type="checkbox"][name="'+$(ob).attr("name")+'"]:checked').length; //获取选中的subcheck的个数  
  if (checkedsub == chsub) {  
  	$("#"+name+"").attr("checked", true);
  }else{
		$("#"+name+"").attr("checked", false); 
  }  
}  