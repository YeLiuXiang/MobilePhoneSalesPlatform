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
 * ˵�������ݿ����ӳع������ݿ�����
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
			//log.info("�ɹ����ӵ�"+dbmd.getDatabaseProductName()+dbmd.getDriverVersion());

		 } catch ( Exception e) {
			// TODO �Զ����ɵ� catch ��
			//log.error("��ʼ��ʧ��"+e);
		}
	 }
		/*
		 * ��ȡ���Ӻ���
		 */
		static public  synchronized Connection getConnection() {
			
			Connection conn=null;
			 try {
				conn=dataSource.getConnection();
				 
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				System.out.println("��ȡ���ݿ����Ӵ���"+e);
			}
			 return conn;
		}
	     /*
	      * �ر�����
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
					// TODO �Զ����ɵ� catch ��
					System.out.println("�ͷ����ݿ�����ʧ��:"+e);
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
