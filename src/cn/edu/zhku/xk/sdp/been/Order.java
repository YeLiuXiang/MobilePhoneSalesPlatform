package cn.edu.zhku.xk.sdp.been;
import java.util.Date;


/**
 * 
 * @author ҹ����
 *
 */
public class Order {				//����������Ϣ
    private int orderId;							//		�������
    private String customerAccount;	//�������˺�
    private Date time;							//	�µ�ʱ��
    private String status;						//��������
    private String receiverPhone;		//�ջ�����ϵ�绰
    private String address;					//�ջ��˵�ַ
    private String receiver;					//�ջ�������
    private int courierNumber;			//��ݵ���
	public Order(int orderId, String customerAccount, Date time, String status, String receiverPhone, String address,
			String receiver, int courierNumber) {
		super();
		this.orderId = orderId;
		this.customerAccount = customerAccount;
		this.time = time;
		this.status = status;
		this.receiverPhone = receiverPhone;
		this.address = address;
		this.receiver = receiver;
		this.courierNumber = courierNumber;
	}
	public Order() {
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
	public int getCourierNumber() {
		return courierNumber;
	}
	public void setCourierNumber(int courierNumber) {
		this.courierNumber = courierNumber;
	}
    
    
}
