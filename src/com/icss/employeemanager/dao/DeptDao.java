package com.icss.employeemanager.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.icss.employeemanager.entity.DeptEntity;

/**
*部门的数据层操作
*@author Giselle
*/
public class DeptDao extends BaseDao{
    
	//分页查询所有部门信息
	public ArrayList<DeptEntity> findAllDept(int pageSize, int firstCount) throws Exception {
		//1.2 打开连接
		openConnection();
		//3.写sql
		String sql = "select * from t_dep limit ?,?";
		//4.创建预编译对象
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, firstCount);
		pst.setInt(2, pageSize);
		//5.执行sql  查询：executeQuery-返回resultset结果集   增删改：executeUpdate--返回受影响行数
		 ResultSet rs = pst.executeQuery();
		//6.如果查询处理resultset结果集  1.选择什么类型接收数据   2.next() 3.getXX
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
    
	//获取部门总数
	public int getDeptCount() throws Exception {
		// 1.2 打开连接
		openConnection();
		// 3.写sql
		String sql = "select count(*) from t_dep";
		// 4.创建预编译对象
		PreparedStatement pst = conn.prepareStatement(sql);
		// 5.执行sql 查询：executeQuery-返回resultset结果集 增删改：executeUpdate--返回受影响行数
		ResultSet rs = pst.executeQuery();
		// 6.如果查询处理resultset结果集 1.选择什么类型接收数据 2.next() 3.getXX
		int count = 0;
		while(rs.next()){
			count = rs.getInt(1);
		}
		return count;
	}
     
	//删除部门
	public void delDep(String[] ids) throws Exception {
		// 1.2 打开连接
		openConnection();
		// 3.写sql  in (1,2,)
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
		// 4.创建预编译对象
		PreparedStatement pst = conn.prepareStatement(sql);
		// 5.执行sql 查询：executeQuery-返回resultset结果集 增删改：executeUpdate--返回受影响行数
		pst.executeUpdate();
		// 6.如果查询处理resultset结果集 1.选择什么类型接收数据 2.next() 3.getXX
	}

	//是否添加过此部门
	public boolean checkDepName(String depname) throws Exception {
		// 1.2 打开连接
		openConnection();
		// 3.写sql
		String sql = "select * from t_dep where depname=?";
		// 4.创建预编译对象
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, depname);
		// 5.执行sql 查询：executeQuery-返回resultset结果集 增删改：executeUpdate--返回受影响行数
		ResultSet rs = pst.executeQuery();
		// 6.如果查询处理resultset结果集 1.选择什么类型接收数据 2.next() 3.getXX
		int depid = 0;
		while (rs.next()) {
			depid = rs.getInt(1);
		}
        boolean result = false;  //是否可以注册的标识符
		if (depid==0) {  //如果有id就证明数据库中存在此名称，反之，可以注册
			result = true; 
		}
		
		return result;
	}
    
	//添加部门
	public int insertDep(String depname, String depdetail) throws Exception {
		// 1.2 打开连接
		openConnection();
		// 3.写sql 
		String sql = "insert into t_dep(depname,depdetail) values(?,?)";
		// 4.创建预编译对象
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, depname);
		pst.setString(2, depdetail);
		// 5.执行sql 查询：executeQuery-返回resultset结果集 增删改：executeUpdate--返回受影响行数
		int res = pst.executeUpdate();
		// 6.如果查询处理resultset结果集 1.选择什么类型接收数据 2.next() 3.getXX
		return res;
	}
    
	//修改部门
	public void updateDep(String depid, String depname, String depdetail) throws Exception {
		// 1.2 打开连接
		openConnection();
		// 3.写sql
		String sql = "update t_dep set depname=?,depdetail=? where depid =?";
		// 4.创建预编译对象
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, depname);
		pst.setString(2, depdetail);
		pst.setInt(3, Integer.parseInt(depid));
		// 5.执行sql 查询：executeQuery-返回resultset结果集 增删改：executeUpdate--返回受影响行数
		pst.executeUpdate();
		// 6.如果查询处理resultset结果集 1.选择什么类型接收数据 2.next() 3.getXX

	}

}
