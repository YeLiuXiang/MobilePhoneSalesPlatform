package cn.edu.zhku.xk.sdp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.edu.zhku.xk.momo.util.JdbcUint;
import cn.edu.zhku.xk.sdp.been.GoodsSalesRank;
import cn.edu.zhku.xk.sdp.been.Order;
import cn.edu.zhku.xk.sdp.been.OrderDetail;
import cn.edu.zhku.xk.sdp.been.OrderGoods;
import cn.edu.zhku.xk.sdp.been.SalesQuantityStatics;
import cn.edu.zhku.xk.sdp.been.SalesStatics;
import cn.edu.zhku.xk.sdp.util.DBCPUtil;
import cn.edu.zhku.xk.sdp.util.GlobalVariable;
import cn.edu.zhku.xk.sdp.util.PageUtil;


public class OrderDao {

	public PageUtil OrderQuery(int searchType,int currentPage, String account) {
		Connection con = null;
		PreparedStatement ps = null;
		Statement stm = null;
		ResultSet rs = null; 
		String sql = "";
		String sqlTotalRows = "";
		PageUtil pageObject = null;

		try {

			//con = DBCPUtil.getInstance().getConnection();
			con=JdbcUint.getConnection();
			stm = con.createStatement();
			switch(searchType){
			case 0:// 未发货
				sqlTotalRows = "select count(*) from view_order where cus2_account='"+account+"'  and ord_status ='waitToSend'";
				break;
			case 1:// 已发货
				sqlTotalRows = "select count(*) from view_order where cus2_account='"+account+"'  and ord_status ='waitToReceive'";
				break;
			case 2:// 已签收
				sqlTotalRows = "select count(*) from view_order where cus2_account='"+account+"'  and ord_status ='received'";
				break;
			default:
				break;
			}

			rs = stm.executeQuery(sqlTotalRows);
			if (rs.next()) {
				pageObject = new PageUtil(rs.getInt(1), currentPage);
				switch(searchType){
				case 0:// 未发货
					sql = "select ord_id, cus_account,ord_order_time, ord_address,ord_receiver_phone from view_order  where  cus2_account='"+account+"'  and ord_status = ? order by ord_id  limit ?,? ";
					break;
				case 1:// 已发货
				
				case 2:// 已签收
					sql = "select ord_id, cus_account,ord_order_time, ord_address,ord_receiver_phone from view_order  where cus2_account='"+account+"'  and  ord_status = ? order by ord_id desc limit ?,? ";
					break;
				default:
					sql = "";
					break;
				}
				ps = con.prepareStatement(sql);
				ps.setInt(2, pageObject.getPageStartRow());

				ps.setInt(3, pageObject.getPageSize());
		
				switch(searchType){
				case 0:// 未发货
					ps.setString(1, "waitToSend");
					break;
				case 1:// 已发货
					ps.setString(1, "waitToReceive");
					break;
				case 2:// 已签收
					ps.setString(1, "received");
					break;
				default:
					break;
				}

				rs = ps.executeQuery();
				List<Order> list  = new ArrayList<Order>();
				while (rs.next()) {
					Order order = new Order();

					order.setOrderId(rs.getInt("ord_id"));
					order.setCustomerAccount(rs.getString("cus_account"));
					order.setTime(new Date(rs.getTimestamp("ord_order_time").getTime()));	
					order.setReceiverPhone(rs.getString("ord_receiver_phone"));
					order.setAddress(rs.getString("ord_address"));
					list.add(order);
				}
				pageObject.setList(list);
			}
			//	OrderGoods orderGoods = new OrderGoods(i++,rs.getInt("mID"),rs.getString("gName"),rs.getFloat("gPrice"),rs.getInt("oNum"));
			DBCPUtil.close(rs, stm, con);
			return pageObject;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBCPUtil.close(rs, stm, con);
		return null;

	}
	
	public PageUtil OrderQueryById(int id,int searchType, String account) {
		Connection con = null;
		PreparedStatement ps = null;
		Statement stm = null;
		ResultSet rs = null; 
		String sql = "";
		String sqlTotalRows = "";
		PageUtil pageObject = null;

		try {

			//con = DBCPUtil.getInstance().getConnection();
			con=JdbcUint.getConnection();
			stm = con.createStatement();
			sql = "select ord_id, cus_account,ord_order_time, ord_address,ord_receiver_phone from view_order  where cus2_account='"+account+"'  and ord_status = ? and  ord_id = ?  ";
			ps = con.prepareStatement(sql);
	
			ps.setInt(2, id);
			switch(searchType){
			case 0:// 未发货
				ps.setString(1, "waitToSend");
				break;
			case 1:// 已发货
				ps.setString(1, "waitToReceive");
				break;
			case 2:// 已签收
				ps.setString(1, "received");
				break;
			default:
				break;
			}

			rs = ps.executeQuery();
			List<Order> list  = new ArrayList<Order>();
			if (rs.next()) {
				Order order = new Order();
				order.setOrderId(rs.getInt("ord_id"));
				order.setCustomerAccount(rs.getString("cus_account"));
				order.setTime(new Date(rs.getTimestamp("ord_order_time").getTime()));	
				order.setReceiverPhone(rs.getString("ord_receiver_phone"));
				order.setAddress(rs.getString("ord_address"));
				list.add(order);
			}
			pageObject = new PageUtil(1, 1);
			pageObject.setList(list);

			DBCPUtil.close(rs, stm, con);
			return pageObject;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public PageUtil OrderDetailQuery(int id,int currentPage ) {
		Connection con = null;
		PreparedStatement ps = null;
		Statement stm = null;
		ResultSet rs = null;
		String sqlTotalRows = "";
		PageUtil pageObject = null;
		try {
			//con = DBCPUtil.getInstance().getConnection();
			con=JdbcUint.getConnection();
			stm = con.createStatement();
			sqlTotalRows = "select count(*) from view_order_detail where  ord_goo_id = "+id;

			rs = stm.executeQuery(sqlTotalRows);
			if (rs.next()) {
				pageObject = new PageUtil(rs.getInt(1), currentPage);

				String sql = "select ord_goo_id, mod_id,goo_name, ord_goo_order_num,ord_goo_price from view_order_detail  where   ord_goo_id =?  limit ?,? ";
				ps = con.prepareStatement(sql);
				ps.setInt(1, id);
				ps.setInt(2, pageObject.getPageStartRow());
				ps.setInt(3, pageObject.getPageSize());
				rs = ps.executeQuery();
				List<OrderDetail> list = new ArrayList<OrderDetail>();
				int i = pageObject.getPageSize()*(currentPage-1)+1;
				while(rs.next()){
					OrderDetail  order = new OrderDetail();
					order.setNo(i++);
					order.setModelId(rs.getInt("mod_id"));
					order.setName(rs.getString("goo_name"));

					order.setNumber(rs.getInt("ord_goo_order_num"));
					order.setPrice(rs.getInt("ord_goo_price"));
					list.add(order);
				}
				pageObject.setList(list);

				DBCPUtil.close(rs, stm, con);
				return pageObject;
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		DBCPUtil.close(rs, stm, con);
			return null;

		}
	
	public boolean OrderStatusModify(int id,String status) {
		Connection con = null;
		PreparedStatement ps = null;
		Statement stm = null;
		ResultSet rs = null; 
		String sql = "";
		
		try {
			
			//con = DBCPUtil.getInstance().getConnection();
			con=JdbcUint.getConnection();
			stm = con.createStatement();
		
			sql = "update   web_order set ord_status = '"+status+"'  where ord_id ="+id;
			
			
			int num = stm.executeUpdate(sql);
			if (num!=0) {
				DBCPUtil.close(rs, stm, con);
				return true;
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBCPUtil.close(rs, stm, con);
		return false;
	}

	/**
	 * 销售额统计
	 * @param account
	 * @return
	 */
	public ArrayList SalesQuery(String account) {
		Connection con = null;
		PreparedStatement ps = null;
		Statement stm = null;
		ResultSet rs = null; 
		String sql = "";
		
		try {
			
			//con = DBCPUtil.getInstance().getConnection();
			con=JdbcUint.getConnection();
			stm = con.createStatement();
		
			sql = "select sum(ord_goo_total_price)   from view_statics where cus_account='"+account+"'";
			
			String sql2 = "select DATE_FORMAT(ord_order_time,'%Y-%m') as month,sum(ord_goo_total_price)  as money  from view_statics where DATE_FORMAT(ord_order_time,'%Y')=2017 and  cus_account='"+account+"' group by month order by month";
			

			rs=stm.executeQuery(sql2);
			ArrayList< SalesStatics> list = new ArrayList<SalesStatics>();
			while(rs.next()){
				SalesStatics salesStatics = new SalesStatics();
				salesStatics.setDate(rs.getString("month"));
				salesStatics.setMoney(rs.getFloat("money"));
				list.add(salesStatics);
			}
			DBCPUtil.close(rs, stm, con);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBCPUtil.close(rs, stm, con);
		return null;
	}
	
	/**
	 * 销售量统计
	 * @param account
	 * @return
	 */
	public ArrayList SalesQuantityQuery(String account) {
		Connection con = null;
		PreparedStatement ps = null;
		Statement stm = null;
		ResultSet rs = null; 
		String sql = "";
		
		try {
			
			//con = DBCPUtil.getInstance().getConnection();
			con=JdbcUint.getConnection();
			stm = con.createStatement();
		
			
			 sql = "select DATE_FORMAT(ord_order_time,'%Y-%m') as month,sum(ord_goo_order_num)  as quantity  from view_statics where DATE_FORMAT(ord_order_time,'%Y')=2017 and  cus_account='"+account+"' group by month order by month";
			rs = stm.executeQuery(sql);
			ArrayList< SalesQuantityStatics> list =new ArrayList<SalesQuantityStatics>();
			while (rs.next()) {
				SalesQuantityStatics salesQuantity = new SalesQuantityStatics();
				salesQuantity.setDate(rs.getString("month"));
				salesQuantity.setNumber(rs.getInt("quantity"));
				list.add(salesQuantity);
			}
			DBCPUtil.close(rs, stm, con);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBCPUtil.close(rs, stm, con);
		return null;
	}
	
	/**
	 * 商品销售量排行查询
	 * @param account
	 * @return
	 */
	public ArrayList SalesQuantityQuery2(String account) {
		Connection con = null;
		PreparedStatement ps = null;
		Statement stm = null;
		ResultSet rs = null; 
		String sql = "";
		
		try {
			
			//con = DBCPUtil.getInstance().getConnection();
			con=JdbcUint.getConnection();
			stm = con.createStatement();
		System.out.println(account);
			sql = "select mod_id,goo_name,mod_volume   from view_statics_rank where cus_account='"+account+"' order by mod_volume desc  limit 10";
			rs = stm.executeQuery(sql);
			ArrayList< GoodsSalesRank> list =new ArrayList<GoodsSalesRank>();
			int i = 1;
			while (rs.next()) {
				GoodsSalesRank goods = new GoodsSalesRank();
				goods.setNo(i++);
				goods.setId(rs.getInt("mod_id"));
				goods.setName(rs.getString("goo_name"));
				goods.setVolume(rs.getInt("mod_volume"));
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
