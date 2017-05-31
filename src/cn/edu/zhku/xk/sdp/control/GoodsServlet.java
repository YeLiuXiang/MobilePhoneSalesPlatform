package cn.edu.zhku.xk.sdp.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.File;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import cn.edu.zhku.xk.sdp.been.GoodsBase;
import cn.edu.zhku.xk.sdp.been.GoodsExtend;
import cn.edu.zhku.xk.sdp.been.GoodsIdModelIdStoreId;
import cn.edu.zhku.xk.sdp.service.GoodsService;
import cn.edu.zhku.xk.sdp.util.GlobalVariable;


@WebServlet("/GoodsServlet")
public class GoodsServlet extends HttpServlet {
       
    public GoodsServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//获取方法名字
//		String servletPath = request.getServletPath();
//			//去掉斜杠和.do
//		String methodName = servletPath.substring(1, servletPath.length()-3);
//		System.out.println("方法名："+methodName);
//		//利用反射获取方法
		try {
		String methodName = request.getParameter("method");
			Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
			System.out.println("method:"+methodName);
			//执行相应的方法
			method.invoke(this, request,response);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void  OnShelfQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String account = request.getParameter("account");
		String currentpage =  request.getParameter("currentpage");
		int currentpage2 = Integer.parseInt(currentpage);
		GoodsService goodsManage = new GoodsService();
		Object result = goodsManage.GoodsQuery(GlobalVariable.OnShelfQuery, currentpage2,account);
		request.setAttribute("Object", result);
		request.getRequestDispatcher("/subpage/productManagement/beenShelves.jsp").forward(request, response);
		
		
	}
	protected void  OffShelfQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String account = request.getParameter("account");
		String currentpage =  request.getParameter("currentpage");
		int currentpage2 = Integer.parseInt(currentpage);
		GoodsService goodsManage = new GoodsService();
		Object result = goodsManage.GoodsQuery(GlobalVariable.OffShelfQuery, currentpage2,account);
		request.setAttribute("Object", result);
		request.getRequestDispatcher("/subpage/productManagement/notYetShelf.jsp").forward(request, response);
		
	}
	protected void GoodsQueryByIdOrName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//根据商品编号或商品名称查商品
		String account = request.getParameter("account");
		String searchType2 = request.getParameter("searchType");
		int searchType = Integer.parseInt(searchType2);
		String id2 = request.getParameter("id");
		PrintWriter out = response.getWriter();
		GoodsService goodsService = new GoodsService();

		Object	 result=	goodsService.GoodsQueryByIdOrName(searchType, id2,account);
		request.setAttribute("Object", result);
		if(searchType==GlobalVariable.OffShelfQuery){
			request.getRequestDispatcher("/subpage/productManagement/notYetShelf.jsp").forward(request, response);
		}else if(searchType==GlobalVariable.OnShelfQuery){
			request.getRequestDispatcher("/subpage/productManagement/beenShelves.jsp").forward(request, response);

		}


	}
	
	protected void GoodsOnShelf(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//商品上架
		
		String id2 = request.getParameter("id");
		int id = Integer.parseInt(id2);
		GoodsService goodsService = new GoodsService();	
		boolean result =	goodsService.GoodsUpShelf(id);
		PrintWriter out = response.getWriter();
		out.print(result);
		return;
	}
	
	protected void GoodsOffShelf(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//商品下架
		String id2 = request.getParameter("id");
		int id = Integer.parseInt(id2);
		GoodsService goodsService = new GoodsService();	
		boolean result =	goodsService.GoodsOffShelf(id);
		PrintWriter out = response.getWriter();
		out.print(result);
		return;
	}
	protected void GoodsAddPre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//跳转到商品添加页面 在此之前要先获取一些参数
		String account = request.getParameter("account");
		GoodsService goodsService = new GoodsService();	
		GoodsIdModelIdStoreId g=	goodsService.GoodsAddPre(account);
		request.setAttribute("storeId", g.getStoreId());
		request.setAttribute("modelId", g.getModelId());
		request.setAttribute("goodsId", g.getGoodsId());
		
		request.getRequestDispatcher("/subpage/productManagement/addPrdct.jsp").forward(request, response);

	}
	protected void AddGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//新增商品
		SmartUpload upload = new SmartUpload();
		upload.initialize(this.getServletConfig(), request, response);
		try {
			upload.upload("UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String gID = upload.getRequest().getParameter("goodsId");
		String gName = upload.getRequest().getParameter("name");
		String gTitle = upload.getRequest().getParameter("title");
		String storeId = upload.getRequest().getParameter("storeId");

	//	String mID[] = upload.getRequest().getParameterValues("mID");
		String color[] = upload.getRequest().getParameterValues("color");
		String capacity[] = upload.getRequest().getParameterValues("capacity"); // 容量
		String number[] = upload.getRequest().getParameterValues("number");
		String price[] = upload.getRequest().getParameterValues("price");
		
		System.out.println(gID);
	//	System.out.println(mID[0]);
		
		ArrayList<GoodsExtend> goodsList = new ArrayList<GoodsExtend>();
		GoodsBase goodsBase = null;
		boolean flag = false;
		for (int i = 0; i < upload.getFiles().getCount(); i++) {
			flag = true;
			String fileName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			File imageFile = upload.getFiles().getFile(i);

			String fileExtend = imageFile.getFileExt(); // 获取文件后缀格式
			String fileAllName = fileName + "." + fileExtend;
			
			if (imageFile.isMissing()) // 若文件不存在则继续
				continue;
			if (i == 0) {
				goodsBase = new GoodsBase(Integer.parseInt(gID), gName,Integer.parseInt( storeId),new Date(), fileAllName, gTitle, "unshelf");
			} else {
				GoodsExtend gExt = new GoodsExtend();
				i = i-1;
				goodsBase.setImgAddr(fileAllName);
//				gExt.setModelId(Integer.parseInt(mID[i]));
				gExt.setGoodsId(Integer.parseInt(gID));
				gExt.setPicAddress(fileAllName);
				gExt.setColor(color[i]);
				gExt.setCapacity(capacity[i]);
				gExt.setNumber(Integer.parseInt(number[i]));
				gExt.setVolume(0);
				gExt.setPrice(Float.parseFloat(price[i]));
				goodsList.add(gExt);
				i = i+1;
			}

			try {
				imageFile.saveAs(
						"F:/工作区间/java_eclipse_outfileEE/Moko_Management/WebContent/imageFile/" + fileName + "." + fileExtend,
						imageFile.SAVEAS_AUTO);
				String filePath = this.getServletConfig().getServletContext().getRealPath("/");
				System.out.println(filePath);
				imageFile.saveAs("/imageFile/" + fileName + "." + fileExtend, imageFile.SAVEAS_VIRTUAL);
			} catch (SmartUploadException e) {
				e.printStackTrace();
			}

		}
		if(flag){
			GoodsService goodsService = new GoodsService();
			boolean result=	goodsService.GoodsAdd(goodsBase, goodsList);
			PrintWriter out = response.getWriter();
			out.print(result);
			return;
		}
		return ;
	}

	protected void GoodsTitleModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//商品标题修改
		String id =request.getParameter("id");
		String title =request.getParameter("title");
		GoodsService goodsService = new GoodsService();
		Object result = goodsService.GoodsTitleModify(Integer.parseInt(id),title);
		PrintWriter out = response.getWriter();
		out.print(result);
	}
	protected void GoodsModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//商品标题修改
		String id =request.getParameter("id");
		String index2 =request.getParameter("index");
		int index=Integer.parseInt(index2);
		String[] stockNumber =request.getParameterValues("number");
		String[] price =request.getParameterValues("price");
		GoodsService goodsService = new GoodsService();
		Object result = goodsService.GoodsModify(Integer.parseInt(id),Integer.parseInt(stockNumber[index]),Float.parseFloat(price[index]));
		PrintWriter out = response.getWriter();
		out.print(result);
	}
	protected void GoodsDetailQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//商品详情查询
		String id2 =request.getParameter("id");
		int id = Integer.parseInt(id2);
		GoodsService goodsService = new GoodsService();
		Object result = goodsService.GoodsDetailQuery(id);
		request.setAttribute("Object", result);
		request.getRequestDispatcher("/subpage/productManagement/productDetail.jsp").forward(request, response);
	}
	
	
	protected void GoodsNameQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//查询特定店铺下的所有商品名称信息
		String account =request.getParameter("account");
		String searchType =request.getParameter("searchType");
		GoodsService goodsService = new GoodsService();
		Object result = goodsService.GoodsNameQuery(account);
		request.setAttribute("Object", result);
		if(Integer.parseInt(searchType)==GlobalVariable.AddAdvertisingBooth){
			request.getRequestDispatcher("/subpage/BoothManagement/addAdvertisingBooth.jsp").forward(request, response);
		}else if(Integer.parseInt(searchType)==GlobalVariable.AddCommodityBooth){
			request.getRequestDispatcher("/subpage/BoothManagement/addCommodityBooth.jsp").forward(request, response);
		}
	}
}
