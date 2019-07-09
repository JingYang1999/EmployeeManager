package com.icss.employeemanager.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.employeemanager.biz.DeptBiz;

/**
 * ��鲿�������Ƿ���ӹ�
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
//	    1.����ҳ�洫�ݵĲ���
		String depname = request.getParameter("depname");
//		2.����biz���󣬵��÷������õ�����ֵ
		DeptBiz biz = new DeptBiz();
		boolean result =  biz.checkDepName(depname);   //true:����ע��  false��������
//		3.��Ӧ����
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
