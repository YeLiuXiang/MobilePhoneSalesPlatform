<%@page import="net.sf.json.JSONArray"%>
<%@page import="net.sf.json.JSONObject"%>
<%@page import="cn.edu.zhku.xk.momo.been.GoodsForSearch"%>
<%@page import=" java.text.DecimalFormat"%>
<%@page import=" java.util.List"%>
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        
        <link rel="stylesheet" href="${pageContext.request.contextPath}/style/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/style/goodsSearchShow.css">
<%
JSONObject result=(JSONObject)request.getAttribute("result");
List<GoodsForSearch> goodsList=JSONArray.toList(result.getJSONArray("data"), GoodsForSearch.class);

%>
    </head>


<!-- 商品循环开始 --> 
					

<body >
<div class=maintain>

	<!--搜索-->
	<div class="search" >
		<div class="col-lg-3" id="search">
			<div class="input-group">
				<input type="text" class="form-control" id="keyword" autofocus>
				<span class="input-group-btn">
					<button class="btn btn-default" type="button" id="btnSearch">搜索</button></span>
			</div>
		</div>
	</div>
	以下是关于的搜索结果：
			<!--搜索结束-->
			<br><br>

    <!--商品浏览-->
	<div id="case">
	<%
	int i=0;
	DecimalFormat df = new DecimalFormat();
	String style = "0,000.00#";
	df.applyPattern(style); 
	for(GoodsForSearch goods:goodsList){ 
		if(i++%3==0){
	%>
			<div class="row">
		<%} %>	
		<!--一件商品开始-->
			<div class="col-lg-3 col-md-4 col-sm-6 col-xs-12 product">
				<div class="thumbnail">
					<a href="${pageContext.request.contextPath}/GoodsSearch?method=goodsDetails&goodsId=<%=goods.getId() %>">
						<img  src="${pageContext.request.contextPath}/resource/<%=goods.getPictureAddress()%>" width="245px" height="345px" alt="">
						
							<h3><%=goods.getTitle() %></h3><!--手机标题-->
					</a>
					<div class="caption">
						<p > 店铺:<a href="${pageContext.request.contextPath}/GoodsSearch?method=goodsDetails&goodsId=<%=goods.getId() %>"><%=goods.getStoreName() %> </a>
						库存：<label class ="modelCapacity"><%=goods.getVolume() %></label></p>
						<p class="price">¥<%=df.format(goods.getPrice()) %></p><!--手机价格-->
					</div>
				</div>
			
			</div>			
		<% if(i%3==0){%>
			</div><!-- row -->
	<%} %>
<%} %> 	
<% if(i%3!=0){%>
	</div><!-- row 未满3的倍数-->
<%} %>
</div>
<!--商品浏览结束-->
</div>						
</body>
    <script src="${pageContext.request.contextPath}/style/js/jquery-3.1.1.js"></script>
</html>