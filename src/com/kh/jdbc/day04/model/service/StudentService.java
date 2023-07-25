package com.kh.jdbc.day04.model.service;

import java.sql.*;
import java.util.List;

import com.kh.jdbc.day04.common.JDBCTemplate;
import com.kh.jdbc.day04.model.dao.Student_DAO;
import com.kh.jdbc.day04.model.vo.StudentVo;

public class StudentService {
	private Student_DAO sDao;
	private JDBCTemplate jdbctemplate;
	public StudentService() {
		sDao = new Student_DAO();
//		jdbctemplate= new JDBCTemplate(); //생성자가 private이기 때문에 사용못한다
		jdbctemplate= JDBCTemplate.getinstance();
	}
	public List<StudentVo> selectAll() {
		Connection conn =
				jdbctemplate.createConnection();
		List<StudentVo> sList 
		= sDao.selectAll();
		jdbctemplate.close();

		return sList;
	}
	public StudentVo selectOneById(String studentId) {
		Connection conn =
				jdbctemplate.createConnection();
		StudentVo student =
				sDao.selectOneById(studentId);
		jdbctemplate.close();

		return student;
	}
	public List<StudentVo> inputStudentName(String stdname) {
		Connection conn =
				jdbctemplate.createConnection();
		List<StudentVo>sList = sDao.inputStudentName(stdname);
		jdbctemplate.close();
		return sList;
	}
	public int inputStudent(StudentVo student) {
		Connection conn =
				jdbctemplate.createConnection();
		int result =sDao.inputStudent(student);
		result +=sDao.inputStudent(student);
		if(result>1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		jdbctemplate.close();

		return result;
	}
	public int Updateinfo(StudentVo student) {
		Connection conn =
				jdbctemplate.createConnection();
		int result = sDao.Updateinfo(student);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		jdbctemplate.close();
		return result;
	}
	public int delete(String id) {
		Connection conn =
				jdbctemplate.createConnection();
		int result = 
				sDao.delete(id);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		jdbctemplate.close();
		return result;
	}
}
