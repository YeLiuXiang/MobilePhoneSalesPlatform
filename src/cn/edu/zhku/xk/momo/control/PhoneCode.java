package cn.edu.zhku.xk.momo.control;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zhku.xk.momo.dao.CustomerDao;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class ImageServlet
 */
@WebServlet("/phoneCode")
public class PhoneCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PhoneCode() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONObject result=new JSONObject();
		String account=(String)request.getSession().getAttribute("userName");
		String newPhone=(String)request.getParameter("newPhone");
		if(account==null&&newPhone==null){
			result.put("flag", "false");
			result.put("msg", "��û�е�¼,���¼!");
		}
		else if(newPhone!=null){
			request.getSession().setAttribute("newPhone", newPhone);
			result.put("phone", newPhone);
			String code=sendPhoneCode(newPhone);
			request.getSession().setAttribute("newPhoneCode",code);
			System.out.println("�ֻ���֤��:"+code);
			result.put("flag", "true");
			String temp=newPhone.substring(0, 3)+"*****"+newPhone.substring(8, 11);
			result.put("msg", "�Ѿ����ֻ�����"+temp+"������֤��,������֤������κ���!");
			
		}else{
			CustomerDao customerDao= new CustomerDao();
			String  phone=customerDao.getPhone(account);
			if(phone==null){
				result.put("flag", "false");
				result.put("msg", "��ȡ�ֻ���ʧ��,����ϵ����Ա!");
			}else{
				result.put("phone", phone);
				String code=sendPhoneCode(phone);
				request.getSession().setAttribute("phoneCode",code);
				System.out.println("�ֻ���֤��:"+code);
				result.put("flag", "true");
				String temp=phone.substring(0, 3)+"*****"+phone.substring(8, 11);
				result.put("msg", "�Ѿ����ֻ�����"+temp+"������֤��,������֤������κ���!");
			}
			
		}
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
	}
	
	public String sendPhoneCode(String phone){
		
		long seed=new Date().getTime();
		Random r=new Random(seed);
		StringBuffer code=new StringBuffer();
		for(int i=0;i<4;i++){
			int index=r.nextInt(10);//�������һ��0-9������
			code.append(index);
		}
		/*
		 * 
		 * ���Ͷ��ŵ�Api
		 */
		return code.toString();
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
