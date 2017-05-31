package cn.edu.zhku.xk.service;

import cn.edu.zhku.xk.dao.BoothDao;
import cn.edu.zhku.xk.dao.StoreDao;
import cn.edu.zhku.xk.sdp.util.GlobalVariable;
import cn.edu.zhku.xk.sdp.util.PageUtil;

public class BoothService {
	public PageUtil AdvertingBoothQuery() {
		BoothDao boothDao = new BoothDao();
		PageUtil result = boothDao.AdvertingBoothQuery();
		return result;
	}
	public PageUtil AdvertingBoothApplyQuery(int currentPage) {
		BoothDao boothDao = new BoothDao();
		PageUtil result = boothDao.AdvertingBoothApplyQuery(currentPage);
		return result;
	}
	public boolean AdvertingBoothAgree(int id) {
		BoothDao boothDao = new BoothDao();
	StoreDao storeDao = new StoreDao();
	boolean result=false;
		boolean success= storeDao.WalletModify(GlobalVariable.AddAdvertisingBooth,id);
		if(success){
		 result = boothDao.AdvertingBoothStatusModify(id);
		}else{
			return false;
		}
		return result;
	}
	public boolean CommodityBoothAgree(int id) {
		BoothDao boothDao = new BoothDao();
		StoreDao storeDao = new StoreDao();
		boolean result=false;
		boolean success= storeDao.WalletModify(GlobalVariable.AddAdvertisingBooth,id);
		if(success){
			result = boothDao.CommodityBoothStatusModify(id);
		}else{
			return false;
		}
		return result;
	}
	public boolean AdvertingBoothModify(int id) {
		BoothDao boothDao = new BoothDao();
		boolean result = boothDao.AdvertingBoothDelete(id);
		return result;
	}
	public Object CommodityBoothQuery(int currentPage) {
		BoothDao boothDao = new BoothDao();
		PageUtil result = boothDao.CommodityBoothQuery(currentPage);
		return result;
	}
	public Object CommodityBoothApplyQuery(int currentPage) {
		BoothDao boothDao = new BoothDao();
		PageUtil result = boothDao.CommodityBoothApplyQuery(currentPage);
		return result;
	}
	public Object CommodityBoothModify(int id) {
		BoothDao boothDao = new BoothDao();
		boolean result = boothDao.CommodityBoothDelete(id);
		return result;
	}

}
