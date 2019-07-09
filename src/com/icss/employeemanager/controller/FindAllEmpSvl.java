package com.icss.employeemanager.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.employeemanager.biz.EmployeeBiz;
import com.icss.employeemanager.entity.EmployeeEntity;
import com.icss.employeemanager.utils.ResultPage;

/**
 * Servlet implementation class FindAllEmpSvl
 */
public class FindAllEmpSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FindAllEmpSvl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String current = request.getParameter("current");
		int currentPage = 1;
		if (current != null && !"".equals(current)) {
			currentPage = Integer.parseInt(current);
		}
		EmployeeBiz biz = new EmployeeBiz();
		ResultPage<EmployeeEntity> pageInfo = new ResultPage<EmployeeEntity>();
		pageInfo.setCurrentPage(currentPage);
		biz.findAllEmp(pageInfo);
		System.out.println(pageInfo);
		request.setAttribute("pageinfo", pageInfo);
		request.getRequestDispatcher("employee/employee.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
