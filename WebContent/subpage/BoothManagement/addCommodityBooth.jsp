<%@ page language = "java" pageEncoding="UTF-8"  %>
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/core"  prefix ="c" %>
<!DOCTYPE html>
<html>
<c:set var="basePath" scope="request">${pageContext.request.contextPath}</c:set>
	<head>
		<meta charset="UTF-8">
		<title>商品展位申请新增</title>
		<link rel="stylesheet" type="text/css" href="${basePath}/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="${basePath}/css/addCommodityBooth.css"/>
		<script src="${basePath}/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="${basePath}/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="${basePath}/js/AdvertisingBooth.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<body>
		<input type="hidden" id="account"  value="${param.account}">
		<div id="container">
			<form  id="AddCommodityBoothForm" enctype="multipart/form-data" class="navbar-form navbar-left" role="search">
			  	<div class="form-group">
			  		<span>链接商品：</span>
			  			<select class="form-control" style="width: 100" name="linkID">
			  		<c:forEach items="${requestScope.Object }" var="st">
			  		<option>${st.id }:${st.name }</option>
			  		</c:forEach>
			  		</select>
			    <!-- 	<input id="linkID" name ="linkID" type="text" class="form-control"> -->
			    	
			  	</div>
			  	<div class="form-group">
			  		<span>图&nbsp;&nbsp;&nbsp;片：</span>
			    	<input type="file" id="file_upload" name ="file_upload"  style="display: inline;">
			  	</div>
			  	<div class="image_container form-group">
					<img id="preview" >
				</div>
			  	<div class="btnDiv">
			  		<input type="button" onclick="AddCommodityBooth()" class="btn btn-default" value="确定">
			  		<input type="reset"   name ="button" class="btn btn-default" value="取消">
			  	</div>
			</form>
		</div>
		
	</body>
</html>
