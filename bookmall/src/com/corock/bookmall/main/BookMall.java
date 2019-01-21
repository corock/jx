package com.corock.bookmall.main;

import java.util.List;

import com.corock.bookmall.dao.BookDAO;
import com.corock.bookmall.dao.CategoryDAO;
import com.corock.bookmall.dao.MemberDAO;
import com.corock.bookmall.vo.BookVO;
import com.corock.bookmall.vo.CategoryVO;
import com.corock.bookmall.vo.MemberVO;

public class BookMall<T> {

	public static void main(String[] args) {
		/** 1. 회원 리스트 - 2명 */
//		insert("이도훈", "010-9876-5432", "coco@gmail.com", "coco1234");
//		insert("김원준", "010-2345-6789", "kim@naver.com", "kim1234");
		System.out.println("\n===== 1. 회원 리스트(연번, 이름, 전화번호, 이메일, 비밀번호) =====");
		getList("회원");
		
		
		/** 2. 카테고리 리스트 - 3개 */
//		insert("인문");
//		insert("예술");
//		insert("컴퓨터/IT");
		System.out.println("\n===== 2. 카테고리 리스트(연번, 분류) =====");
		getList("카테고리");
		
		
		/** 3. 상품 리스트 - 3개 */
		System.out.println("\n===== 3. 상품 리스트(연번, 제목, 가격, 카테고리) =====");
//		insert("Hamlet", 7100, 1);
//		insert("In Paris", 26250, 2);
//		insert("Effective Java", 36000, 3);
		getList("상품");
		
		
		/** 4. 카트 리스트 - 2개 */
		/** 5. 주문 리스트 - 1개 */
		/** 6. 주문 도서 리스트 - 2개 */
	}
	
	public static void getList(String keyword) {
		if ("회원".equals(keyword)) {
			List<MemberVO> list = new MemberDAO().getList();

			for (MemberVO vo : list) {
				System.out.println(vo);
			}
		}
		
		if ("카테고리".equals(keyword)) {
			List<CategoryVO> list = new CategoryDAO().getList();
			
			for (CategoryVO vo : list) {
				System.out.println(vo);
			}
		}
		
		if ("상품".equals(keyword)) {
			List<BookVO> list = new BookDAO().getList();
			
			for (BookVO vo : list) {
				System.out.println(vo);
			}
		}
		
		return;
	}
	
	public static void insert(String title, int price, long categoryNo) {
		BookVO vo = new BookVO();
		vo.setTitle(title);
		vo.setPrice(price);
		vo.setCategoryNo(categoryNo);
		new BookDAO().insert(vo);
	}
	
	public static void insert(String subject) {
		CategoryVO vo = new CategoryVO();
		vo.setSubject(subject);		
		new CategoryDAO().insert(vo);
	}
	
	public static void insert(String name, String tel, String email, String passwd) {
		MemberVO vo = new MemberVO();
		vo.setName(name);
		vo.setTel(tel);
		vo.setEmail(email);
		vo.setPasswd(passwd);				
		new MemberDAO().insert(vo);
	}

}
