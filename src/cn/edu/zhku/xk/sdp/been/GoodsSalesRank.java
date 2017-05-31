package cn.edu.zhku.xk.sdp.been;

import java.util.Date;

public class GoodsSalesRank {
	private int no;								//序号
	private int id;								//商品型号
	private String name;					//名称
	private int volume;					//商品销售量
	private String imgAddr;				//商品展示图片地址
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
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
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	public String getImgAddr() {
		return imgAddr;
	}
	public void setImgAddr(String imgAddr) {
		this.imgAddr = imgAddr;
	}

}
