package com.icss.employeemanager.controller.global;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icss.employeemanager.biz.EmployeeBiz;

import Decoder.BASE64Decoder;
import org.json.JSONObject;



/**
 * Servlet implementation class FaceRegisterSvl
 */
public class FaceRegisterSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FaceRegisterSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String base = request.getParameter("base");
		
		HttpSession session=request.getSession();
		JSONObject json=new JSONObject();
		json.put("message", "");
		String empname = (String)session.getAttribute("empname");
	    String path = "C:/inetpub/ftproot/file/head/";
        String urlPath=path+empname+".jpg";
        File uploadDir = new File(path);
        if (!uploadDir.exists() && !uploadDir.isDirectory()) {// 检查目录
			uploadDir.mkdirs();
		}
        path+=empname+".jpg";
		OutputStream out = null;
        InputStream is = null;
		try {
			byte[] imgByte  = new BASE64Decoder().decodeBuffer(base); 
			for (int i = 0; i < imgByte.length; ++i) {
				if (imgByte[i] < 0) {// 调整异常数据
				imgByte[i] += 256;
				}
			}
			out = new FileOutputStream(path);
			is = new ByteArrayInputStream(imgByte);
			byte[] buff = new byte[1024];
	        int len = 0;
	        while((len=is.read(buff))!=-1){
	            out.write(buff, 0, len);
	        }
	        
		} catch (IOException e) {
			json.put("message", "注册失败");
			e.printStackTrace();
			response.getWriter().print(json.toString());
		}finally{
			if(is!=null){
				try {
					is.close();
			       
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			}
		EmployeeBiz biz = new EmployeeBiz();
		biz.updateFaceUrlByName1((Integer)session.getAttribute("empid"), urlPath,path);
		json.put("message", "注册成功");
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
