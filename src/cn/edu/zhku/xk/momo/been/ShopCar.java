
package cn.edu.zhku.xk.momo.been;

	/**
	 * '
	 * @author 夜留香
	 *
	 */
public class ShopCar {		//购物车
    private int carId;								//购单编号
    private String customerAccount;		//消费者账号
    private int goodsModelId;			//商品型号
    private int number;							//订购数量
	public ShopCar(int carId, String customerAccount, int goodsModelId, int number) {
		super();
		this.carId = carId;
		this.customerAccount = customerAccount;
		this.goodsModelId = goodsModelId;
		this.number = number;
	}
	public ShopCar() {
		super();
		
	}
	public int getCarId() {
		return carId;
	}
	public void setCarId(int carId) {
		this.carId = carId;
	}
	public String getCustomerAccount() {
		return customerAccount;
	}
	public void setCustomerAccount(String  customerAccount) {
		this.customerAccount = customerAccount;
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

    
}
