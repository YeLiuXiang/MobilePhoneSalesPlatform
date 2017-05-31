<%@page import="net.sf.json.JSONObject"%>
<%@page import="net.sf.json.JSONArray"%>
<%@page import="java.util.List"%>
<%@page import="cn.edu.zhku.xk.momo.been.Comment"%>
<%@page import="cn.edu.zhku.xk.momo.been.ShopCarShow"%>
<%@page import="cn.edu.zhku.xk.momo.util.MultipleMap"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>


<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<title>购物车</title>
<link rel="icon" href="${pageContext.request.contextPath}/style/image/favicon.ico"/>
   		<link rel="shortcut icon" href="${pageContext.request.contextPath}/style/image/favicon.ico"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/style/css/bootstrap.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/style/css/cart.css">
		<script src="${pageContext.request.contextPath}/style/js/jquery-3.1.1.js"></script>
		<script src="${pageContext.request.contextPath}/style/js/bootstrap.min.js"></script>
		<script src="${pageContext.request.contextPath}/style/js/cart.js"></script>
		
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
	
	MultipleMap<ShopCarShow> shopCarMap=MultipleMap.toMultipleMap( result.getString("shopCarMap"),new ShopCarShow());
	List<String> storeList=shopCarMap.getKey();
%>


	
<div id="bodyDiv" >

	<div id ="goodsTable">
		<div class="row head">
				<div class="col-md-1 select"><input type="checkbox" id="selectAll" onclick="selectAll()"/>全选</div>
				<div class="col-md-3">商品信息</div>
				<div class="col-md-1">商品分类</div>
				<div class="col-md-2">单价</div>
				<div class="col-md-1">数量</div>
				<div class="col-md-1">金额</div>
		</div><!--行结束-->
<%	
	for(int i=0;i<storeList.size();i++){
	String storeName=storeList.get(i);
	 List<ShopCarShow> shopCarShowList=shopCarMap.getValue(storeName);
	%>
	
	<div class="store">店铺:<a href=""><%=storeName%></a></div>
	<!-- 店铺名称和连接 -->
	<div class="storeGoods"><!-- 店铺div -->
	<%for(ShopCarShow car:shopCarShowList){
		if("unshelf".equals(car.getGoodsStatus())){
			
		}
	%>
			<!-- 购物车项 -->
			<div class="row data" ><!-- 行开头 -->
			
			
				<div class="col-md-1 select" ><!--列开始-->
					<input type="checkbox" name="select"  onclick="selectGoods()"/>
					<span></span><!-- 不能删除 用来控制元素位置 -->
				</div><!--列结束-->
				
				
				<div class="col-md-3" style="text-align:left;"><!--列开始-->
					<div style="display:none" name="modelId"><%=car.getGoodsModelId() %></div>
					<span></span><!-- 不能删除 用来控制元素位置 -->
					<a href="">
						<img src="${pageContext.request.contextPath}/resource/<%=car.getPicAddress()%>"  alt="<%=car.getGoodsName() %>"  height="96" width="128"/>
						<div style="margin-left: 120px;margin-top: -80px;"><%=car.getGoodsName()+"  "+car.getTitle() %></div>
					</a>
				</div><!--列结束-->
					
				
				<div class="col-md-1"><!--列开始-->
					<span></span><!-- 不能删除 用来控制元素位置 -->
					<label><%=car.getColor()+" "+car.getCapacity() %>
					</label>
				</div><!--列结束-->
				
				
				<div class="col-md-1"><!--列开始-->
					<span></span><!-- 不能删除 用来控制元素位置 -->
					<label name="singlePrice"><%=car.getPrice() %></label> 
				</div><!--列结束-->
				
				
				<div class="col-md-2"><!--列开始-->
					<input class="MinusBtn" type="button" name="minus" value="-" onclick="minus(this)" />
					<input type="text" size="5" name="goodsNum" value="<%=car.getShopCarnumber() %>" onchange="numChange(this)"/>
					<input class="AddBtn" type="button" name="add" value="+" onclick="add(this)"/>
					<span></span><!-- 不能删除 用来控制元素位置 -->
					<div style="margin-top: -45px;">
						库存:<lebel name="store"><%=car.getStorenumber() %></lebel>
					</div>
				</div><!--列结束-->
				
					
				<div class="col-md-1"><!--列开始-->
					<label name="price"><%=car.getPrice()*car.getShopCarnumber()  %> </label> 
					<span></span><!-- 不能删除 用来控制元素位置 -->
				</div><!--列结束-->
				
				<div class="col-md-1"><!--列开始-->
					<input type="button" class="btn btn-default"value="删除" name="delete" onclick="remove(this)"/>
				<span></span><!-- 不能删除 用来控制元素位置 -->
				</div><!--列结束-->
				
			</div><!--行结束-->
		<%}%>
		</div><!-- 店铺div -->
	<%}%>

</div>


		<div id="result">
			
				<div>
					合计: <label id="totel">0.00</span>元
				</div>
				<input type="button" class="btn btn-primary btn-lg" id="account" value="结算" onclick="account()" />
			


		</div>
	
</div>


</body>
</html>
