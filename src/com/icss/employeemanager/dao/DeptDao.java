package com.icss.employeemanager.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.icss.employeemanager.entity.DeptEntity;
import com.icss.employeemanager.entity.JobEntity;

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
		// 3.дsql
		String wherein="where depid in(";
		for (int i=0;i<ids.length;i++) {
			if(i==ids.length-1) {
				wherein+=ids[i];
			}else {
				wherein+=ids[i]+",";
			}
			wherein+=")";
		}
		String sql = "delete from t_dep "+wherein;
		// 4.����Ԥ�������
		PreparedStatement pst = conn.prepareStatement(sql);
		// 5.ִ��sql ��ѯ��executeQuery-����resultset����� ��ɾ�ģ�executeUpdate--������Ӱ������
		//ResultSet rs = pst.executeQuery();
		pst.executeUpdate();
		// 6.�����ѯ����resultset����� 1.ѡ��ʲô���ͽ������� 2.next() 3.getXX
		
		
	}
	//��鲿�������Ƿ��ظ�
	public boolean checkDepName(String depname) throws Exception {
		// TODO Auto-generated method stub
		//1.2 ������
			openConnection();
			//3.дsql
			String sql = "select * from t_dep where depname=?";
			//4.����Ԥ�������
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, depname);
			//5.ִ��sql  ��ѯ��executeQuery-����resultset�����   ��ɾ�ģ�executeUpdate--������Ӱ������
			ResultSet rs;
			rs = pst.executeQuery();
			//6.�����ѯ����resultset�����  1.ѡ��ʲô���ͽ�������   2.next() 3.getXX
			int depid=0;
			while(rs.next()){
				depid=rs.getInt(1);
			}
			 boolean result=false;
			if(depid==0){
				result=true;
			}
		return result;
	}
	//��Ӳ���
	public int insertDep(String depname, String depdetail) throws Exception {
		openConnection();
		String sql = "insert into t_dep(depname,depdetail) values(?,?) ";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1,depname);
		pst.setString(2,depdetail);
		
		int res=pst.executeUpdate();
		return res;
	}
	

	public void updateDep(String depid, String depname, String depdetail) throws SQLException {
		// TODO Auto-generated method stub
		int res=0;
		try {
			openConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql = "update t_dep set depname=?,depdetail=? where depid =?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1,depname);
		pst.setString(2,depdetail);
		res=Integer.parseInt(depid);
		pst.setInt(3,res);
		
		pst.executeUpdate();
	}

	public ArrayList<DeptEntity> getAllDep() throws Exception {

			// TODO Auto-generated method stub
			openConnection();
			String sql="select * from t_dep";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			ArrayList<DeptEntity> deps=new ArrayList<DeptEntity>();
			DeptEntity dep;
			while(rs.next())
			{
				dep = new DeptEntity();
				dep.setDepid(rs.getInt(1));
				dep.setDepname(rs.getString(2));
				dep.setDepdetail(rs.getString(3));
				deps.add(dep);
				dep=null;
			}
			return deps;
		}
	
}
