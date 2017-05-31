<%@page import="java.net.URLEncoder"%>
<%@ page language = "java" pageEncoding="UTF-8"  %>
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/core"  prefix ="c" %>

	
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>moko店铺管理首页</title>
		<script type="text/javascript" src="./js/jquery.min.js"></script>
		<script src="./js/bootstrap.min.js"></script>
	<script src="./js/index.js" type="text/javascript" charset="utf-8"></script>
		<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="./css/index.css"/>
	</head>

	<script type="text/javascript">
	</script>
	
	<body>
		
		<!--头部导航begin-->
		<nav class="navbar navbar-inverse" style="background: #171614;" role="navigation">
	  		<div class="navbar-header">
	  			<img src="img/logo.jpg"/>
	  		</div>
	  		
	  		<div id="nav-ul">
	  			<ul>
	  			<li><a href="LoginServlet?method=Logout"><span class="glyphicon glyphicon-user">&nbsp;<strong>${sessionScope.account}</strong></span></a></li>
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
								商品管理
							</h4>
						</div></a>
						<div id="collapseOne" class="panel-collapse collapse">
							<a href="GoodsServlet?method=GoodsAddPre&account=${sessionScope.account}"  target="page"><div class="panel-body">
								添加商品
							</div></a>
							<a href="GoodsServlet?method=OnShelfQuery&currentpage=1&account=${sessionScope.account}"  target="page"><div class="panel-body">
								已上架商品
							</div></a>
							<a href="GoodsServlet?method=OffShelfQuery&currentpage=1&account=${sessionScope.account}" target="page"><div class="panel-body">
								未上架商品
							</div></a>
						</div>
					</div>
				
					<div class="panel panel-default">
						<a href="#collapseTwo" data-toggle="collapse" data-parent="#accordion"><div class="panel-heading">
							<h4 class="panel-title">
								订单管理
							</h4>
						</div></a>
						<div id="collapseTwo" class="panel-collapse collapse">
							<a href="OrderServlet?method=OrderQuery&searchType=0&currentpage=1&account=${sessionScope.account}" target="page"><div class="panel-body">
								订单详情
							</div></a>
							<a href="ReturnOrderServlet?method=ReturnOrderQuery&searchType=0&currentpage=1&account=${sessionScope.account}" target="page"><div class="panel-body">
								退换货处理
							</div></a>
						</div>
					</div>
					
					<div class="panel panel-default">
						<a href="#collapseThree" data-toggle="collapse" data-parent="#accordion"><div class="panel-heading">
							<h4 class="panel-title">
								店铺管理
							</h4>
						</div></a>
						<div id="collapseThree" class="panel-collapse collapse">
							<a href="StoreServlet2?method=StoreMessageQuery&account=${sessionScope.account}" target="page"><div class="panel-body">
								信息浏览
							</div></a>
							<a href="StoreServlet2?method=StoreMessageModifyPre&account=${sessionScope.account}"  target="page"><div class="panel-body">
								信息修改
							</div></a> 
							<a href="subpage/StoreManagement/walletTopUp.jsp?account=${sessionScope.account}" target="page"><div class="panel-body">
								钱包充值
							</div></a>
						</div>
					</div>
					<div class="panel panel-default">
						<a href="#collapseFour" data-toggle="collapse" data-parent="#accordion"><div class="panel-heading">
							<h4 class="panel-title">
								销售统计
							</h4>
						</div></a>
						<div id="collapseFour" class="panel-collapse collapse">
							<a href="OrderServlet?method=SalesStatistics&account=${sessionScope.account}" target="page"><div class="panel-body">
								销售额统计
							</div></a>  
							<a href="OrderServlet?method=SalesQuantityStatistics&account=${sessionScope.account}" target="page"><div class="panel-body">
								销售量统计
							</div></a> 
							<a href="OrderServlet?method=GoodsSalesRank&account=${sessionScope.account}" target="page"><div class="panel-body">
								销售商品排行
							</div></a>
						</div>
					</div>
					<div class="panel panel-default">
						<a href="#collapseFive" data-toggle="collapse" data-parent="#accordion"><div class="panel-heading">
							<h4 class="panel-title">
								我要推广
							</h4>
						</div></a>
						<div id="collapseFive" class="panel-collapse collapse">
							<a href="BoothsServlet?method=AdvertingBoothQuery&account=${sessionScope.account}" target="page"><div class="panel-body">
								广告展位竞拍
							</div></a>
							<a href="BoothsServlet?method=CommodityBoothQuery&account=${sessionScope.account}" target="page"><div class="panel-body">
								商品展位竞拍
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
	