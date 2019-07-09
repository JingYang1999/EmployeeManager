package com.icss.employeemanager.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.employeemanager.biz.DeptBiz;

/**
 * 删除部门
 */
public class DelDepSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DelDepSvl() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.接收页面传递的参数
		String[] ids = request.getParameterValues("ids");
		// 2.创建业务层对象，调用方法
		DeptBiz biz = new DeptBiz();
		biz.delDep(ids);
		// 3.请求转发到FindAllDepSvl
		request.getRequestDispatcher("FindAllDeptSvl").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
