package cn.edu.zhku.xk.momo.been;
import java.util.Date;

/**
 * 
 * @author 夜留香
 *
 */
public class GoodsBase {		//商品基本信息
    private int id;								//商品编号
    private String name;					//名称
    private int storeId;						//所属店铺
    private Date upTime;					//上架时间
    private String desc;						//详细介绍图片地址
    private String picAddress;			//图片地址
    private String title;						//标题简介
    private String status;					//商品状态

    public GoodsBase() {
       
    }

	public String getPicAddress() {
		return picAddress;
	}

	public void setPicAddress(String picAddress) {
		this.picAddress = picAddress;
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

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public Date getUpTime() {
		return upTime;
	}

	public void setUpTime(Date upTime) {
		this.upTime = upTime;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

    
}
