<%@ page language = "java" pageEncoding="UTF-8"  %>
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/core"  prefix ="c" %>
<!DOCTYPE html>
<html>
<c:set var="basePath" scope="request">${pageContext.request.contextPath}</c:set>
	<head>
		<title>商品详情</title>
		<link rel="stylesheet" type="text/css" href="${basePath}/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="${basePath}/css/prdctDetails.css"/>
		<script src="${basePath}/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="${basePath}/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="${basePath}/js/goodsAdd.js" type="text/javascript" charset="utf-8"></script>
		<script src="${basePath}/js/jquery-form.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<body>
		
		<div id="container">
			
			<form  id="modifyGoodsForm" enctype="multipart/form-data"  class="navbar-form navbar-left" role="search">
				<div class="form-group">
			  		<span>商品编号：</span>
			  		<p class="form-control">${requestScope.Object.id }</p>
			  	</div>
			  	<div class="form-group">
			  		<span>商品名称：</span>
			    		<p class="form-control">${requestScope.Object.name }</p>
				</div>
			  	
			  	<div class="form-group">
			  		<span>标题简介：</span>
			    	<input id = "title" name = "title"  type="text"  value ="${requestScope.Object.title }" class="form-control">
			    	<label class="errorClass" id="titleError">${errors.titleError}</label>
			  	</div >
			  	<div class="form-group">
			  		<input type="button"  onclick="GoodsModify1(${requestScope.Object.id })" class="btn btn-default" value="修&nbsp;&nbsp;&nbsp;改">
			  	</div>
			  	<input id="storeId" name="storeId" type="hidden" value="${requestScope.storeId}">
			  	<input id="goodsId" name="goodsId" type="hidden" value="${requestScope.goodsId+1}">
			<!--内容表格begin-->
			  	<table class="table table-bordered">
			  		<thead>
			  			<tr class="">	<!--标题-->
			  				<th class="text-center">型号</th>
			  				<th class="text-center">颜色</th>
			  				<th class="text-center">内存</th>
			  				<th class="text-center">库存</th>
			  				<th class="text-center">价格</th>
			  				<th class="text-center">操作</th>
			  			</tr>
			  		</thead>
			  		<tbody>
			  		<c:forEach  var = "goods" items="${requestScope.Object.goodsList }"  varStatus="st">
							<c:choose>
								<c:when test="${st.count%2==0}">
									<tr id="content" class="warning">
								</c:when>
								<c:otherwise>
									<tr id="content"  class="info">
								</c:otherwise>
							</c:choose>
			  				<!--第1行-->
			  				<td class="text-center"><p id=""   class="data mID">${goods.modelId }</p></td>
			  				<td class="text-center"><p id="" class="data color">${goods.color}</p></td>
			  				<td class="text-center"><p id="" class="data capacity">${goods.capacity}</p></td>
			  				<td class="text-center"><input class="form-control"  name = "number" type="text" value="${goods.number }"/></td>
			  				<td class="text-center"><input class="form-control"  name = "price" type="text"  value="${goods.price } "/></td>
			  				<td class="text-center"> 	<input type="button"  onclick="GoodsModify(${goods.modelId},${st.index})" class="btn btn-default" value="修&nbsp;&nbsp;&nbsp;改">
			  				</td>
			  			</tr>
			  			</c:forEach>
			  		</tbody>
			  	</table>
			  	
			  
			</form>
			
		</div>
		
	</body>
</html>
