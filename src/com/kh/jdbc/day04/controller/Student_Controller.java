package com.kh.jdbc.day04.controller;

import java.sql.Date;
import java.util.List;

import com.kh.jdbc.day04.model.dao.Student_DAO;
import com.kh.jdbc.day04.model.service.StudentService;
import com.kh.jdbc.day04.model.vo.StudentVo;

public class Student_Controller {
	private Student_DAO sDao;
	private StudentService sservice;
	public Student_Controller() {
		sDao = new Student_DAO();
	}
	
	public List<StudentVo> selectAll() {
		List<StudentVo>sList = sservice.selectAll();
		return sList;
	}

	public StudentVo selectOneById(String studentId) {
		StudentVo student = sservice.selectOneById(studentId);
		return student;
	}

	public List<StudentVo> inputStudentName(String stdname) {
		List<StudentVo> sList = sservice.inputStudentName(stdname);
		
		
		return sList;
		
		

	}

	public int inputStudent(StudentVo student) {
		int result = sservice.inputStudent(student); 
		return result;
	}

	public int Updateinfo(StudentVo student) {
		int result =  sservice.Updateinfo(student);
		return result ;
	}

	public int delete(String id) {
		int result = sservice.delete(id);
		return result;
	}
}
