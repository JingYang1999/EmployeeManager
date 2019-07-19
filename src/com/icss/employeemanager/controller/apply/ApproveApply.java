package com.icss.employeemanager.controller.apply;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.icss.employeemanager.biz.ApplyBiz;

/**
 * Servlet implementation class ApproveApply
 */
public class ApproveApply extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ApproveApply() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		String approve_flag = request.getParameter("approve_flag");
		String app_id = request.getParameter("apply_id");
		HttpSession session = request.getSession();

		int approve_id=(int) session.getAttribute("empid");
		
		int apply_id = Integer.parseInt(app_id);

		JSONObject json = new JSONObject();
		json.put("message", "");

		ApplyBiz biz = new ApplyBiz();

		Boolean flag = biz.approveApply(approve_flag, apply_id,approve_id);
		if (flag.equals(true)) {
			json.put("message", "ok");
		} else {
			json.put("message", "失败");
		}
		response.getWriter().print(json.toString());
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
