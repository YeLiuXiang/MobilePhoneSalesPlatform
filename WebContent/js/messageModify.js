function Modify(){
	$.ajax({
		url:"StoreServlet?method=StoreMessageModify",
		data:$('#modifyForm').serialize(),
		type:"POST",
		dataType:"json",
		success:function(data){
			if(data){
				alert("修改成功");
			}
			else{
				alert("修改失败");
			}
			window.location = "";

		},    
		error : function() {    
			alert("提交错误");    
		}    
	});
}