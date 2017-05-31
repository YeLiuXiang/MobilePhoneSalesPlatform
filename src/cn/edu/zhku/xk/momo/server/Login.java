package cn.edu.zhku.xk.momo.server;

import cn.edu.zhku.xk.momo.been.Customer;
import cn.edu.zhku.xk.momo.dao.CustomerDao;
import net.sf.json.JSONObject;

public class Login {
	public JSONObject login(String account, String password,String codeIn,String code){
		JSONObject result=new JSONObject();
		String flag="false";
		String msg=null;
		if(code==null||!code.equals(codeIn)){
			msg="验证码输入错误!";
		}else{
			CustomerDao customerDao=new CustomerDao();
			Customer customer=customerDao.getCustomer(account);
			if(customer==null){
				msg="账号或密码错误";
			}else{
				flag="true";
				msg="登录成功!";
			}
		}
		result.put("flag", flag);
		result.put("msg", msg);
		return result;
	}
}
