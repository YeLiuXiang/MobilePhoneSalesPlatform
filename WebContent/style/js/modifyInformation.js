$(document).ready(function () {
	var localObj = window.location;
	var contextPath = localObj.pathname.split("/")[1];
	var server_context = localObj.protocol + "//" + localObj.host + "/" + contextPath;
	//表单提交
	$(".submit1").click(function (){
		//获取表单的值
		var sex = $("#sexSelect").val();
		var nickName = $("#nickName").val();
		var email = $("#email").val();
		var receiver = $("#receiver").val();
		var receiverPhone = $("#receiverPhone").val();
		var address = $("#address").val();
		
		if (receiverPhone!=""&&!receiverPhone.match(/^1[34578]\d{9}$/)) {
			text("请输入正确的手机号");
			$("#receiverPhone").focus();
			msgClear();
			return false;
		}
		if (email != "" && !email.match(/^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/)) {
			text("请输入正确的邮箱");
			$("#email").focus();
			msgClear();
			return false;
		}
		
		$.ajax({
			url: server_context + "/CustomerInfo?method=modifyBaseInfo",
			type: "POST",
			dataType: "json",
			data: {  "sex": sex, "nickName": nickName,"email": email,"receiver": receiver, "receiverPhone": receiverPhone ,  "address": address},

			success: function (data) {
				if (data.flag == "true") {
					alert(data.msg);
					
				} else if (data.flag == "false") {
					text(data.msg);
					msgClear();
				}
			},
			error: function (XMLHttpRequest, textStatus, errorThrown) {
				alert(XMLHttpRequest.status + " " + XMLHttpRequest.readyState + " " + textStatus);
			},
		});
	});
	

});
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
	
	


	