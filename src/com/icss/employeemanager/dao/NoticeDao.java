package com.icss.employeemanager.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.icss.employeemanager.entity.NoticeEntity;
import com.mysql.jdbc.PreparedStatement;

public class NoticeDao extends BaseDao {

	public ArrayList<NoticeEntity> findAllNotice(int pageSize, int firstCount) throws Exception {
		// TODO Auto-generated method stub
		// 1.2 打开连接
		openConnection();
		// 3.写sql
		String sql = "select * from t_notice  n,t_employee e where n.empid=e.empid limit ?,?";
		// 4.创建预编译对象
		PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
		pst.setInt(1, firstCount);
		pst.setInt(2, pageSize);
		// 5.执行sql 查询：executeQuery-返回resultset结果集 增删改：executeUpdate--返回受影响行数
		ResultSet rs = pst.executeQuery();
		// 6、如果查询处理resultset结果集 1.选择什么类型 2.next（） 3.getXX
		ArrayList<NoticeEntity> notices = new ArrayList<NoticeEntity>();
		NoticeEntity notice = null;
		while (rs.next()) {
			notice = new NoticeEntity();
			notice.setNoticeid(rs.getInt(1));
			notice.setEmpid(rs.getInt(2));
			notice.setNoticename(rs.getString(3));
			notice.setNoticecontent(rs.getString(4));
			notice.setCreatetime(rs.getTimestamp("createtime"));
			notice.setEmpname(rs.getString("empname"));
			notices.add(notice);
		}
		return notices;
	}

	public int getNoticeCount() throws Exception {
		// TODO Auto-generated method stub
		// 1.2 打开连接
		openConnection();
		// 3.写sql
		String sql = "select count(*) from t_notice";
		// 4.创建预编译对象
		PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
		// 5.执行sql 查询：executeQuery-返回resultset结果集 增删改：executeUpdate--返回受影响行数
		ResultSet rs = pst.executeQuery();
		// 6、如果查询处理resultset结果集 1.选择什么类型 2.next（） 3.getXX
		int count = 0;
		while (rs.next()) {
			count = rs.getInt(1);
		}
		return count;
	}

	public void delNotice(String[] ids) throws Exception {
		// TODO Auto-generated method stub
		// 1.2 打开连接
		openConnection();
		// 3.写sql
		String wherein = " where noticeid in (";
		int i;
		for (i = 0; i < ids.length - 1; i++) {
			wherein += ids[i] + ",";
		}
		wherein += ids[i] + ")";
		String sql = "delete from t_notice" + wherein;
		// 4.创建预编译对象
		PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
		// 5.执行sql语句, 查询：executeQuery-返回resultset结果集 增删改：executeUpdate--返回受影响行数
		pst.executeUpdate();
		// 6、如果查询处理resultset结果集 1.选择什么类型 2.next（） 3.getXX
	}

	// 不分页有条件查询公告信息
	public ArrayList<NoticeEntity> searchNotice(String empname,String noticename, String noticecontent) throws Exception {
		// TODO Auto-generated method stub
		// 1.2 打开连接
		openConnection();
		// 3.写sql
		String sql1 = "", sql2 = "",sql3="";
		// 员工姓名非空判断 noticename like '%?%'
		if (noticename != null && !"".equals(noticename)) {
			sql1 = " and n.noticename like '%" + noticename + "%'";
		}
		if (noticecontent != null && !"".equals(noticecontent)) {
			sql2 = " and n.noticecontent like '%" + noticecontent + "%'";
		}
		if (empname != null && !"".equals(empname)) {
			sql3 = " and e.empname like '%" + empname + "%'";
		}
		String sql = "select * from t_notice n,t_employee e where e.empid=n.empid" + sql1 + sql2+sql3;
		PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
		// System.out.println("sql="+sql);
		ResultSet rs = pst.executeQuery();
		ArrayList<NoticeEntity> notices = new ArrayList<NoticeEntity>();
		NoticeEntity notice = null;
		while (rs.next()) {
			notice = new NoticeEntity();
			notice.setNoticeid(rs.getInt("noticeid"));
			notice.setEmpname(rs.getString("empname"));
			notice.setNoticename(rs.getString("noticename"));
			notice.setNoticecontent(rs.getString("noticecontent"));
			notice.setCreatetime(rs.getTimestamp("createtime"));
			System.out.println("notice="+notice);
			notices.add(notice);
		}
		return notices;
	}

	public int insertNotice(String noticename, String noticecontent, int empid) throws Exception {
		// TODO Auto-generated method stub
		// 1.2 打开连接
		openConnection();
		// 3.写sql
		String sql = "insert into t_notice(noticename,noticecontent,empid) values(?,?,?)";
		// 4.创建预编译对象
		PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
		pst.setString(1, noticename);
		pst.setString(2, noticecontent);
		pst.setInt(3, empid);
		// 5.执行sql语句, 查询：executeQuery-返回resultset结果集 增删改：executeUpdate--返回受影响行数
		int res = pst.executeUpdate();
		// 6、如果查询处理resultset结果集 1.选择什么类型 2.next（） 3.getXX
		return res;
	}

	public void updateNotice(String noticename, String noticecontent, int empid, int noticeid) throws Exception {
		// TODO Auto-generated method stub
		// 1.2 打开连接
		openConnection();
		// 3.写sql
		String sql = "update t_notice n,t_employee e set n.noticename=?,n.noticecontent=? where n.empid=e.empid and n.noticeid=?";
		// 4.创建预编译对象
		PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
		pst.setString(1,noticename);
		pst.setString(2, noticecontent);
		pst.setInt(3, noticeid);
		// 5.执行sql语句, 查询：executeQuery-返回resultset结果集 增删改：executeUpdate--返回受影响行数
		pst.executeUpdate();
		// 6、如果查询处理resultset结果集 1.选择什么类型 2.next（） 3.getXX
	}

	public NoticeEntity findPreviewNotice(String noticeid) throws Exception {
		// TODO Auto-generated method stub
		openConnection();
		String sql="select * from t_notice where noticeid=?";
		PreparedStatement pst=(PreparedStatement) conn.prepareStatement(sql);
		pst.setInt(1,Integer.parseInt(noticeid));
		ResultSet rs=pst.executeQuery();
		NoticeEntity notice=null;
		while(rs.next()) {
			notice=new NoticeEntity();
			notice.setNoticename(rs.getString("noticename"));
			notice.setNoticecontent(rs.getString("noticecontent"));
		}
		return notice;
	}

}
