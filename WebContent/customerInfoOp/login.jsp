<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<head>

   	<title>登录</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/css/login.css" />
    <script  type="text/javascript" src="${pageContext.request.contextPath}/style/js/jquery-3.1.1.js"></script>
 	<script type="text/javascript" src="${pageContext.request.contextPath}/style/js/login.js"></script>
</head>

<body>

<jsp:include page="/TopAndFooter/Top.jsp" flush="true" />
<div id="loginDiv">
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
 <jsp:include page="/TopAndFooter/Footer.jsp" flush="true" /> 
        
	
</body>

</html>