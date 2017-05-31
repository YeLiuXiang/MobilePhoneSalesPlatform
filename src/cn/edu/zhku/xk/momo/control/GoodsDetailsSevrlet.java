package cn.edu.zhku.xk.momo.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zhku.xk.momo.server.GoodsDetails;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class GoodsDetails
 */
@WebServlet("/GoodsDetails")
public class GoodsDetailsSevrlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoodsDetailsSevrlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String method=(String)request.getParameter("method");
		if(method==null){
			
		}else if(method.equals("details")){
			int  goodsId=Integer.valueOf(request.getParameter("goodsId"));
			GoodsDetails goodsDetails=new GoodsDetails();
			JSONObject result=goodsDetails.getGoodsBase(goodsId);
			request.setAttribute("result", result);
			request.getRequestDispatcher("goods/details.jsp").forward(request, response);
		}else if(method.equals("getColor")){
			int  goodsId=Integer.valueOf(request.getParameter("goodsId"));
			String capacity=(String)request.getParameter("capacity");
			GoodsDetails goodsDetails=new GoodsDetails();
			JSONObject result=goodsDetails.getGoodsColor(goodsId,capacity);
			PrintWriter out=response.getWriter();
			out.println(result);
			out.flush();
			out.close();
		}else if(method.equals("getCapacity")){
			int  goodsId=Integer.valueOf(request.getParameter("goodsId"));
			GoodsDetails goodsDetails=new GoodsDetails();
			JSONObject result=goodsDetails.getGoodsCapacity(goodsId);
			PrintWriter out=response.getWriter();
			out.println(result);
			out.flush();
			out.close();
		}else if(method.equals("getGoodsExtend")){
			int  goodsId=Integer.valueOf(request.getParameter("goodsId"));
			String capacity=(String)request.getParameter("capacity");
			String color=(String)request.getParameter("color");
			GoodsDetails goodsDetails=new GoodsDetails();
			JSONObject result=goodsDetails.getGoodsExtend(goodsId, color, capacity);
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
