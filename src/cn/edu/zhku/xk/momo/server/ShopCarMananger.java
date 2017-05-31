package cn.edu.zhku.xk.momo.server;

import java.util.List;


import cn.edu.zhku.xk.momo.been.ShopCar;
import cn.edu.zhku.xk.momo.been.ShopCarShow;
import cn.edu.zhku.xk.momo.dao.ShopCarDao;
import cn.edu.zhku.xk.momo.util.MultipleMap;
import net.sf.json.JSONObject;

public class ShopCarMananger {
	public JSONObject showShopCar(String account){
		ShopCarDao shopCarDao=new ShopCarDao();
		JSONObject result=new JSONObject();
		List<ShopCarShow> shopCarList=shopCarDao.searchShopCar(account);
		MultipleMap<ShopCarShow> shopCarMap=new MultipleMap<ShopCarShow>();
		for(ShopCarShow temp:shopCarList){
			shopCarMap.put(temp.getStoreName(), temp);
		}
		result.put("flag", "true");
		result.put("shopCarMap", shopCarMap.toJSONObject());
		return result;
	}
	public JSONObject addShopCar(ShopCar shopCar){
		ShopCarDao shopCarDao=new ShopCarDao();
		JSONObject result=new JSONObject();
		if(shopCarDao.addShopCar(shopCar)){
			result.put("msg", "添加购物车成功!");
			result.put("flag", "true");
			
		}else{
			result.put("msg", "添加购物车失败,请重新添加!");
			result.put("flag", "false");
		}
		return result;
	}
	public JSONObject deleteShopCar(String accout,int modelId){
		ShopCarDao shopCarDao=new ShopCarDao();
		JSONObject result=new JSONObject();
		if(shopCarDao.deleteShopCar(modelId,accout)){
			result.put("msg", "删除购物车成功!");
			result.put("flag", "true");
			
		}else{
			result.put("msg", "删除购物车失败,请重新添加!");
			result.put("flag", "false");
		}
		return result;
	}
	public JSONObject modifyShopCar(ShopCar shopCar){
		ShopCarDao shopCarDao=new ShopCarDao();
		JSONObject result=new JSONObject();
		if(shopCarDao.modifyShopCar(shopCar)){
			result.put("msg", "修改成功!");
			result.put("flag", "true");
			
		}else{
			result.put("msg", "修改购物车失败,请重新添加!");
			result.put("flag", "false");
		}
		return result;
	}
	
}
