package com.icss.employeemanager.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.icss.employeemanager.entity.DeptEntity;

/**
*���ŵ����ݲ����
*@author Giselle
*/
public class DeptDao extends BaseDao{
    
	//��ҳ��ѯ���в�����Ϣ
	public ArrayList<DeptEntity> findAllDept(int pageSize, int firstCount) throws Exception {
		//1.2 ������
		openConnection();
		//3.дsql
		String sql = "select * from t_dep limit ?,?";
		//4.����Ԥ�������
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, firstCount);
		pst.setInt(2, pageSize);
		//5.ִ��sql  ��ѯ��executeQuery-����resultset�����   ��ɾ�ģ�executeUpdate--������Ӱ������
		 ResultSet rs = pst.executeQuery();
		//6.�����ѯ����resultset�����  1.ѡ��ʲô���ͽ�������   2.next() 3.getXX
		 ArrayList<DeptEntity> deps = new ArrayList<DeptEntity>();
		 DeptEntity dep = null;
		 while(rs.next()){
			 dep = new DeptEntity();
			 dep.setDepid(rs.getInt(1));
			 dep.setDepname(rs.getString(2));
			 dep.setDepdetail(rs.getString(3));
			 deps.add(dep);
		 }
		return deps;
	}
    
	//��ȡ��������
	public int getDeptCount() throws Exception {
		// 1.2 ������
		openConnection();
		// 3.дsql
		String sql = "select count(*) from t_dep";
		// 4.����Ԥ�������
		PreparedStatement pst = conn.prepareStatement(sql);
		// 5.ִ��sql ��ѯ��executeQuery-����resultset����� ��ɾ�ģ�executeUpdate--������Ӱ������
		ResultSet rs = pst.executeQuery();
		// 6.�����ѯ����resultset����� 1.ѡ��ʲô���ͽ������� 2.next() 3.getXX
		int count = 0;
		while(rs.next()){
			count = rs.getInt(1);
		}
		return count;
	}
     
	//ɾ������
	public void delDep(String[] ids) throws Exception {
		// 1.2 ������
		openConnection();
		// 3.дsql  in (1,2,)
		String wherein = "where depid in(";
		for (int i = 0; i < ids.length; i++) {
			if (i==ids.length-1) {
				wherein += ids[i];
			}else {
				wherein += ids[i]+",";
			}
		}
		wherein+=")";
		String sql = "delete from t_dep "+wherein;
		System.out.println(sql);
		// 4.����Ԥ�������
		PreparedStatement pst = conn.prepareStatement(sql);
		// 5.ִ��sql ��ѯ��executeQuery-����resultset����� ��ɾ�ģ�executeUpdate--������Ӱ������
		pst.executeUpdate();
		// 6.�����ѯ����resultset����� 1.ѡ��ʲô���ͽ������� 2.next() 3.getXX
	}

	//�Ƿ���ӹ��˲���
	public boolean checkDepName(String depname) throws Exception {
		// 1.2 ������
		openConnection();
		// 3.дsql
		String sql = "select * from t_dep where depname=?";
		// 4.����Ԥ�������
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, depname);
		// 5.ִ��sql ��ѯ��executeQuery-����resultset����� ��ɾ�ģ�executeUpdate--������Ӱ������
		ResultSet rs = pst.executeQuery();
		// 6.�����ѯ����resultset����� 1.ѡ��ʲô���ͽ������� 2.next() 3.getXX
		int depid = 0;
		while (rs.next()) {
			depid = rs.getInt(1);
		}
        boolean result = false;  //�Ƿ����ע��ı�ʶ��
		if (depid==0) {  //�����id��֤�����ݿ��д��ڴ����ƣ���֮������ע��
			result = true; 
		}
		
		return result;
	}
    
	//��Ӳ���
	public int insertDep(String depname, String depdetail) throws Exception {
		// 1.2 ������
		openConnection();
		// 3.дsql 
		String sql = "insert into t_dep(depname,depdetail) values(?,?)";
		// 4.����Ԥ�������
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, depname);
		pst.setString(2, depdetail);
		// 5.ִ��sql ��ѯ��executeQuery-����resultset����� ��ɾ�ģ�executeUpdate--������Ӱ������
		int res = pst.executeUpdate();
		// 6.�����ѯ����resultset����� 1.ѡ��ʲô���ͽ������� 2.next() 3.getXX
		return res;
	}
    
	//�޸Ĳ���
	public void updateDep(String depid, String depname, String depdetail) throws Exception {
		// 1.2 ������
		openConnection();
		// 3.дsql
		String sql = "update t_dep set depname=?,depdetail=? where depid =?";
		// 4.����Ԥ�������
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, depname);
		pst.setString(2, depdetail);
		pst.setInt(3, Integer.parseInt(depid));
		// 5.ִ��sql ��ѯ��executeQuery-����resultset����� ��ɾ�ģ�executeUpdate--������Ӱ������
		pst.executeUpdate();
		// 6.�����ѯ����resultset����� 1.ѡ��ʲô���ͽ������� 2.next() 3.getXX

	}

}
