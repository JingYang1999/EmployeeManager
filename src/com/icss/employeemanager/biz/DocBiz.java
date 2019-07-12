package com.icss.employeemanager.biz;

import com.icss.employeemanager.dao.DocDao;
import com.icss.employeemanager.entity.DocEntity;

public class DocBiz {
	
	DocDao dao=new DocDao();

	public int addDoc(DocEntity doc) {
		int flag=0;
		// TODO Auto-generated method stub
		try {
			flag=dao.addDoc(doc);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		dao.closeConnection();
		return flag;
	}



}
