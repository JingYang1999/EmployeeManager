package com.icss.employeemanager.biz;

import java.util.ArrayList;

import com.icss.employeemanager.dao.DeptDao;
import com.icss.employeemanager.entity.DeptEntity;
import com.icss.employeemanager.utils.ResultPage;

public class DeptBiz {
	DeptDao dao = new DeptDao();

	public void findAllDept(ResultPage<DeptEntity> pageInfo) {
		try {
			int pageSize = 4;
			int firstCount = (pageInfo.getCurrentPage() - 1) * pageSize;
			ArrayList<DeptEntity> deps = dao.findAllDept(pageSize, firstCount);
			pageInfo.setLists(deps);
			int totalCount = dao.getDeptCount();
			pageInfo.setTotalCount(totalCount);
			int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
			pageInfo.setTotalPage(totalPage);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dao.closeConnection();
		}
	}

	public void delDep(String[] ids) {
		try {
			dao.delDep(ids);
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
//			关闭连接
			dao.closeConnection();
		}
	}

}
