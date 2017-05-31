function Modify(id2){
	if(!YesOrNo2()){
		return;
	}
	
	$.ajax({
		url:"/Moko_Management/BoothServlet",
		data:{
			method:"CommodityBoothModify",
			id:id2
		},
		type:"POST",
		dataType:"json",
		success:function(date){
			if(date){
				window.location.href="/Moko_Management/BoothServlet?method=CommodityBoothApplyQuery&currentpage=1";
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
	if(!YesOrNo1()){
		return;
	}
	
	$.ajax({
		url:"/Moko_Management/BoothServlet",
		data:{
			method:"CommodityBoothAgree",
			id:id2
		},
		type:"POST",
		dataType:"json",
		success:function(date){
			if(date){
				alert("请求成功");
				}else{
					alert("请求失败！");
				}
			window.location.href="BoothServlet?method=CommodityBoothQuery&currentpage=1";
		},
		error:function(data){
			alert("提交失败！"); 
		}
	});
}
function YesOrNo1() {
	var msg = "确认该商品审核通过？";
	if (confirm(msg)==true){
	return true;
	}else{
	return false;
	}
}
function YesOrNo2() {
	var msg = "确认撤除该展位商品，并替换新商品？";
	if (confirm(msg)==true){
	return true;
	}else{
	return false;
	}
}