package cn.edu.zhku.xk.momo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cn.edu.zhku.xk.momo.been.Customer;
import cn.edu.zhku.xk.momo.been.ReceivingInfo;
import cn.edu.zhku.xk.momo.util.JdbcUint;

public class CustomerDao {
	/*
	 * 函数说明：账号验证函数
	 * 参数参数：用户账号
	 * 返回参数：ture：没有重复账号，false：数据库里面已存在该账号
	 */
	public  Customer getCustomer(String account){
		String sql="select * from web_customer where cus_account='"+account+"'";
		Connection conn=null;
		Statement stm=null;
		ResultSet rs=null;
		Customer customer=null;
		
		try {
			conn=JdbcUint.getConnection();
			stm = conn.createStatement();
			rs=stm.executeQuery(sql);
			if(rs.next()){
				customer=new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), 
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUint.closeConnection(conn,stm,rs);
		}
		return customer;
	}
	
	
	public  String getPhone(String account){
		String sql="select cus_phone from web_customer where cus_account='"+account+"'";
		Connection conn=null;
		Statement stm=null;
		ResultSet rs=null;
		String phone=null;
		
		try {
			conn=JdbcUint.getConnection();
			stm = conn.createStatement();
			rs=stm.executeQuery(sql);
			if(rs.next()){
				phone=rs.getString(1);
						}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUint.closeConnection(conn,stm,rs);
		}
		return phone;
	}
	public boolean addCustomer(Customer customer){
		
		String sql="insert into web_customer(cus_account,cus_pwd,cus_phone) values('"+customer.getAccount()+"','"+customer.getPassword()+"','"+customer.getPhone()+"')";
		return JdbcUint.executeUpdate(sql)==1;
	}
	/*
	 *函数说明:设置用户基本信息,除账号,密码,手机号码外的用户信息
	 */
	public boolean setBaseInfo(Customer customer){
		String sql="update web_customer set cus_sex ='"+customer.getSex()+"',cus_nickname ='"
	+customer.getNickName()+"',cus_email ='"+customer.getEmail()+"',cus_receiver_phone ='"+customer.getReceiverPhone()
	+"',cus_address ='"+customer.getAddress()+"',cus_receiver ='"+customer.getReceiver()
	+"' where cus_account ='"+customer.getAccount()+"'";
	return JdbcUint.executeUpdate(sql)==1;
	}
	/*
	 *函数说明:设置手机号码 
	 */
	public boolean setPssword(String account,String password){
		String sql="update web_customer set cus_pwd ='"+password+"' where cus_account ='"+account+"'";
		return JdbcUint.executeUpdate(sql)==1;
	}
	/*
	 *函数说明:设置密码 
	 */
	public boolean setPhone(String account,String phone){
		String sql="update web_customer set cus_phone ='"+phone+"' where cus_account ='"+account+"'";
		return JdbcUint.executeUpdate(sql)==1;
	}
	/*
	 *函数说明:设置收货信息 ,包括,收货地址,联系电话,收货人姓名
	 */
	public boolean setReceiverInfo(Customer customer){
		String sql="update web_custome set cus_receiver_phon ='"+customer.getReceiverPhone()
	+"'cus_address ='"+customer.getAddress()+"'cus_receiver ='"+customer.getReceiver()
	+"' where cus_account ='"+customer.getAccount()+"'";
	return JdbcUint.executeUpdate(sql)==1;
	}
	
	public  ReceivingInfo getReceivingInfo(String account){
		String sql="select cus_receiver,cus_receiver_phone,cus_address from web_customer where cus_account='"+account+"'";
		Connection conn=null;
		Statement stm=null;
		ResultSet rs=null;
		ReceivingInfo receivingInfo=null;
		
		try {
			conn=JdbcUint.getConnection();
			stm = conn.createStatement();
			rs=stm.executeQuery(sql);
			if(rs.next()){
				receivingInfo =new ReceivingInfo();
				receivingInfo.setReceiver(rs.getString(1));
				receivingInfo.setReceiverPhone(rs.getString(2));
				receivingInfo.setAddress(rs.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUint.closeConnection(conn,stm,rs);
		}
		return receivingInfo;
	}
}
