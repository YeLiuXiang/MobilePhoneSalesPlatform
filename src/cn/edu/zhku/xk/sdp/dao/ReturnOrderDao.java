package cn.edu.zhku.xk.sdp.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zhku.xk.momo.util.JdbcUint;
import cn.edu.zhku.xk.sdp.been.ReturnPurchase;
import cn.edu.zhku.xk.sdp.util.DBCPUtil;
import cn.edu.zhku.xk.sdp.util.GlobalVariable;
import cn.edu.zhku.xk.sdp.util.PageUtil;


public class ReturnOrderDao {

	public PageUtil RetuExchQuery(int currentPage,int searchType, String account) {
		Connection con = null;
		PreparedStatement ps = null;
		Statement stm = null;
		ResultSet rs = null;
		PageUtil pageObject = null;
		String sqlTotalRows = "";
		try {
			//con = DBCPUtil.getInstance().getConnection();
			con=JdbcUint.getConnection();
			stm = con.createStatement();
			switch(searchType){
			case 0:// 未处理
				 sqlTotalRows = "select count(*) from view_refund_change where cus_account='"+account+"'  and ref_status = 'NoOperate'";
				break;
			case 1:// 已同意
				 sqlTotalRows = "select count(*) from view_refund_change where cus_account='"+account+"'  and ref_status = 'Agreed'";
				break;
			case 2:// 已拒绝
				 sqlTotalRows = "select count(*) from view_refund_change where cus_account='"+account+"'  and ref_status = 'refused'";
				break;
				default:
					break;
			}
			rs = stm.executeQuery(sqlTotalRows);
			if (rs.next()) {
				pageObject = new PageUtil(rs.getInt(1), currentPage);
				String sql = "select * from  view_refund_change where cus_account='"+account+"'  and ref_status = ? limit ?,? ";	//存取过程来实现
				ps = con.prepareStatement(sql);
				ps.setInt(2, pageObject.getPageStartRow());
				ps.setInt(3, pageObject.getPageSize());
				switch(searchType){
				case 0:// 未处理
					ps.setString(1, "NoOperate");
					break;
				case 1:// 已同意
					ps.setString(1, "Agreed");
					break;
				case 2:// 已拒绝
					ps.setString(1, "refused");
					break;
					default:
						break;
				}
				rs.close();
				rs = ps.executeQuery();
			List<ReturnPurchase> retuList = new ArrayList<>();
			while(rs.next()){
				ReturnPurchase retu = new ReturnPurchase();
				retu.setId(rs.getInt("ref_id"));
				retu.setOrderId(rs.getInt("ord_id"));
				if("return".equals(rs.getString("ref_type")))
				{
				retu.setType("退货");
				}else{
					retu.setType("换货");
				
				}
				if("NoOperate".equals(rs.getString("ref_status"))){
					retu.setStatus("未处理");
				}else if("Agreed".equals(rs.getString("ref_status"))){
					retu.setStatus("已同意");
				}else{
					retu.setStatus("已拒绝");
				}
				
				retu.setDate(rs.getTimestamp("ref_date"));
				retu.setReason(rs.getString("ref_reason"));
				retuList.add(retu);
			}
			pageObject.setList(retuList);
			}
			DBCPUtil.close(rs, stm, con);
			return pageObject;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public boolean StatusModify(int status, int id) {
		Connection con = null;
		PreparedStatement ps = null;
		Statement stm = null;
		ResultSet rs = null;
		CallableStatement cs  =null;
		
		String sql = null;
		if(status == GlobalVariable.AgreeReturnOrder){//
		 sql = "update   web_refund_change  set  ref_status = 'Agreed' where ref_id = "+id;	//
		}
		else if(status==GlobalVariable.RefuseReturnOrder){//
			 sql = "update   web_refund_change  set  ref_status = 'refused' where ref_id = "+id;	//
						
		}
		
		try {
			//con = DBCPUtil.getInstance().getConnection();
			con=JdbcUint.getConnection();
			stm = con.createStatement();
			int num = stm.executeUpdate(sql);
			DBCPUtil.close(rs, stm, con);
			if(num==1){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBCPUtil.close(rs, stm, con);
		return false;

	}

	public PageUtil RetuExchQueryById(int id, int searchType,String account) {
		Connection con = null;
		PreparedStatement ps = null;
		Statement stm = null;
		ResultSet rs = null;
		PageUtil pageObject = null;
		String sqlTotalRows = "";
		try {
			//con = DBCPUtil.getInstance().getConnection();
			con=JdbcUint.getConnection();
			stm = con.createStatement();
			switch(searchType){
			case 0:// 未处理
				 sqlTotalRows = "select count(*) from view_refund_change where cus_account='"+account+"'  and ref_status = 'NoOperate'";
				break;
			case 1:// 已同意
				 sqlTotalRows = "select count(*) from view_refund_change where cus_account='"+account+"'  and ref_status = 'Agreed'";
				break;
			case 2:// 已拒绝
				 sqlTotalRows = "select count(*) from view_refund_change where cus_account='"+account+"'  and ref_status = 'refused'";
				break;
				default:
					break;
			}
			rs = stm.executeQuery(sqlTotalRows);
			if (rs.next()) {
				pageObject = new PageUtil(rs.getInt(1), 1);
				String sql = "select * from  view_refund_change where cus_account='"+account+"'  and ref_status = ? and ref_id ="+id;	//存取过程来实现
				ps = con.prepareStatement(sql);
		
				switch(searchType){
				case 0:// 未处理
					ps.setString(1, "NoOperate");
					break;
				case 1:// 已同意
					ps.setString(1, "Agreed");
					break;
				case 2:// 已拒绝
					ps.setString(1, "refused");
					break;
					default:
						break;
				}
				rs.close();
				rs = ps.executeQuery();
			List<ReturnPurchase> retuList = new ArrayList<>();
			if(rs.next()){
				ReturnPurchase retu = new ReturnPurchase();
				retu.setId(rs.getInt("ref_id"));
				retu.setOrderId(rs.getInt("ord_id"));
				if("return".equals(rs.getString("ref_type")))
				{
				retu.setType("退货");
				}else{
					retu.setType("换货");
				
				}
				if("NoOperate".equals(rs.getString("ref_status"))){
					retu.setStatus("未处理");
				}else if("Agreed".equals(rs.getString("ref_status"))){
					retu.setStatus("已同意");
				}else{
					retu.setStatus("已拒绝");
				}
				
				retu.setDate(rs.getTimestamp("ref_date"));
				retu.setReason(rs.getString("ref_reason"));
				retuList.add(retu);
			}
			pageObject.setList(retuList);
			}
			DBCPUtil.close(rs, stm, con);
			return pageObject;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBCPUtil.close(rs, stm, con);
		return null;

	}
	
}
