package com.icss.employeemanager.controller.dep;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.employeemanager.biz.DeptBiz;

/**
 * Servlet implementation class CheckDepNameSvl
 */
public class CheckDepNameSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckDepNameSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.����ҳ�洫�ݵĲ���
		String depname =request.getParameter("depname");
		//2.����ҵ�����󣬵��÷���
		System.out.println(depname);
		DeptBiz biz= new DeptBiz();
		boolean result=biz.checkDepName(depname);//true��ע��
		//3.��Ӧ����
		response.getWriter().print(result);
		//request.getRequestDispatcher("FindAllDeptSvl").forward(request,response);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
