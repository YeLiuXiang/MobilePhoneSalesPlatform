package cn.edu.zhku.xk.momo.been;
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
    private String picAddress;			//ͼƬ��ַ
    private String title;						//������
    private String status;					//��Ʒ״̬

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
