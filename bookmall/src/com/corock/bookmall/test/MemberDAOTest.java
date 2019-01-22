package com.corock.bookmall.test;

import java.util.List;

import com.corock.bookmall.dao.MemberDAO;
import com.corock.bookmall.vo.MemberVO;

public class MemberDAOTest {

	public static void main(String[] args) {
//		insertTest();
		getListTest();
	}

	private static void getListTest() {
		System.out.println("회원정보\n");
		
		List<MemberVO> list = new MemberDAO().getList();
		for (MemberVO vo : list) {
			System.out.println(vo);
		}
		
		System.out.println("==================================================");
	}

	private static void insertTest() {
		MemberVO memberVo = new MemberVO("이도훈", "010-9876-5432", "coco@gmail.com", "******");
		new MemberDAO().insert(memberVo);
		
		memberVo = new MemberVO("김원준", "010-2345-6789", "kim@naver.com", "********");
		new MemberDAO().insert(memberVo);
	}
	
}
