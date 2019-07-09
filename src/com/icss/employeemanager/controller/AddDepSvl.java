package com.icss.employeemanager.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.employeemanager.biz.DeptBiz;

/**
 * 添加部门
 */
public class AddDepSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddDepSvl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   System.out.println("fadfsadas");
		//1.接收页面参数
	  String depname = request.getParameter("depname");
	  String depdetail = request.getParameter("depdetail");
	  
	  //2.调用biz方法
	  DeptBiz biz = new DeptBiz();
	  boolean result = biz.insertDep(depname,depdetail);
	  
	  //3.响应数据
	  response.getWriter().print(result);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
