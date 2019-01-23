package com.corock.bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.corock.bookmall.config.DB;
import com.corock.bookmall.vo.CategoryVO;

public class CategoryDAO {

	public List<CategoryVO> getList() {
		List<CategoryVO> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DB.getConnection();
			String sql = "SELECT * FROM Category";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				CategoryVO vo = new CategoryVO();
				vo.setNo(rs.getLong(1));
				vo.setSubject(rs.getString(2));
				list.add(vo);
			}
			
		} catch (SQLException e) {
			System.out.println("Error: " + e);
		} finally {
			try {
				if (rs != null)    { rs.close(); }
				if (pstmt != null) { pstmt.close(); }
				if (pstmt != null) { conn.close(); }
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	public boolean insert(CategoryVO vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DB.getConnection();
			String sql = "INSERT INTO Category VALUES (NULL, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getSubject());
			
			int count = pstmt.executeUpdate();
			result = (count == 1);
			
		} catch (SQLException e) {
			System.out.println("Error: " + e);
		} finally {
			try {
				if (conn != null) { conn.close(); }
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
}
