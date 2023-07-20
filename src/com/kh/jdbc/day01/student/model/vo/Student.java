package com.kh.jdbc.day01.student.model.vo;

import java.sql.Date;

public class Student {
	private String studentID;
	private String sutdnetPwd;
	private String sutdnetName;
	private char gender;
	private int age;
	private String email;
	private String phone; 
	private String address;
	private String hobby;
	private Date erollDate;
	@Override
	public String toString() {
		return "Student [학생ID=" + studentID + ", 학생 PWD=" + sutdnetPwd + ", 학생이름=" + sutdnetName
				+ ", 성별=" + gender + ", 나이=" + age + ", 이메일=" + email + ", 전화번호=" + phone + ", 주소="
				+ address + ", 취미=" + hobby + ", 등록일=" + erollDate + "]";
	}
	public String getStudentID() {
		return studentID;
	}
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	public String getSutdnetPwd() {
		return sutdnetPwd;
	}
	public void setSutdnetPwd(String sutdnetPwd) {
		this.sutdnetPwd = sutdnetPwd;
	}
	public String getSutdnetName() {
		return sutdnetName;
	}
	public void setSutdnetName(String sutdnetName) {
		this.sutdnetName = sutdnetName;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public Date getErollDate() {
		return erollDate;
	}
	public void setErollDate(Date erollDate) {
		this.erollDate = erollDate;
	}

	
}
