package com.icss.employeemanager.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.employeemanager.biz.DeptBiz;

/**
 * 检查部门名称是否添加过
 */
public class CheckDepNameSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CheckDepNameSvl() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	    1.接收页面传递的参数
		String depname = request.getParameter("depname");
//		2.创建biz对象，调用方法，拿到返回值
		DeptBiz biz = new DeptBiz();
		boolean result =  biz.checkDepName(depname);   //true:可以注册  false：不可以
//		3.响应数据
		response.getWriter().print(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
