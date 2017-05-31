$(function() {
	
	$("#file_upload").change(function() {
		var $file = $(this);	
		var fileObj = $file[0];	
		var windowURL = window.URL || window.webkitURL;
		var dataURL;
		var $img = $("#preview");
		 
		if(fileObj && fileObj.files && fileObj.files[0]){
			dataURL = windowURL.createObjectURL(fileObj.files[0]);
			$img.attr('src',dataURL);
		}else{
			dataURL = $file.val();
			var imgObj = document.getElementById("preview");
			imgObj.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
			imgObj.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = dataURL;
		}
	});
		
});


function Modify(id2){
	if(!YesOrNo2()){
		return;
	}
	
	$.ajax({
		url:"/Moko_Management/BoothServlet",
		data:{
			method:"AdvertingBoothModify",
			id:id2
		},
		type:"POST",
		dataType:"json",
		success:function(date){
			if(date){
				window.location.href="/Moko_Management/BoothServlet?method=AdvertingBoothApplyQuery&currentpage=1";
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
			method:"AdvertingBoothAgree",
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
			window.location.href="BoothServlet?method=AdvertingBoothQuery&currentpage=1";
		},
		error:function(data){
			alert("提交失败！"); 
		}
	});
}
function Delete(id2){
	var account = $("#account").val();
	if(!YesOrNo2()){
		return;
	}
	$.ajax({
		url:"/Moko_Management/BoothsServlet",
		data:{
			method:"AdvertingBoothDelete",
			id:id2
		},
		type:"POST",
		dataType:"json",
		success:function(date){
			if(date){
				window.location = "/Moko_Management/subpage/BoothManagement/addAdvertisingBooth.jsp?account="+account;
				}else{
					alert("请求失败！");
				}
//			window.location.href="BoothsServlet?method=AdvertingBoothQuery&account=${param.account}";
	},
		error:function(data){
			alert("提交失败！");
		}
	});
}
function DeleteBooth(id2){
	var  account=  $("#account").val();
	if(!YesOrNo4()){
		return;
	}
	$.ajax({
		url:"/Moko_Management/BoothsServlet",
		data:{
			method:"CommodityBoothDelete",
			id:id2
		},
		type:"POST",
		dataType:"json",
		success:function(date){
			if(date){
				window.location = "/Moko_Management/subpage/BoothManagement/addCommodityBooth.jsp?account="+account;
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
	var msg = "确认该广告审核通过？";
	if (confirm(msg)==true){
	return true;
	}else{
	return false;
	}
}

function YesOrNo2() {
	var msg = "确认撤除该广告，并替换新广告申请？";
	if (confirm(msg)==true){
	return true;
	}else{
	return false;
	}
}
function YesOrNo4() {
	var msg = "确认撤除该商品，并替换新的商品展位申请？";
	if (confirm(msg)==true){
	return true;
	}else{
	return false;
	}
}

function YesOrNo() {
	var msg = "确认撤除该广告？";
	if (confirm(msg)==true){
	return true;
	}else{
	return false;
	}
}


function AddAdvertisingVerify(){
	var  account2=  $("#account").val();

 $.ajax({
     url:"/Moko_Management/BoothsServlet",
     type:"post",
     data:{
    	 method:"WalletQuery",
    	 account:account2,
    	 type:1
     },
     dataType:'json',    
     success:function(data){
 		if(data){
 			window.location = "GoodsServlet?method=GoodsNameQuery&searchType=1&account="+account2;
// 			window.location = "/Moko_Management/subpage/BoothManagement/addAdvertisingBooth.jsp?account="+account2;
 		}else{
 			alert("您的钱包余额不足2000,请充值后再提交广告申请！");
 		}
 		
     },
     error:function(e){
   	  alert("提交失败");
     }
 });        
}
function AddAdvertisingBooth(){
	var  account=  $("#account").val();
	 var form = new FormData(document.getElementById("AddAdvertisingBoothForm"));
 $.ajax({
     url:"/Moko_Management/BoothsServlet?method=AdvertingBoothApply",
     type:"post",
     data:form,
     processData:false,
     contentType:false,
     success:function(data){

 		if(data){
 			alert("申请成功！(若竞拍成功将从您的钱包中扣除2000￥)");
 		}else{
 			alert("申请失败！");
 		}
 		window.location.href="/Moko_Management/BoothsServlet?method=AdvertingBoothQuery&account="+account;
     },
     error:function(e){
   	  alert("提交失败");
     }
 });        
 get();//此处为上传文件的进度条

}
function AddCommodifyVerify(){
	var  account2=  $("#account").val();

 $.ajax({
     url:"/Moko_Management/BoothsServlet",
     type:"post",
     data:{
    	 method:"WalletQuery",
    	 account:account2,
    	 type:2
     },
     dataType:'json',    
     success:function(data){
  
 		if(data){
 			window.location = "GoodsServlet?method=GoodsNameQuery&searchType=2&account="+account2;
 		//	/Moko_Management/subpage/BoothManagement/addCommodityBooth.jsp?account="+account2;
 		}else{
 			alert("您的钱包余额不足1000,请充值后再提交商品展位申请！");
 		}
 		
     },
     error:function(e){
   	  alert("提交失败");
     }
 });        
}
function AddCommodityBooth(){
	var  account=  $("#account").val();
	 var form = new FormData(document.getElementById("AddCommodityBoothForm"));
$.ajax({
    url:"/Moko_Management/BoothsServlet?method=CommodityBoothApply",
    type:"post",
    data:form,
    processData:false,
    contentType:false,
    success:function(data){

		if(data){
			alert("申请成功！(若竞拍成功将从您的钱包中扣除1000￥)");
		}else{
			alert("申请失败！");
		}
		window.location.href="/Moko_Management/BoothsServlet?method=CommodityBoothQuery&account="+account;
    },
    error:function(e){
  	  alert("提交失败");
    }
});        
get();//此处为上传文件的进度条

}