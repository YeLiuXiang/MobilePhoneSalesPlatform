package cn.edu.zhku.xk.sdp.been;



/**
 * 
 * @author ҹ����
 *
 */
public class Notice {
	private int nId;				//������
    private String nPic;		//ͼƬ��ַ
    private String nLink;	//������Ʒ���
    
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
