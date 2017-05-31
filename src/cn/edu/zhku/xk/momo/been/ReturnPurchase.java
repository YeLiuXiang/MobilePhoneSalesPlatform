package cn.edu.zhku.xk.momo.been;
import java.util.Date;

/**
 * 
 * @author 夜留香
 *
 */
public class ReturnPurchase {
    private int Id;										//退货单编号
    private String customerAccount;		
    private int orderId;							//订单编号
    private String type;								//类型
    private String status;							//状态
    private Date date;								//日期
    private String reason;							//退换货原因

    public ReturnPurchase() {
    }

	public ReturnPurchase(int id, String customerAccount, int orderId, String type, String status, Date date,
			String reason) {
		super();
		Id = id;
		this.customerAccount = customerAccount;
		this.orderId = orderId;
		this.type = type;
		this.status = status;
		this.date = date;
		this.reason = reason;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(String customerAccount) {
		this.customerAccount = customerAccount;
	}

	public int getorderId() {
		return orderId;
	}

	public void setorderId(int orderId) {
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
