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

import cn.edu.zhku.xk.sdp.service.StoreService;

@WebServlet("/StoreServlet2")
public class StoreServlet extends HttpServlet {
       
    public StoreServlet() {
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
	protected void StoreMessageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//店铺信息查询
	String account = request.getParameter("account");
		StoreService storeService =new StoreService();
		Object result=	storeService.StoreMessageQuery(account);
		request.setAttribute("Object", result);
		request.getRequestDispatcher("subpage/StoreManagement/messageDisplay.jsp").forward(request, response);
	}
	
	protected void StoreMessageModifyPre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//跳转到店铺信息修改页面，在此之前进行一系列动作
		String account = request.getParameter("account");
		
		StoreService storeService =new StoreService();
	Object result=	storeService.MessageModifyQuery( account);
	request.setAttribute("Object", result);
	request.getRequestDispatcher("subpage/StoreManagement/messageModify.jsp").forward(request, response);
	}
	protected void StoreMessageModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//店铺信息修改
		String account = request.getParameter("account");
		String imgAddr = request.getParameter("imgAddr");
		String customerService = request.getParameter("customerService");

		StoreService storeService =new StoreService();
		Object result = storeService.MessageModify( account,customerService,imgAddr);
		PrintWriter out = response.getWriter();
		out.print(result);
	}
	protected void WalletTopUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String moneyRadio = request.getParameter("radio");
		String account = request.getParameter("account");
		StoreService storeService =new StoreService();
		Object result = storeService.WalletTopUp(Float.parseFloat(moneyRadio),account);
		PrintWriter out = response.getWriter();
		out.print(result);
	}
}
