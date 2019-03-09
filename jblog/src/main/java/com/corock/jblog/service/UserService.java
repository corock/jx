package com.corock.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corock.jblog.repository.BlogDAO;
import com.corock.jblog.repository.CategoryDAO;
import com.corock.jblog.repository.UserDAO;
import com.corock.jblog.vo.UserVO;
import com.corock.security.AuthLoginInterceptor;

@Service
public class UserService {

	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private BlogDAO blogDao;

	@Autowired
	private CategoryDAO categoryDao;
	
	public void join( UserVO userVo ) {
		// 1. Create a user
		userDao.insert( userVo );
		Long userNo = userDao.get( userVo.getId() );
		
		// 2. Create your blog simultaneously
		blogDao.insert( userNo );
		
		// 3. Create default category automatically
		categoryDao.insert( userNo );
	}

	/**
	 * @see {@link AuthLoginInterceptor}
	 */
	public UserVO getUser( String id, String password ) {
		return userDao.get( id, password );
	}

	public Long getNoById( String id ) {
		return userDao.get( id );
	}

}
