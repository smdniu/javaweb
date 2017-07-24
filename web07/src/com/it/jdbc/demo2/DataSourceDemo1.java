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
			//��ȡ���ӣ���װ��������
			conn = dataSource.getConnection();
			//����ִ��sql���Ķ���ִ��sql
			String sql = "insert into category values(null,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "��ױ����");
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//�ͷ���Դ,����conn.close�黹����
			JDBCUtil.release(pstmt, conn);
		}
	}
	

}
