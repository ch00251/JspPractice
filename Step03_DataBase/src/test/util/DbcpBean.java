package test.util;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DbcpBean {
	//�ʵ�
	private Connection conn;
	//������
	public DbcpBean() {
		try {
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");
			conn = ds.getConnection();
			System.out.println("Connection ������ ����!");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	//Connection ��ü�� �������ִ� �޼ҵ�
	public Connection getConn() {
		return conn;
	}
}