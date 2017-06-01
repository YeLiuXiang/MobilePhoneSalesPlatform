<%@ page language = "java" pageEncoding="UTF-8"  %>
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/core"  prefix ="c" %>
<!DOCTYPE html>
<html>
<c:set var="basePath" scope="request">${pageContext.request.contextPath}</c:set>

	<head>
		<meta charset="UTF-8">
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
		<div id="container">
			<!--位置提醒-->
			<div class="posiRemind">您当前的位置：<span>公告资讯/关于企业</span> > <strong>公告资讯</strong></div>
			
			<div id="content">
		<table class="table table-hover table-bordered">
				<thead>
					<tr>
						<!--标题-->
						<th class="text-center">申请编号</th>
						<th class="text-center">所属店铺</th>
						<th class="text-center">图片</th>
						<th class="text-center">链接商品编号</th>
						<th class="text-center">状态</th>
						<th class="text-center">操作</th>
					</tr>
				</thead>
				<tbody>
				
					<c:forEach var="anno" items="${requestScope.Object.list }"  varStatus="st">
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
							<td class="text-center">${anno.storeName }</td>
							<td class="text-center"><img src="${basePath}/resource/${anno.imgAddr }" /></td>
							<td class="text-center">${anno.goodsId }</td>
								<td class="text-center">${anno.status }</td>
							<td class="text-center"><button onclick="Agree(${anno.comId})"	class="btn btn-danger btn-sm">同意</button></td>
						</tr>
					</c:forEach>
				
				</tbody>
			</table>
			
		</div>
	
		

			<!--底部分页begin-->
			<footer>
				<ul class="pager">
	    				<li><a href="${basePath}/BoothServlet?method=CommodityBoothApplyQuery&currentpage=${ requestScope.Object.previousPage}">上一页</a></li>
	    				<li><a href="${basePath}/BoothServlet?method=CommodityBoothApplyQuery&currentpage=${ requestScope.Object.nextPage}">下一页</a></li>
					</ul>
					<form method = "post" action = "${basePath}/BoothServlet?method=CommodityBoothQuery"  class="navbar-form navbar-right" role="search" >
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

	</body>
</html>
