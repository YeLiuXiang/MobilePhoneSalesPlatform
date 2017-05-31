package cn.edu.zhku.xk.momo.dao;

import cn.edu.zhku.xk.momo.util.JdbcUint;

public class OrderGoodsDao {
	public boolean changeCommentStatus(int oderlId,int modelId){
		String sql="update web_order_goods set comment_status= 'comment' where ord_goo_id='"
				+oderlId+"'mod_id='"+modelId+"'";
		return	JdbcUint.executeUpdate(sql)==1;
		
	}
}
