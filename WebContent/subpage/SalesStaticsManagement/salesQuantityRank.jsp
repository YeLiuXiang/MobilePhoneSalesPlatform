<%@ page language = "java" pageEncoding="UTF-8"  %>
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/core"  prefix ="c" %>
<!DOCTYPE html>
<html>
<c:set var="basePath" scope="request">${pageContext.request.contextPath}</c:set>

	<head>
		<meta charset="UTF-8">
		<title>销售量排行</title>
	<link rel="stylesheet" type="text/css" href="${basePath}/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="${basePath}/css/common2.css"/>
		<link rel="stylesheet" type="text/css" href="${basePath}/css/shelf.css"/>
		<script src="${basePath}/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="${basePath}/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="${basePath}/js/shelf.js" type="text/javascript" charset="utf-8"></script>
		<script src="${basePath}/js/AdvertisingBooth.js" type="text/javascript" charset="utf-8"></script>
		</head>
	
	<body>
		<div id="container">
			<!--位置提醒-->
			<div class="posiRemind">您当前的位置：<span>销售统计</span> > <strong>销售量排行</strong></div>
			
	 	
			<div id="content">
			<table class="table table-hover  ">
				<thead>
					<tr>
						<!--标题-->
						<th class="text-center">排名</th>
						<th class="text-center">商品型号</th>
						<th class="text-center">商品名称</th>
						<th class="text-center">商品销量</th>
					</tr>
				</thead>
				<tbody>
				
					<c:forEach var="goods" items="${requestScope.Object}" varStatus="st">
						<c:choose>
								<c:when test="${st.count%2==0}">
									<tr id="content" class="warning">
								</c:when>
								<c:otherwise>
									<tr id="content"  class="info">
								</c:otherwise>
							</c:choose>
							<td class="text-center">${goods.no }</td>
							<td class="text-center">${goods.id }</td>
							<td class="text-center">${goods.name }</td>
							<td class="text-center">${goods.volume }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
		</div>
		</div> 

	</body>
</html>
