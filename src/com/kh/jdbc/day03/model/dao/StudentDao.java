package com.kh.jdbc.day03.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.kh.jdbc.day03.model.vo.StudentVo;

public class StudentDao {

	private final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private final String USER = "STUDENT";
	private final String PASSWORD = "STUDENT";
	public StudentVo login(StudentVo student) {
			String query = "SELECT * FROM STUDENT_TBL WHERE STUDENT_ID =? AND STUDENT_PWD = ?";
			StudentVo result = null;
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rset =null;
			try {
				Class.forName(DRIVER_NAME);
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
				pstmt = conn.prepareStatement(query);
	//			ResultSet rset = pstmt.executeQuery(query);
				pstmt.setString(1, student.getStudentId());
				pstmt.setString(2, student.getStudentPwd()); //시작은 1로 하고 마지막 수는 물음표의 갯수와 같다.(물음표 = 위치홀더)
				rset = pstmt.executeQuery();
				// 후처리
				if(rset.next()) {
					result = rsetToStudent(rset);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					rset.close();
					pstmt.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			return result;
		}
	public int insertStudent(StudentVo student) {
			String query = "INSERT INTO STUDENT_TBL VALUES(?,?,?,?,?,?,?,?,?,SYSDATE)";
			Connection conn =null;
			PreparedStatement pstmt =null;
			int result = -1;
			try {
				Class.forName(DRIVER_NAME);
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, student.getStudentId());
				pstmt.setString(2, student.getStudentPwd());
				pstmt.setString(3, student.getStudentName());
				pstmt.setString(4, String.valueOf(student.getGender()));
				pstmt.setInt(5, student.getAge());
				pstmt.setString(6, student.getEmail());
				pstmt.setString(7, student.getPhone());
				pstmt.setString(8, student.getAddress());
				pstmt.setString(9, student.getHobby());
				result = pstmt.executeUpdate();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					pstmt.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			return result;
	}
	public List<StudentVo> selectAll() {
		/*
		 * 1. 드라이버 등록
		 * 2. DB 연결 생성
		 * 3. 쿼리문 실행 준비
		 * 4. 쿼리문 실행 및 5. 결과 받기
		 * 6. 자원해제(close())
		 */
		String query = "SELECT * FROM STUDENT_TBL";
		List<StudentVo> sList = null;
		StudentVo student = null;
		Connection conn=null;
		Statement stmt =null;
		ResultSet rset = null;
		try {
			// 1. 드라이버 등록
			Class.forName(DRIVER_NAME);
			// 2. DB 연결 생성(DriverManger)
			 conn = 
					DriverManager.getConnection(URL, USER, PASSWORD);
			// 3.쿼리문 실행 준비
			 stmt = 
					conn.createStatement();
			// 4. 쿼리문 실행 및 5. 결과 받기
			 rset = 
					stmt.executeQuery(query);
			sList = new ArrayList<StudentVo>();
			// 후처리
			while(rset.next()) {
				student = rsetToStudent(rset);
				sList.add(student);
			}
			// 6. 자원해제
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return sList;
	}
public List<StudentVo> selectAllByNames(String studentName) {
		String query = "SELECT * FROM STUDENT_TBL WHERE STUDENT_NAME =?"; //위치 홀더
		List<StudentVo> sList = new ArrayList<StudentVo>();
		Connection conn =null;
		PreparedStatement pstmt=null;
		ResultSet rset =null;
		
		StudentVo student = null;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,studentName);
			rset = pstmt.executeQuery();
			// 후처리
			while(rset.next()) {
				student = rsetToStudent(rset);
				sList.add(student);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rset.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return sList;
	}
public StudentVo findByoneId(String studentId) {
	// SELECT * FROM STUDENT_TBL WHERE STUDENT_ID = 'khuser01'
String query = "SELECT * FROM STUDENT_TBL WHERE STUDENT_ID ='"+studentId +"'";
StudentVo student = null;
Connection conn =null;
Statement stmt= null;
ResultSet rset =null;
try {
Class.forName(DRIVER_NAME);
 conn = DriverManager.getConnection(URL, USER, PASSWORD);
 stmt = conn.createStatement();
 rset = stmt.executeQuery(query);
//while(rset.next()) {}
if(rset.next()) {
student = rsetToStudent(rset);
}
} catch (ClassNotFoundException e) {
e.printStackTrace();
} catch (SQLException e) {
e.printStackTrace();
}finally {
	try {
		rset.close();
		stmt.close();
		conn.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
return student;
}
public int updateStudent(StudentVo student) {
	String query = "Update STUDENT_TBL set STUDENT_PWD = ?,EMAIL = ? ,PHONE = ? ,ADDRESS = ?,HOBBY = ? WHERE STUDENT_ID = ?";
	int result = -1;
	Connection conn =null;
	PreparedStatement pstmt =null;
	try {
		Class.forName(DRIVER_NAME);
		conn = DriverManager.getConnection(URL, USER, PASSWORD);
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, student.getStudentPwd());
		pstmt.setString(2, student.getEmail());
		pstmt.setString(3, student.getPhone());
		pstmt.setString(4, student.getAddress());
		pstmt.setString(5, student.getHobby());
		pstmt.setString(6, student.getStudentId());
		result = pstmt.executeUpdate();
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		try {
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	return result;		
}
	public int deleteStudent(String studentId) {
		StudentVo student = new StudentVo();
		String query = "DELETE  FROM STUDENT_TBL WHERE STUDENT_ID  = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = -1;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, studentId);
			result = pstmt.executeUpdate();
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	private StudentVo rsetToStudent(ResultSet rset) throws SQLException {
		StudentVo student = new StudentVo();
		student.setStudentId(rset.getString("STUDENT_ID"));
		student.setStudentPwd(rset.getString("STUDENT_PWD"));
		student.setStudentName(rset.getString("STUDENT_NAME"));
		student.setAge(rset.getInt("AGE"));
		student.setEmail(rset.getString("EMAIL"));
		student.setPhone(rset.getString("PHONE"));
		// 문자는 문자열에 문자로 잘라서 사용, charAt() 메소드 사용
		student.setGender(rset.getString("GENDER").charAt(0));
		student.setAddress(rset.getString("ADDRESS"));
		student.setHobby(rset.getString("HOBBY"));
		student.setEnrollDate(rset.getDate("ENROLL_DATE"));
		return student;
	}
	

}
