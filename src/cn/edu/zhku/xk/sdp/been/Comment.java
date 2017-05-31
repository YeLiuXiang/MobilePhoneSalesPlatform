package cn.edu.zhku.xk.sdp.been;

import java.util.Date;

/**
 * 
 * @author ҹ����
 *
 */
public class Comment {		//����
    private int commentId;					//��Ʒ���۱��
    private String customerAccount;	//�������˺�
    private int goodsId;						//��Ʒ���
    private Date date;							//��������
    private String content;					//��������

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
