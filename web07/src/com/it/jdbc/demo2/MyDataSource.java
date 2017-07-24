package com.it.jdbc.demo2;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.it.jdbc.utils.JDBCUtil;

/**
 * 自定义连接池
 * @author Administrator
 *
 */
public class MyDataSource implements DataSource{
	List<Connection> list = new ArrayList<Connection>();
	public MyDataSource() {
		//初始化的时候创建5个连接
		for(int i=0;i<5;i++){
			Connection conn = JDBCUtil.getConnection();
			list.add(conn);
		}
	}
	public Connection getConnection() throws SQLException {
		if(list.size()==0){
			for(int i=0;i<3;i++){
				Connection conn = JDBCUtil.getConnection();
				list.add(conn);
			}
		}
		Connection conn = list.remove(0);
		//包装连接对象调用conn.close()可以归还给list
		MyConn myConn = new MyConn(conn,list);
		return myConn;
	}

	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}


	public Connection getConnection(String username, String password)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
