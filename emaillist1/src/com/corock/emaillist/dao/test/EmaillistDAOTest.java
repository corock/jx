package com.corock.emaillist.dao.test;

import java.util.List;

import com.corock.emaillist.dao.EmaillistDAO;
import com.corock.emaillist.vo.EmaillistVO;

public class EmaillistDAOTest {

	public static void main(String[] args) {
		getListTest();
	}
	
	public static void getListTest() {
		List<EmaillistVO> list = new EmaillistDAO().getList();
		
		for (EmaillistVO vo : list) {
			System.out.println(vo);
		}
	}

}
