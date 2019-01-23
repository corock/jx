package com.corock.bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.corock.bookmall.config.DB;
import com.corock.bookmall.vo.CartVO;
import com.corock.bookmall.vo.OrderVO;

public class OrderDAO {
	
	public List<OrderVO> getOrderBookList() {
		List<OrderVO> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DB.getConnection();
			String sql = "SELECT b.book_no, c.title, a.customer_no" +
						 "  FROM Order_List a, Order_Book b, Book c" + 
						 " WHERE b.order_no = a.no" +
						 "   AND b.book_no = c.no";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				OrderVO vo = new OrderVO();
				
				vo.setBookNo(rs.getLong(1));
				vo.setBookName(rs.getString(2));
				vo.setCustomerNo(rs.getLong(3));
				
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
	
	public boolean insertOrderBook(OrderVO vo) {
		List<OrderVO> list = new ArrayList<>();
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DB.getConnection();
			String sql = "INSERT INTO Order_Book VALUES (?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, vo.getOrderNo());
			pstmt.setLong(2, vo.getBookNo());
			pstmt.setInt(3, vo.getCount());

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
	
	public List<OrderVO> getList() {
		List<OrderVO> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DB.getConnection();
			String sql = "SELECT a.no," +
						 "       concat(b.name, '/', b.email)," +
						 "       a.total_price," +
						 "       a.address" + 
						 "  FROM Order_List a, Member b" + 
						 " WHERE a.customer_no = b.no";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				OrderVO vo = new OrderVO();
				
				vo.setNo(rs.getLong(1));
				vo.setCustomerName(rs.getString(2));
				vo.setTotalPrice(rs.getInt(3));
				vo.setAddress(rs.getString(4));
				
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
	
	public boolean insert(OrderVO vo) {
		List<OrderVO> list = new ArrayList<>();
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DB.getConnection();
			String sql = "INSERT INTO Order_List VALUES (NULL, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, vo.getTotalPrice());
			pstmt.setString(2, vo.getAddress());
			pstmt.setLong(3, vo.getCustomerNo());

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
