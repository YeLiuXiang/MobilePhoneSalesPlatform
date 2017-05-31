<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ page import="cn.edu.zhku.xk.momo.been.Customer" %>   
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/style/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/style/css/customerInfo.css">
     

    </head>
    <body>
		
		<div id="headPotrait">
				<img class="img-circle" src="${pageContext.request.contextPath}/style/image/customer_head.png" >
		</div>

		<div id="customerInformation">
		<div id="errText"></div>
	    <form class="form-horizontal" role="form">
	    	<div class="form-group">
        		<label class="col-sm-4 control-label">用户名:</label>
                <span class="col-sm-6">
                	${userName}
                </span>
     		</div>
     		<div class="form-group">
        		<label class="col-sm-4 control-label">新密码</label>
        		<div class="col-sm-6">
        			<input type="password" maxlength="16" placeholde="密码" id="password"
						tabindex="2" tabindex="1"/>  
                </div>
     		</div>
     		
    		<div class="form-group">
        		<label class="col-sm-4 control-label">密码确认</label>
                <div class="col-sm-6">
                	<input type="password" maxlength="16" id="passwordVerify"
						placeholde="再一次输入密码" tabindex="2" />
                </div>
     		</div>
    		
            
			<div class="form-group">
        		<label class="col-sm-4 control-label">手机验证码</label>
               <div class="row">
	                <div class="col-sm-4">
	                	<input type="text" class="code" id="phoneCode" tabindex="2"/> 
	                </div>
	                  <div class="col-sm-4">
	                	<input type="button" class="btn btn-default btn-xs" id="phoneCodeB" value="获取验证码" onclick="sendCode(this)" /> 
	                </div>
                </div>
     		</div>
     		
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="button" id ="mofidyPassword" class="btn btn-default ">修改密码</button>
					<input type="reset" class="btn btn-default " value="重置" >
				</div>
			</div>
    	</form>
	</div>


		
	<script src="${pageContext.request.contextPath}/style/js/jquery-3.1.1.js"></script>
    <script src="${pageContext.request.contextPath}/style/js/modifyPassword.js"></script>

	
    </body>
</html>