
/*将商品ID隐藏*/
$(".modelNo").hide();

/*当鼠标经过“加入购物车按钮时，判断库存，若库存为0，则两个按钮禁用”*/
$(".btnAddCart").mouseover(function() {
	var modelCapacity = $(this).prev().prev().prev().children('.modelCapacity').text();
	if(modelCapacity==0){
			$(this).prop("disabled",true);
			$(this).next().prop("disabled",true);
	}

});
/*当鼠标经过“加入购物车按钮时，判断库存，若库存为0，则两个按钮禁用”*/
$(".btnBuyNow").mouseover(function() {
	var modelCapacity = $(this).prev().prev().prev().prev().children('.modelCapacity').text();
	if(modelCapacity==0){
			$(this).prev().prop("disabled",true);
			$(this).prop("disabled",true);
	}

});


/*点击加入购物车按钮,获取当前商品的ID*/
$(".btnAddCart").click(function() {
	var modelNo = $(this).prev().text();//商品编号
	var count = 1;//商品数量
	var localObj = window.location;
	var contextPath = localObj.pathname.split("/")[1];
	var basePath = localObj.protocol+"//"+localObj.host+"/"+contextPath;
	var server_context=basePath;
	$.ajax({
		url:server_context+"/addToCart",
		type: "POST",
		dataType: "text",
		data: {"modelNo":modelNo,"count":count},
		success:function(msg){
			if(msg=="success"){
                alert("商品已加入购物车");
            }else if(msg.indexOf("<title>登录</title>")>0){
            	location.href=server_context+"/login";
            }else{
            	alert(msg);
            }
			
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status+" "+XMLHttpRequest.readyState+" "+textStatus );
        },
	});
});

/*点击立即购买按钮,获取当前商品的ID */
$(".btnBuyNow").click(function() {
	var modelNo = $(this).prev().prev().text();//商品编号
	var count = "1";//商品数量
	var json = [];
	var arr  =
	{
	 modelNo: modelNo,
	 goodNum : count
	};
	json.push(arr);
	 var localObj = window.location;
	 var contextPath = localObj.pathname.split("/")[1];
	 var basePath = localObj.protocol+"//"+localObj.host+"/"+contextPath;
	 var server_context=basePath;
	 // window.location=server_context+"/avc?goodsJson="+JSON.stringify(json);
	 $.ajax({
	 url: server_context+"/checkOrder",
	 type: "POST",
	 dataType: "text",
	 data: { "goodsJson": JSON.stringify(json),"source":"oneKeyOrder"},
	 success: function (msg) {
	 window.location=msg;
	 },
	 error: function (XMLHttpRequest, textStatus, errorThrown) {
	 alert(XMLHttpRequest.status + " " + XMLHttpRequest.readyState + " " +
	 textStatus);
	 },
	 });
});