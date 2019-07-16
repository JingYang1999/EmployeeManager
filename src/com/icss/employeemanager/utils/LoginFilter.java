package com.icss.employeemanager.utils;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登入访问权限过滤器
 */
public class LoginFilter implements Filter {

    public LoginFilter() {
    }

	public void destroy() {
	}
    
	//核心
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest re = (HttpServletRequest)request;  //HttpServletRequest请求对象
		HttpServletResponse rq = (HttpServletResponse)response; //响应对象
		String url = re.getRequestURI();//获取资源地址
		System.out.println(url);
		//如果url包含loginForm.jsp就放行
		if ((url.indexOf("loginForm.jsp")!=-1)||(url.indexOf("login_face.jsp")!=-1)) {
			chain.doFilter(request, response);  //放行，访问的下一个目标资源
		}else {
			HttpSession session = re.getSession();//获取会话对象
			Integer empid = (Integer)session.getAttribute("empid");//把登入成功后存储的empid的值取出
			//如果员工id不为空--登入状态，放行 ；为空--未登入状态，跳转到登入页面（继续判断）
			if (empid!=null &&!"".equals(empid)) {
				chain.doFilter(request, response);  //放行，访问的下一个目标资源
			}else {
				//如果url是xx_main.jsp或者是Svl，就重定向到loginForm.jsp  反之是../loginForm.jsp
				if (url.indexOf("main.jsp")!=-1||url.indexOf("Svl")!=-1) {
					rq.sendRedirect("loginForm.jsp"); //响应重定向，不带数据
				}else{
					rq.sendRedirect("../loginForm.jsp"); //响应重定向，不带数据
				}
			}
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
