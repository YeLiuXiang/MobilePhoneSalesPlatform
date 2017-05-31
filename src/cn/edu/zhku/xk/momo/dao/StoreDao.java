package cn.edu.zhku.xk.momo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cn.edu.zhku.xk.momo.been.Store;
import cn.edu.zhku.xk.momo.util.JdbcUint;


public class StoreDao {
	public Store getStoreByName(String storeName){
		String sql="select sto_id,sto_name,cus_account,sto_img_addres,sto_address,"
				+ "sto_customer_service, sto_status from web_store "
				+ "where sto_name='"+storeName+"'";
		
		Connection conn=null;
		Statement stm=null;
		ResultSet rs=null;
		
		Store store=new Store();
		try {
			conn=JdbcUint.getConnection();
			stm = conn.createStatement();
			rs=stm.executeQuery(sql);
			if(rs.next()){
				store.setId(rs.getInt(1));
				store.setName(rs.getString(2));
				store.setAccount(rs.getString(3));
				store.setImageAddress(rs.getString(4));
				store.setAccount(rs.getString(5));
				store.setCustomerService(rs.getString(6));
				store.setStatus(rs.getString(7));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUint.closeConnection(conn,stm,rs);
		}
		
		
		return store;
		
	}
	
	public boolean isStore(String account){
		String sql="select sto_id,sto_name  from web_store "
				+ "where cus_account='"+account+"'";
		
		Connection conn=null;
		Statement stm=null;
		ResultSet rs=null;
		boolean result=false;
		Store store=new Store();
		try {
			conn=JdbcUint.getConnection();
			stm = conn.createStatement();
			rs=stm.executeQuery(sql);
			if(rs.next()){
				result=true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUint.closeConnection(conn,stm,rs);
		}
		
		
		return result;
		
	}

}
