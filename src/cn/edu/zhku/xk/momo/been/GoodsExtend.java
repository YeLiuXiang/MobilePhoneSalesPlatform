
package cn.edu.zhku.xk.momo.been;

/**
 * 
 * @author 夜留香
 *
 */
public class GoodsExtend {				//商品扩展表（商品型号表）
    private int modelId;					//型号编号
    private int goodsId;					//商品编号
    private String color;					//颜色
    private String picAddress;			//图片地址
    private int number;						//库存数量
    private String capacity;				//内存容量
    private int volume;						//成交量
    private float price;						//单价
	public GoodsExtend(int modelId, int goodsId, String color, String picAddress, int number, String capacity,
			int volume, float price) {
		super();
		this.modelId = modelId;
		this.goodsId = goodsId;
		this.color = color;
		this.picAddress = picAddress;
		this.number = number;
		this.capacity = capacity;
		this.volume = volume;
		this.price = price;
	}
	public GoodsExtend() {
		super();
		
	}
	public int getModelId() {
		return modelId;
	}
	public void setModelId(int modelId) {
		this.modelId = modelId;
	}
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
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
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
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

    
}
