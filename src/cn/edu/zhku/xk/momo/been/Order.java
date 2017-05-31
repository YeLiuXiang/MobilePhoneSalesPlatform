package cn.edu.zhku.xk.momo.been;
import java.util.Date;

/**
 * 
 * @author 夜留香
 *
 */
public class Order {				//订单基本信息
    private int orderId;							//		订单编号
    private String customerAccount;	//消费者账号
    private Date time;							//	下单时间
    private String status;						//订单进度
    private String receiverPhone;		//收货人联系电话
    private String address;					//收货人地址
    private String receiver;					//收货人姓名
    private String courierNumber;			//快递单号
	
	public Order() {
		super();
		
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getCustomerAccount() {
		return customerAccount;
	}
	public void setCustomerAccount(String customerAccount) {
		this.customerAccount = customerAccount;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReceiverPhone() {
		return receiverPhone;
	}
	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getCourierNumber() {
		return courierNumber;
	}
	public void setCourierNumber(String courierNumber) {
		this.courierNumber = courierNumber;
	}
    
    
}
