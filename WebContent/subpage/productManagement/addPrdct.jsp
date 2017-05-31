<%@ page language = "java" pageEncoding="UTF-8"  %>
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/core"  prefix ="c" %>
<!DOCTYPE html>
<html>
<c:set var="basePath" scope="request">${pageContext.request.contextPath}</c:set>
	<head>
		<title>添加商品</title>
		<link rel="stylesheet" type="text/css" href="${basePath}/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="${basePath}/css/addPrdct.css"/>
		<script src="${basePath}/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="${basePath}/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="${basePath}/js/goodsAdd.js" type="text/javascript" charset="utf-8"></script>
		<script src="${basePath}/js/jquery-form.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<body>
		<div id="container">
			<form  id="addGoodsForm" enctype="multipart/form-data"  class="navbar-form navbar-left" role="search">
				<div class="form-group">
			  		<span>商品编号：</span>
			    	<p class="form-control">${requestScope.goodsId +1}</p>
			  	</div>
			  	<div class="form-group">
			  		<span>商品名称：</span>
			    	<input id = "name" name = "name"  type="text" class="form-control">
			    	<label class="errorClass" id="nameError">${errors.nameError}</label>
			  	</div>
			  	<div class="form-group">
			  		<span>详情介绍图片地址：</span>
			    	<input id = "imge" name = "imge" type="file">
			  	</div>
			  	<div class="form-group">
			  		<span>标题简介：</span>
			    	<input id = "title" name = "title"  type="text" class="form-control">
			    	<label class="errorClass" id="titleError">${errors.titleError}</label>
			  	</div>
			  	<input id="storeId" name="storeId" type="hidden" value="${requestScope.storeId}">
			  	<input id="goodsId" name="goodsId" type="hidden" value="${requestScope.goodsId+1}">
			  	<table class="table table-bordered">
			  		<thead>
			  			<tr class="success">	<!--标题-->
			  				<th class="text-center">型号</th>
			  				<th class="text-center">颜色</th>
			  				<th class="text-center">库存</th>
			  				<th class="text-center">内存</th>
			  				<th class="text-center">价格</th>
			  				<th class="text-center">图片地址</th>
			  			</tr>
			  		</thead>
			  		<tbody>
			  			<tr class="info">	<!--第1行-->
			  				<td class="text-center"><p class="form-control">${requestScope.modelId +1}</p></td>
			  				<td class="text-center"><input class="form-control"  name = "color" type="text" /></td>
			  				<td class="text-center"><input class="form-control"  name = "number" type="text" /></td>
			  				<td class="text-center"><input class="form-control"  name = "capacity" type="text" /></td>
			  				<td class="text-center"><input class="form-control"  name = "price" type="text" /></td>
			  				<td class="text-center"><input   name = "image" type="file" /></td>
			  			</tr>
			  			<tr class="warning">	<!--第2行-->
			  				<td class="text-center"><p class="form-control">${requestScope.modelId +2}</p></td>
			  				<td class="text-center"><input class="form-control"  name = "color" type="text" /></td>
			  				<td class="text-center"><input class="form-control"  name = "number" type="text" /></td>
			  				<td class="text-center"><input class="form-control"  name = "capacity" type="text" /></td>
			  				<td class="text-center"><input class="form-control"  name = "price" type="text" /></td>
			  				<td class="text-center"><input  name = "image2" type="file" /></td>
			  			</tr>
			  			<tr  class="info">	<!--第3行-->
			  				<td class="text-center"><p class="form-control">${requestScope.modelId +3}</p></td>
			  				<td class="text-center"><input class="form-control"  name = "color" type="text" /></td>
			  				<td class="text-center"><input class="form-control"  name = "number" type="text" /></td>
			  				<td class="text-center"><input class="form-control"  name = "capacity" type="text" /></td>
			  				<td class="text-center"><input class="form-control"  name = "price" type="text" /></td>
			  				<td class="text-center"><input  name = "image3" type="file" /></td>
			  			</tr>
			  			<tr class="warning">	<!--第4行-->
			  				<td class="text-center"><p class="form-control">${requestScope.modelId +4}</p></td>
			  				<td class="text-center"><input class="form-control"  name = "color" type="text" /></td>
			  				<td class="text-center"><input class="form-control"  name = "number" type="text" /></td>
			  				<td class="text-center"><input class="form-control"  name = "capacity" type="text" /></td>
			  				<td class="text-center"><input class="form-control"  name = "price" type="text" /></td>
			  				<td class="text-center"><input   name = "image4" type="file" /></td>
			  			</tr>
			  			<tr  class="info">	<!--第5行-->
			  				<td class="text-center"><p class="form-control">${requestScope.modelId +5}</p></td>
			  				<td class="text-center"><input class="form-control"  name = "color" type="text" /></td>
			  				<td class="text-center"><input class="form-control"  name = "number" type="text" /></td>
			  				<td class="text-center"><input class="form-control"  name = "capacity" type="text" /></td>
			  				<td class="text-center"><input class="form-control"  name = "price" type="text" /></td>
			  				<td class="text-center"><input   name = "image5" type="file" /></td>
			  			</tr>
			  		</tbody>
			  	</table>
			  	
			  	<div class="btnDiv">
				  	<input type="button"  onclick="GoodsAdd()" class="btn btn-default" value="确&nbsp;&nbsp;&nbsp;定">
				  	<!--<input type="submit" class="btn btn-default" value="取&nbsp;&nbsp;&nbsp;消">-->
				  	<button  type="reset" class="btn btn-default">重&nbsp;&nbsp;&nbsp;置</button>
				</div>
			</form>
			
		</div>
		
	</body>
</html>
