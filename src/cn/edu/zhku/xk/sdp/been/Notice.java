package cn.edu.zhku.xk.sdp.been;



/**
 * 
 * @author “π¡Ùœ„
 *
 */
public class Notice {
	private int nId;				//π´∏Ê±‡∫≈
    private String nPic;		//Õº∆¨µÿ÷∑
    private String nLink;	//¡¥Ω”…Ã∆∑±‡∫≈
    
	public Notice(int nId, String nPic, String nLink) {
		super();
		this.nId = nId;
		this.nPic = nPic;
		this.nLink = nLink;
	}
	public int getnId() {
		return nId;
	}
	public void setnId(int nId) {
		this.nId = nId;
	}
	public String getnPic() {
		return nPic;
	}
	public void setnPic(String nPic) {
		this.nPic = nPic;
	}
	public String getnLink() {
		return nLink;
	}
	public void setnLink(String nLink) {
		this.nLink = nLink;
	}
    
}
