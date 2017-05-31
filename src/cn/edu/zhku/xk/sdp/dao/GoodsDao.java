package cn.edu.zhku.xk.sdp.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.edu.zhku.xk.momo.util.JdbcUint;
import cn.edu.zhku.xk.sdp.been.GoodsBase;
import cn.edu.zhku.xk.sdp.been.GoodsDetail;
import cn.edu.zhku.xk.sdp.been.GoodsExtend;
import cn.edu.zhku.xk.sdp.been.GoodsIdModelIdStoreId;
import cn.edu.zhku.xk.sdp.been.GoodsShelf;
import cn.edu.zhku.xk.sdp.util.DBCPUtil;
import cn.edu.zhku.xk.sdp.util.GlobalVariable;
import cn.edu.zhku.xk.sdp.util.PageUtil;


public class GoodsDao {

	
	public PageUtil GoodsQueryOff( int currentPage, String account) {
		Connection con = null;
		PreparedStatement ps = null;
		Statement stm = null;
		ResultSet rs = null;
	PageUtil pageObject = null;
		
		try {
			//con = DBCPUtil.getInstance().getConnection();
			con=JdbcUint.getConnection();
			stm = con.createStatement();
			String sqlTotalRows = "select count(distinct view_goods_shelf.goo_id) from view_goods_shelf where  cus_account='"+account+"'  and goo_status = 'unshelf' ";
			rs = stm.executeQuery(sqlTotalRows);
			if (rs.next()) {
				pageObject = new PageUtil(rs.getInt(1), currentPage);
				String sql = "select goo_id,goo_name,goo_img_addr,goo_status   from view_goods_shelf   where   cus_account='"+account+"'  and goo_status = 'unshelf' limit ?,?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, pageObject.getPageStartRow());
				ps.setInt(2, pageObject.getPageSize());
				rs.close();
				rs = ps.executeQuery();
			List<GoodsShelf> goodsList = new ArrayList<GoodsShelf>();
			while (rs.next()) {
				GoodsShelf goods = new GoodsShelf();
				goods.setId(rs.getInt("goo_id"));
				goods.setName(rs.getString("goo_name"));
				goods.setPicture(rs.getString("goo_img_addr"));
				if ("unshelf".equals(rs.getString("goo_status"))) {
					goods.setStatus("未上架");
				} else {
					goods.setStatus("已上架");
				}
				goodsList.add(goods);
				}
			pageObject.setList(goodsList);
			}
				DBCPUtil.close(rs, stm, con);
				return pageObject;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBCPUtil.close(rs, stm, con);
		return null;
	}
	
	public PageUtil GoodsQueryOn( int currentPage, String account) {
		Connection con = null;
		PreparedStatement ps = null;
		Statement stm = null;
		ResultSet rs = null;
	PageUtil pageObject = null;
		try {
			//con = DBCPUtil.getInstance().getConnection();
			con=JdbcUint.getConnection();
			stm = con.createStatement();
			String sqlTotalRows = "select count(*) from view_goods_shelf where cus_account='"+account+"'  and goo_status = 'shelf'";
			rs = stm.executeQuery(sqlTotalRows);
			if (rs.next()) {
				pageObject = new PageUtil(rs.getInt(1), currentPage);
				String sql = "select goo_id,goo_name,goo_img_addr,goo_status  from view_goods_shelf where cus_account='"+account+"'  and goo_status = 'shelf' limit ?,?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, pageObject.getPageStartRow());
				ps.setInt(2, pageObject.getPageSize());
				rs.close();
				rs = ps.executeQuery();
			List<GoodsShelf> goodsList = new ArrayList<GoodsShelf>();
			while (rs.next()) {
				GoodsShelf goods = new GoodsShelf();
				goods.setId(rs.getInt("goo_id"));
				goods.setName(rs.getString("goo_name"));
				goods.setPicture(rs.getString("goo_img_addr"));
				if ("unshelf".equals(rs.getString("goo_status"))) {
					goods.setStatus("未上架");
				} else {
					goods.setStatus("已上架");
				}
				goodsList.add(goods);
				}
			pageObject.setList(goodsList);
			}
				DBCPUtil.close(rs, stm, con);
				return pageObject;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBCPUtil.close(rs, stm, con);
		return null;
	}
	
	public PageUtil GoodsQueryById(int searchType,int id, String account ) {
		Connection con = null;
		PreparedStatement ps = null;
		Statement stm = null;
		ResultSet rs = null;
		String sql="";
		PageUtil pageObject =  new PageUtil(1,1);
			
	// 特定编号商品
		if(searchType==GlobalVariable.OffShelfQuery){//未上架
			sql = "select *  from  view_goods_shelf where cus_account='"+account+"'  and goo_status ='unshelf' and goo_id = "+ id;
		}else if(searchType==GlobalVariable.OnShelfQuery){//在架
			sql = "select * from  view_goods_shelf where cus_account='"+account+"'  and  goo_status = 'shelf' and goo_id = "+id;
			
		}
		try {
			//con = DBCPUtil.getInstance().getConnection(); 
			con=JdbcUint.getConnection();
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			List<GoodsShelf> goodsList = new ArrayList<GoodsShelf>();
			if (rs.next()) {

				GoodsShelf goods = new GoodsShelf();
				goods.setId(rs.getInt("goo_id"));
				goods.setName(rs.getString("goo_name"));
				goods.setPicture(rs.getString("goo_img_addr"));
				if ("unshelf".equals(rs.getString("goo_status"))) {
					goods.setStatus("未上架");
				} else {
					goods.setStatus("已上架");
				}
				goodsList.add(goods);
				pageObject.setList(goodsList);
			}

			DBCPUtil.close(rs, stm, con);
			return pageObject;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBCPUtil.close(rs, stm, con);
		return null;
	}

	
	
	public PageUtil GoodsQueryByName(int searchType,String name, String account ) {
		Connection con = null;
		PreparedStatement ps = null;
		Statement stm = null;
		ResultSet rs = null;
		String sql="";
		PageUtil pageObject =  new PageUtil(1,1);
			
	
	// 特定编号商品
		if(searchType==0){//未上架
			sql = "select * from  view_goods_shelf where cus_account='"+account+"'  and goo_status ='unshelf' and goo_name = '"+ name+"'";
		}else if(searchType==1){//在架
			sql = "select * from  view_goods_shelf where cus_account='"+account+"'  and goo_status = 'shelf'    and goo_name = '"+ name+"'";
			
		}
		System.out.println(sql);
		try {
			//con = DBCPUtil.getInstance().getConnection();
			con=JdbcUint.getConnection();
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			List<GoodsShelf> goodsList = new ArrayList<GoodsShelf>();
			if (rs.next()) {

				GoodsShelf goods = new GoodsShelf();
				goods.setId(rs.getInt("goo_id"));
				goods.setName(rs.getString("goo_name"));
				goods.setPicture(rs.getString("goo_img_addr"));
				if ("unshelf".equals(rs.getString("goo_status"))) {
					goods.setStatus("未上架");
				} else {
					goods.setStatus("已上架");
				}
				goodsList.add(goods);
				pageObject.setList(goodsList);
			}

			DBCPUtil.close(rs, stm, con);
			return pageObject;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBCPUtil.close(rs, stm, con);
		return null;
	}

	public boolean OrderStatusModify(int id,int statusType) {
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		String sql  = null;
		if(statusType ==GlobalVariable.GoodsUpShelf)
			sql = "update  web_goods_base  set  goo_status  = 'shelf' where goo_id = "+id;
		else if(statusType ==GlobalVariable.GoodsOffShelf){
			 sql = "update  web_goods_base  set  goo_status  = 'unshelf' where goo_id = "+id;
		}
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

	public boolean GoodsBaseAdd(GoodsBase goodsBase) {
		Connection con = null;
		PreparedStatement ps = null;
		Statement stm = null;
		ResultSet rs = null;
		String sql = "insert into   web_goods_base(goo_name,sto_id,goo_up_time,goo_det_intr_img_addr,goo_title,goo_status,goo_img_addr)   values(?,?,?,?,?,?,?)" ;
		Date date = new Date();
		try {
			//con = DBCPUtil.getInstance().getConnection(); 
			con=JdbcUint.getConnection();
	
			ps = con.prepareStatement(sql);
			
	//		ps.setInt(1, goodsBase.getId());
			ps.setString(1,goodsBase.getName() );
			ps.setInt(2, goodsBase.getStoreId());
			ps.setDate(3, new java.sql.Date(goodsBase.getUpTime().getTime()));
			ps.setString(4, goodsBase.getDesc());
			ps.setString(5,goodsBase.getTitle());
			ps.setString(6,goodsBase.getStatus());
			ps.setString(7,goodsBase.getImgAddr());
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

	public boolean GoodsModelAdd(ArrayList<GoodsExtend> goodsList) {
		Connection con = null;
		PreparedStatement ps = null;
		Statement stm = null;
		ResultSet rs = null;
		
		String sql = "insert into   web_goods_model(mod_img_addr,goo_id,mod_color,mod_stock_num,mod_capacity,mod_volume,mod_price)   values(?,?,?,?,?,?,?)" ;
		Date date = new Date();
		try {
			//con = DBCPUtil.getInstance().getConnection();
			con=JdbcUint.getConnection();
			for (int i = 0; i < goodsList.size(); i++) {

				ps = con.prepareStatement(sql);
				ps.setString(1, goodsList.get(i).getPicAddress());
				ps.setInt(2, goodsList.get(i).getGoodsId());
				ps.setString(3, goodsList.get(i).getColor());
				ps.setInt(4, goodsList.get(i).getNumber());
				ps.setString(5, goodsList.get(i).getCapacity());
				ps.setInt(6, goodsList.get(i).getVolume());
				ps.setFloat(7, goodsList.get(i).getPrice());
				int num = ps.executeUpdate();
				if (num != 1) {
					DBCPUtil.close(rs, stm, con);
					return false;
				}
			}
			DBCPUtil.close(rs, stm, con);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBCPUtil.close(rs, stm, con);
		return false;

	}

	public GoodsIdModelIdStoreId  GidMidSidQuery(String account) {
		// 商品编号、商品型号、店铺编号查询
		
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		CallableStatement cs = null;
		String sql = "{call pr_GidMidSid(?,?,?,?)}";
		try {
			//con = DBCPUtil.getInstance().getConnection(); 
			con=JdbcUint.getConnection();
			cs = con.prepareCall(sql);
			cs.setString(1, account);
			cs.execute();
			GoodsIdModelIdStoreId gidMidSid = new GoodsIdModelIdStoreId(cs.getInt("goodsId"), cs.getInt("modelId"), cs.getInt("storeId"));
			System.out.println( "g:"+cs.getInt("goodsId")+",m:"+cs.getInt("modelId")+",s:"+cs.getInt("storeId"));
			DBCPUtil.close(rs, stm, con);
			cs.close();
				return gidMidSid;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBCPUtil.close(rs, stm, con);
			try {
				cs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return null;
	}

	public GoodsDetail GoodsDetailQuery(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		Statement stm = null;
		ResultSet rs = null;
		String sql= "select * from  web_goods_base where  goo_id = "+id;
		String sql2= "select * from  web_goods_model where  goo_id = "+id;
			
		try {
			//con = DBCPUtil.getInstance().getConnection(); 
			con=JdbcUint.getConnection();
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			
			
				if (rs.next()) {

				GoodsDetail goods = new GoodsDetail();
				goods.setId(rs.getInt("goo_id"));
				goods.setName(rs.getString("goo_name"));
				goods.setTitle(rs.getString("goo_title"));
				goods.setDesc(rs.getString("goo_det_intr_img_addr"));
				rs = stm.executeQuery(sql2);
				ArrayList goodsList = new ArrayList<GoodsExtend>();
				
				while(rs.next()){
					GoodsExtend goodsModel = new GoodsExtend();
					goodsModel.setModelId(rs.getInt("mod_id"));
					goodsModel.setCapacity(rs.getString("mod_capacity"));
					goodsModel.setColor(rs.getString("mod_color"));
					goodsModel.setNumber(rs.getInt("mod_stock_num"));
					goodsModel.setPrice(rs.getFloat("mod_price"));
					goodsModel.setPicAddress(rs.getString("mod_img_addr"));
					goodsList.add(goodsModel);
				}
				goods.setGoodsList(goodsList);
				
				
				DBCPUtil.close(rs, stm, con);
				return goods;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBCPUtil.close(rs, stm, con);
		return null;
	}

	public boolean GoodsTitleModify(int id, String title) {
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		String sql  = null;
			 sql = "update  web_goods_base  set  goo_title  = '"+title+"' where goo_id = "+id;
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

	public boolean GoodsModify(int id, int stockNumber, float price) {
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		String sql  = null;
			 sql = "update  web_goods_model  set  mod_stock_num  = "+stockNumber+" ,  mod_price="+price+" where mod_id = "+id;
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

	public Object GoodsNameQuery(String account) {
		Connection con = null;
		PreparedStatement ps = null;
		Statement stm = null;
		ResultSet rs = null;
		String sql= "select goo_name,goo_id from  view_booth_goods_name where  cus_account = '"+account+"'";
			
		try {
			//con = DBCPUtil.getInstance().getConnection();
			con=JdbcUint.getConnection();
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			ArrayList<GoodsBase> list = new ArrayList<>();
			while (rs.next()) {
				GoodsBase goods = new GoodsBase();
				goods.setId(rs.getInt("goo_id"));
				goods.setName(rs.getString("goo_name"));
				list.add(goods);
			}
			DBCPUtil.close(rs, stm, con);
			return list;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBCPUtil.close(rs, stm, con);
		return null;
	}
	
	
}
