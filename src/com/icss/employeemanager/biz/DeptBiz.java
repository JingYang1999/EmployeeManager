package com.icss.employeemanager.biz;

import java.sql.SQLException;
import java.util.ArrayList;

import com.icss.employeemanager.dao.DeptDao;
import com.icss.employeemanager.entity.DeptEntity;
import com.icss.employeemanager.utils.ConstValue;
import com.icss.employeemanager.utils.ResultPage;

public class DeptBiz {
	DeptDao dao = new DeptDao();

	public ResultPage<DeptEntity> findAllDept(int currentPage, String finddepname) {
		ResultPage<DeptEntity> pageInfo = new ResultPage<DeptEntity>();
		pageInfo.setCurrentPage(currentPage);  
		int pageSize = ConstValue.getDepPageSize();//从常量库调取页面大小
		int firstCount = (pageInfo.getCurrentPage() - 1) * pageSize;
		try {
			ArrayList<DeptEntity> deps = dao.findAllDept(pageSize, firstCount,finddepname);
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
		return pageInfo;
	}

	public void delDep(String[] ids) {
		try {
			dao.delDep(ids);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			�ر�����
			dao.closeConnection();
		}
	}

	// ��鲿�������Ƿ���ӹ�
	public boolean checkDepName(String depname) {
		boolean result = false;
		try {
			result = dao.checkDepName(depname);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dao.closeConnection();
		}
		return result;
	}

	public boolean insertDep(String depname, String depdetail) throws Exception {
		int res = dao.insertDep(depname, depdetail);
		dao.closeConnection();
		return res > 0 ? true : false;
	}

	public void updateDep(String depid, String depname, String depdetail) throws SQLException {
		// TODO Auto-generated method stub
		dao.updateDep(depid, depname, depdetail);
		dao.closeConnection();
	}

	public ArrayList<DeptEntity> getAllDep() {
		ArrayList<DeptEntity> deps = null;
		try {
			deps = dao.getAllDep();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao.closeConnection();
		return deps;
	}

}
