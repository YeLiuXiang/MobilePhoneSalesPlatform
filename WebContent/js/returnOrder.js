function Refuse(id2){
	if(!YesOrNo2()){
		return;
	}
	$.ajax({
		url:"ReturnOrderServlet",
		data:{
			method:"RefuseReturn",
			id:id2
		},
		type:"POST",
		dataType:"json",
		success:function(date){
			if(date){
				alert("已拒绝退换货");
				window.location.href="ReturnOrderServlet?method=ReturnOrderQuery&searchType=0&currentpage=1";
				}else{
					alert("请求失败！");
				}
		},
		error:function(data){
			alert("提交失败！");
		}
	});
	
}

function Agree(id2){
	if(!YesOrNo()){
		return;
	}
	
	$.ajax({
		url:"ReturnOrderServlet",
		data:{
			method:"AgreeReturn",
			id:id2
		},
		type:"POST",
		dataType:"json",
		success:function(date){
			if(date){
				alert("已同意退换货");
				window.location.href="ReturnOrderServlet?method=ReturnOrderQuery&searchType=0&currentpage=1";
				}else{
					alert("请求失败！");
				}
			
		},
		error:function(data){
			alert("提交失败！");
		}
	});
}

function YesOrNo() {
	var msg = "您已经过审核？确认同意退换货！";
	if (confirm(msg)==true){
	return true;
	}else{
	return false;
	}
	}
function YesOrNo2() {
	var msg = "您已经过审核？确认拒绝退换货！";
	if (confirm(msg)==true){
	return true;
	}else{
	return false;
	}
	}