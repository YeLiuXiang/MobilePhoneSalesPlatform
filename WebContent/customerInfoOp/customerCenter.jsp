<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="net.sf.json.JSONObject"%>
<!DOCTYPE html>
<html>
<head>

<title>个人中心</title>
<link rel="icon" href="${pageContext.request.contextPath}/style/image/favicon.ico" />
<link rel="shortcut icon" href="${pageContext.request.contextPath}/style/image/favicon.ico" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/css/top_foot.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/css/customerCenter.css">
</head>

<body>
<%JSONObject result =(JSONObject ) request.getAttribute("result"); %>
	<!--顶部导航-->
	<nav class="navbar navbar-default" role="nevigation">
		<div class="navbar-header">
			<img src="${pageContext.request.contextPath}/style/image/logo.png" width="150px" height="45px" />
		</div>
		<ul class="nav nav-tabs">
			<li><a href="${pageContext.request.contextPath}/Home"><span class="glyphicon glyphicon-home"></span>首页</a></li>
			<li><a href="customerCenter"><span
					class="glyphicon glyphicon-user"></span>个人中心</a></li>
			<li><a href="cart"><span
					class="glyphicon glyphicon-shopping-cart"></span>购物车</a></li>
			<ul class="nav nav-tabs navbar-right">
				<li><a href="logout">注销</a></li>
			</ul>
		</ul>
	</nav>
	
	<!--顶部导航结束-->
	<div id="information">

		<!--竖直导航栏-->
		<div class="col-sm-2">
		<h3>用户信息</h3>
			<div class="list-group">
				<a class="list-group-item"  href="${pageContext.request.contextPath}/CustomerInfo" target="show">个人信息</a>
				<a class="list-group-item"  href="${pageContext.request.contextPath}/customerInfoOp/modifyPassword.jsp" target="show">修改密码</a>
				<a class="list-group-item"  href="${pageContext.request.contextPath}/customerInfoOp/modifyPhone.jsp" target="show">修改保密手机</a>
				<%if(result==null||(boolean)result.get("isStore")==false){
					%>
					<a class="list-group-item"  href="" >开通店铺</a>
				<%}else{%>
					<a class="list-group-item"  href="" >店铺管理</a>
				<%}%>
			</div>
			<h3>订单信息</h3>
			<div class="list-group">
			<a class="list-group-item" id="waitToPay" href="${pageContext.request.contextPath}/Order?method=showAll"
					target="show">待付款</a>
				<!-- <a class="list-group-item" id="waitToPay" href="orderWaitToPay"
					target="show">待付款</a>  -->
					<a class="list-group-item" id="waitSend"
					href="orderWaitSend" target="show">待发货</a> 
					<a class="list-group-item"
					href="orderWaitReceive" target="show">待收货</a> 
					<a
					class="list-group-item" href="orderReceived" target="show">已收货</a>
					<a class="list-group-item" href="orderCancel" target="show">已取消</a>
					<a class="list-group-item" href="orderService" target="show">售后</a>

			</div>

		</div>
		<!--竖直导航栏结束-->


		<!--页面右边信息框架--> 
        <div class="col-sm-10">          
                <iframe src="${pageContext.request.contextPath}/CustomerInfo" id="rightIframe" width="100%" height="100%" boder="1px" name="show" frameborder="0"></iframe>
        </div> 
        <!--页面右边信息-->

	</div>
	<script src="${pageContext.request.contextPath}/style/js/jquery-3.1.1.js"></script>

</body>
</html>