package cn.edu.zhku.xk.sdp.service;

import cn.edu.zhku.xk.sdp.dao.ReturnOrderDao;
import cn.edu.zhku.xk.sdp.util.PageUtil;

public class ReturnOrderService {
	public PageUtil RetuExchQuery(int currentPage,int searchType, String account) {
		ReturnOrderDao returnOrderDao = new ReturnOrderDao();
		PageUtil result = 	returnOrderDao.RetuExchQuery(currentPage,searchType,account);
		return result;

	}

	public Object RetuExchQueryById(int id, int searchType,String account) {
		ReturnOrderDao returnOrderDao = new ReturnOrderDao();
		PageUtil result = 	returnOrderDao.RetuExchQueryById(id,searchType,account);
		return result;
	}
}
