package com.icss.employeemanager.controller.global;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icss.employeemanager.biz.EmployeeBiz;
import com.icss.employeemanager.entity.EmployeeEntity;
import com.icss.employeemanager.utils.FaceClient;

import Decoder.BASE64Encoder;
import org.json.JSONObject;

/**
 * Servlet implementation class checkPswd
 */
public class checkPswd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public checkPswd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		String pswd = request.getParameter("old_pswd");
		
		HttpSession session = request.getSession();
		java.lang.Integer empid_s=(java.lang.Integer) session.getAttribute("empid");
		
		int empid = empid_s;
		
		JSONObject json = new JSONObject();
		json.put("message", "");
		EmployeeBiz biz = new EmployeeBiz();
		Boolean flag=biz.checkPswd(empid,pswd);
		if(flag.equals(true)) {
			json.put("message", "ok");
		}
		else {
			json.put("message", "密碼錯誤");
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

}
