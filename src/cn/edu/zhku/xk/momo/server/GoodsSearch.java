package cn.edu.zhku.xk.momo.server;

import java.util.List;

import cn.edu.zhku.xk.momo.been.GoodsForSearch;
import cn.edu.zhku.xk.momo.dao.GoodsDao;
import net.sf.json.JSONObject;

public class GoodsSearch {
	/*¹Ø¼ü´ÊËÑË÷*/
	public JSONObject searchByKey(String key){
		GoodsDao goodsDao=new GoodsDao();
		List<GoodsForSearch> goodsList=goodsDao.SearchBaseInfo(key);
		JSONObject result=new JSONObject();
		result.put("data", goodsList);
		return result;
	}
	
	
}
