package cn.edu.zhku.xk.momo.been;

//��Ʒ������javabeen
public class GoodsForSearch {
	  	private int id;								//��Ʒ���
	    private String name;						//����
	    private String storeName;						//��������
	    private String title;						//������
	    private String pictureAddress;          	//��ƷͼƬ��ַ
	    private int volume;						//�ɽ���
	    private float price;						//����
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
		public String getStoreName() {
			return storeName;
		}
		public void setStoreName(String storeName) {
			this.storeName = storeName;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		
		public String getPictureAddress() {
			return pictureAddress;
		}
		public void setPictureAddress(String pictureAddress) {
			this.pictureAddress = pictureAddress;
		}
		
		public int getVolume() {
			return volume;
		}
		public void setVolume(int volume) {
			this.volume = volume;
		}
		public float getPrice() {
			return price;
		}
		public void setPrice(float price) {
			this.price = price;
		}
		public GoodsForSearch() {
			super();
			// TODO Auto-generated constructor stub
		}

	    
}
