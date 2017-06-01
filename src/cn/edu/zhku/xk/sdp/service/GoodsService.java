package cn.edu.zhku.xk.sdp.service;
import java.util.ArrayList;

import cn.edu.zhku.xk.sdp.been.GoodsBase;
import cn.edu.zhku.xk.sdp.been.GoodsDetail;
import cn.edu.zhku.xk.sdp.been.GoodsExtend;
import cn.edu.zhku.xk.sdp.been.GoodsIdModelIdStoreId;
import cn.edu.zhku.xk.sdp.dao.GoodsDao;
import cn.edu.zhku.xk.sdp.util.GlobalVariable;
import cn.edu.zhku.xk.sdp.util.PageUtil;

/**
 * 
 * @author 夜留香
 *	商品管理
 */
public class GoodsService {

	/**
	 * 	
	 * @param searchType		查询类型：1：在架商品查询  2：未上架商品查询
	 * @param currentPage		当前页码
	 * @param account 
	 * @return		一页内容的商品链
	 */
	public PageUtil GoodsQuery(int searchType,int currentPage, String account) {
		// 宏定义 public static final
		GoodsDao goodsDao = new GoodsDao();
		
		switch (searchType) {

		case GlobalVariable.OnShelfQuery:
			PageUtil result = goodsDao.GoodsQueryOn(currentPage,account);// 已上架商品
			return result;
		case GlobalVariable.OffShelfQuery:
			PageUtil result2 = goodsDao.GoodsQueryOff( currentPage,account);// 未上架商品
			return result2;
		default:
			return null;
		}
	}
	/**
	 * 根据商品编号或名称查询商品
	 * @param searchType	查询类型
	 * @param id2		编号
	 * @param account		店主账号
	 * @return		一个商品的信息
	 */
	public PageUtil GoodsQueryByIdOrName(int searchType,String id2, String account) {
		// 宏定义 public static final
		GoodsDao goodsDao = new GoodsDao();
		PageUtil result = null;
		boolean isNum = id2.matches("[0-9]+"); 
		if(isNum){
			int id = Integer.parseInt(id2);
			 result=	goodsDao.GoodsQueryById(searchType, id,account);
			
		}else{
			 result=	goodsDao.GoodsQueryByName(searchType, id2,account);
		}
		return result;
	}
	/**
	 * 商品上架
	 * @param id
	 * @return
	 */
	public boolean GoodsUpShelf(int id) {
		GoodsDao goodsDao = new GoodsDao();
	 	boolean result = goodsDao.OrderStatusModify(id,GlobalVariable.GoodsUpShelf);
	 	return result;
	}
	public boolean GoodsOffShelf(int id) {
		GoodsDao goodsDao = new GoodsDao();
	 	boolean result = goodsDao.OrderStatusModify(id,GlobalVariable.GoodsOffShelf);
	 	return result;
	}

	public boolean GoodsAdd(GoodsBase goodsBase, ArrayList<GoodsExtend> goodsList) {
		GoodsDao goodsDao = new GoodsDao();
		boolean result = false;
		boolean  flag = goodsDao.GoodsBaseAdd(goodsBase);
		if(flag){
		 result = goodsDao.GoodsModelAdd(goodsList);
		}
		return result;
	}
	public GoodsIdModelIdStoreId GoodsAddPre(String account) {
		GoodsDao goodsDao = new GoodsDao();
	GoodsIdModelIdStoreId goodsIdModelIdStoreId = 	goodsDao.GidMidSidQuery(account);
		return goodsIdModelIdStoreId;
	}
	/**
	 * 商品详情查询
	 * @param id
	 * @return
	 */
	public GoodsDetail GoodsDetailQuery(int id) {
		GoodsDao goodsDao = new GoodsDao();
		GoodsDetail goodsDetail = goodsDao.GoodsDetailQuery(id);
		return goodsDetail;
	}
	public Object GoodsTitleModify(int id, String title) {
		GoodsDao goodsDao = new GoodsDao();
		boolean result = goodsDao.GoodsTitleModify(id,title);
		return result;
	}
	public Object GoodsModify(int id, int stockNumber, float price) {
		GoodsDao goodsDao = new GoodsDao();
		boolean result = goodsDao.GoodsModify(id,stockNumber,price);
		return result;
	}
	public Object GoodsNameQuery(String account) {
		GoodsDao goodsDao = new GoodsDao();
		Object result = goodsDao.GoodsNameQuery(account);
		return result;
	}
	
}
