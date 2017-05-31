package cn.edu.zhku.xk.momo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.zhku.xk.momo.been.OrderShowInfo;
import cn.edu.zhku.xk.momo.been.Order;
import cn.edu.zhku.xk.momo.been.OrderACK;
import cn.edu.zhku.xk.momo.been.ReceivingInfo;
import cn.edu.zhku.xk.momo.util.JdbcUint;
import cn.edu.zhku.xk.momo.util.MultipleMap;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class OrderDao {
	public OrderACK getOrderAck(int goodsModelId){
		String sql="select sto_name,goo_id,goo_name,goo_title,mod_id,mod_price,mod_img_addr,"
				+ "mod_color,mod_capacity,mod_stock_num,sto_id from orderforackview "
				+ "where mod_id='"+goodsModelId+"'";
		Connection conn=null;
		Statement stm=null;
		ResultSet rs=null;
		OrderACK orderACK=null;
		try {
			conn=JdbcUint.getConnection();
			stm = conn.createStatement();
			rs=stm.executeQuery(sql);
			if(rs.next()){
				orderACK=new OrderACK();
				orderACK.setStoreName(rs.getString(1));
				orderACK.setGoodsId(rs.getInt(2));
				orderACK.setGoodsName(rs.getString(3));
				orderACK.setTitle(rs.getString(4));
				orderACK.setGoodsModelId(rs.getInt(5));
				orderACK.setPrice(rs.getFloat(6));
				orderACK.setPicAddress(rs.getString(7));
				orderACK.setColor(rs.getString(8));
				orderACK.setCapacity(rs.getString(9));
				orderACK.setStoreNumber(rs.getInt(10));
				orderACK.setStoreId(rs.getInt(11));
				
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUint.closeConnection(conn,stm,rs);
		}
		return orderACK;
		
	}
	
	public List<OrderACK> getOrderAckFromShopCar(int modelId[],String account){
		String sql="";
		Connection conn=null;
		Statement stm=null;
		ResultSet rs=null;
		OrderACK orderACK=null;
		List<OrderACK> orderACKList=new ArrayList<>();
		try {
			conn=JdbcUint.getConnection();
			stm = conn.createStatement();
			conn.setAutoCommit(false);
			for(int i=0;i<modelId.length;i++){
				sql="select sto_name,goo_id,goo_name,goo_title,mod_id,mod_price,mod_img_addr,"
					+ "mod_color,mod_capacity,mod_stock_num,goo_status,car_id,car_order_num,sto_id from shopcarforshowview "
					+ "where cus_account='"+account+"' and mod_id='"+modelId[i]+"'";
					rs=stm.executeQuery(sql);
				if(rs.next()){
					orderACK=new OrderACK();
					orderACK.setStoreName(rs.getString(1));
					orderACK.setGoodsId(rs.getInt(2));
					orderACK.setGoodsName(rs.getString(3));
					orderACK.setTitle(rs.getString(4));
					orderACK.setGoodsModelId(rs.getInt(5));
					orderACK.setPrice(rs.getFloat(6));
					orderACK.setPicAddress(rs.getString(7));
					orderACK.setColor(rs.getString(8));
					orderACK.setCapacity(rs.getString(9));
					orderACK.setStoreNumber(rs.getInt(10));
					orderACK.setStatus(rs.getString(11));
					orderACK.setNumber(rs.getInt(13));
					orderACK.setStoreId(rs.getInt(14));
					orderACKList.add(orderACK);
						
				}else{
						orderACKList.clear();
						break;
				}
			}
				
				
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUint.closeConnection(conn,stm,rs);
		}
		return orderACKList;
		
	}
	
	
	public List<Integer> addOrder(String account,MultipleMap<OrderACK> orderACKMap,ReceivingInfo  receivingInfo,boolean isFromCar){
		String sql="";
		Connection conn=null;
		Statement stm=null;
		ResultSet rs=null;
		List<String> storeList=orderACKMap.getKey();
		List<Integer> orderIdArr=new ArrayList<>();
		List<OrderACK> orderACKList=null;
		try {
			conn=JdbcUint.getConnection();
			conn.setAutoCommit(false);
			stm = conn.createStatement();
			// 增加订单表的信息
			for(int k=0;k<storeList.size();k++){
				String storeName=storeList.get(k);
				orderACKList=orderACKMap.getValue(storeName);
				
				sql="insert into web_order(cus_account,ord_receiver,ord_receiver_phone,ord_address,sto_id) "
						+ "values('"+account+"','"+receivingInfo.getReceiver()+"','"+receivingInfo.getReceiverPhone()+"','"+receivingInfo.getAddress()+"','"+orderACKList.get(0).getStoreId()+"')";
				
				int i=stm.executeUpdate(sql);
				if(i!=1){
					conn.rollback();
					return null;
				}
				//查找刚插入的订单的订单编号
				sql="select max(ord_id) from web_order";
				rs=stm.executeQuery(sql);
				if(!rs.next()){
					conn.rollback();
					JdbcUint.closeConnection(conn,stm,rs);
					return null;
				}
				orderIdArr.add(rs.getInt(1));
				
				for(OrderACK temp:orderACKList){
					//订单记录表中主意插入记录
					sql="insert into web_order_goods(ord_goo_id,mod_id,ord_goo_order_num,ord_goo_price) "
								+ "values('"+orderIdArr.get(k)+"','"+temp.getGoodsModelId()+"','"+temp.getNumber()+"','"+temp.getPrice()+"')";
					int j=stm.executeUpdate(sql);
					if(j!=1){
						conn.rollback();
						JdbcUint.closeConnection(conn,stm,rs);
						return null;
					}
					//查找相应商品型号的的商品的库存量和交易量
					sql="select mod_stock_num,mod_volume from web_goods_model where mod_id='"+temp.getGoodsModelId()+"'";
					rs=stm.executeQuery(sql);
					if(!rs.next()){
						conn.rollback();
						JdbcUint.closeConnection(conn,stm,rs);
						return null;
					}
					int storeNum=rs.getInt(1)-temp.getNumber();
					int volume=rs.getInt(2)+temp.getNumber();
					//修改相应商品型号的的商品的库存量和交易量
					sql="update web_goods_model set mod_stock_num='"+storeNum+"',mod_volume='"+volume+"' where mod_id='"+temp.getGoodsModelId()+"'";
					j=stm.executeUpdate(sql);
					if(j!=1){
						conn.rollback();
						JdbcUint.closeConnection(conn,stm,rs);
						return null;
					}
					//如果来着购物车,删除购物车表中相应信息
					if(isFromCar==true){
						sql="delete from web_store_car  where mod_id='"+temp.getGoodsModelId()+"' and cus_account='"+account+"'";
						j=stm.executeUpdate(sql);
						if(j!=1){
							conn.rollback();
							JdbcUint.closeConnection(conn,stm,rs);
							return null;
						}
					}
				}
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
		return orderIdArr;
		
		
		
	}
	

	public boolean changeOrderStatus(String account,int oderlId,String status){
		String sql="update web_order set ord_status= '"+status+"' where ord_id='"
				+oderlId+"' and cus_account='"+account+"'";
		return	JdbcUint.executeUpdate(sql)==1;
		
	}
	
	public JSONObject getAll(String account){
		String sql="select sto_name,goo_id,goo_name,goo_title,mod_id,ord_goo_price,mod_img_addr,"//7
				+ "mod_color,mod_capacity,ord_goo_order_num,comment_status,ord_id,ord_order_time,"//13
				+ "ord_status,ord_receiver,ord_receiver_phone,ord_address,ord_courier_num,cus_account	"//19
				+ "from orderforshowview "
				+ "where cus_account='"+account+"' ORDER BY ord_id DESC";
		Connection conn=null;
		Statement stm=null;
		ResultSet rs=null;
		OrderShowInfo ordershow=new OrderShowInfo();
		Order order=new Order();
		JSONObject result=new JSONObject();
		List<Order> orderList=new ArrayList<>();
		MultipleMap<OrderShowInfo> oderMap=new MultipleMap<OrderShowInfo>();
		try {
			conn=JdbcUint.getConnection();
			stm = conn.createStatement();
			rs=stm.executeQuery(sql);
			int i=-1;
			
			while(rs.next()){
				
				ordershow=new OrderShowInfo();
				ordershow.setStoreName(rs.getString(1));
				ordershow.setGoodsId(rs.getInt(2));
				ordershow.setGoodsName(rs.getString(3));
				ordershow.setTitle(rs.getString(4));
				ordershow.setGoodsModelId(rs.getInt(5));
				ordershow.setPrice(rs.getFloat(6));
				ordershow.setPicAddress(rs.getString(7));
				ordershow.setColor(rs.getString(8));
				ordershow.setCapacity(rs.getString(9));
				ordershow.setNumber(rs.getInt(10));
				ordershow.setCommentStatus(rs.getString(11));
				ordershow.setOrderId(rs.getInt(12));
				ordershow.setAccount(rs.getString(19));
				oderMap.put(String.valueOf(ordershow.getOrderId()), ordershow);
				if(i!=ordershow.getOrderId()){
					
					order=new Order();
					order.setOrderId(rs.getInt(12));
					//order.setTime(rs.getDate(13));
					order.setStatus(rs.getString(14));
					order.setReceiver(rs.getString(15));
					order.setReceiverPhone(rs.getString(16));
					order.setAddress(rs.getString(17));
					order.setCourierNumber(rs.getString(18));
					order.setCustomerAccount(rs.getString(19));
					orderList.add(order);
					i=ordershow.getOrderId();
				}
			}
			result.put("orderList", JSONArray.fromObject(orderList).toString());
			result.put("orderMap", oderMap.toJSONObject());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUint.closeConnection(conn,stm,rs);
			
		}
		
		
		return result;
		
	}
}