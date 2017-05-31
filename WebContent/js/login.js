$(function(){
//	var regUsrAccount = /^1[34578]\d{9}$/;	//管理员账号为手机号
	var regUsrPsw = /\w{3,20}/;	//密码字母数字共8位
	
	$("input.form-control").each(function(i){
		if (i == 0){	//账号
			$(this).change(function(){
				var usrAcnt = $(this).val();
				if( $(this).val() == "" ){
					alert("请输入账号！");
//				}else if(!usrAcnt.match(regUsrAccount)){
//					alert("请输入11位数的手机号！");
				}
			});
//			$(this).blur(function(){
//				if( $(this).val() == "" ){
//					alert("请输入账号！");
//				}
//			});
		}else if(i == 1){	//密码
			$(this).change(function(){
				var usrPsw = $(this).val();
				if( $(this).val() == "" ){
					alert("请输入密码！");
				}else if(!usrPsw.match(regUsrPsw)){
					alert("请输入大于3位且小于20位的密码！");
				}
			});
//			$(this).blur(function(){
//				if( $(this).val() == "" ){
//					alert("请输入密码！");
//				}
//			});
		}
	});
	
})
function login(){
	
	var account=$("#account").val();
	var pass = $("#pass").val();//获取输入框内容
	var validateCode=$("#validateCode").val();
	if(account==""){
		alert("请输入账号！");
		return;
	}else if(pass==""){
		alert("请输入密码！");
		return;
	}else if(validateCode==""){
		alert("请输入验证码！");
		return;
	}
		
		$.ajax({
			url:"/Moko_Management/LoginServlet?method=Login&pass="+$.md5(pass),
			data:$('#registForm').serialize(),
			type:"POST",
			dataType:"json",
			success:function(date){
			
				
				if(date.result==2){
					var account=date.account+"";    
					if("aaaa" == $.trim(account)|"bbbb" == $.trim(account))
							window.location.href="index.jsp";
					else
						window.location.href="index3.jsp";
				}	else if(date.result==0){
					alert("账号不存在");
				}else if(date.result==1){
					alert("密码不正确");
				}else if(date.result==3){
					alert("验证码不正确");
					}else{
						alert("error:"+date);
					}
			},
			error:function(data){
				alert("登陆失败");
			}
		});
		
	}	

//
//	$(".errorClass").each(function() {
//		showError($(this));//遍历每个元素，使用每个元素来调用showError方法  
//	})
//	//输入框得到焦点时隐藏错误信息
//	$(".form-control").focus(function() {
//		var labelID = $(this).attr("id") +"Error";
//		$("#"+labelID).text("");
//		showError($("#"+labelID));
//	});
//	$(".form-control").blur(function() {  //输入框失去焦点进行校验 
//		var  id = $(this).attr("id");
//		var funName = "validateLoginname()";//
//		eval(funName);
//	});
//	/* 
//	 * 5. 表单提交时进行校验 
//	 */  
//	$("#registForm").submit(function() {  
//		var bool = true;//表示校验通过  
//		if(!validateLoginname()) {  
//			bool = false;  
//		}  
////		if(!validateLoginpass()) {  
////		bool = false;  
////		}  
////		if(!validateReloginpass()) {  
////		bool = false;  
////		}  
////		if(!validateEmail()) {  
////		bool = false;  
////		}  
////		if(!validateVerifyCode()) {  
////		bool = false;  
////		}  
//
//		return bool;  
//	});  
//
//	/* 
//	 * 登录名校验方法 
//	 */  
//	function validateLoginname() {  
//		var id = "usertelephone";  
//		var value = $("#"+id).val();
//
//		if(!value){
//			$("#" + id + "Error").text("用户名不能为空！");  
//			showError($("#" + id + "Error"));  
//			return false;
//		}
//		$.ajax({
//			url:"/Moko_Management/ManagerServlet",
//			date:{flag:"验证",usertelephone:value},
//			type:"POST",
//			dateType:"json",
//			async:false,//是否异步请求，如果是异步，那么不会等服务器返回，我们这个函数就向下运行了。
//			success:function(result){
//				if(!result){
//					$("#" + id + "Error").text("用户名已被注册！");  
//					showError($("#" + id + "Error"));    
//					return false;  
//				}
//			}
//
//
//		});
//		return true;
//
//	}
//
//	/* 
//	 * 判断当前元素是否存在内容，如果存在显示，不页面不显示！ 
//	 */  
//	function showError(ele) { 
//		var text= ele.text();
//		if(!text) {//如果没有内容  
//			ele.css("display", "none");//隐藏元素  
//		} else {//如果有内容  
//			ele.css("display", "");//显示元素  
//		}  
//
//	}

//})

