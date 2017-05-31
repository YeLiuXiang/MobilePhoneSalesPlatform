function goodsQueryByIdOrName( ) {
	var id = $("#goodsid").val();
	if(!id) {//非空校验
	alert("输入不能为空！");
	return false;
	}
	window.location = "GoodsServlet?method=GoodsQueryByIdOrName&searchType=1&id="
			+ id;
	
}

function goodsOffShelf(id2) {
	
	if(!YesOrNo()){
		return;
	}
	$.ajax( {    

		url:'GoodsServlet',// 跳转到     
		data:{    
			id:id2,
			method:"GoodsOffShelf",
		},    
		type:'post',     
		dataType:'json',    
		success:function(data) {    
			if(data){
				alert("下架成功");
				window.location = "GoodsServlet?method=OnShelfQuery&currentpage=1";
			}
			else{
				alert("下架失败");
			}
		},    
		error : function() {    
			alert("提交错误");    
		}    
	});  
}
function YesOrNo() {
	var msg = "确认下架该商品？";
	if (confirm(msg)==true){
	return true;
	}else{
	return false;
	}
}