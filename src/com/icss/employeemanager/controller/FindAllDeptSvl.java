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
 * �鿴���в���
 */
public class FindAllDeptSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FindAllDeptSvl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	    1.��ȡҳ�洫�ݹ����Ĳ���--current(��һ�η���û�У�����ʼֵ) 
		String current = request.getParameter("current");
		int currentPage = 1;  //��ǰҳ��ģʽ��1��һҳ
		//�ǿ��ж�current��û��ֵ��û��ֵ��Ĭ���ǵ�һҳ
		if (current!=null && !"".equals(current)) {
			currentPage = Integer.parseInt(current); //����ת����String--int
		}
//	    2. ����biz���󣬵��÷������õ�����ֵ
		DeptBiz biz = new DeptBiz();
		ResultPage<DeptEntity> pageInfo = new ResultPage<DeptEntity>(); //�������������
		pageInfo.setCurrentPage(currentPage);  
		biz.findAllDept(pageInfo);
		System.out.println(pageInfo);
//	    3.�����ݱ��浽������
		request.setAttribute("pageinfo", pageInfo);
//	    4.����ת��  
		request.getRequestDispatcher("dept/dept.jsp").forward(request, response);
	
	    
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
