package com.icss.employeemanager.biz;

import java.util.ArrayList;

import com.icss.employeemanager.dao.EmployeeDao;
import com.icss.employeemanager.entity.EmployeeEntity;

/**
 * ҵ��㣺���к�Ա����ص�ҵ���߼�����---�������ݲ����ҵ����
 * 
 * @author Giselle
 */
public class EmployeeBiz {
	EmployeeDao dao = new EmployeeDao(); // �������ݲ����
	
	// ��¼����
	public EmployeeEntity login(String empname, String password) {
		EmployeeEntity emp = null;
		try {
			emp = dao.login(empname, password);
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // �����Ƿ����쳣������ִ��finally��
			dao.closeConnection(); // �ر���Դ
		}
		return emp;
	}
	// ���Ա��
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
	// �鿴����Ա����Ϣ
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
	// �鿴Ա������
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
