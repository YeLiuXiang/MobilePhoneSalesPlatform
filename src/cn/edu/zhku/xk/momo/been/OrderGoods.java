package cn.edu.zhku.xk.momo.been;

/**
 * 
 * @author 夜留香
 *
 */
public class OrderGoods {
    private int orderId;			//订单编号
    private int modelId;		//商品型号
    private float price;			//商品单价
    private int number;			//销售数量

    public OrderGoods() {
    }

	public OrderGoods(int orderId, int modelId, float price, int number) {
		super();
		this.orderId = orderId;
		this.modelId = modelId;
		this.price = price;
		this.number = number;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getModelId() {
		return modelId;
	}

	public void setModelId(int modelId) {
		this.modelId = modelId;
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

}
