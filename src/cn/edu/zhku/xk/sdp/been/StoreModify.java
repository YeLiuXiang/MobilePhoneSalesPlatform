package cn.edu.zhku.xk.sdp.been;

public class StoreModify {
	private	String imageAddress;			//������ҳͼƬ��ַ
	private	String customerService;		//�ͷ���ϵ��ʽ
	public StoreModify(String imageAddress, String customerService) {
		super();
		this.imageAddress = imageAddress;
		this.customerService = customerService;
	}
	public StoreModify() {
		super();
	}
	public String getImageAddress() {
		return imageAddress;
	}
	public void setImageAddress(String imageAddress) {
		this.imageAddress = imageAddress;
	}
	public String getCustomerService() {
		return customerService;
	}
	public void setCustomerService(String customerService) {
		this.customerService = customerService;
	}

}
