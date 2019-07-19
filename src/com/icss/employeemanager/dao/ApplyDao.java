package com.icss.employeemanager.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.icss.employeemanager.entity.ApplyEntity;

public class ApplyDao extends BaseDao {

	public ArrayList<ApplyEntity> findAllApply(int pagesize, int firstCount, String findapplytype,
			String findapplystatus, int empid) throws Exception {
		// TODO Auto-generated method stub
		openConnection();
		String sql = "select * from t_apply a left join t_employee e1 on a.empid = e1.empid left join t_employee e2 on e2.empid = a.approverid  where applyreason like '%%' ";

		if (findapplytype.equals("all")) {
			sql += " ";
		} else if (findapplytype.equals("请假") || findapplytype.equals("外出") || findapplytype.equals("加班")) {
			sql += " and applytype = '" + findapplytype + "'";
		}

		if (findapplystatus.equals("all")) {
			sql += " ";
		} else if (findapplystatus.equals("待审核") || findapplystatus.equals("已批准") || findapplystatus.equals("已拒绝")) {
			sql += " and applystatus = '" + findapplystatus + "'";
		}
		if (empid != -1) {
			sql += " and empid = ? ";
		}
		sql += " limit ? , ?";
		PreparedStatement pst = conn.prepareStatement(sql);
		System.out.println("id=" + empid);
		if (empid != -1) {
			pst.setInt(1, empid);
			pst.setInt(2, firstCount);
			pst.setInt(3, pagesize);
		} else {
			pst.setInt(1, firstCount);
			pst.setInt(2, pagesize);
		}
		System.out.println("sql=" + pst);

		ResultSet rs = pst.executeQuery();

		ArrayList<ApplyEntity> apps = new ArrayList<ApplyEntity>();
		ApplyEntity app = null;
		while (rs.next()) {
			app = new ApplyEntity();

			app.setEmpid(rs.getInt("empid"));
			app.setApplyid(rs.getInt("applyid"));
			app.setApplyreason(rs.getString("applyreason"));
			app.setApplytype(rs.getString("applytype"));
			app.setApplystatus(rs.getString("applystatus"));
			app.setApplytime(rs.getTimestamp("applytime"));
			app.setRemark(rs.getString("remark"));
			app.setApproverid(rs.getInt("approverid"));
			app.setApprovertime(rs.getTimestamp("approvertime"));
			app.setApplyreason(rs.getString("applyreason"));
			app.setStarttime(rs.getTimestamp("starttime"));
			app.setEndtime(rs.getTimestamp("endtime"));

			app.setEmpname(rs.getString("e1.empname"));
			app.setApprovername(rs.getString("e2.empname"));

			apps.add(app);
		}
		return apps;
	}

	public int getApplyCount(String findapplytype, String findapplystatus, int empid) throws Exception {
		// TODO Auto-generated method stub
		openConnection();
		String sql = "select count(*) from t_apply where applytype = " + findapplytype + " ";

		if (findapplytype.equals("all")) {
			sql += " ";
		} else if (findapplytype.equals("请假") || findapplytype.equals("外出") || findapplytype.equals("加班")) {
			sql += " and applytype = " + findapplytype;
		}

		if (findapplystatus.equals("all")) {
			sql += " ";
		} else if (findapplystatus.equals("待审核") || findapplystatus.equals("已批准") || findapplystatus.equals("已拒绝")) {
			sql += "and applystatus = " + findapplystatus;
		}
		if (empid != -1) {
			sql += " and empid = ? ";
		}
		PreparedStatement pst = conn.prepareStatement(sql);
		if (empid != -1) {
			pst.setInt(1, empid);
		}
		ResultSet rs = pst.executeQuery();
		int count = 0;
		while (rs.next()) {
			count = rs.getInt(1);
		}
		return count;
	}

	public Boolean approveApply(String approve_flag, int apply_id, int approve_id) throws Exception {
		// TODO Auto-generated method stub
		openConnection();
		String sql = "update t_apply set applystatus = ? , approverid = ? , approvertime = ? where applyid = ? ";
		String status = null;
		if (approve_flag.equals("true")) {
			status = "已批准";
		} else if (approve_flag.equals("false")) {
			status = "已拒绝";
		} else {
			return false;
		}
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, status);
		pst.setInt(2, approve_id);
		pst.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
		pst.setInt(4, apply_id);
System.out.println(pst);
		pst.executeUpdate();
		return true;
	}

	public Boolean addApply(String applyreason, Timestamp starttime, Timestamp endtime, String remark, String applytype,
			int empid) throws Exception {
		// TODO Auto-generated method stub
		openConnection();
		String sql = "insert into t_apply(applyreason,starttime,endtime,remark,applytype,empid,applystatus,applytime) values(?,?,?,?,?,?,?,?) ";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, applyreason);
		pst.setTimestamp(2, starttime);
		pst.setTimestamp(3, endtime);
		pst.setString(4, remark);
		pst.setString(5, applytype);
		pst.setInt(6, empid);
		pst.setString(7, "待审核");
		pst.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
		System.out.println(pst);
		pst.executeUpdate();
		return true;
	}
}
