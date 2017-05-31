package cn.edu.zhku.xk.momo.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zhku.xk.momo.server.Login;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String  method=(String)request.getParameter("method");
		if(method==null||!method.equals("login")){
			request.getRequestDispatcher("customerInfoOp/login.jsp").forward(request, response);
		}else {
			String account=(String )request.getParameter("account");
			String password=(String)request.getParameter("password");
			String codeIn=(String)request.getParameter("checkCode");
			String code=(String)request.getSession().getAttribute("checkCode");
			request.getSession().removeAttribute("checkCode");
			Login login=new Login();
			JSONObject result=login.login(account, password, codeIn, code);
			if("true".equals(result.get("flag"))){
				request.getSession().setAttribute("userName", account);
				request.getSession().setAttribute("account", account);
			}
			PrintWriter out=response.getWriter();
			out.println(result);
			out.flush();
			out.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
}
