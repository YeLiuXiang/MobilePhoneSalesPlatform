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
 * @author ҹ����
 *	��Ʒ����
 */
public class GoodsService {

	/**
	 * 	
	 * @param searchType		��ѯ���ͣ�1���ڼ���Ʒ��ѯ  2��δ�ϼ���Ʒ��ѯ
	 * @param currentPage		��ǰҳ��
	 * @param account 
	 * @return		һҳ���ݵ���Ʒ��
	 */
	public PageUtil GoodsQuery(int searchType,int currentPage, String account) {
		// �궨�� public static final
		GoodsDao goodsDao = new GoodsDao();
		
		switch (searchType) {

		case GlobalVariable.OnShelfQuery:
			PageUtil result = goodsDao.GoodsQueryOn(currentPage,account);// ���ϼ���Ʒ
			return result;
		case GlobalVariable.OffShelfQuery:
			PageUtil result2 = goodsDao.GoodsQueryOff( currentPage,account);// δ�ϼ���Ʒ
			return result2;
		default:
			return null;
		}
	}
	/**
	 * ������Ʒ��Ż����Ʋ�ѯ��Ʒ
	 * @param searchType	��ѯ����
	 * @param id2		���
	 * @param account		�����˺�
	 * @return		һ����Ʒ����Ϣ
	 */
	public PageUtil GoodsQueryByIdOrName(int searchType,String id2, String account) {
		// �궨�� public static final
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
	 * ��Ʒ�ϼ�
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
	 * ��Ʒ�����ѯ
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
