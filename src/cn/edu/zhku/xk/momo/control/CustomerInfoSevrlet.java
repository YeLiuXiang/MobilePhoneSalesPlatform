package cn.edu.zhku.xk.momo.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zhku.xk.momo.been.Customer;
import cn.edu.zhku.xk.momo.server.CustomerInforMananger;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class CustomerInfoSevrlet
 */
@WebServlet("/CustomerInfo")
public class CustomerInfoSevrlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerInfoSevrlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String method=(String)request.getParameter("method");
		String account=(String)request.getSession().getAttribute("userName");
		JSONObject result=null;
		if(account==null){
			//没有登录不能访问信息,返回主页
			PrintWriter out=response.getWriter();
			out.print("没有登录请返回主页");
			out.flush();
			out.close();
			
		}
		
		//基本信息的获取
		else if(method==null||method.equals("premodify")){
			CustomerInforMananger cim=new CustomerInforMananger();
			result=cim.getInfo(account);
			
			//Customer customer=(Customer)JSONObject.toBean((JSONObject) result.get("data"), Customer.class);
			request.setAttribute("result", result);
			request.getRequestDispatcher("customerInfoOp/modifycustomerInfo.jsp").forward(request, response);
			
		}
		//进入个人中心
		else if(method.equals("centre")){
			CustomerInforMananger cim=new CustomerInforMananger();
			result=cim.isStore(account);
			
			//Customer customer=(Customer)JSONObject.toBean((JSONObject) result.get("data"), Customer.class);
			request.setAttribute("result", result);
			request.getRequestDispatcher("customerInfoOp/customerCenter.jsp").forward(request, response);
			
		}
		//修改信息
		
		else if(method.equals("modifyBaseInfo")){
			String sex=(String)request.getParameter("sex");
			String nickName=(String)request.getParameter("nickName");
			String email=(String)request.getParameter("email");
			String receiverPhone=(String)request.getParameter("receiverPhone");
			String address=(String)request.getParameter("address");
			String receiver=(String)request.getParameter("receiver");
			Customer customer=new Customer();
			customer.setAccount(account);
			customer.setSex(sex);
			customer.setNickName(nickName);
			customer.setEmail(email);
			customer.setReceiverPhone(receiverPhone);
			customer.setAddress(address);
			customer.setReceiver(receiver);
			CustomerInforMananger cim=new CustomerInforMananger();
			result=cim.modifyBaseInform(customer);
			PrintWriter out=response.getWriter();
			out.println(result);
			out.flush();
			out.close();
		}
		else if(method.equals("modifyPassword")){
			account=(String)request.getSession().getAttribute("userName");
			String newPwd=(String)request.getParameter("newPassword");
			String codeIn=(String)request.getParameter("phoneCode");
			String code=(String)request.getSession().getAttribute("phoneCode");
			CustomerInforMananger cim=new CustomerInforMananger();
			result=cim.modifyPassword(account, newPwd, codeIn, code);
			if("true".equals(result.get("flag"))){
				request.getSession().removeAttribute("phoneCode");
			}
			PrintWriter out=response.getWriter();
			out.println(result);
			out.flush();
			out.close();
		}
		else if(method.equals("modifyPhone")){
			account=(String)request.getSession().getAttribute("userName");
			String newPhone=(String)request.getSession().getAttribute("newPhone");
			String newPhoneIn=(String)request.getParameter("newPhone");
			String codeIn=(String)request.getParameter("phoneCode");
			String code=(String)request.getSession().getAttribute("phoneCode");
			String newCodeIn=(String)request.getParameter("newPhoneCode");
			String newCode=(String)request.getSession().getAttribute("newPhoneCode");
			CustomerInforMananger cim=new CustomerInforMananger();
			result=cim.modifyPhone(account, newPhoneIn, newPhoneIn, newCodeIn, newCode, codeIn, code);
			if("true".equals(result.get("flag"))){
				request.getSession().removeAttribute("phoneCode");
				request.getSession().removeAttribute("newPhoneCode");
				request.getSession().removeAttribute("newPhone");
			}
			PrintWriter out=response.getWriter();
			out.println(result);
			out.flush();
			out.close();
		}
		else if(method.equals("checkPhone")){
			account=(String)request.getSession().getAttribute("userName");
			String codeIn=(String)request.getParameter("phoneCode");
			String code=(String)request.getSession().getAttribute("phoneCode");
			if(!codeIn.equals(code)){
				result.put("flag", "false");
				result.put("msg", "验证码错误,请重新输入!");
			}else{
				request.getSession().removeAttribute("phoneCode");
				result.put("flag", "true");
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
