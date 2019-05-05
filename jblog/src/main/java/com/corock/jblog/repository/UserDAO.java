package com.corock.jblog.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.corock.jblog.service.UserService;
import com.corock.jblog.vo.UserVO;

@Repository
public class UserDAO {

	@Autowired
	private SqlSession sqlSession;
	
	public int insert( UserVO userVo ) {
		return sqlSession.insert( "user.insert", userVo );
	}

	public UserVO get(String id, String password) {
		Map<String, String> map = new HashMap<>();
		map.put( "id", id );
		map.put( "password", password );
		
		return sqlSession.selectOne( "user.getByEmailAndPassword", map );
	}

	/**
	 * @see {@link UserService#getNoById}
	 */	
	public Long get( String id ) {
		return sqlSession.selectOne( "user.getById", id );
	}

}
