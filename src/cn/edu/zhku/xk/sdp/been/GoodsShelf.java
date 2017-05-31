package cn.edu.zhku.xk.sdp.been;

public class GoodsShelf {
	private int id;						//商品编号
    private String name;			//商品名称
    private String picture;		//商品图片地址
    private String status;			//商品状态
	public GoodsShelf(int id, String name, String picture, String status) {
		super();
		this.id = id;
		this.name = name;
		this.picture = picture;
		this.status = status;
	}
	public GoodsShelf() {
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
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
    
}
