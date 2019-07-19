package com.icss.employeemanager.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.icss.employeemanager.entity.DeptEntity;
import com.icss.employeemanager.entity.JobEntity;
import com.mysql.cj.protocol.Resultset;

public class JobDao extends BaseDao {
	public ArrayList<JobEntity> findAllJob(int pageSize, int firstCount) throws Exception {
		openConnection();
		String sql = "select * from t_job limit ?,?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, firstCount);
		pst.setInt(2, pageSize);
		ResultSet rs = pst.executeQuery();
		ArrayList<JobEntity> jobs = new ArrayList<JobEntity>();
		JobEntity job = null;
		while (rs.next()) {
			job = new JobEntity();
			job.setJobid(rs.getInt(1));
			job.setJobname(rs.getString(2));
			job.setJobdetail(rs.getString(3));
			jobs.add(job);
		}
		return jobs;
	}

	public int getJobCount() throws Exception {
		openConnection();
		String sql = "select count(*) from t_job";
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		int count = 0;
		while (rs.next()) {
			count = rs.getInt(1);
		}
		return count;
	}

	public ArrayList<JobEntity> getAllJob() throws Exception {
		// TODO Auto-generated method stub
		openConnection();
		String sql="select * from t_job";
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs=pst.executeQuery();
		ArrayList<JobEntity> jobs=new ArrayList<JobEntity>();
		JobEntity job;
		while(rs.next())
		{
			job = new JobEntity();
			job.setJobid(rs.getInt(1));
			job.setJobname(rs.getString(2));
			job.setJobdetail(rs.getString(3));
			jobs.add(job);
		}
		return jobs;
	}

	public boolean checkJobName(String jobname) throws Exception {
		// TODO Auto-generated method stub//1.2 打开连接
		openConnection();
		//3.写sql
		String sql="select * from t_job where jobname=?";
		//4.创建预编译对象
		PreparedStatement pst=(PreparedStatement) conn.prepareStatement(sql);
		pst.setString(1, jobname);
		//5.执行sql  查询：executeQuery-返回resultset结果集 增删改：executeUpdate--返回受影响行数
		ResultSet rs=pst.executeQuery();
		//6、如果查询处理resultset结果集 1.选择什么类型  2.next（） 3.getXX
		int jobid=0;
		while(rs.next()) {
			jobid=rs.getInt(1);
		}
		boolean result=false;//是否可以注册的标识符
		if(jobid==0) {//如果有id,说明数据库已存在该部门名称。反之，不存在，可以注册
			result=true;
		}
		return result;
	}

	public int insertJob(String jobname, String jobdetail) throws Exception {
		// TODO Auto-generated method stub
		//1.2 打开连接
		openConnection();
		//3.写sql
		String sql="insert into t_job(jobname,jobdetail) values(?,?)";
		//4.创建预编译对象
		PreparedStatement pst=(PreparedStatement) conn.prepareStatement(sql);
		pst.setString(1, jobname);
		pst.setString(2, jobdetail);
		//5.执行sql语句,  查询：executeQuery-返回resultset结果集 增删改：executeUpdate--返回受影响行数
		int res=pst.executeUpdate();
		//6、如果查询处理resultset结果集 1.选择什么类型  2.next（） 3.getXX
		return res;
	}
}