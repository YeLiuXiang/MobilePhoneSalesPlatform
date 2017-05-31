function orderQueryById() {
	var id = $("#orderid").val();

	window.location = "OrderServlet?method=OrderQueryById&searchType=0&id="+ id;
}

function DeliverGoods(id2){
	var account=$("#account").val();
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
				window.location='OrderServlet?method=OrderQuery&searchType=0&currentpage=1&account='+account;
				}else{
					alert("发货失败！");
				}
		},
		error:function(data){
			alert("提交失败！");
		}
	});
}

