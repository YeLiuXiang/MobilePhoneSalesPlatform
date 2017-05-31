var test = 1;
function reloadCode() {
	
	document.getElementById("imagecode").src = "checkCode?" + new Date().getTime();
}
var localObj = window.location;
var contextPath = localObj.pathname.split("/")[1];
var basePath = localObj.protocol + "//" + localObj.host + "/" + contextPath;
var server_context = basePath;
//验证码倒计时
var countdown=60; 
function settime(val) { 
	if (countdown == 0) { 
		val.removeAttribute("disabled"); 
		val.value="免费获取验证码"; 
		countdown = 60; 
	} else{ 
		val.setAttribute("disabled", true); 
		val.value="重新发送(" + countdown + ")"; 
		countdown--; 
		setTimeout(function() { 
			settime(val) 
			},1000)
	} 
	 
} 
//发送手机验证码
function sendCode(val){
	var phone = $("#phone").val();
	if(!phone.match(/(0|\+86)?(13[0-9])|15[0-356]|18[025-9]\d{8}/)) {
		text("您输入的手机号格式有误");
		$("#phone").focus();
		msgClear();
	}else{
		$.ajax({  
			type:"POST",
			data:{
				"phone":phone,
         	},//发送ajax请求
         	dataType : "json",
         	url	: server_context+"/phoneCode",
         	success : function(data) {
				if (data.flag == "true") {
					settime(val);
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


/* 打印错误信息 */
function text(msg) {
/*	var err=$("#errText").val();
	if(err=="")//防止信息被覆盖
*/	$("#errText").html(msg);
}
/* 输入框改变时错误信息消失 */
function msgClear() {
	$(this).keydown(function() {
		$("#errText").html("");
	});
}
/* 点击重置按钮 */
$("#reset").click(function() {
	$("input").val("");
});

/* 账号输入框失去焦点时取得内容并判断 */
$(function() {
	$("#account").blur(
			function(event) {
				var account = $(this).val();
				if (account == "") {
					text("账号不能为空");
					$("#account").focus();
					msgClear();
					return false;
				} else {
					
					$.ajax({
						url :  server_context+"/RegisterServlet",
						type : "POST",
						data : {
							"account" : account,
							"method" :"verifyaccount"
						},
						timeout:30000,//超时时间：30秒  
			             dataType:"json",//设置返回数据的格式  
						success : function(data) {
							
							if (data.flag=="false") {
								text(data.msg);
								$("#account").val("");
								$("#account").focus();
								msgClear();
							} else {
								test = 0;
							}

						},
						error : function(XMLHttpRequest, textStatus,
								errorThrown) {
							alert(XMLHttpRequest.status + " "
									+ XMLHttpRequest.readyState + " "
									+ textStatus);
						},
					});

				}
			});
});
/* 密码输入框失去焦点时取得内容并判断 */
$(function() {

	$("#password").blur(function(event) {
		if (test == 0) {
			var password = $(this).val();
			if (password == "") {
				text("密码不能为空");
				msgClear();
				return false;
			} else if (password.length < 8) {
				text("密码不能少于8位");
				msgClear();
				return false;
			}
		}
	});

});

/* 确认密码输入框失去焦点时取得内容并判断 */
$(function() {
	$("#passwordVerify").blur(function(event) {
		if (test == 0) {
			var passwordCheck = $(this).val();
			var password = $("#password").val();
			if (passwordCheck != password) {
				text("密码不一致，请重新输入！");
				msgClear();
				return false;
			}
		}
	});
});

/* 手机号码输入框失去焦点时取得内容并判断 */
$(function() {
	$("#phone").blur(function(event) {
		if (test == 0) {
			var phone = $(this).val();
			if (phone == "") {
				text("手机号码不能为空");
				msgClear();
				return false;
			} else if (!phone.match(/(0|\+86)?(13[0-9])|15[0-356]|18[025-9]\d{8}/)) {
				text("您输入的手机号码有误!");
				msgClear();
				return false;
			}
		}
	});

});

/* 点击提交按钮 */
$("#submit").click(
		function() {
			var account = $("#account").val();
			var password = $("#password").val();
			var passwordVerify = $("#passwordVerify").val();
			var phone = $("#phone").val();
			var code = $("#phoneCode").val();
			if (account == "") {//check if the input is legal
		        text("账号不可以为空！");
		        $("#account").focus();
		        msgClear();
		        return false;
		    }
			else if(password == ""){
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
			else if (phone == "") {
				text("手机号码不能为空");
				$("#phone").focus();
				msgClear();
				return false;
			}else if (phone != ""&&!phone.match(/(0|\+86)?(13[0-9])|15[0-356]|18[025-9]\d{8}/)) {
				text("您输入的手机号码有误!");
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
				url :  server_context+"/RegisterServlet",
				type : "POST",
				dataType : "json",
				data : {
					"account" : account,
					"password" : password,
					"phone" : phone,
					"phoneCode" :code,
					"method":"register"
				},
				success : function(data) {
					if (data.flag == "true") {
						alert(data.msg);
						location.href = server_context+"/Login";
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
