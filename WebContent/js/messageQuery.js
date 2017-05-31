function MessageQueryByIdOrName() {
	var IdOrName = $("#sid").val();
	if(IdOrName==""){
		alert("查询条件不能为空！");
		return;
	}
	window.location.href="/Moko_Management/StoreServlet?method=StoreMessageQuery&id="+IdOrName;
}

function Freeze(id2) {
	if(!YesOrNo1()){
		return;
	}
	
	$.ajax({
		url:"/Moko_Management/StoreServlet",
		data:{
			method:"StoreFreeze",
			id:id2
		},
		type:"POST",
		dataType:"json",
		success:function(date){
			if(date){
				window.location.href="/Moko_Management/StoreServlet?method=StoreMessageQuery&id="+id2;
				}else{
					alert("请求失败！");
				}
		
		},
		error:function(data){
			alert("提交失败！"); 
		}
	});
}
function UnFreeze(id2) {
	if(!YesOrNo2()){
		return;
	}
	
	$.ajax({
		url:"/Moko_Management/StoreServlet",
		data:{
			method:"StoreUnFreeze",
			id:id2
		},
		type:"POST",
		dataType:"json",
		success:function(date){
			if(date){
				window.location.href="/Moko_Management/StoreServlet?method=StoreMessageQuery&id="+id2;
				}else{
					alert("请求失败！");
				}
		
		},
		error:function(data){
			alert("提交失败！"); 
		}
	});
}
function YesOrNo1() {
	var msg = "确认冻结该店铺？";
	if (confirm(msg)==true){
	return true;
	}else{
	return false;
	}
}
function YesOrNo2() {
	var msg = "确认解除对该店铺的冻结？";
	if (confirm(msg)==true){
	return true;
	}else{
	return false;
	}
}
