package cn.edu.zhku.xk.momo.control;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ImageServlet
 */
@WebServlet("/checkCode")
public class CheckCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckCode() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		BufferedImage bi=new BufferedImage(136,44,BufferedImage.TYPE_INT_RGB);
		Graphics g=bi.getGraphics();
		Color c=new Color(220,150,255);
		Font font=new Font("Default",Font.BOLD,32); 
		g.setColor(c);
		g.setFont(font);
		g.fillRect(0, 0, 136, 44);
		
		char[] ch="abcdefghijklmnpqrlstuvwxyz123456789".toCharArray();
		long seed=new Date().getTime();
		Random r=new Random(seed);
		StringBuffer sb=new StringBuffer();
		int len=ch.length,index;
		for(int i=0;i<4;i++){
			index=r.nextInt(len);
			g.setColor(new Color(r.nextInt(88),r.nextInt(188),r.nextInt(255)));
			g.drawString(ch[index]+"", (i*30)+6, 24+r.nextInt(24));
			sb.append(ch[index]);
		}
		request.getSession().setAttribute("checkCode",sb.toString());
		ImageIO.write(bi, "jpg", response.getOutputStream());
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
