package cn.edu.zhku.xk.sdp.been;


/**
 * 
 * @author ҹ����
 *
 */
public class Store {				//������Ϣ
private	int	id;									//���̱��
private	String	name;							//��������
private	String account;						//�����˺�
private	String imageAddress;			//������ҳͼƬ��ַ
private	String address;						//ע���ַ
private	String customerService;		//�ͷ���ϵ��ʽ
private	String status;							//����״̬
public Store(int id, String name, String account, String imageAddress, String address, String customerService,
		String status) {
	super();
	this.id = id;
	this.name = name;
	this.account = account;
	this.imageAddress = imageAddress;
	this.address = address;
	this.customerService = customerService;
	this.status = status;
}
public Store() {
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getAccount() {
	return account;
}
public void setAccount(String account) {
	this.account = account;
}
public String getImageAddress() {
	return imageAddress;
}
public void setImageAddress(String imageAddress) {
	this.imageAddress = imageAddress;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getCustomerService() {
	return customerService;
}
public void setCustomerService(String customerService) {
	this.customerService = customerService;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}



}
