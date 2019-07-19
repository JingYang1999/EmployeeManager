package com.icss.employeemanager.biz;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.icss.employeemanager.dao.ApplyDao;
import com.icss.employeemanager.entity.ApplyEntity;

import com.icss.employeemanager.utils.ConstValue;
import com.icss.employeemanager.utils.ResultPage;

public class ApplyBiz {
	ApplyDao dao = new ApplyDao();

	public ResultPage<ApplyEntity> findApplyInfo(int currentPage, String findapplytype, String findapplystatus,
			int empid) {
		// TODO Auto-generated method stub

		ResultPage<ApplyEntity> pageInfo = new ResultPage<ApplyEntity>();
		pageInfo.setCurrentPage(currentPage);
		System.out.println(currentPage);
		int pageSize = ConstValue.getApfPageSize();// 从常量库调取页面大小
		int firstCount = (pageInfo.getCurrentPage() - 1) * pageSize;
		// pageInfo.setCurrentPage(firstCount);
		try {
			ArrayList<ApplyEntity> apps = dao.findAllApply(pageSize, firstCount, findapplytype, findapplystatus, empid);
			pageInfo.setLists(apps);
			int totalCount = dao.getApplyCount(findapplytype, findapplystatus, empid);
			pageInfo.setTotalCount(totalCount);
			int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
			pageInfo.setTotalPage(totalPage);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dao.closeConnection();
		}
		return pageInfo;
	}

	@SuppressWarnings("finally")
	public Boolean approveApply(String approve_flag, int apply_id, int approve_id) {
		// TODO Auto-generated method stub
		Boolean flag = false;
		try {
			flag = dao.approveApply(approve_flag, apply_id,approve_id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dao.closeConnection();
			return flag;
		}
	}

	public Boolean addApply(String applyreason, Timestamp starttime, Timestamp endtime, String remark, String applytype,
			int empid) {
		// TODO Auto-generated method stub
		Boolean flag = false;
		try {
			flag = dao.addApply(applyreason,starttime,endtime,remark,applytype,empid);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dao.closeConnection();
			return flag;
		}
	}

}
