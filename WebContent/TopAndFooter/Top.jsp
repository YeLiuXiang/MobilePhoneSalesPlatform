<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/style/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/css/login.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/css/top_foot.css">
    
    <link rel="icon" href="${pageContext.request.contextPath}/style/image/favicon.ico" />
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/style/image/favicon.ico" />
    
    <script src="${pageContext.request.contextPath}/style/js/bootstrap.min.js" ></script>
	<script src="${pageContext.request.contextPath}/style/js/jquery-3.1.1.js" ></script>
	<script src="${pageContext.request.contextPath}/style/js/top.js" ></script>
 	<script type="text/javascript" src="${pageContext.request.contextPath}/style/js/login.js"></script>

</head>
<body>
<%
String userName=(String)session.getAttribute("userName");
%>
	<!--顶部导航-->
	<div class="navLevel">
		<div class="navbar-header">
			<img src="${pageContext.request.contextPath}/style/image/logo.png" width="150px" height="45px" />
		</div>
		<ul class="nav nav-tabs">
			<li><a href="./"><span class="glyphicon glyphicon-home"></span>首页</a></li>
			<li><a href="${pageContext.request.contextPath}/customerInfoOp/customerCenter.jsp"><span
					class="glyphicon glyphicon-user"></span>个人中心</a></li>
			<li><a href="${pageContext.request.contextPath}/ShopCar"><span
					class="glyphicon glyphicon-shopping-cart"></span>购物车</a></li>
			<ul class="nav nav-tabs navbar-right">
			<%if(userName==null) {%>
				<li><a  href="${pageContext.request.contextPath}/customerInfoOp/login.jsp">登录</a></li>
				<!-- <li><a data-toggle="modal" data-target="#loginDiv">登录</a></li> -->
				<li><a href="${pageContext.request.contextPath}/Register">注册</a></li>
			<%}else{%>
				<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">welcome,${userName}<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a onclick="exit()">退出</a></li>
					</ul>
				</li>
			<%}%>
			</ul>
		</ul>
	</div>
	<!--顶部导航结束-->
<%-- 	<%if(userName==null) {%>
<div id="loginDiv"class="modal fade"  tabindex="-1" role="dialog"  aria-hidden="true">
            <div id="logo">
                <img src="${pageContext.request.contextPath}/style/image/logo.png"/>
            </div>
            <h3> moko手机销售平台系统登陆</h3>
            <div id="errText"></div>
            <div id="loginForm" class="container">
                <div class="row">
                    <div class="col-md-3">
                        <span>账号</span>
                    </div>
                    <div class="col-md-9">
                        <input type="text"  id="account" autofocus="" tabindex="1" />
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3">
                        <span>密码</span>
                    </div>
                    <div class="col-md-9">
                        <input type="password" maxlength="16" id="password" tabindex="2" />
                    </div>
                </div>
                
                 <div class="row">
                    <div class="col-md-3">
                        <span>验证码</span>
                    </div>
                    <div class="col-md-6">
                        <input type="text" maxlength="4" id="code" tabindex="3"/>
                    </div>
                    <div class="col-md-3" onclick="reloadCode()">
                        <img src="${pageContext.request.contextPath}/checkCode" alt="" width="80px" id="imagecode"/>
                    </div>
                </div>
                <div class="row">
                <div class="btnLoRe">
                    <button class="btn btn-primary" id="login">登录</button>
                    <button class="btn btn-primary" type="reset" id="reset" >重置</button>
                </div>
                
            </div>
        </div>
      </div>
      	<%}%> --%>
</body>
</html>