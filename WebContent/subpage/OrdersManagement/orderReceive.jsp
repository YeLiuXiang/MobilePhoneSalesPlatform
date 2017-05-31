<%@ page language = "java" pageEncoding="UTF-8"  %>
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/core"  prefix ="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"   prefix="fmt" %> 

<!DOCTYPE html>
<html>
<c:set var="basePath" scope="request">${pageContext.request.contextPath}</c:set>

	<head>
		<meta charset="UTF-8">
		<title>订单详情</title>
	<link rel="stylesheet" type="text/css" href="${basePath}/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="${basePath}/css/common.css"/>
		<link rel="stylesheet" type="text/css" href="${basePath}/css/order.css"/>
		<script src="${basePath}/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="${basePath}/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="${basePath}/js/shelf.js" type="text/javascript" charset="utf-8"></script>
		<script src="${basePath}/js/orderreceive.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<body>
		<!--大容器begin-->
		<div id="container">
			<!--位置提醒-->
			<div class="posiRemind">您当前的位置：<span>订单管理</span> > <strong>订单详情</strong></div>
			
			<!--顶部搜索框组begin-->
			<div class="row">
				<!--订单类型-->
				<div class="btn-group">
				  	<button onclick="window.location='${basePath}/OrderServlet?method=OrderQuery&searchType=0&id=0&currentpage=1&account=${sessionScope.account }'" type="button" class="btn btn-default">未发货</button>
				  	<button onclick="window.location='${basePath}/OrderServlet?method=OrderQuery&searchType=1&id=0&currentpage=1&account=${sessionScope.account }'" type="button" class="btn btn-default">已发货</button>
				 	<button onclick="window.location='${basePath}/OrderServlet?method=OrderQuery&searchType=2&id=0&currentpage=1&account=${sessionScope.account }'" type="button" class="btn btn-info">已签收</button>
				</div>
				
				<!--搜索框-->
				<div class="col-lg-6">
				    <div class="input-group">
				       	<input  name = "orderid" id="orderid"  type="text" class="form-control" placeholder="请输入订单编号">
				      	<span class="input-group-btn">
				        	<button onclick="orderQueryById()" class="btn btn-default" type="button">查询</button>
				      	</span>
				    </div>	
				</div>	
			</div>	
			<!--顶部搜索框组end-->
				<c:if test= "${empty requestScope.Object.list}"  >
			<center><h4>您查找的订单不存在！</h4></center>
			</c:if>
			<c:if test= "${!empty requestScope.Object.list}"  >
			<!--内容表格begin-->
			<div id="content">
				<table class="table table-hover table-bordered">
					<thead>
						<tr>	<!--标题-->
								<th class="text-center">订单编号</th>
							<th class="text-center">用户账号</th>
							<th class="text-center">联系方式</th>
							<th class="text-center">收货地址</th>
							<th class="text-center">下单时间</th>
							<th class="text-center">操作</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach  var = "order" items="${requestScope.Object.list }" varStatus="st">
							<c:choose>
								<c:when test="${st.count%2==0}">
									<tr id="content" class="warning">
								</c:when>
								<c:otherwise>
									<tr id="content"  class="info">
								</c:otherwise>
							</c:choose>
								<!--第1行-->
								<td class="text-center">${order.orderId }</td>
								<td class="text-center">${order.customerAccount }</td>
								<td class="text-center">${order.receiverPhone }</td>
								<td class="text-center">${order.address }</td>
								<td class="text-center"><fmt:formatDate	value="${order.time }" type="both" /></td>
								<td class="text-center">
								<a	href="${basePath}/OrderServlet?method=OrderDetailQueryById&id=${order.orderId }&currentpage=1"	class="btn btn-warning btn-sm">订单详情</a></td>
							</tr>
						</c:forEach>
						
					</tbody>
				</table>
			</div>
			<!--内容表格end-->
			
			<!--底部分页begin-->
			<footer>
				<ul class="pager">
	    				<li><a href="${basePath}/OrderServlet?method=OrderQuery&searchType=2&account=${sessionScope.account }&currentpage=${ requestScope.Object.previousPage}">上一页</a></li>
	    				<li><a href="${basePath}/OrderServlet?method=OrderQuery&searchType=2&account=${sessionScope.account }&currentpage=${ requestScope.Object.nextPage}">下一页</a></li>
					</ul>
					<form method = "post" action = "${basePath}/OrderServlet?method=OrderQuery&searchType=2&account=${sessionScope.account }"  class="navbar-form navbar-right" role="search" >
						第<strong style="color: darkmagenta;">${requestScope.Object.currentPage}</strong>页
						共<strong style="color: darkmagenta;">${requestScope.Object.totalPages}</strong>页
					  	<div class="form-group">
					    	<input type="text" name = currentpage class="form-control">
					  	</div>
					  	<button type="submit" class="btn btn-success">跳转</button>
					</form>
			</footer>
			<!--底部分页end-->
		</div>
		<!--大容器end-->
		
			</c:if>
	</body>
</html>
