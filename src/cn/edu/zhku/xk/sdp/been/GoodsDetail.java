package cn.edu.zhku.xk.sdp.been;

import java.util.ArrayList;
import java.util.Date;

public class GoodsDetail {
	private int id;								//商品编号
    private String name;					//名称
    private Date upTime;					//上架时间
    private String desc;						//详细介绍图片地址
    private String title;						//标题简介
    private String status;					//商品状态
    private ArrayList goodsList;		//商品型号链表
	public GoodsDetail(int id, String name, Date upTime, String desc, String title, String status,
			ArrayList goodsList) {
		super();
		this.id = id;
		this.name = name;
		this.upTime = upTime;
		this.desc = desc;
		this.title = title;
		this.status = status;
		this.goodsList = goodsList;
	}
	
	public GoodsDetail() {
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
	public ArrayList getGoodsList() {
		return goodsList;
	}
	public void setGoodsList(ArrayList goodsList) {
		this.goodsList = goodsList;
	}
    
}
