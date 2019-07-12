package com.icss.employeemanager.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import com.icss.employeemanager.entity.EmployeeEntity;
public class EmployeeDao extends BaseDao {
	public EmployeeEntity login(String empname, String password) throws Exception {
		openConnection();
		String sql = "select * from t_employee where empname=? and password=? and status=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, empname); 
		pst.setString(2, password);
		pst.setString(3, "1");
		ResultSet rs = pst.executeQuery();
		EmployeeEntity emp = null;
		while (rs.next()) {
			emp = new EmployeeEntity();
			emp.setEmpId(rs.getInt("empid"));
			emp.setEmpname(rs.getString("empname"));
			emp.setPassword(rs.getString("password"));
			emp.setRole(rs.getString("role"));
		}
		return emp;
	}
	public int addEmployee(EmployeeEntity emp) throws Exception {
		openConnection();
		String sql = "insert into t_employee(depid,jobid,empname,cardnumber,sex,education,email,phone,tel,party,qq,address,postcode,birthday,race,speciality,hobby,remark,createtime,password,role,status)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
		int res = pst.executeUpdate();
		return res;
	}
	public ArrayList<EmployeeEntity> findAllEmp() throws Exception {
		openConnection();
		String sql = "select * from t_employee e,t_dep d , t_job j where e.depid=d.depid and e.jobid=j.jobid and status=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, "1");
		ResultSet rs = pst.executeQuery();
		ArrayList<EmployeeEntity> list = new ArrayList<EmployeeEntity>();
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
			list.add(emp);
		}
		return list;
	}
	public EmployeeEntity findEmpDetail(int empid) throws Exception {
		openConnection();
		String sql = "select * from t_employee e,t_dep d , t_job j where e.depid=d.depid and e.jobid=j.jobid and e.empid=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, empid);
		ResultSet rs = pst.executeQuery();
		EmployeeEntity emp = null;
		while (rs.next()) {
			emp = new EmployeeEntity();
			emp.setEmpId(rs.getInt("empid"));
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
	public int getEmpCount() throws Exception {
		openConnection();
		String sql = "select count(*) from t_employee where status=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, "1");
		ResultSet rs = pst.executeQuery();
		int res = 0;
		while (rs.next()) {
			res = rs.getInt(1);
		}
		return res;
	}
	public ArrayList<EmployeeEntity> searchEmp(String jobid, String empname, String cardnumber, String sex,
			String phone, String depid) throws Exception {
		openConnection();
		String sql1 = "", sql2 = "", sql3 = "", sql4 = "", sql5 = "", sql6 = "";
		if (!"0".equals(jobid)) {
			sql1 = " and j.jobid=" + jobid;
		}
		if (empname != null && !"".equals(empname)) {
			sql2 = " and e.empname like '%" + empname + "%'";
		}
		if (cardnumber != null && !"".equals(cardnumber)) {
			sql3 = " and e.cardnumber='" + cardnumber + "'";
		}
		if (!"0".equals(sex)) {
			sql4 = " and e.sex= " + sex;
		}
		if (phone != null && !"".equals(phone)) {
			sql5 = " and e.phone='" + phone + "'";
		}
		if (!"0".equals(depid)) {
			sql6 = " and d.depid=" + depid;
		}
		String sql = "select * from t_employee e,t_dep d , t_job j where e.depid=d.depid and e.jobid=j.jobid and status=?"
				+ sql1 + sql2 + sql3 + sql4 + sql5 + sql6;
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, "1");
		System.out.println(sql);
		ResultSet rs = pst.executeQuery();
		ArrayList<EmployeeEntity> list = new ArrayList<EmployeeEntity>();
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
			list.add(emp); 
		}
		return list;
	}
	public void delEmp(String[] empids, Integer loginEmpid) throws Exception {
		openConnection();
		if(empids==null)return;
		String sql = "update t_employee set status=2 where empid in (";
		if(empids[0]!=null) sql += empids[0];
		for (int i = 1; i < empids.length; i++) {
			sql += ","+empids[i];
		}
		sql+=")";
		System.out.println(sql);
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.executeUpdate();
		closeConnection();
	}
	public void updateEmp(EmployeeEntity emp) throws Exception {
		// TODO Auto-generated method stub
		openConnection();
		
		int empid = emp.getEmpId();
		String empname = emp.getEmpname();
		String cardnumber = emp.getCardunmber();
		String sex = emp.getSex();
		int jobid = emp.getJobId();
		String education = emp.getEducation();
		String email = emp.getEmail();
		String phone = emp.getPhone();
		String tel = emp.getTel();
		String party = emp.getParty();
		String qq = emp.getQq();
		String address = emp.getAddress();
		String postcode = emp.getPostcode();
		Timestamp birthday = emp.getBirthday();
		String race = emp.getRace();
		String speciality = emp.getSpeciality();
		String hobby = emp.getHobby();
		String remark = emp.getRemark();
		int depid = emp.getDepId();
		
		String sql="UPDATE t_employee SET "+
				"empname = ? , cardnumber = ? , sex = ? , jobid = ? , education = ? "+
				"email = ? , phone = ? , tel = ? , party = ? , qq = ? , address = ? "+
				"postcode = ? birthday = ? , race = ? , speciality = ? , hobby = ? "+
				"remark = ? , depid = ? "+
				"WHERE empid = ? ";
		
		PreparedStatement pst = conn.prepareStatement(sql);
		
		pst.setString(1, empname);
		pst.setString(2, cardnumber);
		pst.setString(3, sex);
		pst.setInt(4, jobid);
		pst.setString(5, education);
		pst.setString(6, email);
		pst.setString(7, phone);
		pst.setString(8, tel);
		pst.setString(9, party);
		pst.setString(10, qq);
		pst.setString(11,address);
		pst.setString(12, postcode);
		pst.setTimestamp(13, birthday);
		pst.setString(14, race);
		pst.setString(15, speciality);
		pst.setString(16, hobby);
		pst.setString(17, remark);
		pst.setInt(18, depid);
		pst.setInt(19, empid);
		pst.executeUpdate();
	}
}