//$(function() {
//	var $width = $("#back").width();
//	var $height = $("#back").height();
//	$width = $(window).width();
//	$height = $(window).height();
//		alert($width + " + " + $height);
//})

$(function(){
	$("button.btn-danger").click(function(){	//点击“编辑”按钮弹出
		$("#editMsg").css({"display":"block"});
	});
	
	 $("#back").css({
	 	width:$(window).width() + "px",
	 	height:$(window).height() + "px"
	 });
	 
	 $("button.close").click(function() {	//点击关闭按钮
	 	$("#editMsg").css({"display":"none"});
	 });
	 
//	 $("button.btn-success").click(function(){	//点击“完成”按钮
//	 	$("#editMsg").css({"display":"none"});
//	 })
})


