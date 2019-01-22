package com.corock.bookmall.test;

import java.util.List;

import com.corock.bookmall.dao.CartDAO;
import com.corock.bookmall.vo.CartVO;

public class CartDAOTest {

	public static void main(String[] args) {
//		insertTest();
		getListTest();
	}

	private static void getListTest() {
		System.out.println("카트 리스트\n");

		List<CartVO> list = new CartDAO().getList();
		for (CartVO vo : list) {
			System.out.println(vo);
		}

		System.out.println("==================================================");
	}


	private static void insertTest() {
		/** constructor args: @customerNo, @bookNo, @count */
		CartVO cartVo = new CartVO(1, 1, 2);
		new CartDAO().insert(cartVo);

		cartVo = new CartVO(1, 2, 3);
		new CartDAO().insert(cartVo);
	}

}
