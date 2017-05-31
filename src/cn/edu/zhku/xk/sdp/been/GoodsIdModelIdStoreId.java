package cn.edu.zhku.xk.sdp.been;

public class GoodsIdModelIdStoreId {
private int goodsId;
private int modelId;
private int storeId;
public GoodsIdModelIdStoreId(int goodsId, int modelId, int storeId) {
	super();
	this.goodsId = goodsId;
	this.modelId = modelId;
	this.storeId = storeId;
}
public int getGoodsId() {
	return goodsId;
}
public void setGoodsId(int goodsId) {
	this.goodsId = goodsId;
}
public int getModelId() {
	return modelId;
}
public void setModelId(int modelId) {
	this.modelId = modelId;
}
public int getStoreId() {
	return storeId;
}
public void setStoreId(int storeId) {
	this.storeId = storeId;
}


}
