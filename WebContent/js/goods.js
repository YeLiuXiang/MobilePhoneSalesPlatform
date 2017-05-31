function goodsOffShelf(id2) {
	
	if(!YesOrNo()){
		return;
	}
	$.ajax( {    

		url:'/Smartisan/ManagerServlet',// 跳转到     
		data:{    
			id:id2,
			flag:"商品下架",
		},    
		type:'post',     
		dataType:'json',    
		success:function(data) {    
			if(data){
				alert("下架成功");
			}
			else{
				alert("下架失败");
			}
			window.location = "/Smartisan/ManagerServlet?flag=已上架商品查询&currentpage=1";

		},    
		error : function() {    
			alert("提交错误");    
		}    
	});  
}
function goodsUpShelf(id2) {
	if(!YesOrNo2()){
		return;
	}
	$.ajax( {    

		url:'/Smartisan/ManagerServlet',// 跳转到     
		data:{    
			id:id2,
			flag:"商品上架",
		},    
		type:'post',     
		dataType:'json',    
		success:function(data) {    
			if(data){
				alert("上架成功");
			}
			else{
				alert("上架失败");
			}
			window.location = "/Smartisan/ManagerServlet?flag=未上架商品查询&currentpage=1";

		},    
		error : function() {    
			alert("提交错误！");    
		}    
	});  
}

function YesOrNo() {
	var msg = "确认下架该商品？";
	if (confirm(msg)==true){
	return true;
	}else{
	return false;
	}
	}
function YesOrNo2() {
	var msg = "确认上架该商品？";
	if (confirm(msg)==true){
	return true;
	}else{
	return false;
	}
	}
function goodsUpShelfQuery() {
	//window.location = '/Smartisan/ManagerServlet?flag=商品下架&id=${goods.no }';
	//	var id2 = $("#goodsid").val();
//	window.location = "/Smartisan/ManagerServlet?flag=ID商品查询&searchType=1&id="
//	+ id;
	var id2 = $("#goodsid").val();
	alert(id2);
	//或者名称
	$.ajax( {    

		url:'/Smartisan/ManagerServlet',// 跳转到     
		data:{    
			id:id2,
			flag:"ID商品查询",
			searchType:1,
		},    
		type:'post',     
		dataType:'json',    
		success:function(data) {  
			
			var str =data.result+"";
			if($.trim(str)=="null"){
				alert("查找出错了！");
			}
			else{
				var str =data.result.list+"";
				alert("111"+JSON.stringify(data));
			
				if($.trim(str)=="null"){
					alert("您查找的商品不存在！");
				}
				alert("查找成功！");
				$("#table tr:not(:first)").empty();
//				alert("0:"+data.result.list[0].name);
//				alert("1:"+data.result.list[0].no);
//				alert("2:"+data.result.list[0].picture);
//				alert("3:"+data.result.list[0].status);
//				alert(data.result.list);
				var obj = eval(data.result.list);
				alert(obj);
				var ht = '';
				for(var i = 0;i<data.result.list.length;i++){
					var picc='/Smartisan/imageFile/'+obj[i].pic;
					ht = ht+'<tr>';
					ht = ht+'<td class="text-center">'+obj[i].no+'</td>'; 
					 ht = ht + '<td class="text-center"><img src='+ picc+ '/></td>'; 
				    ht = ht + '<td class="text-center">' +obj[i].name + '</td>'; 
				    ht = ht + '<td class="text-center">' +obj[i].status + '</td>'; 
				    ht = ht + '<a href="/Smartisan/ManagerServlet?flag=商品详情查询&id='+obj[i].no+'  class="btn btn-warning btn-sm">商品详情</a>';
				    ht = ht +  '<button onclick="goodsOffShelf('+obj[i].no+')" class="btn btn-danger btn-sm">下架</button>'
				    ht = ht+'</tr>';
				}
				
				$("#table tr:last").after(ht);
//				var row = $("#content").clone();
//				row.find("#id").text(obj[0].no);
//                row.find("#pic").text(obj[0].pic);
//                row.find("#name").text(obj[0].name);
//                row.find("#status").text(obj[0].status);
//                row.find("#oper").text("haha");
//                
                
//				row.find("#id").text(11);
//                row.find("#pic").text(122);
//                row.find("#name").text(22);
//                row.find("#status").text(22);
//                row.find("#oper").text("22");

				//window.location = "beenShelves.jsp?Object ="+data.result;

			}
		},    
		error : function() {    
			alert("查询错误！");    
		}    
	});  
}
function goodsOffShelfQuery() {
	//window.location = '/Smartisan/ManagerServlet?flag=商品下架&id=${goods.no }';
	//	var id2 = $("#goodsid").val();
//	window.location = "/Smartisan/ManagerServlet?flag=ID商品查询&searchType=1&id="
//	+ id;
	var id2 = $("#goodsid").val();
	$.ajax( {    

		url:'/Smartisan/ManagerServlet',// 跳转到     
		data:{    
			id:id2,
			flag:"ID商品查询",
			searchType:0,
		},    
		type:'post',     
		dataType:'json',    
		success:function(data) {    
			if(data){
				alert("上架成功");
			}
			else{
				alert("上架 失败");
			}
			window.location = "/Smartisan/ManagerServlet?flag=未上架商品查询&currentpage=1";

		},    
		error : function() {    
			alert("提交错误！");    
		}    
	});  
}