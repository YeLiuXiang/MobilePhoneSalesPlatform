<%@ page language = "java" pageEncoding="UTF-8"  %>
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/core"  prefix ="c" %>
<!DOCTYPE html>
<html>
<c:set var="basePath" scope="request">${pageContext.request.contextPath}</c:set>
	<head>
		<meta charset="UTF-8">
		<title>未上架</title>
			<link rel="stylesheet" type="text/css" href="${basePath}/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="${basePath}/css/common.css"/>
		<link rel="stylesheet" type="text/css" href="${basePath}/css/shelf.css"/>
		<script src="${basePath}/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="${basePath}/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${basePath}/js/notYetShelf.js"  type="text/javascript"  charset="utf-8">	</script>
	</head>

	<body>
		<div id="container">
			
			<!--位置提醒-->
			<div class="posiRemind">您当前的位置：<span>商品管理</span> > <strong>未上架商品</strong></div>
			
			<!--顶部搜索框begin-->
			<div id="searchDIV" class="input-group">
				<input  name ="goodsid" id="goodsid" type="text" class="form-control" placeholder="请输入商品名称或编号"> 
				<span class="input-group-btn"><input  onclick="goodsQueryByIdOrName()" type="button" name="" class="btn btn-success" value="查询"/></span>
			</div>
			<!--顶部搜索框end-->
			<c:if test= "${empty requestScope.Object.list}"  >
			<center><h4>您查找的商品不存在！</h4></center>
			</c:if>
			<c:if test= "${!empty requestScope.Object.list}"  >
			<!--内容表格begin-->
			<div id="content">
			
			
				<table class="table table-hover table-bordered">
					<thead>
						<tr>	<!--标题-->
							<th>编号</th>
							<th>缩略图</th>
							<th>名称</th>
							<th>状态</th>
							<th>操作</th>
						</tr>
					</thead>
				<tbody>
					<c:forEach var="goods" items="${requestScope.Object.list }" varStatus="st">
					<c:choose>
								<c:when test="${st.count%2==0}">
									<tr id="content" class="warning">
								</c:when>
								<c:otherwise>
									<tr id="content"  class="info">
								</c:otherwise>
							</c:choose>
							<!--第1行-->
							<td class="text-center">${goods.id }</td>
							<td class="text-center"><img
								src="${basePath}/imageFile/${goods.picture}" /></td>
							<td class="text-center">${goods.name}</td>
							<td class="text-center">${goods.status}</td>
							<td class="text-center"><a
									href="${basePath}/GoodsServlet?method=GoodsDetailQuery&id=${goods.id }"
									class="btn btn-info btn-sm">商品详情</a>
									 <button onclick="goodsUpShelf(${goods.id})" class="btn btn-danger btn-sm">上架</button></td>
						</tr>
					</c:forEach>
					
				</tbody>
			</table>
				
			</div>
			<!--内容表格end-->
			
			<!--底部分页begin-->
			<footer>
			<!--<div>-->
			<ul class="pager">
				<li><a
					href="${basePath}/GoodsServlet?method=OffShelfQuery&account=${sessionScope.account }&currentpage=${ requestScope.Object.previousPage}">上一页</a></li>
				<li><a
					href="${basePath}/GoodsServlet?method=OffShelfQuery&account=${sessionScope.account }&currentpage=${ requestScope.Object.nextPage}">下一页</a></li>
			</ul>
					<form method = "post" action = "${basePath}/GoodsServlet?method=OffShelfQuery&account=${sessionScope.account }" class="navbar-form navbar-right" role="search" action="" method="get">
						第<strong style="color: darkmagenta;">${requestScope.Object.currentPage}</strong>页
						共<strong style="color: darkmagenta;">${requestScope.Object.totalPages}</strong>页
					  	<div class="form-group">
					    	<input type="text" name = currentpage class="form-control">
					  	</div>
					  	<button type="submit" class="btn btn-success">跳转</button>
					</form>
				<!--</div>-->
			</footer>
			<!--底部分页end-->
			
		</div>
		</c:if>
	</body>
</html>
