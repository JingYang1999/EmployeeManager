package com.icss.employeemanager.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.icss.employeemanager.entity.JobEntity;

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
}