package cn.edu.zhku.xk.momo.been;

/**
 * 
 * @author ҹ����
 *
 */
public class OrderGoods {
    private int orderId;			//�������
    private int modelId;		//��Ʒ�ͺ�
    private float price;			//��Ʒ����
    private int number;			//��������

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
