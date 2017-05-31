package cn.edu.zhku.xk.sdp.been;

import java.util.Date;

/**
 * 
 * @author 夜留香
 *
 */
public class Comment {		//评论
    private int commentId;					//商品评价编号
    private String customerAccount;	//消费者账号
    private int goodsId;						//商品编号
    private Date date;							//评价日期
    private String content;					//评价内容

    public Comment() {
    }

	public Comment(int commentId, String customerAccount, int goodsId, Date date, String content) {
		super();
		this.commentId = commentId;
		this.customerAccount = customerAccount;
		this.goodsId = goodsId;
		this.date = date;
		this.content = content;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(String customerAccount) {
		this.customerAccount = customerAccount;
	}

	public int getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
