function MessageQueryByIdOrName( ) {
	var id = $("#sid").val();
	if(!id) {//非空校验
	alert("输入不能为空！");
	return false;
	}
	window.location = "StoreServlet?method=MessageQueryByIdOrName&id="
			+ id;
	
}