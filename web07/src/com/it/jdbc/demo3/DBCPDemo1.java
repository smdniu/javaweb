package com.it.jdbc.demo3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.junit.Test;

import com.it.jdbc.utils.JDBCUtil;

/**
 *dbcp连接池测试
 * @author Administrator
 *
 */
public class DBCPDemo1 {

	@Test
	/**
	 * 手动设置参数
	 */
	public void demo1(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			BasicDataSource dataSource  = new BasicDataSource();
			dataSource.setDriverClassName("com.mysql.jdbc.Driver");
			dataSource.setUrl("jdbc:mysql:///web07");
			dataSource.setUsername("root");
			dataSource.setPassword("123456");
			conn = dataSource.getConnection();
			String sql = "insert into category values(null,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,"男装女装");
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtil.release(pstmt, conn);
		}
	}

	@Test
	/**
	 * 属性文件
	 */
	public void demo2(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream("src/dbcp.properties"));
			DataSource dataSource = new BasicDataSourceFactory().createDataSource(properties);
			conn = dataSource.getConnection();
			String sql = "select * from category";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				System.out.println(rs.getInt("cid")+" "+rs.getString("cname"));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtil.release(rs, pstmt, conn);
		}
		
		
		
	}


}

