var localObj = window.location;
var contextPath = localObj.pathname.split("/")[1];
var server_context = localObj.protocol + "//" + localObj.host + "/" + contextPath;

	/* 打印错误信息 */
	function text(msg) {
		var err=$("#errText").val();
		$("#errText").html(msg);
	}
	/* 输入框改变时错误信息消失 */
	function msgClear() {
		$(this).keydown(function() {
			$("#errText").html("");
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
	//发送手机验证码
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
	//发送新手机验证码
	function sendNewCode(val){
		var newPhone= $("#newPhone").val();
		if (newPhone == "") {
			text("手机号码不能为空");
			$("#newPhone").focus();
			msgClear();
			return false;
		}else if (newPhone != ""&&!newPhone.match(/(0|\+86)?(13[0-9])|15[0-356]|18[025-9]\d{8}/)) {
			text("您输入的手机号码有误!");
			msgClear();
			return false;
		}else{
			$.ajax({  
				type:"POST",
				data:{
					"newPhone":newPhone
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

	}
	

$("#mofidyPassword").click(function (){
		//获取表单的值
		
			var password = $("#password").val();
			var passwordVerify = $("#passwordVerify").val();
			var code = $("#phoneCode").val();
			 if(password == ""){
		        text("密码不可以为空!");
		        $("#password").focus();
		        msgClear();
		        return false;
		    }
			else if (password != passwordVerify) {
				text("密码不一致!");
				$("#passwordVerify").focus();
				msgClear();
				return false;
			}
			else if (code == "") {//check if the input is legal
		        text("请输入验证码");
		        $("#code").focus();
		        msgClear();
		        return false;
		    }else{
		    	 
			$.ajax({
				url :  server_context+"/CustomerInfo",
				type : "POST",
				dataType : "json",
				data : {
					"newPassword" : password,
					"phoneCode" :code,
					"method":"modifyPassword"
				},
				success : function(data) {
					if (data.flag == "true") {
						alert(data.msg);
					} else if (data.flag == "false") {
						text(data.msg);
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
							
						
	});
$("#mofidyPhone").click(function (){
	//获取表单的值
	
		var phoneCode = $("#phoneCode").val();
		var newPhone = $("#newPhone").val();
		var newPhoneCode = $("#newPhoneCode").val();
		 if(phoneCode == ""){
	        text("原手机验证码不能为空!");
	        $("#phonCode").focus();
	        msgClear();
	        return false;
	    }else if (newPhone == "") {
			text("手机号码不能为空");
			$("#newPhone").focus();
			msgClear();
			return false;
		}else if (newPhone != ""&&!newPhone.match(/(0|\+86)?(13[0-9])|15[0-356]|18[025-9]\d{8}/)) {
			text("您输入的手机号码有误!");
			msgClear();
			return false;
		}
		else if (newPhoneCode == "") {
			text("新手机验证码不能为空!");
			$("#newPhoneCode").focus();
			msgClear();
			return false;
		}else{
	    	 
		$.ajax({
			url :  server_context+"/CustomerInfo",
			type : "POST",
			dataType : "json",
			data : {
				"newPhone" : newPhone,
				"newPhoneCode" :newPhoneCode,
				"phoneCode" :phoneCode,
				"method":"modifyPhone"
			},
			success : function(data) {
				if (data.flag == "true") {
					alert(data.msg);
				} else if (data.flag == "false") {
					text(data.msg);
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
						
					
});
