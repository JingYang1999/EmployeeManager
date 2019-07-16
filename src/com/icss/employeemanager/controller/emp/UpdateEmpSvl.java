package com.icss.employeemanager.controller.emp;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.employeemanager.biz.EmployeeBiz;
import com.icss.employeemanager.entity.EmployeeEntity;

public class UpdateEmpSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateEmpSvl() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EmployeeEntity emp = new EmployeeEntity();
		EmployeeBiz biz = new EmployeeBiz();

		request.setCharacterEncoding("utf-8");
		
		
		emp.setEmpId(Integer.parseInt(request.getParameter("id")));
		emp.setEmpname(request.getParameter("name"));
		emp.setCardunmber(request.getParameter("cardId"));
		emp.setSex(request.getParameter("sex"));
		emp.setJobId(Integer.parseInt(request.getParameter("job_id")));
		emp.setEducation(request.getParameter("education"));
		emp.setEmail(request.getParameter("email"));
		emp.setPhone(request.getParameter("phone"));
		emp.setTel(request.getParameter("tel"));
		emp.setParty(request.getParameter("party"));
		emp.setQq(request.getParameter("qqNum"));
		emp.setAddress(request.getParameter("address"));
		emp.setPostcode(request.getParameter("postCode"));
		//emp.setBirthday(getBD(request.getParameter("birthday")));
		emp.setRace(request.getParameter("race"));
		emp.setSpeciality(request.getParameter("speciality"));
		emp.setHobby(request.getParameter("hobby"));
		emp.setRemark(request.getParameter("remark"));
		emp.setDepId(Integer.parseInt(request.getParameter("deptid")));

		System.out.println(emp);

//		String empid = request.getParameter("id");
//		String empname = request.getParameter("name");
//		String cardnumber = request.getParameter("cardId");
//		String sex = request.getParameter("sex");
//		String jobid = request.getParameter("job_id");
//		String education = request.getParameter("education");
//		String email = request.getParameter("email");
//		String phone = request.getParameter("phone");
//		String tel = request.getParameter("tel");
//		String party = request.getParameter("party");
//		String qq = request.getParameter("qqNum");
//		String address = request.getParameter("address");
//		String postcode = request.getParameter("postCode");
//		String birthday = request.getParameter("birthday");
//		String race = request.getParameter("race");
//		String speciality = request.getParameter("speciality");
//		String hobby = request.getParameter("hobby");
//		String remark = request.getParameter("remark");
//		String depid = request.getParameter("deptid");

		
		
		biz.updateEmp(emp);
		
		request.getRequestDispatcher("/EmployeeManager/FindAllEmpSvl").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	private Timestamp getBD(String s) {
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		Timestamp ts=null;
		try {
			ts=new Timestamp(sf.parse(s).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return ts;
		
	}
}
