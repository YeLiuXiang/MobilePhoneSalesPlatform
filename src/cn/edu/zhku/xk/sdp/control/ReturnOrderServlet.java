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
import cn.edu.zhku.xk.sdp.service.ReturnOrderService;
import cn.edu.zhku.xk.sdp.util.GlobalVariable;


@WebServlet("/ReturnOrderServlet")
public class ReturnOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReturnOrderServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
	protected void ReturnOrderQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//退换货申请表查询
		String account = request.getParameter("account");
		String searchType2 = request.getParameter("searchType");
		int searchType = Integer.parseInt(searchType2);
	
		String currentPage2 = request.getParameter("currentpage");
		int currentPage = Integer.parseInt(currentPage2);
		ReturnOrderService returnOrderService = new ReturnOrderService();
		 Object  result=	 returnOrderService.RetuExchQuery(currentPage,searchType,account);
			request.setAttribute("Object", result);
		if(searchType==0){
			request.getRequestDispatcher("/subpage/OrdersManagement/returnNoOper.jsp").forward(request, response);
		}else if(searchType==1){
			request.getRequestDispatcher("/subpage/OrdersManagement/returnAgree.jsp").forward(request, response);
		}else if(searchType==2){
			request.getRequestDispatcher("/subpage/OrdersManagement/returnRefuse.jsp").forward(request, response);
		}
	}
	protected void ReturnOrderQueryById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//根据id查询退换货申请表
		String account = request.getParameter("account");
		String searchType2 = request.getParameter("searchType");
		int searchType = Integer.parseInt(searchType2);
		String id2 = request.getParameter("id");
		int id = Integer.parseInt(id2);
			ReturnOrderService returnOrderService = new ReturnOrderService();
		 Object  result=	 returnOrderService.RetuExchQueryById(id,searchType,account);
			request.setAttribute("Object", result);
		if(searchType==0){
			request.getRequestDispatcher("/subpage/OrdersManagement/returnNoOper.jsp").forward(request, response);
		}else if(searchType==1){
			request.getRequestDispatcher("/subpage/OrdersManagement/returnAgree.jsp").forward(request, response);
		}else if(searchType==2){
			request.getRequestDispatcher("/subpage/OrdersManagement/returnRefuse.jsp").forward(request, response);
		}
		
	}
	protected void AgreeReturn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//同意退换货
		String id2 = request.getParameter("id");
		int id = Integer.parseInt(id2);

		OrderService orderService = new OrderService();
		boolean result = orderService.RetuExchOper(GlobalVariable.AgreeReturnOrder, id);
		PrintWriter out = response.getWriter();
		out.print(result);
	}
	protected void RefuseReturn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//拒绝退换货
		String id2 = request.getParameter("id");
		int id = Integer.parseInt(id2);

		OrderService orderService = new OrderService();
		boolean result = orderService.RetuExchOper(GlobalVariable.RefuseReturnOrder, id);
		PrintWriter out = response.getWriter();
		out.print(result);
	}
	
}
