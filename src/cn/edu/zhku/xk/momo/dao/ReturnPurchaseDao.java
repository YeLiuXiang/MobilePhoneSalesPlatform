package cn.edu.zhku.xk.momo.dao;

import cn.edu.zhku.xk.momo.been.ReturnPurchase;
import cn.edu.zhku.xk.momo.util.JdbcUint;

public class ReturnPurchaseDao {

	public boolean addReturnPurchase(ReturnPurchase returnPurchase){
		String sql="insert into web_refund_change(ord_id,ref_type,ref_reason) values('"
	+returnPurchase.getorderId()+"','"+returnPurchase.getType()+"','"+returnPurchase.getReason()+"')";
		return JdbcUint.executeUpdate(sql)==1;
	}
}
