package com.icss.employeemanager.controller.apply;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.icss.employeemanager.biz.ApplyBiz;

/**
 * Servlet implementation class addApply
 */
public class addApply extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addApply() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");

		String applyreason=request.getParameter("applyreason");
		Timestamp starttime=transtots(request.getParameter("starttime"));
		Timestamp endtime=transtots(request.getParameter("starttime"));
		String remark=request.getParameter("remark");
		String applytype=request.getParameter("applytype");
		
		HttpSession session = request.getSession();
		int empid=(int) session.getAttribute("empid");
		
		
		
		ApplyBiz biz = new ApplyBiz();
		
		System.out.println(applyreason+starttime+endtime+remark+applytype+empid);

		Boolean flag = biz.addApply(applyreason,starttime,endtime,remark,applytype,empid);
		
		

		JSONObject json = new JSONObject();
		json.put("message", "");

		if (flag.equals(true)) {
			json.put("message", "ok");
		} else {
			json.put("message", "失败");
		}
		response.getWriter().print(json.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private Timestamp transtots(String tsStr) {
		Timestamp ts = new Timestamp(System.currentTimeMillis());  
        try {  
            ts = Timestamp.valueOf(tsStr);  
            System.out.println(ts);  
            return ts;
        } catch (Exception e) {  
            e.printStackTrace();  
        }
		return null;  
	}

}
