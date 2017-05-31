package cn.edu.zhku.xk.sdp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import cn.edu.zhku.xk.momo.util.JdbcUint;
import cn.edu.zhku.xk.sdp.been.AdvertisingBooth;
import cn.edu.zhku.xk.sdp.been.CommodityBooth;
import cn.edu.zhku.xk.sdp.been.SalesQuantityStatics;
import cn.edu.zhku.xk.sdp.util.DBCPUtil;
import cn.edu.zhku.xk.sdp.util.GlobalVariable;

public class BoothDao {
	public boolean AdvertingBoothAdd(String imgAddr, String linkGoodsId) {
		Connection con = null;
		PreparedStatement ps = null;
		Statement stm = null;
		ResultSet rs = null;
		String sql = "insert into   web_advertising_booth(adv_img_address,adv_link_goods_id,adv_status)   values(?,?,?)" ;
		try {
//	con = DBCPUtil.getInstance().getConnection(); 
	con=JdbcUint.getConnection();
	
			ps = con.prepareStatement(sql);
			
			ps.setString(1,imgAddr );
			ps.setString(2, linkGoodsId);
			ps.setString(3, "invalid");
		
			int num = ps.executeUpdate();
			if(num ==1)
				DBCPUtil.close(rs, stm, con);
				return true;		//成功
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBCPUtil.close(rs, stm, con);
		return false;
	}
	public boolean CommodityBoothAdd(String imgAddr, String linkGoodsId) {
		Connection con = null;
		PreparedStatement ps = null;
		Statement stm = null;
		ResultSet rs = null;
		String sql = "insert into   web_commodity_booth(com_img_address,com_link_goods_id,com_status)   values(?,?,?)" ;
		try {
//	con = DBCPUtil.getInstance().getConnection();
	con=JdbcUint.getConnection();
	
			ps = con.prepareStatement(sql);
			
			ps.setString(1,imgAddr );
			ps.setString(2, linkGoodsId);
			ps.setString(3, "invalid");
		
			int num = ps.executeUpdate();
			if(num ==1)
				DBCPUtil.close(rs, stm, con);
				return true;		//成功
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBCPUtil.close(rs, stm, con);
		return false;
	}
	public ArrayList AdvertingBoothQueryByAccount(String account) {
		Connection con = null;
		PreparedStatement ps = null;
		Statement stm = null;
		ResultSet rs = null; 
		String sql = "";
		try {
//	con = DBCPUtil.getInstance().getConnection();
	con=JdbcUint.getConnection();
			stm = con.createStatement();
			 sql = "select *  from view_adverting_booth where   cus_account='"+account+"' ";
			rs = stm.executeQuery(sql);
			ArrayList< AdvertisingBooth> list =new ArrayList<AdvertisingBooth>();
			while (rs.next()) {
				AdvertisingBooth booth = new AdvertisingBooth();
				booth.setAdvId(rs.getInt("adv_id"));
			
				booth.setImgAddr(rs.getString("adv_img_address"));
				booth.setGoodsId(rs.getInt("adv_link_goods_id"));
				if("invalid".equals(rs.getString("adv_status"))){
					booth.setStatus("申请中");
				}else {
					booth.setStatus("展示中");
				}
				list.add(booth);
			}
			DBCPUtil.close(rs, stm, con);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBCPUtil.close(rs, stm, con);
		return null;
	}
	public Object CommodityBoothQueryByAccount(String account) {
		Connection con = null;
		PreparedStatement ps = null;
		Statement stm = null;
		ResultSet rs = null; 
		String sql = "";
		try {
//	con = DBCPUtil.getInstance().getConnection();
	con=JdbcUint.getConnection();
			stm = con.createStatement();
			 sql = "select *  from view_commodity_booth where   cus_account='"+account+"' ";
			rs = stm.executeQuery(sql);
			ArrayList< CommodityBooth> list =new ArrayList<CommodityBooth>();
			while (rs.next()) {
				CommodityBooth booth = new CommodityBooth();
				booth.setComId(rs.getInt("com_id"));
			System.out.println("you");
				booth.setImgAddr(rs.getString("com_img_address"));
				booth.setGoodsId(rs.getInt("com_link_goods_id"));
				if("invalid".equals(rs.getString("com_status"))){
					booth.setStatus("申请中");
				}else {
					booth.setStatus("展示中");
				}
				list.add(booth);
			}
			DBCPUtil.close(rs, stm, con);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBCPUtil.close(rs, stm, con);
		return null;
	}
	public boolean AdvertingBoothDelete(int id) {
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		String sql  = null;
			sql = "delete from  web_advertising_booth   where adv_id = "+id;
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
	public boolean CommodityBoothDelete(int id) {
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		String sql  = null;
			sql = "delete from  web_commodity_booth   where com_id = "+id;
		try {
//	con = DBCPUtil.getInstance().getConnection();
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
	public boolean WalletQuery(String account, int type) {
		Connection con = null;
		PreparedStatement ps = null;
		Statement stm = null;
		ResultSet rs = null; 
		String sql = "";
		try {
	//con = DBCPUtil.getInstance().getConnection(); 
	con=JdbcUint.getConnection();
			stm = con.createStatement();
			 sql = "select sto_wallet  from web_store where   cus_account='"+account+"' ";
			rs = stm.executeQuery(sql);
			if(rs.next())
				switch (type) {
				case GlobalVariable.AddAdvertisingBooth:
					if(rs.getFloat(1)>=2000){
						DBCPUtil.close(rs, stm, con);
						return true;
						}
					break;
				case GlobalVariable.AddCommodityBooth:
					if(rs.getFloat(1)>=1000){
						DBCPUtil.close(rs, stm, con);
						return true;
					}
					break;
				default:
					break;
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBCPUtil.close(rs, stm, con);
		return false;
	}

}
