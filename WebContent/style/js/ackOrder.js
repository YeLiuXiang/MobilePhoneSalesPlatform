var localObj = window.location;
var contextPath = localObj.pathname.split("/")[1];
var basePath = localObj.protocol + "//" + localObj.host + "/" + contextPath;
var server_context = basePath;

$(document).ready(function () {
	$('#payDiv').on('hidden.bs.modal', function () {

		setTimeout("history.go(-1)", 100);
	});
	

});
function text(msg){
	/*打印错误信息*/
	$("#errText").html(msg);
}
function msgClear(){
	/*输入框改变时错误信息消失*/
	$(this).keydown(function() {
		$("#errText").html("");
		});
}

	
	
	
	
	
function ackOrder(){
		//获取表单的值
	var address = $("#address").val();
	var receiverPhone = $("#receiverPhone").val();
	var receiver = $("#receiver").val();
	if (receiverPhone!=""&&!receiverPhone.match(/^1[34578]\d{9}$/)) {
		text("请输入正确的手机号");
		$("#receiverPhone").focus();
		msgClear();
		return false;
	}
	else if (receiverPhone == "") {
		text("手机号不能为空");
		$("#receiverPhone").focus();
		msgClear();
		return false;
	}else if (address == "") {
		text("收货地址不能为空");
		$("#address").focus();
		msgClear();
		return false;
	}else if (receiver == "") {
		text("收货人姓名不能为空");
		$("#receiver").focus();
		msgClear();
		return false;
	}
		
		
		$.ajax({
			url: server_context + "/Order",
			type: "POST",
			dataType: "json",
			data: { "receiverPhone": receiverPhone, 
				"address": address, 
				"receiver": receiver,
				"method":"create" },
			success: function (data) {
				if (data.flag="true") {
					$('#payDiv').modal('show');
				} else{
					alert(data.msg);
				} 
			},
			error: function (XMLHttpRequest, textStatus, errorThrown) {
				alert(XMLHttpRequest.status + " " + XMLHttpRequest.readyState + " " + textStatus);
			},
		});
}
function settime(val,countdown) { 
	if (countdown == 0) { 
		val.removeAttribute("disabled"); 
		val.value="免费获取验证码";
	} else{ 
		val.setAttribute("disabled", true); 
		val.value="重新发送(" + countdown + ")"; 
		setTimeout(function() { 
			settime(val,--countdown) 
			},1000)
	} 
	 
} 

function sendCode(val){
	
	$.ajax({  
		type:"POST",
		data:{
     	},//发送ajax请求
     	dataType : "json",
     	url	: server_context+"/phoneCode",
     	success : function(data) {
			if (data.flag == "true"){
				settime(val,60);
				alert(data.msg);
				
			} else if (data.flag == "false") {
				text(data.msg);
				msgClear();
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status+" "+XMLHttpRequest.readyState+" "+textStatus );
            },
 });


}
function pay(){
	//获取表单的值
	
	$('#payDiv').modal('show');
		var code = $("#phoneCode").val();
		if (code == "") {//check if the input is legal
			alert("请输入验证码");
	        $("#code").focus();
	        msgClear();
	        return false;
	    }else{
	    	 
		$.ajax({
			url :  server_context+"/Order",
			type : "POST",
			dataType : "json",
			data : {
				"phoneCode" :code,
					method : "pay"
			},
			success : function(data) {
				if (data.flag == "true") {
					alert("支付成功");
					$('#payDiv').modal('hide');
				} else if (data.flag == "false") {
					alert(data.msg);
					msgClear();
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(XMLHttpRequest.status + " "
						+ XMLHttpRequest.readyState + " " + textStatus);
			},
		});
		return true;
		}
						
					
}

function cancelPay(){
	//获取表单的值
	$('#payDiv').modal('hide');	
	 				
}

