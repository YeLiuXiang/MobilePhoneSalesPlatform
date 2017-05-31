$(function() {
	var ss = $("#ss").text();
	alert(ss);
	/*
	 * 1. 得到所有的错误信息，循环遍历之。调用一个方法来确定是否显示错误信息！
	 */
	$(".errorClass").each(function() {
		showError($(this));//遍历每个元素，使用每个元素来调用showError方法
	});
	
	
	
	/*
	 * 3. 输入框得到焦点隐藏错误信息
	 */
	$(".form-control").focus(function() {
		var labelId = $(this).attr("id") + "Error";//通过输入框找到对应的label的id
		$("#" + labelId).text("");//把label的内容清空！
		showError($("#" + labelId));//隐藏没有信息的label
	});
	
	/*
	 * 4. 输入框失去焦点进行校验
	 */
	$(".form-control").blur(function() {
		var id = $(this).attr("id");//获取当前输入框的id
		var funName = "validate" + id.substring(0,1).toUpperCase() + id.substring(1) + "()";//得到对应的校验函数名
		eval(funName);//执行函数调用
	});
	
	/*
	 * 登录密码校验方法
	 */
	

	
})
function validateLoginpass() {
		var id = "loginpass";
		var value = $("#" + id).val();//获取输入框内容
		/*
		 * 1. 非空校验
		 */
		var ss = $("#ss").html();
		alert(ss);
		if(!value) {
			/*
			 * 获取对应的label
			 * 添加错误信息
			 * 显示label
			 */
			$("#" + id + "Error").text("密码不能为空！");
			showError($("#" + id + "Error"));
			return false;
		}
		/*
		 * 2. 长度校验
		 */
		if(value.length < 3 || value.length > 20) {
			/*
			 * 获取对应的label
			 * 添加错误信息
			 * 显示label
			 */
			$("#" + id + "Error").text("密码长度必须在3 ~ 20之间！");
			showError($("#" + id + "Error"));
			return false;
		}
		return true;	
	}
	
	/*
	 * 联系方式校验方法
	 */
	function validateTel() {
		var id = "tel";
		var value = $("#" + id).val();//获取输入框内容
		/*
		 * 1. 非空校验
		 */
		if(!value) {
			/*
			 * 获取对应的label
			 * 添加错误信息
			 * 显示label
			 */
			$("#" + id + "Error").text("联系电话不能为空！");
			showError($("#" + id + "Error"));
			return false;
		}
		 if(value.length !=11) {
			 /*
			  * 获取对应的label
			  * 添加错误信息
			  * 显示label
			  */
			 $("#" + id + "Error").text("手机号必须为11位数字");
			 showError($("#" + id + "Error"));
			return  false;
		 }
		 if(!(/^1[34578]\d{9}$/.test(value))){ 
	      $("#" + id + "Error").text("手机号不符合格式要求！");
			showError($("#" + id + "Error"));
	        return false; 
	    } 
		
		
		/*
		 * 3. 是否已经绑定校验
		 */
	//	 var id = $("#id").val;
		$.ajax({
			url:"/Smartisan/ManagerServlet?flag=TelVerification&tel="+value,//要请求的servlet
			data:{},//给服务器的参数
			type:"POST",
			dataType:"json",
		//	async:false,//是否异步请求，如果是异步，那么不会等服务器返回，我们这个函数就向下运行了。
			cache:false,
			success:function(result) {
			if(result) {		//如果校验失败
					$("#" + id + "Error").text("该手机号已被绑定！");
					showError($("#" + id + "Error"));
					return false;
				}
			else{
				$("#" + id + "Error").text("√");
				showError($("#" + id + "Error"));
				
			}
			}

		});
		return true;		
	}

function  modify(){
	
	if(!validateLoginpass()) {
		return false;
	}
	if(!validateTel()) {
		
	}
	var  id=  $("#id").val();
	$.ajax({
		url:"/Smartisan/ManagerServlet?flag=AdminModify&id="+id,
		data:$('#ModifyForm').serialize(),
		type:"POST",
		dataType:"json",
		success:function(date){
			alert("返回数据");
		if(date.result==true){
			alert("修改成功");
		window.location.href="/Smartisan/ManagerServlet?flag=管理员信息查询&currentpage=1";
		}else{
			alert("修改失败！");
		}
		},
		error:function(data){
			alert("提交失败");
		}
	});
	
}	
function showError(ele) {
	var text = ele.text();//获取元素的内容
	if(!text) {//如果没有内容
		ele.css("display", "none");//隐藏元素
	} else {//如果有内容
		ele.css("display", "");//显示元素
	}
}
