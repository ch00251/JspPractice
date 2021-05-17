package test.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import test.member.dto.MemberDto;
import test.util.DbcpBean;

public class MemberDao {
	//1. �ڽ��� �������� ���� static �ʵ�
	private static MemberDao dao;
	//2. �ܺο��� ��ü �����Ҽ� ������ �������� ���� �����ڸ� private  ��
	private MemberDao() {}
	//3. �ڽ��� �������� �������ִ� ����(public) static �޼ҵ� ����
	public static MemberDao getInstance() {
		//���� ȣ��ɶ��� dao �ʵ�� null �̴�
		if(dao==null) {
			dao=new MemberDao();//��ü �����ؼ� static �������� �ʵ忡 ����
		}
		return dao; //static �ʵ忡 �ִ� ������ ������ �ֱ� 
	}

	//ȸ�� ��ü ����� �����ϴ� �޼ҵ�
	public List<MemberDto> getList(){
		//ȸ������� ���� ��ü ����
		List<MemberDto> list=new ArrayList<>();
		//�ʿ��� ��ü�� ���� �������� �����
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			//Connection ��ü (connection pool �κ���) �ϳ� ������ ���� 
			conn=new DbcpBean().getConn();
			String sql="SELECT num,name,addr FROM member"
					+ " ORDER BY num DESC";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				MemberDto dto=new MemberDto();
				dto.setNum(rs.getInt("num"));
				dto.setName(rs.getString("name"));
				dto.setAddr(rs.getString("addr"));
				list.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				//connection pool �� �ݳ��ϱ� 
				if(conn!=null)conn.close(); 
			}catch(Exception e) {}
		}
		return list;
	}
	//ȸ�� �Ѹ��� ���� �߰� 
	public boolean insert(MemberDto dto) {
		return false;
	}
	//ȸ�� �Ѹ��� ���� ����
	public boolean update(MemberDto dto) {
		return false;
	}
	//ȸ�� �Ѹ��� ���� ����
	public boolean delete(int num) {
		return false;
	}
	//ȸ�� �Ѹ��� ���� ����
	public MemberDto getData(int num) {
		return null;
	}
}
