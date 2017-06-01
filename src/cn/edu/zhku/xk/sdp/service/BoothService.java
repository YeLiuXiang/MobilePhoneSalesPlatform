package cn.edu.zhku.xk.sdp.service;

import cn.edu.zhku.xk.sdp.dao.BoothDao;
import net.sf.json.JSONObject;

/**
 * 
 * @author 夜留香
 *
 */
public class BoothService {
	/**
	 * 广告展位申请
	 * @param imgAddr	图片地址
	 * @param linkGoodsId	链接商品编号
	 * @return		操作结果 true/false
	 */
	public JSONObject AdvertingBoothApply( String imgAddr, String linkGoodsId) {
		BoothDao boothDao = new BoothDao();
		boolean  result = 	boothDao.AdvertingBoothAdd(imgAddr, linkGoodsId);
		JSONObject json = new JSONObject();
		json.put("result", result);
		return json;
	}

	/**
	 * 商品展位申请
	 * @param imgAddr		同上
	 * @param linkGoodsId
	 * @return
	 */
	public JSONObject CommodityBoothApply(String imgAddr, String linkGoodsId) {
		BoothDao boothDao = new BoothDao();
		boolean  result = 	boothDao.CommodityBoothAdd(imgAddr, linkGoodsId);
		JSONObject json = new JSONObject();
		json.put("result", result);
		return json;
	}
	/**
	 * 广告展位查询
	 * @param account		店家账号
	 * @return		广告展位信息/null
	 */
	public Object AdvertingBoothQuery(String account) {
	BoothDao boothDao = new BoothDao();
	Object  result = 	boothDao.AdvertingBoothQueryByAccount(account);
		return result;
	}
	/**
	 * 删除广告展位
	 * @param id		广告展位编号
	 * @return  操作结果true/false
	 */
	public Object AdvertingBoothDelete(int id) {
		BoothDao boothDao = new BoothDao();
		Object  result = 	boothDao.AdvertingBoothDelete(id);
		JSONObject json = new JSONObject();
		json.put("result", result);
		return json;
	}
/**
 * 商品展位查询
 * @param account			店主账号
 * @return		商品展位信息/null
 */
	public Object CommodityBoothQuery(String account) {
		BoothDao boothDao = new BoothDao();
		Object  result = 	boothDao.CommodityBoothQueryByAccount(account);
		return result;
	}

	/**
	 * 商品展位删除
	 * @param id	展位编号
	 * @return 操作结果	true/false
	 */
	public Object CommodityBoothDelete(int id) {
		BoothDao boothDao = new BoothDao();
		Object  result = 	boothDao.CommodityBoothDelete(id);
		JSONObject json = new JSONObject();
		json.put("result", result);
		return json;
	}

		/**
		 * 店铺钱包查询
		 * 判断钱包余额是否满足申请条件
		 * @param account		店主账号
		 * @param type		查询类型：满足广告展位申请/满足商品展位申请
		 * @return		查询结果true/false
		 */
	public boolean WalletQuery(String account, int type) {
		BoothDao boothDao = new BoothDao();
		boolean  result = 	boothDao.WalletQuery(account,type);
		return result;
	}

	
}
