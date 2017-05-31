package cn.edu.zhku.xk.momo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zhku.xk.momo.been.AdvertisingBooth;
import cn.edu.zhku.xk.momo.been.CommodityBooth;
import cn.edu.zhku.xk.momo.been.GoodsForSearch;
import cn.edu.zhku.xk.momo.util.JdbcUint;

public class AdvertiseDao {
	public List<GoodsForSearch> getCommodityBooth(){
		String sql="select goo_id, goo_name,goo_title,sto_name,goo_img_addr,"
				+ " goo_volume,goo_min_price from CommodityBoothView ";
		Connection conn=null;
		Statement stm=null;
		ResultSet rs=null;
		GoodsForSearch goods=null;
		List<GoodsForSearch> commodityBoothList =new ArrayList<GoodsForSearch>();
		try {
			conn=JdbcUint.getConnection();
			stm = conn.createStatement();
			rs=stm.executeQuery(sql);
			while(rs.next()){
				goods=new GoodsForSearch();
				goods.setId(rs.getInt("goo_id"));
				goods.setName(rs.getString("goo_name"));
				goods.setTitle(rs.getString("goo_title"));
				goods.setStoreName(rs.getString("sto_name"));
				goods.setPictureAddress(rs.getString("goo_img_addr"));
				goods.setVolume(rs.getInt("goo_volume"));
				goods.setPrice(rs.getFloat("goo_min_price"));
				commodityBoothList.add(goods);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUint.closeConnection(conn,stm,rs);
		}
		return commodityBoothList;
	}
	/*public  List<CommodityBooth> getCommodityBooth(){
		String sql="select goo_id,goo_name,goo_title,com_img_address,price from CommodityBoothView ";
		Connection conn=null;
		Statement stm=null;
		ResultSet rs=null;
		List<CommodityBooth> commodityBoothList=new ArrayList<CommodityBooth>();
		
		try {
			conn=JdbcUint.getConnection();
			stm = conn.createStatement();
			rs=stm.executeQuery(sql);
			while(rs.next()){
				CommodityBooth commodityBooth=new CommodityBooth();
				commodityBooth.setGoodsId(rs.getInt(1));
				commodityBooth.setName(rs.getString(2));
				commodityBooth.setTitle(rs.getString(3));
				commodityBooth.setPicAddress(rs.getString(4));
				commodityBooth.setPrice(rs.getFloat(5));
				commodityBoothList.add(commodityBooth);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUint.closeConnection(conn,stm,rs);
		}
		return commodityBoothList;
	}*/
	public  List<AdvertisingBooth> getAdvertiseBooth(){
		String sql="select adv_link_goods_id,adv_img_address from web_advertising_booth where adv_status='valid' ";
		Connection conn=null;
		Statement stm=null;
		ResultSet rs=null;
		List<AdvertisingBooth> advertisingBoothList=new ArrayList<AdvertisingBooth>();
		
		try {
			conn=JdbcUint.getConnection();
			stm = conn.createStatement();
			rs=stm.executeQuery(sql);
			while(rs.next()){
				AdvertisingBooth advertisingBooth=new AdvertisingBooth();
				advertisingBooth.setGoodsId(rs.getInt(1));
				advertisingBooth.setImgAddr(rs.getString(2));
				advertisingBoothList.add(advertisingBooth);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUint.closeConnection(conn,stm,rs);
		}
		return advertisingBoothList;
	}
}
