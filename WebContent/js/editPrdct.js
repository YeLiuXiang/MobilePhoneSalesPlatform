$(function(){
	var flag = false;
	$("input[type='button']").click(function(){
		$("input[type='text']").each(function(i){
			if( $(this).val() == "" ){
				flag = false;
				if(i == 0){	//商品编号输入框
					alert("请输入商品编号！");
				}
				if(i == 1){	//商品名称输入框
					alert("请输入商品名称！");
				}
				if(i == 2){	//商品类型输入框
					alert("请输入商品类型！");
				}
				if(i == 3){	//标题简介输入框
					alert("请输入标题简介！");
				}
				if(i == 4){	//型号输入框
					alert("请输入商品型号！");
				}
				if(i == 5){	//颜色输入框
					alert("请输入商品颜色！");
				}
				if(i == 6){	//库存输入框
					alert("请输入商品库存！");
				}
				if(i == 7){	//内存输入框
					alert("请输入商品内存！");
				}
				if(i == 8){	//价格输入框
					alert("请输入商品价格！");
				}
			}
			else{
				flag = true;
			}
		});
		if(flag == true){
			$("form").submit();
		}
	});
})

function modify(){
	$.ajax({
		url:"/Smartisan/ManagerServlet?flag=GoodsModify",
		data:$('#modifyForm').serialize(),
		type:"POST",
		dataType:"json",
		success:function(data){
			if(data){
				alert("修改成功");
			}
			else{
				alert("修改失败");
			}
			window.location = "/Smartisan/ManagerServlet?flag=已上架商品查询&currentpage=1";

		},    
		error : function() {    
			alert("提交错误");    
		}    
	});
	

}