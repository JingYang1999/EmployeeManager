package com.icss.employeemanager.controller.global;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icss.employeemanager.biz.EmployeeBiz;
import com.icss.employeemanager.entity.EmployeeEntity;

/**
 * Servlet implementation class LoginSvl
 */
public class LoginSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginSvl() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String empname = request.getParameter("loginname");
		String password = request.getParameter("password");
		EmployeeBiz biz = new EmployeeBiz();
		EmployeeEntity emp = biz.login(empname, password);
		String url = "";
		if (emp == null) {
			request.setAttribute("msg", "name 或者 pswd 不正确");
			url = "loginForm.jsp";
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("empid", emp.getEmpId());
			session.setAttribute("empname", emp.getEmpname());
			session.setAttribute("emprole", emp.getRole());
			session.setAttribute("empentity", emp);

			url = "main.jsp";
		}
		request.getRequestDispatcher(url).forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
