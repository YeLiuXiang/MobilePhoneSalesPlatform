package cn.edu.zhku.xk.momo.been;


public class CommodityBooth {
		private int goodsId;					//商品编号
	    private String name;					//名称
	    private String picAddress;			//图片地址
	    private String title;						//标题简介
	    private float price;					//商品最低价格
		public int getGoodsId() {
			return goodsId;
		}
		public void setGoodsId(int goodsId) {
			this.goodsId = goodsId;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getPicAddress() {
			return picAddress;
		}
		public void setPicAddress(String picAddress) {
			this.picAddress = picAddress;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public float getPrice() {
			return price;
		}
		public void setPrice(float price) {
			this.price = price;
		}
		public CommodityBooth() {
			super();
			// TODO Auto-generated constructor stub
		}
	    
	    
}
