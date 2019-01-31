package com.corock.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.corock.mysite.vo.BoardVO;
import com.corock.mysite.vo.GuestbookVO;
import com.corock.mysite.vo.UserVO;

public class BoardDAO {
	
	public boolean reply(BoardVO vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			String sql = "INSERT INTO Board VALUES (NULL, ?, ?, now(), ?, " +
						 "?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setInt(3, vo.getHit());
			pstmt.setInt(4, vo.getGroupNo());
			pstmt.setInt(5, vo.getOrderNo());
			pstmt.setInt(6, vo.getDepth());
			pstmt.setLong(7, vo.getUserNo());
			
			int count = pstmt.executeUpdate();
			result = (count == 1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) { pstmt.close(); }
				if (conn != null)  { conn.close(); }
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public boolean delete(BoardVO vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			String sql = "DELETE FROM Board WHERE no = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, vo.getNo());
			
			int count = pstmt.executeUpdate();
			result = (count == 1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) { pstmt.close(); }
				if (conn != null)  { conn.close(); }
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public boolean update(BoardVO vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			String sql = "UPDATE Board" +
						 "   SET title = ?, contents = ?" +
						 " WHERE no = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3, vo.getNo());
			
			int count = pstmt.executeUpdate();
			result = (count == 1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) { pstmt.close(); }
				if (conn != null)  { conn.close(); }
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public boolean addHitCount(long no) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			String sql = "UPDATE Board SET hit = hit + 1 WHERE no = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, no);
			
			int count = pstmt.executeUpdate();
			result = (count == 1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) { pstmt.close(); }
				if (conn != null)  { conn.close(); }
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	public boolean insert(BoardVO vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			String sql = "INSERT INTO Board VALUES (NULL, ?, ?, now(), ?, " +
						 "(SELECT IFNULL(MAX(group_no), 0) + 1 FROM Board a), 1, 0, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setInt(3, vo.getHit());
			pstmt.setLong(4, vo.getUserNo());
			
			int count = pstmt.executeUpdate();
			result = (count == 1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) { pstmt.close(); }
				if (conn != null)  { conn.close(); }
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public BoardVO get(long no) {
		BoardVO vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "SELECT b.title, b.contents, b.user_no, b.no, b.group_no, b.order_no, b.depth" +
						 "  FROM Board b, User c" +
						 " WHERE b.user_no = c.no" +
						 "   AND b.no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				vo = new BoardVO();
				vo.setTitle(rs.getString(1));
				vo.setContents(rs.getString(2));
				vo.setUserNo(rs.getLong(3));
				vo.setNo(rs.getLong(4));
				vo.setGroupNo(rs.getInt(5));
				vo.setOrderNo(rs.getInt(6));
				vo.setDepth(rs.getInt(7));
			}
			
		} catch (SQLException e) {
			System.out.println("Error: " + e);
		} finally {
			try {
				if (rs != null)    { rs.close(); }
				if (pstmt != null) { pstmt.close(); }
				if (conn != null)  { conn.close(); }
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return vo;
	}
	
	public List<BoardVO> getList(String keyword, int pageCount) {
		List<BoardVO> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "   SELECT b.no, b.title, c.name, b.hit, date_format(b.write_date, '%Y-%m-%d %H:%m:%s'), c.no, b.depth" + 
						 "     FROM Board b, User c " +
						 "    WHERE b.user_no = c.no" + 
						 "      AND (b.title LIKE ? OR b.contents LIKE ?)" +
						 " ORDER BY b.group_no DESC, b.order_no ASC" +
						 "    LIMIT ?, 10";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setString(2, "%" + keyword + "%");
			pstmt.setInt(3, (pageCount - 1) * 10);

			rs = pstmt.executeQuery();
			 
			while (rs.next()) {
				BoardVO vo = new BoardVO();
				
				vo.setNo(rs.getLong(1));
				vo.setTitle(rs.getString(2));
				vo.setUserName(rs.getString(3));
				vo.setHit(rs.getInt(4));
				vo.setWriteDate(rs.getString(5).replace("\r\n", "<br />"));
				vo.setUserNo(rs.getLong(6));
				vo.setDepth(rs.getInt(7));
				
				list.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("getList() Error: " + e);
		} finally {
			try {
				if (rs != null)    { rs.close(); }
				if (pstmt != null) { pstmt.close(); }
				if (conn != null)  { conn.close(); }
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			String url = "jdbc:mysql://localhost:3306/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("Failed to load driver: " + e);
		}
		
		return conn;
	}

	public int getTotalCount(String keyword) {
		List<BoardVO> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalCount = 0;
		
		try {
			conn = getConnection();
			String sql = "   SELECT COUNT(*) FROM Board" +
						 "    WHERE title LIKE ? OR contents LIKE ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setString(2, "%" + keyword + "%");

			rs = pstmt.executeQuery();
			 
			if (rs.next()) {
				totalCount = rs.getInt(1);
			}

		} catch (SQLException e) {
			System.out.println("getList() Error: " + e);
		} finally {
			try {
				if (rs != null)    { rs.close(); }
				if (pstmt != null) { pstmt.close(); }
				if (conn != null)  { conn.close(); }
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return totalCount;
	}
	
}
