<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="cn.edu.zhku.xk.momo.been.Customer" %>
    
<!DOCTYPE html>
<html >
    <head>
        
        <link rel="stylesheet" href="style/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/css/customerCenter.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/css/customerInfo.css">
     
    </head>
    <body>
			<div id="headPotrait">
				<img class="img-circle" src="${pageContext.request.contextPath}/style/image/customer_head.png" >
		</div>
			<div id="customerInformation">
				<p>账号：<label>${result.data.account}</label></p>
				<p>性别：<label>${result.data.sex}</label></p>
				<p>昵称：<label>${result.data.nickName}</label></p>
				<p>手机号：<label>${result.data.phone}</label></p>
				<p>邮箱：<label>${result.data.email}</label></p>
				<p>收货人姓名：<label>${result.data.receiver}</label></p>
				<p>收货联系方式：<label>${result.data.receiverPhone}</label></p>
				<p>收货地址：<label>${result.data.address}</label></p>
				
				<a href="${pageContext.request.contextPath}/CustomerInfo?method=premodify" target="show"><button class="btn btn-default" id="alter">修改信息</button></a>
			</div>	
    </body>
</html>