package com.it.jdbc.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.junit.Test;

/**
 * 增删改工具类
 * @author Administrator
 *
 */
public class MyDBUtil {
	private static Connection conn = null;
	private static PreparedStatement pstmt = null;
	public static void update(String sql ,Object...args){
		try {
			conn = JDBCUtil2.getConnection();
			pstmt = conn.prepareStatement(sql);
			for (int i =0;i<args.length;i++) {
				pstmt.setObject(i+1, args[i]);
			}
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtil2.release(pstmt, conn);
		}
	}

	@Test 
	public void testUpd(){
		MyDBUtil.update("insert into category values(null,?)", "儿童玩具");
	}
}
