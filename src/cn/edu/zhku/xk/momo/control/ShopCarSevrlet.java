package cn.edu.zhku.xk.momo.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zhku.xk.momo.been.ShopCar;
import cn.edu.zhku.xk.momo.server.ShopCarMananger;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class ShopCarSevrlet
 */
@WebServlet("/ShopCar")
public class ShopCarSevrlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopCarSevrlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String account=(String)request.getSession().getAttribute("userName");
		String method=(String)request.getParameter("method");
		if(account==null){
			JSONObject result=new JSONObject();
			result.put("msg", "ÇëµÇÂ¼!");
			result.put("flag", "false");
			PrintWriter out=response.getWriter();
			out.println(result);
			out.flush();
			out.close();
		}else if(method==null){
			ShopCarMananger shopCarMananger=new ShopCarMananger();
			JSONObject result=shopCarMananger.showShopCar(account);
			request.setAttribute("result", result);
			request.getRequestDispatcher("goods/cart.jsp").forward(request, response);
			
		}else if(method.equals("add")){
			int goodsModelId=Integer.valueOf(request.getParameter("modelId"));	
			int number=Integer.valueOf(request.getParameter("number"));
			ShopCar shopCar=new ShopCar();
			shopCar.setCustomerAccount(account);
			shopCar.setGoodsModelId(goodsModelId);
			shopCar.setNumber(number);
			ShopCarMananger shopCarMananger=new ShopCarMananger();
			JSONObject result=shopCarMananger.addShopCar(shopCar);
			PrintWriter out=response.getWriter();
			out.println(result);
			out.flush();
			out.close();
		}else if(method.equals("show")){
			ShopCarMananger shopCarMananger=new ShopCarMananger();
			JSONObject result=shopCarMananger.showShopCar(account);
			request.setAttribute("result", result);
			request.getRequestDispatcher("goods/cart.jsp").forward(request, response);
		}else if(method.equals("modify")){
			int modelId=Integer.valueOf(request.getParameter("modelId"));
			int goodsNum=Integer.valueOf(request.getParameter("goodsNum"));
			ShopCar shopCar=new ShopCar();
			shopCar.setCustomerAccount(account);
			shopCar.setNumber(goodsNum);
			shopCar.setGoodsModelId(modelId);
			ShopCarMananger shopCarMananger=new ShopCarMananger();
			JSONObject result=shopCarMananger.modifyShopCar(shopCar);
			PrintWriter out=response.getWriter();
			out.println(result);
			out.flush();
			out.close();
		}else if(method.equals("delete")){
			
			int modelId=Integer.valueOf(request.getParameter("modelId"));
			ShopCarMananger shopCarMananger=new ShopCarMananger();
			JSONObject result=shopCarMananger.deleteShopCar(account,modelId);
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
