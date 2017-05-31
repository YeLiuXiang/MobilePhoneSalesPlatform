<%@page import="java.net.URLEncoder"%>
<%@ page language = "java" pageEncoding="UTF-8"  %>
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/core"  prefix ="c" %>

	
<!DOCTYPE html>
<html>
<c:set var="basePath" scope="request">${pageContext.request.contextPath}</c:set>
	<head>
		<meta charset="UTF-8">
		<title>墨客平台管理系统首页</title>
		<script type="text/javascript" src="./js/jquery.min.js"></script>
		<script src="./js/bootstrap.min.js"></script>
		<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="./css/index.css"/>
	</head>
	<script type="text/javascript">
		$('#collapseOne, #collapseTwo, #collapseThree').collapse({
	 		parent : '#accordion',
	 		toggle : false,
		});
	</script>
	<body>
		<!--头部导航begin-->
		<nav class="navbar navbar-inverse" style="background: #171614;" role="navigation">
	  		<div class="navbar-header">
	  			<img src="img/logo.jpg"/>
	  		</div>
	  		
	  		<div id="nav-ul">
	  					<ul>
				<li><a href=""><span class="glyphicon glyphicon-user">&nbsp;<strong>${sessionScope.account}</strong></span></a></li>
				
			</ul>
	  		</div>
		</nav>
		<!--头部导航end-->
		
		<!--下部容器begin-->
		<div id="container">
			
			<!--左侧导航begin-->
			<div id="body-left">
				<div class="panel-group" id="accordion">
					
					<div class="panel panel-default">
						<a href="#collapseOne" data-toggle="collapse" data-parent="#accordion"><div class="panel-heading">
							<h4 class="panel-title">
								店铺信息管理
							</h4>
						</div></a>
						<div id="collapseOne" class="panel-collapse collapse">
							<a href="/Moko_Management/subpage/StoreManagement/messageQuery2.jsp?flag=true"  target="page"><div class="panel-body">
								信息查询
							</div></a>
							<a href="${basePath}/ManagerServlet?method=未上架商品查询&currentpage=1" target="page"><div class="panel-body">
								店铺统计
							</div></a>
						</div>
					</div>
				
					
					
					<div class="panel panel-default">
						<a href="#collapseThree" data-toggle="collapse" data-parent="#accordion"><div class="panel-heading">
							<h4 class="panel-title">
								平台信息管理
							</h4>
						</div></a>
						<div id="collapseThree" class="panel-collapse collapse">
							<a href="${basePath}/BoothServlet?method=AdvertingBoothQuery" target="page"><div class="panel-body">
								广告展位
							</div></a>
							<a href="${basePath}/BoothServlet?method=CommodityBoothQuery&currentpage=1" target="page"><div class="panel-body">
								商品展位
							</div></a>
							<a href="" target="page"><div class="panel-body">
								友情链接
							</div></a>
							<a href="" target="page"><div class="panel-body">
								企业文化
							</div></a>
						</div>
					</div>
					
					
					
				</div>
			</div>
			<!--左侧导航end-->
			
			<!--右侧内容begin-->
			<iframe name="page" id="page"  width="1210px" height="592px" style="border: 1px solid black; position: absolute;top: 50px;left: 150px;">
				
			</iframe>
			<!--右侧内容end-->
		
		</div>
		<!--下部容器end-->
		
	</body>
</html>