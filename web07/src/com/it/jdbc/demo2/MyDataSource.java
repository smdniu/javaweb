package com.it.jdbc.demo2;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.it.jdbc.utils.JDBCUtil;

/**
 * �Զ������ӳ�
 * @author Administrator
 *
 */
public class MyDataSource implements DataSource{
	List<Connection> list = new ArrayList<Connection>();
	public MyDataSource() {
		//��ʼ����ʱ�򴴽�5������
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
		//��װ���Ӷ������conn.close()���Թ黹��list
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
