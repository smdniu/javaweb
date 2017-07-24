package com.it.jdbc.demo2;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.junit.Test;

import com.it.jdbc.utils.JDBCUtil;


public class DataSourceDemo1 {
	private Connection conn = null;
	 PreparedStatement pstmt = null;
	private DataSource dataSource = new MyDataSource();
	
	@Test
	public void demo1(){
		try {
			//获取连接，包装过的连接
			conn = dataSource.getConnection();
			//创建执行sql语句的对象，执行sql
			String sql = "insert into category values(null,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "彩妆护肤");
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//释放资源,调用conn.close归还连接
			JDBCUtil.release(pstmt, conn);
		}
	}
	

}
