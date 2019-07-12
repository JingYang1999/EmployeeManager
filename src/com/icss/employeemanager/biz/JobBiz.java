package com.icss.employeemanager.biz;

import java.util.ArrayList;

import com.icss.employeemanager.dao.JobDao;
import com.icss.employeemanager.entity.DeptEntity;
import com.icss.employeemanager.entity.JobEntity;
import com.icss.employeemanager.utils.ResultPage;

public class JobBiz {
	JobDao dao = new JobDao(); 

	public void findAllJob(ResultPage<JobEntity> pageInfo) {
		try {
			int pageSize = 4;
			int firstCount = (pageInfo.getCurrentPage() - 1) * pageSize;
			ArrayList<JobEntity> jobs = dao.findAllJob(pageSize, firstCount);
			pageInfo.setLists(jobs);
			int totalCount = dao.getJobCount();
			pageInfo.setTotalCount(totalCount);
			int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
			pageInfo.setTotalPage(totalPage);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dao.closeConnection();
		}
	}public ArrayList<JobEntity> getAllJob(){
		
		ArrayList<JobEntity> jobs=null;
		try {
			jobs=dao.getAllJob();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dao.closeConnection();
		return jobs;
	}
}