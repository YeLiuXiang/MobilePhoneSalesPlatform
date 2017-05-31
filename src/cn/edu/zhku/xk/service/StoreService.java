package cn.edu.zhku.xk.service;

import cn.edu.zhku.xk.dao.StoreDao;
import cn.edu.zhku.xk.sdp.util.GlobalVariable;
import cn.edu.zhku.xk.sdp.util.PageUtil;

public class StoreService {

	public Object MessageQuery(String idOrName) {
		StoreDao storeDao = new StoreDao();
		PageUtil result = null;
		boolean isID = idOrName.matches("[0-9]+"); 
		if(isID){
			int id = Integer.parseInt(idOrName);
			 result=	storeDao.MessageQueryById(id);
			
		}else{
			 result=	storeDao.MessageQueryByName(idOrName);
		}
		return result;
	}

	public Object StoreFreeze(int id) {
		StoreDao storeDao = new StoreDao();
		Object result=storeDao.StoreStatusModify(GlobalVariable.FreezeStore,id);
		return result;
	}

	public Object StoreUnFreeze(int id) {
		StoreDao storeDao = new StoreDao();
		Object result=storeDao.StoreStatusModify(GlobalVariable.UnFreezeStore,id);
		return result;
	}

	

}
