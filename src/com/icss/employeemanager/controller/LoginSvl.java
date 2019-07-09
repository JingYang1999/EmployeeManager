package com.icss.employeemanager.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icss.employeemanager.biz.EmployeeBiz;
import com.icss.employeemanager.entity.EmployeeEntity;

/**
 * Servlet implementation class LoginSvl
 */
public class LoginSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginSvl() {
        super();
    }
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    //1.����ҳ��Ĳ���
		String empname = request.getParameter("loginname");  //��ȡ�������������ݣ�������input��name������ֵ����һ��
	    String password = request.getParameter("password");
	    
	    System.out.println(empname+"+"+password);
        
	    //2.����ҵ���߼���Ķ��󣬵��õ�¼�������õ�����ֵ
	    EmployeeBiz biz = new EmployeeBiz();//����ҵ������
	    EmployeeEntity emp = biz.login(empname, password);  
	    
	    //3.�����ݱ��浽�����
	    //����õ�������Ϊnull�ǵ�¼ʧ�ܣ����ص�����ҳ�棬�����ش�����Ϣ�������Ϊ���жϽ�ɫ��ת����ͬҳ��
	    String url = "";
	    if (emp==null) {
	    	request.setAttribute("msg", "找不到");  //�����ݱ��浽������
	    	url="loginForm.jsp";  //����ת����Ŀ����Դ
		}else {
			HttpSession session = request.getSession();//���session����
			session.setAttribute("empid", emp.getEmpId()); //�����ݱ��浽�Ự��
			session.setAttribute("empname", emp.getEmpname());
			//�жϽ�ɫ   1--��ͨ�û�  2--����Ա
			if (emp.getRole().equals("1")) {
				url = "emp_main.jsp";
			}else {
				url = "admin_main.jsp";
			}
		}
	    //4.����ת��
    	request.getRequestDispatcher(url).forward(request, response); //����ת��������ҳ��

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
