package com.kh.jdbc.day03.controller;

import java.sql.SQLException;
import java.util.List;

import com.kh.jdbc.day03.model.dao.StudentDao;
import com.kh.jdbc.day03.model.vo.StudentVo;

public class StudentController {
	private StudentDao dao;
 public	StudentController(){
		dao = new StudentDao();
	}
	public StudentVo login(StudentVo student) {
	
	StudentVo result = dao.login(student);
	return result;
}
	public int insertStudent(StudentVo student) {
		int result = dao.insertStudent(student);
		return result;
	}
	public StudentVo printStudentById(String studentId) {
		StudentVo sv = new StudentVo();
		sv = dao.findByoneId(studentId);
		return sv;
	}
	public List<StudentVo> selectAllStudent() {
		List<StudentVo>slist = dao.selectAll();
		return slist;
	}
	public List<StudentVo> selectAllByName(String studentName) {
		List<StudentVo>sList = dao.selectAllByNames(studentName);
		
		return sList;
	}
	public int updateStudent(StudentVo student) {
		int result = dao.updateStudent(student);
		return result;
	}
	public int deleteStudent(String studentId) {
		int result = dao.deleteStudent(studentId);
		return result;
	}
}
