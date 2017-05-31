package cn.edu.zhku.xk.momo.server;

import cn.edu.zhku.xk.momo.been.Customer;
import cn.edu.zhku.xk.momo.dao.CustomerDao;
import cn.edu.zhku.xk.momo.dao.StoreDao;
import net.sf.json.JSONObject;

public class CustomerInforMananger {
	/*
	 * ����˵��:�޸Ļ����û�������Ϣ����,�ֻ������Ǳ���,�˻��������벻���޸�
	 * �������:customer:�û���Ϣʵ��
	 * 		
	 * 		
	 */
	public JSONObject modifyBaseInform(Customer customer){
		String flag="false";
		String msg=null;
		CustomerDao customerDao=new CustomerDao();
		if(!customerDao.setBaseInfo(customer)){
			msg="�޸Ļ�����Ϣʧ��!";
		}else{
			msg="�޸Ļ�����Ϣ�ɹ�";
			flag="true";
		}
		JSONObject result=new JSONObject();
		result.put("flag", flag);
		result.put("msg", msg);
		return result;
	}
	/*
	 * ����˵��:�޸Ļ����û���¼���뺯��,�����ֻ���֤
	 * �������:account:�û��˺�	
	 */
	public JSONObject modifyPassword(String account,String newPwd,String codeIn,String code){
		String flag="false";
		String msg=null;
		if(!codeIn.equals(code)){
			msg="��֤�벻��ȷ";
		}
		else{
			CustomerDao customerDao=new CustomerDao();
			if(!customerDao.setPssword(account, newPwd)){
				msg="�޸�����ʧ��!";
			}else{
				msg="�޸�����ɹ�";
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
			msg="ԭ�ֻ���֤�벻��ȷ";
		}else if(!newPhoneIn.equals(newPhone)){
			msg="���ֻ������Ѿ��ı������·�����֤��!";
		}else if(!newCodeIn.equals(newCode)){
			msg="���ֻ���֤�벻��ȷ!";
		}
		else{
			CustomerDao customerDao=new CustomerDao();
			if(!customerDao.setPhone(account, newPhone)){
				msg="�޸��ֻ�����ʧ��!";
			}else{
				msg="�޸��ֻ�����ɹ�";
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
			msg="���¼!";
		}else{
			CustomerDao customerDao=new CustomerDao();
			customer=customerDao.getCustomer(account);
			if(customer==null){
				msg="������Ϣʧ��!";
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
