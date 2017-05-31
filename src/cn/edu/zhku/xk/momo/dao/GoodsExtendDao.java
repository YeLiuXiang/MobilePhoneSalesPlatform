package cn.edu.zhku.xk.momo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zhku.xk.momo.been.GoodsExtend;
import cn.edu.zhku.xk.momo.util.JdbcUint;

public class GoodsExtendDao {

	public List<GoodsExtend> getGoodsExtendByGoodsId(int goodsId){
		String sql="select mod_id,mod_img_addr,mod_color,mod_stock_num,mod_capacity,"
				+ "mod_volume,mod_price from web_goods_model where  goo_id="+goodsId;
		Connection conn=null;
		Statement stm=null;
		ResultSet rs=null;
		List<GoodsExtend> goodsExtendList=new  ArrayList<>();
		try {
			conn=JdbcUint.getConnection();
			stm = conn.createStatement();
			rs=stm.executeQuery(sql);
			while(rs.next()){
				GoodsExtend goodsExtend=new GoodsExtend();
				goodsExtend.setModelId(rs.getInt(1));
				goodsExtend.setPicAddress(rs.getString(2));
				goodsExtend.setColor(rs.getString(3));
				goodsExtend.setNumber(rs.getInt(4));
				goodsExtend.setCapacity(rs.getString(5));
				goodsExtend.setVolume(rs.getInt(6));
				goodsExtend.setPrice(rs.getFloat(7));
				goodsExtendList.add(goodsExtend);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUint.closeConnection(conn,stm,rs);
		}
		return goodsExtendList;
	}
	public GoodsExtend getGoodsExtendByGoodsId(int goodsId,String color,String capacity){
		String sql="select mod_id,mod_img_addr,mod_color,mod_stock_num,mod_capacity,"
				+ "mod_volume,mod_price from web_goods_model where  goo_id='"+goodsId
				+"' and mod_capacity='"+capacity+"'and mod_color='"+color+"'";
		Connection conn=null;
		Statement stm=null;
		ResultSet rs=null;
		GoodsExtend goodsExtend=new GoodsExtend();
		try {
			conn=JdbcUint.getConnection();
			stm = conn.createStatement();
			rs=stm.executeQuery(sql);
			while(rs.next()){
				
				goodsExtend.setModelId(rs.getInt(1));
				goodsExtend.setPicAddress(rs.getString(2));
				goodsExtend.setColor(rs.getString(3));
				goodsExtend.setNumber(rs.getInt(4));
				goodsExtend.setCapacity(rs.getString(5));
				goodsExtend.setVolume(rs.getInt(6));
				goodsExtend.setPrice(rs.getFloat(7));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUint.closeConnection(conn,stm,rs);
		}
		return goodsExtend;
	}
	public List<String> getGoodsColor(int goodsId,String capacity){
		String sql="select distinct mod_color from web_goods_model "
				+ "where  goo_id='"+goodsId+"' and mod_capacity='"+capacity+"'";
		Connection conn=null;
		Statement stm=null;
		ResultSet rs=null;
		List<String> colorList=new  ArrayList<>();
		try {
			conn=JdbcUint.getConnection();
			stm = conn.createStatement();
			rs=stm.executeQuery(sql);
			while(rs.next()){
				
				colorList.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUint.closeConnection(conn,stm,rs);
		}
		return colorList;
	}
	public List<String> getGoodsCapacity(int goodsId){
		String sql="select distinct mod_capacity from web_goods_model where  goo_id="+goodsId;
		Connection conn=null;
		Statement stm=null;
		ResultSet rs=null;
		List<String> capacityList=new  ArrayList<>();
		try {
			conn=JdbcUint.getConnection();
			stm = conn.createStatement();
			rs=stm.executeQuery(sql);
			while(rs.next()){
				
				capacityList.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUint.closeConnection(conn,stm,rs);
		}
		return capacityList;
	}
}
