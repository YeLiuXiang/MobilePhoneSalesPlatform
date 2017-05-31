package cn.edu.zhku.xk.momo.server;



import java.util.ArrayList;
import java.util.List;

import cn.edu.zhku.xk.momo.been.Comment;
import cn.edu.zhku.xk.momo.been.OrderACK;
import cn.edu.zhku.xk.momo.been.ReceivingInfo;
import cn.edu.zhku.xk.momo.been.ReturnPurchase;
import cn.edu.zhku.xk.momo.dao.CommentDao;
import cn.edu.zhku.xk.momo.dao.CustomerDao;
import cn.edu.zhku.xk.momo.dao.OrderDao;
import cn.edu.zhku.xk.momo.dao.OrderGoodsDao;
import cn.edu.zhku.xk.momo.dao.ReturnPurchaseDao;
import cn.edu.zhku.xk.momo.util.MultipleMap;
import net.sf.json.JSONObject;

public class OrderMananger {
	public JSONObject getACKFromGoods(String account,int goodsModelId,int number) {
		JSONObject result = new JSONObject();
		OrderDao orderDao=new OrderDao ();
		OrderACK orderACK=orderDao.getOrderAck(goodsModelId);
		if(orderACK==null){
			result.put("flag", "false");
			result.put("msg", "该商品不存在或者已下架,请购买其他商品!");
			return result;
		}else if(orderACK.getStoreNumber()<number){
			result.put("flag", "false");
			result.put("msg", "该商品库存不足,请减少购买量或者购买其他商品!");
			return result;
		}else{
			result.put("flag", "true");
			List<OrderACK> orderACKList=new ArrayList<>();
			orderACK.setNumber(number);
			orderACKList.add(orderACK);
			MultipleMap<OrderACK> orderACKMap=new MultipleMap<OrderACK>();
			for(OrderACK temp:orderACKList){
				orderACKMap.put(temp.getStoreName(), temp);
			}
			CustomerDao customerDao=new CustomerDao();
			ReceivingInfo  receivingInfo=customerDao.getReceivingInfo(account);
			result.put("orderACKMap", orderACKMap.toJSONObject());
			result.put("receivingInfo", receivingInfo);
			return result;
		}
	}
		public JSONObject getACKFromShopCar(String account,int ModelIdId[]) {
			JSONObject result = new JSONObject();
			OrderDao orderDao=new OrderDao ();
			OrderACK orderACK=null;
			List<OrderACK> orderACKList=orderDao.getOrderAckFromShopCar(ModelIdId,account);
			if(orderACKList.size()==0){
				result.put("flag", "false");
				result.put("msg", "该商品不存在或者已下架,请购买其他商品!");
				return result;
			}
			for(int i=0;i<orderACKList.size();i++){
				orderACK=orderACKList.get(i);
				if(orderACK.getNumber()>orderACK.getStoreNumber()||!"shelf".equals(orderACK.getStatus())){
					result.put("flag", "false");
					result.put("msg", "订单出现错误,请返回购物车重新下单!");
					return result;
				}
			}
			MultipleMap<OrderACK> orderACKMap=new MultipleMap<OrderACK>();
			for(OrderACK temp:orderACKList){
				orderACKMap.put(temp.getStoreName(), temp);
			}
			result.put("flag", "true");
			CustomerDao customerDao=new CustomerDao();
			ReceivingInfo  receivingInfo=customerDao.getReceivingInfo(account);
			result.put("orderACKMap", orderACKMap.toJSONObject());
			result.put("receivingInfo", receivingInfo);
			return result;
			
	}
		public JSONObject createOrder(String account,String MMS,ReceivingInfo receivingInfo,boolean isFromCar) {
		MultipleMap<OrderACK> orderACKMap=MultipleMap.toMultipleMap(MMS,new OrderACK());
		OrderDao orderDao=new OrderDao ();
		JSONObject result = new JSONObject();
		List<Integer> orderIdArr=orderDao.addOrder(account, orderACKMap, receivingInfo, isFromCar);
		if(orderIdArr==null){
			result.put("flag", "false");
			result.put("msg", "下单失败,请重试!");
			return result;
		}
		
		result.put("flag", "true");
		result.put("orderIdArr",orderIdArr);
		result.put("msg", "下单成功!");
		return result;
		
		}
		public JSONObject payOrder(String account,List<Integer> oderIdArr) {
			OrderDao orderDao=new OrderDao ();
			JSONObject result = new JSONObject();
			for(int i=0;i<oderIdArr.size();i++){
				if(!orderDao.changeOrderStatus(account, oderIdArr.get(i), "waitToSend")){
					result.put("flag", "false");
					result.put("msg", "支付失败,请重试!");
					return result;
				}
			}
			result.put("flag", "true");
			result.put("msg","支付成功");
			return result;
			
	}	
		public JSONObject returnOrder(ReturnPurchase returnPurchase) {
			JSONObject result = new JSONObject();
			OrderDao orderDao=new OrderDao ();
			ReturnPurchaseDao returnPurchaseDao=new ReturnPurchaseDao();
			if(!returnPurchaseDao.addReturnPurchase(returnPurchase)&&!orderDao.changeOrderStatus(returnPurchase.getCustomerAccount(), returnPurchase.getorderId(), "closed")){
					result.put("flag", "false");
					result.put("msg", "评价失败,请重试!");
					return result;
			}
			result.put("flag", "true");
			result.put("msg","评价成功");
			return result;
			
	}	
		public JSONObject commentGoods(Comment comment,int orderId,int modelId) {
			JSONObject result = new JSONObject();
			OrderGoodsDao orderGoodsDao=new OrderGoodsDao ();
			CommentDao commentDao=new CommentDao();
			if(!commentDao.addComment(comment)&&!orderGoodsDao.changeCommentStatus(orderId, modelId)){
					result.put("flag", "false");
					result.put("msg", "评价失败,请重试!");
					return result;
			}
			result.put("flag", "true");
			result.put("msg","评价成功");
			return result;
			
	}		
		public JSONObject showAll(String account) {
			
			OrderDao orderDao=new OrderDao ();
			JSONObject result=orderDao.getAll(account);
			result.put("flag", "true");
			return result;
	}		
		
		public JSONObject cancalOrder(String account,int orderId) {
			JSONObject result = new JSONObject();
			OrderDao orderDao=new OrderDao ();
			if(!orderDao.changeOrderStatus(account,orderId,"close")){
					result.put("flag", "false");
					result.put("msg", "取消订单失败,请重试!");
					return result;
			}
			result.put("flag", "true");
			result.put("msg","取消订单成功");
			return result;
			
	}		
		public JSONObject receiveOrder(String account,int orderId) {
			JSONObject result = new JSONObject();
			OrderDao orderDao=new OrderDao ();
			if(!orderDao.changeOrderStatus(account,orderId,"received")){
					result.put("flag", "false");
					result.put("msg", "确认收货失败,请重试!");
					return result;
			}
			result.put("flag", "true");
			result.put("msg","确认收货成功");
			return result;
			
	}	
}
