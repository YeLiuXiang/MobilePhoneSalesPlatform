package cn.edu.zhku.xk.service;

import cn.edu.zhku.xk.dao.ManagerDao;
import net.sf.json.JSONObject;

/**
 * ����Ա��Ϣ����
 * @author ҹ����
 *
 */
public class ManagerService {

	/***********************************************************************************************
	 * �������� ��  �˺���֤
	 *  �������ܣ� ��֤����Ա���˺��Ƿ����
	 *   �������� �� ����Ա���˺ţ�����
	 *   ��������ֵ �� true/false 
	 *   �����������ڣ� 2017/5/11 
	 *   �����޸����� ��
	 ***********************************************************************************************/
	public JSONObject ManVeriITel(String account,String pwd,String checkCode,String checkCodeSess) { // Verification
		ManagerDao manDao = new ManagerDao();
		JSONObject json = new JSONObject();
		String pwd2 = manDao.PasswordQuery(account);
		if(!checkCode.equalsIgnoreCase(checkCodeSess)){		////��֤�����
			json.put("result", 3);
		}
		else if (pwd2 == null) // �˺Ų�����
		{
			json.put("result", 0);
		} 
		else if(pwd2.equals(pwd)){//�˺�������ȷ
			json.put("result", 2);
			json.put("account", account);
		}else{
			json.put("result", 1);//�������
		}
		return json;
	}

}
