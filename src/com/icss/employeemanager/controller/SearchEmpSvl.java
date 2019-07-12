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

/**
 * Servlet implementation class SearchEmpSvl
 */
public class SearchEmpSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchEmpSvl() {
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
		String jobid = request.getParameter("job_id");

		String empname = request.getParameter("name");

		String cardnumber = request.getParameter("cardId");

		String sex = request.getParameter("sex");

		String phone = request.getParameter("phone");

		String depid = request.getParameter("dept_id");

		// System.out.println(jobid);

		EmployeeBiz biz = new EmployeeBiz();

		ArrayList<EmployeeEntity> emps = biz.searchEmp(jobid, empname, cardnumber, sex, phone, depid);

		DeptBiz depbiz = new DeptBiz();

		ArrayList<DeptEntity> deps = depbiz.getAllDep();

		// System.out.println(emps);

//		3获取所有职位列表

		JobBiz jobbiz = new JobBiz();

		ArrayList<JobEntity> jobs = null;

		try {

			jobs = jobbiz.getAllJob();

		} catch (Exception e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

		request.setAttribute("deps", deps);

		request.setAttribute("jobs", jobs);

		request.setAttribute("emps", emps);

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
