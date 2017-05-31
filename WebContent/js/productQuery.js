$(function(){

	$("button.btn-danger").click(function(){	//点击“编辑”按钮弹出
		$("#container input").attr("disabled",true);
		$("#editMsg").css({"display":"block"});
	});
				
	$("#back").css({
		width:$(window).width() + "px",
		height:$(window).height() + "px"
	});
				 
	$("button.close").click(function() {	//点击关闭按钮
		$("#container input").attr("disabled",false);
		$("#editMsg").css({"display":"none"});
	});
				 
	//$('input').attr("disabled",true);	//将input元素设置为disabled 
	 $("#editMsg button.btn-success").click(function(){	//点击“完成”按钮
	 	$("#editMsg").css({"display":"none"});
	 })

})