package com.it.jdbc.demo1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.it.jdbc.utils.JDBCUtil;

public class JDBCdemo1 {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	@Test
	/**
	 * ��ӷ������
	 */
	public void demo1(){
		
		try {
			//1.������
			Class.forName("com.mysql.jdbc.Driver");
			//2.�������
			conn = DriverManager.getConnection("jdbc:mysql:///web07", "ss", "123456");
			//3.����ִ��sql���Ķ���
			String sql = "insert into category values(null,?)";
			pstmt = conn.prepareStatement(sql);
			//���ò���
			pstmt.setString(1, "���԰칫");
			//ִ��sql���
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				pstmt=null;
			}
			
			if(conn!=null){
				try {
					conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
				conn=null;
			}
		}
	}
	
	@Test
	/**
	 * �޸ķ���Ĳ���
	 */
	public void demo2(){
		/**
		 * ������������
		 * ����ִ��sql���Ķ���ִ��sql
		 * �ͷ���Դ
		 */
		try {
			//�������
			conn = JDBCUtil.getConnection();
			//����ִ��sql���Ķ���ִ��sql���
			String sql = "update category set cname = ? where cid = ?";
			pstmt = conn.prepareStatement(sql);
			//���ò���
			pstmt.setString(1, "�ֻ�����");
			pstmt.setInt(2,5);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtil.release(pstmt, conn);
		}
		
		
	}
	
	@Test
	/**
	 * ɾ�����
	 */
	public void demo3(){
		try {
			conn = JDBCUtil.getConnection();
			String sql = "delete from category where cid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 5);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			JDBCUtil.release(pstmt, conn);
		}
	}

	@Test
	/**
	 * ��ѯ���з������
	 */
	public void demo4(){
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select * from category";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				System.out.println(rs.getInt("cid")+" "+rs.getString("cname"));
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtil.release(rs,pstmt, conn);
		}
	}
	
	@Test 
	/*
	 * ��ѯĳ������
	 */
	public void demo5(){
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select cname from category where cid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 7);
			rs = pstmt.executeQuery();
			while(rs.next()){
				System.out.println(rs.getString("cname"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtil.release(rs, pstmt, conn);
		}
	}
	
	
}
