<%@ page language = "java" pageEncoding="UTF-8"  %>
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/core"  prefix ="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"   prefix="fmt" %> 
<!DOCTYPE html>
<html>
<c:set var="basePath" scope="request">${pageContext.request.contextPath}</c:set>

	<head>
		<meta charset="UTF-8">
		<title>订单详情中的订单详情</title>
		<link rel="stylesheet" type="text/css" href="${basePath}/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="${basePath}/css/orderDetail.css"/>
		<script src="${basePath}/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="${basePath}/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<body>
		
		<div id="container">
			
			<div id="content">
				<header>
					<h4><strong>商品详情</strong></h4>
				</header>
				<table class="table table-bordered">
					<thead>
						<tr >	<!--标题-->
						<th class="text-center">序号</th>
						<th class="text-center">型号</th>
						<th class="text-center">名称</th>
						<th class="text-center">价格</th>
						<th class="text-center">数量</th>
					</tr>
					</thead>
					<tbody>
							<c:forEach  var = "goods" items="${requestScope.Object.list }" varStatus="st">
						<c:choose>
								<c:when test="${st.count%2==0}">
									<tr id="content" class="warning">
								</c:when>
								<c:otherwise>
									<tr id="content"  class="info">
								</c:otherwise>
							</c:choose>
						<td class="text-center">${goods.no }</td>
							<td class="text-center">${goods.modelId }</td>
							<td class="text-center">${goods.name }</td>
							<td class="text-center">${goods.price }</td>
							<td class="text-center">${goods.number }</td>
						</tr>
						</c:forEach>
						
					</tbody>
				</table>
				
				<footer>
					<!--<input type="button" name="" id="" value="" />-->
					<ul class="pager">
		    			<li><a href="${basePath}/OrderServlet?method=OrderDetailQueryById&id=${param.id }&currentpage=${ requestScope.Object.previousPage}">上一页</a></li>
		    			<li><a  href="${basePath}/OrderServlet?method=OrderDetailQueryById&id=${param.id }&currentpage=${ requestScope.Object.nextPage}">下一页</a></li>
		    			<li><a>共${requestScope.Object.totalPages}页</a></li>
					</ul>
				</footer>
			</div>
			
		</div>
		
	</body>
</html>
