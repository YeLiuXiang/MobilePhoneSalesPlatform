$(document).ready(function () {
	var localObj = window.location;
	var contextPath = localObj.pathname.split("/")[1];
	var basePath = localObj.protocol+"//"+localObj.host+"/"+contextPath;
	var server_context=basePath;
	var goodsId=$("#goodsId").html();//商品编号
	$.ajax({
		url:server_context+"/GoodsDetails",
		type: "POST",
		dataType: "json",
		data: {"method":"getCapacity","goodsId":goodsId},
		success:function(data){
			if(data.flag =="true"){
				var msg="";
				
			
				for(var i=0; i<data.capacityList.length; i++)  
				  {  
					msg=msg+"<label class='btn btn-default' onclick='getColor(this)'><input type='radio'   name='capacity' id='"+data.capacityList[i]+"'>" +data.capacityList[i]+"</label>";
				     
				  } 
				$("#capacity").html(msg);
				$("#"+data.capacityList[0]).click();
            }else{
            	alert(msg);
            }
			
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status+" "+XMLHttpRequest.readyState+" "+textStatus );
        },
	});
});

var localObj = window.location;
var contextPath = localObj.pathname.split("/")[1];
var basePath = localObj.protocol+"//"+localObj.host+"/"+contextPath;
var server_context=basePath;

function getColor(val) {
	
	
	var capacity=val.innerText;
	var goodsId=$("#goodsId").html();//商品编号
	
	$.ajax({
		url:server_context+"/GoodsDetails",
		type: "POST",
		dataType: "json",
		data: {"method":"getColor","goodsId":goodsId,"capacity":capacity},
		success:function(data){
			if(data.flag =="true"){
				var msg="";
				for(var i=0; i<data.colorList.length; i++)  
				  {  
					
					msg=msg+"<label class='btn btn-default' onclick='getGoods(this)'><input type='radio' name='color' id='"+data.colorList[i]+"'>" +data.colorList[i]+"</label>";
				     
				       
				  } 
				
				$("#color").html(msg);
				$("#"+data.colorList[0]).click();
            }else{
            	alert(msg);
            }
			
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status+" "+XMLHttpRequest.readyState+" "+textStatus );
        },
	});
	
}
function getGoods(val){
	var color=val.innerText;
	var goodsId=$("#goodsId").html();//商品编号
	var capacityOR = document.getElementsByName('capacity');
	var capacity="";
    for (var i = 0; i < capacityOR.length; i++) {
        if (capacityOR[i].checked == true) {
        	capacity=capacityOR[i].id;
        }
    }
	$.ajax({
		url:server_context+"/GoodsDetails",
		type: "POST",
		dataType: "json",
		data: {"method":"getGoodsExtend","goodsId":goodsId,"capacity":capacity,"color":color},
		success:function(data){
			if(data.flag =="true"){
				$("#price").html(data.goodsExtend.price);
				$("#store").html(data.goodsExtend.number);
				$("#volume").html(data.goodsExtend.volume);
				document.getElementById("showPic").src=server_context+"/resource/"+data.goodsExtend.picAddress;
				$("#modelId").html(data.goodsExtend.modelId);
				
            }else{
            	alert(msg);
            }
			
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status+" "+XMLHttpRequest.readyState+" "+textStatus );
        },
	});
	
}



	
//点击加入购物车按钮,获取当前商品的ID和数量
function AddCart() {
	var modelId = $("#modelId").html();//商品编号
	var number = $("#buyNum").val();//商品数量
	var store=parseInt($("#store").html());
	if(number>store){
		alert("库存不足,请修改购买数量");
		return false;
	}
	$.ajax({
		url:server_context+"/ShopCar",
		type: "POST",
		dataType: "json",
		data: {"modelId":modelId,"number":number,"method":"add"},
		success:function(data){
			if(data.flag=="true"){
                alert(data.msg);
            }else{
            	alert(data.msg);
            }
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status+" "+XMLHttpRequest.readyState+" "+textStatus );
        },
	});
}


function numChange() {
	var num=document.getElementById('buyNum').value;
	var store=parseInt($("#store").html());
	if(!num.match(/^[1-9]+[0-9]*/))document.getElementById('buyNum').value=1;
	if(num>store)document.getElementById('buyNum').value=store;
}
//点击立即-按钮,减小购买商品数量

function minus(){
	var num=document.getElementById('buyNum').value;
	if(num>1)document.getElementById('buyNum').value=--num;
}
function add(){
	var num=document.getElementById('buyNum').value;
	var store=parseInt($("#store").html());
	if(num<store)document.getElementById('buyNum').value=++num;
	
}
//点击立即购买按钮,获取当前商品的ID*
function BuyNow(){
	var num=document.getElementById('buyNum').value;//商品数量
	var store=parseInt($("#store").html());//库存
	var modelId = $("#modelId").html();//商品编号
	if(num>store){
		alert("库存不足,请修改购买数量");
		return false;
	}
	 var localObj = window.location;
	 var contextPath = localObj.pathname.split("/")[1];
	 var basePath = localObj.protocol+"//"+localObj.host+"/"+contextPath;
	 var server_context=basePath;
	 $.ajax({
	 url: server_context+"/Order",
	 type: "POST",
	 dataType: "json",
	 data: { "modelId": modelId,"number":num,"method":"ACKFromGoods"},
	 success:function(data){
			if(data.flag=="true"){
				 window.location=server_context+"/Order?method=ACK";
         }else{
         	alert(data.msg);
         }
		},
	 error: function (XMLHttpRequest, textStatus, errorThrown) {
	 alert(XMLHttpRequest.status + " " + XMLHttpRequest.readyState + " " +
	 textStatus);
	 },
	 });
}


