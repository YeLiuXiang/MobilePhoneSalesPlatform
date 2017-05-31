

$(".carousel-control").css("line-height",$(".carousel-inner img").height()+"px");
$(window).resize(function() {
	/* Act on the event */
	var $height = $(".carousel-inner img").eq(0).height() ||
	$(".carousel-inner img").eq(0).height()
	$(".carousel-inner img").eq(0).height();
	$(".carousel-control").css("line-height",$(".carousel-inner img").height()+"px");
});


$("#btnSearch").click(function(event) {
	/* Act on the event */
	var keyword = $("#keyword").val();
	var localObj = window.location;
	var contextPath = localObj.pathname.split("/")[1];
	var basePath = localObj.protocol+"//"+localObj.host+"/"+contextPath;
	var server_context=basePath;
	window.open(server_context+"/GoodsSearch?method=key&key="+keyword);
});


