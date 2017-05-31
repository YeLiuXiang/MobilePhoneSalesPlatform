<%@page import="net.sf.json.JSONObject"%>
<%@page import="net.sf.json.JSONArray"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="cn.edu.zhku.xk.momo.util.MultipleMap"%>
<%@page import="cn.edu.zhku.xk.momo.been.ReceivingInfo"%>
<%@page import="cn.edu.zhku.xk.momo.been.OrderShowInfo"%>
<%@page import="cn.edu.zhku.xk.momo.been.OrderACK"%>
<%@page import="cn.edu.zhku.xk.momo.been.Order"%>
<%@page import="cn.edu.zhku.xk.momo.util.MultipleMap"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>


<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<title>订单确认</title>
<link rel="icon" href="${pageContext.request.contextPath}/style/image/favicon.ico"/>
   		<link rel="shortcut icon" href="${pageContext.request.contextPath}/style/image/favicon.ico"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/style/css/bootstrap.min.css" />
		<script src="${pageContext.request.contextPath}/style/js/jquery-3.1.1.js"></script>
		<script src="${pageContext.request.contextPath}/style/js/bootstrap.min.js"></script>
		<script src="${pageContext.request.contextPath}/style/js/ordershow.js"></script>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/style/css/orderShow.css">
</head>


<body>

<%
	JSONObject result=(JSONObject)request.getAttribute("result");
	
	if (result==null){ %>
		
		<script type="text/javascript">
			alert("没有订单信息!即将返回上一页!");
			setTimeout("history.go(-1)", 50); 
		</script>
		<%return;
	} 
	
	List<Order> orderList=(List<Order>)JSONArray.toList(JSONArray.fromObject(result.get("orderList")),Order.class);
	MultipleMap<OrderShowInfo> orderMap=MultipleMap.toMultipleMap(result.getString("orderMap"),new OrderShowInfo());
	
	
	
%>


	
<div id="bodyDiv" >
	<div id ="goodsTable">
		<div class="row head">
				<div class="col-md-1 col-1">商品信息</div>
				<div class="col-md-1 col-2">商品分类</div>
				<div class="col-md-1 col-3">单价</div>
				<div class="col-md-1 col-4">数量</div>
				<div class="col-md-1 col-5">金额</div>
				<div class="col-md-1 col-6">订单状态</div>
				<div class="col-md-1 col-7">评价</div>
		</div>

<%	float totol=0;
	for(int i=0;i<orderList.size();i++){
		Order order=orderList.get(i);
		int orderId=order.getOrderId();
	 List<OrderShowInfo> orderShowList=orderMap.getValue(String.valueOf(orderId));
	%>
	
	<div class="store">店铺:<a href=""><%=orderShowList.get(0).getStoreName()%></a>
	</div>
	
	<!-- 店铺名称和连接 -->
	
	<div class="storeGoods"><!-- 店铺div -->
	<%int k=0;
	for(OrderShowInfo orderShowInfo : orderShowList){
		totol+=orderShowInfo.getPrice()*orderShowInfo.getNumber();
	%>
			<!-- 购物车项 -->
			<div class="row data" ><!-- 行开头 -->
				<div class="col-md-1 col-1" ><!--列开始-->
					<div style="display:none" name="orderId"><%=orderShowInfo.getOrderId() %></div>
					<span></span><!-- 不能删除 用来控制元素位置 -->
					<a href="">
						<img src="${pageContext.request.contextPath}/resource/<%=orderShowInfo.getPicAddress()%>"    height="96" width="128"/>
						<div style="margin-left: 140px;margin-top: -80px;"><%=orderShowInfo.getGoodsName()+"  "+orderShowInfo.getTitle() %></div>
					</a> 
					
				</div><!--列结束-->
				<div class="col-md-1 col-2" ><!--列开始-->
				<span></span><!-- 不能删除 用来控制元素位置 -->
					<label>
						<%=orderShowInfo.getColor()+"" +orderShowInfo.getCapacity() %>
					</label>
					
				</div><!--列结束-->
				<div class="col-md-1 col-3"><!--列开始-->
					<span></span><!-- 不能删除 用来控制元素位置 -->
					<label name="singlePrice"><%=orderShowInfo.getPrice() %></label> 
					
				</div><!--列结束-->
				
				<div class="col-md-1 col-4"><!--列开始-->
					<span></span><!-- 不能删除 用来控制元素位置 -->
					<lebel><%=orderShowInfo.getNumber() %></label>
					
				</div><!--列结束-->	
				
				
				<div class="col-md-1 col-5"><!--列开始-->
					<label name="price"><%=orderShowInfo.getPrice()*orderShowInfo.getNumber()  %> </label> 
					<span></span><!-- 不能删除 用来控制元素位置 -->
				</div><!--列结束-->
				
				<div class="col-md-1 col-6"><!--列开始-->
					
					<%if(0==k++){
						if(order.getStatus().equals("waitToPay")){
							%><span></span><!-- 不能删除 用来控制元素位置 -->
							<div>
							<label>订单未支付</label><br>
							<input type="button" class="btn btn-default"value="支付" name="pay" onclick="pay(this)"/>
							<br><input type="button" class="btn btn-default"value="取消" name="cancel" onclick="cancel(this)"/>
							</div>
							
						<%
						}else if(order.getStatus().equals("waitToSend")){
							%>
							<label>订单未发货</label>
							<span></span><!-- 不能删除 用来控制元素位置 -->
						<%
						}else if(order.getStatus().equals("waitToReceive")){
							%>
							<label>订单已发货,快递单号为:<%=order.getCourierNumber() %></label>
							<input type="button" class="btn btn-default"value="确认收货" name="receive" onclick="receive(this)"/>
							<span></span><!-- 不能删除 用来控制元素位置 -->
							
						<%
						}else if(order.getStatus().equals("received")){
							%>
							<label>订单已完成</label>
							<span></span><!-- 不能删除 用来控制元素位置 -->
						<%
						}else if(order.getStatus().equals("closed")){
							%>
							<label>订单已关闭</label>
							<span></span><!-- 不能删除 用来控制元素位置 -->
						<%
						}

					} %>
				</div><!--列结束-->	
				<div class="col-md-1 col-7"><!--列开始-->
					
					<%if(orderShowInfo.getCommentStatus().equals("notcomment")&&order.getStatus().equals("received")){%>
						<a href="${pageContext.request.contextPath}/Order?method=comment&goodsId=<%=orderShowInfo.getGoodsId()%>">
						<input type="button" class="btn btn-default"value="评价" />
						</a>
						<span></span><!-- 不能删除 用来控制元素位置 -->
					<%}else if(orderShowInfo.getCommentStatus().equals("comment")){%>
						<label>已评价</label>
							<span></span><!-- 不能删除 用来控制元素位置 -->
					<%}%>
				</div><!--列结束-->
			</div><!--行结束-->
		<%}%>
		</div>
	<%}%>
	</div>
	


<div id="payDiv"  class="modal fade"  tabindex="-1" role="dialog"  aria-hidden="true" >
            <div class="centra">
			<label > 请输入手机验证码确认支付</label>
              <div class="payIn">
	               <div class="col-md-8">
	               	 手机验证码:<input type="text" class="code" id="phoneCode" /> 
	               </div>
	                <div class="col-md-3">
	                	<input type="button" class="btn btn-default btn-xs"  id="phoneCodeB" value="获取验证码" onclick="sendCode(this)" /> 
	               </div>
               </div>
 			<div id="buyBtn">
		    		<button  class="btn btn-primary btn-lg" onclick="ackPay()">确认支付</button>
	    			<button  class="btn btn-primary btn-lg" onclick="cancelPay()">取消支付</button>
		    </div>
	 </div>
 </div>

</body>
</html>
