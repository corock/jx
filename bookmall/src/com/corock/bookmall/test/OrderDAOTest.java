package com.corock.bookmall.test;

import java.util.List;

import com.corock.bookmall.dao.CartDAO;
import com.corock.bookmall.dao.OrderDAO;
import com.corock.bookmall.vo.CartVO;
import com.corock.bookmall.vo.OrderVO;

public class OrderDAOTest {

	public static void main(String[] args) {
//		insertTest();
		getListTest();
		
//		insertOrderBookTest();
		getOrderBookTest();
	}
	
	private static void getOrderBookTest() {
		System.out.println("주문 도서 리스트\n");

		List<OrderVO> list = new OrderDAO().getOrderBookList();
		for (OrderVO vo : list) {
			System.out.println(vo.getBookNo() + ", " + vo.getBookName() + ", " + vo.getCustomerNo());
		}	
	}

	private static void insertOrderBookTest() {
		/** constructor args: @bookNo, @bookName, @customerNo */
		OrderVO orderVo = new OrderVO(1, 1, 2);
		new OrderDAO().insertOrderBook(orderVo);

		orderVo = new OrderVO(1, 2, 3);
		new OrderDAO().insertOrderBook(orderVo);
	}

	private static void getListTest() {
		System.out.println("주문 리스트\n");

		List<OrderVO> list = new OrderDAO().getList();
		for (OrderVO vo : list) {
			System.out.println(vo);
		}
		
		System.out.println("==================================================");
	}

	private static void insertTest() {
		OrderVO orderVo = new OrderVO(1, 1, 62000, "부산 수영구 수영로");
		new OrderDAO().insert(orderVo);
	}

}
