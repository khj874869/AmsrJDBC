package com.kh.jdbc.day04.common;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.*;
import java.util.Properties;

public class JDBCTemplate {
	
	private Properties prop;

	
	

	private static JDBCTemplate instance;
	private static Connection conn;
	private JDBCTemplate() {
		
	}
	//디자인 패턴 : 각기 다른 소프트웨어 모듈이나 기능을 가진 응용 SW를
	//개발할 때 공통되는 설계 문제를 해결히기 위하여 사용되는 패턴 
	// ==> 효율적인 방식을 위함
	// 패턴의 종류 : 생성 , 구조, 행위 패턴
	//1.생성: 싱글톤 패턴,추상팩토리,팩토리 메서드,...
	//2.구조: 컴포지트, 데코레이트,...
	//3.행위: 옵저버, 스테이트 , 전략, 템플릿 메서드,...
	
	/*public class Singletone{
	 * 			private static Singletons instance;
	 * 			private singletoe(){}
	 * 			public static Singleton getInstance(){
	 * 				if (instance ==null){
	 * 				instance =	new Singletone();
	 * 				}
	 * 			
	 */
	//무조건 딱 한번만 생성되고 없을 때에만 생성한다.
	//이미 존재하면 존재하는 객체를 사용한다.
	public static JDBCTemplate getinstance() {
		//이미 만들어져 있는지 체크하고
		if(instance == null) {
			//안 만들어져 있으면 그것을 사용함
			instance = JDBCTemplate.getinstance();
		}
		//만들어져 있으면 그것을 사용 
		return instance;		
	}
	public Connection createConnection() {
		try {
			prop = new Properties();
			Reader reader = new FileReader("resources/dev.properties");
			prop.load(reader);
			String driverName = prop.getProperty("driverName");
			String url = prop.getProperty("url");
			String user = prop.getProperty("user");
			String password = prop.getProperty("password");
			
				if(conn ==null || conn.isClosed()) {
					Class.forName(driverName);
					conn = DriverManager.getConnection(url,user,password);
					conn.setAutoCommit(false); //Auto Commit 해제
		}} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
			 //DBCP(DataBase Connection pool)
		 catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return conn;	
	}
	public static void commit(Connection conn) {
		try {
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void rollback(Connection conn) {
		try {
			if(conn !=null && !conn.isClosed())conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		if(conn!=null) {
			try {
				if(!conn.isClosed()) {
					conn.isClosed();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
