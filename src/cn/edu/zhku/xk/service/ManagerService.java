package cn.edu.zhku.xk.service;

import cn.edu.zhku.xk.dao.ManagerDao;
import net.sf.json.JSONObject;

/**
 * 管理员信息处理
 * @author 夜留香
 *
 */
public class ManagerService {

	/***********************************************************************************************
	 * 函数名称 ：  账号验证
	 *  函数功能： 验证管理员的账号是否存在
	 *   函数参数 ： 管理员的账号，密码
	 *   函数返回值 ： true/false 
	 *   函数创建日期： 2017/5/11 
	 *   函数修改日期 ：
	 ***********************************************************************************************/
	public JSONObject ManVeriITel(String account,String pwd,String checkCode,String checkCodeSess) { // Verification
		ManagerDao manDao = new ManagerDao();
		JSONObject json = new JSONObject();
		String pwd2 = manDao.PasswordQuery(account);
		if(!checkCode.equalsIgnoreCase(checkCodeSess)){		////验证码错误
			json.put("result", 3);
		}
		else if (pwd2 == null) // 账号不存在
		{
			json.put("result", 0);
		} 
		else if(pwd2.equals(pwd)){//账号密码正确
			json.put("result", 2);
			json.put("account", account);
		}else{
			json.put("result", 1);//密码错误
		}
		return json;
	}

}
