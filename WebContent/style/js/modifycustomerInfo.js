$(document).ready(function () {
	/*name不为空时设置文本框只读*/
	if ($("#name").val() != " ") { $("#name").attr("readonly", "readonly"); }
	//验证码刷新
	$(".checkCodeImg").click(function (){
		var time=new Date().getTime();
		document.getElementById("checkCodeImage").src="checkCode.jpg?"+time;
	});
	
	//表单提交
	$(".submit1").click(function (){
		//获取表单的值
		var name = $("#name").val();
		var sex = $("#sexSelect").val();
		var phoneNumber = $("#phoneNumber").val();
		var email = $("#email").val();
		var address = $("#addressReceive").val();
		var checkCode = $("#checkCode").val();
		if (name == "") {
			nameNull();
			return false;
		}
		if (phoneNumber == "") {
			text("手机号不能为空");
			phoneNumberFail();
			return false;
		}
		if (!phoneNumber.match(/^1[34578]\d{9}$/)) {
			text("请输入正确的手机号");
			phoneNumberFail();
			return false;
		}
		if (email != "" && !email.match(/^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/)) {
			text("请输入正确的邮箱");
			$("#email").focus();
			msgClear();
			return false;
		}
		if (address == "") {
			text("请输入收货地址");
			$("#addressReceive").focus();
			msgClear();
			return false;
		}
		if (checkCode == "") {
			text("请输入验证码");
			checkCodeFail();
			return false;
		}
		
		var localObj = window.location;
		var contextPath = localObj.pathname.split("/")[1];
		var basePath = localObj.protocol + "//" + localObj.host + "/" + contextPath;
		var server_context = basePath;
		$.ajax({
			url: server_context + "/AlterCustomerCheck",
			type: "POST",
			dataType: "text",
			data: { "name": name, "sex": sex, "phoneNumber": phoneNumber, "email": email, "address": address, "checkCode": checkCode },

			success: function (msg) {
				if (msg == "success") {
					alert("修改成功");
					location.href = server_context + "/customer";
				} else if (msg == "nullFail") {
					text("姓名/手机号不能为空");
				} else if (msg == "phoneNumberIsExist") {
					text("手机号存在");
					phoneNumberFail();
				} else if (msg == "checkCodeFail") {
					text("验证码错误");
					checkCodeFail();
				} else {
					alert(msg);
				}
			},
			error: function (XMLHttpRequest, textStatus, errorThrown) {
				alert(XMLHttpRequest.status + " " + XMLHttpRequest.readyState + " " + textStatus);
			},
		});
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
	function nameNull(){
		/*姓名为空时处理函数*/
		text("姓名不能为空");
        $("#name").focus();
        msgClear();
	}
	function phoneNumberFail(){
		/*手机号错误处理函数*/
		$("#phoneNumber").val("");
		$("#phoneNumber").focus();
		msgClear();
	}
	function checkCodeFail(){
		/*验证码错误处理函数*/
        $("#checkCode").val("");
        $("#checkCode").focus();
        msgClear();
	}