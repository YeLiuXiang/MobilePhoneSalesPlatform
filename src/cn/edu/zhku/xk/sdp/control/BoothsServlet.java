package cn.edu.zhku.xk.sdp.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.File;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import cn.edu.zhku.xk.sdp.service.BoothService;
import cn.edu.zhku.xk.sdp.service.StoreService;

/**
 * 
 * @author ҹ����
 *չλ����
 */
@WebServlet("/BoothsServlet")
public class BoothsServlet extends HttpServlet {
       
    public BoothsServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String methodName = request.getParameter("method");
				Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
				System.out.println("method:"+methodName);
				//ִ����Ӧ�ķ���
				method.invoke(this, request,response);
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	protected void AdvertingBoothQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���չλ��ѯ
		String account = request.getParameter("account");
		BoothService boothService =new BoothService();
		Object result = boothService.AdvertingBoothQuery(account);
		request.setAttribute("Object", result);
		request.getRequestDispatcher("/subpage/BoothManagement/advertisingBoothDisplay.jsp").forward(request, response);
	}
	protected void WalletQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ѯǮ������Ƿ���Ͼ���Ҫ��
		String account = request.getParameter("account");
		String type = request.getParameter("type");
		BoothService boothService =new BoothService();
		boolean result = boothService.WalletQuery(account,Integer.parseInt(type));
		PrintWriter out = response.getWriter();
		out.print(result);
		}
	protected void CommodityBoothQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��Ʒչλ��ѯ
		String account = request.getParameter("account");
		BoothService boothService =new BoothService();
		Object result = boothService.CommodityBoothQuery(account);
		request.setAttribute("Object", result);
request.getRequestDispatcher("/subpage/BoothManagement/commodityBoothDisplay.jsp").forward(request, response);
	}
	protected void AdvertingBoothApply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���չλ����
		SmartUpload upload = new SmartUpload();
		upload.initialize(this.getServletConfig(), request, response);
		try {
			upload.upload();
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}
		// 1.����ÿ���ϴ��ļ�����󳤶ȡ�
//				upload.setMaxFileSize(10000);
//		 2.�������ϴ����ݵĳ��ȡ�
//				upload.setTotalMaxFileSize(20000);
//		 3.�趨�����ϴ����ļ���ͨ����չ�����ƣ�,������doc,txt�ļ���
//				upload.setAllowedFilesList("jpg,png,bmp,jpeg");
	// 4.�趨��ֹ�ϴ����ļ���ͨ����չ�����ƣ�,��ֹ�ϴ�����exe,bat,jsp,htm,html��չ�����ļ���û����չ�����ļ���
		
		String linkID=upload.getRequest().getParameter("linkID");
		int index=linkID.indexOf(":");
		System.out.println(index);
		linkID=linkID.substring(0, index);
		System.out.println(linkID);
		Object result=null;
		for (int i = 0; i < upload.getFiles().getCount(); i++) {
			String fileName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			File imageFile = upload.getFiles().getFile(i);
			// ���ļ������������
			String fileExtend = imageFile.getFileExt();
			if (imageFile.isMissing())
				continue;
			try {
				imageFile.saveAs("F:/��������/java_eclipse_outfileEE/Moko_Management/WebContent/resource/" + fileName + "."+fileExtend, imageFile.SAVEAS_AUTO);
//				imageFile.saveAs("F:/Program Files/Apache Software Foundation/Tomcat 9.0/webapps/tt/reasouce/" + fileName + "."+fileExtend, imageFile.SAVEAS_AUTO);

				String filePath = this.getServletConfig().getServletContext().getRealPath("/");
				System.out.println(filePath);
				imageFile.saveAs("/resource/" + fileName + "."+fileExtend, imageFile.SAVEAS_VIRTUAL);
			} catch (SmartUploadException e) {
				e.printStackTrace();
			}
			BoothService boothService =new BoothService();
			 result = boothService.AdvertingBoothApply( fileName+"."+fileExtend,linkID);
		}
		
		PrintWriter out = response.getWriter();
		out.print(result);
	}
	protected void AdvertingBoothDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���չλ����
		
		String id2 = request.getParameter("id");
		
		BoothService boothService =new BoothService();
		Object result = boothService.AdvertingBoothDelete(Integer.parseInt(id2));
		PrintWriter out = response.getWriter();
		out.print(result);
	}
	protected void CommodityBoothDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���չλ����
		
		String id2 = request.getParameter("id");
		
		BoothService boothService =new BoothService();
		Object result = boothService.CommodityBoothDelete(Integer.parseInt(id2));
		PrintWriter out = response.getWriter();
		out.print(result);
	}
	protected void CommodityBoothApply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��Ʒչλ����
		SmartUpload upload = new SmartUpload();
		upload.initialize(this.getServletConfig(), request, response);
		try {
			upload.upload();
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}
		// 1.����ÿ���ϴ��ļ�����󳤶ȡ�
//				upload.setMaxFileSize(10000);
//		 2.�������ϴ����ݵĳ��ȡ�
//				upload.setTotalMaxFileSize(20000);
//		 3.�趨�����ϴ����ļ���ͨ����չ�����ƣ�,������doc,txt�ļ���
//				upload.setAllowedFilesList("jpg,png,bmp,jpeg");
	// 4.�趨��ֹ�ϴ����ļ���ͨ����չ�����ƣ�,��ֹ�ϴ�����exe,bat,jsp,htm,html��չ�����ļ���û����չ�����ļ���
		
		
		String linkID=upload.getRequest().getParameter("linkID");
		int index=linkID.indexOf(":");
		linkID=linkID.substring(0, index);
		Object result=null;
		for (int i = 0; i < upload.getFiles().getCount(); i++) {
			String fileName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			File imageFile = upload.getFiles().getFile(i);
			// ���ļ������������
			String fileExtend = imageFile.getFileExt();
			if (imageFile.isMissing())
				continue;
			try {
				imageFile.saveAs("F:/��������/java_eclipse_outfileEE/Moko_Management/WebContent/imageFile/" + fileName + "."+fileExtend, imageFile.SAVEAS_AUTO);
//				imageFile.saveAs("F:/Program Files/Apache Software Foundation/Tomcat 9.0/webapps/tt/reasouce/" + fileName + "."+fileExtend, imageFile.SAVEAS_AUTO);

				String filePath = this.getServletConfig().getServletContext().getRealPath("/");
				System.out.println(filePath);
				imageFile.saveAs("/imageFile/" + fileName + "."+fileExtend, imageFile.SAVEAS_VIRTUAL);
			} catch (SmartUploadException e) {
				e.printStackTrace();
			}
			BoothService boothService =new BoothService();
			 result = boothService.CommodityBoothApply( fileName+"."+fileExtend,linkID);
		}
		
		PrintWriter out = response.getWriter();
		out.print(result);
	}
}
