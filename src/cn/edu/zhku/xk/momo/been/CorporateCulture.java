package cn.edu.zhku.xk.momo.been;


/**
 * 
 * @author ҹ����
 *
 */
public class CorporateCulture {		//��ҵ�Ļ�
	private int cID;
    private String cText;		//����
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
