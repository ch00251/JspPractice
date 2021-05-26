package test.file.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import test.file.dto.FileDto;
import test.util.DbcpBean;

public class FileDao {
	public static FileDao dao;
	private FileDao() {}
	public static FileDao getInstance() {
		if(dao==null) {
			dao=new FileDao();
		}
		return dao;
	}
	//���� �ٿ�ε� Ƚ���� ���� ��Ű�� �޼ҵ�
	public boolean addDownCount(int num) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		int flag=0;
		try {
			conn=new DbcpBean().getConn();
			String sql="update board_file set downCount=downCount+1 where num=?";
			pstmt=conn.prepareStatement(sql);
			//?�� �� ���ε� �ϱ�
			pstmt.setInt(1, num);
			flag=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception e) {
			}
		}
		if(flag>0) {
			return true;
		}else {
			return false;
		}
	}
	
	//���ڷ� ���޵Ǵ� ���Ϲ�ȣ�� �ش��ϴ� ���� ������ �������ִ� �޼ҵ�
	public FileDto getData(int num) {
		FileDto dto=null;
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=new DbcpBean().getConn();
			String sql="select writer,title,saveFileName,"
					+" orgFileName,fileSize,downCount,regdate"
					+" from board_file where num=?";
			pstmt=conn.prepareStatement(sql);
			//?�� �� ���ε�
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				dto=new FileDto();
				dto.setNum(num);
				dto.setWriter(rs.getString("writer"));
				dto.setTitle(rs.getString("title"));
				dto.setOrgFileName(rs.getString("orgFileName"));
				dto.setSaveFileName(rs.getString("saveFileName"));
				dto.setFileSize(rs.getLong("fileSize"));
				dto.setDownCount(rs.getInt("downCount"));
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
			}catch(Exception e) {
				
			}
		}
		return dto;
	}
	
	//���ε�� ������ ������ DB �� �����ϴ� �޼ҵ�
	public boolean insert(FileDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int flag = 0;
		try {
			conn = new DbcpBean().getConn();
			String sql = "INSERT INTO board_file"
					+ " (num,writer,title,orgFileName,saveFileName,"
					+ " fileSize,regdate)"
					+ " VALUES(board_file_seq.NEXTVAL,?,?,?,?,?,SYSDATE)";
			pstmt = conn.prepareStatement(sql);
			// ? �� �� ���ε� �ϱ�
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getOrgFileName());
			pstmt.setString(4, dto.getSaveFileName());
			pstmt.setLong(5, dto.getFileSize());
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
	
	//���� ����� �����ϴ� �޼ҵ�
	public List<FileDto> getList(){
		List<FileDto> list=new ArrayList<>();
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=new DbcpBean().getConn();
			String sql="select num,writer,title,orgFileName,fileSize,downCount,regdate"
					+" from board_file order by num desc";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				FileDto dto=new FileDto();
				dto.setNum(rs.getInt("num"));
				dto.setWriter(rs.getString("writer"));
				dto.setTitle(rs.getString("title"));
				dto.setOrgFileName(rs.getString("orgFileName"));
				dto.setFileSize(rs.getLong("fileSize"));
				dto.setDownCount(rs.getInt("downCount"));
				dto.setRegdate(rs.getString("regdate"));
				list.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				//connection pool�� �ݳ��ϱ�
				if(conn!=null)conn.close();
			}catch(Exception e) {
			}
		}
		return list;
	}
}
