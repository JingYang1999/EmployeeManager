package com.icss.employeemanager.controller.doc;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.employeemanager.biz.DocBiz;
import com.icss.employeemanager.entity.DocEntity;
import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

/**
 * Servlet implementation class AddDocSvl
 */
public class AddDocSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddDocSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		SmartUpload smu=new SmartUpload();
		smu.initialize(getServletConfig(),request,response);
		try {
			smu.upload();
		} catch (SmartUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Files files=smu.getFiles();
		File file=files.getFile(0);
		
		String filepath="";
		String rootpath="C:/inetpub/ftproot";
		
		if(files.getSize()>0 && file.getSize()>0)
		{
			filepath = file.getFileName();
			try {
				file.saveAs(rootpath+filepath);
			} catch (SmartUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String title = smu.getRequest().getParameter("title");
			String remark = smu.getRequest().getParameter("title");
			int empid=Integer.parseInt(smu.getRequest().getParameter("empid"));
			
			
			DocEntity doc=new DocEntity(empid,title,filepath,remark);
			DocBiz biz=new DocBiz();
			int flag=biz.addDoc(doc);
			request.setAttribute("msg",flag>0?"成功":"失敗");
			
			request.getRequestDispatcher("document/showAddDocument.jsp").forward(request, response);
			
		}
		else return;
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
