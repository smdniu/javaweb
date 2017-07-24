package com.it.jdbc.demo5;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

import com.it.jdbc.utils.JDBCUtil2;
import com.it.jdbc.utils.MyDBUtil;

public class CategoryDao {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	//添加分类
	
	public void add(Category category){
		/*try {
			conn = JDBCUtil2.getConnection();
			String sql = "insert into category values(null,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, category.getCname());
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtil2.release(pstmt, conn);
		}*/
		String sql = "insert into category values(null,?)";
		MyDBUtil.update(sql, category.getCname());
	}
	//删除分类
	public void delete(int cid){
		/*try {
			conn = JDBCUtil2.getConnection();
			String sql = "delete from category where cid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cid);
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtil2.release(pstmt, conn);
		}
		*/
		String sql = "delete from category where cid = ?";
		MyDBUtil.update(sql, cid);
	}
	
	//修改分类
	public void update(Category category){
		/*try{
			conn = JDBCUtil2.getConnection();
			String sql = "update category set cname = ? where cid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, category.getCname());
			pstmt.setInt(2, category.getCid());
			pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtil2.release(pstmt, conn);
		}*/
		String sql = "update category set cname = ? where cid = ?";
		MyDBUtil.update(sql, category.getCname(),category.getCid());
	}
	//查询分类
	//查询所有
	public void query(){
		try {
			conn = JDBCUtil2.getConnection();
			String sql = "select * from category";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);
			while(rs.next()){
				System.out.println(rs.getInt("cid")+ " "+rs.getString("cname"));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtil2.release(rs, pstmt, conn);
		}
	
		
	}
	
	@Test
	public void testAdd(){
		Category category = new Category();
		category.setCname("零食");
		this.add(category);
		
	}
	
	@Test 
	public void testDel(){
		Category category = new Category();
		category.setCid(4);
		this.delete(category.getCid());
	}
	
	@Test
	public void testUpd(){
		Category category = new Category();
		category.setCid(2);
		category.setCname("彩妆护肤");
		this.update(category);
	}
	
	@Test
	public void testQue(){
		this.query();
	}
}
