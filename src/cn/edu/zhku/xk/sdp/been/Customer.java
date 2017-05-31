package cn.edu.zhku.xk.sdp.been;

/**
 * 
 * @author ҹ����
 *
 */
public class Customer {
    private String account;			
    private String password;
    private String sex;
    private String nickName;				//�泼
    private String email;
    private String phone;						//�����ߵ绰
    private String receiverPhone;		//�ջ�����ϵ�绰
    private String address;					//�ջ��˵�ַ
    private String receiver;					//�ջ�������
	public Customer(String account, String password, String sex, String nickName, String email, String phone,
			String receiverPhone, String address, String receiver) {
		super();
		this.account = account;
		this.password = password;
		this.sex = sex;
		this.nickName = nickName;
		this.email = email;
		this.phone = phone;
		this.receiverPhone = receiverPhone;
		this.address = address;
		this.receiver = receiver;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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

   
   

    
}
