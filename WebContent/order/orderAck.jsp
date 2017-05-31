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
<title>订单确认</title>
<link rel="icon" href="${pageContext.request.contextPath}/style/image/favicon.ico"/>
   		<link rel="shortcut icon" href="${pageContext.request.contextPath}/style/image/favicon.ico"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/style/css/bootstrap.min.css" />
		<script src="${pageContext.request.contextPath}/style/js/jquery-3.1.1.js"></script>
		<script src="${pageContext.request.contextPath}/style/js/bootstrap.min.js"></script>
		<script src="${pageContext.request.contextPath}/style/js/ackOrder.js"></script>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/style/css/ackOrder.css">
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
	request.getSession().setAttribute("orderACKMap",orderACKMap.toJSONObject());
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
	<div class="receivingInfo">	
		<form class="form-horizontal" role="form">
			<div id="errText"></div>
			<div class="form-group">
        		<label class="col-sm-4 control-label">收货人</label>
                <div class="col-sm-6">
                	<input type="text" class="form-control" value="<%= receivingInfo.getReceiver()%>" id="receiver">
                </div>
     		</div>
     		<div class="form-group">
        		<label class="col-sm-4 control-label">联系方式</label>
                <div class="col-sm-6">
                	<input type="tel" class="form-control" value="<%= receivingInfo.getReceiverPhone()%>" id="receiverPhone">
                </div>
     		</div>
			<div class="form-group">
        		<label class="col-sm-4 control-label">收货地址</label>
                <div class="col-sm-6">
                	<input type="text" class="form-control" value="<%= receivingInfo.getAddress()%>" id="address">
                </div>
     		</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<input type="reset" class="btn btn-default " value="重置" >
				</div>
			</div>
    	</form>
	
</div>
<div id="result">
	<div>
		合计: <label id="totel"><%=totol %></lebel>元
	</div>
	<input type="button" class="btn btn-primary btn-lg"    value="确认下单" onclick="ackOrder()" />
	</div>
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
		    		<button  class="btn btn-primary btn-lg" onclick="pay()">确认支付</button>
	    			<button " class="btn btn-primary btn-lg" onclick="cancelPay()">取消支付</button>
		    </div>
	 </div>
 </div>

</body>
</html>
