package cn.edu.zhku.xk.momo.dao;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zhku.xk.momo.been.ShopCar;
import cn.edu.zhku.xk.momo.been.ShopCarShow;
import cn.edu.zhku.xk.momo.util.JdbcUint;

public class ShopCarDao {
	public boolean addShopCar(ShopCar shopCar){
		String sql="select car_id,car_order_num from web_store_car where cus_account='"
				+shopCar.getCustomerAccount()+"' and mod_id='"+shopCar.getGoodsModelId()+"'";
		Connection conn=null;
		Statement stm=null;
		ResultSet rs=null;
		int i=0;
		try {
			conn=JdbcUint.getConnection();
			conn.setAutoCommit(false);
			stm = conn.createStatement();
			rs=stm.executeQuery(sql);
			if(rs.next()){
				int carId=rs.getInt(1);
				int num=rs.getInt(2)+shopCar.getNumber();
				sql="update web_store_car set car_order_num='"+num+"' where car_id='"+carId+"'";
			}else{
				sql="insert into web_store_car(cus_account,mod_id,car_order_num) values('"
						+shopCar.getCustomerAccount()+"','"+shopCar.getGoodsModelId()+"','"+shopCar.getNumber()+"')";
			}
			i=stm.executeUpdate(sql);
			if(i!=1){
				conn.rollback();
				
			}
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
			JdbcUint.closeConnection(conn,stm,rs);
			}
		}
		return i==1;
		
	}
	public boolean modifyShopCar(ShopCar shopCar){
		String sql="update web_store_car set car_order_num='"+shopCar.getNumber()+"' where mod_id='"
				+shopCar.getGoodsModelId()+"' and cus_account='"+shopCar.getCustomerAccount()+"'";
		return JdbcUint.executeUpdate(sql)==1;
	}
public boolean deleteShopCar(int modelId,String account){
		
		String sql="delete from web_store_car  where mod_id='"+modelId+"' and cus_account='"+account+"'";
		return JdbcUint.executeUpdate(sql)==1;
	}
public List<ShopCarShow> searchShopCar(String account){
	
	String sql="select sto_name,goo_id,goo_name,goo_title, goo_status,mod_id, mod_price,"
			+ "mod_img_addr,mod_color,mod_capacity,mod_stock_num,car_id,car_order_num"
			+ " from shopCarForShowView  where cus_account='"+account+"'";
	Connection conn=null;
	Statement stm=null;
	ResultSet rs=null;
	List<ShopCarShow> shopCarList=new ArrayList<>();
	try {
		conn=JdbcUint.getConnection();
		stm = conn.createStatement();
		rs=stm.executeQuery(sql);
		while(rs.next()){
			ShopCarShow shopCar=new ShopCarShow();
			shopCar.setStoreName(rs.getString(1));
			shopCar.setGoodsId(rs.getInt(2));
			shopCar.setGoodsName(rs.getString(3));
			shopCar.setTitle(rs.getString(4));
			shopCar.setGoodsStatus(rs.getString(5));
			shopCar.setGoodsModelId(rs.getInt(6));
			shopCar.setPrice(rs.getFloat(7));
			shopCar.setPicAddress(rs.getString(8));
			shopCar.setColor(rs.getString(9));
			shopCar.setCapacity(rs.getString(10));
			shopCar.setStorenumber(rs.getInt(11));
			shopCar.setCarId(rs.getInt(12));
			shopCar.setShopCarnumber(rs.getInt(13));
			shopCarList.add(shopCar);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		JdbcUint.closeConnection(conn,stm,rs);
	}
	return shopCarList;
}
}
