package cn.edu.zhku.xk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zhku.xk.momo.util.JdbcUint;
import cn.edu.zhku.xk.sdp.been.AdvertisingBooth;
import cn.edu.zhku.xk.sdp.been.CommodityBooth;
import cn.edu.zhku.xk.sdp.util.DBCPUtil;
import cn.edu.zhku.xk.sdp.util.PageUtil;



public class BoothDao {

	public boolean AdvertingBoothStatusModify(int id) {
		
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		int num=0;
//		String sql  = "update  web_advertising_booth  set  adv_status  = 'invalid' where adv_id = min(adv_id)";;
		String	 sql2 = "update  web_advertising_booth  set  adv_status  = 'valid' where adv_id = "+id;
		
		try {
				//con = DBCPUtil.getInstance().getConnection();con=JdbcUint.getConnection();
			stm = con.createStatement();
//			 num = stm.executeUpdate(sql);
//			if(num!=0){
				num = stm.executeUpdate(sql2);
				if(num!=0){
					DBCPUtil.close(rs, stm, con);
					return true;
				}
//			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBCPUtil.close(rs, stm, con);
		return false;
	}
public boolean AdvertingBoothDelete(int id) {
		
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		int num=0;
//		String sql  = "update  web_advertising_booth  set  adv_status  = 'invalid' where adv_id = min(adv_id)";;
		String	 sql2 = "delete from  web_advertising_booth  where adv_id  = "+id;
		
		try {
				//con = DBCPUtil.getInstance().getConnection();con=JdbcUint.getConnection();
			stm = con.createStatement();
//			 num = stm.executeUpdate(sql);
//			if(num!=0){
				num = stm.executeUpdate(sql2);
				if(num!=0){
					DBCPUtil.close(rs, stm, con);
					return true;
				}
//			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBCPUtil.close(rs, stm, con);
		return false;
	}
	public boolean CommodityBoothStatusModify(int id) {
		
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		String sql  = null;
			 sql = "update  web_commodity_booth  set  com_status  = 'valid' where com_id = "+id;
		
		try {
				//con = DBCPUtil.getInstance().getConnection();con=JdbcUint.getConnection();
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
			 sql = "delete from  web_commodity_booth where com_id = "+id;
		
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
	public PageUtil AdvertingBoothQuery() {
		Connection con = null;
		PreparedStatement ps = null;
		Statement stm = null;
		ResultSet rs = null;
	PageUtil pageObject = null;
		
		try {
				//con = DBCPUtil.getInstance().getConnection();
			con=JdbcUint.getConnection();
			stm = con.createStatement();
	String sqlTotalRows = "select count(distinct view_adverting_booth .adv_id) from view_adverting_booth where adv_status='valid'";
			rs = stm.executeQuery(sqlTotalRows);
			if (rs.next()) {
				pageObject = new PageUtil(rs.getInt(1), 1);
				String sql = "select*  from view_adverting_booth where  adv_status='valid'   ";
				ps = con.prepareStatement(sql);
				rs.close();
				rs = ps.executeQuery();
			List<AdvertisingBooth> list = new ArrayList<AdvertisingBooth>();
			int i = 1;
			while (rs.next()) {
				AdvertisingBooth booth = new AdvertisingBooth();
				booth.setNo(i++);
				booth.setAdvId(rs.getInt("adv_id"));
				booth.setStoreName(rs.getString("sto_name"));
				booth.setImgAddr(rs.getString("adv_img_address"));
				booth.setGoodsId(rs.getInt("adv_link_goods_id"));
				booth.setStatus("展示中");
				list.add(booth);
				}
			pageObject.setList(list);
			}
				DBCPUtil.close(rs, stm, con);
				return pageObject;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBCPUtil.close(rs, stm, con);
		return null;
	}
	public PageUtil AdvertingBoothApplyQuery(int currentPage) {
		Connection con = null;
		PreparedStatement ps = null;
		Statement stm = null;
		ResultSet rs = null;
	PageUtil pageObject = null;
		
		try {
				//con = DBCPUtil.getInstance().getConnection();
			con=JdbcUint.getConnection();
			stm = con.createStatement();
					String sqlTotalRows = "select count(distinct view_adverting_booth .adv_id) from view_adverting_booth where adv_status='invalid'";
			rs = stm.executeQuery(sqlTotalRows);
			if (rs.next()) {
				pageObject = new PageUtil(rs.getInt(1), currentPage);
				String sql = "select*  from view_adverting_booth where  adv_status='invalid'   limit ?,?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, pageObject.getPageStartRow());
				ps.setInt(2, pageObject.getPageSize());
				rs.close();
				rs = ps.executeQuery();
			List<AdvertisingBooth> list = new ArrayList<AdvertisingBooth>();
			int i = pageObject.getPageSize()*(currentPage-1)+1;
			while (rs.next()) {
				AdvertisingBooth booth = new AdvertisingBooth();
				booth.setNo(i++);
				booth.setAdvId(rs.getInt("adv_id"));
				booth.setStoreName(rs.getString("sto_name"));
				booth.setImgAddr(rs.getString("adv_img_address"));
				booth.setGoodsId(rs.getInt("adv_link_goods_id"));
				booth.setStatus("申请中");
				list.add(booth);
				}
			pageObject.setList(list);
			}
				DBCPUtil.close(rs, stm, con);
				return pageObject;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBCPUtil.close(rs, stm, con);
		return null;
	}
	public PageUtil CommodityBoothQuery(int currentPage) {
		Connection con = null;
		PreparedStatement ps = null;
		Statement stm = null;
		ResultSet rs = null;
		PageUtil pageObject = null;

		try {
				//con = DBCPUtil.getInstance().getConnection();
			con=JdbcUint.getConnection();
			stm = con.createStatement();
			String sqlTotalRows = "select count(distinct view_commodity_booth .com_id) from view_commodity_booth where com_status='valid'";
			rs = stm.executeQuery(sqlTotalRows);
			if (rs.next()) {
				pageObject = new PageUtil(rs.getInt(1), currentPage);
				String sql = "select*  from view_commodity_booth where  com_status='valid'   limit ?,?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, pageObject.getPageStartRow());
				ps.setInt(2, pageObject.getPageSize());
				rs.close();
				rs = ps.executeQuery();
				List<CommodityBooth> list = new ArrayList<CommodityBooth>();
				int i = 1;
				while (rs.next()) {
					CommodityBooth booth = new CommodityBooth();
					booth.setNo(i++);
					booth.setComId(rs.getInt("com_id"));
					booth.setStoreName(rs.getString("sto_name"));
					booth.setImgAddr(rs.getString("com_img_address"));
					booth.setGoodsId(rs.getInt("com_link_goods_id"));
					booth.setStatus("展示中");
					list.add(booth);
				}
				pageObject.setList(list);
			}
			DBCPUtil.close(rs, stm, con);
			return pageObject;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBCPUtil.close(rs, stm, con);
		return null;
	}
	public PageUtil CommodityBoothApplyQuery(int currentPage) {
		Connection con = null;
		PreparedStatement ps = null;
		Statement stm = null;
		ResultSet rs = null;
	PageUtil pageObject = null;
		
		try {
				//con = DBCPUtil.getInstance().getConnection();
			con=JdbcUint.getConnection();
			stm = con.createStatement();
					String sqlTotalRows = "select count(distinct view_commodity_booth .com_id) from view_commodity_booth where com_status='invalid'";
			rs = stm.executeQuery(sqlTotalRows);
			if (rs.next()) {
				pageObject = new PageUtil(rs.getInt(1), currentPage);
				String sql = "select*  from view_commodity_booth where  com_status='invalid'   limit ?,?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, pageObject.getPageStartRow());
				ps.setInt(2, pageObject.getPageSize());
				rs.close();
				rs = ps.executeQuery();
			List<CommodityBooth> list = new ArrayList<CommodityBooth>();
			int i = pageObject.getPageSize()*(currentPage-1)+1;
			while (rs.next()) {
				CommodityBooth booth = new CommodityBooth();
				booth.setNo(i++);
				booth.setComId(rs.getInt("com_id"));
				booth.setStoreName(rs.getString("sto_name"));
				booth.setImgAddr(rs.getString("com_img_address"));
				booth.setGoodsId(rs.getInt("com_link_goods_id"));
				booth.setStatus("申请中");
				list.add(booth);
				}
			pageObject.setList(list);
			}
				DBCPUtil.close(rs, stm, con);
				return pageObject;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBCPUtil.close(rs, stm, con);
		return null;
	}
	
}
