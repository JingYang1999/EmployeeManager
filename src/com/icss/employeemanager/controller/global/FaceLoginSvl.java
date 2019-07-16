package com.icss.employeemanager.controller.global;

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
import net.sf.json.JSONObject;

/**
 * Servlet implementation class FaceLoginSvl
 */
public class FaceLoginSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FaceLoginSvl() {
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
		String base = request.getParameter("base");
		HttpSession session = request.getSession();
		JSONObject json = new JSONObject();
		json.put("message", "");
		EmployeeBiz biz = new EmployeeBiz();
		ArrayList<EmployeeEntity> list = biz.findAllEmp(); //获取所有员工信息
		for (int i = 0; i < list.size(); i++) {
			EmployeeEntity emp = list.get(i);
			if (emp.getFacePath() != null && !emp.getFacePath().trim().equals("")) {
				String base1 = this.getImageStr(emp.getFacePath());
				FaceClient faceClient = FaceClient.getInstance();
				boolean loginBool = faceClient.faceContrast(base, base1);
				if (loginBool) {
					json.put("message", "登录成功");
					json.put("this_role", emp.getRole());
					json.put("current_user",emp);
					
					session.setAttribute("empid", emp.getEmpId());
					session.setAttribute("empname", emp.getEmpname());
					session.setAttribute("empentity", emp);
					break;
				}
			}
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

	public String getImageStr(String imgFile) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		InputStream in = null;
		byte[] data = null;
		// 读取图片字节数组
		try {
			in = new FileInputStream(imgFile);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);// 返回Base64编码过的字节数组字符串
	}

}
