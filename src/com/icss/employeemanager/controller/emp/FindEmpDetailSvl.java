package com.icss.employeemanager.controller.emp;

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

public class FindEmpDetailSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FindEmpDetailSvl() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int empid = Integer.parseInt(request.getParameter("empid"));
		EmployeeBiz empbiz = new EmployeeBiz();
		JobBiz jobbiz = new JobBiz();
		DeptBiz depbiz = new DeptBiz();
		EmployeeEntity emp = new EmployeeEntity();
		emp = empbiz.findEmpDetail(empid);
		ArrayList<DeptEntity> deps = depbiz.getAllDep();
		ArrayList<JobEntity> jobs = jobbiz.getAllJob();

		request.setAttribute("deps", deps);
		request.setAttribute("emp", emp);
		request.setAttribute("jobs", jobs);

		request.getRequestDispatcher("employee/showUpdateEmployee.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
