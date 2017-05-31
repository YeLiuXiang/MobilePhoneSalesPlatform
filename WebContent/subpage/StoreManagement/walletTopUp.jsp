<%@ page language = "java" pageEncoding="UTF-8"  %>
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/core"  prefix ="c" %>
<!DOCTYPE html>
<html>
<c:set var="basePath" scope="request">${pageContext.request.contextPath}</c:set>
	<head>
		<meta charset="UTF-8">
		<title>店铺钱包充值</title>
		<link rel="stylesheet" type="text/css" href="${basePath}/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="${basePath}/css/storeWallet.css"/>
		
		<script src="${basePath}/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="${basePath}/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="${basePath}/js/walletTopUp.js" type="text/javascript" charset="utf-8"></script>
		<script src="${basePath}/js/jquery-form.js" type="text/javascript" charset="utf-8"></script>
	</head>
<body>
	<input type="hidden" id="account" value="${param.account}">
	<div id="container">
	
	<form id="walletTopUpForm" class="navbar-form navbar-left"   >
	<div>请选择充值的金额（￥）：</div>
		<label style="font-size: 30px;">
		<input type="radio" id = "radio1" name="radio"	value="2000"  checked="checked"/>2000
		</label>
		<label style="font-size: 30px;">
		<input type="radio" id = "radio2" name="radio"	value="4000"/>4000
		</label>
		<label style="font-size: 30px;">
		<input type="radio" id = "radio3" name="radio"	value="8000"/>8000
		</label>
		<label style="font-size: 30px;">
		<input type="radio" id = "radio4" name="radio"	value="10000"/>10000
		</label>
			<div class="btnDiv">
			<input type="button" onclick="WalletTopUp()"  value= "充值"  class="btn btn-success"/>
			</div>
			</form>
	</div>

</body>
</html>
