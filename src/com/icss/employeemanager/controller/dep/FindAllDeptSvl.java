package com.icss.employeemanager.controller.dep;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.employeemanager.biz.DeptBiz;
import com.icss.employeemanager.entity.DeptEntity;
import com.icss.employeemanager.utils.ResultPage;

public class FindAllDeptSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FindAllDeptSvl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String current = request.getParameter("current");
		String finddepname = request.getParameter("finddepname");

		if(finddepname==null)finddepname="";
		int currentPage = 1;
		if (current!=null && !"".equals(current)) {
			currentPage = Integer.parseInt(current);
		}
		DeptBiz biz = new DeptBiz();
		ResultPage<DeptEntity> pageInfo ;
		
		pageInfo=biz.findAllDept(currentPage,finddepname);
		
		request.setAttribute("pageinfo", pageInfo);
		request.setAttribute("finddepname",finddepname);
		request.getRequestDispatcher("dept/dept.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
