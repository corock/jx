package com.corock.bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.corock.bookmall.config.DB;
import com.corock.bookmall.vo.OrderVO;

public class OrderBookDAO {

	public boolean orderToList(ArrayList<OrderVO> list) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
		
		try {
			conn = DB.getConnection();
			sql = "INSERT INTO Order_Book VALUES (?, ?, ?)";
			pstmt = conn.prepareStatement(sql);

//			pstmt.setLong(1, vo.getCustomerNo());
//			pstmt.setLong(2, vo.getBookNo());
//			pstmt.setInt(3, vo.getCount());

			int count = pstmt.executeUpdate();
			result = (count == 1);
			
		} catch (SQLException e) {
			System.out.println("OrderDAO insert() Error: " + e);
		} finally {
			try {
				if (conn != null) { conn.close(); }
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public List<OrderVO> getList(long orderNo) {
		List<OrderVO> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		try {
			conn = DB.getConnection();

			sql = "SELECT a.name, b.total_price, b.address" + 
				  "  FROM Member a, Order_List b" + 
				  " WHERE a.no = b.customer_no" +
				  "   AND b.no = " + orderNo;
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				OrderVO vo = new OrderVO();
				vo.setCustomerName(rs.getString(1));
				vo.setTotalPrice(rs.getInt(2));
				vo.setAddress(rs.getString(3));
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
	
}
