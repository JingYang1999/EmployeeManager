package com.icss.employeemanager.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import com.icss.employeemanager.entity.EmployeeEntity;

/**
 * ���ݲ㣺�������ݿ⣬����Ա����صĲ���
 * 
 * @author Giselle
 */
public class EmployeeDao extends BaseDao {
	// ��¼
	/*
	 * ��������Ҫ�� 1��������û�з���ֵ����--��д����ֵ���� û��--void
	 * 2����û�в�������--����Щ������д�β� û��--����
	 * 
	 */
	public EmployeeEntity login(String empname, String password) throws Exception {
		// 1.2 ������
		openConnection();
		// 3.дsql���
		String sql = "select * from t_employee where empname=? and password=? and status=?";
		// 4.����Ԥ�������
		PreparedStatement pst = conn.prepareStatement(sql);
		// ����У���ƴ���������û�У��Ϳ���ִ��sql��
		pst.setString(1, empname); // setXX��?��˳��,����--Ҫƴ��sql��������ֵ�� xx�����кͱ���Ҫһ��
		pst.setString(2, password);
		pst.setString(3, "1");

		// 5.ѡ��ִ��sql���ķ��� ��ɾ�ģ�executeupdate���ص�����Ӱ������� ��ѯ��
		// executeQuery������һ�������
		ResultSet rs = pst.executeQuery();
		System.out.println("dao.rs:" + rs);
		// 6.����ǲ�ѯ����Ҫ�������� ��1��ѡ������ ��2������ next()���ƶ�����һ��
		EmployeeEntity emp = null;
		while (rs.next()) {
			emp = new EmployeeEntity();
			System.out.println("dao.emp:" + emp);
			emp.setEmpId(rs.getInt("empid"));// getXX(����--�����ֶε�˳��/������ֶ���)��������ݿ����ֵ��1.xx�����ͺ����ݿ�����ֶε�����һ��
			emp.setEmpname(rs.getString("empname"));
			emp.setPassword(rs.getString("password"));
			emp.setRole(rs.getString("role"));

		}
		return emp;
	}

	// ���Ա��
	public int addEmployee(EmployeeEntity emp) throws Exception {
		// 1.2 ������
		openConnection();
		// 3.дsql
		String sql = "insert into t_employee(depid,jobid,empname,cardnumber,sex,education,email,phone,tel,party,qq,address,postcode,birthday,race,speciality,hobby,remark,createtime,password,role,status)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		// 4.����Ԥ�������
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, emp.getDepId());
		pst.setInt(2, emp.getJobId());
		pst.setString(3, emp.getEmpname());
		pst.setString(4, emp.getCardunmber());
		pst.setString(5, emp.getSex());
		pst.setString(6, emp.getEducation());
		pst.setString(7, emp.getEmail());
		pst.setString(8, emp.getPhone());
		pst.setString(9, emp.getTel());
		pst.setString(10, emp.getParty());
		pst.setString(11, emp.getQq());
		pst.setString(12, emp.getAddress());
		pst.setString(13, emp.getPostcode());
		pst.setTimestamp(14, emp.getBirthday());
		pst.setString(15, emp.getRace());
		pst.setString(16, emp.getSpeciality());
		pst.setString(17, emp.getHobby());
		pst.setString(18, emp.getRemark());
		pst.setTimestamp(19, new Timestamp(new Date().getTime()));
		pst.setString(20, emp.getPassword());
		pst.setString(21, emp.getRole());
		pst.setString(22, emp.getStatus());
		// 5.ִ��sql��� ��ɾ�ģ�executeupdate--����int���� ��ѯ��executequery--����resultset
		int res = pst.executeUpdate();
		// 6.��Ҫ���������� �� ����Ҫ ֻ�в�ѯ��Ҫ��������
		return res;
	}

	// �鿴����Ա��
//	public ArrayList<EmployeeEntity> findAllEmp() throws Exception {
//		openConnection();
//		String sql = "select * from t_employee e,t_dep d , t_job j where e.depid=d.depid and e.jobid=j.jobid and status=?";
//		PreparedStatement pst = conn.prepareStatement(sql);
//		pst.setString(1, "1");
//		ResultSet rs = pst.executeQuery();
//		ArrayList<EmployeeEntity> list = new ArrayList<EmployeeEntity>();// �������϶���
//		EmployeeEntity emp = null;
//		while (rs.next()) {
//			emp = new EmployeeEntity();
//			emp.setEmpId(rs.getInt("empid"));
//			emp.setEmpname(rs.getString("empname"));
//			emp.setJobname(rs.getString("jobname"));
//			emp.setDepname(rs.getString("depname"));
//			emp.setAddress(rs.getString("address"));
//			emp.setEducation(rs.getString("education"));
//			emp.setCreateTime(rs.getTimestamp("createtime"));
//			emp.setEmail(rs.getString("email"));
//			emp.setCardunmber(rs.getString("cardnumber"));
//			emp.setPhone(rs.getString("phone"));
//			emp.setSex(rs.getString("sex"));
//			emp.setStatus(rs.getString("status"));
//			list.add(emp);
//		}
//		return list;
//	}

	
	public ArrayList<EmployeeEntity> findAllEmp(int pageSize, int firstCount) throws Exception {
		openConnection();
		String sql = "select * from t_employee e,t_dep d , t_job j where e.depid=d.depid and e.jobid=j.jobid and status=? limit ?,?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, "1");
		pst.setInt(2, firstCount);
		pst.setInt(3, pageSize);
		ResultSet rs = pst.executeQuery();
		ArrayList<EmployeeEntity> emps=new ArrayList<EmployeeEntity>();	
		EmployeeEntity emp = null;
		while (rs.next()) {
			emp = new EmployeeEntity();
			emp.setEmpId(rs.getInt("empid"));
			emp.setEmpname(rs.getString("empname"));
			emp.setJobname(rs.getString("jobname"));
			emp.setDepname(rs.getString("depname"));
			emp.setAddress(rs.getString("address"));
			emp.setEducation(rs.getString("education"));
			emp.setCreateTime(rs.getTimestamp("createtime"));
			emp.setEmail(rs.getString("email"));
			emp.setCardunmber(rs.getString("cardnumber"));
			emp.setPhone(rs.getString("phone"));
			emp.setSex(rs.getString("sex"));
			emp.setStatus(rs.getString("status"));
			emps.add(emp);
		}
		return emps;
	}
	
	// �鿴Ա������(����Ա��id�鿴Ա������)
	public EmployeeEntity findEmpDetail(int empid) throws Exception {
		// 1.2������
		openConnection();
		// 3.дsql
		String sql = "select * from t_employee e,t_dep d , t_job j where e.depid=d.depid and e.jobid=j.jobid and e.empid=?";
		// 4.����Ԥ�������
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, empid);
		// 5.ִ��sql executequery
		ResultSet rs = pst.executeQuery();
		// 6.�������� 1.ѡ����ʲô�����ղ��� next getXX
		EmployeeEntity emp = null;
		while (rs.next()) {
			emp = new EmployeeEntity();
			emp.setEmpId(rs.getInt("empid"));// getXX������--�����ֶε�˳��������ݱ�����ֶ�����������ݿ����ֵ��1.��
			emp.setEmpname(rs.getString("empname"));
			emp.setRole(rs.getString("role"));
			emp.setJobname(rs.getString("jobname"));
			emp.setDepname(rs.getString("depname"));
			emp.setPassword(rs.getString("password"));
			emp.setAddress(rs.getString("address"));
			emp.setBirthday(rs.getTimestamp("birthday"));
			emp.setEducation(rs.getString("education"));
			emp.setCreateTime(rs.getTimestamp("createtime"));
			emp.setEmail(rs.getString("email"));
			emp.setFaceUrl(rs.getString("facepath"));
			emp.setFacePath(rs.getString("faceurl"));
			emp.setTel(rs.getString("tel"));
			emp.setPostcode(rs.getString("postcode"));
			emp.setCardunmber(rs.getString("cardnumber"));
			emp.setQq(rs.getString("qq"));
			emp.setParty(rs.getString("party"));
			emp.setPhone(rs.getString("phone"));
			emp.setSpeciality(rs.getString("speciality"));
			emp.setSex(rs.getString("sex"));
			emp.setStatus(rs.getString("status"));
			emp.setRace(rs.getString("race"));
			emp.setRemark(rs.getString("remark"));
			emp.setHobby(rs.getString("hobby"));
		}

		return emp;

	}

	// ��ȡԱ������
	public int getEmpCount() throws Exception {
		// 1.2������
		openConnection();
		// 3.дsql
		String sql = "select count(*) from t_employee where status=?";
		// 4.����Ԥ�������
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, "1");
		// 5.ִ��sql executequery
		ResultSet rs = pst.executeQuery();
		// 6.�������� 1.ѡ����ʲô�����ղ��� next getXX
		int res = 0;
		while (rs.next()) {
			res = rs.getInt(1);
		}
		return res;
	}
}
