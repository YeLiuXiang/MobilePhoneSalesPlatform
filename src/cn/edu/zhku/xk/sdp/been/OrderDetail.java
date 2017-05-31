package cn.edu.zhku.xk.sdp.been;

/**
 * 
 * @author 夜留香
 *订单详情：一个订单的所有商品
 */
public class OrderDetail {
    private int no;					//商品序号
    private int modelId;		//商品型号
    private String  name;		//商品名称
    private float price;			//商品单价
    private int number;			//销售数量
	public OrderDetail(int no, int modelId, String name, float price, int number) {
		super();
		this.no = no;
		this.modelId = modelId;
		this.name = name;
		this.price = price;
		this.number = number;
	}
	public OrderDetail() {
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getModelId() {
		return modelId;
	}
	public void setModelId(int modelId) {
		this.modelId = modelId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
