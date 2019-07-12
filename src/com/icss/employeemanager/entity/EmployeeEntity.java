package com.icss.employeemanager.entity;

import java.sql.Timestamp;

/**
 * Ա��ʵ����--��װ�ԣ�����˽�л� �������л�
 * 
 * @author Giselle
 */
public class EmployeeEntity {

	private int empId, depId, jobId;
	private String empname, cardunmber, address, postcode, tel, phone, qq, email, party, depname, jobname;
	private Timestamp birthday, createtime;
	private String race, education, speciality, hobby, remark;
	private String password,faceurl,facepath;
	private String sex, role, status;

	public EmployeeEntity() {
	}

	public EmployeeEntity(int depId, int jobId, String empname, String cardunmber, String address, String postcode,
			String tel, String phone, String qq, String email, String party, Timestamp birthday, Timestamp createTime,
			String race, String education, String speciality, String hobby, String remark, String password,
			String faceUrl, String facePath, String sex, String role, String status) {
		super();
		this.depId = depId;
		this.jobId = jobId;
		this.empname = empname;
		this.cardunmber = cardunmber;
		this.address = address;
		this.postcode = postcode;
		this.tel = tel;
		this.phone = phone;
		this.qq = qq;
		this.email = email;
		this.party = party;
		this.birthday = birthday;
		this.createtime = createTime;
		this.race = race;
		this.education = education;
		this.speciality = speciality;
		this.hobby = hobby;
		this.remark = remark;
		this.password = password;
		this.faceurl = faceUrl;
		this.facepath = facePath;
		this.sex = sex;
		this.role = role;
		this.status = status;
	}

	public int getEmpId() {
		return empId;
	}

	public int getDepId() {
		return depId;
	}

	public int getJobId() {
		return jobId;
	}

	public String getEmpname() {
		return empname;
	}

	public String getCardunmber() {
		return cardunmber;
	}

	public String getAddress() {
		return address;
	}

	public String getPostcode() {
		return postcode;
	}

	public String getTel() {
		return tel;
	}

	public String getPhone() {
		return phone;
	}

	public String getQq() {
		return qq;
	}

	public String getEmail() {
		return email;
	}

	public String getParty() {
		return party;
	}

	public Timestamp getBirthday() {
		return birthday;
	}

	public Timestamp getCreateTime() {
		return createtime;
	}

	public String getRace() {
		return race;
	}

	public String getEducation() {
		return education;
	}

	public String getSpeciality() {
		return speciality;
	}

	public String getHobby() {
		return hobby;
	}

	public String getRemark() {
		return remark;
	}

	public String getPassword() {
		return password;
	}

	public String getFaceUrl() {
		return faceurl;
	}

	public String getFacePath() {
		return facepath;
	}

	public String getSex() {
		return sex;
	}

	public String getRole() {
		return role;
	}

	public String getStatus() {
		return status;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public void setDepId(int depId) {
		this.depId = depId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public void setCardunmber(String cardunmber) {
		this.cardunmber = cardunmber;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public void setBirthday(Timestamp birthday) {
		this.birthday = birthday;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createtime = createTime;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setFaceUrl(String faceUrl) {
		this.faceurl = faceUrl;
	}

	public void setFacePath(String facePath) {
		this.facepath = facePath;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDepname() {
		return depname;
	}

	public void setDepname(String depname) {
		this.depname = depname;
	}

	public String getJobname() {
		return jobname;
	}

	public void setJobname(String jobname) {
		this.jobname = jobname;
	}

	@Override
	public String toString() {
		return "EmployeeEntity [empId=" + empId + ", depId=" + depId + ", jobId=" + jobId + ", empname=" + empname
				+ ", cardunmber=" + cardunmber + ", address=" + address + ", postcode=" + postcode + ", tel=" + tel
				+ ", phone=" + phone + ", qq=" + qq + ", email=" + email + ", party=" + party + ", depname=" + depname
				+ ", jobname=" + jobname + ", birthday=" + birthday + ", createTime=" + createtime + ", race=" + race
				+ ", education=" + education + ", speciality=" + speciality + ", hobby=" + hobby + ", remark=" + remark
				+ ", password=" + password + ", faceUrl=" + faceurl + ", facePath=" + facepath + ", sex=" + sex
				+ ", role=" + role + ", status=" + status + "]";
	}

}
