package cn.edu.zhku.xk.momo.server;

import java.util.List;

import cn.edu.zhku.xk.momo.been.AdvertisingBooth;
import cn.edu.zhku.xk.momo.been.GoodsForSearch;
import cn.edu.zhku.xk.momo.dao.AdvertiseDao;
import net.sf.json.JSONObject;

public class Home {
	public JSONObject getHomeDate(){
		AdvertiseDao advertiseDao =new AdvertiseDao ();
		
		List<AdvertisingBooth> advertisingBoothList=advertiseDao.getAdvertiseBooth();
		for(int i=0;advertisingBoothList.size()>3;++i){
			advertisingBoothList.remove(3);
		}
		List<GoodsForSearch> commodityBoothList=advertiseDao.getCommodityBooth();
		for(int i=0;commodityBoothList.size()>6;++i){
			commodityBoothList.remove(6);
		}
		JSONObject result=new JSONObject();
		result.put("advertisingBoothList", advertisingBoothList);
		result.put("commodityBoothList", commodityBoothList);
		result.put("flag", "false");
		return result;
	}
}
