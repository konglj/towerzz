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

/* xucc mod 2016.05.23 start */
function rent_ht_display(){
	
	if ($("#ischeckht").val()==0) { 
		$("#renthtid").val("");
		//$("#rentht").val("");
		//$("#renthtid").attr("readonly","readonly");
	//	$("#file").attr("disabled","disabled");
		$("#tr_htid").attr("hidden","hidden");
		$("#tr_htimg").attr("hidden","hidden");
		 $('#demo-files').empty();
	} else if($("#ischeckht").val()==1){
		//$("#file").attr("disabled","disabled");
		 $('#demo-files').empty();
		 //$("#renthtid").removeAttr("readonly","");
		 $("#tr_htid").removeAttr("hidden");
		$("#tr_htimg").attr("hidden","hidden");
	} else{
		$("#renthtid").val("");
		// $("#renthtid").attr("readonly","readonly");
		//$("#file").removeAttr("disabled","");
		 $("#tr_htimg").removeAttr("hidden");
			$("#tr_htid").attr("hidden","hidden");
	}
}

function del_old_img(type){
	if(type==0){
		 $('#demo-files').empty();
		 $('#demo-files').html('<span class="demo-note"></span>');
	}
	if($("#fileurl").val()=='')
		return;
	  $.ajax({
		 	url : "del_old_img.html",
			type : 'post',
			dataType : "json",
			data : {
			oldimg : $("#fileurl").val()

		},
		error : function(request) {

		},
		success : function(data) {
		

		}
	});
	
}
/* xucc mod end */

function go_page(pageNo){
	$("#pageno").val(pageNo);
	  $("#form_order").submit();
	
}

function jump_page() {
	if($("#jumppage").val()==""||isNaN($("#jumppage").val())){
	return;
	}
	$("#pageno").val($("#jumppage").val());
	$("#form_order").submit();

}

function order_sh(id,result){
	var info=$("#shinfo").val();
	if(result==0&&info==""){
		alert("请输入拒绝原因！");
		return;
	}
	
	
	$.ajax({
		url : "order_sh.html",
		type : 'post',
		dataType : "json",
		data : {
			orderid:id,
			result:result,
			shinfo:info
			
		},
		success : function(data) {
			if (data.result == "success") {
			if (confirm("审核成功！")) {
				window.location='order.html';
				}else{
					window.location='order.html';
				}
			} else{
				alert('审核失败！');
			}
				
		}
	});
}

function tj_first_fee(){
	/* xucc mod 2016.05.23
	var htno=$("#htno").val();
	if(htno==""){
		alert("请输入合同编号！");
		return;
	}
	if($("#firstfile").val()==""){
		alert("请输入选择合同扫描件！");
		return;
	}
	*/
	/* xucc mod 2016.05.23 start*/
	var ischeckht=$("#ischeckht").val();
	if(ischeckht==1){
		 var renthtid=$("#renthtid").val();
		 if(renthtid==undefined||renthtid==''){
			alert('请填写租赁合同编号！');
			return;
		}
		 if(renthtid.length<=1&&renthtid!=''){
			 swal("",'请正确填写租赁合同编号！');
				return;
			}
		
		
	}else if(ischeckht==2){
		 var rentht=$('#demo-files img').attr("src");
		 if(rentht==undefined||rentht==''){
			alert('请上传租赁合同文件！');
			return;
		}
	}
	/* xucc mod end */
	//firstfile
	var endfee=$("#endfee").val();
	if(Number(endfee)<=0||$("#firstfee").val()==""){
		alert("请输入正确的首付金额！");
		return;
	}
	$("#form_first_fee").ajaxSubmit({
		type : "post",
		url : "order_first_fee.html",
		dataType : "json",
		success : function(data) {

			if (data.result == 'success') {
				if (confirm("申请成功！")) {
					window.location.href = "order.html";
				}else{
					window.location.href = "order.html";
				}
			} else {
				alert("申请失败！");

			}

		}
	});

}

function tj_end_fee(){
	if($("#endfile").val()==""){
		alert("请输入选择验收报告！");
		return;
	}
	$("#form_end_fee").ajaxSubmit({
		type : "post",
		url : "order_end_fee.html",
		dataType : "json",
		success : function(data) {

			if (data.result == 'success') {
				if (confirm("申请成功！")) {
					window.location.href = "order.html";
				}else{
					window.location.href = "order.html";
				}
			} else {
				alert("申请失败！");

			}

		}
	});

}

function fee_sh(id,result){
	var info=$("#shinfo").val();
	if(result==0&&info==""){
		alert("请填写拒绝理由！");
		return;
	}
	$.ajax({
		url : "update_fee_sh.html",
		type : 'post',
		dataType : "json",
		data : {
			shinfo:info,
			orderid:id,
			result:result
		},
		success : function(data) {
			if (data.result == "success") {
				if (confirm("审核成功！")) {
			    	window.location='fee_sh.html';
				}else{
					window.location='fee_sh.html';
				}
			} else{
				alert('审核失败');
			}
				
		}
	});
}

function yq(id){
	$.ajax({
		url : "order_yq.html",
		type : 'post',
		dataType : "json",
		data : {
			orderid:id
		},
		success : function(data) {
			if (data.result == "success") {
				window.location='order.html';
			} else{
				alert('审核失败');
			}
				
		}
	});
}


function  shjj_show(id){
	 $("#tip").fadeIn(200);
	 $("#select_order_id").val(id);
}

function  shjj(){
	var id= $("#select_order_id").val();
	 $.ajax({
			url : "order_shjj.html",
			type : 'post',
			dataType : "json",
			data : {
				orderid:id
			},
			success : function(data) {
				if (data.result == "success") {
					window.location='order.html';
				} else{
					 $("#tip").fadeOut(200);
					alert("拒绝失败！");
					
				}
					
			}
		});

}

function dc_orders(){
	location.href = "dc_orders.html?"+ $('#form_order').serialize();
	
}