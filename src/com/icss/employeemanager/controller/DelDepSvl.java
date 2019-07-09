package com.icss.employeemanager.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.employeemanager.biz.DeptBiz;

/**
 * ɾ������
 */
public class DelDepSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DelDepSvl() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.����ҳ�洫�ݵĲ���
		String[] ids = request.getParameterValues("ids");
		// 2.����ҵ�����󣬵��÷���
		DeptBiz biz = new DeptBiz();
		biz.delDep(ids);
		// 3.����ת����FindAllDepSvl
		request.getRequestDispatcher("FindAllDeptSvl").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
