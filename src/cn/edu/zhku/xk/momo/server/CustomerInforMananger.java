package cn.edu.zhku.xk.momo.server;

import cn.edu.zhku.xk.momo.been.Customer;
import cn.edu.zhku.xk.momo.dao.CustomerDao;
import cn.edu.zhku.xk.momo.dao.StoreDao;
import net.sf.json.JSONObject;

public class CustomerInforMananger {
	/*
	 * 函数说明:修改基本用户基本信息函数,手机号码是必填,账户名和密码不能修改
	 * 传入参数:customer:用户信息实体
	 * 		
	 * 		
	 */
	public JSONObject modifyBaseInform(Customer customer){
		String flag="false";
		String msg=null;
		CustomerDao customerDao=new CustomerDao();
		if(!customerDao.setBaseInfo(customer)){
			msg="修改基本信息失败!";
		}else{
			msg="修改基本信息成功";
			flag="true";
		}
		JSONObject result=new JSONObject();
		result.put("flag", flag);
		result.put("msg", msg);
		return result;
	}
	/*
	 * 函数说明:修改基本用户登录密码函数,经过手机验证
	 * 传入参数:account:用户账号	
	 */
	public JSONObject modifyPassword(String account,String newPwd,String codeIn,String code){
		String flag="false";
		String msg=null;
		if(!codeIn.equals(code)){
			msg="验证码不正确";
		}
		else{
			CustomerDao customerDao=new CustomerDao();
			if(!customerDao.setPssword(account, newPwd)){
				msg="修改密码失败!";
			}else{
				msg="修改密码成功";
				flag="true";
			}
		}
		JSONObject result=new JSONObject();
		result.put("flag", flag);
		result.put("msg", msg);
		return result;	
	}
	public JSONObject modifyPhone(String account,String newPhone,String newPhoneIn,String newCodeIn,String newCode,String codeIn,String code){
		String flag="false";
		String msg=null;
		
		if(!codeIn.equals(code)){
			msg="原手机验证码不正确";
		}else if(!newPhoneIn.equals(newPhone)){
			msg="新手机号码已经改变请重新发送验证码!";
		}else if(!newCodeIn.equals(newCode)){
			msg="新手机验证码不正确!";
		}
		else{
			CustomerDao customerDao=new CustomerDao();
			if(!customerDao.setPhone(account, newPhone)){
				msg="修改手机号码失败!";
			}else{
				msg="修改手机号码成功";
				flag="true";
			}
		}
		JSONObject result=new JSONObject();
		result.put("flag", flag);
		result.put("msg", msg);
		return result;	
	}
	public JSONObject getInfo(String account){
		String flag="false";
		String msg=null;
		JSONObject result=new JSONObject();
		Customer customer=null;
		if(account==null){
			msg="请登录!";
		}else{
			CustomerDao customerDao=new CustomerDao();
			customer=customerDao.getCustomer(account);
			if(customer==null){
				msg="查找信息失败!";
			}else{
				flag="true";
				result.put("data", customer);
			}
		}
		result.put("flag", flag);
		result.put("msg", msg);
		return result;
	}
	public JSONObject isStore(String account){
		StoreDao storeDao=new StoreDao();
		
		JSONObject result=new JSONObject();
		result.put("isStore", storeDao.isStore(account));
		return result;	
	}
	
	
}
