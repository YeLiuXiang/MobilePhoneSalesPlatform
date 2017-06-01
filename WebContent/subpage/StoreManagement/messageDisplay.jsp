<%@ page language = "java" pageEncoding="UTF-8"  %>
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/core"  prefix ="c" %>
<!DOCTYPE html>
<html>
<c:set var="basePath" scope="request">${pageContext.request.contextPath}</c:set>
	<head>
		<meta charset="UTF-8">
		<title>店铺信息浏览</title>
		<link rel="stylesheet" type="text/css" href="${basePath}/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="${basePath}/css/storemessage.css"/>
		<script src="${basePath}/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="${basePath}/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="${basePath}/js/addGoods.js" type="text/javascript" charset="utf-8"></script>
		<script src="${basePath}/js/jquery-form.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<body>
		<div id="container">
			<form    class="navbar-form navbar-left" role="search">
				<div class="form-group">
			  		<span>店铺编号：</span>
			  		<p class="form-control">${requestScope.Object.id }</p>
			  	</div>
			  	<div class="form-group">
			  		<span>店铺名称：</span>
			  		<p class="form-control">${requestScope.Object.name }</p>
			  	</div>
			  	<div class="form-group">
			  	<span>店铺主页图片：</span>
			  	</div>
			  	<div class="image_container ">
			  		<img  id ="preview" src="${basePath}/resource/${requestScope.Object.imageAddress }">
			  	</div>
			  	<div class="form-group">
			  		<span>注册地址：</span>
			  		<p class="form-control">${requestScope.Object.address }</p>
			  	</div>
			  		<div class="form-group">
			  		<span>客服联系方式：</span>
			  		<p class="form-control">${requestScope.Object.customerService }</p>
			  	</div>
			 	<div class="form-group">
			  		<span>店铺状态：</span>
			  		<p class="form-control">${requestScope.Object.status }</p>
			  	</div>
			</form>
			
		</div>
		
	</body>
</html>
