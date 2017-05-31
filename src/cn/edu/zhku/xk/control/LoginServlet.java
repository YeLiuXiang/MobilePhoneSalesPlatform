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
import javax.servlet.http.HttpSession;

import cn.edu.zhku.xk.service.ManagerService;
import net.sf.json.JSONObject;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("管理平台登陆");
		try {
			String methodName = request.getParameter("method");
			System.out.println("method:"+methodName);
				Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
		
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
	protected void Login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String account = request.getParameter("account");
		String pass = request.getParameter("pass");
		System.out.println(pass);
		String checkCode = request.getParameter("validateCode");
		String checkCodeSess = request.getSession().getAttribute("checkcode").toString();
		ManagerService manager = new ManagerService();
		JSONObject json=	manager.ManVeriITel(account, pass,checkCodeSess,checkCode);
		if(json.getInt("result")==2){
			request.getSession().setAttribute("account", account);
		}
		PrintWriter out = response.getWriter();
		out.print(json);
	}
	protected void Logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().removeAttribute("userId");
		response.sendRedirect(request.getContextPath()+"/login.jsp");
	}

}
