
$(function(){
	$("button.btn-danger").click(function(){	//点击红色“编辑”按钮弹出信息编辑弹框
		$("#editMsg").css({"display":"block"});
		$("#container .row .col-lg-6 input[type='text']").css("z-index",-10);
	});
	
	$("#back").css({	//灰色背景div
		width:$(document).width() + "px",
		height:$(document).height() + "px"
	});
	
	$("button.close").click(function() {	//点击关闭按钮
		$("#editMsg").css({"display":"none"});
		$("#container .row .col-lg-6 input[type='text']").css("z-index",0);
	});
	 
	 $("input.btn-success[value='完成']").click(function(){//点击“完成”按钮
		$("#container .row .col-lg-6 input[type='text']").css("z-index",0);
	 	$("#editMsg").css({"display":"none"});
	 });
	 
//	var $as = $("#editMsg ul#dropdownMenu1 li>a");	//“状态”下拉框选中事件
//	for(var i = 0; i<$as.length; i++){
//		$as[i].click(function(){
//			var aText = $as[i].html();
//			$("button#dropdownMenu1").html(aText);
//		})
//	}

	
	
});

