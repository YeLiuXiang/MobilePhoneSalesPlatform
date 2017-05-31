package cn.edu.zhku.xk.sdp.been;


/**
 * 
 * @author Ò¹ÁôÏã
 *
 */
public class Store {				//µêÆÌĞÅÏ¢
private	int	id;									//µêÆÌ±àºÅ
private	String	name;							//µêÆÌÃû³Æ
private	String account;						//µêÖ÷ÕËºÅ
private	String imageAddress;			//µêÆÌÊ×Ò³Í¼Æ¬µØÖ·
private	String address;						//×¢²áµØÖ·
private	String customerService;		//¿Í·şÁªÏµ·½Ê½
private	String status;							//µêÆÌ×´Ì¬
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
