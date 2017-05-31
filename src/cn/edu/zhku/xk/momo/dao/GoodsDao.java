package cn.edu.zhku.xk.momo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zhku.xk.momo.been.GoodsBase;
import cn.edu.zhku.xk.momo.been.GoodsForSearch;
import cn.edu.zhku.xk.momo.util.JdbcUint;

public class GoodsDao {
	//¹Ø¼ü´ÊËÑË÷
	public List<GoodsForSearch> SearchBaseInfo(String key){
		String sql="select goo_id, goo_name,goo_title,sto_name,goo_img_addr,"
				+ " goo_volume,goo_min_price from goodsInfoForSearchView where goo_name Like '%"
	+key+"%' or goo_title Like '%"+key+"%' ";
		Connection conn=null;
		Statement stm=null;
		ResultSet rs=null;
		GoodsForSearch goods=null;
		List<GoodsForSearch> goodlist =new ArrayList<GoodsForSearch>();
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
				goodlist.add(goods);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUint.closeConnection(conn,stm,rs);
		}
		return goodlist;
	}
	
	//ÉÌÆ·±àºÅËÑË÷
		public GoodsBase SearchByid(int goodsId){
			String sql="select goo_name,sto_id,goo_det_intr_img_addr,goo_img_addr,goo_title,goo_status "
					+ "from web_goods_base where goo_id ='"+goodsId+"'" ;
			Connection conn=null;
			Statement stm=null;
			ResultSet rs=null;
			GoodsBase goods=null;
			try {
				conn=JdbcUint.getConnection();
				stm = conn.createStatement();
				rs=stm.executeQuery(sql);
				if(rs.next()){
					goods=new GoodsBase();
					goods.setId(goodsId);
					goods.setName(rs.getString("goo_name"));
					goods.setStoreId(rs.getInt("sto_id"));
					goods.setPicAddress(rs.getString("goo_img_addr"));
					goods.setDesc(rs.getString("goo_det_intr_img_addr"));
					goods.setTitle(rs.getString("goo_title"));
					goods.setStatus(rs.getString("goo_status"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				JdbcUint.closeConnection(conn,stm,rs);
			}
			return goods;
		}
}
