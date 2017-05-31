package cn.edu.zhku.xk.momo.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.apache.tomcat.dbcp.dbcp.BasicDataSourceFactory;


/*
 * 说明：数据库连接池管理数据库连接
 * 
 */
public class JdbcUint {
	private static final String configFile = "resource/dbcp.properties";
	 private static DataSource dataSource;
	 static {
		 Properties dbProperties = new Properties();
		 try {
			dbProperties.load(JdbcUint.class.getClassLoader().getResourceAsStream(configFile));
			dataSource=BasicDataSourceFactory.createDataSource(dbProperties);
			Connection conn=getConnection();
			DatabaseMetaData dbmd=conn.getMetaData();
			//log.info("成功连接到"+dbmd.getDatabaseProductName()+dbmd.getDriverVersion());

		 } catch ( Exception e) {
			// TODO 自动生成的 catch 块
			//log.error("初始化失败"+e);
		}
	 }
		/*
		 * 获取连接函数
		 */
		static public  synchronized Connection getConnection() {
			
			Connection conn=null;
			 try {
				conn=dataSource.getConnection();
				 
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				System.out.println("获取数据库连接错误："+e);
			}
			 return conn;
		}
	     /*
	      * 关闭连接
	      */
		static public synchronized void closeConnection(Connection conn ,Statement stm,ResultSet rs) {
			 try {
				 	if(rs!=null){
				 		rs.close();
				 	}
				 	if(stm!=null){
				 		stm.close();
				 	}
					if(conn!=null&&!conn.isClosed()){ 
						 	conn.setAutoCommit(true);
						    conn.close(); 
						}
				} catch (SQLException e) {
					// TODO 自动生成的 catch 块
					System.out.println("释放数据库连接失败:"+e);
				}
			 }
		static public int executeUpdate(String sql){
			Connection conn=null;
			Statement stm=null;
			int result=-1;
			try {
				conn=JdbcUint.getConnection();
				stm = conn.createStatement();
				result=stm.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				JdbcUint.closeConnection(conn,stm,null);
			}
			
			return result;
		}
	
}
