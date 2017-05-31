function goodsQueryByIdOrName( ) {
	var id = $("#goodsid").val();
	if(!id) {//非空校验
	alert("输入不能为空！");
	return false;
	}
	window.location = "GoodsServlet?method=GoodsQueryByIdOrName&searchType=2&id="
			+ id;
	
}

function goodsUpShelf(id2) {
	if(!YesOrNo2()){
		return;
	}
	$.ajax( {    

		url:'GoodsServlet',// 跳转到     
		data:{    
			id:id2,
			method:"GoodsOnShelf",
		},    
		type:'post',     
		dataType:'json',    
		success:function(data) {    
			if(data){
				alert("上架成功");
				window.location = "GoodsServlet?method=OffShelfQuery&currentpage=1";
			}
			else{
				alert("上架失败");
			}

		},    
		error : function() {    
			alert("提交错误");    
		}    
	});    
}

function YesOrNo2() {
	var msg = "确认上架该商品？";
	if (confirm(msg)==true){
	return true;
	}else{
	return false;
	}
	}