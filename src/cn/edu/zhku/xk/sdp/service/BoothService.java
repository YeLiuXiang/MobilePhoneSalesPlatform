package cn.edu.zhku.xk.sdp.service;

import cn.edu.zhku.xk.sdp.dao.BoothDao;
import net.sf.json.JSONObject;

/**
 * 
 * @author ҹ����
 *
 */
public class BoothService {
	/**
	 * ���չλ����
	 * @param imgAddr	ͼƬ��ַ
	 * @param linkGoodsId	������Ʒ���
	 * @return		������� true/false
	 */
	public JSONObject AdvertingBoothApply( String imgAddr, String linkGoodsId) {
		BoothDao boothDao = new BoothDao();
		boolean  result = 	boothDao.AdvertingBoothAdd(imgAddr, linkGoodsId);
		JSONObject json = new JSONObject();
		json.put("result", result);
		return json;
	}

	/**
	 * ��Ʒչλ����
	 * @param imgAddr		ͬ��
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
	 * ���չλ��ѯ
	 * @param account		����˺�
	 * @return		���չλ��Ϣ/null
	 */
	public Object AdvertingBoothQuery(String account) {
	BoothDao boothDao = new BoothDao();
	Object  result = 	boothDao.AdvertingBoothQueryByAccount(account);
		return result;
	}
	/**
	 * ɾ�����չλ
	 * @param id		���չλ���
	 * @return  �������true/false
	 */
	public Object AdvertingBoothDelete(int id) {
		BoothDao boothDao = new BoothDao();
		Object  result = 	boothDao.AdvertingBoothDelete(id);
		JSONObject json = new JSONObject();
		json.put("result", result);
		return json;
	}
/**
 * ��Ʒչλ��ѯ
 * @param account			�����˺�
 * @return		��Ʒչλ��Ϣ/null
 */
	public Object CommodityBoothQuery(String account) {
		BoothDao boothDao = new BoothDao();
		Object  result = 	boothDao.CommodityBoothQueryByAccount(account);
		return result;
	}

	/**
	 * ��Ʒչλɾ��
	 * @param id	չλ���
	 * @return �������	true/false
	 */
	public Object CommodityBoothDelete(int id) {
		BoothDao boothDao = new BoothDao();
		Object  result = 	boothDao.CommodityBoothDelete(id);
		JSONObject json = new JSONObject();
		json.put("result", result);
		return json;
	}

		/**
		 * ����Ǯ����ѯ
		 * �ж�Ǯ������Ƿ�������������
		 * @param account		�����˺�
		 * @param type		��ѯ���ͣ�������չλ����/������Ʒչλ����
		 * @return		��ѯ���true/false
		 */
	public boolean WalletQuery(String account, int type) {
		BoothDao boothDao = new BoothDao();
		boolean  result = 	boothDao.WalletQuery(account,type);
		return result;
	}

	
}
