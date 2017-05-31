//$(function() {
//	/*
//	 * 1. 得到所有的错误信息，循环遍历之。调用一个方法来确定是否显示错误信息！
//	 */
//	$(".errorClass").each(function() {
//		showError($(this));//遍历每个元素，使用每个元素来调用showError方法
//	});
//	
//	/*
//	 * 3. 输入框得到焦点隐藏错误信息
//	 */
//	$(".form-control").focus(function() {
//		var labelId = $(this).attr("id") + "Error";//通过输入框找到对应的label的id
//		$("#" + labelId).text("");//把label的内容清空！
//		showError($("#" + labelId));//隐藏没有信息的label
//	});
//	/*
//	 * 4. 输入框失去焦点进行校验
//	 */
//	$(".form-control").blur(function() {
//		var id = $(this).attr("id");//获取当前输入框的id
//		var funName = "validate" + id.substring(0,1).toUpperCase() + id.substring(1) + "()";//得到对应的校验函数名
//		eval(funName);//执行函数调用
//	});
//	
//	/*
//	 * 登录编号校验方法
//	 */
//	
//
//	
//})
//function validateId() {
//		var id = "id";
//		var value = $("#" + id).val();//获取输入框内容
//		if(!value) {//非空校验
//			
//			$("#" + id + "Error").text("商品编号不能为空！");
//			showError($("#" + id + "Error"));
//			return false;
//		}
////		if(value.length < 3 || value.length > 20) {//长度校验
////			$("#" + id + "Error").text("密码长度必须在3 ~ 20之间！");
////			showError($("#" + id + "Error"));
////			return false;
////		}
//		$.ajax({
//			url:"/Moko_Management/ManagerServlet?flag=IDVerification",//要请求的servlet
//			data:{
//				id:value,
//				searchType:0
//			},//给服务器的参数
//			type:"POST",
//			dataType:"json",
//			cache:false,
//			success:function(result) {
//			if(result) {		//商品编号已存在
//					$("#" + id + "Error").text("该编号已被绑定！");
//					showError($("#" + id + "Error"));
//					return false;
//				}
//			else{
//				$("#" + id + "Error").text("√");
//				showError($("#" + id + "Error"));
//				
//			}
//		}
//
//		});
//	
//		return true;	
//	}
//	
//	/*
//	 * 型号校验方法
//	 */
//	function validateMID1() {
//		var id = "mID1";
//		var value = $("#" + id).val();//获取输入框内容
//		if(!value) {
//			$("#" + id + "Error").text("型号不能为空！");
//			showError($("#" + id + "Error"));
//			return false;
//		}
////		 if(value.length !=11) {
////			 /*
////			  * 获取对应的label
////			  * 添加错误信息
////			  * 显示label
////			  */
////			 $("#" + id + "Error").text("手机号必须为11位数字");
////			 showError($("#" + id + "Error"));
////			return  false;
////		 }
////		 if(!(/^1[34578]\d{9}$/.test(value))){ 
////	      $("#" + id + "Error").text("手机号不符合格式要求！");
////			showError($("#" + id + "Error"));
////	        return false; 
////	    } 
////		
//		$.ajax({
//			url:"/Moko_Management/ManagerServlet?flag=IDVerification",//要请求的servlet
//			data:{
//				id:value,
//				searchType:1
//			},//给服务器的参数
//			type:"POST",
//			dataType:"json",
//			success:function(result) {
//			if(result) {		//型号已存在
//					$("#" + id + "Error").text("×");
//					showError($("#" + id + "Error"));
//					return false;
//				}
//			else{
//				$("#" + id + "Error").text("√");
//				showError($("#" + id + "Error"));
//				
//			}
//			},
//			error:function(){
//				
//			}
//
//		});
//		return true;		
//	}
//
//function NameVerity(){
//	var name=$("#name").val();
//	if(name==""){
//		$("#nameError").text("商品名称不能为空");
//		showError($("#nameError"));
//		return false;
//	}
//}
function  GoodsAdd(){
	var name = $("#name").val();
	var imge = $("#imge").val();
	var title = $("#title").val();
	if(name==""){
		alert("商品名称不能为空！");
		return;
	}else if(imge==""){
		alert(" 详情图片未选择");
		return;
	}else if(title==""){
		alert(" 商品标题不能为空");
		return;
	}
	var storeId = $("#storeId").val();
	 var form = new FormData(document.getElementById("addGoodsForm"));
  $.ajax({
      url:"/Moko_Management/GoodsServlet?method=AddGoods&storeId="+storeId,
      type:"post",
      data:form,
      processData:false,
      contentType:false,
      success:function(data){

  		if(data){
  			alert("商品添加成功");
  		}else{
  			alert("商品添加失败！");
  		}
      },
      error:function(e){
    	  alert("提交失败");
      }
  });        
  get();//此处为上传文件的进度条
//	$
	
}	
//function showError(ele) {
//	var text = ele.text();//获取元素的内容
//	if(!text) {//如果没有内容
//		ele.css("display", "none");//隐藏元素
//	} else {//如果有内容
//		ele.css("display", "");//显示元素
//	}
//}
function  GoodsModify1(id2){
	var title2 = $("#title").val();
	if(title2==""){
		alert("商品标题不能为空！");
		return;
	}
	
	
  $.ajax({
      url:"/Moko_Management/GoodsServlet",
      type:"post",
      data:{method:"GoodsTitleModify",
    	  id:id2,
    	  title:title2
      },
      success:function(data){

  		if(data){
  			alert("商品标题修改成功");
  		}else{
  			alert("修改失败！");
  		}
      },
      error:function(e){
    	  alert("提交失败");
      }
  });        
}	
function  GoodsModify(id2,index){
//	if(number==""){
//		alert("商品库存不能为空！");
//		return;
//	}else if(price==""){
//		alert("商品价格不能为空！");
//		return;
//	}
  $.ajax({
      url:"/Moko_Management/GoodsServlet?method=GoodsModify&id="+id2+"&index="+index,
      type:"post",
      data:$('#modifyGoodsForm').serialize(),
      success:function(data){
  		if(data){
  			alert("商品修改成功");
  		}else{
  			alert("修改失败！");
  		}
      },
      error:function(e){
    	  alert("提交失败");
      }
  });        
}	
