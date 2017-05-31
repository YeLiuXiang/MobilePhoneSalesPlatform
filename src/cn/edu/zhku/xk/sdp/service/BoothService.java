package cn.edu.zhku.xk.sdp.service;

import cn.edu.zhku.xk.sdp.dao.BoothDao;
import net.sf.json.JSONObject;

public class BoothService {
	
	public JSONObject AdvertingBoothApply( String imgAddr, String linkGoodsId) {
		BoothDao boothDao = new BoothDao();
		boolean  result = 	boothDao.AdvertingBoothAdd(imgAddr, linkGoodsId);
		JSONObject json = new JSONObject();
		json.put("result", result);
		return json;
	}

	public JSONObject CommodityBoothApply(String imgAddr, String linkGoodsId) {
		BoothDao boothDao = new BoothDao();
		boolean  result = 	boothDao.CommodityBoothAdd(imgAddr, linkGoodsId);
		JSONObject json = new JSONObject();
		json.put("result", result);
		return json;
	}

	public Object AdvertingBoothQuery(String account) {
	BoothDao boothDao = new BoothDao();
	Object  result = 	boothDao.AdvertingBoothQueryByAccount(account);
		return result;
	}

	public Object AdvertingBoothDelete(int id) {
		BoothDao boothDao = new BoothDao();
		Object  result = 	boothDao.AdvertingBoothDelete(id);
		JSONObject json = new JSONObject();
		json.put("result", result);
		return json;
	}

	public Object CommodityBoothQuery(String account) {
		BoothDao boothDao = new BoothDao();
		Object  result = 	boothDao.CommodityBoothQueryByAccount(account);
		return result;
	}

	public Object CommodityBoothDelete(int id) {
		BoothDao boothDao = new BoothDao();
		Object  result = 	boothDao.CommodityBoothDelete(id);
		JSONObject json = new JSONObject();
		json.put("result", result);
		return json;
	}

	public boolean WalletQuery(String account, int type) {
		BoothDao boothDao = new BoothDao();
		boolean  result = 	boothDao.WalletQuery(account,type);
		return result;
	}

	
}
