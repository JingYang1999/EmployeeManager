package com.icss.employeemanager.controller.job;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.employeemanager.biz.JobBiz;
import com.icss.employeemanager.entity.JobEntity;
import com.icss.employeemanager.utils.ResultPage;

/**
 * Servlet implementation class FindAllJobSvl
 */
public class FindAllJobSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FindAllJobSvl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String current = request.getParameter("current");
		int currentPage = 1;
		if (current!=null && !"".equals(current)) {
			currentPage = Integer.parseInt(current);
		}
		JobBiz biz = new JobBiz();
		ResultPage<JobEntity> pageInfo = new ResultPage<JobEntity>();
		pageInfo.setCurrentPage(currentPage);  
		biz.findAllJob(pageInfo);
		System.out.println(pageInfo);
		request.setAttribute("pageinfo", pageInfo);
		request.getRequestDispatcher("job/job.jsp").forward(request, response);
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}