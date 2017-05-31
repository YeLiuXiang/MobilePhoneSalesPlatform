
package cn.edu.zhku.xk.momo.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zhku.xk.momo.server.GoodsDetails;
import cn.edu.zhku.xk.momo.server.GoodsSearch;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class GoodsSearchFilter
 */
@WebServlet("/GoodsSearch")
public class GoodsSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoodsSearchServlet() {
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
			request.getRequestDispatcher("home.jsp").forward(request, response);
		}else if(method.equals("key")){
			String key=(String)request.getParameter("key");
			GoodsSearch goodsSearch=new  GoodsSearch();
			JSONObject result=goodsSearch.searchByKey(key);
			request.setAttribute("result", result);
			request.getRequestDispatcher("goods/searchShow.jsp").forward(request, response);
			
		}else if(method.equals("goodsDetails")){
			int  goodsId=Integer.valueOf(request.getParameter("goodsId"));
			GoodsDetails goodsDetails=new GoodsDetails();
			JSONObject result=goodsDetails.showGoodsDetails(goodsId);
			request.setAttribute("result", result);
			request.getRequestDispatcher("goods/details.jsp").forward(request, response);
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
