<%@ page language = "java" pageEncoding="UTF-8"  %>
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/core"  prefix ="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"   prefix="fmt" %> 

<!DOCTYPE html>
<html>
<c:set var="basePath" scope="request">${pageContext.request.contextPath}</c:set>

	<head>
		<meta charset="UTF-8">
		<title>退换货申请单处理</title>
<link rel="stylesheet" type="text/css" href="${basePath}/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="${basePath}/css/common.css"/>
		<link rel="stylesheet" type="text/css" href="${basePath}/css/returnOrder.css"/>
		<script src="${basePath}/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="${basePath}/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="${basePath}/js/shelf.js" type="text/javascript" charset="utf-8"></script>
		</head>
		<script type="text/javascript">
		function send() {
			var id = $("input#returnId").val();
			if(id==""){
				alert("查询条件不能为空！");
				return;
			}
			window.location = "${basePath}/ReturnOrderServlet?method=ReturnOrderQueryById&searchType=2&ref_id="+ id;
		}
		</script>
	<body>
		<div id="container">
			<!--位置提醒-->
			<div class="posiRemind">您当前的位置：<span>订单管理</span> > <strong>退换货申请单处理</strong></div>
			
			<!--顶部搜索框begin-->
			<div class="row">
				
				<!--下拉框-->
				<div class="btn-group">
				  	<button  onclick="window.location='${basePath}/ReturnOrderServlet?method=ReturnOrderQuery&searchType=0&currentpage=1&account=${sessionScope.account }'" type="button" class="btn btn-default">未处理</button>
				  	<button onclick="window.location='${basePath}/ReturnOrderServlet?method=ReturnOrderQuery&searchType=1&currentpage=1&account=${sessionScope.account }'" type="button" class="btn btn-default">已同意</button>
				 	<button onclick="window.location='${basePath}/ReturnOrderServlet?method=ReturnOrderQuery&searchType=2&currentpage=1&account=${sessionScope.account }'" type="button" class="btn btn-info">已拒绝</button>
				</div>
				
				<!--搜索框-->
				<div class="col-lg-6">
				    <div class="input-group">
				      	<input name = "returnId" id="returnId"  type="text" class="form-control" placeholder="请输入申请表编号">
				      	<span class="input-group-btn">
				        	<button  onclick="send()" class="btn btn-default" type="button">查询</button>
				      	</span>
				    </div>	
				</div>	
			</div>	
			<!--顶部搜索框end-->
				<c:if test= "${empty requestScope.Object.list}"  >
			<center><h4>您查找的申请单不存在！</h4></center>
			</c:if>
			<c:if test= "${!empty requestScope.Object.list}"  >
			<!--内容表格begin-->
			<div id="content">
				<table class="table table-hover table-bordered">
					<thead>
						<tr>
							<th class="text-center">类型</th>
							<th class="text-center">申请表编号</th>
								<th class="text-center">订单编号</th>
							<th class="text-center">状态</th>
							<th class="text-center">申请日期</th>
							<th class="text-center">申请原因</th>
							<th class="text-center">操作</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var ="retu" items="${requestScope.Object.list }">
						<tr>	<!--第1行-->
					<td class="text-center">${retu.type }</td>
							<td class="text-center">${retu.id }</td>
							<td class="text-center">${retu.orderId }</td>
							<td class="text-center">${retu.status }</td>
							<td class="text-center"><fmt:formatDate	value="${retu.date }" type="both" /></td>
							<td class="text-center">${retu.reason }</td>
							<td class="text-center">
						<!--  		<button class="btn btn-info btn-sm">同意</button>
								<button class="btn btn-danger btn-sm">拒绝</button> 
								-->
							</td>
						</tr>
						</c:forEach>
						
					</tbody>
				</table>
			</div>
			<!--内容表格end-->
			
			<!--底部分页begin-->
			<footer>
					<ul class="pager">
	    				<li><a href="${basePath}/ReturnOrderServlet?method=ReturnOrderQuery&searchType=2&account=${sessionScope.account }&currentpage=${ requestScope.Object.previousPage}">上一页</a></li>
	    				<li><a href="${basePath}/ReturnOrderServlet?method=ReturnOrderQuery&searchType=2&account=${sessionScope.account }&currentpage=${ requestScope.Object.nextPage}">下一页</a></li>
					</ul>
					<form method = "post" action = "${basePath}/ReturnOrderServlet?method=ReturnOrderQuery&searchType=2&account=${sessionScope.account }"  class="navbar-form navbar-right" role="search" >
						第<strong style="color: darkmagenta;">${requestScope.Object.currentPage}</strong>页
						共<strong style="color: darkmagenta;">${requestScope.Object.totalPages}</strong>页
					  	<div class="form-group">
					    	<input type="text" name = currentpage class="form-control">
					  	</div>
					  	<button type="submit" class="btn btn-success">跳转</button>
					</form>
			</footer>
			<!--底部分页end-->
		</div>
			
			</c:if>
	</body>
</html>
