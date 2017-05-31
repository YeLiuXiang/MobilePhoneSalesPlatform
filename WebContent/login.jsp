<%@ page language = "java" pageEncoding="UTF-8"  %>
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/core"  prefix ="c" %>
<%@ page import="org.apache.commons.lang.RandomStringUtils"%>
<!DOCTYPE html>
<html>
	<head>
	<c:set var="basePath" scope="request">${pageContext.request.contextPath}</c:set>

		<title>平台管理系统登录</title>
		<link rel="stylesheet" type="text/css" href=" ${basePath}/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href=" ${basePath}/css/login.css"/>
		<script type="text/javascript" src="js/jquery.min.js"></script>
		<script src=" ${basePath}/js/bootstrap.min.js"></script>
		<script src=" ${basePath}/js/login.js" type="text/javascript" charset="utf-8"></script>
		<script src=" ${basePath}/js/jquery-form.js" type="text/javascript" charset="utf-8"></script>
		<script src="${basePath}/js/jquery.md5.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<body>
		
		<div id="loginDiv">
			<div id="logoDiv">
				<img src="img/logo.jpg"/>
			</div>
			
			<form id="registForm"  name = "registForm"  class="navbar-form navbar-left" role="search"  >
				<div class="form-group">
					<span>账号：</span>
					<input id = "account" name="account" type="text" class="form-control">
				</div>
				<div class="form-group">
					<span>密码：</span>
					<input type="password"  id = "pass"  class="form-control" >
				</div>
				<div class="form-group">
				  	<span>验证码：</span>
				    <input id = "validateCode" name="validateCode"  type="text" class="form-control">
				    <div id="verificationCodeDiv"><img src="${basePath}/PictureCheckCode" id="createCheckCode"onClick = "this.src = 'PictureCheckCode?time = '+ (new Date()).valueOf()"/></div>
				</div>
				<input class="btn btn-success " id = "register" type="button"  onclick = "login()" value="登&nbsp;&nbsp;&nbsp;&nbsp;录" />
			</form>
			
		</div>
		
	</body>
</html>



