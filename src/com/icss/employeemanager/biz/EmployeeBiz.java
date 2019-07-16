package com.icss.employeemanager.biz;

import java.util.ArrayList;
import com.icss.employeemanager.dao.EmployeeDao;
import com.icss.employeemanager.entity.EmployeeEntity;

public class EmployeeBiz {
	EmployeeDao dao = new EmployeeDao();

	public EmployeeEntity login(String empname, String password) {
		EmployeeEntity emp = null;
		try {
			emp = dao.login(empname, password);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dao.closeConnection();
		}
		return emp;
	}

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

	public ArrayList<EmployeeEntity> findAllEmp() {
		ArrayList<EmployeeEntity> emplist = null;
		try {
			emplist = dao.findAllEmp();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dao.closeConnection();
		}
		return emplist;
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

	public ArrayList<EmployeeEntity> searchEmp(String jobid, String empname, String cardnumber, String sex,
			String phone, String depid) {
		ArrayList<EmployeeEntity> emps = null;
		try {
			emps = dao.searchEmp(jobid, empname, cardnumber, sex, phone, depid);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dao.closeConnection();
		}
		return emps;
	}

	public void delEmp(String[] empids, Integer loginEmpid) {
		try {
			dao.delEmp(empids, loginEmpid);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dao.closeConnection();
		}
	}

	public void updateEmp(EmployeeEntity emp) {
		// TODO Auto-generated method stub
		try {
			dao.updateEmp(emp);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dao.closeConnection();
		}
	}

	public void updateFaceUrlByName1(Integer empid, String urlPath, String path) {
		// TODO Auto-generated method stub
		try {
			dao.updateFaceUrlByName(empid,urlPath,path) ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dao.closeConnection();
		}
	}

	public Boolean checkPswd(int empid, String pswd) {
		// TODO Auto-generated method stub
		boolean flag=false;
		try {
			flag=dao.checkPswd(empid,pswd);
			System.out.println("flag+"+flag);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dao.closeConnection();
			return flag;
		}
	}

	public Boolean updatePswd(int empid, String pswd) {
		// TODO Auto-generated method stub
		boolean flag=false;
		try {
			flag=dao.updatePswd(empid,pswd);
			System.out.println("flag+"+flag);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dao.closeConnection();
			return flag;
		}
	}
}