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
                	${result.data.account}
                </span>
     		</div>

     		<div class="form-group">
        		<label class="col-sm-4 control-label">性别</label>
        		
                <div class="col-sm-6">
	                <select name="sex" id="sexSelect">
	        			<option value="男"  ${result.data.sex=='男'?'selected':''}>男</option>
	        			<option value="女" ${result.data.sex=='女'?'selected':''}>女</option>
	        			<option value="" ${result.data.sex==''?'selected':''}>保密</option>
	        		</select>	
                </div>
     		</div>
     		
    		<div class="form-group">
        		<label class="col-sm-4 control-label">昵称</label>
                <div class="col-sm-6">
                	<input type="text" class="form-control" value="${result.data.nickName}" id="nickName">
                </div>
     		</div>
    		
			
            <div class="form-group">
        		<label class="col-sm-4 control-label">邮箱</label>
                <div class="col-sm-6">
                	<input type="email" class="form-control" value="${result.data.email}" id="email">
                </div>
     		</div>
			<div class="form-group">
        		<label class="col-sm-4 control-label">收货人</label>
                <div class="col-sm-6">
                	<input type="text" class="form-control" value="${result.data.receiver}" id="receiver">
                </div>
     		</div>
     		<div class="form-group">
        		<label class="col-sm-4 control-label">联系方式</label>
                <div class="col-sm-6">
                	<input type="text" class="form-control" value="${result.data.receiverPhone}" id="receiverPhone">
                </div>
     		</div>
			<div class="form-group">
        		<label class="col-sm-4 control-label">收货地址</label>
                <div class="col-sm-6">
                	<input type="text" class="form-control" value="${result.data.address}" id="address">
                </div>
     		</div>

     		
            
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="button" class="btn btn-default submit1">修改信息</button>
					<input type="reset" class="btn btn-default " value="重置" >
				</div>
			</div>
    	</form>
	</div>


		
	<script src="${pageContext.request.contextPath}/style/js/jquery-3.1.1.js"></script>
    <script src="${pageContext.request.contextPath}/style/js/modifyInformation.js"></script>

	
    </body>
</html>