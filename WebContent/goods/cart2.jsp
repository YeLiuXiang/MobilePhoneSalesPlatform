<%@page import="net.sf.json.JSONObject"%>
<%@page import="net.sf.json.JSONArray"%>
<%@page import="java.util.List"%>
<%@page import="cn.edu.zhku.xk.momo.been.ReceivingInfo"%>
<%@page import="cn.edu.zhku.xk.momo.been.OrderACK"%>
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
	JSONObject result=(JSONObject) request.getSession().getAttribute("result");
	
	if (result==null){ %>
		
		<script type="text/javascript">
			alert("没有订单信息!即将返回上一页!");
			setTimeout("history.go(-1)", 50); 
		</script>
		<%return;
	} 
	request.getSession().removeAttribute("result");
	String flag =(String)result.getString("flag");
	MultipleMap<OrderACK> orderACKMap=MultipleMap.toMultipleMap(result.getString("orderACKMap"),new OrderACK());
	List<String> storeList=orderACKMap.getKey();
	ReceivingInfo receivingInfo=(ReceivingInfo)JSONObject.toBean(result.getJSONObject("receivingInfo"),ReceivingInfo.class);
%>


	
<div id="bodyDiv" >
	<div id ="goodsTable">
		<div class="row head">
				<div class="col-md-3">商品信息</div>
				<div class="col-md-1">商品分类</div>
				<div class="col-md-1">单价</div>
				<div class="col-md-2">数量</div>
				<div class="col-md-1">金额</div>
		</div>

<%	float totol=0;
	for(int i=0;i<storeList.size();i++){
	String storeName=storeList.get(i);
	 List<OrderACK> orderACKList=orderACKMap.getValue(storeName);
	%>
	<div class=""><!-- 行开头 -->
	<div class="store">店铺:<a href=""><%=storeName%></a>
	</div>
	</div><!--行结束-->
	<!-- 店铺名称和连接 -->
	
	<div class="storeGoods"><!-- 店铺div -->
	<%for(OrderACK orderACK:orderACKList){
		totol+=orderACK.getPrice()*orderACK.getNumber();
	%>
			<!-- 购物车项 -->
			<div class="row data" ><!-- 行开头 -->
				<div class="col-md-3" style="text-align:left;"><!--列开始-->
					<div style="display:none" name="modelId"><%=orderACK.getGoodsModelId() %></div>
					<span></span><!-- 不能删除 用来控制元素位置 -->
					<a href="">
						<img src="${pageContext.request.contextPath}/resource/<%=orderACK.getPicAddress()%>"  alt="<%=orderACK.getGoodsName() %>"  height="96" width="128"/>
						<div style="margin-left: 120px;margin-top: -80px;"><%=orderACK.getGoodsName()+"  "+orderACK.getTitle() %></div>
					</a>
					
				</div><!--列结束-->
				<div class="col-md-1"><!--列开始-->
				<span></span><!-- 不能删除 用来控制元素位置 -->
					<label>
						<%=orderACK.getColor()+"" +orderACK.getCapacity() %>
					</label>
					
				</div><!--列结束-->
				<div class="col-md-1"><!--列开始-->
					<span></span><!-- 不能删除 用来控制元素位置 -->
					<label name="singlePrice"><%=orderACK.getPrice() %></label> 
					
				</div><!--列结束-->
				
				<div class="col-md-2"><!--列开始-->
					<span></span><!-- 不能删除 用来控制元素位置 -->
					<lebel><%=orderACK.getNumber() %></label>
					
				</div><!--列结束-->	
				
				
				<div class="col-md-1"><!--列开始-->
					<label name="price"><%=orderACK.getPrice()*orderACK.getNumber()  %> </label> 
					<span></span><!-- 不能删除 用来控制元素位置 -->
				</div><!--列结束-->
			</div><!--行结束-->
		<%}%>
		</div>
	<%}%>
	</div>

<div id="result">
	<div>
		合计: <label id="totel"><%=totol %></lebel>元
	</div>
	<input type="button" class="btn btn-primary btn-lg" value="确认下单" onclick="ackOrder()" />
	</div>
</div>



</body>
</html>
