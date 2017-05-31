var localObj = window.location;
var contextPath = localObj.pathname.split("/")[1];
var basePath = localObj.protocol + "//" + localObj.host + "/" + contextPath;
var server_context = basePath;
var modelIdA=-1;
//取消

function getOrderId(val){
	var buttArr = document.getElementsByName(val.name);
	var orderIdArr = document.getElementsByName("orderId");
	for(var i = 0; i < buttArr.length; i++)
		if(buttArr[i]==val){
		return orderIdArr[i];
		}
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

function cancel(val){
	var orderId=getOrderId(val);
	//跳页面

	$.ajax({
		url: server_context + "/Order",
		type: "POST",
		dataType: "json",
		data: {  
			"orderId": orderId, 
			"method":"cancel" },
		success: function (data) {
			if (data.flag="true") {
				alert("取消订单成功!");
				window.location.reload();
			} else{
				alert(data.msg);
			} 
		},
		error: function (XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status + " " + XMLHttpRequest.readyState + " " + textStatus);
		},
	});
}


function receive(val){
	var orderId=getOrderId(val);

	$.ajax({
		url: server_context + "/Order",
		type: "POST",
		dataType: "json",
		data: {  
			"orderId": orderId, 
			"method":"receive" },
		success: function (data) {
			if (data.flag="true") {
				alert("确认收货成功!");
				window.location.reload();
			} else{
				alert(data.msg);
			} 
		},
		error: function (XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status + " " + XMLHttpRequest.readyState + " " + textStatus);
		},
	});
}

function pay(val){
	orderIdA=getOrderId(val);
	$('#payDiv').modal('show');
}
function ackPay(){
	//获取表单的值
	if(modelIdA==-1){
		alert("错误");
		return ;
		$('#payDiv').modal('hide');
	}
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
				"orderId" :orderId,
					method : "pay"
			},
			success : function(data) {
				if (data.flag == "true") {
					orderIdA=-1;
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

