
function selectAll()
{
	var all = document.getElementById("selectAll");
	var select = document.getElementsByName("select");
	var price = document.getElementsByName("price");
	var account = document.getElementById("account");
	var money =0.00;
	if (all.checked){
		for(var i = 0; i <select.length; i++){
			if(select[i].disabled == false){
				select[i].checked = true;	
				money += parseFloat(price[i].innerText);
			}
			account.disabled=false;
		}
	}else{
		
		for(var i = 0; i <select.length; i++)
			select[i].checked = false;
		account.disabled=true;
	}
	$("#totel").html(money);
	
}

function selectGoods(){
	var select = document.getElementsByName("select");
	var price = document.getElementsByName("price");
	var account = document.getElementById("account");
	var money = 0;
	var count=0;
	for(var i = 0; i < select.length; i++)
		if(select[i].checked == true){
			money += parseFloat(price[i].innerText);
			count++;
		}
	if(count==0){
		account.disabled=true;
	}else if(count!=0){
		account.disabled=false;
	}
	$("#totel").html(money);
} 

function numChange(val) {
	
	var numInp=document.getElementsByName("goodsNum");
	var priceArr=document.getElementsByName("price");
	var singlePriceArr=document.getElementsByName("singlePrice");
	var storeArr=document.getElementsByName("store");
	for(var i=0;i<numInp.length;i++){
		if(numInp[i]==val){
			var price=parseInt(priceArr[i].innerText);
			var singlePrice=parseInt(singlePriceArr[i].innerText);
			var store=parseInt(storeArr[i].innerText);
			var oldNum=price/singlePrice;
			var num=numInp[i].value;
			if(!num.match(/^[1-9]+[0-9]*/)){
				numInp[i].value=oldNum;
				break;
			}else if(num>store){
				numInp[i].value=oldNum;
				break;
			}else if(num<1){
				numInp[i].value=oldNum;
			}else {
				var flag=modify(i);
				if(flag=="true")priceArr[i].innerText=parseInt(num*singlePrice);
				else{numInp[i].value=oldNum;}
				break;
			}
		}
	}
	
	
	
}
//按钮控制商品数量
function updataCol(val,type){
	var buttArr = document.getElementsByName(val.name);
	var numInp=document.getElementsByName("goodsNum");
	var priceArr=document.getElementsByName("price");
	var singlePriceArr=document.getElementsByName("singlePrice");
	var storeArr=document.getElementsByName("store");
	for(var i=0;i<buttArr.length;i++){
		if(buttArr[i]==val){
			var price=parseInt(priceArr[i].innerText);
			var singlePrice=parseInt(singlePriceArr[i].innerText);
			var store=parseInt(storeArr[i].innerText);
			var store=parseInt(storeArr[i].innerText);
			var oldNum=price/singlePrice;
			var num=parseInt(numInp[i].value);
			if(num>=store && type=="add"){
				break;
			}else if(num<=1 && type=="minus"){
				break;
			}else if(type=="add"){
				numInp[i].value=++num;
				var flag=modify(i);
				if(flag=="true"){
					priceArr[i].innerText=parseInt(num*singlePrice);
				}else{
					numInp[i].value=--num;
				}
				break;
			}else if(type=="minus"){
				numInp[i].value=--num;
				var flag=modify(i);
				if(flag=="true"){
					priceArr[i].innerText=parseInt(num*singlePrice);
				}else{
					numInp[i].value=++num;
				}
				break;
			}
			
		}
	}

}


//点击立即-按钮,减小购买商品数量
function minus(val){
	updataCol(val,"minus");
}
//点击立即+按钮,减小购买商品数量

function add(val){
	updataCol(val,"add");
}



// 删除商品 

function remove(val){
	
	var deleteButArr=document.getElementsByName(val.name);
	var modelIdArr=document.getElementsByName("modelId");
	var modelId;
	for(var i=0;i<deleteButArr.length;i++){
		if(deleteButArr[i]==val){
			modelId= modelIdArr[i].innerText;
			break;
		}
	}
	
	var localObj = window.location;
	var contextPath = localObj.pathname.split("/")[1];
	var basePath = localObj.protocol+"//"+localObj.host+"/"+contextPath;
	var server_context=basePath;
//	window.location=server_context+"/avc?goodsJson="+JSON.stringify(json);
	$.ajax({
	url: server_context+"/ShopCar",
	type: "POST",
	dataType: "json",
	data: { "modelId": modelId,"method":"delete"},
	success: function (data) {
		if(data.flag=="true"){
			var thisnode = val.parentNode.parentNode;
			thisnode.parentNode.removeChild(thisnode);
		}else{
			alert(data.msg);
		}
	},
	error: function (XMLHttpRequest, textStatus, errorThrown) {
		alert(XMLHttpRequest.status + " " + XMLHttpRequest.readyState + " " + textStatus);
	},
	});
	
	
	
	
}


function modify(val){
	
//获得商品id
	var modelIdArr=document.getElementsByName("modelId");
//获取修改后数量
	
	var numInp=document.getElementsByName("goodsNum");
	var modelId = modelIdArr[val].innerText;
	var goodsNum = numInp[val].value;
	var localObj = window.location;
	var contextPath = localObj.pathname.split("/")[1];
	var basePath = localObj.protocol+"//"+localObj.host+"/"+contextPath;
	var server_context=basePath;
	$.ajax({
		url: server_context+"/ShopCar",
		type: "POST",
		dataType: "json",
		data: { "modelId": modelId,"goodsNum": goodsNum,"method" : "modify"},
		success: function (data) {
			flag=data.flag;
			if(data.flag=="false"){
				alert(data.msg);
			}
		},
		error: function (XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status + " " + XMLHttpRequest.readyState + " " + textStatus);
			flag="false";
		},
	});
	return flag;

}


//结算
function account(){
	var select = document.getElementsByName("select");
	var modelIdArr=document.getElementsByName("modelId");
	var json = [];
	for(var i = 0;i < select.length;i++){
		if(select[i].checked == true){
			var arr  =
			{
					modelId : modelIdArr[i].innerText
			};
			json.push(arr);
		}
	}

	if(json.length == 0){
		alert("请选择至少一件商品！");
		return;
	}
	var localObj = window.location;
	var contextPath = localObj.pathname.split("/")[1];
	var basePath = localObj.protocol+"//"+localObj.host+"/"+contextPath;
	var server_context=basePath;
//	window.location=server_context+"/avc?goodsJson="+JSON.stringify(json);
	$.ajax({
	url: server_context+"/Order",
	type: "POST",
	dataType: "json",
	data: { "modelIdArr": JSON.stringify(json),"method":"ACKFromShopCar"},
	success: function (data) {
		if(data.flag=="true"){
			window.location=server_context+"/Order?method=ACK";
		}else{
			alert(data.msg);
		}
		
	},
	error: function (XMLHttpRequest, textStatus, errorThrown) {
		alert(XMLHttpRequest.status + " " + XMLHttpRequest.readyState + " " + textStatus);
	},
	});


}
