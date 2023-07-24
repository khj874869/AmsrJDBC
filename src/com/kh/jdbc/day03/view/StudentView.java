package com.kh.jdbc.day03.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.kh.jdbc.day01.student.model.vo.Student;
import com.kh.jdbc.day03.controller.StudentController;
import com.kh.jdbc.day03.model.vo.StudentVo;

public class StudentView {
	private StudentController controller;
	public StudentView() {
		controller = new StudentController();
	}
	public void studentProgram() {
		StudentVo student = null;
		List<StudentVo> sList = null;
		finish:
		while(true) {
			int input = printMenu();
			switch(input) {
			case 1 : 
				sList= controller.selectAllStudent();
				if(!sList.isEmpty()) {
					showAllStudents(sList);
				}else {
					displayError("학생 정보가 조회되지 않습니다.");
				}
				break;				
			case 2 : 
				String studentId = inputStudentId("찾기");
				student = controller.printStudentById(studentId);
				if(student != null) {
					showStudent(student);
				}else {
					displayError("학생 정보가 존재하지 않습니다.");
				}
				// printStudentById() 메소드가 학생 정보를 조회, dao의 메소드는 selectOneById()로 명명
				// showStudent() 메소드로 학생 정보를 출력
				break;				
			case 3 : 
				//Select * from STUDENT_TBL WHERE STUDENT_NAME = '일용자'; 
				String studentName = inputName();
				sList = controller.selectAllByName(studentName);
				showAllStudents(sList);
				break;
			case 4 : 
				student = inputStudent("넣기");
				 int result=controller.insertStudent(student);
				 if(result>0) {
					 displaySucces("학생 정보 등록 성공");
				 }else {
					 displayError("학생 정보 등록 실패");
				 }
				 break;
			case 5 : 
				studentId = inputStudentId("수정");
				student = controller.printStudentById(studentId);
				if(student != null) {
					student= modifyStudent();
					student.setStudentId(studentId);
					result = controller.updateStudent(student);
				}
				
				break;
			case 6 : 
				studentId = inputStudentId("삭제");
				result = controller.deleteStudent(studentId);
				if(result>0) {
					displaySucces("학생 정보 삭제 성공");
				}else {
					displayError("학생 정보 삭제 실패");
				}
				break ;
			case 9 : 
				student = inputloginInfo();
				controller.login(student);
				if(student != null) {
					displaySucces("로그인 성공");
				}else {
					displayError("회원 가입 해주세요.");
				}
				break;
			case 0 : 
				break finish;
			
			}
		}
	}

	private int printMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("===== 학생관리 프로그램 =====");
		System.out.println("9.학생 로그인");
		System.out.println("1. 학생 전체 조회");
		System.out.println("2. 학생 아이디로 조회");
		System.out.println("3. 학생 이름으로 조회");
		System.out.println("4. 학생 정보 등록");
		System.out.println("5. 학생 정보 수정");
		System.out.println("6. 학생 정보 삭제");
		System.out.println("0. 프로그램 종료");
		System.out.print("메뉴 선택 : ");
		int input = sc.nextInt();
		return input;
	}
	private StudentVo inputloginInfo() {
		Scanner sc = new Scanner(System.in);
		System.out.println("===== 학생 로그인 =====");
		System.out.print("ID : ");
		String studentId = sc.nextLine();
		System.out.print("PW :");
		String studentPwd = sc.nextLine();
		StudentVo student =new StudentVo(studentId,studentPwd);
		return student;
	}
	private String inputName() {
		Scanner sc = new Scanner(System.in);
		System.out.println("===== 학생 이름 조회 =====");
		System.out.print("학생 이름 입력 : ");
		String studentName = sc.next();
		return studentName;		
	}
	private void showStudent(StudentVo student) {
		System.out.println("===== 학생 정보 출력(아이디로 조회) =====");
		System.out.printf("이름 : %s, 나이 : %d, 아이디 : %s"
				+ ", 성별 : %s, 이메일 : %s, 전화번호 : %s, 주소 : %s"
				+ ", 취미 : %s, 가입날짜 : %s\n"
				, student.getStudentName()
				, student.getAge()
				, student.getStudentId()
				, student.getGender()
				, student.getEmail()
				, student.getPhone()
				, student.getAddress()
				, student.getHobby()
				, student.getEnrollDate());
		
	}
	private void showAllStudents(List<StudentVo> sList) {
			System.out.println("===== 학생 전체 정보 출력 =====");
			for(StudentVo student : sList) {
				System.out.printf("이름 : %s, 나이 : %d, 아이디 : %s"
						+ ", 성별 : %s, 이메일 : %s, 전화번호 : %s, 주소 : %s"
						+ ", 취미 : %s, 가입날짜 : %s\n"
						, student.getStudentName()
						, student.getAge()
						, student.getStudentId()
						, student.getGender()
						, student.getEmail()
						, student.getPhone()
						, student.getAddress()
						, student.getHobby()
						, student.getEnrollDate());
			}
	}
	private String inputStudentId(String category) {
		Scanner sc = new Scanner(System.in);
		System.out.print(category + "할 아이디 입력 : ");
		System.out.println("===== 학생 아이디로 조회 =====");
		System.out.print("학생 아이디 입력 : ");
		String studentId = sc.next();
		return studentId;
	}
	private StudentVo inputStudent(String category) {
			Scanner sc = new Scanner(System.in);
			System.out.print("아이디 : ");
			String studentId = sc.next();
			System.out.print("비밀번호 : ");
			String studentPw = sc.next();
			System.out.print("이름 : ");
			String studentName = sc.next();
			System.out.print("성별 : ");
			char gender = sc.next().charAt(0);
			System.out.print("나이 : ");
			int age = sc.nextInt();
			System.out.print("이메일 : ");
			String email = sc.next();
			System.out.print("전화번호 : ");
			String phone = sc.next();
			System.out.print("주소 : ");
			sc.nextLine();	// 공백 제거, 엔터 제거
			String address = sc.nextLine();
			System.out.print("취미(,로 구분) : ");
			String hobby = sc.next();
	//		Student student = new Student();
	//		student.setStudentId(studentId);
			StudentVo student = new StudentVo(studentId, studentPw, studentName
										, gender, age, email, phone, address, hobby);
			return student;
		}
	private StudentVo modifyStudent() {
		Scanner sc = new Scanner(System.in);
		System.out.println("===== 학생 정보 수정 =====");
		System.out.print("아이디 : ");
		String studentId = sc.next();
		System.out.print("비밀번호 : ");
		String studentPw = sc.next();
		System.out.print("이메일 : ");
		String email = sc.next();
		System.out.print("전화번호 : ");
		String phone = sc.next();
		System.out.print("주소 : ");
		sc.nextLine();	// 공백 제거, 엔터 제거
		String address = sc.nextLine();
		System.out.print("취미(,로 구분) : ");
		String hobby = sc.next();
		StudentVo student = new StudentVo(studentId, studentPw, email, phone, address, hobby);
		return student;
	}
	private void displayError(String msg) {
		System.out.println(msg);
	}
	private void displaySucces(String msg) {
		System.out.println(msg);
	}
}
