package com.icss.employeemanager.controller.job;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.employeemanager.biz.JobBiz;

/**
 * Servlet implementation class AddJobSvl
 */
public class AddJobSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddJobSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//1.接收页面参数
		String jobname=request.getParameter("jobname");
		String jobdetail=request.getParameter("jobdetail");
		//2.调用biz方法
		JobBiz biz=new JobBiz();
		boolean result = false;
		try {
			result = biz.insertJob(jobname,jobdetail);
		} catch (Exception e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
