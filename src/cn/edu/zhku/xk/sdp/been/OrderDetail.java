package cn.edu.zhku.xk.sdp.been;

/**
 * 
 * @author ҹ����
 *�������飺һ��������������Ʒ
 */
public class OrderDetail {
    private int no;					//��Ʒ���
    private int modelId;		//��Ʒ�ͺ�
    private String  name;		//��Ʒ����
    private float price;			//��Ʒ����
    private int number;			//��������
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
