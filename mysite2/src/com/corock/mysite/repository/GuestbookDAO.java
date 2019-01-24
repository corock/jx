package com.corock.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.corock.mysite.vo.GuestbookVO;

public class GuestbookDAO {
	
	public boolean delete(GuestbookVO vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			String sql = "DELETE FROM Guestbook" +
						 "      WHERE no = ?" +
						 "        AND password = PASSWORD(?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, vo.getNo());
			pstmt.setString(2, vo.getPassword());
			
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

	public boolean insert(GuestbookVO vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			String sql = "INSERT INTO Guestbook" +
						 "     VALUES (NULL, ?, PASSWORD(?), ?, now())";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getMessage());
			
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
	
	public List<GuestbookVO> getList() {
		List<GuestbookVO> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "   SELECT no, name, password, message, date_format(reg_date, '%b %d, %Y')" + 
						 "     FROM Guestbook" + 
						 " ORDER BY no DESC";
			pstmt = conn.prepareStatement(sql);
	
			rs = pstmt.executeQuery();
			 
			while (rs.next()) {
				GuestbookVO vo = new GuestbookVO();
				
				vo.setNo(rs.getLong(1));
				vo.setName(rs.getString(2));
				vo.setPassword(rs.getString(3));
				vo.setMessage(rs.getString(4).replace("\r\n", "<br />"));
				vo.setRegDate(rs.getString(5));
				
				list.add(vo);
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
	
}
