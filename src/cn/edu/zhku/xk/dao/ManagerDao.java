package cn.edu.zhku.xk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cn.edu.zhku.xk.momo.util.JdbcUint;
import cn.edu.zhku.xk.sdp.util.DBCPUtil;


public class ManagerDao {

	
	public String PasswordQuery(String account){
		Connection con = null;
		PreparedStatement ps = null;
		Statement stm = null;
		ResultSet rs = null;
		String sql = "select man_pwd from web_manager where man_account = '"+account+"'";
		
		try {
			//con = DBCPUtil.getInstance().getConnection();
			con=JdbcUint.getConnection();
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			if(rs.next()){
				String pwd = rs.getString("man_pwd");
				DBCPUtil.close(rs, stm, con);
				return pwd;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBCPUtil.close(rs, stm, con);
		return null;
		
	}
}
