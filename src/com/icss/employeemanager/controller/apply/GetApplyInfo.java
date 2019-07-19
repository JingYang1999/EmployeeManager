package com.icss.employeemanager.controller.apply;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icss.employeemanager.biz.ApplyBiz;
import com.icss.employeemanager.entity.ApplyEntity;
import com.icss.employeemanager.utils.ResultPage;

public class GetApplyInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetApplyInfo() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String current = request.getParameter("current");
		String findapplytype = request.getParameter("findapplytype");
		String findapplystatus = request.getParameter("findapplystatus");

		int empid = -1;
		String role = null;

		HttpSession session = request.getSession();
		role = (String) session.getAttribute("emprole");
		if (role.equals("1")) {
			empid = (int) session.getAttribute("empid");
		}

		if (findapplytype == null || findapplytype.equals("")) {
			findapplytype = "all";
		}
		if (findapplystatus == null || findapplystatus.equals("")) {
			findapplystatus = "all";
		}
		int currentPage = 1;
		if (current != null && !"".equals(current)) {
			currentPage = Integer.parseInt(current);
		}
		ApplyBiz biz = new ApplyBiz();
		ResultPage<ApplyEntity> pageInfo;

		pageInfo = biz.findApplyInfo(currentPage, findapplytype, findapplystatus, empid);

		request.setAttribute("pageinfo", pageInfo);
		request.setAttribute("findapplytype", findapplytype);
		request.setAttribute("findapplystatus", findapplystatus);
		request.getRequestDispatcher("apply/apply.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
