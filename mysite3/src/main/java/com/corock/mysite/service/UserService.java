package com.corock.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corock.mysite.repository.UserDAO;
import com.corock.mysite.vo.UserVO;

@Service
public class UserService {

	@Autowired
	private UserDAO userDao;

	public void join( UserVO userVo ) {
		// 1. insert into DB membership information
		userDao.insert( userVo );
		
		// 2. send to email to confirm address
	}
	
}
