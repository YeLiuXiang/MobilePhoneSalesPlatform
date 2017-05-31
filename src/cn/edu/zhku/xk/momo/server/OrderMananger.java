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
			result.put("msg", "����Ʒ�����ڻ������¼�,�빺��������Ʒ!");
			return result;
		}else if(orderACK.getStoreNumber()<number){
			result.put("flag", "false");
			result.put("msg", "����Ʒ��治��,����ٹ��������߹���������Ʒ!");
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
				result.put("msg", "����Ʒ�����ڻ������¼�,�빺��������Ʒ!");
				return result;
			}
			for(int i=0;i<orderACKList.size();i++){
				orderACK=orderACKList.get(i);
				if(orderACK.getNumber()>orderACK.getStoreNumber()||!"shelf".equals(orderACK.getStatus())){
					result.put("flag", "false");
					result.put("msg", "�������ִ���,�뷵�ع��ﳵ�����µ�!");
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
			result.put("msg", "�µ�ʧ��,������!");
			return result;
		}
		
		result.put("flag", "true");
		result.put("orderIdArr",orderIdArr);
		result.put("msg", "�µ��ɹ�!");
		return result;
		
		}
		public JSONObject payOrder(String account,List<Integer> oderIdArr) {
			OrderDao orderDao=new OrderDao ();
			JSONObject result = new JSONObject();
			for(int i=0;i<oderIdArr.size();i++){
				if(!orderDao.changeOrderStatus(account, oderIdArr.get(i), "waitToSend")){
					result.put("flag", "false");
					result.put("msg", "֧��ʧ��,������!");
					return result;
				}
			}
			result.put("flag", "true");
			result.put("msg","֧���ɹ�");
			return result;
			
	}	
		public JSONObject returnOrder(ReturnPurchase returnPurchase) {
			JSONObject result = new JSONObject();
			OrderDao orderDao=new OrderDao ();
			ReturnPurchaseDao returnPurchaseDao=new ReturnPurchaseDao();
			if(!returnPurchaseDao.addReturnPurchase(returnPurchase)&&!orderDao.changeOrderStatus(returnPurchase.getCustomerAccount(), returnPurchase.getorderId(), "closed")){
					result.put("flag", "false");
					result.put("msg", "����ʧ��,������!");
					return result;
			}
			result.put("flag", "true");
			result.put("msg","���۳ɹ�");
			return result;
			
	}	
		public JSONObject commentGoods(Comment comment,int orderId,int modelId) {
			JSONObject result = new JSONObject();
			OrderGoodsDao orderGoodsDao=new OrderGoodsDao ();
			CommentDao commentDao=new CommentDao();
			if(!commentDao.addComment(comment)&&!orderGoodsDao.changeCommentStatus(orderId, modelId)){
					result.put("flag", "false");
					result.put("msg", "����ʧ��,������!");
					return result;
			}
			result.put("flag", "true");
			result.put("msg","���۳ɹ�");
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
					result.put("msg", "ȡ������ʧ��,������!");
					return result;
			}
			result.put("flag", "true");
			result.put("msg","ȡ�������ɹ�");
			return result;
			
	}		
		public JSONObject receiveOrder(String account,int orderId) {
			JSONObject result = new JSONObject();
			OrderDao orderDao=new OrderDao ();
			if(!orderDao.changeOrderStatus(account,orderId,"received")){
					result.put("flag", "false");
					result.put("msg", "ȷ���ջ�ʧ��,������!");
					return result;
			}
			result.put("flag", "true");
			result.put("msg","ȷ���ջ��ɹ�");
			return result;
			
	}	
}
