package com.corock.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corock.mysite.repository.UserDAO;
import com.corock.mysite.vo.UserVO;

@Service
public class UserService {

	@Autowired
	private UserDAO userDao;

	public boolean existEmail( String email ) {
		UserVO userVo = userDao.get( email );
		return userVo != null;
	}
	
	public void join( UserVO userVo ) {
		// 1. Insert into DB membership information
		userDao.insert( userVo );
		
		// 2. Send to email to confirm address
	}

	public UserVO getUser( String email, String password ) {
		return userDao.get( email, password );
	}

	public UserVO getUser( Long no ) {
		return userDao.get( no );
	}

	public boolean modifyUser( UserVO userVo ) {
		return userDao.update( userVo ) == 1;
	}
	
}
