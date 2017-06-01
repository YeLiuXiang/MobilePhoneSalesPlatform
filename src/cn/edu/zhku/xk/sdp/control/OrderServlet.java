package cn.edu.zhku.xk.sdp.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zhku.xk.sdp.service.OrderService;
import cn.edu.zhku.xk.sdp.util.GlobalVariable;

/**
 * 
 * @author 夜留香
 *订单管理
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
       
    public OrderServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		//获取方法名字
//		String servletPath = request.getServletPath();
//			//去掉斜杠和.do
//		int indexOfXie = servletPath.indexOf("/");
//		System.out.println(indexOfXie);
//		String methodName = servletPath.substring(1, servletPath.length()-3);
//		System.out.println("方法名："+methodName);
		//利用反射获取方法
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
	
	protected void OrderQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String account = request.getParameter("account");
		String searchType2 = request.getParameter("searchType");		//查询类型：已发货、未发货  、已签收
		String currentPage2 = request.getParameter("currentpage");
		int searchType = Integer.parseInt(searchType2);
		int currentPage = Integer.parseInt(currentPage2);
		OrderService orderService = new OrderService();
		Object  result=orderService.OrderQuery(searchType, currentPage,account);
		request.setAttribute("Object", result);
		if(searchType==GlobalVariable.NoSendOrderQuery){
			request.getRequestDispatcher("/subpage/OrdersManagement/orderNoSend.jsp").forward(request, response);
		}else if(searchType==GlobalVariable.SendOrderQuery){
			request.getRequestDispatcher("/subpage/OrdersManagement/orderSend.jsp").forward(request, response);
		}else if(searchType==GlobalVariable.ReceiveOrderQuery){
			request.getRequestDispatcher("/subpage/OrdersManagement/orderReceive.jsp").forward(request, response);
		}
	}
	
	protected void OrderQueryById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//根据订单id查询订单
		String account = request.getParameter("account");
		String searchType2 = request.getParameter("searchType");		//查询类型：已发货、未发货  、已签收
		String id2 = request.getParameter("id");
		int searchType = Integer.parseInt(searchType2);
		int id = Integer.parseInt(id2);
		OrderService orderService = new OrderService();
		Object  result=orderService.OrderQueryById(id,searchType,account);
		request.setAttribute("Object", result);
		if(searchType==GlobalVariable.NoSendOrderQuery){
			request.getRequestDispatcher("/subpage/OrdersManagement/orderNoSend.jsp").forward(request, response);
		}else if(searchType==GlobalVariable.SendOrderQuery){
			request.getRequestDispatcher("/subpage/OrdersManagement/orderSend.jsp").forward(request, response);
		}else if(searchType==GlobalVariable.ReceiveOrderQuery){
			request.getRequestDispatcher("/subpage/OrdersManagement/orderReceive.jsp").forward(request, response);
		}
	}
	protected void OrderDetailQueryById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//根据订单id查询订单详情
		String id2 = request.getParameter("id");
		int id = Integer.parseInt(id2);
		String currentPage2 = request.getParameter("currentpage");
		int currentPage = Integer.parseInt(currentPage2);
		OrderService orderService = new OrderService();
		Object  result=orderService.OrderDetailQuery(id, currentPage);
		request.setAttribute("Object", result);
		request.getRequestDispatcher("/subpage/OrdersManagement/orderDetail.jsp").forward(request, response);
	}
	protected void DeliverGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//发货
		String id2 = request.getParameter("id");
		int id = Integer.parseInt(id2);
		OrderService orderService = new OrderService();
		Object  result=orderService.DeliverGoods(id); 
		PrintWriter out = response.getWriter();
		out.print(result);
		return;
	}
	protected void SalesStatistics(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//销售额统计
		String account = request.getParameter("account");
		OrderService orderService = new OrderService();
		Object  result=orderService.SalesStatistics(account); 
		request.setAttribute("Object", result);
		request.getRequestDispatcher("/subpage/SalesStaticsManagement/salesStatics.jsp").forward(request, response);
	}
	protected void SalesQuantityStatistics(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//销售量统计
		String account = request.getParameter("account");
		OrderService orderService = new OrderService();
		Object  result=orderService.SalesQuantityStatistics(account); 
		request.setAttribute("Object", result);
		request.getRequestDispatcher("/subpage/SalesStaticsManagement/salesQuantityStatics.jsp").forward(request, response);
	}
	protected void GoodsSalesRank(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//商品销售排行
		String account =request.getParameter("account");
		
		OrderService orderService = new OrderService();
		Object  result=orderService.GoodsSalesRank(account); 
		request.setAttribute("Object", result);
		request.getRequestDispatcher("/subpage/SalesStaticsManagement/salesQuantityRank.jsp").forward(request, response);
	}
}
