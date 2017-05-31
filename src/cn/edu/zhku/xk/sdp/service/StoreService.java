package cn.edu.zhku.xk.sdp.service;


import cn.edu.zhku.xk.sdp.been.Store;
import cn.edu.zhku.xk.sdp.been.StoreModify;
import cn.edu.zhku.xk.sdp.dao.StoreDao;

public class StoreService {

	/**
	 * 店铺预修改信息查询
	 * @return 
	 */
	public StoreModify MessageModifyQuery(String account) {
		StoreDao storeDao = new StoreDao();
		StoreModify storeMessage =	storeDao.StoreModifyMessageQuery(account);
		return storeMessage;
	}

	public boolean MessageModify(String account, String customerService, String imgAddr) {
		StoreDao storeDao = new StoreDao();
	boolean  result = 	storeDao.StoreMessageModify(account, customerService, imgAddr);
		return result;
		
	}

	public Object StoreMessageQuery(String account) {
		StoreDao storeDao = new StoreDao();
		Store storeMessage =	storeDao.StoreMessageQuery(account);
		return storeMessage;
	}

	public Object WalletTopUp(float money, String account) {
		StoreDao storeDao = new StoreDao();
		boolean result =	storeDao.walletModify(money,account);
		return result;
	}

	

}
