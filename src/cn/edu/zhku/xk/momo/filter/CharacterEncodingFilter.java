package cn.edu.zhku.xk.momo.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class CharacterEncodingFilter
 */
@WebFilter("/*")
public class CharacterEncodingFilter implements Filter {
	//编码格式
	private String encode = null;  
    /**
     * Default constructor. 
     */
    public CharacterEncodingFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest rq= (HttpServletRequest)request;  
		HttpServletResponse rp = (HttpServletResponse)response; 
		/* 
		   * 判断在web.xml文件中是否配置了编码格式的信息 
		   * 如果为空，则设置编码格式为配置文件中的编码格式 
		   * 否则编码格式设置为GBK 
		   */  
		 if(this.encode != null && !this.encode.equals("")){  
		   rq.setCharacterEncoding(this.encode);  
		   rp.setCharacterEncoding(this.encode);  
		  }else{  
		   rq.setCharacterEncoding("UTF-8");  
		   rp.setCharacterEncoding("UTF-8");  
		  }  
		// pass the request along the filter chain
		chain.doFilter(rq, rp);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		//获取在web.xml文件中配置了的编码格式的信息  
		  this.encode = fConfig.getInitParameter("encode");  
	}

}
