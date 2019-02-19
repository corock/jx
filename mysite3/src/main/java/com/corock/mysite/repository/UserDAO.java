package com.corock.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.corock.mysite.exception.UserDAOException;
import com.corock.mysite.vo.UserVO;

public class UserDAO {
	
	public boolean update(UserVO vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			String sql = "UPDATE User" +
						 "   SET name = ?, password = PASSWORD(?), gender = ?" +
						 " WHERE no = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getGender());
			pstmt.setLong(4, vo.getNo());
			
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

	public UserVO get(long no) {
		UserVO result = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			String sql = "SELECT no, name, email, password, gender" +
						 "  FROM User" +
						 " WHERE no = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, no);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {				
				result = new UserVO();
				result.setNo(no);
				result.setEmail(rs.getString(3));
				result.setGender(rs.getString(5));			
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
		
		return result;
	}
	
	public UserVO get(String email, String password) {
		UserVO result = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			String sql = "SELECT no, name" +
						 "  FROM User" +
						 " WHERE email = ?" +
						 "   AND password = PASSWORD(?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				long no = rs.getLong(1);
				String name = rs.getString(2);
				
				result = new UserVO();
				result.setNo(no);
				result.setName(name);
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
		
		return result;
	}
	
	public int insert( UserVO vo ) throws UserDAOException {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			String sql = "INSERT INTO User" +
						 "     VALUES (NULL, ?, ?, PASSWORD(?), ?, now())";
			pstmt = conn.prepareStatement( sql );
			
			pstmt.setString( 1, vo.getName() );
			pstmt.setString( 2, vo.getEmail() );
			pstmt.setString( 3, vo.getPassword() );
			pstmt.setString( 4, vo.getGender() );
			
			count = pstmt.executeUpdate();
			
		} catch ( SQLException e ) {
			throw new UserDAOException( "회원정보 저장 실패" );
		} finally {
			try {
				if ( pstmt != null ) { pstmt.close(); }
				if ( conn != null )  { conn.close(); }
			} catch ( SQLException e ) {
				throw new UserDAOException( "회원정보 저장 실패" );
			}
		}
		
		return count;
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			// 1. Load jdbc driver
			Class.forName("com.mysql.jdbc.Driver");

			// 2. Connect to MySQL
			String url = "jdbc:mysql://localhost:3306/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("Failed to load driver: " + e);
		}
		
		return conn;
	}
	
}
