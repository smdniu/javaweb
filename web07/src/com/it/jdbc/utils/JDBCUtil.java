package com.it.jdbc.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.junit.Test;




public class JDBCUtil {
	/*
	 * 1.读取属性文件-静态代码块
	 * 2.加载驱动-c
	 * 3.获得连接-c
	 * 4.释放资源-c
	 * 
	 */
	//定义登录数据库常量
	private static final String DRIVERCLASS;
	private static final String URL;
	private static final String USERNAME;
	private static final String PASSWORD;
	private static  Connection conn = null;
	//静态代码块读取属性文件(加载驱动)
	static{
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream("src/db.properties"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		DRIVERCLASS = properties.getProperty("jdbc.driverClass");
		//System.out.println(DRIVERCLASS);
		URL = properties.getProperty("jdbc.url");
		USERNAME = properties.getProperty("jdbc.username");
		PASSWORD = properties.getProperty("jdbc.password");
		/*try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}*/
	}
	
	//加载驱动
	public static void loadDriver(){
		try {
			Class.forName(DRIVERCLASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//获取连接
	public static Connection getConnection(){
		/*try {
			conn = DriverManager.getConnection("jdbc:mysql:///web07", "ss", "123456");
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		
		loadDriver();
		try {
			conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	//释放资源
	public static void release(Statement stmt , Connection conn){
		if(stmt!=null){
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			stmt=null;
		}
		
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conn=null;
		}
	}
	
	public static void release(ResultSet rs ,Statement stmt , Connection conn){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			rs=null;
		}
		if(stmt!=null){
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			stmt=null;
		}
		
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn=null;
		}
	}
	
	@Test
	public void test(){
		Connection conn = null;
		conn = JDBCUtil.getConnection();
		if(conn!=null){
			System.out.println("数据库连接成功！");
		}else{
			System.out.println("数据库连接失败!");
		}
		//JDBCUtil  j = new JDBCUtil();
		
		
		
	}

}
