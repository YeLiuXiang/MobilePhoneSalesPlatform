package cn.edu.zhku.xk.sdp.been;
import java.util.Date;

/**
 * 
 * @author ҹ����
 *
 */
public class GoodsBase {		//��Ʒ������Ϣ
    private int id;								//��Ʒ���
    private String name;					//����
    private int storeId;						//��������
    private Date upTime;					//�ϼ�ʱ��
    private String desc;						//��ϸ����ͼƬ��ַ
    private String title;						//������
    private String status;					//��Ʒ״̬
    private String imgAddr;				//��ƷչʾͼƬ��ַ

    public GoodsBase(int id, String name, int storeId, Date upTime, String desc, String title, String status) {
        this.id = id;
        this.name = name;
       this.storeId = storeId;
        this.upTime = upTime;
        this.desc = desc;
        this.title = title;
        this.status = status;
    }

	



	public GoodsBase() {
		// TODO �Զ����ɵĹ��캯�����
	}



	public String getImgAddr() {
		return imgAddr;
	}

	public void setImgAddr(String imgAddr) {
		this.imgAddr = imgAddr;
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
