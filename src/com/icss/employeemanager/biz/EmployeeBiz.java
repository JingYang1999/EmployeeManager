package com.icss.employeemanager.biz;

import java.util.ArrayList;

import com.icss.employeemanager.dao.EmployeeDao;
import com.icss.employeemanager.entity.EmployeeEntity;
import com.icss.employeemanager.utils.ResultPage;


public class EmployeeBiz {
	EmployeeDao dao = new EmployeeDao(); // �������ݲ����

	// ��¼����
	public EmployeeEntity login(String empname, String password) {
		EmployeeEntity emp = null;
		try {
			emp = dao.login(empname, password);
			System.out.println("biz:"+emp);
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // �����Ƿ����쳣������ִ��finally��
			dao.closeConnection(); // �ر���Դ
		}
		return emp;
	}

	// ���Ա��
	public int addEmployee(EmployeeEntity emp) {
		int res = 0;
		try {
			res = dao.addEmployee(emp);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dao.closeConnection();
		}
		return res;
	}


//	public ArrayList<EmployeeEntity> findAllEmp() {
//		ArrayList<EmployeeEntity> emplist = null;
//		try {
//			emplist = dao.findAllEmp();
////			int res = dao.getEmpCount(); 
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			dao.closeConnection();
//		}
//		return emplist;
//	}

	public void findAllEmp(ResultPage<EmployeeEntity> pageInfo) {
		try {
			int pageSize = 10;
			int firstCount = (pageInfo.getCurrentPage() - 1) * pageSize;
			ArrayList<EmployeeEntity> emps = dao.findAllEmp(pageSize, firstCount);
			pageInfo.setLists(emps);
			int totalCount = dao.getEmpCount();
			pageInfo.setTotalCount(totalCount);
			int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
			pageInfo.setTotalPage(totalPage);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dao.closeConnection();
		}
	}

	public EmployeeEntity findEmpDetail(int empid) {
		EmployeeEntity emp = null;
		try {
			emp = dao.findEmpDetail(empid);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dao.closeConnection();
		}
		return emp;
	}
}
