package com.it.jdbc.demo4;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;

import com.it.jdbc.utils.JDBCUtil;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0Demo1 {

	@Test
	/**
	 * 手动设置参数
	 */
	public void demo1(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		//创建连接池
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		try {
			dataSource.setDriverClass("com.mysql.jdbc.Driver");
			dataSource.setJdbcUrl("jdbc:mysql:///web07");
			dataSource.setUser("root");
			dataSource.setPassword("123456");
			conn = dataSource.getConnection();
			String sql = "delete from category where cid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 9);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtil.release(pstmt, conn);
		}
		
	}
	
	@Test 
	/**
	 * 配置文件的方式
	 */
	public void demo2(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ComboPooledDataSource dataSource = new ComboPooledDataSource("mysql");
		try {
			conn = dataSource.getConnection();
			String sql = "update category set cname=?where cid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,"手机数码");
			pstmt.setInt(2, 10);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtil.release(pstmt, conn);
		}
		
	}
	
}
