package cn.edu.zhku.xk.momo.been;

/**
 * 
 * @author ҹ����
 *
 */
public class Manager {		//����Ա
    private String  account;		//�˺�
    private String password;	//����
    private String telephone;	//��ϵ�绰
    private String name;			//����

    public Manager() {
    }

    public Manager(String account, String password, String telephone, String name) {
        this.account = account;
        this.password = password;
        this.telephone = telephone;
        this.name = name;
    }

    

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
