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
import cn.edu.zhku.xk.sdp.been.GoodsShelf;
import cn.edu.zhku.xk.sdp.been.Store;
import cn.edu.zhku.xk.sdp.been.StoreModify;
import cn.edu.zhku.xk.sdp.util.DBCPUtil;
import cn.edu.zhku.xk.sdp.util.GlobalVariable;
import cn.edu.zhku.xk.sdp.util.PageUtil;

public class StoreDao {

	public StoreModify StoreModifyMessageQuery(String account) {
		Connection con = null;
		PreparedStatement ps = null;
		Statement stm = null;
		ResultSet rs = null;
		String sql=null;
			
			sql = "select sto_customer_service,sto_img_addres  from  web_store where cus_account = '"+account+"'";
			
		
		try {
					//con = DBCPUtil.getInstance().getConnection();
			con=JdbcUint.getConnection();
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			if (rs.next()) {

				StoreModify storeModify = new StoreModify();
				storeModify.setImageAddress(rs.getString("sto_img_addres"));
				storeModify.setCustomerService(rs.getString("sto_customer_service"));
				DBCPUtil.close(rs, stm, con);
				return storeModify;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBCPUtil.close(rs, stm, con);
		return null;
	}
	public boolean StoreMessageModify(String account,String customerService,String imgAddr) {
		Connection con = null;
		PreparedStatement ps = null;
		Statement stm = null;
		ResultSet rs = null;
		String sql=null;
			
			sql = "update  web_store  set  sto_customer_service  = '"+customerService+"' and set sto_img_addres = '"+imgAddr+"' where cus_account = "+account;
			
		
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
	public Store StoreMessageQuery(String account) {
		Connection con = null;
		PreparedStatement ps = null;
		Statement stm = null;
		ResultSet rs = null;
		String sql=null;
			sql = "select *  from  web_store where cus_account = '"+account+"'";
		try {
					//con = DBCPUtil.getInstance().getConnection();
			con=JdbcUint.getConnection();
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			if (rs.next()) {

				Store store = new Store();
				store.setId(rs.getInt("sto_id"));
				store.setName(rs.getString("sto_name"));
				store.setAccount(rs.getString("cus_account"));
				store.setAddress(rs.getString("sto_address"));
				store.setImageAddress(rs.getString("sto_img_addres"));
				store.setCustomerService(rs.getString("sto_customer_service"));
				if ("operating".equals(rs.getString("sto_status"))) {
					store.setStatus("运营中");
				} else {
					store.setStatus("冻结");
				}
				DBCPUtil.close(rs, stm, con);
				return store;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBCPUtil.close(rs, stm, con);
		return null;
	}
	public boolean walletModify(float money, String account) {
		Connection con = null;
		PreparedStatement ps = null;
		Statement stm = null;
		ResultSet rs = null;
		String sql=null;
			
			sql = "update  web_store  set  sto_wallet  = sto_wallet+"+money+"  where cus_account = '"+account+"'";
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
	
}
