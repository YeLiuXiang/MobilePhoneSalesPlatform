package cn.edu.zhku.xk.momo.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.zhku.xk.momo.been.Comment;
import cn.edu.zhku.xk.momo.been.Order;
import cn.edu.zhku.xk.momo.been.OrderACK;
import cn.edu.zhku.xk.momo.been.OrderShowInfo;
import cn.edu.zhku.xk.momo.been.ReceivingInfo;
import cn.edu.zhku.xk.momo.been.ReturnPurchase;
import cn.edu.zhku.xk.momo.server.OrderMananger;
import cn.edu.zhku.xk.momo.util.MultipleMap;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class OrderSevrlet
 */
@WebServlet("/Order")
public class OrderSevrlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderSevrlet() {
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
		HttpSession senssion=request.getSession();
		if(account==null){
			JSONObject result=new JSONObject();
			result.put("msg", "请登录!");
			result.put("flag", "false");
			PrintWriter out=response.getWriter();
			out.println(result);
			out.flush();
			out.close();
		}else if(method==null){
	
		}else if(method.equals("ACKFromShopCar")){
			OrderMananger orderMananger=new OrderMananger();
			String modelIdS=(String)request.getParameter("modelIdArr");
			JSONArray modelIdArr=JSONArray.fromObject(modelIdS);
			
			int []modelIdI=new int[modelIdArr.size()];
			for(int i=0;i<modelIdArr.size();i++){
				modelIdI[i]=modelIdArr.getJSONObject(i).getInt("modelId");
			}
			JSONObject result=orderMananger.getACKFromShopCar(account, modelIdI);
			if(result.get("flag").equals("true")){
				senssion.setAttribute("from", "shoCar");
				senssion.setAttribute("result", result);
			}
			PrintWriter out=response.getWriter();
			out.println(result);
			out.flush();
			out.close();
			
		}else if(method.equals("ACKFromGoods")){
			OrderMananger orderMananger=new OrderMananger();
			int modelId=Integer.valueOf(request.getParameter("modelId"));
			int number=Integer.valueOf(request.getParameter("number"));
			JSONObject result=orderMananger.getACKFromGoods(account, modelId, number);
			if(result.get("flag").equals("true")){
				senssion.setAttribute("from", "goods");
				senssion.setAttribute("result", result);
			}
			PrintWriter out=response.getWriter();
			out.println(result);
			out.flush();
			out.close();
		}else if(method.equals("ACK")){
			request.getRequestDispatcher("order/orderAck.jsp").forward(request, response);
		}
		else if(method.equals("create")){
			String from=(String)senssion.getAttribute("from");
			String orderACKMapS=(String)senssion.getAttribute("orderACKMap");
			JSONObject result=new JSONObject();
			String receiverPhone=(String)request.getParameter("receiverPhone");
			String address=(String)request.getParameter("address");
			String receiver=(String)request.getParameter("receiver");	
			ReceivingInfo receivingIno=new ReceivingInfo();
			receivingIno.setAddress(address);
			receivingIno.setReceiver(receiver);
			receivingIno.setReceiverPhone(receiverPhone);
			boolean isFromCar=false;
			if(from==null){
				result.put("msg", "下单失败请重新申请!");
				result.put("flag", "false");
				
			}else{
				if(from.equals("shopCar")){
					isFromCar=true;	
				}
				OrderMananger orderMananger=new OrderMananger();
				result=orderMananger.createOrder(account,orderACKMapS,receivingIno, isFromCar);
			}
			if(result.get("flag").equals("true")){
				List<Integer> orderIdArr=JSONArray.toList(result.getJSONArray("orderIdArr"), Integer.class);
				senssion.setAttribute("orderIdArr", orderIdArr);
			}
			PrintWriter out=response.getWriter();
			out.println(result);
			out.flush();
			out.close();
		}else if(method.equals("pay")){
			String from=(String)senssion.getAttribute("from");
			String codeIn=(String)request.getParameter("phoneCode");
			String code=(String)request.getSession().getAttribute("phoneCode");
			JSONObject result=new JSONObject();
			if(code==null||!code.equals(codeIn)){
				result.put("msg", "验证码不正确!");
				result.put("flag", "false");
			}else{
				List<Integer> orderIdArr=new ArrayList<>();
				if(from==null){
					int orderId=Integer.valueOf(request.getParameter("orderId"));
					orderIdArr.add(orderId);
				}else{
					orderIdArr=(List<Integer>)senssion.getAttribute("orderIdArr");
					
				}
				if(orderIdArr==null){
					result.put("msg", "没有订单信息!");
					result.put("flag", "false");	
				}else{
					OrderMananger orderMananger=new OrderMananger();
					result=orderMananger.payOrder(account, orderIdArr);
					if("true".equals(result.get("flag"))){
						request.getSession().removeAttribute("phoneCode");
						senssion.removeAttribute("orderIdArr");
					}
				}
			}
			PrintWriter out=response.getWriter();
			out.print(result);
			out.flush();
			out.close();
			
		}else if(method.equals("return")){
			JSONObject result=new JSONObject();
			
			String type=(String)request.getParameter("type");
			String reason=(String)request.getParameter("reason");
			int orderId=Integer.valueOf(request.getParameter("orderId"));
			ReturnPurchase returnPurchase=new ReturnPurchase();
			returnPurchase.setCustomerAccount(account);
			returnPurchase.setorderId(orderId);
			returnPurchase.setReason(reason);
			returnPurchase.setType(type);
			
			OrderMananger orderMananger=new OrderMananger();
			result=orderMananger.returnOrder(returnPurchase);
			PrintWriter out=response.getWriter();
			out.println(result);
			out.flush();
			out.close();
		}else if(method.equals("comment")){
			JSONObject result=new JSONObject();
			int orderId=Integer.valueOf(request.getParameter("orderId"));
			int modelId=Integer.valueOf(request.getParameter("modelId"));
			int goodsId=Integer.valueOf(request.getParameter("goodsId"));
			String content=(String)request.getParameter("content");
			Comment comment=new Comment();
			comment.setContent(content);
			comment.setCustomerAccount(account);
			comment.setGoodsId(goodsId);
			OrderMananger orderMananger=new OrderMananger();
			result=orderMananger.commentGoods(comment,orderId,modelId);
			PrintWriter out=response.getWriter();
			out.println(result);
			out.flush();
			out.close();
		}else if(method.equals("showAll")){
			JSONObject result=new JSONObject();
			
			OrderMananger orderMananger=new OrderMananger();
			result=orderMananger.showAll(account);
			request.setAttribute("result", result);
			
			request.getRequestDispatcher("order/orderShow.jsp").forward(request, response);
			
		}else if(method.equals("cancal")){//取消订单
			JSONObject result=new JSONObject();
			int orderId=Integer.valueOf(request.getParameter("orderId"));
			
			OrderMananger orderMananger=new OrderMananger();
			result=orderMananger.cancalOrder(account,orderId);
			PrintWriter out=response.getWriter();
			out.println(result);
			out.flush();
			out.close();
		}else if(method.equals("receive")){//取消订单
			JSONObject result=new JSONObject();
			int orderId=Integer.valueOf(request.getParameter("orderId"));
			
			OrderMananger orderMananger=new OrderMananger();
			result=orderMananger.receiveOrder(account,orderId);
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
