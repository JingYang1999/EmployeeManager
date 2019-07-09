package com.icss.employeemanager.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.employeemanager.biz.DeptBiz;

/**
 * 修改部门
 */
public class UpdateDepSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateDepSvl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   request.setCharacterEncoding("utf-8");  //post生效，修改编码格式
	   //1.接收参数
	   String depid = request.getParameter("id");
	   String depname = request.getParameter("name");
	   String depdetail = request.getParameter("remark");
//	   2.创建业务层对象，调用方法
	   DeptBiz biz = new DeptBiz();
	   biz.updateDep(depid,depname,depdetail);
//     3.请求转发到FindAllDepSvl
	   request.getRequestDispatcher("FindAllDeptSvl").forward(request, response);
	   
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
