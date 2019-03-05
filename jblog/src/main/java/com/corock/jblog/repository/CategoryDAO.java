package com.corock.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.corock.jblog.service.UserService;

@Repository
public class CategoryDAO {

	@Autowired
	private SqlSession sqlSession;

	/**
	 * @see {@link UserService#join}
	 */
	public int insert(Long userNo) {
		return sqlSession.insert( "category.insert", userNo );
	}
	
	
	
}
