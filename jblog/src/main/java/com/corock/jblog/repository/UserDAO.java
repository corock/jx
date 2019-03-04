package com.corock.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.corock.jblog.vo.UserVO;

@Repository
public class UserDAO {

	@Autowired
	private SqlSession sqlSession;
	
	public void insert( UserVO userVo ) {
		sqlSession.insert( "user.insert", userVo );
	}

}
