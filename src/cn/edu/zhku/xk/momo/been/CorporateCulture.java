package cn.edu.zhku.xk.momo.been;


/**
 * 
 * @author 夜留香
 *
 */
public class CorporateCulture {		//企业文化
	private int cID;
    private String cText;		//内容
	public CorporateCulture(int cID, String cText) {
		super();
		this.cID = cID;
		this.cText = cText;
	}
	public int getcID() {
		return cID;
	}
	public void setcID(int cID) {
		this.cID = cID;
	}
	public String getcText() {
		return cText;
	}
	public void setcText(String cText) {
		this.cText = cText;
	}
	
}
