package com.icss.employeemanager.entity;

import java.sql.Timestamp;

/**
 * ��Դ����ʵ����
 * 
 * @author Giselle
 */
public class DocEntity {
	private int documentid;
	private int empid;
	private String empname;
	private String title;
	private String filepath;
	public DocEntity(int empid, String title, String filepath, String remark) {
		super();
		this.empid = empid;
		this.title = title;
		this.filepath = filepath;
		this.remark = remark;
	}



	private String remark;
	private Timestamp createtime;

	public int getDocumentid() {
		return documentid;
	}

	public int getEmpid() {
		return empid;
	}

	public String getTitle() {
		return title;
	}

	public String getFilepath() {
		return filepath;
	}

	public String getRemark() {
		return remark;
	}

	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setDocumentid(int documentid) {
		this.documentid = documentid;
	}

	public void setEmpid(int empid) {
		this.empid = empid;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	@Override
	public String toString() {
		return "DocumentEntity [documentid=" + documentid + ", empid=" + empid + ", empname=" + empname + ", title="
				+ title + ", filepath=" + filepath + ", remark=" + remark + ", createtime=" + createtime + "]";
	}

}
