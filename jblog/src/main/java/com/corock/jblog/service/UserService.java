package com.corock.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corock.jblog.repository.UserDAO;
import com.corock.jblog.vo.UserVO;

@Service
public class UserService {

	@Autowired
	private UserDAO userDao;
	
	public void join( UserVO userVo ) {
		userDao.insert( userVo );
	}

	/**
	 * @see #AuthLoginInterceptor
	 */
	public UserVO getUser( String id, String password ) {
		return userDao.get( id, password );
	}

}
