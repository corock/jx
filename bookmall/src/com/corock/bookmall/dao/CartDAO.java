package com.corock.bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.corock.bookmall.config.DB;
import com.corock.bookmall.vo.CartVO;

public class CartDAO {

	public int calculateTotalPrice(long customerNo) {
		int total = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DB.getConnection();
			String sql = "SELECT SUM(b.price * c.count) AS 'sum'" + 
						 "  FROM Member a, Book b, Cart c" + 
						 " WHERE a.no = c.customer_no" + 
						 "   AND b.no = c.book_no" +
						 "   AND c.customer_no = " + customerNo;
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				total = rs.getInt(1);				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)    { rs.close(); }
				if (pstmt != null) { pstmt.close(); }
				if (conn != null)  { conn.close(); }
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return total;
	}
	
	public List<CartVO> getList() {
		List<CartVO> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DB.getConnection();
			String sql = "SELECT b.no, b.title, c.count, b.price" + 
						 "  FROM Member a, Book b, Cart c" + 
						 " WHERE a.no = c.customer_no" + 
						 "   AND b.no = c.book_no";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				CartVO vo = new CartVO();
				
				vo.setCustomerNo(rs.getLong(1));
				vo.setBookName(rs.getString(2));
				vo.setCount(rs.getInt(3));
				vo.setPrice(rs.getInt(4));
				
				list.add(vo);
			}
			
		} catch (SQLException e) {
			System.out.println("Error3: " + e);
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
	
	public boolean insert(CartVO vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DB.getConnection();
			String sql = "INSERT INTO Cart VALUES (?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, vo.getCustomerNo());
			pstmt.setLong(2, vo.getBookNo());
			pstmt.setInt(3, vo.getCount());
			
			int count = pstmt.executeUpdate();
			result = (count == 1);
			
		} catch (SQLException e) {
			System.out.println("Error2: " + e);
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
