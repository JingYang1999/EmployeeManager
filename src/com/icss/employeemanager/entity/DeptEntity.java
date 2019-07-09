package com.icss.employeemanager.entity;

/**
 * 部门实体类
 * 
 * @author Giselle
 */
public class DeptEntity {
	private int depid;
	private String depname;
	private String depdetail;

	public int getDepid() {
		return depid;
	}

	public String getDepname() {
		return depname;
	}

	public String getDepdetail() {
		return depdetail;
	}

	public void setDepid(int depid) {
		this.depid = depid;
	}

	public void setDepname(String depname) {
		this.depname = depname;
	}

	public void setDepdetail(String depdetail) {
		this.depdetail = depdetail;
	}

	@Override
	public String toString() {
		return "DeptEntity [depid=" + depid + ", depname=" + depname + ", depdetail=" + depdetail + "]";
	}

}
