package com.icss.employeemanager.entity;

import java.sql.Timestamp;

public class NoticeEntity {
	private int noticeid, empid;
	private String empname,noticename, noticecontent;
	private Timestamp createtime;

	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public int getNoticeid() {
		return noticeid;
	}

	public void setNoticeid(int noticeid) {
		this.noticeid = noticeid;
	}

	public int getEmpid() {
		return empid;
	}

	public void setEmpid(int empid) {
		this.empid = empid;
	}

	public String getNoticename() {
		return noticename;
	}

	public void setNoticename(String noticename) {
		this.noticename = noticename;
	}

	public String getNoticecontent() {
		return noticecontent;
	}

	public void setNoticecontent(String noticecontent) {
		this.noticecontent = noticecontent;
	}

	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	public NoticeEntity() {}
	public NoticeEntity(int noticeid, int empid, String empname, String noticename, String noticecontent,
			Timestamp createtime) {
		super();
		this.noticeid = noticeid;
		this.empid = empid;
		this.empname = empname;
		this.noticename = noticename;
		this.noticecontent = noticecontent;
		this.createtime = createtime;
	}

	@Override
	public String toString() {
		return "NoticeEntity [noticeid=" + noticeid + ", empid=" + empid + ", empname=" + empname + ", noticename="
				+ noticename + ", noticecontent=" + noticecontent + ", createtime=" + createtime + "]";
	}

	

}
