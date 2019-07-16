package com.icss.employeemanager.controller.emp;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.icss.employeemanager.biz.EmployeeBiz;
public class DelEmpSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public DelEmpSvl() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] empids = request.getParameterValues("empids");
		HttpSession session=request.getSession();
		Integer loginEmpid=(Integer) session.getAttribute("empid");
		EmployeeBiz biz = new EmployeeBiz();
		biz.delEmp(empids,loginEmpid);
		
		System.out.println("Del+empid="+empids);
		request.getRequestDispatcher("FindAllEmpSvl").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
