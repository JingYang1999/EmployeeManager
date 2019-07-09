package com.icss.employeemanager.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.icss.employeemanager.entity.DeptEntity;

public class DeptDao extends BaseDao {
	public ArrayList<DeptEntity> findAllDept(int pageSize, int firstCount) throws Exception {
		openConnection();
		String sql = "select * from t_dep limit ?,?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, firstCount);
		pst.setInt(2, pageSize);
		ResultSet rs = pst.executeQuery();
		ArrayList<DeptEntity> deps = new ArrayList<DeptEntity>();
		DeptEntity dep = null;
		while (rs.next()) {
			dep = new DeptEntity();
			dep.setDepid(rs.getInt(1));
			dep.setDepname(rs.getString(2));
			dep.setDepdetail(rs.getString(3));
			deps.add(dep);
		}
		return deps;
	}

	public int getDeptCount() throws Exception {
		openConnection();
		String sql = "select count(*) from t_dep";
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		int count = 0;
		while (rs.next()) {
			count = rs.getInt(1);
		}
		return count;
	}
	
	//删除部门
	public void delDep(String[] ids) throws Exception {
		// 1.2 打开连接
		openConnection();
		// 3.写sql
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
		// 4.创建预编译对象
		PreparedStatement pst = conn.prepareStatement(sql);
		// 5.执行sql 查询：executeQuery-返回resultset结果集 增删改：executeUpdate--返回受影响行数
		//ResultSet rs = pst.executeQuery();
		pst.executeUpdate();
		// 6.如果查询处理resultset结果集 1.选择什么类型接收数据 2.next() 3.getXX
	}
	
}