package com.icss.employeemanager.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * BaseDao�����ݿ����ӹ���
 * 
 * @author Giselle
 */
public class BaseDao {
	protected Connection conn;

	// ������
	public void openConnection() throws Exception {
		// ֻ��Connection����Ϊnull���߱��رյ�����Ŵ�������
		Class.forName("com.mysql.cj.jdbc.Driver");

		String url = "jdbc:mysql://localhost:3306/m_empl_db?serverTimezone=GMT%2B8";
		String username = "root"; 
		String password = "jingyang";
		conn = DriverManager.getConnection(url, username, password);
		//System.out.println(conn.toString());
	}
	//�ر�����
	public void closeConnection(){
		//���Ӳ�Ϊ�յ�ʱ�򣬲���Ҫ�ر�
		if (conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} //�ر�����
		}
	}
	
}
