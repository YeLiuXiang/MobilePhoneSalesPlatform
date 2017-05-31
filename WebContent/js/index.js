$('#collapseOne, #collapseTwo, #collapseThree, #collapseFout, #collapseFive').collapse({
	 		parent : '#accordion',
	 		toggle : false,
		});

//function addGoods() {
//	
//	
//	//用户店铺好
//	//商品编号
//	//商品型号
//	//用视图
//	$.ajax( {    
//
//		url:'GoodsServlet',// 跳转到     
//		data:{    
//			id:id2,
//			method:"IdQuery",
//		},    
//		type:'post',     
//		dataType:'json',    
//		success:function(data) {    
//			if(data){
//				alert("下架成功");
//				window.location = "subpage/productManagement/addPrdct.jsp";
//			}
//			else{
//				alert("下架失败");
//			}
//		},    
//		error : function() {    
//			alert("提交错误");    
//		}    
//	});  
//}
//}