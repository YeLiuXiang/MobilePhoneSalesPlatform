<%@ page language = "java" pageEncoding="UTF-8"  %>
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/core"  prefix ="c" %>
<!DOCTYPE html>
<html>
<c:set var="basePath" scope="request">${pageContext.request.contextPath}</c:set>

	<head>
		<meta charset="UTF-8">
		<title>店铺信息查询</title>
		<link rel="stylesheet" type="text/css" href="${basePath}/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="${basePath}/css/common.css"/>
		<link rel="stylesheet" type="text/css" href="${basePath}/css/storeQuery.css"/>
		<script src="${basePath}/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="${basePath}/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="${basePath}/js/messageQuery.js"  type="text/javascript"  charset="utf-8">	</script>
	</head>
	
<body>
		<div id="container" style="width: 1000px;">
			
			<!--位置提醒-->
			<div class="posiRemind">您当前的位置：<span>店铺管理</span> > <strong>店铺信息查询</strong></div>
			
			<!--顶部搜索框begin-->
			
			<div id="searchDIV" class="input-group">
				<input  name ="sid" id="sid" type="text" class="form-control" placeholder="请输入店铺名称或编号">
				<span class="input-group-btn"> 
				<input   onclick="MessageQueryByIdOrName()" type="button" name="" class="btn btn-success" value="查询"/>
				</span>
			</div>
			<!--顶部搜索框end-->
		<c:if test="${empty param.flag }">
			<c:if test="${empty requestScope.Object.list}">
				<center>
					<h4>您查找的店铺不存在！</h4>
				</center>
			</c:if>
		</c:if>
		<c:if test= "${!empty requestScope.Object.list}"  >
			<!--内容表格begin-->
			<div id="content">
				<table  id = "table" class="table table-hover table-bordered">
					<thead>
						<tr>	<!--标题-->
							<th>店铺编号</th>
							<th>店铺名称</th>
							<th>店主账号</th>
							<th>状态</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach  var = "store" items="${requestScope.Object.list }"  varStatus="st">
							<c:choose>
								<c:when test="${st.count%2==0}">
									<tr id="content" class="warning">
								</c:when>
								<c:otherwise>
									<tr id="content"  class="info">
								</c:otherwise>
							</c:choose>

				<!-- 			<tr id="content" class="warning"> -->
								<!--第1行-->
								<td id="id" class="text-center">${store.id }</td>
								<td id="name" class="text-center">${store.name }</td>
								<td id="account" class="text-center">${store.account}</td>
								<td id="status" class="text-center">${store.status}</td>
								<c:choose>
								<c:when test="${store.status=='运营中'}">
									<td class="text-center"><button onclick="Freeze(${store.id })"	class="btn btn-danger btn-sm">冻结</button></td>
								</c:when>
								<c:otherwise>
									<td class="text-center"><button onclick="UnFreeze(${store.id })"	class="btn btn-danger btn-sm">解冻</button></td>
								</c:otherwise>
							</c:choose>
							</tr>
						</c:forEach>
					
					</tbody>
				</table>
			</div>
			<!--内容表格end-->
			
		
			
		</div>
		</c:if>
	</body>
</html>
