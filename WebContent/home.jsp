<%@page import="net.sf.json.JSONArray"%>
<%@page import="net.sf.json.JSONObject"%>
<%@ page import="cn.edu.zhku.xk.momo.been.AdvertisingBooth" %>
<%@ page import="cn.edu.zhku.xk.momo.been.GoodsForSearch" %>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/TopAndFooter/Top.jsp" flush="true" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/css/home.css" />
</head>
<body>
<%
	JSONObject result=(JSONObject)request.getAttribute("result");
	List<AdvertisingBooth> advertisingBoothList=JSONArray.toList(result.getJSONArray("advertisingBoothList"), AdvertisingBooth.class);
	List<GoodsForSearch> commodityBoothList=JSONArray.toList(result.getJSONArray("commodityBoothList"), GoodsForSearch.class);
%>
			<div class="container">
			<!--轮播图（公告）-->
			<div id="myCarousel" class="carousel slide" data-ride="carousel">
			<div class="carousel-inner" style="background-color: black">
			<ol class="carousel-indicators" >
				<li data-target="#myCarousel" data-slide-to="0"  class="active"></li>
				<li data-target="#myCarousel" data-slide-to="1"></li>
				<li data-target="#myCarousel" data-slide-to="2"></li>
			</ol>
			<%
			int i=0;
			for(AdvertisingBooth advertise:advertisingBoothList){ 
				if(i++==0){
			%>
					<div class="item active" >
						
					<%}else{
			%>
					<div class="item" >
					<%} %>
					<a  href="${pageContext.request.contextPath}/GoodsDetails?method=details&goodsId=<%=advertise.getGoodsId()%>">
					<img  src="${pageContext.request.contextPath}/resource/<%=advertise.getImgAddr()%>" alt="<%=i%>"/>
					</a>
					</div>
						
			<%} %>
			</div>
			<a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>
			<a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>
			</div>
			<!--轮播图（公告）结束-->

			<!--搜索-->
			<div class="row">
				<div class="col-lg-3" id="search">
					<div class="input-group">
						<input type="text" class="form-control" id="keyword">
						<span class="input-group-btn ">
						<button class="btn btn-default "  type="button" id="btnSearch">搜索</button>
						</span>
					</div>
				</div>
			</div>

			<!--搜索结束-->
	
			<!--商品浏览-->
			<div id="case">
			<%
			i=0;
			for(GoodsForSearch commodity : commodityBoothList){ 
				if(i++%3==0){
			%>
				<div class="row">
				<%} %>	
					<div class="col-lg-3 col-md-4 col-sm-6 col-xs-12">
						<a href="${pageContext.request.contextPath}/GoodsDetails?method=details&goodsId=<%=commodity.getId()%>">
							<div class="thumbnail">
								<img  src="${pageContext.request.contextPath}/resource/<%=commodity.getPictureAddress()%>" width="245px" height="345px" alt="">
								<div class="caption">
									<h3><%=commodity.getTitle() %></h3><!--手机标题-->
									<p > <a><%=commodity.getStoreName() %> </a>
									库存：<label class ="modelCapacity"><%=commodity.getVolume() %></label></p>
									<p class="price">¥<%=commodity.getPrice() %></p><!--手机价格-->
								</div>
							</div>
						</a>
					</div> 	
				<% if(i%3==0){%>
				</div><!-- row -->
				<%} %>
				<%} %> 	
				<% if(i%3!=0){%>
				</div><!-- row -->
				<%} %>
				</div>
			</div>
			<!--商品浏览结束-->
	
	<script src="${pageContext.request.contextPath}/style/js/jquery-3.1.1.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/style/js/home.js"></script>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/style/js/bootstrap.min.js"></script>

</body>
<jsp:include page="/TopAndFooter/Footer.jsp" flush="true" /> 
</html>