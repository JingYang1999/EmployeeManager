package com.icss.employeemanager.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.employeemanager.biz.DeptBiz;

/**
 * �޸Ĳ���
 */
public class UpdateDepSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateDepSvl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   request.setCharacterEncoding("utf-8");  //post��Ч���޸ı����ʽ
	   //1.���ղ���
	   String depid = request.getParameter("id");
	   String depname = request.getParameter("name");
	   String depdetail = request.getParameter("remark");
//	   2.����ҵ�����󣬵��÷���
	   DeptBiz biz = new DeptBiz();
	   biz.updateDep(depid,depname,depdetail);
//     3.����ת����FindAllDepSvl
	   request.getRequestDispatcher("FindAllDeptSvl").forward(request, response);
	   
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
