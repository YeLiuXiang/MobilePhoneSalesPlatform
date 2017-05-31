package cn.edu.zhku.xk.sdp.util;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.apache.tomcat.dbcp.dbcp2.DataSourceConnectionFactory;



//import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
//import org.apache.tomcat.dbcp.dbcp.DataSourceConnectionFactory;

public class DBCPUtil {
	private static DBCPUtil DBCPUtil = null;
	private BasicDataSource bds = null;
	private DataSourceConnectionFactory dscf = null;

	private DBCPUtil() {
		if (bds == null)
			bds = new BasicDataSource();
		 /** 
	     * 指定所有参数连接数据源 
	     *  
	     * @param connectURI 
	     *            数据库 
	     * @param username 
	     *            用户名 
	     * @param pswd 
	     *            密码 
	     * @param driverClass 
	     *            数据库连接驱动名 
	     * @param initialSize 
	     *            初始连接池连接个数 
	     * @param maxtotal 
	     *            最大活动连接数 
	     * @param maxIdle 
	     *            最大连接数 
	     * @param maxWaitMillis 
	     *            获得连接的最大等待毫秒数 
	     * @param minIdle 
	     *            最小连接数 
	     * @return 
	     */  
		bds.setUsername("root");
		bds.setPassword("");
		bds.setUrl("jdbc:mysql://localhost:3306/web_moko?characterEncoding=utf8");
		bds.setDriverClassName("com.mysql.jdbc.Driver");
		bds.setInitialSize(20);
		bds.setMaxTotal(15);
	//	bds.setMaxActive(15);
		bds.setMinIdle(2);
	bds.setMaxWaitMillis(1000 * 5);
	//		bds.setMaxWait(5000);

		dscf = new DataSourceConnectionFactory(bds);
	}

	public synchronized static DBCPUtil getInstance() {
		if (DBCPUtil == null)
			DBCPUtil = new DBCPUtil();
		return DBCPUtil;
	}

	public Connection getConnection() {
		Connection con = null;
		try {
			con = (Connection)dscf.createConnection();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return con;
	}

	/**
	 * 释放数据源
	 */
	public void shutDowmDataSource() {
		if (bds != null)
			try {
				bds.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
	}

	public static void close(ResultSet rs, Statement stm, Connection con) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (stm != null) {
			try {
				stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
