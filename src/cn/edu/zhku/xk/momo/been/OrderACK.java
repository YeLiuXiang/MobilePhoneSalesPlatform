package cn.edu.zhku.xk.momo.been;


public class OrderACK {
	 	private int goodsId;					//商品编号
	    private String goodsName;				//商品名称
	    private int storeId;				//商店Id
	    private String storeName;				//所属店铺的名称
	    private String title;					//标题简介
	    private int goodsModelId;				//商品型号
	    private int number;				      	//购买数量
	    private String color;					//颜色
	    private String picAddress;				//图片地址
	    private String capacity;				//内存容量
	    private float price;					//单价
	    private int StoreNumber;				//库存数量,用于判断
	    private String Status;				 //商品状态
		public int getGoodsId() {
			return goodsId;
		}
		public void setGoodsId(int goodsId) {
			this.goodsId = goodsId;
		}
		public int getStoreId() {
			return storeId;
		}
		public void setStoreId(int storeId) {
			this.storeId = storeId;
		}
		public String getGoodsName() {
			return goodsName;
		}
		public void setGoodsName(String goodsName) {
			this.goodsName = goodsName;
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

		public int getGoodsModelId() {
			return goodsModelId;
		}
		public void setGoodsModelId(int goodsModelId) {
			this.goodsModelId = goodsModelId;
		}
		public int getNumber() {
			return number;
		}
		public void setNumber(int number) {
			this.number = number;
		}
		public String getColor() {
			return color;
		}
		public void setColor(String color) {
			this.color = color;
		}
		public String getPicAddress() {
			return picAddress;
		}
		public void setPicAddress(String picAddress) {
			this.picAddress = picAddress;
		}
	
		public String getCapacity() {
			return capacity;
		}
		public void setCapacity(String capacity) {
			this.capacity = capacity;
		}
		public float getPrice() {
			return price;
		}
		public void setPrice(float price) {
			this.price = price;
		}
		public int getStoreNumber() {
			return StoreNumber;
		}
		public void setStoreNumber(int storeNumber) {
			StoreNumber = storeNumber;
		}
		public String getStatus() {
			return Status;
		}
		public void setStatus(String status) {
			Status = status;
		}
		public OrderACK() {
			super();
			// TODO Auto-generated constructor stub
		}
	    
}
