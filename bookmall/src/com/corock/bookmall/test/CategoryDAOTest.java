package com.corock.bookmall.test;

import java.util.List;

import com.corock.bookmall.dao.CategoryDAO;
import com.corock.bookmall.dao.MemberDAO;
import com.corock.bookmall.vo.CategoryVO;
import com.corock.bookmall.vo.MemberVO;

public class CategoryDAOTest {

	public static void main(String[] args) {
//		insertTest();
		getListTest();
	}

	private static void getListTest() {
		System.out.println("카테고리 리스트\n");
		
		List<CategoryVO> list = new CategoryDAO().getList();
		for (CategoryVO vo : list) {
			System.out.println(vo);
		}
		
		System.out.println("==================================================");
	}

	private static void insertTest() {
		CategoryVO vo = new CategoryVO("소설");
		new CategoryDAO().insert(vo);

		vo = new CategoryVO("예술");
		new CategoryDAO().insert(vo);
		
		vo = new CategoryVO("컴퓨터/IT");
		new CategoryDAO().insert(vo);
	}
	
}
