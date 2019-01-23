package com.corock.bookmall.main;

import java.util.List;

import com.corock.bookmall.dao.BookDAO;
import com.corock.bookmall.dao.CartDAO;
import com.corock.bookmall.dao.CategoryDAO;
import com.corock.bookmall.dao.MemberDAO;
import com.corock.bookmall.dao.OrderDAO;
import com.corock.bookmall.vo.BookVO;
import com.corock.bookmall.vo.CartVO;
import com.corock.bookmall.vo.CategoryVO;
import com.corock.bookmall.vo.MemberVO;
import com.corock.bookmall.vo.OrderVO;

public class BookMall {

	public static void main(String[] args) {
		/** 1. 회원 리스트 - 2명 */
		insertMember();
		getMemberList();
			
		/** 2. 카테고리 리스트 - 3개 */
		insertCategory();
		getCategoryList();

		/** 3. 상품 리스트 - 3개 */
		insertBook();
		getBookList();
		
		/** 4. 카트 리스트 - 2개 */
		insertCart();
		getCartList();
		
		/** 5. 주문 리스트 - 1개 */
		insertOrder();
		getOrderList();

		/** 6. 주문 도서 리스트 - 2개 */
		insertOrderBook();
		getOrderBookList();
	}

	private static void getOrderBookList() {
		System.out.println("주문 도서 리스트\n");

		List<OrderVO> list = new OrderDAO().getOrderBookList();
		for (OrderVO vo : list) {
			System.out.println(vo.getBookNo() + ", " + vo.getBookName() + ", " + vo.getCustomerNo());
		}		
	}

	private static void insertOrderBook() {
		/** constructor args: @bookNo, @bookName, @customerNo */
		OrderVO orderVo = new OrderVO(1, 1, 2);
		new OrderDAO().insertOrderBook(orderVo);

		orderVo = new OrderVO(1, 2, 3);
		new OrderDAO().insertOrderBook(orderVo);
	}

	private static void getOrderList() {
		System.out.println("주문 리스트\n");

		List<OrderVO> list = new OrderDAO().getList();
		for (OrderVO vo : list) {
			System.out.println(vo);
		}
		
		System.out.println("==================================================");		
	}

	private static void insertOrder() {
		OrderVO orderVo = new OrderVO(1, 1, 62000, "부산 수영구 수영로");
		new OrderDAO().insert(orderVo);
	}

	private static void getCartList() {
		System.out.println("카트 리스트\n");

		List<CartVO> list = new CartDAO().getList();
		for (CartVO vo : list) {
			System.out.println(vo);
		}

		System.out.println("==================================================");
	}

	private static void insertCart() {
		/** constructor args: @customerNo, @bookNo, @count */
		CartVO cartVo = new CartVO(1, 1, 2);
		new CartDAO().insert(cartVo);

		cartVo = new CartVO(1, 2, 3);
		new CartDAO().insert(cartVo);
	}
	
	private static void getBookList() {
		System.out.println("상품 리스트\n");
		
		List<BookVO> list = new BookDAO().getList();
		for (BookVO vo : list) {
			System.out.println(vo);
		}
		
		System.out.println("==================================================");
	}
	
	private static void insertBook() {
		BookVO bookVo = new BookVO("Effective Java", 36000, 1);
		new BookDAO().insert(bookVo);

		bookVo = new BookVO("In Paris", 26000, 2);
		new BookDAO().insert(bookVo);

		bookVo = new BookVO("1984", 7100, 3);
		new BookDAO().insert(bookVo);
	}
	
	private static void getCategoryList() {
		System.out.println("카테고리 리스트\n");
		List<CategoryVO> list = new CategoryDAO().getList();
		
		for (CategoryVO vo : list) {
			System.out.println(vo);
		}
		System.out.println("==================================================");
	}

	private static void insertCategory() {
		CategoryVO vo = new CategoryVO("소설");
		new CategoryDAO().insert(vo);

		vo = new CategoryVO("예술");
		new CategoryDAO().insert(vo);
		
		vo = new CategoryVO("컴퓨터/IT");
		new CategoryDAO().insert(vo);
	}
	
	private static void getMemberList() {
		System.out.println("회원정보\n");
		List<MemberVO> list = new MemberDAO().getList();

		for (MemberVO vo : list) {
			System.out.println(vo);
		}
		System.out.println("==================================================");
	}
	
	private static void insertMember() {
		MemberVO memberVo = new MemberVO("이도훈", "010-9876-5432", "coco@gmail.com", "******");
		new MemberDAO().insert(memberVo);

		memberVo = new MemberVO("김원준", "010-2345-6789", "kim@naver.com", "********");
		new MemberDAO().insert(memberVo);
	}

}
