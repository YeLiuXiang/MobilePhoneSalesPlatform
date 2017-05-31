
	
function DeliverGoods(id2){
	$.ajax({
		url:"OrderServlet",
		data:{
			method:"DeliverGoods",
			id:id2
		},
		type:"POST",
		dataType:"json",
		success:function(date){
			if(date){
				alert("发货成功");
				}else{
					alert("发货失败！");
				}
		},
		error:function(data){
			alert("提交失败！");
		}
	});
}

