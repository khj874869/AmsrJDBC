package com.kh.jdbc.day04.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.kh.jdbc.day04.controller.Student_Controller;
import com.kh.jdbc.day04.model.vo.StudentVo;

public class Student_View {
	private Student_Controller controller;
	public Student_View() {
		
		controller = new Student_Controller();
		
	}
	public void studentProgram() {
		List<StudentVo>sList = null;
		while(true) {
			int choice = printMenu();
			int result = 0;
			switch(choice) {
			case 1 : 
				//SELECT * FROM STUDENT_TBL
				sList = controller.selectAll();
				if(!sList.isEmpty()) {
					printAllStudents(sList);
				}else {
					displayError("데이터 조회에 실패 하였습니다.");
				}
				break;
			case 2 : 
				//SELECT FROM STUDENT_TBL WHERE STUDENT_ID = 'khuser01';
				String StudentId =getbyOneId();
				StudentVo student = controller.selectOneById(StudentId);
				break;
			case 3 : 
				String stdname = inputStudentName("검색");
				sList = controller.inputStudentName(stdname);
				break;
			case 4 : 
				student = inputStudent("등록");
				 result = controller.inputStudent(student);
				if(result>0) {
					success("등록에 성공하셨습니다.");
				}else {
					displayError("등록에 실패하였습니다.");
				}
				break;
			case 5 : 
				String Ido = inputStudentName("수정할");
			    student = modifyStudent(Ido);
				result = controller.Updateinfo(student);
				if(result>0) {
					success("수정에 성공하셨습니다.");
				}else {
					displayError("수정에 실패하였습니다.");
				}
				break;
			case 6 :
				String Id = inputStudentName("삭제할");
				result = controller.delete(Id);
				if(result>0) {
					success("등록에 성공하셨습니다.");
				}else {
					displayError("등록에 실패하였습니다.");
				}
				break;
			case 0 : break;
			}
		}
	}

	private void success(String string) {
		System.out.println(string);
	}
	private StudentVo modifyStudent(String string) {
		Scanner sc = new Scanner(System.in);
		System.out.println("===== 학생 정보 수정 =====");
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
		StudentVo student = new StudentVo(studentPw, email, phone, address, hobby);
		return student;
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
	private String inputStudentName(String category) {
		Scanner sc = new Scanner(System.in);
		System.out.print(category + "할 아이디 입력 : ");
		System.out.println("===== 학생 아이디로 조회 =====");
		System.out.print("학생 아이디 입력 : ");
		String studentId = sc.next();
		return studentId;
	}
	
	private String getbyOneId() {
		Scanner sc = new Scanner(System.in);
		System.out.print("찾을 학생의 아이디를 입력해주세요.:");
		String studentname = sc.next();
		return studentname;
	}
	private void printAllStudents(List<StudentVo> sList) {
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
	private void displayError(String msg) {
		System.out.println(msg);

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
}

