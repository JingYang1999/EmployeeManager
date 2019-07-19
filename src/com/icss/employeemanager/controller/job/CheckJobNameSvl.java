package com.icss.employeemanager.controller.job;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.employeemanager.biz.JobBiz;

/**
 * Servlet implementation class CheckJobNameSvl
 */
public class CheckJobNameSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckJobNameSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//1.接收页面传递的参数
		String jobname=request.getParameter("jobname");
		//2.创建biz对象，调用方法，拿到返回值
		JobBiz biz=new JobBiz();
		boolean result = false;
		try {
			result = biz.checkJobName(jobname);
		} catch (Exception e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}//true:可以注册    false：不可以
		//3.响应数据
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
