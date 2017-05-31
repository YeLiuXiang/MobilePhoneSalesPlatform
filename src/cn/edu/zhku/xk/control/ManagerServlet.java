package cn.edu.zhku.xk.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ManagerServlet")
public class ManagerServlet extends HttpServlet {
       
    public ManagerServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void AccountQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	protected void MessageModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
