package com.corock.bookmall.test;

import java.util.List;

import com.corock.bookmall.dao.BookDAO;
import com.corock.bookmall.vo.BookVO;

public class BookDAOTest {

	public static void main(String[] args) {
//		insertTest();
		getListTest();
	}

	private static void getListTest() {
		System.out.println("상품 리스트\n");
		
		List<BookVO> list = new BookDAO().getList();
		for (BookVO vo : list) {
			System.out.println(vo);
		}
		
		System.out.println("==================================================");
	}

	private static void insertTest() {
		BookVO bookVo = new BookVO("Effective Java", 36000, 1);
		new BookDAO().insert(bookVo);

		bookVo = new BookVO("In Paris", 26000, 2);
		new BookDAO().insert(bookVo);

		bookVo = new BookVO("1984", 7100, 3);
		new BookDAO().insert(bookVo);
	}
	
}
