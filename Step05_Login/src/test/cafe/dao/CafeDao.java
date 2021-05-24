package test.cafe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import test.cafe.dto.CafeDto;
import test.util.DbcpBean;

public class CafeDao {
	private static CafeDao dao;
	private CafeDao() {}
	public static CafeDao getInstance() {
		if(dao==null) {
			dao=new CafeDao();
		}
		return dao;
	}
	//�� ��ü�� ������ �����ϴ� �޼ҵ�
	public int getCount() {
		int rowCount=0;
		Connection  conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=new DbcpBean().getConn();
			String sql="select max(rownum) as count from board_cafe";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				rowCount=rs.getInt("count");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception e) {
				
			}
		}
		return rowCount;
	}
	
	//�� ���� �����ϴ� �޼ҵ�
	public boolean insert(CafeDto dto) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		int flag=0;
		try {
			conn=new DbcpBean().getConn();
			String sql="insert into board_cafe"
					+" (num,writer,title,content,viewCount,regdate)"
					+" values(board_cafe_seq.nextval,?,?,?,0,sysdate)";
			pstmt=conn.prepareStatement(sql);
			//?�� �� ���ε��ϱ�
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
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
	
	//�� ����� �����ϴ� �޼ҵ�  ( ctrl + shift + o : auto import )
	public List<CafeDto> getList(CafeDto dto){
		List<CafeDto> list=new ArrayList<CafeDto>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=new DbcpBean().getConn();
			/*
				SELECT * 
				FROM
			    	(SELECT result1.*, ROWNUM AS rnum
			    FROM
			        (SELECT num,writer,title,viewCount,regdate
			        FROM board_cafe
			        ORDER BY num DESC) result1)
				WHERE rnum BETWEEN ? AND ?
			 */
			String sql="SELECT *"
					+ " FROM"
					+ " (SELECT result1.*, ROWNUM AS rnum"
					+ " FROM"
					+ " (SELECT num,writer,title,viewCount,regdate"
					+ " FROM board_cafe"
					+ " ORDER BY num DESC) result1)"
					+ " WHERE rnum BETWEEN ? AND ?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getStartRowNum());
			pstmt.setInt(2, dto.getEndRowNum());
			rs=pstmt.executeQuery();
			while(rs.next()) {//�ݺ��� ���鼭
				//select�� row�� ������ CafeDto ��ü�� ��Ƽ�
				CafeDto tmp=new CafeDto();
				tmp.setNum(rs.getInt("num"));
				tmp.setWriter(rs.getString("writer"));
				tmp.setTitle(rs.getString("title"));
				tmp.setViewCount(rs.getInt("viewCount"));
				tmp.setRegdate(rs.getString("regdate"));
				//ArrayList��ü�� ���� ��Ų��.
				list.add(tmp);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception e) {				
			}
		}
		return list;
	}
}
