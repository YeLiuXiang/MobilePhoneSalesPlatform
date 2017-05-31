package cn.edu.zhku.xk.momo.been;

//商品搜索的javabeen
public class GoodsForSearch {
	  	private int id;								//商品编号
	    private String name;						//名称
	    private String storeName;						//所属店铺
	    private String title;						//标题简介
	    private String pictureAddress;          	//商品图片地址
	    private int volume;						//成交量
	    private float price;						//单价
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
