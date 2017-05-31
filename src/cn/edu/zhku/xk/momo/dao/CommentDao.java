package cn.edu.zhku.xk.momo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zhku.xk.momo.been.Comment;
import cn.edu.zhku.xk.momo.util.JdbcUint;

public class CommentDao {
	public List<Comment> getCommentByGoodsId(int goodsId){
		String sql="select cus_account,eva_date,eva_content from web_evaluation where  goo_id="+goodsId;
		Connection conn=null;
		Statement stm=null;
		ResultSet rs=null;
		List<Comment> commentList=new  ArrayList<>();
		try {
			conn=JdbcUint.getConnection();
			stm = conn.createStatement();
			rs=stm.executeQuery(sql);
			while(rs.next()){
				Comment comment=new Comment();
				comment.setCustomerAccount(rs.getString(1));
				comment.setDate(rs.getDate(2));
				comment.setContent(rs.getString(3));
				commentList.add(comment);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUint.closeConnection(conn,stm,rs);
		}
		return commentList;
	}
	
	
public boolean addComment(Comment comment){
		
		String sql="insert into web_evaluation(cus_account,goo_id,eva_date,eva_content) values('"
		+comment.getCustomerAccount()+"','"+comment.getGoodsId()+"','"+comment.getDate()+"','"+comment.getContent()+"')";
		return JdbcUint.executeUpdate(sql)==1;
	}
}
