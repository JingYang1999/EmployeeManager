package com.icss.employeemanager.controller.dep;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.employeemanager.biz.DeptBiz;

/**
 * Servlet implementation class AddDepSvl
 *��Ӳ���
 */
public class AddDepSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddDepSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.����ҳ�����
		String depname =request.getParameter("depname");
		String depdetail =request.getParameter("depdetail");
		//System.out.println(depname+"    "+depdetail);
		//2.����ҵ�����󣬵���biz����
		DeptBiz biz= new DeptBiz();	
		boolean result=false;
		try {
			result = biz.insertDep(depname,depdetail);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//3.��Ӧ����
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
