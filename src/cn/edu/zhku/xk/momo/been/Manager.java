package cn.edu.zhku.xk.momo.been;

/**
 * 
 * @author 夜留香
 *
 */
public class Manager {		//管理员
    private String  account;		//账号
    private String password;	//密码
    private String telephone;	//联系电话
    private String name;			//姓名

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
