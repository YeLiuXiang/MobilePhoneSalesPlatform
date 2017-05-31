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

import cn.edu.zhku.xk.service.BoothService;

@WebServlet("/BoothServlet")
public class BoothServlet extends HttpServlet {
    public BoothServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String methodName = request.getParameter("method");
				Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
				System.out.println("method:"+methodName);
				//ִ����Ӧ�ķ���
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
	protected void AdvertingBoothQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���չλ��ѯ��չʾ�У�
		
		BoothService boothService = new BoothService();
	Object result=	boothService.AdvertingBoothQuery();
	request.setAttribute("Object", result);
	request.getRequestDispatcher("/subpage/BoothManagement/advertisingBoothing.jsp").forward(request, response);
	}
	protected void AdvertingBoothApplyQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���չλ�����ѯ�������У�
		BoothService boothService = new BoothService();
		String currentPage2 = request.getParameter("currentpage");
		int currentPage = Integer.parseInt(currentPage2);
	Object result=	boothService.AdvertingBoothApplyQuery(currentPage);
	request.setAttribute("Object", result);
	request.getRequestDispatcher("/subpage/BoothManagement/advertisingBooth.jsp").forward(request, response);
	}
	protected void CommodityBoothQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��Ʒչλ��ѯ��չʾ�У�
		String currentPage2 = request.getParameter("currentpage");
		int currentPage = Integer.parseInt(currentPage2);
		BoothService boothService = new BoothService();
		Object result=	boothService.CommodityBoothQuery(currentPage);
		request.setAttribute("Object", result);
	request.getRequestDispatcher("/subpage/BoothManagement/commodityBoothing.jsp").forward(request, response);
	}
	protected void CommodityBoothApplyQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��Ʒչλ�����ѯ�������У�
		BoothService boothService = new BoothService();
		String currentPage2 = request.getParameter("currentpage");
		int currentPage = Integer.parseInt(currentPage2);
	Object result=	boothService.CommodityBoothApplyQuery(currentPage);
	request.setAttribute("Object", result);
	request.getRequestDispatcher("/subpage/BoothManagement/commodityBooth.jsp").forward(request, response);
	}
	protected void AdvertingBoothModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���չλ����
		String id2 = request.getParameter("id");
		int id = Integer.parseInt(id2);
		BoothService boothService = new BoothService();
		Object result=	boothService.AdvertingBoothModify( id);
			PrintWriter out = response.getWriter();
			out.print(result);
	}
	protected void CommodityBoothModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���չλ����
		String id2 = request.getParameter("id");
		int id = Integer.parseInt(id2);
		BoothService boothService = new BoothService();
		Object result=	boothService.CommodityBoothModify( id);
			PrintWriter out = response.getWriter();
			out.print(result);
	}
	protected void AdvertingBoothAgree(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���չλ����ͨ��
		String id2 = request.getParameter("id");
		int id = Integer.parseInt(id2);
		BoothService boothService = new BoothService();
		Object result=	boothService.AdvertingBoothAgree( id);
		PrintWriter out = response.getWriter();
		out.print(result);
	}
	protected void CommodityBoothAgree(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��Ʒչλ����ͨ��
		String id2 = request.getParameter("id");
		int id = Integer.parseInt(id2);
		BoothService boothService = new BoothService();
		Object result=boothService.CommodityBoothAgree( id);
		PrintWriter out = response.getWriter();
		out.print(result);
	}
}
