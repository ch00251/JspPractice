package test.todo.dao;
/*
 *  Application �������� ���� �Ѱ��� ��ü�� �����Ǿ ���Ǵ� ������ 
 *  Dao Ŭ���� �����ϱ�
 *  
 *  1. �ܺο��� ��ü �����Ҽ� ������ �������� ���� �����ڸ� private �� ���� 
 *  2. �ڽ��� �������� ���� static �ʵ� ����
 *  3. �ڽ��� �������� �������ִ� public static �޼ҵ� ����
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import test.todo.dto.TodoDto;
import test.util.DbcpBean;


public class TodoDao {
	//2. 
	private static TodoDao dao;
	//1. 
	private TodoDao() {}
	//3.
	public static TodoDao getInstance() {
		if(dao==null) {// ���� ȣ�� �ɶ��� null �̴�. 
			//null �̸� ��ü�� �����ؼ� static �������� �ʵ忡 �����Ѵ�.
			dao=new TodoDao();
		}
		return dao;
	}
	//������ DB ���� �����ϱ�
	public boolean delete(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int flag = 0;
		try {
			conn = new DbcpBean().getConn();
			String sql = "DELETE FROM todo"
					+ " WHERE num=?";
			pstmt = conn.prepareStatement(sql);
			// ? �� �� ���ε� �ϱ�
			pstmt.setInt(1, num);
			flag = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		if (flag > 0) {
			return true;
		} else {
			return false;
		}
	}
		
	//������ DB �� �����ϱ�
	public boolean insert(String content) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int flag = 0;
		try {
			conn = new DbcpBean().getConn();
			String sql = "INSERT INTO todo"
					+ " (num, content, regdate)"
					+ " VALUES(todo_seq.NEXTVAL, ?, SYSDATE)";
			pstmt = conn.prepareStatement(sql);
			// ? �� �� ���ε� �ϱ�
			pstmt.setString(1, content);
			flag = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		if (flag > 0) {
			return true;
		} else {
			return false;
		}
	}

	//���� ��� �����ϱ�
	public List<TodoDto> getList(){
		List<TodoDto> list=new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = new DbcpBean().getConn();
			String sql = "SELECT num,content,regdate"
					+ " FROM todo"
					+ " ORDER BY num ASC";
			pstmt = conn.prepareStatement(sql);
			// ? �� �� ���ε� 

			rs = pstmt.executeQuery();
			while (rs.next()) {
				TodoDto dto=new TodoDto();
				dto.setNum(rs.getInt("num"));
				dto.setContent(rs.getString("content"));
				dto.setRegdate(rs.getString("regdate"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				//connection pool �� �ݳ��ϱ� 
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		return list;
	}
}