package com.icss.employeemanager.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.employeemanager.biz.DeptBiz;
import com.icss.employeemanager.biz.EmployeeBiz;
import com.icss.employeemanager.biz.JobBiz;
import com.icss.employeemanager.entity.DeptEntity;
import com.icss.employeemanager.entity.EmployeeEntity;
import com.icss.employeemanager.entity.JobEntity;
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
		/*
		 * String current = request.getParameter("current"); int currentPage = 1; if
		 * (current != null && !"".equals(current)) { currentPage =
		 * Integer.parseInt(current); }
		 */
		EmployeeBiz empbiz = new EmployeeBiz();
		ArrayList<EmployeeEntity> emps = empbiz.findAllEmp();
		//System.out.println("svl.emps=" + emps);
		DeptBiz deptbiz = new DeptBiz();
		ArrayList<DeptEntity> deps = deptbiz.getAllDep();
		JobBiz jobbiz = new JobBiz();
		ArrayList<JobEntity> jobs = jobbiz.getAllJob();
		request.setAttribute("deps", deps);
		request.setAttribute("emps", emps);
		request.setAttribute("jobs", jobs);

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