package com.icss.employeemanager.entity;

public class JobEntity {
	private int jobid;
	private String jobname, jobdetail;

	/**
	 * @return the jobid
	 */
	public int getJobid() {
		return jobid;
	}

	/**
	 * @param jobid the jobid to set
	 */
	public void setJobid(int jobid) {
		this.jobid = jobid;
	}

	/**
	 * @return the jobname
	 */
	public String getJobname() {
		return jobname;
	}

	/**
	 * @param jobname the jobname to set
	 */
	public void setJobname(String jobname) {
		this.jobname = jobname;
	}

	/**
	 * @return the jobdetail
	 */
	public String getJobdetail() {
		return jobdetail;
	}

	/**
	 * @param jobdetail the jobdetail to set
	 */
	public void setJobdetail(String jobdetail) {
		this.jobdetail = jobdetail;
	}

	@Override
	public String toString() {
		return "JobEntity [jobid=" + jobid + ", jobname=" + jobname + ", jobdetail=" + jobdetail + ", getJobid()="
				+ getJobid() + ", getJobname()=" + getJobname() + ", getJobdetail()=" + getJobdetail() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
}
