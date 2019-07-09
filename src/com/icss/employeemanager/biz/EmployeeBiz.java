package com.icss.employeemanager.biz;

import java.util.ArrayList;

import com.icss.employeemanager.dao.EmployeeDao;
import com.icss.employeemanager.entity.EmployeeEntity;

/**
 * 业务层：所有和员工相关的业务逻辑处理---调用数据层进行业务处理
 * 
 * @author Giselle
 */
public class EmployeeBiz {
	EmployeeDao dao = new EmployeeDao(); // 创建数据层对象
	
	// 登录功能
	public EmployeeEntity login(String empname, String password) {
		EmployeeEntity emp = null;
		try {
			emp = dao.login(empname, password);
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // 无论是否有异常，都会执行finally块
			dao.closeConnection(); // 关闭资源
		}
		return emp;
	}
	// 添加员工
	public int addEmployee(EmployeeEntity emp){
		int res=0;
		try {
			res = dao.addEmployee(emp);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dao.closeConnection();
		}
		return res;
	}
	// 查看所有员工信息
	public ArrayList<EmployeeEntity> findAllEmp(){
		ArrayList<EmployeeEntity> emplist = null;
		try {
			emplist = dao.findAllEmp();
//			int res = dao.getEmpCount(); 
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dao.closeConnection();
		}
		return emplist;
	}
	// 查看员工详情
	public EmployeeEntity findEmpDetail(int empid){
		EmployeeEntity emp = null;
		try {
			emp = dao.findEmpDetail(empid);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dao.closeConnection();
		}
		return emp;
	}
}
