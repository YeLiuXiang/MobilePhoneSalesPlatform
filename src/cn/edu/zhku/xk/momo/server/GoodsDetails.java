package cn.edu.zhku.xk.momo.server;

import java.util.List;

import cn.edu.zhku.xk.momo.been.Comment;
import cn.edu.zhku.xk.momo.been.GoodsBase;
import cn.edu.zhku.xk.momo.been.GoodsExtend;
import cn.edu.zhku.xk.momo.dao.CommentDao;
import cn.edu.zhku.xk.momo.dao.GoodsDao;
import cn.edu.zhku.xk.momo.dao.GoodsExtendDao;
import cn.edu.zhku.xk.momo.util.MultipleMap;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GoodsDetails {
	public JSONObject showGoodsDetails(int goodsId){
		GoodsDao goodsDao=new GoodsDao();
		GoodsBase goodsBase=goodsDao.SearchByid(goodsId);
		JSONObject result=new JSONObject();
		if(goodsBase==null){
			result.put("flag", "false");
			result.put("msg", "找不到该商品");
			return result;
		}else if(goodsBase.getStatus().equals("unshelf")){
			result.put("flag", "false");
			result.put("msg", "该商品以下架或者未上架!");
			return result;
		}
		result.put("goodsBase", goodsBase);
		GoodsExtendDao goodsExtendDao=new GoodsExtendDao();
		List<GoodsExtend> goodsDetails=goodsExtendDao.getGoodsExtendByGoodsId(goodsId);
		MultipleMap<GoodsExtend> goodsMap=new MultipleMap<GoodsExtend>();
		for(GoodsExtend goodsTemp : goodsDetails){
			goodsMap.put(goodsTemp.getCapacity(),goodsTemp);
		}
		result.put("goodsMap", goodsMap.toJSONObject());
		System.out.println(goodsMap.toJSONObject());
		CommentDao commentDao=new CommentDao();
		List<Comment> commentList=commentDao.getCommentByGoodsId(goodsId);
		result.put("commentList", commentList);
		result.put("flag", "true");
		return result;
	}
	
	public JSONObject getGoodsBase(int goodsId){
		GoodsDao goodsDao=new GoodsDao();
		GoodsBase goodsBase=goodsDao.SearchByid(goodsId);
		JSONObject result=new JSONObject();
		if(goodsBase==null){
			result.put("flag", "false");
			result.put("msg", "找不到该商品");
			return result;
		}else if(goodsBase.getStatus().equals("unshelf")){
			result.put("flag", "false");
			result.put("msg", "该商品以下架或者未上架!");
			return result;
		}
		result.put("goodsBase", goodsBase);
		result.put("flag", "true");
		return result;
	}
	public JSONObject getGoodsColor(int goodsId,String capacity){
	
		JSONObject result=new JSONObject();
		GoodsExtendDao goodsExtendDao=new GoodsExtendDao();
		List<String> colorList=goodsExtendDao.getGoodsColor(goodsId,capacity);
		result.put("colorList", JSONArray.fromObject(colorList).toString());
		result.put("flag", "true");
		return result;
	}
	public JSONObject getGoodsCapacity(int goodsId){
		
		JSONObject result=new JSONObject();
		GoodsExtendDao goodsExtendDao=new GoodsExtendDao();
		List<String> capacityList=goodsExtendDao.getGoodsCapacity(goodsId);
		result.put("capacityList", JSONArray.fromObject(capacityList).toString());
		result.put("flag", "true");
		return result;
	}
	public JSONObject getGoodsExtend(int goodsId,String color,String capacity){
		
		GoodsExtendDao goodsExtendDao=new GoodsExtendDao();
		GoodsExtend goodsExtend=goodsExtendDao.getGoodsExtendByGoodsId(goodsId, color, capacity);
		JSONObject result=new JSONObject();
		result.put("goodsExtend", JSONObject.fromObject(goodsExtend).toString());
		result.put("flag", "true");
		return result;
	}
}
