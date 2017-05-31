package cn.edu.zhku.xk.sdp.been;
import java.util.Date;

/**
 * 
 * @author ҹ����
 *
 */
public class ReturnPurchase {
    private int id;										//�˻������
    private int orderId;							//�������
    private String type;								//����
    private String status;							//״̬
    private Date date;								//����
    private String reason;							//�˻���ԭ��

    public ReturnPurchase() {
    }

	public ReturnPurchase(int id, int orderId, String type, String status, Date date, String reason) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.type = type;
		this.status = status;
		this.date = date;
		this.reason = reason;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
