package com.icss.employeemanager.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.employeemanager.biz.DeptBiz;
import com.icss.employeemanager.entity.DeptEntity;
import com.icss.employeemanager.utils.ResultPage;

/**
 * 查看所有部门
 */
public class FindAllDeptSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FindAllDeptSvl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	    1.获取页面传递过来的参数--current(第一次访问没有，赋初始值) 
		String current = request.getParameter("current");
		int currentPage = 1;  //当前页，模式是1第一页
		//非空判断current有没有值，没有值就默认是第一页
		if (current!=null && !"".equals(current)) {
			currentPage = Integer.parseInt(current); //类型转换，String--int
		}
//	    2. 创建biz对象，调用方法，得到返回值
		DeptBiz biz = new DeptBiz();
		ResultPage<DeptEntity> pageInfo = new ResultPage<DeptEntity>(); //创建工具类对象
		pageInfo.setCurrentPage(currentPage);  
		biz.findAllDept(pageInfo);
		System.out.println(pageInfo);
//	    3.把数据保存到请求域
		request.setAttribute("pageinfo", pageInfo);
//	    4.请求转发  
		request.getRequestDispatcher("dept/dept.jsp").forward(request, response);
	
	    
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
