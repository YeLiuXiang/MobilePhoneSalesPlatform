<%@ page language = "java" pageEncoding="UTF-8"  %>
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/core"  prefix ="c" %>
<!DOCTYPE html>
<html>
<c:set var="basePath" scope="request">${pageContext.request.contextPath}</c:set>

	<head>
	
		<title>商品展位管理</title>
	<link rel="stylesheet" type="text/css" href="${basePath}/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="${basePath}/css/common.css"/>
		<link rel="stylesheet" type="text/css" href="${basePath}/css/shelf.css"/>
		<script src="${basePath}/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="${basePath}/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="${basePath}/js/shelf.js" type="text/javascript" charset="utf-8"></script>
		<script src="${basePath}/js/AdvertisingBooth.js" type="text/javascript" charset="utf-8"></script>
		</head>
	<body>
	<input type="hidden" id="account"  value="${param.account}">
		<div id="container">
			<!--位置提醒-->
			<div class="posiRemind">您当前的位置：<span>我要推广</span> > <strong>商品展位竞拍</strong></div>
			<c:if test= "${empty requestScope.Object}"  >
			<center><h4>您尚未申请商品展位！</h4></center>
			</c:if>
			<c:if test= "${!empty requestScope.Object}"  >
			<div id="content">
		<table class="table table-hover table-bordered">
				<thead>
					<tr>
						<!--标题-->
						<th class="text-center">申请编号</th>
 						<th class="text-center">图片</th>
						<th class="text-center">链接商品编号</th>
						<th class="text-center">状态</th>
						<th class="text-center">操作</th>
					</tr>
				</thead>
				<tbody>
				
					<c:forEach var="anno" items="${requestScope.Object }"  varStatus="st">
							<c:choose>
								<c:when test="${st.count%2==0}">
									<tr id="content" class="warning">
								</c:when>
								<c:otherwise>
									<tr id="content"  class="info">
								</c:otherwise>
							</c:choose>
							<!--第1行-->
							<td class="text-center">${anno.comId }</td>
							<td class="text-center"><img src="${basePath}/resource/${anno.imgAddr }" /></td>
							<td class="text-center">${anno.goodsId }</td>
							<td class="text-center">${anno.status }</td>
							<c:choose>
								<c:when test="${anno.status=='申请中'}">
									<td class="text-center"><button onclick="DeleteBooth(${anno.comId})"	class="btn btn-danger btn-sm">替换</button></td>
								</c:when>
								<c:otherwise>
									<td class="text-center"></td>
								</c:otherwise>
							</c:choose>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		</c:if>
		<c:if test= "${empty requestScope.Object}"  >
			<input onclick="AddCommodifyVerify()" type="button" class="btn btn-info btn-block"  value="商品展位申请"/>
		</c:if>
		</div> 

	</body>
</html>
