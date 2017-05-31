package cn.edu.zhku.xk.momo.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zhku.xk.momo.been.Customer;
import cn.edu.zhku.xk.momo.dao.CustomerDao;
import cn.edu.zhku.xk.momo.server.Register;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/Register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String method=(String)request.getParameter("method");
		if(method==null){//没有参数时返回注册页面
			request.getRequestDispatcher("/customerInfoOp/register.jsp").forward(request, response);
		}
		else if(method.equals("verifyaccount")){//参数为验证账号时电泳账号验证函数
			verifyAcout(request, response);
		}
		else if(method.equals("register")){//参数为注册时
			register(request, response);
		}
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	/*
	 * 函数说明：异步验证账号信息
	 */
	public void verifyAcout(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String account=(String)request.getParameter("account");
		PrintWriter out = response.getWriter();
		JSONObject resultJson = new JSONObject();
		Register register=new Register();
		if(register.verifyAccount(account)){
			resultJson.put("flag", "true");
		}else{
			resultJson.put("flag", "false");
			resultJson.put("msg", "该用户名已经存在请重新输入！");
		}
		out.print(resultJson);
		out.flush();
		out.close();
	}
	/*
	 * 注册函数
	 */
	public void register(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String account=(String)request.getParameter("account");
		String password=(String)request.getParameter("password");
		String phone = (String)request.getParameter("phone");
		String checkCodeIn=(String)request.getParameter("phoneCode");
		String checkCode=(String)request.getSession().getAttribute("phoneCode");
		request.getSession().removeAttribute("phoneCode");
		JSONObject resultJson = new JSONObject();
		
		String flag="false";
		String msg=null;
		PrintWriter out = response.getWriter();
		if(checkCode==null){
			msg="验证码错误,请重新点击发送验证码！";
		}
		else if(!checkCodeIn.equals(checkCode)){
			msg="验证码输入错误！";
		}
		else{
			Register register=new Register();
			if(!register.verifyAccount(account)){ 
				msg="该账号已被注册！";
			}else{	
				Customer customer=new Customer();
				customer.setAccount(account);
				customer.setPassword(password);
				customer.setPhone(phone);;
				if(!register.register(customer)){
					msg="注册失败,请联系管理员！";
				}else{
					flag="true";
					msg="注册成功!";
				}
			}
			
		}
		resultJson.put("flag", flag);
		resultJson.put("msg", msg);
		out.println(resultJson);
		out.flush();
		out.close();
	}
}
