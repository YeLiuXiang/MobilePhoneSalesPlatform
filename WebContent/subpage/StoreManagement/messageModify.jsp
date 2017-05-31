<%@ page language = "java" pageEncoding="UTF-8"  %>
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/core"  prefix ="c" %>
<!DOCTYPE html>
<html>
<c:set var="basePath" scope="request">${pageContext.request.contextPath}</c:set>
	<head>
		<meta charset="UTF-8">
		<title>店铺信息修改</title>
		<link rel="stylesheet" type="text/css" href="${basePath}/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="${basePath}/css/storemessage.css"/>
		<script src="${basePath}/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="${basePath}/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="${basePath}/js/messageModify.js" type="text/javascript" charset="utf-8"></script>
		<script src="${basePath}/js/jquery-form.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<body>
		
		<div id="container">
			
			<form  id="addGoodsForm" enctype="multipart/form-data"  class="navbar-form navbar-left" role="search">
				<div class="form-group">
				<span>店铺主页图片预览：</span>
				</div>
				<div class="image_container ">
				<img id ="preview" src="${basePath}/imageFile/${requestScope.Object.imageAddress}" />
					</div>
			  	<div class="form-group">
			  		<span>如需更改，请点击↓：</span>
			    	<input id = " imge" name = "imge" type="file">
			    	<label class="errorClass" id="nameError">${errors.nameError}</label>
			  	</div>
				  		<div class="form-group">
			  		<span>客服联系方式：</span>
			    	<input id = " title" name = "title"  type="text" value="${requestScope.Object.customerService }" class="form-control">
			    	<label class="errorClass" id="titleError">${errors.titleError}</label>
			  	</div>
			  	<div class="btnDiv">
				  	<input type="button"  onclick="Modify()" class="btn btn-default" value="确定修改">
				  	<button type="reset" class="btn btn-default">取&nbsp;&nbsp;&nbsp;消</button>
				</div>
			</form>
			
		</div>
		
	</body>
</html>
