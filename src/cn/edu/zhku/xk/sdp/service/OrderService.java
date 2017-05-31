package cn.edu.zhku.xk.sdp.service;


import java.util.ArrayList;

import cn.edu.zhku.xk.sdp.been.Order;
import cn.edu.zhku.xk.sdp.dao.OrderDao;
import cn.edu.zhku.xk.sdp.dao.ReturnOrderDao;
import cn.edu.zhku.xk.sdp.util.GlobalVariable;
import cn.edu.zhku.xk.sdp.util.PageUtil;
import net.sf.json.JSONObject;

public class OrderService {

	public Object OrderQuery(int searchType,int currentpage, String account) {
		OrderDao orderDao = new OrderDao( );
		JSONObject json = null;
		PageUtil result =null;
		switch(searchType){
		case GlobalVariable.NoSendOrderQuery:
		case GlobalVariable.SendOrderQuery:
		case GlobalVariable.ReceiveOrderQuery:	//已签收/已发货/未发货
			result = 	orderDao.OrderQuery(searchType, currentpage,account);
			break;

	
		default:
			break;
		}

		return result;

	}
	public Object OrderQueryById(int id,int searchType, String account) {//根据id查订单
		OrderDao orderDao = new OrderDao( );
		JSONObject json = null;
		PageUtil result =null;
		result = orderDao.OrderQueryById(id,searchType,account);
		return result;

	}
	
	
	public Object OrderDetailQuery(int id,int currentpage) {
		OrderDao orderDao = new OrderDao( );
		PageUtil result = orderDao.OrderDetailQuery(id,currentpage);
		return result;
		
	}
	public Object DeliverGoods(int id) {
		OrderDao orderDao = new OrderDao( );
		boolean result = orderDao.OrderStatusModify(id,"waitToReceive");
		return result;
		
	}
	public boolean RetuExchOper(int status, int id) {
		ReturnOrderDao returnOrderDao = new ReturnOrderDao();
		boolean 	result	= returnOrderDao.StatusModify(status,id);
		return result;
	}
	public ArrayList SalesStatistics(String account) {
		OrderDao orderDao = new OrderDao( );
		ArrayList list = orderDao.SalesQuery(account);
		return list;
	}
	public ArrayList SalesQuantityStatistics(String account) {
		OrderDao orderDao = new OrderDao( );
		ArrayList list = orderDao.SalesQuantityQuery(account);
		return list;
	}
	
	public Object GoodsSalesRank(String account) {
		OrderDao orderDao = new OrderDao( );
		ArrayList list = orderDao.SalesQuantityQuery2(account);
		return list;
	}
}
