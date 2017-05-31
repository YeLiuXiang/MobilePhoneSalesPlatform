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
		if(method==null){//û�в���ʱ����ע��ҳ��
			request.getRequestDispatcher("/customerInfoOp/register.jsp").forward(request, response);
		}
		else if(method.equals("verifyaccount")){//����Ϊ��֤�˺�ʱ��Ӿ�˺���֤����
			verifyAcout(request, response);
		}
		else if(method.equals("register")){//����Ϊע��ʱ
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
	 * ����˵�����첽��֤�˺���Ϣ
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
			resultJson.put("msg", "���û����Ѿ��������������룡");
		}
		out.print(resultJson);
		out.flush();
		out.close();
	}
	/*
	 * ע�ắ��
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
			msg="��֤�����,�����µ��������֤�룡";
		}
		else if(!checkCodeIn.equals(checkCode)){
			msg="��֤���������";
		}
		else{
			Register register=new Register();
			if(!register.verifyAccount(account)){ 
				msg="���˺��ѱ�ע�ᣡ";
			}else{	
				Customer customer=new Customer();
				customer.setAccount(account);
				customer.setPassword(password);
				customer.setPhone(phone);;
				if(!register.register(customer)){
					msg="ע��ʧ��,����ϵ����Ա��";
				}else{
					flag="true";
					msg="ע��ɹ�!";
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
