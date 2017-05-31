package cn.edu.zhku.xk.sdp.been;
import java.util.Date;

/**
 * 
 * @author 夜留香
 *
 */
public class ReturnPurchase {
    private int id;										//退货单编号
    private int orderId;							//订单编号
    private String type;								//类型
    private String status;							//状态
    private Date date;								//日期
    private String reason;							//退换货原因

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
