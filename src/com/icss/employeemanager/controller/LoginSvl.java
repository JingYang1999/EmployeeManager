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
	    //1.接收页面的参数
		String empname = request.getParameter("loginname");  //获取输入框输入的内容，参数和input的name的属性值必须一致
	    String password = request.getParameter("password");
        
	    //2.创建业务逻辑层的对象，调用登录方法，拿到返回值
	    EmployeeBiz biz = new EmployeeBiz();//创建业务层对象
	    EmployeeEntity emp = biz.login(empname, password);  
	    
	    //3.把数据保存到域对象
	    //如果得到的数据为null是登录失败，返回到登入页面，并返回错误信息；如果不为空判断角色跳转到不同页面
	    String url = "";
	    if (emp==null) {
	    	request.setAttribute("msg", "员工姓名或者密码错误");  //把数据保存到请求域
	    	url="loginForm.jsp";  //请求转发的目标资源
		}else {
			HttpSession session = request.getSession();//获得session对象
			session.setAttribute("empid", emp.getEmpId()); //把数据保存到会话域
			session.setAttribute("empname", emp.getEmpname());
			//判断角色   1--普通用户  2--管理员
			if (emp.getRole().equals("1")) {
				url = "emp_main.jsp";
			}else {
				url = "admin_main.jsp";
			}
		}
	    //4.请求转发
    	request.getRequestDispatcher(url).forward(request, response); //请求转发到登入页面

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
