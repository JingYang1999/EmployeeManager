package com.icss.employeemanager.controller.global;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 退出登录
 */
public class LoginOutSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginOutSvl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  //1.清除session中的值
		HttpSession session = request.getSession();
		session.invalidate();  //立即清除session
	  //2.跳转到登录页面
		request.getRequestDispatcher("loginForm.jsp").forward(request, response);
	  }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
