package test.users.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import test.users.dto.UsersDto;
import test.util.DbcpBean;

public class UsersDao {
	private static UsersDao dao;
	private UsersDao() {}
	public static UsersDao getInstance() {
		if(dao==null) {
			dao=new UsersDao();
		}
		return dao;
	}
	//���ڷ� ���޵Ǵ� ȸ��������(�̸���) DB �� �����ݿ��ϴ� �޼ҵ�
	public boolean update(UsersDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int flag = 0;
		try {
			conn = new DbcpBean().getConn();
			String sql = "update users set email=? where id=?";
			pstmt = conn.prepareStatement(sql);
			// ? �� �� ���ε� �ϱ�
			pstmt.setString(1, dto.getEmail());
			pstmt.setString(2, dto.getId());
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
	
	//���ڷ� ���޵Ǵ� ���̵� �ش��ϴ� ���������� �������ִ� �޼ҵ�
	public UsersDto getData(String id) {
		UsersDto dto=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=new DbcpBean().getConn();
			String sql="select pwd,email,regdate from users where id=?";
			pstmt=conn.prepareStatement(sql);
			//?�� �� ���ε�
			pstmt=conn.prepareStatement(sql);
			//?�� �� ���ε�
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				dto=new UsersDto();
				dto.setId(id);
				dto.setPwd(rs.getString("pwd"));
				dto.setEmail(rs.getString("email"));
				dto.setRegdate(rs.getString("regdate"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				//connection pool�� �ݳ��ϱ�
				if(conn!=null)conn.close();
			}catch(Exception e) {}
		}
		return dto;
	}
	
	//���ڷ� ���޵Ǵ� ���̵� �����ϴ��� ���θ� �����ϴ� �޼ҵ�
	public boolean isExist(String inputId) {
		boolean isExist=false; //���̵� �̹� ���� �ϴ��� ���� 
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = new DbcpBean().getConn();
			String sql = "select id from users where id=?";
			pstmt = conn.prepareStatement(sql);
			// ? �� �� ���ε� 
			pstmt.setString(1, inputId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				isExist=true; //�̹� �����ϴ� ���̵� ������...
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
		return isExist; //���̵� �̹� �����ϴ��� ���θ� �������ش�.
	}

	//���ڷ� ���޵Ǵ� UsersDto�� ��� ������ ��ȿ�� �������� ���θ� �������ִ� �޼ҵ�
	public boolean isValid(UsersDto dto) {
		boolean isValid=false;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=new DbcpBean().getConn();
			String sql="select * from users where id=? and pwd=?";
			pstmt=conn.prepareStatement(sql);
			//?�� �� ���ε��ϱ�
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPwd());
			rs=pstmt.executeQuery();
			while(rs.next()) {//select�� row�� ������
				//���̵�, ��й�ȣ�� ��ġ������ isValid=true�� �ٲ��ش�
				isValid=true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch (Exception e) {				
			}
		}
		return isValid;
	}
	
	//ȸ�� ���� ������ DB�� �����ϴ� �޼ҵ�
	public boolean insert(UsersDto dto) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		int flag=0;
		try {
			conn=new DbcpBean().getConn();
			String sql="insert into users (id,pwd,email,regdate) values(?,?,?,sysdate)";
			pstmt=conn.prepareStatement(sql);
			//?�� �� ���ε� �ϱ�
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPwd());
			pstmt.setString(3, dto.getEmail());
			flag=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		if(flag>0) {
			return true;
		}else {
			return false;
		}
	}
}
