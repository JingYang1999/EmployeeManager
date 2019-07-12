package com.icss.employeemanager.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

import com.icss.employeemanager.entity.DocEntity;

public class DocDao extends BaseDao{

	public int addDoc(DocEntity doc) throws Exception {
		// TODO Auto-generated method stub
		openConnection();
		String sql="insert into t_document values(null,?,?,?,?,?)";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, doc.getEmpid());
		
		pst.setString(2, doc.getTitle());
		pst.setString(3, doc.getFilepath());
		pst.setString(4, doc.getRemark());
		
		pst.setTimestamp(5, new Timestamp(new Date(0).getTime()));
		return 0;
	}

}
