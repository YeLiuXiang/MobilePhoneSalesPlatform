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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/style/css/top_foot.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/style/detail/goodsDetails.css">
		<script src="${pageContext.request.contextPath}/style/js/jquery-3.1.1.js"></script>
		<script src="${pageContext.request.contextPath}/style/js/bootstrap.min.js"></script>
		<script type="text/javascript"  src="${pageContext.request.contextPath}/style/goodsDetails.js">	</script>
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
    			<img src="${pageContext.request.contextPath}/resource/${result.goodsBase.picAddress}" height="528" width="416">
     		</div>
     		<div style="display:none;" class="modelNo">${result.goodsBase.title}</div><!--此处插入商品标题-->
	    	<div id="div_phone">
	    		<div class="desc" style="font-size:30px;width:300px;height:100px;">
		    		${result.goodsBase.name}
		 	  	</div>

		 	  	<div id="div_phonedesc"  style="">

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
					<div class="tab-content">
					<%for(int i=0;i<keyList.size();i++){
						if(i==0){%>
						  <div id="<%= i %>" class="tab-pane fade in active">
						   <%}else{ %>
						  <div id="<%= i %>" class="tab-pane fade">
						  <%} %>
				 	  		
				 	  		<ul class="nav nav-pills">
				 	  		<%
				 	  		List<GoodsExtend> valueList=goodsMap.getValue(keyList.get(i));
				 	  		for(int j=0;j<valueList.size();j++){
				 	  			if(j==0){
				 	  		%>
							  		<li class="active"><a href="#goodsid<%=valueList.get(j).getModelId() %>" data-toggle="tab"><%=valueList.get(j).getColor()%></a></li>
							   <%}else{ %>
							 		 <li><a href="#goodsid<%=valueList.get(j).getModelId() %>" data-toggle="tab"><%=valueList.get(j).getColor()%></a></li>
							  <%} %>
							   
						  <%} %>
					</ul>
						</div><!-- tab-pane -->
		 	  	
		    	 <%} %>
		    	 </div> <!-- tab-content-->
		    	 
		    	 <div class="tab-content"><!-- 价格详情-->
		    	 <%for(int i=0;i<keyList.size();i++){
				 	  		List<GoodsExtend> valueList=goodsMap.getValue(keyList.get(i));
				 	  		for(int j=0;j<valueList.size();j++){
				 	  			GoodsExtend goodsExtend=valueList.get(j);
				 	  			if(j==0&&i==0){
				 	  		%>
							  		<div class="tab-pane fade " id="goodsid<%=goodsExtend.getModelId() %>">
							   <%}else{ %>
							 		<div class="tab-pane fade " id="goodsid<%=goodsExtend.getModelId() %>">
									<%} %>
									
									<button id="minus" onclick="minus()">-</button>
		 	  		<input type="text" id="buyNum" size="5" value="1" onkeyup="value=value.replace(/[^\d]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" 
onchange="checkNum()"
		 	  		/>
		 	  		<button id="add" onclick="add()">+</button>
		 	  		<span>库存数量:<span id="store"><%=goodsExtend.getNumber() %></span></span>
						<span>价格:<span id="store"><%=goodsExtend.getPrice() %></span></span>			
							   </div>
						  <%} 
				 	  		} %>
		    	 </div><!--tab-content  -->
		    	 
	    	</div><!-- div_phonedesc -->
		</div>



		<ul id="myTab" class="nav nav-tabs">
			<li class="active">
				<a href="#home" data-toggle="tab">商品详情</a>
			</li>
			<li>
				<a href="#assess" data-toggle="tab">评价</a>
			</li>
		</ul>
		
		
		
		<div id="myTabContent" class="tab-content">
			<div class="tab-pane fade in active" id="home">
				<img id="descjpg" src="${pageContext.request.contextPath}/resource/${result.goodsBase.desc}" width="960px">
			</div>



<!-- 这是评价 -->

			<div class="tab-pane fade" id="assess">


<%if(commentList!=null&&commentList.size()>0){
 	for(Comment comment:commentList){	%>
  
				<div class="row">
					<div  class="col-md-1 userName" >
						<%=comment.getCustomerAccount() %>
					</div>
					<div  class="col-md-2 context" > 
						<%=comment.getContent() %>
					</div>
				</div>
				<hr/>	
  <% 
	}
}else{%>
该商品暂无评论!

<% 
	}
%>	


			</div>
		</div>

		


    </div>




	<!-- 返回顶部-->
	<div id="div_back">
		<a href="#top">
			<button class="btn" >返回顶部</button>
		</a>
	</div>




    </body>
</html> 