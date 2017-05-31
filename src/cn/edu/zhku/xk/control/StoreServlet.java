package cn.edu.zhku.xk.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zhku.xk.service.StoreService;

@WebServlet("/StoreServlet")
public class StoreServlet extends HttpServlet {
       
    

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
		String idOrName = request.getParameter("id");

		StoreService storeService =new StoreService();
		Object result = storeService.MessageQuery( idOrName);
		request.setAttribute("Object", result);
		request.getRequestDispatcher("/subpage/StoreManagement/messageQuery2.jsp").forward(request, response);
	}

	protected void StoreFreeze(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//店铺冻结
		String id = request.getParameter("id");
		StoreService storeService =new StoreService();
		Object result=storeService.StoreFreeze(Integer.parseInt(id));
		PrintWriter out = response.getWriter();
		out.print(result);
	}
	protected void StoreUnFreeze(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//店铺解冻
		String id = request.getParameter("id");
		StoreService storeService =new StoreService();
		Object result=storeService.StoreUnFreeze(Integer.parseInt(id));
		PrintWriter out = response.getWriter();
		out.print(result);
	}
	
}
