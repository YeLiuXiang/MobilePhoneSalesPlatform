<%@page import="net.sf.json.JSONObject"%>
<%@page import="net.sf.json.JSONArray"%>
<%@page import="java.util.ArrayList"%>
<%@page import="cn.edu.zhku.xk.momo.been.Comment"%>
<%@page import="cn.edu.zhku.xk.momo.been.GoodsExtend"%>
<%@page import="cn.edu.zhku.xk.momo.util.MultipleMap"%>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

   <!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <link rel="icon" href="${pageContext.request.contextPath}/style/image/favicon.ico"/>
   		<link rel="shortcut icon" href="${pageContext.request.contextPath}/style/image/favicon.ico"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/style/css/bootstrap.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/style/css/goodsDetails.css">
		<script src="${pageContext.request.contextPath}/style/js/jquery-3.1.1.js"></script>
		<script src="${pageContext.request.contextPath}/style/js/bootstrap.min.js"></script>
		<script type="text/javascript"  src="${pageContext.request.contextPath}/style/js/goodsDetails.js">	</script>
		<title>详情</title>
		<%

	JSONObject result =(JSONObject ) request.getAttribute("result");
	String flag =(String)result.getString("flag");
	if (flag.equals("false") ) { 
		response.setStatus(302);
		response.setHeader("location", "redirect2");
		return;
	} 
		List<Comment> commentList=JSONArray.toList(result.getJSONArray("commentList"),Comment.class);
		MultipleMap<GoodsExtend> goodsMap=MultipleMap.toMultipleMap( result.getString("goodsMap"),new GoodsExtend());	
%>
    </head>
    <body>



    <div id="div_body">
    	<div id="div_position">
	    	<div class="phoneimg"> 
    			<img id="goodsPic" src="${pageContext.request.contextPath}/resource/${result.goodsBase.picAddress}" height="300" width="400">
     		</div>
     		
     		
     		
     		
	    	<div id="div_phone">
	    		<div >${result.goodsBase.title}</div><!--此处插入商品标题-->
	    		<div class="desc" style="font-size:30px;width:300px;height:100px;">
	    		
		    		${result.goodsBase.name}
		 	  	</div>

		 	  	<div id="div_phonedesc" >

		 	  		<div class="select">容量：</div>
		 	  		<div class="option">
			 	  		<ul class="nav nav-pills">
			 	  		<%
			 	  		List<String> keyList=goodsMap.getKey();
			 	  		for(int i=0;i<keyList.size();i++){
			 	  		if(i==0){%>
						  <li class="active"><a href="#<%= i %>" data-toggle="tab"><%=keyList.get(i)%></a></li>
						   <%}else{ %>
						  <li><a href="#<%= i %>" data-toggle="tab"><%=keyList.get(i)%></a></li>
						  <%} 
			 	  		} %>
						</ul>
					</div>
					<div class="select">颜色：</div>
					<div class="tab-content"> <!--颜色外面板组  -->
					<%for(int i=0;i<keyList.size();i++){%>
						<%if(i==0){%>
						<div id="<%= i %>" class="tab-pane fade in active">
							<!-- 大的活动面板头 -->
						<%}else{%>
						 <div id="<%= i %>" class="tab-pane fade">
							<!-- 大的待活动面板头 -->
						<%}%>
						<ul class="nav nav-pills" >
						<!-- //导航头 -->
					<%	List<GoodsExtend> valueList=goodsMap.getValue(keyList.get(i));//导航信息
			 	  		for(int j=0;j<valueList.size();j++){
			 	  			GoodsExtend goodsExtend=valueList.get(j);
			 	  			if(j==0){%>
			 	  			 <li class="active"><a href="#goodsid<%=goodsExtend.getModelId() %>" data-toggle="tab"><%=valueList.get(j).getColor()%></a></li>
			 	  				<!-- li活动导航 -->
			 	  		<%	}else{%>
			 	  		 <li><a href="#goodsid<%=goodsExtend.getModelId() %>" data-toggle="tab"><%=valueList.get(j).getColor()%></a></li>
			 	  			<!-- li待活动导航 -->
			 	  			<%}
						}%>
						</ul>
						<!-- 导航结束 -->
						<div class="tab-content"><!--价格面板组  -->
			 	  		<%for(int j=0;j<valueList.size();j++){
			 	  			GoodsExtend goodsExtend=valueList.get(j);
			 	  			if(j==0){%>
			 	  			<div class="tab-pane fade in active" id="goodsid<%=goodsExtend.getModelId() %>">
			 	  				<!-- 小活动面板头 -->
			 	  			<%}else{%>
			 	  			<div class="tab-pane fade " id="goodsid<%=goodsExtend.getModelId() %>">
			 	  				<!-- 小活动面板头 -->
			 	  			<%}%>
			 	  			
			 	  			<div class="price">价格:<span ><%=goodsExtend.getPrice() %></span></div>
			 	  			<button id="minus" onclick="minus()">-</button>
		 	  		<input type="text" id="buyNum" size="5" value="1" onkeyup="value=value.replace(/[^\d]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" 
					onchange="checkNum()"
		 	  		/>
		 	  		<button id="add" onclick="add()">+</button>
			 	  			<span>库存数量:<span id="store"><%=goodsExtend.getNumber() %></span></span>
							
			 	  			<!-- 活动面板 -->
			 	  			</div>
			 	  			<!-- 小活动面板结束 -->
			 	  		<%}%>
			 	  		</div><!--价格面板组  -->
			 	  	</div><!--大的活动面板头 结束  -->
					<%}
					%>
				</div><!--颜色外面板组  -->
					
			<div id="buyBtn">
		    		<button id="btnAddCart" class="btn btn-info btn-lg" onclick="AddCart()">加入购物车</button>
	    			<button id="btnBuyNow" class="btn btn-info btn-lg" onclick="BuyNow()">一键下单</button>
		    	</div>		
	    	</div><!-- div_phonedesc -->
		</div>



		

    </body>
</html> 