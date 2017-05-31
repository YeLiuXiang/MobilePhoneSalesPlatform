<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/css/top_foot.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/css/register.css">
</head>
<jsp:include page="/TopAndFooter/Top.jsp" flush="true" />
<body>

	<div id="register_box">
		<div id="logo">
			<img src="${pageContext.request.contextPath}/style/image/logo.png" />
		</div>
		<h3>moko手机销售平台注册</h3>
		<div id="errText"></div>
		<div id="registerForm" class="container">
			<div class="row">
				<div class="col-md-4">
					<span>账号</span>
				</div>
				<div class="col-md-8">
					<input type="text" id="account" placeholde="账号" autofocus=""
						tabindex="1" />
				</div>
			</div>
			<div class="row">
				<div class="col-md-4">
					<span>登录密码</span>
				</div>
				<div class="col-md-8">
					<input type="password" maxlength="16" placeholde="密码" id="password"
						tabindex="2" />
				</div>
			</div>
			<div class="row">
				<div class="col-md-4">
					<span>密码确认</span>
				</div>
				<div class="col-md-8">
					<input type="password" maxlength="16" id="passwordVerify"
						placeholde="再一次输入密码" tabindex="3" />
				</div>
			</div>
			<div class="row">
				<div class="col-md-4">
                        <span>手机号码</span>
                    </div>
                    <div class="col-md-8">
                        <input type="text" maxlength="16" id="phone" tabindex="4" />
                 </div>
          </div>  
          <div class="row">   
                    <div class="col-md-4">
                        <span>手机验证码</span>
                    </div>
                    <div class="col-md-4">
                        <input type="text" maxlength="4" id="phoneCode" tabindex="5"/>
                    </div>
                    <div class="col-md-4" >
                    	<input type="button" class="btn btn-default btn-xs" id="phoneCodeB" value="获取验证码" onclick="sendCode(this)" /> 
                    </div>
            </div>
            <div class="row"> 
				<button class="btn btn-primary" id="submit">提交</button>
				<button class="btn btn-primary" type="reset" id="reset">重置</button>
			</div>
 		</div>
		
	</div>
	
	<jsp:include page="/TopAndFooter/Footer.jsp" flush="true" />
</body>

<script type="text/javascript" src="${pageContext.request.contextPath}/style/js/jquery-3.1.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/style/js/register.js"></script>
</html>