package com.icss.employeemanager.entity;

import java.sql.Timestamp;

public class ApplyEntity {
	@Override
	public String toString() {
		return "ApplyEntity [empid=" + empid + ", applyid=" + applyid + ", approverid=" + approverid + ", applyreason="
				+ applyreason + ", applytype=" + applytype + ", applystatus=" + applystatus + ", remark=" + remark
				+ ", refusereason=" + refusereason + ", empname=" + empname + ", approvername=" + approvername
				+ ", applytime=" + applytime + ", approvertime=" + approvertime + ", starttime=" + starttime
				+ ", endtime=" + endtime + "]";
	}
	public int getEmpid() {
		return empid;
	}
	public void setEmpid(int empid) {
		this.empid = empid;
	}
	public int getApplyid() {
		return applyid;
	}
	public void setApplyid(int applyid) {
		this.applyid = applyid;
	}
	public int getApproverid() {
		return approverid;
	}
	public void setApproverid(int approverid) {
		this.approverid = approverid;
	}
	public String getApplyreason() {
		return applyreason;
	}
	public void setApplyreason(String applyreason) {
		this.applyreason = applyreason;
	}
	public String getApplytype() {
		return applytype;
	}
	public void setApplytype(String applytype) {
		this.applytype = applytype;
	}
	public String getApplystatus() {
		return applystatus;
	}
	public void setApplystatus(String applystatus) {
		this.applystatus = applystatus;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRefusereason() {
		return refusereason;
	}
	public void setRefusereason(String refusereason) {
		this.refusereason = refusereason;
	}
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	public String getApprovername() {
		return approvername;
	}
	public void setApprovername(String approvername) {
		this.approvername = approvername;
	}
	public Timestamp getApplytime() {
		return applytime;
	}
	public void setApplytime(Timestamp applytime) {
		this.applytime = applytime;
	}
	public Timestamp getApprovertime() {
		return approvertime;
	}
	public void setApprovertime(Timestamp approvertime) {
		this.approvertime = approvertime;
	}
	public Timestamp getStarttime() {
		return starttime;
	}
	public void setStarttime(Timestamp starttime) {
		this.starttime = starttime;
	}
	public Timestamp getEndtime() {
		return endtime;
	}
	public void setEndtime(Timestamp endtime) {
		this.endtime = endtime;
	}
	int empid, applyid,approverid;
	String applyreason, applytype, applystatus, remark, refusereason,empname,approvername;
	Timestamp applytime, approvertime, starttime, endtime;
}
