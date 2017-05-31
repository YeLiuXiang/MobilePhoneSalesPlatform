function orderQueryById() {
	var id = $("#orderid").val();

	window.location = "OrderServlet?method=OrderQueryById&searchType=1&id="+ id;
}