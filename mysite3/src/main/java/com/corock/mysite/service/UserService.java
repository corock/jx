package com.corock.mysite.service;

import javax.servlet.http.HttpSession;

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

	public UserVO getUser( UserVO vo ) {
		UserVO userVo = userDao.get( vo.getEmail(), vo.getPassword() );
		return userVo;
	}

	public UserVO getUser( long no ) {
		UserVO userVo = userDao.get( no );
		return userVo;
	}

	public void logout( HttpSession session ) {
		if ( session != null && session.getAttribute("authUser") != null ) {
			// logout processing
			session.removeAttribute( "authUser" );
			session.invalidate();
			return;
		}
	}

	public void modify( UserVO userVo ) {
		userDao.update( userVo );
	}
	
}
