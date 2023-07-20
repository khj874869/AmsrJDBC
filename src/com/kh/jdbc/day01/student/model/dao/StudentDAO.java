package com.kh.jdbc.day01.student.model.dao;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.jdbc.day01.student.model.vo.Student;

public class StudentDAO {
	
	public List<Student> selectAll() {

		/*
		 * 1. 드라이버 등록
		 * 2. DB 연결 생성
		 * 3. 쿼리 실행 준비
		 * 4. 쿼리 실행 및 5. 결과받기
		 * 6. 자원해제(close())  
		 */
		String driverName= "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "STUDENT";
		String password = "STUDENT";
		String query = "SELECT * FROM STUDENT_TBL";
		List<Student> sList = null;
		Student student = null;
		try {
			//1. 드라이버 등록
			Class.forName(driverName);
			//2. DB연결 생성(Driver Manager)
			Connection conn= 
			DriverManager.getConnection(url, user, password);
			//3.쿼리문 실행준비(Statement 객체 생성)
			Statement stmt = conn.createStatement();
			//4.쿼리문 실행(SELECT면 ResultSet), 5. 결과값 받기
			ResultSet rset = stmt.executeQuery(query);
			//후 처리가 필요하다. = 디비에서 가져온 데이터 사용하기 위함이다.
			sList =new ArrayList<Student>();
			while(rset.next()) {
				student = new Student();
				student.setStudentID(rset.getString("STUDENT_ID"));
				student.setSutdnetPwd(rset.getString("STUDENT_PWD"));
				student.setSutdnetName(rset.getString("STUDENT_NAME"));
				student.setAge(rset.getInt("AGE"));
				student.setEmail(rset.getString("EMAIL"));
				student.setPhone(rset.getString("PHONE"));
				//문자는 문자열에서 문자로 잘라서 사용 한다.
				student.setGender(rset.getString("GENDER").charAt(0));
				student.setAddress(rset.getString("ADDRESS"));
				student.setHobby(rset.getString("HOBBY"));
				student.setErollDate(rset.getDate("ENROLL_DATE"));
				sList.add(student);
			}
		
			//6. 자원 해제
			rset.close();
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sList;
	}


}
