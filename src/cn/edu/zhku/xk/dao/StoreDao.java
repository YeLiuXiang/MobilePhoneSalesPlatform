package cn.edu.zhku.xk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zhku.xk.momo.util.JdbcUint;
import cn.edu.zhku.xk.sdp.been.Store;
import cn.edu.zhku.xk.sdp.util.DBCPUtil;
import cn.edu.zhku.xk.sdp.util.GlobalVariable;
import cn.edu.zhku.xk.sdp.util.PageUtil;


public class StoreDao {

	public boolean AdvertingBoothStatusModify(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		Statement stm = null;
		ResultSet rs = null;
		String sql=null;
			
			sql = "update  web_advertising_booth  set  adv_status  = 'valid'  where adv_id = "+id;
			
		
		try {
			//con = DBCPUtil.getInstance().getConnection();
			con=JdbcUint.getConnection();
			stm = con.createStatement();
			int num = stm.executeUpdate(sql);
			if(num!=0){
				DBCPUtil.close(rs, stm, con);
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBCPUtil.close(rs, stm, con);
		return false;
	}
	
	public boolean CommodityBoothStatusModify(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		Statement stm = null;
		ResultSet rs = null;
		String sql=null;
			
			sql = "update  web_commodity_booth  set  com_status  = 'valid'  where com_id = "+id;
			
		
		try {
			//con = DBCPUtil.getInstance().getConnection();
			con=JdbcUint.getConnection();
			stm = con.createStatement();
			int num = stm.executeUpdate(sql);
			if(num!=0){
				DBCPUtil.close(rs, stm, con);
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBCPUtil.close(rs, stm, con);
		return false;
	}

	public PageUtil MessageQueryById(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		Statement stm = null;
		ResultSet rs = null;
		String sql=null;
		PageUtil pageObject =  new PageUtil(1,1);
			
			sql = "select sto_id,sto_name,cus_account,sto_status from  web_store where  sto_id = "+id;
			
		try {
			//con = DBCPUtil.getInstance().getConnection();
			con=JdbcUint.getConnection();
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			List<Store> storeList = new ArrayList<Store>();
			if (rs.next()) {

				Store store = new Store();
				store.setId(rs.getInt("sto_id"));
				store.setName(rs.getString("sto_name"));
				store.setAccount(rs.getString("cus_account"));
				if ("operating".equals(rs.getString("sto_status"))) {
					store.setStatus("运营中");
				} else {
					store.setStatus("冻结");
				}
				storeList.add(store);
				pageObject.setList(storeList);
			}

			DBCPUtil.close(rs, stm, con);
			return pageObject;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBCPUtil.close(rs, stm, con);
		return null;
	}

	public PageUtil MessageQueryByName(String idOrName) {
		Connection con = null;
		PreparedStatement ps = null;
		Statement stm = null;
		ResultSet rs = null;
		String sql=null;
		PageUtil pageObject =  new PageUtil(1,1);
			
	// 特定编号商品
			sql = "select sto_id,sto_name,cus_account,sto_status from  web_store where  sto_name = '"+idOrName+"'";
			
		try {
			//con = DBCPUtil.getInstance().getConnection();
			con=JdbcUint.getConnection();
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			List<Store> storeList = new ArrayList<Store>();
			if (rs.next()) {
				Store store = new Store();
				store.setId(rs.getInt("sto_id"));
				store.setName(rs.getString("sto_name"));
				store.setAccount(rs.getString("cus_account"));
				if ("operating".equals(rs.getString("sto_status"))) {
					store.setStatus("运营中");
				} else {
					store.setStatus("冻结");
				}
				storeList.add(store);
				pageObject.setList(storeList);
			}

			DBCPUtil.close(rs, stm, con);
			return pageObject;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBCPUtil.close(rs, stm, con);
		return null;
	}

	public boolean StoreStatusModify(int modifyType, int id) {
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		int num=0;
		String	 sql=null;
		if(modifyType==GlobalVariable.FreezeStore){
			 sql = "update  web_store  set  sto_status  = 'frozen' where sto_id = "+id;
		}else if(modifyType==GlobalVariable.UnFreezeStore){
			 sql = "update  web_store  set  sto_status  = 'operating' where sto_id = "+id;
		}
		try {
			//con = DBCPUtil.getInstance().getConnection();
			con=JdbcUint.getConnection();
			stm = con.createStatement();
			num = stm.executeUpdate(sql);
			if(num!=0){
				DBCPUtil.close(rs, stm, con);
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBCPUtil.close(rs, stm, con);
		return false;

	}

	public boolean WalletModify(int boothType, int id) {
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		int num=0;
		String	 sql=null;
		if(boothType==GlobalVariable.AddAdvertisingBooth){
			 sql = "update  web_store  set  sto_wallet  = sto_wallet-2000 where sto_id = "+id;
		}else if(boothType==GlobalVariable.AddCommodityBooth){
			 sql = "update  web_store  set  sto_wallet  =  sto_wallet-1000  where sto_id = "+id;
		}
		try {
			//con = DBCPUtil.getInstance().getConnection();
			con=JdbcUint.getConnection();
			stm = con.createStatement();
			num = stm.executeUpdate(sql);
			if(num!=0){
				DBCPUtil.close(rs, stm, con);
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBCPUtil.close(rs, stm, con);
		return false;

	}

}
