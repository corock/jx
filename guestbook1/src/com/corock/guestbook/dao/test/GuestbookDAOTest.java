package com.corock.guestbook.dao.test;

import java.util.List;

import com.corock.guestbook.dao.GuestbookDAO;
import com.corock.guestbook.vo.GuestbookVO;

public class GuestbookDAOTest {

	public static void main(String[] args) {
		insertTest();
		getListTest();
//		deleteTest();
	}

	private static void deleteTest() {
		GuestbookVO guestbookVo = new GuestbookVO(1, "1234");
		new GuestbookDAO().delete(guestbookVo);
	}
	
	private static void getListTest() {
		List<GuestbookVO> list = new GuestbookDAO().getList();
		
		for (GuestbookVO vo : list) {
			System.out.println(vo);
		}
	}

	private static void insertTest() {
		GuestbookVO guestbookVo = new GuestbookVO("coco", "1234", "hello!");
		new GuestbookDAO().insert(guestbookVo);
	}
	
}
