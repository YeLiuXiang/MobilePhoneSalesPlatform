var test = 1;
var localObj = window.location;
var contextPath = localObj.pathname.split("/")[1];
var server_context = localObj.protocol + "//" + localObj.host + "/" + contextPath;

function reloadCode() {
	
	document.getElementById("imagecode").src =server_context+ "/checkCode?" + new Date().getTime();
}

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
				} 
					
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

//登录
$("#login").click(function(){
    var account = $("#account").val();
    var password = $("#password").val();
    var code = $("#code").val();
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
   
    else if (code == "") {//check if the input is legal
        text("请输入验证码");
        $("#code").focus();
        msgClear();
        return false;
    }else{
        $.ajax({  
            type:"POST",
            data:{
            	"account":account,
            	"password":password,
            	"checkCode":code,
            	"method":"login",},//发送ajax请求
            dataType : "json",
            url	: server_context+"/Login",
            success : function(data) {
            	
				if (data.flag == "true") {
					setTimeout("history.go(-1)", 500); 
					
				} else if (data.flag == "false") {
					text(data.msg);
					msgClear();
				}
			},
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                 alert(XMLHttpRequest.status+" "+XMLHttpRequest.readyState+" "+textStatus );
                   },
        });
       return true;
       }
});

});
