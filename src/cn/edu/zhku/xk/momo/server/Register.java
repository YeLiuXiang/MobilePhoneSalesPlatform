package cn.edu.zhku.xk.momo.server;

import cn.edu.zhku.xk.momo.been.Customer;
import cn.edu.zhku.xk.momo.dao.CustomerDao;

public class Register {
	public boolean verifyAccount(String account){
		CustomerDao customerDao=new CustomerDao();
		return customerDao.getCustomer(account)==null;//’À∫≈≤ª¥Ê‘⁄∑µªÿtrue
	}
	public boolean register(Customer customer) {
		CustomerDao customerDao=new CustomerDao();
		return customerDao.addCustomer(customer);
	}
}
