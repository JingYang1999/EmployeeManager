package com.icss.employeemanager.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import com.icss.employeemanager.entity.EmployeeEntity;

/**
 * 数据层：操作数据库，所有员工相关的操作
 * 
 * @author Giselle
 */
public class EmployeeDao extends BaseDao {
	// 登录
	/*
	 * 方法两个要素
	 * 1、方法有没有返回值：有--就写返回值类型   没有--void
	 * 2、有没有参数：有--有哪些参数就写形参   没有--（）
	 * 
	 */
	public EmployeeEntity login(String empname, String password) throws Exception {
		//1.2 打开连接
		openConnection();
		//3.写sql语句
		String sql = "select * from t_employee where empname=? and password=? and status=?";
		//4.创建预编译对象
		PreparedStatement pst = conn.prepareStatement(sql);
		//如果有？就拼参数；如果没有，就可以执行sql了
		pst.setString(1, empname);  //setXX（?的顺序,变量--要拼在sql语句里面的值） xx的类行和变量要一致
		pst.setString(2, password);
		pst.setString(3, "1");
		
		//5.选择执行sql语句的方法   增删改：executeupdate返回的是受影响的行数   查询： executeQuery，返回一个结果集  
		ResultSet rs = pst.executeQuery();
		
		//6.如果是查询就需要处理结果集 （1）选择容器 （2）遍历 next()：移动到下一行
		EmployeeEntity emp = null;
		while(rs.next()){
		   emp = new EmployeeEntity();
		   emp.setEmpId(rs.getInt("empid"));//getXX(索引--表里字段的顺序/表里的字段名)获得在数据库里的值：1.xx的类型和数据库里此字段的类型一致
		   emp.setEmpname(rs.getString("empname"));
		   emp.setPassword(rs.getString("password"));
		   emp.setRole(rs.getString("role"));
				   
		}
		return emp;
	}
	
	// 添加员工
	public int addEmployee(EmployeeEntity emp) throws Exception{
		//1.2 打开连接
		openConnection();
		//3.写sql
		String sql = "insert into t_employee(depid,jobid,empname,cardnumber,sex,education,email,phone,tel,party,qq,address,postcode,birthday,race,speciality,hobby,remark,createtime,password,role,status)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		//4.创建预编译对象
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
		pst.setTimestamp(19,new Timestamp(new Date().getTime()));
		pst.setString(20, emp.getPassword());
		pst.setString(21, emp.getRole());
		pst.setString(22, emp.getStatus());
		//5.执行sql语句   增删改：executeupdate--返回int类型   查询：executequery--返回resultset
		int res =  pst.executeUpdate();
		//6.需要处理结果集吗 ？ 不需要  只有查询需要处理结果集
		return res;
	} 
	
	// 查看所有员工
	public ArrayList<EmployeeEntity> findAllEmp() throws Exception{
		//1.2打开连接
		openConnection();
		//3.写sql
		String sql = "select * from t_employee e,t_dep d , t_job j where e.depid=d.depid and e.jobid=j.jobid and status=?";
		//4.创建预编译对象
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, "1");
		//5.执行sql  executequery
		ResultSet rs = pst.executeQuery();
		//6.处理结果集  1.选择用什么来接收参数  next  getXX
		ArrayList<EmployeeEntity> list = new ArrayList<EmployeeEntity>();//创建集合对象
		EmployeeEntity emp = null;
		while(rs.next()){ //遍历
			emp = new EmployeeEntity();
			emp.setEmpId(rs.getInt("empid"));//getXX（索引--表里字段的顺序或者数据表里的字段名获得在数据库里的值：1.）
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
			list.add(emp);  //把创建好的对象存放到集合中
		}
		return list;
		
	}
	
	// 查看员工详情(根据员工id查看员工详情)
	public EmployeeEntity findEmpDetail(int empid) throws Exception {
		// 1.2打开连接
		openConnection();
		// 3.写sql
		String sql = "select * from t_employee e,t_dep d , t_job j where e.depid=d.depid and e.jobid=j.jobid and e.empid=?";
		// 4.创建预编译对象
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1,empid);
		// 5.执行sql executequery
		ResultSet rs = pst.executeQuery();
		// 6.处理结果集 1.选择用什么来接收参数 next getXX
		EmployeeEntity emp=null;
		while(rs.next()){
			emp = new EmployeeEntity();
			emp.setEmpId(rs.getInt("empid"));//getXX（索引--表里字段的顺序或者数据表里的字段名获得在数据库里的值：1.）
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
	
	// 获取员工数量
	public int getEmpCount() throws Exception {
		// 1.2打开连接
		openConnection();
		// 3.写sql
		String sql = "select count(*) from t_employee where status=?";
		// 4.创建预编译对象
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, "1");
		// 5.执行sql executequery
		ResultSet rs = pst.executeQuery();
		// 6.处理结果集 1.选择用什么来接收参数 next getXX
		int res=0;
		while(rs.next()){
			res=rs.getInt(1);
		}
		return res;
	}
}
