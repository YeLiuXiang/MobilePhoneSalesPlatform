package cn.edu.zhku.xk.momo.been;

public class OrderShowInfo {
	private int goodsId;					//商品编号
    private String goodsName;				//商品名称
    private String storeName;				//所属店铺的名称
    private String title;					//标题简介
    private int goodsModelId;				//商品型号
    private String color;					//颜色
    private String picAddress;				//图片地址
    private String capacity;				//内存容量
    private int orderId;			//订单编号
    private float price;			//商品单价
    private int number;			//销售数量
    private String commentStatus;				//评价状态	
    private String account;			//用户名
	public OrderShowInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
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
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getCommentStatus() {
		return commentStatus;
	}
	public void setCommentStatus(String commentStatus) {
		this.commentStatus = commentStatus;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
    
   
}
