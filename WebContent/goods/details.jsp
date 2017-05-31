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
	
    </head>
    <body>
  	<%

		JSONObject result =(JSONObject ) request.getAttribute("result");
		String flag =(String)result.getString("flag");
		if (flag.equals("false") ) { 
			response.setStatus(302);
			response.setHeader("location", "redirect2");
			return;
		} 
%>

    <div  id="div_body">
    	<div id="div_position">
	    		<div class="phoneimg"> 
    			<img id="showPic" src="${pageContext.request.contextPath}/resource/${result.goodsBase.picAddress}" height="480" width="640">
     		</div>
	    	<div id="div_phone">
	    	<span id="goodsId" style="display:none">${result.goodsBase.id}</span>
	    	<span id="modelId" style="display:none"></span>
	    		<div >${result.goodsBase.title}</div><!--此处插入商品标题-->
	    		<div class="desc" style="font-size:30px;width:300px;height:100px;">
	    		
		    		${result.goodsBase.name}
		 	  	</div>

		 	  	<div id="div_phonedesc"  style="">
					<div class="select">价格:
			 	  		<span id="price"></span>
					</div>
		 	  		

		 	  		

			 		<div class="select">1.容量：</div>
			 		
			 		<div class="btn-group" data-toggle="buttons" id="capacity">
      
						<label class="btn btn-default">
							<input type="radio" name="capacity" id="option1"> 选项 1
						</label>
					</div>	
		 	  		
					<div class="select">2.外观颜色：</div>
		 	  		<div class="btn-group" data-toggle="buttons" id="color">
						<label class="btn btn-default">
							<input type="radio" name="color" id="option1"> 选项 1
						</label>
					</div>
					<div>	
			 	  		<button id="minus" onclick="minus()">-</button>
			 	  		
			 	  		<input type="text"  onchange="numChange()" id="buyNum" size="5" value=1 />
			 	  		<button id="add" onclick="add()">+</button>
			 	  		<span>库存数量:</span><span id="store"></span>
			 	  		<span>成交量:</span><span id="volume"></span>
		 	  		</div>
		 	  	</div>
		    	<div id="buyBtn">
		    		<button id="btnAddCart" class="btn btn-info btn-lg" onclick="AddCart()">加入购物车</button>
	    			<button id="btnBuyNow" class="btn btn-info btn-lg" onclick="BuyNow()">一键下单</button>
		    	</div>
	    	</div>
		</div>





    </body>
</html> 